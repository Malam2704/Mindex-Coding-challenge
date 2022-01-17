package com.mindex.challenge.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.mindex.challenge.controller.EmployeeController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingStructure {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    
    private Employee myEmployee;
    private List<Employee> directReportList;
    private List<Employee> allReportsList;
    // total number of reports under a given employee ( see example in read me)
    private int numberOfReports;

    public ReportingStructure(Employee employee){
        this.myEmployee = employee;
        this.directReportList = employee.getDirectReports();
        this.numberOfReports = employee.getDirectReports().size();
        this.allReportsList = new List<Employee>;
    }

    public void addEmployee(Employee thisEmployee){
        if(anEmployee.getDirectReports.size < 1){
            //Helper function to iterate over employee direct reports
            allReportsList.add(thisEmployee);
            return null;
        }else{
            addEmployee()
        }
    }

    @GetMapping("/reporting_structure/employee/{id}")
    public List<Employee> read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        for(int i=0; i < directReportList.size; i++){
            Employee anEmployee = directReportList.get(i);

            
        }
    }
}
