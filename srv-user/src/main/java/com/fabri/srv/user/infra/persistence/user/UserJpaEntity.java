package com.fabri.srv.user.infra.persistence.user;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.vo.Adress;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_user")
public class UserJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String cpf;
    private String name;
    private String address;
    private Integer number;
    private String city;
    private String state;

    @ManyToMany
    @JoinTable(
            name = "tb_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    public UserJpaEntity(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail().getValue();
        this.cpf = user.getCpf().getCpfCnpjSemFormatacao();
        this.name = user.getFullName().toString();

        Adress adress = user.getAdress();
        this.address = adress.getAddress();
        this.number = adress.getNumber();
        this.city = adress.getCity();
        this.state = adress.getState();

        this.roles = user.getRoles().stream()
                .map(RoleEntity::new)
                .collect(Collectors.toSet());
    }
}
