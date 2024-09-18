package com.postal.hibrepoimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.postal.model.PostOfficeHead;
import com.postal.repository.PostOfficeRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@Repository
public class PostOfficeRepoImp implements PostOfficeRepo{

	@Autowired
	EntityManager em;

	@Override
	public void addPo(PostOfficeHead po) {

		em.merge(po);
	}

	@Override
	public void delPo(int pincode) {
		PostOfficeHead po = em.find(PostOfficeHead.class, pincode);
		if (po != null) {
			em.remove(po);
		}		
	}

	@Override
	public void updatePo(PostOfficeHead po) {

		em.merge(po);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostOfficeHead> getAllPos() {
		return em.createQuery("from PostOfficeHead").getResultList();

	}

	@Override
	public PostOfficeHead findByPincode(int pincode) {
		Query q = em.createQuery("from PostOfficeHead where pincode = ?1");
		q.setParameter(1, pincode);
		return (PostOfficeHead) q.getSingleResult();
	}

	@Override
	public Optional<PostOfficeHead> findByCity(String city) {
		Query query = em.createQuery("SELECT u FROM PostOfficeHead u WHERE u.city = :city");
		query.setParameter("city", city);
		try {
			PostOfficeHead po = (PostOfficeHead) query.getSingleResult();
			return Optional.of(po);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<PostOfficeHead> findByState(String state) {
		Query query = em.createQuery("SELECT u FROM PostOfficeHead u WHERE u.state = :state");
		query.setParameter("state", state);
		try {
			PostOfficeHead po = (PostOfficeHead) query.getSingleResult();
			return Optional.of(po);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public PostOfficeHead Login(int pincode, String password) {
		Query q = em.createQuery("from PostOfficeHead where pincode =?1 and password = ?2");
		q.setParameter(1, pincode);
		q.setParameter(2, password);
		return (PostOfficeHead) q.getSingleResult();	}

}



