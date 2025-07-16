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
public class RegisteredUserListener {

    private final EmailSenderAdapter email;

    @EventListener
    public void userRegistered(final RegisteredUserEvent event) {
        log.info("User of id: {} and roles: {} has been registered", event.getId(), event.getRoles());

        String message = String.format("""
                Dear, %s, we would like to wish you a warm Welcome to ConectarSaude System !
                
                We are are proud to help you give this next step forward to a future better, Future
                which we figure out you achieving more health to you and your family in an easily way,
                by the way, It's your right as a citizen who pay so much taxes.
                
                We gonna remain using this channel in order to communicate with you now, stay safe and healthy !
                
                ConectarSaude Team, regardless.
                
                """, event.getName());

        this.email.sendEmail(event.getEmail(), message);
    }
}
