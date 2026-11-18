package com.lab3;

import com.lab3.model.FurnitureSet;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class FurnitureSetTest {
    @Test
    public void testConstructor() {
        FurnitureSet fs = new FurnitureSet(1, "Кухня", "Кухня", "ДСП", 1000.0, new Date());
        assertEquals(1, fs.getId());
        assertEquals("Кухня", fs.getName());
        assertEquals(1000.0, fs.getPrice());
    }

    @Test
    public void testSetters() {
        FurnitureSet fs = new FurnitureSet();
        fs.setId(5);
        fs.setName("Спальня");
        assertEquals(5, fs.getId());
        assertEquals("Спальня", fs.getName());
    }
}