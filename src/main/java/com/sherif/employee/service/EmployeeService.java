package com.sherif.employee.service;

import com.sherif.employee.entity.Employee;
import com.sherif.employee.repositry.EmployeeRepositry;
import com.sherif.employee.vo.Department;
import com.sherif.employee.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepositry employeeRepositry;

    @Autowired
    private RestTemplate restTemplate;


    public Employee findEmployeeById(Long id) {

        return employeeRepositry.findEmployeeById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepositry.save(employee);
    }

    public ResponseTemplateVO findEmployeeWithDepartment(Long id) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Employee employee = employeeRepositry.findEmployeeById(id);

        Department department = restTemplate.getForObject("http://localhost:8080/departments/" + employee.getId(), Department.class);

        responseTemplateVO.setDepartment(department);
        responseTemplateVO.setEmployee(employee);

        return responseTemplateVO;
    }

    public List<ResponseTemplateVO> findAll() {

        List<Employee> employees = employeeRepositry.findAll();
        List<ResponseTemplateVO> responseTemplateVOs = new ArrayList<ResponseTemplateVO>();

        for (Employee employee : employees) {

            ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();

            Department department = restTemplate.getForObject("http://localhost:8080/departments/" + employee.getId(), Department.class);

            responseTemplateVO.setDepartment(department);
            responseTemplateVO.setEmployee(employee);

            responseTemplateVOs.add(responseTemplateVO);
        }

        return responseTemplateVOs;
    }

    public Department findDepartmentById(Employee employee) {
        Department department = restTemplate.getForObject("http://localhost:8080/departments/" + employee.getId(), Department.class);

        return department;
    }
}
