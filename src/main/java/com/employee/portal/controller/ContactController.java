package com.employee.portal.controller;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@EntityScan(value="com.employee.portal.model")
public class ContactController {


}
