package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.Patient;

@Repository
public class PatientDAO extends DAO<Patient> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public Patient create(Patient entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public Patient read(Long entityId) {
		return entityManager.find(Patient.class,entityId);
	}

	@Override
	@Transactional
	public Patient update(Patient entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(Patient entity) {
		Patient toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<Patient> findAll() {
		
		List<Patient> llistatTots = (List<Patient>)entityManager.createNamedQuery("Patient.findAll").getResultList();

		return llistatTots;
	}
}
