package main.java.com.beautyathome.infrastructure.persistence.dao;


public interface BaseDAO<T, ID> {

    T save(T entity);

    T findById(ID id);

    void delete(ID id);
}
