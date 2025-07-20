package com.fabri.srv.user.domain.user.gateway;

import com.fabri.srv.user.domain.user.Role;
import com.fabri.srv.user.domain.user.vo.RoleEnum;

public interface RoleGateway {
    Role byEnum(RoleEnum roleEnum);
}
