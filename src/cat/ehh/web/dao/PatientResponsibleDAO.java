package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cat.ehh.web.model.PatientResponsible;

@Repository
public class PatientResponsibleDAO extends DAO<PatientResponsible> {

	@PersistenceContext(unitName = "entities")
	protected EntityManager entityManager;

	@Override
	@Transactional
	public PatientResponsible create(PatientResponsible entity) {
		try{
			entityManager.persist(entity);	
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional
	public PatientResponsible read(Long entityId) {
		return entityManager.find(PatientResponsible.class,entityId);
	}

	@Override
	@Transactional
	public PatientResponsible update(PatientResponsible entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public boolean delete(PatientResponsible entity) {
		PatientResponsible toBeRemoved = entityManager.merge(entity);
		entityManager.remove(toBeRemoved);
		return true;
	}

	@Transactional
	public List<PatientResponsible> findAll() {
		
		List<PatientResponsible> llistatTots = (List<PatientResponsible>)entityManager.createNamedQuery("PatientResponsible.findAll").getResultList();

		return llistatTots;
	}
	
	
	@Transactional
	public PatientResponsible findByPatientAndResponsible(int patientId, int responsibleId) {
		
		Query query = entityManager.createQuery("SELECT p FROM PatientResponsible p where p.patientId = ?1 and p.responsibleId = ?2");
		query.setParameter(1, patientId);
		query.setParameter(2, responsibleId);
		
		
		PatientResponsible patientResponsible = (PatientResponsible) query.getSingleResult();

		return patientResponsible;
	}
	
	
}
