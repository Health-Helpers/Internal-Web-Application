package cat.ehh.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public UserEHH read(Long entityId) {
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
		UserEHH removedUser = entityManager.merge(entity);
		entityManager.remove(removedUser);
		return true;
	}

	@Transactional
	public List<UserEHH> findAll() {
		List<UserEHH> llistatTots = (List<UserEHH>)entityManager.createNamedQuery("User.findAll").getResultList();
		return llistatTots;
	}

	public boolean checkUserExistence(String idDoc, String phone) {
		
		Query query = entityManager.createQuery("SELECT u FROM UserEHH u where u.iddoc = ?1 and u.phone = ?2");
		query.setParameter(1, idDoc);
		query.setParameter(2, phone);
		
		List<UserEHH> userList = (List<UserEHH>) query.getResultList();
		
		if(userList!=null && userList.size()==0){
			return false;	
		}else if(userList==null || userList.size()>0){
			return true;
		}else{
			return true;
		}
		
	}
}

