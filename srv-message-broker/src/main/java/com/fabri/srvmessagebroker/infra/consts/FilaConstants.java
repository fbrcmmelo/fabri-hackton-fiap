package com.fabri.srvmessagebroker.infra.consts;

public final class FilaConstants {

    public static final String EMR_TRIAGE = "emr-triage-queue";
    public static final String EMR_APPOINTMENT = "emr-appointment-queue";
    public static final String PATIENT_EMAIL_NOTIFICATION = "patient-notification-queue";
    public static final String PATIENT_FINISHED_TRIAGE = "finished-patient-triage-queue";
    public static final String DOCTOR_EMAIL_NOTIFICATION = "doctor-notification-queue";

    FilaConstants() {
        throw new IllegalStateException("Utility class");
    }
}
