package com.fabri.srv.user.application;

import com.fabri.srv.user.application.dto.DoctorOutput;
import com.fabri.srv.user.infra.IOUseCase;

public interface FindDoctorByIdUseCase extends IOUseCase<String, DoctorOutput> {
}
