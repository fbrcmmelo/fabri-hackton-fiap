package com.fabri.srv.user.infra.utils;

import com.fabri.srv.user.domain.user.gateway.UserGateway;
import com.fabri.srvmessagebroker.domain.vo.EmailNotificationQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EmailNotificationFactory {

    private final UserGateway userGateway;

    public EmailNotificationQueue welcome(String to) {
        final var user = userGateway.findByEmail(to);
       return new EmailNotificationQueue(
                to,
                "Welcome to ConectarSaude System",
                String.format("""
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
                        
                        """, user.getFullName().getName())
        );
    }

    public EmailNotificationQueue doctorWelcome(String to) {
        final var user = userGateway.findByEmail(to);
        user.validateIfIAmDoctor();

        return new EmailNotificationQueue(
                to,
                "Welcome to ConectarSaude System",
                String.format("""
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
                        
                        """, user.getFullName().getName()));
    }

    public EmailNotificationQueue doctorActivation(String to) {
        final var user = userGateway.findByEmail(to);
        user.validateIfIAmDoctor();

        return new EmailNotificationQueue(
                to,
                "Hey Doctor, your account has been activated",
                String.format("""
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
                        
                        """, user.getFullName().getName(), user.getUsername()));
    }
}