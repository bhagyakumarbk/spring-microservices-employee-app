package com.employee.app.service;

import java.util.List;

import com.employee.app.model.EmployeeDTO;
import com.employee.app.model.EmployeeEntity;

public interface EmployeeAPI {

	public EmployeeDTO createEmployee(EmployeeDTO employeeEntity);

	public EmployeeDTO getEmployeeById(int id);

	public List<EmployeeDTO> getAllEmployees();

	public EmployeeDTO updateEmployee(EmployeeDTO employeeEntity);

}
