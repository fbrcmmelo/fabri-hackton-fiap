package com.fabri.srvappointment.domain.vo;

public enum TriageStatus {

    PENDING_DOCTOR_APPROVAL,
    APPROVED,
    CANCELLED,
    ERROR;

    public boolean isApproved() {
        return this == APPROVED;
    }

    public boolean isCancelled() {
        return this == CANCELLED;
    }

    public boolean isError() {
        return this == ERROR;
    }
}
