package com.dailycodebuffer.department_service.client;

import com.dailycodebuffer.department_service.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EmployeeClientFallback implements EmployeeClient {
    @Override
    public List<EmployeeDTO> findByDepartment(Long departmentId) {
        // Return an empty list or default message to handle errors
        return Collections.emptyList();
    }
}
