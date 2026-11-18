package com.lab3;

import com.lab3.model.FurnitureSet;
import com.lab3.repository.ListRepository;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ListRepositoryTest {
    @Test
    public void testAddAndGet() {
        ListRepository repo = new ListRepository();
        repo.add(new FurnitureSet(1, "A", "B", "C", 10.0, new Date()));
        assertEquals(1, repo.getAll().size());
    }

    @Test
    public void testDelete() {
        ListRepository repo = new ListRepository();
        repo.add(new FurnitureSet(1, "A", "B", "C", 10.0, new Date()));
        repo.delete(1);
        assertEquals(0, repo.getAll().size());
    }
}