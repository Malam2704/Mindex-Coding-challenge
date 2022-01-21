package com.mindex.challenge.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mindex.challenge.controller.EmployeeController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Compensation {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private Employee employee;
    private Double salary;
    private Date effectiveDate;

    public Compensation(Employee employee, Double salary, Date effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    //
    // Function that goes through the root emplooyee and adds all it's direct
    // reports to the hashmap. Then iterates over all keys within the hashmap and
    // applying the reduce function to get those values to reach 0, which means 0
    // reports left to add.
    //
    @GetMapping("/compensation/employee/new/{id}")
    public List<Employee> createNew(@PathVariable String id) {
        LOG.debug("Received Compensation create request for employee");

        Employee myEmployee = new Employee();
        myEmployee.set

        for (Employee employee : employeeConnectionsMap.keySet()) {
            reduceEmployeeHashmap(employee);
        }

        return this.allReportsList;
    }

    @GetMapping("/compensation/employee/{id}")
    public List<Employee> readEID(@PathVariable String id) {
        LOG.debug("Received Compensation read request for employee with id [{}]", id);

        addEmployeeReports(this.myEmployee);

        for (Employee employee : employeeConnectionsMap.keySet()) {
            reduceEmployeeHashmap(employee);
        }

        return this.allReportsList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Double getSalary() {
        return salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

}
