package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.AdjBackup;

@Repository
public class AdjBackupDAO extends DAO<AdjBackup> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public AdjBackup create(AdjBackup entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public AdjBackup read(Long entityId) {
		return entityManager.find(AdjBackup.class,entityId);
	}

	@Override
	@Transactional
	public AdjBackup update(AdjBackup entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(AdjBackup entity) {
		AdjBackup toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<AdjBackup> findAll() {
		
		List<AdjBackup> llistatTots = (List<AdjBackup>)entityManager.createNamedQuery("AdjBackup.findAll").getResultList();
		

		return llistatTots;
	}

}
