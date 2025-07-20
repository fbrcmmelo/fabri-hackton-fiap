package com.fabri.srvappointment.infra.client;

import com.fabri.srvappointment.domain.vo.DoctorOutput;
import com.fabri.srvappointment.infra.client.user.UserOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "srv-api-gateway", path = "/api/v1/users")
public interface UserClient {

    @GetMapping("/{id}")
    UserOutput findById(@PathVariable String id);

    @GetMapping("/doctors/{id}")
    DoctorOutput getDoctor(@PathVariable String id);
}
