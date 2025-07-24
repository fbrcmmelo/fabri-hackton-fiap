package com.fabri.srvappointment.infra.adapters.gateway;

import com.fabri.srvappointment.domain.gateway.IUserGateway;
import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import com.fabri.srvappointment.infra.client.UserClient;
import com.fabri.srvappointment.infra.client.user.UserOutput;
import com.fabri.srvappointment.infra.utils.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements IUserGateway {

    private final UserClient client;
    @Value("${security.crypto-key}")
    private String encryptionKey;

    @Override
    public UserOutput getUser(String userId) {
        UserOutput byId = client.findById(userId);
        String emailDecrypted = CryptoUtil.decrypt(byId.getEmail(), CryptoUtil.generateKey(encryptionKey));
        byId.setEmail(emailDecrypted);

        return byId;
    }

    @Override
    public void saveNextAvailableAppointment(SaveNextAppointment doctor) {
        client.saveNextDoctorAppointment(doctor.getDoctorId(), doctor);
    }

}
