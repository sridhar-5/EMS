package com.sridhar.project.course.courseapiapp.DataModel;

public class EmployeeDTO {
    private int employeeId;
    private String date;

    public String getEmployeeId(){
        return Integer.toString(employeeId);
    }

    public String getDate(){
        return date;
    }
}



