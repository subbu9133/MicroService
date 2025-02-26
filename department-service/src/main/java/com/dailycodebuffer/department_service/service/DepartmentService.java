package com.dailycodebuffer.department_service.service;

import com.dailycodebuffer.department_service.client.EmployeeClient;
import com.dailycodebuffer.department_service.dto.DepartmentWithEmployeesDTO;
import com.dailycodebuffer.department_service.dto.EmployeeDTO;
import com.dailycodebuffer.department_service.exception.DepartmentNotFoundException;
import com.dailycodebuffer.department_service.model.Department;
import com.dailycodebuffer.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeClient employeeClient) {
        this.departmentRepository = departmentRepository;
        this.employeeClient = employeeClient;
    }

    // Add a new department
    public Department addDepartment(Department department) {
        logger.info("Adding new department: {}", department);
        return departmentRepository.save(department);
    }

    // Get all departments
    public List<Department> findAllDepartments() {
        logger.info("Fetching all departments.");
        return departmentRepository.findAll();
    }

    // Get department by ID
    public Department findDepartmentById(Long id) {
        logger.info("Fetching department with ID: {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + id + " not found"));
    }

    // Get all departments with employees (using DTO)
    public List<DepartmentWithEmployeesDTO> findAllDepartmentsWithEmployees() {
        logger.info("Fetching all departments with employees.");
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(department -> {
            List<EmployeeDTO> employees;
            try {
                employees = employeeClient.findByDepartment(department.getId());
            } catch (Exception e) {
                logger.error("Error fetching employees for department ID: {}", department.getId(), e);
                employees = Collections.emptyList();
            }
            return new DepartmentWithEmployeesDTO(department.getId(), department.getName(), employees);
        }).collect(Collectors.toList());
    }
}
