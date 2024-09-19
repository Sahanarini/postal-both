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
	public void delMail(int mId) {
		repo.delMail(mId);
	}

	@Override
	public void updateMail(Mail mail) {
		repo.updateMail(mail);
	}

	@Override
	public List<Mail> getAllMail() {
		return repo.getAllMail();

	}

	@Override
	public Mail findMailbyMailId(int mId) {
		return repo.findMailbyMailId(mId);
	}

	@Override
	public Optional<Integer> findUserPincodeByMailId(int mId) {
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
	public Integer addMal(String service, String articleType, String articlecontent, LocalDate createdAt, int price,
	                      int weight, int length, int height, int width, int value, String collectiondate, 
	                      String time, String status, Integer user, Integer address) {
	    Mail savedMail = repo.addMal(service, articleType, articlecontent, createdAt, price, weight, 
	                                  length, height, width, value, collectiondate, time, status, user, address);
	    return savedMail.getmId(); 
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
			Employee employee = emprepo.findById(employeeId);
			if (employee != null) {
				mail.getEmployees().add(employee);
				repo.updateMail(mail); 
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
