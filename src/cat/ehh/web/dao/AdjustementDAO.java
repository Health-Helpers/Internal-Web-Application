package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.Adjustement;

@Repository
public class AdjustementDAO extends DAO<Adjustement> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public Adjustement create(Adjustement entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public Adjustement read(Long entityId) {
		return entityManager.find(Adjustement.class,entityId);
	}

	@Override
	@Transactional
	public Adjustement update(Adjustement entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(Adjustement entity) {
		Adjustement toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<Adjustement> findAll() {
		
		List<Adjustement> llistatTots = (List<Adjustement>)entityManager.createNamedQuery("Adjustement.findAll").getResultList();
		

		return llistatTots;
	}
}
