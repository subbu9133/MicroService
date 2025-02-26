package com.dailycodebuffer.department_service.controller;

import com.dailycodebuffer.department_service.dto.DepartmentWithEmployeesDTO;
import com.dailycodebuffer.department_service.model.Department;
import com.dailycodebuffer.department_service.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// Get all departments
	@GetMapping
	public List<Department> getAllDepartments() {
		return departmentService.findAllDepartments();
	}

	// Get department by ID
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Department department = departmentService.findDepartmentById(id);
		return ResponseEntity.ok(department);
	}

	// Get all departments with employees
	@GetMapping("/with-employees")
	public List<DepartmentWithEmployeesDTO> getAllDepartmentsWithEmployees() {
		return departmentService.findAllDepartmentsWithEmployees();
	}
}
