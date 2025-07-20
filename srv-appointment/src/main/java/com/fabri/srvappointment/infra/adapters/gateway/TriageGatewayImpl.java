package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.infra.externals.persistence.TriageJpaRepository;
import com.fabri.srvappointment.infra.externals.persistence.entity.TriageEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TriageGatewayImpl implements ITriageGateway {

    private final TriageJpaRepository repository;

    @Override
    public Triage save(Triage triage) {
        return Triage.from(repository.save(TriageEntity.from(triage)));
    }

    @Override
    public Triage getById(Long triageId) {
        TriageEntity entity = repository.findById(triageId).orElseThrow(EntityNotFoundException::new);
        return Triage.from(entity);
    }

}
