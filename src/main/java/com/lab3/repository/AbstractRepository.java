package com.lab3.repository;

import com.lab3.model.AbstractEntity;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {
    public abstract void add(T item);
    public abstract void update(T item);
    public abstract void delete(int id);
    public abstract List<T> getAll();
}