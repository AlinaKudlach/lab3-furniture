package com.lab3.service;

import com.lab3.model.FurnitureSet;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandler {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    public static List<FurnitureSet> readFromFile(String filename) {
        List<FurnitureSet> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 6) {
                    logError("Неверное количество полей: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String type = parts[2];
                    String material = parts[3];
                    double price = Double.parseDouble(parts[4]);
                    Date date = SDF.parse(parts[5]);
                    list.add(new FurnitureSet(id, name, type, material, price, date));
                } catch (NumberFormatException | ParseException e) {
                    logError("Ошибка формата данных в строке: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
        return list;
    }

    public static void writeToFile(String filename, List<FurnitureSet> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (FurnitureSet fs : list) {
                String line = fs.getId() + ";" + fs.getName() + ";" + fs.getType() + ";" +
                        fs.getMaterial() + ";" + fs.getPrice() + ";" + SDF.format(fs.getReleaseDate());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }

    private static void logError(String message) {
        System.out.println("ОШИБКА: " + message);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("errors.log", true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException ignored) { }
    }
}