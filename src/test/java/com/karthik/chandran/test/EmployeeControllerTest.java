package com.karthik.chandran.test;

import com.karthik.chandran.ems.controller.EmployeeController;
import com.karthik.chandran.ems.dto.EmployeeDto;
import com.karthik.chandran.ems.entity.Employee;
import com.karthik.chandran.ems.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	private Employee employee;
	private EmployeeDto employeeDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		employee = new Employee();
		employee.setId(1L);
		employee.setFirstName("Karthik");
		employee.setLastName("Chandran");
		employee.setEmail("karthik.chandran@example.com");
		employee.setPassword("password123");

		employeeDto = new EmployeeDto();
		employeeDto.setId(1L);
		employeeDto.setFirstName("Karthik");
		employeeDto.setLastName("Chandran");
		employeeDto.setEmail("karthik.chandran@example.com");
	}

	@Test
	void testCreateEmployee() {
		when(employeeService.createEmployee(employee)).thenReturn(employeeDto);

		ResponseEntity<EmployeeDto> response = employeeController.createEmployee(employee);

		assertNotNull(response);
		assertEquals(201, response.getStatusCodeValue());
		assertEquals("Karthik", response.getBody().getFirstName());

		verify(employeeService, times(1)).createEmployee(employee);
	}

	@Test
	void testGetEmployee() {
		when(employeeService.getEmployee(1L)).thenReturn(employeeDto);

		ResponseEntity<EmployeeDto> response = employeeController.getEmployee(1L);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Karthik", response.getBody().getFirstName());

		verify(employeeService, times(1)).getEmployee(1L);
	}

	@Test
	void testGetAllEmployees() {
		when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(employeeDto));

		ResponseEntity<List<EmployeeDto>> response = employeeController.getAllEmployee();

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(1, response.getBody().size());
		assertEquals("Karthik", response.getBody().get(0).getFirstName());

		verify(employeeService, times(1)).getAllEmployee();
	}

	@Test
	void testUpdateEmployee() {
		when(employeeService.updateEmployee(1L, employee)).thenReturn(employeeDto);

		ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(1L, employee);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Karthik", response.getBody().getFirstName());

		verify(employeeService, times(1)).updateEmployee(1L, employee);
	}

	@Test
	void testDeleteEmployee() {
		doNothing().when(employeeService).deleteEmployee(1L);

		ResponseEntity<String> response = employeeController.deleteEmployee(1L);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Employee deleted successfully", response.getBody());

		verify(employeeService, times(1)).deleteEmployee(1L);
	}
}

