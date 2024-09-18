package com.postal.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.postal.model.Employee;
import com.postal.model.Mail;
import com.postal.model.PostOfficeHead;

public interface EmployeeRepo {
	public void addEmp(Employee emp);

	public void delEmp(int empId);

	public void updateEmp(Employee emp);

	public List<Employee> getAllEmployee();

	public Employee findById(int empId);
	
	public List<Employee> getEmployeeByPincode(int pincode);
	
	public List<Employee> getEmployeesByToPincode(int toPincode);
	
	public Employee Login(String empEmail, String password);
	
	public void addEmpp(String empName, long empMobile, String area, int slot, LocalDate deliverydate,
			LocalTime deliverytime, String empEmail, String password, Integer pincode);

	public List<Integer> fetchAllEmp();
	
}
