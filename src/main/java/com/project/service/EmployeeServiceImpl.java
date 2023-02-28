package com.project.service;

import com.project.dto.EmployeeDTO;
import com.project.entity.Employee;
import com.project.exception.EmployeeException;
import com.project.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO getEmployeeDetails(Integer employeeId) throws EmployeeException {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow(()-> new EmployeeException("EMPLOYEE_NOT_EXIST"));
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDob(employee.getDob());
        employeeDTO.setEmailId(employee.getEmailId());
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws EmployeeException{
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();
        employees.forEach(e->{
            EmployeeDTO employeeDTO= new EmployeeDTO();
            employeeDTO.setEmployeeId(e.getEmployeeId());
            employeeDTO.setName(e.getName());
            employeeDTO.setDob(e.getDob());
            employeeDTO.setEmailId(e.getEmailId());
            employeeDTOS.add(employeeDTO);
        });
        if(employeeDTOS.isEmpty()){
            throw new EmployeeException("EMPLOYEE_NOT_EXIST");
        }
        return employeeDTOS;
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) throws EmployeeException{
        Optional<Employee> optional = employeeRepository.findById(employeeDTO.getEmployeeId());
        if(optional.isPresent()){
            throw new EmployeeException("EMPLOYEE_AREADY_EXIST");
        }
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setDob(employeeDTO.getDob());
        employee.setEmailId(employeeDTO.getEmailId());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmailId(Integer employeeId,String emailId) throws EmployeeException{
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow(()-> new EmployeeException("EMPLOYEE_NOT_EXIST"));
        employee.setEmailId(emailId);
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws EmployeeException{
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow(()-> new EmployeeException("EMPLOYEE_NOT_EXIST"));
        employeeRepository.delete(employee);
    }
}
