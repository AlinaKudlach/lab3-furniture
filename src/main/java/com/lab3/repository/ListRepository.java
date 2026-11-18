package com.lab3.repository;

import com.lab3.model.FurnitureSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRepository extends AbstractRepository<FurnitureSet> {
    private List<FurnitureSet> items = new ArrayList<>();

    @Override
    public void add(FurnitureSet item) { items.add(item); }

    @Override
    public void update(FurnitureSet item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                items.set(i, item);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        Iterator<FurnitureSet> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public List<FurnitureSet> getAll() { return items; }
}