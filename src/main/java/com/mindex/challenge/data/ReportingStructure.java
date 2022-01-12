package com.mindex.challenge.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingStructure {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    
    private Employee emEmployee;
    // total number of reports under a given employee ( see example in read me)
    private int numberOfReports;

    @GetMapping("/employee/{id}")
    public List<Employee> read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return emEmployee.getDirectReports();
    }
}
