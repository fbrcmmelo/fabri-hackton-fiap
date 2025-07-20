package com.fabri.srvappointment.domain.gateway;

import com.fabri.srvappointment.domain.vo.DoctorOutput;
import com.fabri.srvappointment.infra.client.user.UserOutput;

public interface IUserGateway {

    UserOutput getUser(String userId);

    DoctorOutput getDoctor(String userId);
}
