package com.employee.portal.controller;

import com.employee.portal.model.Contact;
import com.employee.portal.model.ContactType;
import com.employee.portal.model.Employee;
import com.employee.portal.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeController employeeController;

    @Before
    public void init(){
        initMocks(this);
        when(employeeController.findAllEmployees()).thenReturn(buildEmployee());
    }


    private ResponseEntity<List<Employee>> buildEmployee() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("Luthando");
        employee.setLastName("Taitai");
        employee.setIdNumber("11222211");
        employee.setContacts(buildContacts());
        employee.setId(1L);

        employees.add(employee);

        return ResponseEntity.ok(employees);
    }

    private List<Contact> buildContacts() {
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setContact_value("email");
        contact.setContactType(getContactType());
        contact.setId(1L);
        contacts.add(contact);

        return contacts;
    }

    private ContactType getContactType() {
        ContactType contactType = new ContactType();
        contactType.setDescription("Email");
        contactType.setId(1L);
        contactType.setTypeKey(1L);
        return contactType;
    }

    @Test
    public void shouldReturnEmployeesContactsAndContactTypes(){
        ResponseEntity<List<Employee>> employees = employeeController.findAllEmployees();

        employeeController.createEmployee(employees.getBody().stream().findAny().get());
        employeeController.updateEmployee(employees.getBody().stream().findAny().get(),1L);

        assertNotNull(employees.getBody());
        assertTrue(!employees.getBody().isEmpty());
        assertNotNull(employees.getBody().stream().findAny().get().getContacts());

        assertNotNull(employees
                .getBody()
                .stream()
                .findAny()
                .get()
                .getContacts()
                    .stream()
                    .findAny()
                    .get()
                    .getContactType());

    }

}