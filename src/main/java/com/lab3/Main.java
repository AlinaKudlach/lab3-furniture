package com.lab3;

import com.lab3.model.FurnitureSet;
import com.lab3.repository.ListRepository;
import com.lab3.repository.MapRepository;
import com.lab3.service.FileHandler;
import com.lab3.ui.MenuManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListRepository listRepo = new ListRepository();
        MapRepository mapRepo = new MapRepository();

        List<FurnitureSet> loaded = FileHandler.readFromFile("furniture.txt");
        for (FurnitureSet item : loaded) {
            listRepo.add(item);
            mapRepo.add(item);
        }

        MenuManager menu = new MenuManager();
        menu.start(listRepo, mapRepo);
    }
}