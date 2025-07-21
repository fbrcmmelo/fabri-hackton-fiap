package com.fabri.srvpatienthistoryregistry.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoctorOutput {

    private String doctorId;
    private String doctorName;
    private String description;
    private String doctorEmail;
    private String doctorCrm;
}
