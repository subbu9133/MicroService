package com.dailycodebuffer.department_service.client;

import com.dailycodebuffer.department_service.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service", fallback = EmployeeClientFallback.class) // Enables fallback handling
public interface EmployeeClient {

	@GetMapping("/employees/department/{departmentId}")
	List<EmployeeDTO> findByDepartment(@PathVariable("departmentId") Long departmentId);
}
