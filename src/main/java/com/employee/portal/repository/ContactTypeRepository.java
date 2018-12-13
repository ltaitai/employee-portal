package com.employee.portal.repository;

import com.employee.portal.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactType,Long> {
    ContactType findContactTypeByTypeKey(Long typeKey);
    ContactType deleteContactTypeByTypeKey(Long typeKey);
}
