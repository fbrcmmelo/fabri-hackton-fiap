package com.fabri.srvappointment.domain.gateway;

import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import com.fabri.srvappointment.infra.client.user.UserOutput;

public interface IUserGateway {

    UserOutput getUser(String userId);
    UserOutput getDoctor(String doctorId);

    boolean saveNextAvailableAppointment(SaveNextAppointment doctor);
}
