package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.Responsible;

@Repository
public class ResponsibleDAO extends DAO<Responsible> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public Responsible create(Responsible entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public Responsible read(Long entityId) {
		return entityManager.find(Responsible.class,entityId);
	}

	@Override
	@Transactional
	public Responsible update(Responsible entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(Responsible entity) {
		Responsible toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<Responsible> findAll() {
		
		List<Responsible> llistatTots = (List<Responsible>)entityManager.createNamedQuery("Responsible.findAll").getResultList();

		return llistatTots;
	}
}
