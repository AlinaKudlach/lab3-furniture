package com.lab3.service;

import com.lab3.model.FurnitureSet;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlHandler {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    public static void writeToXml(String filename, List<FurnitureSet> items) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("furniture");
            doc.appendChild(root);

            for (FurnitureSet item : items) {
                Element fs = doc.createElement("furnitureSet");
                fs.setAttribute("id", String.valueOf(item.getId()));
                fs.appendChild(createEl(doc, "name", item.getName()));
                fs.appendChild(createEl(doc, "type", item.getType()));
                fs.appendChild(createEl(doc, "material", item.getMaterial()));
                fs.appendChild(createEl(doc, "price", String.valueOf(item.getPrice())));
                fs.appendChild(createEl(doc, "releaseDate", SDF.format(item.getReleaseDate())));
                root.appendChild(fs);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(filename)));
            System.out.println("XML записан: " + filename);
        } catch (Exception e) {
            System.out.println("Ошибка XML: " + e.getMessage());
        }
    }

    private static Element createEl(Document doc, String name, String value) {
        Element el = doc.createElement(name);
        el.setTextContent(value);
        return el;
    }

    public static List<FurnitureSet> readFromXml(String filename) {
        List<FurnitureSet> result = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filename));
            NodeList nodeList = doc.getElementsByTagName("furnitureSet");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element el = (Element) nodeList.item(i);
                int id = Integer.parseInt(el.getAttribute("id"));
                String name = el.getElementsByTagName("name").item(0).getTextContent();
                String type = el.getElementsByTagName("type").item(0).getTextContent();
                String material = el.getElementsByTagName("material").item(0).getTextContent();
                double price = Double.parseDouble(el.getElementsByTagName("price").item(0).getTextContent());
                Date date = SDF.parse(el.getElementsByTagName("releaseDate").item(0).getTextContent());
                result.add(new FurnitureSet(id, name, type, material, price, date));
            }
            System.out.println("XML прочитан: " + filename);
        } catch (Exception e) {
            System.out.println("Ошибка чтения XML: " + e.getMessage());
        }
        return result;
    }
}