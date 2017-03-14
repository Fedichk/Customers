package com.fedich.repository;

import com.fedich.model.Product;
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

public class ProductDAO implements DAO<Product> {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Product product) {
        entityManager.persist(product);
    }

    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }

    public void update(Product product) {
        entityManager.merge(product);
    }

    public void delete(Product product) {
        if (entityManager.contains(product)) {
            entityManager.remove(product);
        } else {
            entityManager.remove(entityManager.merge(product));
        }
    }

    public List<Product> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);
        TypedQuery<Product> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
