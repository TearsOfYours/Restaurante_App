package ies.sequeros.com.dam.pmdm.commons.infraestructura;

import java.util.List;

public interface IDao<T> {
    public T getById(String id);
    public List<T> getAll();
    public void update(T item);
    public void delete(T item);
    public void insert(T item);

}