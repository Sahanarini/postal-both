package com.postal.serviceimplementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.EmployeeRepoImp;
import com.postal.model.Employee;
import com.postal.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeRepoImp repo;
	
	@Override
	public void addEmp(Employee emp) {
		// TODO Auto-generated method stub
		repo.addEmp(emp);
		
	}

	@Override
	public void delEmp(int empId) {
		// TODO Auto-generated method stub
		repo.delEmp(empId);
		
	}

	@Override
	public void updateEmp(Employee emp) {
		// TODO Auto-generated method stub
		repo.updateEmp(emp);
		
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return repo.getAllEmployee();
	}

	@Override
	public Employee findById(int empId) {
		// TODO Auto-generated method stub
		return repo.findById(empId) ;
	}
	
	@Override
	public Employee Login(String empEmail, String password) {
		Employee emp = null;
		try {
			emp = repo.Login(empEmail, password);
		}catch(Exception e) {
			emp = null;
		}
		return emp;	
	}

//	public Employee updateSlot(int empId, int slot) {
//        // Find the employee by id
//        Employee employee = repo.findById(empId);
//        // Update the slot
//        employee.setSlot(slot);
//
//        // Save the updated employee back to the database
//        return repo.updateSlot(empId, slot);
//    }

	@Override
	public void addEmpp(String empName, long empMobile, String area, int slot, LocalDate deliverydate,
			LocalTime deliverytime, String empEmail, String password, Integer pincode) {
		repo.addEmpp(empName, empMobile, area, slot, deliverydate, deliverytime, empEmail, password, pincode);
		
	}

	@Override
	public List<Integer> fetchAllEmp() {
		// TODO Auto-generated method stub
		return repo.fetchAllEmp() ;
	}

	@Override
	public List<Employee> getEmployeeByPincode(int pincode) {
		
		return repo.getEmployeeByPincode(pincode);
	}

	@Override
	public List<Employee> getEmployeesByToPincode(int toPincode) {
		
		return repo.getEmployeesByToPincode(toPincode);
	}
	

	

}
