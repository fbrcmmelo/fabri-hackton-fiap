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
                Dear, %s
                
                We would like to wish you a warm Welcome to ConectarSaude System !
                
                We are are proud to help you give this next step forward to a future better.
                Future which we figure out you achieving more health to you and your family in an easily way,
                By the way, It's your right as a citizen who pay so much taxes.
                
                Now we will stay very close of you, ensuring that you may stay healthy. <3
                
                By here you will receive important information such as notifications about:
                - Your health data;
                - Your health appointments;
                - Your health prescriptions;
                - Your scheduled health exams;
                - Your health treatments
                
                And tips to help you to stay healthy, such as:
                - How to prevent diseases;
                - How to take care of your health;
                - How to take care of your family health;
                - How to take care of your mental health;
                - How to take care of your physical health;
                - How to take care of your emotional health;
                
                And many other important informations that we will be sending to you.
                
                Now we are going to remain this channel in order to communicate with you now.
                Stay safe and healthy, See you soon !
                
                Best regards,
                ConectarSaude Team
                
                """, event.getName());

        this.email.sendEmail(event.getEmail(), message);
    }
}
