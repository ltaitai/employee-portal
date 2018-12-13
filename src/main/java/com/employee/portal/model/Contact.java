package com.employee.portal.model;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private ContactType contactType;

    private String contact_value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContact_value() {
        return contact_value;
    }

    public void setContact_value(String contact_value) {
        this.contact_value = contact_value;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactType=" + contactType +
                ", contact_value='" + contact_value + '\'' +
                '}';
    }
}
