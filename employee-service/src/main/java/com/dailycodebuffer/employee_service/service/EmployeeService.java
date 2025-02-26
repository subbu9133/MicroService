package com.dailycodebuffer.employee_service.service;

import com.dailycodebuffer.employee_service.dto.EmployeeDTO;
import com.dailycodebuffer.employee_service.model.Employee;
import com.dailycodebuffer.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Convert Employee entity to EmployeeDTO
    public EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getDepartmentId(),
                employee.getName(),
                employee.getAge(),
                employee.getPosition()
        );
    }

    // Convert list of Employee entities to list of EmployeeDTOs
    public List<EmployeeDTO> convertToDTOList(List<Employee> employees) {
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get all employees as DTOs
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return convertToDTOList(employees);
    }

    // Get employee by ID as DTO
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        return employeeOpt.map(this::convertToDTO).orElse(null);
    }

    // Save employee and return DTO
    public EmployeeDTO saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Update employee by ID and return DTO
    public EmployeeDTO updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setName(employeeDetails.getName());
            employee.setAge(employeeDetails.getAge());
            employee.setPosition(employeeDetails.getPosition());
            employee.setDepartmentId(employeeDetails.getDepartmentId());
            Employee updatedEmployee = employeeRepository.save(employee);
            return convertToDTO(updatedEmployee);
        }
        return null;
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Get employees by department ID
    public List<EmployeeDTO> findByDepartment(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return convertToDTOList(employees);
    }
}
