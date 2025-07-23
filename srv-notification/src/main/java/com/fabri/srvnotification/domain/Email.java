package com.fabri.srvnotification.domain;

import com.fabri.srvnotification.domain.vo.EmailAddress;
import com.fabri.srvnotification.domain.vo.EmailBody;
import com.fabri.srvnotification.domain.vo.EmailTitle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Email {

    private final EmailAddress to;
    private List<EmailAddress> cc = new ArrayList<>();
    private final EmailTitle title;
    private final EmailBody body;

    public Email(String to, List<String> cc, String title, String body) {

        this.to = new EmailAddress(to);
        this.title = new EmailTitle(title);
        this.body = new EmailBody(body);

        if (cc != null && !cc.isEmpty()) {
            this.cc = cc.stream().map(EmailAddress::new).toList();
        }
    }
}
