package com.employee.portal.controller;

import com.employee.portal.model.ContactType;
import com.employee.portal.repository.ContactTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin
@RestController
@EntityScan(value="com.employee.portal.model")
public class ContactTypeController {

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    @RequestMapping(value="createContactType", method = RequestMethod.POST)
    public ResponseEntity createContactType(@RequestBody ContactType contactType){
        String res = "";
        if(Objects.nonNull(contactType)){
            ContactType contactTypeByTypeKey = contactTypeRepository.findContactTypeByTypeKey(contactType.getTypeKey());
            if(Objects.nonNull(contactTypeByTypeKey) || !String.valueOf(contactTypeByTypeKey).isEmpty()) {
                contactTypeRepository.save(contactType);
                res = "Created";
            }else{
                res = "Exists";
            }

        }
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "findAllContactTypes", method = RequestMethod.GET)
    public ResponseEntity<List<ContactType>> findAllContactTypes(){
        List<ContactType> contactTypes = contactTypeRepository.findAll();
        return ResponseEntity.ok(contactTypes);
    }

    @RequestMapping(value = "deleteContactTypeById", method = RequestMethod.DELETE)
    public ResponseEntity deleteContactType(@RequestParam Long typeKey){
        ContactType contactType = contactTypeRepository.findContactTypeByTypeKey(typeKey);
        if(Objects.nonNull(contactType)){
            contactTypeRepository.deleteById(contactType.getId());
            return ResponseEntity.ok(contactType);
        }
        return ResponseEntity.ok("No Contact Type with That key!");
    }

    @RequestMapping(value = "updateContactType",method = RequestMethod.PUT)
    public ResponseEntity updateContactType(@RequestBody ContactType contactType,@RequestParam Long id){
        AtomicReference<ContactType> savedContactType = new AtomicReference<>(new ContactType());

        contactTypeRepository
                .findById(id)
                .ifPresent(contactTypeOld -> {
                    contactTypeOld.setTypeKey(contactType.getTypeKey());
                    contactTypeOld.setDescription(contactType.getDescription());

                    savedContactType.set(contactTypeRepository.saveAndFlush(contactTypeOld));

        });

        return ResponseEntity.ok(savedContactType);
    }

    @RequestMapping(value = "findContactTypeByKey", method = RequestMethod.GET)
    public ResponseEntity<ContactType> findContactTypeByKey(@RequestParam Long typeKey){
        ContactType contactTypeByTypeKey = contactTypeRepository.findContactTypeByTypeKey(typeKey);
        return ResponseEntity.ok(contactTypeByTypeKey);
    }

}
