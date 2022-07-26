package com.sridhar.project.course.courseapiapp.controllers;

import com.sridhar.project.course.courseapiapp.DataModel.EmployeeDTO;
import com.sridhar.project.course.courseapiapp.service.ConnectionService;
import groovy.util.logging.Log;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class AttendanceService {

    //connection class
    @Autowired
    public ConnectionService connection;

    public List<HashMap<String, String>> getAllLeavesForEmployee(String employeeId){
        //business logic of the get leaves endpoint

        //log for debugging purposes
        List<HashMap<String, String>> resultList = new ArrayList<>();

        try{
            //establishing connection
            Connection conn = connection.createConnectionUsingProps();
            //preparing the query
            PreparedStatement prepareQuery = conn.prepareStatement("Select * from Employee_Leaves where emp_id = ?");
            prepareQuery.setString(1, "19063");
            //execute and store results in result set
            ResultSet resultSet = prepareQuery.executeQuery();
            //process the results

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public HashMap<String, String> addNewLeave(EmployeeDTO employeeLeave){
        //business logic of the ass new leaves endpoint
    }

    public HashMap<String, String> editEmployeeLeave(EmployeeDTO employeeLeave){
        //business logic of the edit employee leave endpoint
    }

    public HashMap<String, String> cancelLeave(EmployeeDTO employeeLeave) {
        //business logic of cancel Leave endpoint

    }

}
