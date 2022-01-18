package com.mindex.challenge.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mindex.challenge.controller.EmployeeController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingStructure {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private Employee myEmployee;
    private List<Employee> directReportList;
    private List<Employee> allReportsList;

    private HashMap<Employee, Integer> employeeConnectionsMap;

    public ReportingStructure(Employee employee) {
        this.myEmployee = employee;
        this.directReportList = employee.getDirectReports();
        this.allReportsList = new ArrayList<Employee>();
        this.employeeConnectionsMap = new HashMap<>();
    }

    //
    // Adds the employee as a Key & their number of direct reports as the size.
    // Will be adding more employees to the Hashmap if not already inside it.
    // That way we can start the whole iteration with just the root employees
    // direct reports, but the sub employees add their reports and so on.
    //
    public void addEmployeeReports(Employee thisEmployee) {
        // Simple for loop to iterate over an employees direct reports and add them to
        // teh all reports list, and hashmap for number of connections, if they're not
        // already they're
        for (int i = 0; i < thisEmployee.getDirectReports().size(); i++) {
            Employee anEmployee = thisEmployee.getDirectReports().get(i);

            if (!allReportsList.contains(thisEmployee)) {
                allReportsList.add(anEmployee);

                this.employeeConnectionsMap.put(anEmployee, anEmployee.getDirectReports().size());
            }
        }
    }

    //
    // While loop fucntion to slowly add all reports of an employee to the all
    // reports list and decrease count of employees left to add.
    //
    public void reduceEmployeeHashmap(Employee thisEmployee) {
        // So we have a while loop to check how many more employees we need to add to
        // the all reports list form the
        // direst reports list
        while (employeeConnectionsMap.get(thisEmployee) > 0) {
            // We plug in the last index, in this case the size of the direct reports - 1,
            // and apply it to the addEmployeeReports function to add all of their ereports
            // into the allreportslist. We add all the sub employees increasing the size of
            // the Hasmap to make sure all employees are aqccurately recorded.
            addEmployeeReports(thisEmployee.getDirectReports().get(employeeConnectionsMap.get(thisEmployee) - 1));
            // As we added all the direct reports of one of our direcdt reports we have 1
            // less report to check so we can decrease the index.
            employeeConnectionsMap.put(thisEmployee, employeeConnectionsMap.get(thisEmployee) - 1);
        }

        // We have reached the end meaning that all the direct reports of our reports
        // have been added.
    }

    //
    // Function that goes through the root emplooyee and adds all it's direct
    // reports to the hashmap. Then iterates over all keys within the hashmap and
    // applying the reduce function to get those values to reach 0, which means 0
    // reports left to add.
    //
    @GetMapping("/reporting_structure/employee/{id}")
    public List<Employee> read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        addEmployeeReports(this.myEmployee);

        for (Employee employee : employeeConnectionsMap.keySet()) {
            reduceEmployeeHashmap(employee);
        }

        return this.allReportsList;
    }

}
