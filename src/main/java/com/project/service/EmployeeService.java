package com.project.service;

import com.project.dto.EmployeeDTO;
import com.project.exception.EmployeeException;

import java.util.List;

public interface EmployeeService {
    public EmployeeDTO getEmployeeDetails(Integer employeeId) throws EmployeeException;
    public List<EmployeeDTO> getAllEmployees() throws EmployeeException;
    public void addEmployee(EmployeeDTO employeeDTO) throws EmployeeException;
    public void updateEmailId(Integer employeeId,String emailId) throws EmployeeException;
    public void deleteEmployee(Integer employeeId) throws EmployeeException;

}
