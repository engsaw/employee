package com.sherif.employee.repositry;

import com.sherif.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositry extends JpaRepository<Employee,Long> {


    Employee findEmployeeById(Long id);
}
