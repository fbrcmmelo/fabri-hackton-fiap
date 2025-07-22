package com.fabri.srv.user.infra.persistence.user;

import com.fabri.srv.user.domain.user.User;
import com.fabri.srv.user.domain.user.vo.Adress;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@DiscriminatorValue("USER")
@Table(name = "tb_user")
public class UserJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(unique = true, nullable = false, length = 14)
    private String cpf;
    private String name;
    private String address;
    private Integer number;
    private String city;
    private String state;

    @Version
    private Long version;

    @ManyToMany(fetch =  FetchType.EAGER)
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    public UserJpaEntity(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail().getValue();
        this.cpf = user.getCpf().getCpfCnpjSemFormatacao();
        this.name = user.getFullName().getName();

        Adress adress = user.getAdress();
        this.address = adress.getAddress();
        this.number = adress.getNumber();
        this.city = adress.getCity();
        this.state = adress.getState();

        this.roles = user.getRoles().stream()
                .map(RoleEntity::new)
                .toList();
    }
}
