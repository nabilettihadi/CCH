package com.cch.cyclingmanager.repository.impl;

import com.cch.cyclingmanager.repository.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GenericRepositoryImpl<T, ID> implements GenericRepository<T, ID> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public GenericRepositoryImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(getCurrentSession().get(entityClass, (java.io.Serializable) id));
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + entityClass.getName(), entityClass).list();
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete);
    }
}