package com.sridhar.project.course.courseapiapp.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AttendanceController {

    //business logic to be coded in other module
    private AttendanceService service;

    @GetMapping(value = "/api/get/all/leaves/{employeeId}")
    public List<HashMap<String, String>> getEmployeeLeaves(@PathVariable String employeeId){
        return service.getAllLeavesForEmployee(employeeId);
    }

    @PostMapping(value="/api/add/leaves")
    public HashMap<String, String> addNewLeave(@RequestBody EmployeeDTO employeeLeave){
        return service.addNewLeave(employeeLeave);
    }

    @PutMapping(value = "/api/edit/leave")
    public HashMap<String, String> editEmployeeLeave(@RequestBody EmployeeDTO employeeLeave){
        return service.editEmployeeLeave(employeeLeave);
    }

    @DeleteMapping(value = "/api/delete/leave")
    public HashMap<String, String> cancelLeave(@RequestBody EmployeeDto employeeLeave) {
        return service.cancelLeave(employeeLeave);
    }
}
