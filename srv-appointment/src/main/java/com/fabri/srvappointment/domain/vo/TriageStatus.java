package com.fabri.srvappointment.domain.vo;

public enum TriageStatus {

    PENDING_DOCTOR_APPROVAL,
    SCHEDULING_APPOINTMENT,
    SCHEDULED_APPOINTMENT,
    CANCELLED,
    ERROR;

    public boolean isScheduledAppointment() {
        return this == SCHEDULED_APPOINTMENT;
    }

    public boolean isCancelled() {
        return this == CANCELLED;
    }

    public boolean isError() {
        return this == ERROR;
    }

    public boolean isSchedulingAppointment() {
        return this == SCHEDULING_APPOINTMENT;
    }

    public boolean isPegingDoctorApproval() {
        return this == PENDING_DOCTOR_APPROVAL;
    }

}
