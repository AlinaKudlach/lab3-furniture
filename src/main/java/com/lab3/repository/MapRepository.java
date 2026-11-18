package com.lab3.repository;

import com.lab3.model.FurnitureSet;
import java.util.*;

public class MapRepository extends AbstractRepository<FurnitureSet> {
    private Map<Integer, FurnitureSet> items = new TreeMap<>();

    @Override public void add(FurnitureSet item) { items.put(item.getId(), item); }
    @Override public void update(FurnitureSet item) { if (items.containsKey(item.getId())) items.put(item.getId(), item); }
    @Override public void delete(int id) { items.remove(id); }
    @Override public List<FurnitureSet> getAll() { return new ArrayList<>(items.values()); }
}