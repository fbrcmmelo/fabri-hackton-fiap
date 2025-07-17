package com.fabri.srv.user.infra.events.user;

import com.fabri.srv.user.domain.user.events.ActivatedDoctorEvent;
import com.fabri.srv.user.infra.adapters.EmailSenderAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ActivatedDoctorListener {

    private final EmailSenderAdapter email;

    @EventListener
    public void activatedDoctorEvent(final ActivatedDoctorEvent event) {
      log.info("Doctor activated by user of id: {}", event.getAdminId());
        String message = String.format("""
                Dear Doctor, %s
                
                It's a honor to us having you here with us again at ConectarSaude System.
                
                Fell yourself fully embraced for us, we wish you our sincerely congratulations !
                
                So, Let's uptade you, your account has been successfully activated, and now you can access the system,
                and more than that, you can start to help us to make the world a better place. We are very happy to have
                you with us, and we are sure that you will make a difference in the lives of many people.
                
                If you have any questions or need assistance, please do not hesitate to contact us at
                
                Here it is your username: %s
                If you have forgotten your password, you can reach out to us, and we will help you to reset it.
                
                ConectarSaude Support Team.
                
                Best regards,
                ConectarSaude Team
                
                """, event.getName(), event.getUserName());

        email.sendEmail(event.getEmail(), message);
    }
}
