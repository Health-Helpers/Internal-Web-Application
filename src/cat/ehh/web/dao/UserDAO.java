package cat.ehh.web.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.UserEHH;

@Repository
public class UserDAO extends DAO<UserEHH> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public UserEHH create(UserEHH entity) {
		try{
			this.entityManager.persist(entity);			
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public UserEHH read(BigDecimal entityId) {
		return entityManager.find(UserEHH.class,entityId);
	}

	@Override
	@Transactional
	public UserEHH update(UserEHH entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(UserEHH entity) {
		entityManager.remove(entity);

		return true;
	}

}

