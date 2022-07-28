package com.sridhar.project.course.courseapiapp.controllers;

import com.sridhar.project.course.courseapiapp.DataModel.EmployeeDTO;
import com.sridhar.project.course.courseapiapp.DataModel.editEmployeeDTO;
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
import java.util.Date;
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
            PreparedStatement prepareQuery = conn.prepareStatement("Select * from Employees where emp_id = ?");
            prepareQuery.setString(1, employeeId);
            //execute and store results in result set
            ResultSet resultSet = prepareQuery.executeQuery();
            //process the results
            while(resultSet.next()){
                HashMap<String, String> currentLeave = new HashMap<>();
                currentLeave.put("date_applied", new Date(Long.parseLong(resultSet.getString("__createdtime__"))).toString());
                currentLeave.put("last_updated_date", new Date(Long.parseLong(resultSet.getString("__updatedtime__"))).toString());
                currentLeave.put("leave_applied_for", resultSet.getString("Date"));
                currentLeave.put("employee_id", resultSet.getString("emp_id"));

                //adding the current employee instance to the list of the leaves
                resultList.add(currentLeave);
            }

            //closing the connection
            conn.close();
        } catch (Exception e) {
            HashMap<String, String> errorRes = new HashMap<>();
            errorRes.put("error", e.getMessage());
            resultList.add(errorRes);
            e.printStackTrace();
        }
        return resultList;
    }

    public HashMap<String, String> addNewLeave(EmployeeDTO employeeLeave){
        //business logic of the ass new leaves endpoint
        HashMap<String, String> newLeave = new HashMap<>();
        try{
            Connection connect = connection.createConnectionUsingProps();
            PreparedStatement statement = connect.prepareStatement("insert into Employee_Leaves.Leaves (date, emp_id) values (? , ?)");
            statement.setString(1, employeeLeave.getDate());
            statement.setString(2, employeeLeave.getEmployeeId());

            //result is no of rows effected in the database
            int effectedRows = statement.executeUpdate();
            if (effectedRows > 1){
                //query success
                newLeave.put("Message", "Insertion Successful");
                newLeave.put("Rows effected", Integer.toString(effectedRows));
            }
            connect.close();
        }catch (Exception e){
            newLeave.put("Message", "Insertion failed");
            newLeave.put("error Message", e.getMessage());
            e.printStackTrace();
        }
        //close the connection to the database
        return newLeave;
    }

    public HashMap<String, String> editEmployeeLeave(editEmployeeDTO employeeLeave){
        //business logic of the edit employee leave endpoint
        HashMap<String , String> editLeave = new HashMap<>();
        try{
            //start connection
            Connection connect = connection.createConnectionUsingProps();
            PreparedStatement prepareSt = connect.prepareStatement("UPDATE Employee_Leaves.Leaves SET date = ? WHERE emp_id = ? AND date = ?");
            prepareSt.setString(1, employeeLeave.getEditedDate());
            prepareSt.setString(2, Integer.toString(employeeLeave.getEmployeeId()));
            prepareSt.setString(3, employeeLeave.getPreviousDate());
            int effectedRows = prepareSt.executeUpdate();
            if (effectedRows > 0){
                editLeave.put("Message", "Success");
                editLeave.put("count", Integer.toString(effectedRows));
            }
            connect.close();
        }catch(Exception e){
            editLeave.put("Message", "Error occured");
            editLeave.put("Error Message", e.getMessage());
        }
        return editLeave;
    }

    public HashMap<String, String> cancelLeave(EmployeeDTO employeeLeave) {
        //business logic of cancel Leave endpoint
        HashMap<String, String> cancelLeaveLog = new HashMap<>();

        try{
            Connection connect = connection.createConnectionUsingProps();
            PreparedStatement statement = connect.prepareStatement("DELETE FROM Employee_Leaves.Leaves WHERE date = ? AND emp_id = ?");
            statement.setString(1, employeeLeave.getDate());
            statement.setString(2, employeeLeave.getEmployeeId());
            int effectedRows = statement.executeUpdate();
            if (effectedRows > 0){
                cancelLeaveLog.put("Message", "Success");
                cancelLeaveLog.put("Rows effected", Integer.toString(effectedRows));
            }
            connect.close();
        }catch(Exception e){
            cancelLeaveLog.put("Message", "error occured");
            cancelLeaveLog.put("Error Message", e.getMessage());
            e.printStackTrace();
        }

        return cancelLeaveLog;
    }
}
