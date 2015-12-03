package cat.ehh.web.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public abstract class DAO<T> {


	
	public abstract T create(T entity);
	public abstract T read(BigDecimal entityId);
	public abstract T update(T entity);
	public abstract boolean delete(T entity);
		

}
