package com.dailycodebuffer.employee_service.controller;

import com.dailycodebuffer.employee_service.dto.EmployeeDTO;
import com.dailycodebuffer.employee_service.model.Employee;
import com.dailycodebuffer.employee_service.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Get all employees
	@GetMapping
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// Get employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
		return employeeDTO != null ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
	}

	// Create new employee
	@PostMapping
	public EmployeeDTO createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	// Update employee by ID
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
		return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
	}

	// Delete employee by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

	// Get employees by department ID
	@GetMapping("/department/{departmentId}")
	public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable Long departmentId) {
		return employeeService.findByDepartment(departmentId);
	}
}
