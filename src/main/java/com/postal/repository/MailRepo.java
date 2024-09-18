package com.postal.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.postal.model.Address;
import com.postal.model.Mail;
import com.postal.model.User;

public interface MailRepo {

	public void addMail(Mail mail);

	public void delMail(int mId);

	public void updateMail(Mail mail);

	public List<Mail> getAllMail();

	Optional<Integer> findUserPincodeByMailId(int mId);

	public Mail findMailbyMailId(int mId);

	Optional<Address> findAddressByMailId(int mId);

	public void addMal(String service1, String articleType, String articlecontent, LocalDate createdAt, int price,
			int weight, int length, int height, int width, int value, String collectiondate, String time, String status,
			Integer user, Integer address);

	public List<Mail> findMailsByPinCode(String pincode);

	public List<Mail> findToMailsByPinCode(String toPincode);

	public Optional<Integer> findToPincodeByMailId(int mId);

	public List<Mail> getFromMailforEmployee(int empId);
	
	public List<Mail> getToMailforEmployee(int empId);

}
