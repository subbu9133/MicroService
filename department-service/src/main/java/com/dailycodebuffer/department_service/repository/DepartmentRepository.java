package com.dailycodebuffer.department_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dailycodebuffer.department_service.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	// You can add custom query methods here if needed
	// For example:
	// List<Department> findByName(String name);
}
