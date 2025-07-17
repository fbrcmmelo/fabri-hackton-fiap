package com.fabri.srv.user.infra.events.user;

import com.fabri.srv.user.domain.user.events.RegisteredUserEvent;
import com.fabri.srv.user.infra.adapters.EmailSenderAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisteredDoctorListener {

    private final EmailSenderAdapter email;

    @EventListener
    public void userRegistered(final RegisteredUserEvent event) {
        log.info("User of id: {} and roles: {} has been registered", event.getId(), event.getRoles());

        String message = String.format("""
                Dear, Doctor %s
                
                We are thrilled to welcome you to the ConectarSaude System!
                Your registration has been successfully completed, and we are excited to have you on board.
                As a doctor, you play a crucial role in our mission to provide quality healthcare services.
                
                Your expertise and dedication are invaluable to us, and we look forward to working together
                to make a positive impact on the lives of our patients.
                
                Right now, your account is not activated yet, but don't worry,
                we will send you another email as soon as it is activated.
                
                In the meantime, if you have any questions or need assistance, please feel free to reach out to us at
                
              
                ConectarSaude Team, Regards.
                
                """, event.getName());

        this.email.sendEmail(event.getEmail(), message);
    }
}
