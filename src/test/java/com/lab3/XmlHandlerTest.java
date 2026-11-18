package com.lab3;

import com.lab3.model.FurnitureSet;
import com.lab3.service.XmlHandler;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class XmlHandlerTest {
    @Test
    public void testWriteAndReadXml() {
        String file = "test_furniture.xml";
        List<FurnitureSet> list = Arrays.asList(
                new FurnitureSet(1, "A", "B", "C", 10.0, new Date())
        );
        XmlHandler.writeToXml(file, list);
        List<FurnitureSet> read = XmlHandler.readFromXml(file);
        assertEquals(1, read.size());
        assertEquals("A", read.get(0).getName());
    }
}