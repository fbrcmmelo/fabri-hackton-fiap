package com.fabri.srvappointment.domain.gateway;

import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.vo.TriageStatus;

import java.util.List;

public interface ITriageGateway {

    Triage save(Triage triage);

    Triage getById(Long triageId);

    boolean getByPatientIdAndDoctorIdAndStatusIn(Long patientId, Long doctorId, List<TriageStatus> statusLit);

    void updateStatus(Long triageId, TriageStatus triageStatus);
}
