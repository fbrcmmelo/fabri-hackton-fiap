package com.fabri.srvnotification.infra.utils;

import com.fabri.srvnotification.infra.exceptions.EmailSenderException;

public class MailValidatorUtils {

    MailValidatorUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void validate(String email) throws EmailSenderException {
        if (email == null || email.isEmpty()) {
            throw new EmailSenderException(email);
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new EmailSenderException(email);
        }

    }
}
