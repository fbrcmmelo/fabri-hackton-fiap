package com.fabri.srv.oauth.domain.user;

import com.fabri.srv.oauth.infra.clients.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String roles = "NONE";

    public static User fromDTO(UserDTO body) {
        User user = new User();
        user.setId(body.id());
        user.setName(body.name());
        user.setRoles(body.roles());

        return user;
    }
}
