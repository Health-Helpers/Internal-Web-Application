package cat.ehh.web.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.Language;

@Repository
public class LanguageDAO extends DAO<Language> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public Language create(Language entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public Language read(BigDecimal entityId) {
		return entityManager.find(Language.class,entityId);
	}

	@Override
	@Transactional
	public Language update(Language entity) {
		entityManager.merge(entity);
		entityManager.refresh(entity);

		return entity;
	}

	@Override
	@Transactional
	public boolean delete(Language entity) {
		entityManager.remove(entity);

		return true;
	}

}
