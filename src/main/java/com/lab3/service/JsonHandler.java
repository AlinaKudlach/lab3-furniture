package com.lab3.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lab3.model.FurnitureSet;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeToJson(String filename, List<FurnitureSet> items) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(items, writer);
            System.out.println("JSON записан: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка JSON: " + e.getMessage());
        }
    }

    public static List<FurnitureSet> readFromJson(String filename) {
        try (Reader reader = new FileReader(filename)) {
            Type listType = new TypeToken<List<FurnitureSet>>() {}.getType();
            List<FurnitureSet> list = gson.fromJson(reader, listType);
            System.out.println("JSON прочитан: " + filename);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}