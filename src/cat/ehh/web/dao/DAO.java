package cat.ehh.web.dao;

import java.math.BigDecimal;

public abstract class DAO<T> {

	public abstract T create(T entity);
	public abstract T read(BigDecimal entityId);
	public abstract T update(T entity);
	public abstract boolean delete(T entity);
		

}
