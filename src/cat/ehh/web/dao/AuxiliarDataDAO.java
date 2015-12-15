package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.Auxiliar_data;

@Repository
public class AuxiliarDataDAO extends DAO<Auxiliar_data> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public Auxiliar_data create(Auxiliar_data entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public Auxiliar_data read(Long entityId) {
		return entityManager.find(Auxiliar_data.class,entityId);
	}

	@Override
	@Transactional
	public Auxiliar_data update(Auxiliar_data entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(Auxiliar_data entity) {
		Auxiliar_data toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<Auxiliar_data> findAll() {
		
		List<Auxiliar_data> llistatTots = (List<Auxiliar_data>)entityManager.createNamedQuery("Auxiliar_data.findAll").getResultList();

		return llistatTots;
	}
}
