package com.employee.portal.controller;

import com.employee.portal.model.Employee;
import com.employee.portal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.management.snmp.jvmmib.EnumJvmMemPoolCollectThreshdSupport;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin
@RestController
@EntityScan(value="com.employee.portal.model")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value="createEmployee", method = RequestMethod.POST)
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        if(Objects.nonNull(employee)){
            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee Created!");
        }else{
            return ResponseEntity.ok("Employee Created!");
        }
    }


    @RequestMapping(value = "findAllEmployees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> contactTypes = employeeRepository.findAll();
        return ResponseEntity.ok(contactTypes);
    }

    @RequestMapping(value = "deleteEmployeeById", method = RequestMethod.DELETE)
    public void deleteEmployeeById(@RequestParam Long id){
        employeeRepository.deleteById(id);
    }


    @RequestMapping(value = "updateEmployee",method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@RequestBody Employee employee,@RequestParam Long id){
        AtomicReference<Employee> savedEmployee = new AtomicReference<>(new Employee());

        employeeRepository
                .findById(id)
                .ifPresent(employeeOld -> {
                    employeeOld.setContacts(employee.getContacts());
                    employeeOld.setFirstName(employee.getFirstName());
                    employeeOld.setLastName(employee.getLastName());
                    employeeOld.setIdNumber(employee.getIdNumber());

                    savedEmployee.set(employeeRepository.saveAndFlush(employeeOld));

                });

        return ResponseEntity.ok(savedEmployee);
    }

    @RequestMapping(value = "findEmployeeById", method = RequestMethod.GET)
    public ResponseEntity<Employee> findEmployeeById(@RequestParam Long id){
        Employee employee = employeeRepository.getOne(id);
        return ResponseEntity.ok(employee);
    }
}
