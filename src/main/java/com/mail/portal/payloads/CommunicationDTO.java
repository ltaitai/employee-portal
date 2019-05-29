package com.mail.portal.payloads;

import lombok.Data;

@Data
public class CommunicationDTO {
    private String recipientEmail;
    private Long age;
    private String name;

}
