package com.fabri.srvappointment.infra.client;

import com.fabri.srvappointment.infra.adapters.controller.dto.SaveNextAppointment;
import com.fabri.srvappointment.infra.client.user.UserOutput;
import com.fabri.srvappointment.infra.config.FeignAuthConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "srv-api-gateway", path = "/srv-user/api/v1/users", configuration = FeignAuthConfig.class)
public interface UserClient {

    @GetMapping("/{userId}")
    UserOutput findById(@PathVariable String userId);

    @GetMapping("/doctors/{doctorId}")
    UserOutput doctorById(@PathVariable String doctorId);

    @PutMapping("/doctors/{doctorId}/next-appointment")
    boolean saveNextDoctorAppointment(@PathVariable Long doctorId, @RequestBody SaveNextAppointment doctor);
}
