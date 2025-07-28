package com.fabri.srvappointment.infra.utils;

import com.fabri.srvappointment.domain.event.FinishedAppointmentEvent;
import com.fabri.srvappointment.domain.event.ScheduledAppointmentEvent;
import com.fabri.srvappointment.domain.event.StartedPatientTriageEvent;
import com.fabri.srvmessagebroker.domain.vo.EmailNotificationQueue;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EmailNotificationFactory {

    public EmailNotificationQueue patientCheckedIn(StartedPatientTriageEvent event) {
        var patientTriage = event.getPatientTriage();
        var patient = patientTriage.getPatient();
        String appointmentDate = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .format(patientTriage.getAppointmentDate().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return new EmailNotificationQueue(
                patient.getPatientEmail(),
                "Check-In Pending Doctor Approval",
                String.format("""
                        Dear, %s, Hold on tight and get ready for your medical appointment for %s!
                        
                        We are excited to inform you that your check-in is pending approval from your doctor.
                    
                        If you have any questions or need to reschedule, please feel free to contact us at least
                        24 hours in advance.
                        
                        We appreciate your patience and understanding during this process and We gonna reach out to you
                        as soon as the doctor approves your check-in.
                        
                        Thank you for choosing ConectarSaude, and we look forward to seeing you soon!
                        
                        ConectarSaude Team, Regards.
                        
                        """, patient.getPatientName(), appointmentDate)
        );
    }

    public EmailNotificationQueue doctorApprovalPending(StartedPatientTriageEvent event) {
        var patientTriage = event.getPatientTriage();
        var doctor = patientTriage.getDoctor();
        String appointmentDate =  DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm")
                .format(patientTriage.getAppointmentDate().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return new EmailNotificationQueue(
                doctor.getDoctorEmail(),
                "Patient Check-In Approval Pending",
                String.format("""
                        Dear, %s
                        
                        It's time to be a hero in another patient's life!
                        
                        You have a pending approval for a patient check-in.
                        The appointment is scheduled for %s.
                        
                        Please review the details and take the necessary action.
                        
                        ConectarSaude Team, Regards.
                        
                        """, doctor.getDoctorName(), appointmentDate)
        );
    }

    public EmailNotificationQueue patientScheduled(ScheduledAppointmentEvent event) {
        return new EmailNotificationQueue(
                event.getPatientEmail(),
                "Appointment Scheduled - ConectarSaude",
                String.format("""
                        Dear, %s,
                        
                        We are pleased to inform you that your appointment has been successfully scheduled.
                        The appointment is set for %s.
                        
                        If you have any questions or need to reschedule, please feel free to contact us at least
                        24 hours in advance.
                        
                        Thank you for choosing ConectarSaude, and we look forward to seeing you soon!
                        
                        ConectarSaude Team, Regards.
                        
                        """, event.getPatientName(), event.getAppointmentAt())
        );
    }

    public EmailNotificationQueue doctorAppointmentReminder(ScheduledAppointmentEvent event) {
        return new EmailNotificationQueue(
                event.getDoctorEmail(),
                "Appointment Reminder - ConectarSaude",
                String.format("""
                        Dear, %s,
                        
                        This is a friendly reminder for your upcoming appointment with patient %s.
                        The appointment is scheduled for %s.
                        
                        Please ensure you are prepared and available at the scheduled time.
                        
                        Thank you for your dedication to patient care!
                        
                        ConectarSaude Team, Regards.
                        
                        """, event.getDoctorName(), event.getPatientName(), event.getAppointmentAt())
        );
    }

    public EmailNotificationQueue finishedPatientAppointment(FinishedAppointmentEvent event) {
        var appointment = event.getAppointment();
        var triage = appointment.getTriage();
        var patient = triage.getPatient();
        var doctor = triage.getDoctor();

       return new EmailNotificationQueue(
                patient.getPatientEmail(),
                "Appointment Finished - ConectarSaude",
                String.format("""
                                Dear, %s,
                                
                                We are pleased to inform you that your appointment with Dr. %s has been successfully completed.
                                We hope you found the consultation helpful and informative.
                                
                                Let's see what the Doctor prescribed for you:
                                %s
                                
                                To help you on getting the medicines, we have also provided the list of best pharmacies in your area
                                and that can deliver the medicines to your home, Here is their contact information:
                                %s
                                
                                Also to help you on getting the exams, we have also provided the list of best laboratories in your area
                                and that can perform the exams, Here is their contact information:
                                %s
                                
                                So please make sure to follow the doctor's recommendations and take any prescribed medications as directed.
                                
                                If you have any follow-up questions or need further assistance, please feel free to reach out.
                                
                                Thank you for choosing ConectarSaude, and we look forward to serving you again in the future!
                                
                                ConectarSaude Team, Regards.
                                
                                """,
                        patient.getPatientName(),
                        doctor.getDoctorName(),
                        appointment.getDoctorPrescription().toText(),
                        "Pharmacies: ",
                        "Laboratories: ")
        );
    }

}