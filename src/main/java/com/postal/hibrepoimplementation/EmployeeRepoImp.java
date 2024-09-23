package com.postal.hibrepoimplementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postal.model.Employee;
import com.postal.model.PostOfficeHead;
import com.postal.repository.EmployeeRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@Repository
public class EmployeeRepoImp implements EmployeeRepo {

	@Autowired
	EntityManager em;

	@Override
	public void addEmp(Employee emp) {
		em.merge(emp);
	}

	@Override
	public void delEmp(int empId) {
		Employee emp = em.find(Employee.class, empId);
		if (emp != null) {
			em.remove(emp);
		}

	}

	@Override
	public void updateEmp(Employee emp) {
		em.merge(emp);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployee() {
		return em.createQuery("from Employee").getResultList();

	}

	@Override
	public Employee findById(int empId) {
		Query q = em.createQuery("from Employee where empId = ?1");
		q.setParameter(1, empId);
		return (Employee) q.getSingleResult();
	}

	@Override
	public Employee Login(String empEmail, String password) {
		try {
			Query q = em.createQuery("from Employee where empEmail = :empEmail and password = :password");
			q.setParameter("empEmail", empEmail);
			q.setParameter("password", password);
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}

	@Override
	public void addEmpp(String empName, long empMobile, String area, int slot, LocalDate deliverydate,
			LocalTime deliverytime, String empEmail, String password, Integer pincode) {
		PostOfficeHead head = em.find(PostOfficeHead.class, pincode);
		Employee emp = new Employee();
		emp.setEmpName(empName);
		emp.setEmpMobile(empMobile);
		emp.setArea(area);
		emp.setSlot(slot);
		emp.setDeliverydate(deliverydate);
		emp.setDeliverytime(deliverytime);
		emp.setEmpEmail(empEmail);
		emp.setPassword(password);
		emp.setPincode(head);
		em.merge(emp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> fetchAllEmp() {
		Query q = em.createQuery("SELECT e.empId FROM Employee e");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeByPincode(int pincode) {
		Query q = em.createQuery("SELECT e FROM Employee e WHERE e.pincode.pincode = :pincode");
		q.setParameter("pincode", pincode);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeesByToPincode(int toPincode) {
	    Query q = em.createQuery(
	        "SELECT e FROM Employee e JOIN Mail m ON e MEMBER OF m.employees JOIN m.address a WHERE a.toPincode = :toPincode"
	    );
	    q.setParameter("toPincode", toPincode);
	    return q.getResultList();
	}


	

}
