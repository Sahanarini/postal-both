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
		repo.addEmp(emp);
		
	}

	@Override
	public void delEmp(int empId) {
		repo.delEmp(empId);
		
	}

	@Override
	public void updateEmp(Employee emp) {
		repo.updateEmp(emp);
		
	}

	@Override
	public List<Employee> getAllEmployee() {
		return repo.getAllEmployee();
	}

	@Override
	public Employee findById(int empId) {
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

	@Override
	public void addEmpp(String empName, long empMobile, String area, int slot, LocalDate deliverydate,
			LocalTime deliverytime, String empEmail, String password, Integer pincode) {
		repo.addEmpp(empName, empMobile, area, slot, deliverydate, deliverytime, empEmail, password, pincode);
		
	}

	@Override
	public List<Integer> fetchAllEmp() {
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
