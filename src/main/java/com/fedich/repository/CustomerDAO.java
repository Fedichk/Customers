package com.fedich.repository;

import com.fedich.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional

public class CustomerDAO implements DAO <Customer> {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer getById(long id){
        return entityManager.find(Customer.class, id);
    }

    public void update(Customer customer){
        entityManager.merge(customer);
    }

    public void delete(Customer customer) {
        if (entityManager.contains(customer)) {
            entityManager.remove(customer);
        } else {
            entityManager.remove(entityManager.merge(customer));
        }
    }

    public List<Customer> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
