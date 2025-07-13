package com.fabri.srv.oauth.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Role implements Serializable {
    private Long id;
    private String name;
}
