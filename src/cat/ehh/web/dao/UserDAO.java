package cat.ehh.web.dao;

import java.math.BigDecimal;

import cat.ehh.web.model.User;

public class UserDAO extends DAO<User> {

	
	@Override
	public User create(User entity) {
		entityManager.persist(entity);
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
