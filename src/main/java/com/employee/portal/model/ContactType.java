package com.employee.portal.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long typeKey;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(Long typeKey) {
        this.typeKey = typeKey;
    }

    @Override
    public String toString() {
        return "ContactType{" +
                "id=" + id +
                ", typeKey=" + typeKey +
                ", description='" + description + '\'' +
                '}';
    }
}
