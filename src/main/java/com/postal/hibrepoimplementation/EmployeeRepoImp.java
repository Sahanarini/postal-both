package com.postal.hibrepoimplementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postal.model.Employee;
import com.postal.model.Mail;
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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

//	@Override
//	public Employee Login(String empEmail, String password) {
//		Query q = em.createQuery("from Employee where empEmail =?1 and password = ?2");
//		q.setParameter(1, empEmail);
//		q.setParameter(2, password);
//		return (Employee) q.getSingleResult();
//	}

	@Override
	public Employee Login(String empEmail, String password) {
		try {
			Query q = em.createQuery("from Employee where empEmail = :empEmail and password = :password");
			q.setParameter("empEmail", empEmail);
			q.setParameter("password", password);
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			// No result found for the provided email and password
			return null;
		} catch (Exception e) {
			// Handle other potential exceptions
			e.printStackTrace(); // Log the exception for debugging
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

	@Override
	public List<Integer> fetchAllEmp() {
		// Create the query to select only the empId from Employee
		Query q = em.createQuery("SELECT e.empId FROM Employee e");

		// Execute the query and get the result list
		return q.getResultList();
	}

	@Override
	public List<Employee> getEmployeeByPincode(int pincode) {
		Query q = em.createQuery("SELECT e FROM Employee e WHERE e.pincode.pincode = :pincode");
		q.setParameter("pincode", pincode);
		return q.getResultList();
	}

	public List<Employee> getEmployeesByToPincode(int toPincode) {
	    Query q = em.createQuery(
	        "SELECT e FROM Employee e JOIN Mail m ON e MEMBER OF m.employees JOIN m.address a WHERE a.toPincode = :toPincode"
	    );
	    q.setParameter("toPincode", toPincode);
	    return q.getResultList();
	}


	

}
