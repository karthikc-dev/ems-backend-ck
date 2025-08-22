package com.karthik.chandran.ems.service;

import com.karthik.chandran.ems.dto.EmployeeDto;
import com.karthik.chandran.ems.entity.Employee;
import com.karthik.chandran.ems.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeDto createEmployee(Employee employee);

    EmployeeDto getEmployee(Long id);

    List<EmployeeDto> getAllEmployee();
    EmployeeDto updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);


}
