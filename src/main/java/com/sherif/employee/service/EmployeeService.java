package com.sherif.employee.service;

import com.sherif.employee.entity.Employee;
import com.sherif.employee.repositry.EmployeeRepositry;
import com.sherif.employee.vo.Department;
import com.sherif.employee.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {


    public EmployeeService(EmployeeRepositry employeeRepositry, RestTemplate restTemplate) {
        this.employeeRepositry = employeeRepositry;
        this.restTemplate = restTemplate;
    }

    private EmployeeRepositry employeeRepositry;
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

        try {
            Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE:1002/departments/" + employee.getEmployeeDepartment(), Department.class);

            responseTemplateVO.setDepartment(department);
            responseTemplateVO.setEmployee(employee);
log.info("We got user" + employee);
            return responseTemplateVO;
        } catch (Exception e){
            log.error(e.getMessage());
        }


        return responseTemplateVO;

    }

    public List<ResponseTemplateVO> findAll() {

        List<Employee> employees = employeeRepositry.findAll();
        List<ResponseTemplateVO> responseTemplateVOs = new ArrayList<>();

        for (Employee employee : employees) {

            ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();

            Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE:1002/departments/" + employee.getEmployeeDepartment(), Department.class);

            responseTemplateVO.setDepartment(department);
            responseTemplateVO.setEmployee(employee);

            responseTemplateVOs.add(responseTemplateVO);


        }

        return responseTemplateVOs;
    }

    public Department findDepartmentById(Employee employee) {
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE:1002/departments/" + employee.getEmployeeDepartment(), Department.class);

        return department;
    }

    public void deleteAllEmployees() {
        employeeRepositry.deleteAll();
    }
}
