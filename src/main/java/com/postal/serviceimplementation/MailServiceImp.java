package com.postal.serviceimplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.AddressRepoImp;
import com.postal.hibrepoimplementation.MailRepoImp;
import com.postal.hibrepoimplementation.UserRepoImp;
import com.postal.model.Address;
import com.postal.model.Employee;
import com.postal.model.Mail;
import com.postal.model.User;
import com.postal.repository.EmployeeRepo;
import com.postal.service.MailService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MailServiceImp implements MailService {

	@Autowired
	private MailRepoImp repo;

	@Autowired
	private EmployeeRepo emprepo;

	@Autowired
	private UserRepoImp userrepo;

	@Autowired
	private AddressRepoImp addrepo;

	@Override
	public void addMail(Mail mail) {
		repo.addMail(mail);
	}

	@Override
	public void delMail(int mId) {
		repo.delMail(mId);
	}

	@Override
	public void updateMail(Mail mail) {
		repo.updateMail(mail);
	}

	@Override
	public List<Mail> getAllMail() {
		// TODO Auto-generated method stub
		return repo.getAllMail();

	}

	@Override
	public Mail findMailbyMailId(int mId) {
		// TODO Auto-generated method stub
		return repo.findMailbyMailId(mId);
	}

	@Override
	public Optional<Integer> findUserPincodeByMailId(int mId) {
		// TODO Auto-generated method stub
		return repo.findUserPincodeByMailId(mId);
	}

	public Optional<Address> getAddressByMailId(int mId) {
		return repo.findAddressByMailId(mId);
	}

	public User findById(int userId) {
		return userrepo.findById(userId);
	}

	public Address findByaddId(int id) {
		return addrepo.findByaddId(id);
	}

	@Override
	public void addMal(String service, String articleType, String articlecontent, LocalDate createdAt, int price,
			int weight, int length, int height, int width, int value, String collectiondate, String time, String status,
			Integer user, Integer address) {
		repo.addMal(service, articleType, articlecontent, createdAt, price, weight, length, height, width, value,
				collectiondate, time, status, user, address);
	}

	@Override
	public List<Mail> getMailsByPinCode(String pincode) {
		return repo.findMailsByPinCode(pincode);
	}

	@Override
	public List<Mail> findToMailsByPinCode(String toPincode) {
		return repo.findToMailsByPinCode(toPincode);
	}

	@Override
	public Optional<Integer> findToPincodeByMailId(int mId) {
		return repo.findToPincodeByMailId(mId);
	}

	@Override
	public void assignEmployeeToMail(int mailId, int employeeId) {
		Mail mail = repo.findMailbyMailId(mailId);
		if (mail != null) {
			// Assuming `Employee` is another repository/service that you have
			Employee employee = emprepo.findById(employeeId);// fetch employee by ID from EmployeeRepo or similar
			if (employee != null) {
				mail.getEmployees().add(employee);
				repo.updateMail(mail); // Update the mail entity with the new employee
			} else {
				throw new RuntimeException("Employee not found");
			}
		} else {
			throw new RuntimeException("Mail not found");
		}
	}

	@Override
	public List<Mail> getFromMailforEmployee(int empId) {

		return repo.getFromMailforEmployee(empId);
	}

	@Override
	public List<Mail> getToMailforEmployee(int empId) {
		return repo.getToMailforEmployee(empId);
	}

	@Override
	public boolean updatestatus(int mId, String status) {
		
		try {
			Mail mail =repo.findMailbyMailId(mId);
			if(mail!=null) {
				mail.setStatus(status);
				repo.updateMail(mail);
				return true;
			}
			else {
				return false;
			}
			
		}
		catch(Exception e) {
			return false;
		}
	}

}
