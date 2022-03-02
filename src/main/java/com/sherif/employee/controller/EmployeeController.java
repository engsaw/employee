package com.sherif.employee.controller;


import com.sherif.employee.entity.Employee;
import com.sherif.employee.service.EmployeeService;
import com.sherif.employee.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    @PostMapping("/")
    public Employee saveEmployee (@RequestBody Employee employee){

     if (employeeService.findDepartmentById(employee) !=null){
         return employeeService.saveEmployee(employee);
     }

     else {
         return null;

     }
    }




    @GetMapping("/{id}")
    public ResponseTemplateVO findEmployeeWithDepartment(@PathVariable ("id") Long id){


        return employeeService.findEmployeeWithDepartment(id);
    }


    @GetMapping("/")
    public List<ResponseTemplateVO> findAllEmployeeWithDepartment(){

        return employeeService.findAll();
    }

}
