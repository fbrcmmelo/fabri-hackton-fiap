package com.fabri.srv.user.infra.adapters.controller;

import com.fabri.srv.user.application.*;
import com.fabri.srv.user.application.dto.*;
import com.fabri.srv.user.infra.adapters.controller.dto.*;
import com.fabri.srv.user.infra.api.user.SaveNextDoctorAppointmentRequest;
import com.fabri.srv.user.infra.utils.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {

    @Value("${security.crypto-key}")
    private String encryptionKey;

    private final FindUserLoginUseCase findUserUseCase;
    private final RegisterPatientUseCase registerPatientUseCase;
    private final RegisterDoctorUseCase registerDoctorUseCase;
    private final ActivateDoctorUseCase activateDoctorUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final FindDoctorByIdUseCase findDoctorByIdUseCase;
    private final SaveNextDoctorAppointmentUseCase saveNextDoctorAppointmentUseCase;

    public UserDTO findByUsernameAndPass(AuthRequest request) {
        var input = new UserLoginInput(request.username(), request.password());
        var output = findUserUseCase.execute(input);
        String encryptedEmal = CryptoUtil.encrypt(output.email(), CryptoUtil.generateKey(encryptionKey));
        return new UserDTO(output.id(), output.name(), encryptedEmal, output.roles(), output.nextAppointmentTime());
    }

    public UserDTO registerPatient(UserRegisterRequest request) {
        var input = RegisterUserInput.from(request);
        var output = registerPatientUseCase.execute(input);
        String encryptedEmal = CryptoUtil.encrypt(output.email(), CryptoUtil.generateKey(encryptionKey));
        return new UserDTO(output.id(), output.name(), encryptedEmal, output.roles(), output.nextAppointmentTime());
    }

    public UserDTO registerDoctor(DoctorRegisterRequest request) {
        var input = RegisterDoctorInput.from(request);
        var output = registerDoctorUseCase.execute(input);
        String encryptedEmal = CryptoUtil.encrypt(output.email(), CryptoUtil.generateKey(encryptionKey));
        return new UserDTO(output.id(), output.name(), encryptedEmal, output.roles(), output.nextAppointmentTime());
    }

    public UserDTO activateDoctor(ActivateDoctorRequest request) {
        var input = ActivateDoctorInput.fromRequest(request);
        var output = activateDoctorUseCase.execute(input);

        String encryptedEmal = CryptoUtil.encrypt(output.email(), CryptoUtil.generateKey(encryptionKey));
        return new UserDTO(output.id(), output.name(), encryptedEmal, output.roles(), output.nextAppointmentTime());
    }

    public UserDTO getUserById(String userId) {
        var output = findUserByIdUseCase.execute(userId);
        String encryptedEmal = CryptoUtil.encrypt(output.email(), CryptoUtil.generateKey(encryptionKey));
        return new UserDTO(output.id(), output.name(), encryptedEmal, output.roles(), output.nextAppointmentTime());
    }

    public void saveNextDoctorAppointment(Long doctorId, SaveNextDoctorAppointmentRequest request) {
        var input = new SaveNextAppointmentInput(doctorId, request.getTriageId(), request.getAppointmentTime());
        saveNextDoctorAppointmentUseCase.execute(input);
    }

    public DoctorDTO getDoctorById(String userId) {
        var output = findDoctorByIdUseCase.execute(userId);
        String encryptedEmal = CryptoUtil.encrypt(output.getEmail(), CryptoUtil.generateKey(encryptionKey));
        String encryptedCrm = CryptoUtil.encrypt(output.getCrm(), CryptoUtil.generateKey(encryptionKey));

        return new DoctorDTO(
                output.getId(),
                output.getName(),
                output.getSpecialization(),
                encryptedEmal,
                encryptedCrm,
                output.getNextAppointmentTime());
    }
}
