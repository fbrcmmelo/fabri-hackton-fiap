package com.fabri.srvappointment.domain.gateway;

import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import com.fabri.srvappointment.infra.client.user.UserOutput;

public interface IUserGateway {

    UserOutput getUser(String userId);

    void saveNextAvailableAppointment(SaveNextAppointment doctor);
}
