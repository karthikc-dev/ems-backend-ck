package com.karthik.chandran.ems.service;

import com.karthik.chandran.ems.dto.EmployeeDto;
import com.karthik.chandran.ems.entity.Employee;
import com.karthik.chandran.ems.exception.ResourceNotFoundException;
import com.karthik.chandran.ems.mapper.EmployeeMapper;
import com.karthik.chandran.ems.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(Employee employee) {
        log.info("Creating new employee with email: {}", employee.getEmail());
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee created successfully with id: {}", savedEmployee.getId());
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployee(Long id) throws ResourceNotFoundException {
        log.info("Fetching employee with id: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> {
                            log.error("Employee not found with id: {}", id);
                           return new ResourceNotFoundException("Employee is not found for the id " + id);
                        });
        log.info("Employee found: {}", employee);
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        log.info("Fetching all employees");
        List<Employee> employees = employeeRepository.findAll();
        log.info("Total employees found: {}", employees.size());
        return employees.stream().map((employee -> EmployeeMapper.mapToEmployeeDto(employee)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, Employee employee) {
        log.info("Updating employee with id: {}", id);
        Employee updatedEmp = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot update. Employee not found with id: {}", id);
                   return new ResourceNotFoundException("Employee is not found for the id " + id);
                });
        updatedEmp.setFirstName(employee.getFirstName());
        updatedEmp.setLastName(employee.getLastName());
        updatedEmp.setEmail(employee.getEmail());
        updatedEmp.setPassword(employee.getPassword());

        Employee emp = employeeRepository.save(updatedEmp);
        log.info("Employee updated successfully with id: {}", emp.getId());
        return EmployeeMapper.mapToEmployeeDto(emp);


    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot delete. Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee is not found for the id " + id);
                });
        employeeRepository.deleteById(id);
        log.info("Employee deleted successfully with id: {}", id);
    }
}
