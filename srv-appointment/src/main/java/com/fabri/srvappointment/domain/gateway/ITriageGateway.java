package com.fabri.srvappointment.domain.gateway;

import com.fabri.srvappointment.domain.Triage;

public interface ITriageGateway {

    Triage save(Triage triage);

    Triage getById(Long triageId);

}
