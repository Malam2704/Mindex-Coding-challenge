package com.mindex.challenge.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import com.mindex.challenge.controller.EmployeeController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingStructure {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    
    private Employee myEmployee;
    private List<Employee> directReportList;
    private List<Employee> allReportsList;

    public ReportingStructure(Employee employee){
        this.myEmployee = employee;
        this.directReportList = employee.getDirectReports();
        this.allReportsList = new ArrayList<Employee>();
    }

    public void addEmployeeReports(Employee thisEmployee){
        // if(anEmployee.getDirectReports.size < 1){
        //     //Helper function to iterate over employee direct reports
        //     allReportsList.add(thisEmployee);
        //     return null;
        // }else{
        //     addEmployee()
        // }
        for(int i=0; i < thisEmployee.getDirectReports().size(); i++){
            Employee anEmployee = thisEmployee.getDirectReports().get(i);

            allReportsList.add(anEmployee);

        }
    }

    @GetMapping("/reporting_structure/employee/{id}")
    public List<Employee> read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        for(int i=0; i < directReportList.size(); i++){
            Employee anEmployee = directReportList.get(i);

            if(!allReportsList.contains(anEmployee)){
                allReportsList.add(anEmployee);
                addEmployeeReports(anEmployee);
            }
        }

        return this.allReportsList;
    }

    // Function that goes through all employees and alls 1st reports them to a list.
    // Another function that iterates over that exact list then puts them in
    // 
}
