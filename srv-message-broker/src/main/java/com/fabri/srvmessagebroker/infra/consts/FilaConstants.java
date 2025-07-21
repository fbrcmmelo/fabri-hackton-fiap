package com.fabri.srvmessagebroker.infra.consts;

public final class FilaConstants {

    public static final String STARTED_PATIENT_TRIAGE = "started-patient-triage-queue";
    public static final String FINISHED_PATIENT_APPOINTMENT = "finished-patient-appointment-queue";
    public static final String PATIENT_EMAIL_NOTIFICATION = "patient-notification-queue";
    public static final String FINISHED_PATIENT_TRIAGE = "finished-patient-triage-queue";
    public static final String DOCTOR_EMAIL_NOTIFICATION = "doctor-notification-queue";

    FilaConstants() {
        throw new IllegalStateException("Utility class");
    }
}
