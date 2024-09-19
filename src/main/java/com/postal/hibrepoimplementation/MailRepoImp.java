package com.postal.hibrepoimplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postal.model.Address;
import com.postal.model.Mail;
import com.postal.model.User;
import com.postal.repository.MailRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class MailRepoImp implements MailRepo {

	@Autowired
	EntityManager em;

	@Override
	public void delMail(int mId) {
		Mail mail = em.find(Mail.class, mId);
		if (mail != null) {
			em.remove(mail);
		}
	}

	@Override
	public void updateMail(Mail mail) {
		em.merge(mail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> getAllMail() {
		return em.createQuery("from Mail").getResultList();

	}


	@Override
	public Optional<Integer> findUserPincodeByMailId(int mId) {
		try {
			Query query = em.createQuery("SELECT m.user.pincode FROM Mail m WHERE m.mId = :mId");
			query.setParameter("mId", mId);
			String pincodeString = (String) query.getSingleResult();
			Integer pincode = Integer.valueOf(pincodeString); // Convert String to Integer
			return Optional.ofNullable(pincode);
		} catch (NoResultException e) {
			return Optional.empty(); 
		}
	}
	
	@Override
	public Optional<Integer> findToPincodeByMailId(int mId) {
		try {
			Query query = em.createQuery("SELECT m.address.toPincode FROM Mail m WHERE m.mId = :mId");
			query.setParameter("mId", mId);
			String pincodeString = (String) query.getSingleResult();
			Integer pincode = Integer.valueOf(pincodeString); 
			return Optional.ofNullable(pincode);
		} catch (NoResultException e) {
			return Optional.empty(); 
		}
	}

	@Override
	public Mail findMailbyMailId(int mId) {
		Query q = em.createQuery("from Mail where mId = ?1");
		q.setParameter(1, mId);
		return (Mail) q.getSingleResult();
	}

	@Override
	public Optional<Address> findAddressByMailId(int mId) {
		try {
			Query query = em.createQuery("SELECT m.address FROM Mail m WHERE m.mId = :mId");
			query.setParameter("mId", mId);
			Address address = (Address) query.getSingleResult();
			return Optional.ofNullable(address);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	
	
	@Override
	public Mail addMal(String service1, String articleType, String articlecontent, LocalDate createdAt, int price,
	                   int weight, int length, int height, int width, int value, String collectiondate, 
	                   String time, String status, Integer user, Integer address) {
	    User reg = em.find(User.class, user);
	    Address add = em.find(Address.class, address);

	    Mail ml = new Mail();
	    ml.setService(service1);
	    ml.setArticleType(articleType);
	    ml.setArticlecontent(articlecontent);
	    ml.setCreatedAt(createdAt);
	    ml.setPrice(price);
	    ml.setWeight(weight);
	    ml.setLength(length);
	    ml.setHeight(height);
	    ml.setWidth(width);
	    ml.setValue(value);
	    ml.setCollectiondate(collectiondate);
	    ml.setTime(time);
	    ml.setStatus(status);
	    ml.setUser(reg);
	    ml.setAddress(add);

	    em.persist(ml); 
	    return ml; 
	}


	@Override
	public List<Mail> findMailsByPinCode(String pincode) {
		String jpql = "SELECT m FROM Mail m WHERE m.user.pincode = :pincode";
		TypedQuery<Mail> query = em.createQuery(jpql, Mail.class);
		query.setParameter("pincode", pincode);
		return query.getResultList();
	}

	@Override
	public List<Mail> findToMailsByPinCode(String toPincode) {
		String jpql = "SELECT m FROM Mail m WHERE m.address.toPincode = :toPincode";
		TypedQuery<Mail> query = em.createQuery(jpql, Mail.class);
		query.setParameter("toPincode", toPincode);
		return query.getResultList();
	}

	@Override
	public List<Mail> getFromMailforEmployee(int empId) {
		String jpql = "SELECT m FROM Mail m JOIN m.employees e JOIN e.pincode p WHERE e.empId = :empId AND p.pincode = m.user.pincode";
		TypedQuery<Mail> query = em.createQuery(jpql, Mail.class);
		query.setParameter("empId", empId);

		return query.getResultList();
	}

	@Override
	public List<Mail> getToMailforEmployee(int empId) {
		String jpql = "SELECT m FROM Mail m JOIN m.employees e JOIN e.pincode p WHERE e.empId = :empId AND p.pincode = m.address.toPincode";
		TypedQuery<Mail> query = em.createQuery(jpql, Mail.class);
		query.setParameter("empId", empId);

		return query.getResultList();
	}

}
