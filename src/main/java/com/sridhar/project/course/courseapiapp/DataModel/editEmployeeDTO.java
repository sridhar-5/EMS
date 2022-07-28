package com.sridhar.project.course.courseapiapp.DataModel;

public class editEmployeeDTO {
    private int employeeId;
    private String previousDate;
    private String editedDate;

    public String getPreviousDate(){
        return previousDate;
    }

    public String getEditedDate(){
        return editedDate;
    }

    public int getEmployeeId(){
        return employeeId;
    }
}

