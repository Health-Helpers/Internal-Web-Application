package cat.ehh.web.dao;

public abstract class DAO<T> {

	public abstract T create(T entity);
	public abstract T read(Long entityId);
	public abstract T update(T entity);
	public abstract boolean delete(T entity);
		

}
