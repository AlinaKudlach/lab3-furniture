package com.lab3;

import com.lab3.model.FurnitureSet;
import com.lab3.service.FileHandler;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    @Test
    public void testWriteAndRead() {
        String file = "test_furniture.txt";
        List<FurnitureSet> list = Arrays.asList(
                new FurnitureSet(1, "A", "B", "C", 10.0, new Date()),
                new FurnitureSet(2, "D", "E", "F", 20.0, new Date())
        );
        FileHandler.writeToFile(file, list);
        List<FurnitureSet> read = FileHandler.readFromFile(file);
        assertEquals(2, read.size());
        assertEquals("A", read.get(0).getName());
    }
}