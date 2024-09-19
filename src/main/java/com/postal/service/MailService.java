package com.postal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.postal.model.Address;
import com.postal.model.Mail;

public interface MailService {

	public void delMail(int mId);

	public void updateMail(Mail mail);

	public List<Mail> getAllMail();

	public Mail findMailbyMailId(int mId);

	public Optional<Integer> findUserPincodeByMailId(int mId);

	Optional<Address> getAddressByMailId(int mId);

	public Integer addMal(String service, String articleType, String articlecontent, LocalDate createdAt, int price,
			int weight, int length, int height, int width, int value, String collectiondate, String time, String status,
			Integer user, Integer address);

	public List<Mail> getMailsByPinCode(String pincode);
	
	public List<Mail> findToMailsByPinCode(String toPincode);
	
	public Optional<Integer> findToPincodeByMailId(int mId);
	
	void assignEmployeeToMail(int mailId, int employeeId);
	
	public List<Mail> getFromMailforEmployee(int empId);
	
	public List<Mail> getToMailforEmployee(int empId);
	
	public boolean updatestatus(int mId,String status);

	


}
