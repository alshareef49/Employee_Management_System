package com.project.api;

import com.project.dto.EmployeeDTO;
import com.project.exception.EmployeeException;
import com.project.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class EmployeeAPI {
    @Autowired
    EmployeeServiceImpl employeeService;
    @GetMapping("employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable Integer employeeId) throws EmployeeException{
        EmployeeDTO employeeDTO = employeeService.getEmployeeDetails(employeeId);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() throws EmployeeException{
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDTOS,HttpStatus.OK);
    }

    @PostMapping("employee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeException{
        employeeService.addEmployee(employeeDTO);
        String message = "Employee successfully added with Id: "+employeeDTO.getEmployeeId();
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PutMapping("employee/{employeeId}")
    public ResponseEntity<String> updateEmailID(@PathVariable Integer employeeId,@RequestBody EmployeeDTO employeeDTO) throws EmployeeException{
        employeeService.updateEmailId(employeeId,employeeDTO.getEmailId());
        String message = "Successfully updated Employee EmailId with Employee Id: "+employeeId;
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) throws EmployeeException{
        employeeService.deleteEmployee(employeeId);
        String message = "Successfully deleted employee with Employee Id: "+employeeId;
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
