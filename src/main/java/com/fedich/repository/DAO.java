package com.fedich.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DAO <T> {

    public void create (T t);

    public T getById(long id);

    public void update(T t);

    public void delete(T t);

    public List<T> getAll ();
}
