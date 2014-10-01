package com.realdolmen.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractRepositoy<T>
{
        @PersistenceContext
        protected EntityManager entityManager;

        private Class<T> t;
        private List<T> tList = new ArrayList<>();

        public List<T> findAll() {
            Query query = entityManager.createQuery("SELECT t FROM " + returnEntityClass().getSimpleName() + " t");
            if (query.getResultList() == null) {
                return tList;
            }
            return query.getResultList();
        }

        public void persist(T t) {
            entityManager.persist(t);
        }


        public T find(Integer id) {

            return entityManager.find(returnEntityClass(), id);
        }


        public void remove(Integer id) {
            entityManager.remove(entityManager.getReference(returnEntityClass(), id));
        }

        /*Is needed because of the bad generics to get class name and class from generic type*/
        @SuppressWarnings("unchecked")
        public Class<T> returnEntityClass() {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        }

        public EntityManager getEntityManager() {
            return entityManager;
        }
    }

