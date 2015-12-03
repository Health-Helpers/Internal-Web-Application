package cat.ehh.web.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import org.springframework.beans.factory.annotation.Autowired;

import cat.ehh.web.model.User;

public class UserDAO extends DAO<User> {


	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	@Autowired
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager em){
		this.entityManager = em;
	}


	@Override
	public User create(User entity) {
		this.entityManager.persist(entity);

		entityManager.refresh(entity);
		return entity;
	}

	@Override
	public User read(BigDecimal entityId) {
		return entityManager.find(User.class,entityId);
	}

	@Override
	public User update(User entity) {
		entityManager.merge(entity);
		entityManager.refresh(entity);

		return entity;
	}

	@Override
	public boolean delete(User entity) {
		entityManager.remove(entity);

		return true;
	}

}
