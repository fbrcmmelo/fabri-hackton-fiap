package com.fabri.srv.user.infra.adapters.controller;

import com.fabri.srv.user.application.*;
import com.fabri.srv.user.application.dto.*;
import com.fabri.srv.user.infra.adapters.controller.dto.*;
import com.fabri.srv.user.infra.api.user.SaveNextDoctorAppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {

    private final FindUserLoginUseCase findUserUseCase;
    private final RegisterPatientUseCase registerPatientUseCase;
    private final RegisterDoctorUseCase registerDoctorUseCase;
    private final ActivateDoctorUseCase activateDoctorUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final SaveNextDoctorAppointmentUseCase saveNextDoctorAppointmentUseCase;

    public UserDTO findByUsernameAndPass(AuthRequest request) {
        var input = new UserLoginInput(request.username(), request.password());
        var output = findUserUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO registerPatient(UserRegisterRequest request) {
        var input = RegisterUserInput.from(request);
        var output = registerPatientUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO registerDoctor(DoctorRegisterRequest request) {
        var input = RegisterDoctorInput.from(request);
        var output = registerDoctorUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO activateDoctor(ActivateDoctorRequest request) {
        var input = ActivateDoctorInput.fromRequest(request);
        var output = activateDoctorUseCase.execute(input);

        return UserDTO.from(output);
    }

    public UserDTO getUserById(String userId) {
        var output = findUserByIdUseCase.execute(userId);
        return UserDTO.from(output);
    }

    public void saveNextDoctorAppointment(Long doctorId, SaveNextDoctorAppointmentRequest request) {
        var input = new SaveNextAppointmentInput(doctorId, request.getTriageId(), request.getNextAvailableAppointment());
        saveNextDoctorAppointmentUseCase.execute(input);
    }
}
