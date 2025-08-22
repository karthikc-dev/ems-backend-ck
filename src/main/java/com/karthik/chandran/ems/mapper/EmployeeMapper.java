package com.karthik.chandran.ems.mapper;

import com.karthik.chandran.ems.dto.EmployeeDto;
import com.karthik.chandran.ems.entity.Employee;


public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
            return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
