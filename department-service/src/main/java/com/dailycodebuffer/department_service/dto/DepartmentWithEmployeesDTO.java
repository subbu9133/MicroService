package com.dailycodebuffer.department_service.dto;

import java.util.List;

public class DepartmentWithEmployeesDTO {

    private Long id;
    private String name;
    private List<EmployeeDTO> employees;

    // Constructor
    public DepartmentWithEmployeesDTO(Long id, String name, List<EmployeeDTO> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
