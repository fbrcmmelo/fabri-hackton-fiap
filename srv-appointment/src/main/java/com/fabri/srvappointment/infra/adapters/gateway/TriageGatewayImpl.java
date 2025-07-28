package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.gateway.ITriageGateway;
import com.fabri.srvappointment.domain.vo.TriageStatus;
import com.fabri.srvappointment.infra.externals.persistence.TriageJpaRepository;
import com.fabri.srvappointment.infra.externals.persistence.entity.TriageEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
        TriageEntity entity = repository.findById(triageId)
                .orElseThrow(EntityNotFoundException::new);

        return Triage.from(entity);
    }

    @Override
    public boolean getByPatientIdAndDoctorIdAndStatusIn(Long patientId, Long doctorId,
                                                        List<TriageStatus> statusLit) {
        return repository.existsByPatientIdAndDoctorIdAndTriageStatusIsIn(
                patientId, doctorId, statusLit
        );
    }

    @Override
    public void updateStatus(Long triageId, TriageStatus triageStatus) {
        TriageEntity triageEntity = repository.findById(triageId)
                .orElseThrow(EntityNotFoundException::new);

        triageEntity.setTriageStatus(triageStatus);
        repository.save(triageEntity);
    }

}
