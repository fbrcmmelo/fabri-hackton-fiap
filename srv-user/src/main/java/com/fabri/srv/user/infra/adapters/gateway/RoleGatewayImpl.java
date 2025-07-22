package com.fabri.srv.user.infra.adapters.gateway;

import com.fabri.srv.user.domain.user.Role;
import com.fabri.srv.user.domain.user.gateway.RoleGateway;
import com.fabri.srv.user.domain.user.vo.RoleEnum;
import com.fabri.srv.user.infra.persistence.user.RoleJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleGatewayImpl implements RoleGateway {

    private final RoleJpaRepository jpaRepository;

    @Override
    public Role byEnum(RoleEnum roleEnum) {
        return jpaRepository.findByName(roleEnum.name())
                .map(Role::fromJpaEntity)
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleEnum));
    }
}
