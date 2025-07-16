package com.fabri.srv.user.domain.user;

import com.fabri.srv.user.application.dto.RegisterDoctorInput;
import com.fabri.srv.user.domain.user.vo.DoctorCRM;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Doctor extends User {

    DoctorCRM crm;

    public Doctor(Long id, RegisterDoctorInput input, DoctorCRM crm) {
        super(id, input);
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(crm, "CRM cannot be null");

        this.crm = crm;
    }
}
