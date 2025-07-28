package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.application.dto.RegisterUserInput;
import com.fabri.srv.user.domain.user.vo.*;
import com.fabri.srv.user.infra.exceptions.DomainException;
import com.fabri.srv.user.infra.persistence.user.UserJpaEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private FullName fullName;
    private Adress adress;
    private Email email;
    private CPF cpf;
    private Long version;
    private Set<Role> roles = new HashSet<>();
    private List<DoctorAppointment> doctorAppointments = new ArrayList<>();

    public User(Long id, @NotNull RegisterUserInput input) {
        final var encryptedPassword = BCrypt.hashpw(input.getPassword(), BCrypt.gensalt());

        this.id = id;
        this.username = input.getUsername();
        this.password = encryptedPassword;
        this.fullName = new FullName(input.getFirstName(), input.getLastName());
        this.cpf = new CPF(input.getCpf());
        this.email = new Email(input.getEmail());
        this.adress = new Adress(input.getNumber(), input.getAddress(), input.getCity(), input.getState());
        this.roles = new HashSet<>();
    }

    public User withRoles(@NotNull Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public static User fromJpaEntity(UserJpaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        var user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setFullName(new FullName(entity.getName()));
        user.setAdress(new Adress(entity.getNumber(), entity.getAddress(), entity.getCity(), entity.getState()));
        user.setEmail(new Email(entity.getEmail()));
        user.setCpf(new CPF(entity.getCpf()));
        user.setVersion(entity.getVersion());

        user.setRoles(entity.getRoles().stream()
                .map(Role::fromJpaEntity)
                .collect(Collectors.toSet()));

        return user;
    }

    public String rolesAsString() {
        return this.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(","));
    }

    public void checkPassword(String pass) {
        boolean wrongPassword = !BCrypt.checkpw(pass, this.password);
        if (wrongPassword) {
            throw new DomainException("Invalid operation");
        }
    }

    public void checkCPF(String cpf) {
        CPF cpfValid = new CPF(cpf);
        if (!this.cpf.getCpfCnpjSemFormatacao()
                .equalsIgnoreCase(cpfValid.getCpfCnpjSemFormatacao())) {
            throw new DomainException("Invalid CPF");
        }
    }

    public void validateIfIAmDoctor() {
        this.roles.stream()
                .filter(role -> Objects.equals(RoleEnum.DOCTOR.name(), role.getName()))
                .findFirst()
                .orElseThrow(() -> new DomainException("User does not have doctor role"));
    }

    public void checkRoleToActiveDoctor() {
        this.roles.stream()
                .map(Role::getName)
                .filter(roleName -> RoleEnum.ADMIN.name().equals(roleName))
                .findFirst()
                .orElseThrow(() -> new DomainException("User does not have permission to activate doctor"));
    }

    public Instant getNextAppointmentTime() {
        return this.doctorAppointments
                .stream().max(Comparator.comparing(DoctorAppointment::getAppointmentTime))
                .orElse(new DoctorAppointment()).getAppointmentTime();
    }
}
