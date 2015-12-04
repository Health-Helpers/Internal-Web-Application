package cat.ehh.web.dao;

import java.util.List;

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
	public Language read(Long entityId) {
		return entityManager.find(Language.class,entityId);
	}

	@Override
	@Transactional
	public Language update(Language entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(Language entity) {
		Language toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<Language> findAll() {
		
		List<Language> llistatTots = (List<Language>)entityManager.createNamedQuery("Language.findAll").getResultList();
		

		return llistatTots;
	}
	
}
