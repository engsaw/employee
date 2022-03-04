package com.sherif.employee.controller;


import com.sherif.employee.entity.Employee;
import com.sherif.employee.service.EmployeeService;
import com.sherif.employee.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    @PostMapping("/")
    public Employee saveEmployee (@RequestBody Employee employee){

      //  return employeeService.saveEmployee(employee);


  if (employeeService.findDepartmentById(employee) !=null){
      log.info("Done adding new employee :" + employee);
         return employeeService.saveEmployee(employee);
     }

     else {
      log.info("Sorry this department doesn't exist");
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

    @DeleteMapping("/")
    public void deleteAllEmployeeWithDepartment(){

   employeeService.deleteAllEmployees();
    }


}
