package com.fabri.srvappointment.domain.event;

import com.fabri.srvappointment.domain.Appointment;
import com.fabri.srvappointment.domain.IDomainEvent;
import com.fabri.srvappointment.domain.Triage;
import com.fabri.srvappointment.domain.vo.Doctor;
import com.fabri.srvappointment.domain.vo.Patient;
import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
public class ScheduledAppointmentEvent implements IDomainEvent {

    private final Long appointmentId;
    private final Long triageId;
    private final String patientName;
    private final String patientEmail;
    private final String doctorName;
    private final String doctorEmail;
    private final String appointmentAt;

    public ScheduledAppointmentEvent(Appointment appointment) {
        Triage triage = appointment.getTriage();
        this.triageId = triage.getId();
        this.appointmentId = appointment.getAppointmentId();

        Patient patient = triage.getPatient();
        this.patientName = patient.getPatientName();
        this.patientEmail = patient.getPatientEmail();

        Doctor doctor = triage.getDoctor();
        this.doctorName = doctor.getDoctorName();
        this.doctorEmail = doctor.getDoctorEmail();

        this.appointmentAt = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .format(triage.getAppointmentDate().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    @Override
    public String toString() {
        return "Appointment scheduled Event";
    }
}
