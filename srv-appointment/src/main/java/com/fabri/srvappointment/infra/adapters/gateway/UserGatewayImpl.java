package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import com.fabri.srvappointment.infra.client.UserClient;
import com.fabri.srvappointment.infra.client.user.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements IUserGateway {

    private final UserClient client;

    @Override
    public UserOutput getUser(String userId) {
        return client.findById(userId);
    }

    @Override
    public void saveNextAvailableAppointment(SaveNextAppointment doctor) {
        client.saveNextDoctorAppointment(doctor.getDoctorId(), doctor);
    }

}
