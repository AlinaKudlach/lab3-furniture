package com.lab3.ui;

import com.lab3.model.FurnitureSet;
import com.lab3.repository.AbstractRepository;
import com.lab3.service.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuManager {
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void start(AbstractRepository<FurnitureSet> listRepo, AbstractRepository<FurnitureSet> mapRepo) {
        while (true) {
            System.out.println("\nМЕНЮ:");
            System.out.println("1. Вывести List");
            System.out.println("2. Вывести Map");
            System.out.println("3. Добавить");
            System.out.println("4. Удалить по ID");
            System.out.println("5. Сортировать по цене");
            System.out.println("6. Сортировать по дате (новые сначала)");
            System.out.println("7. Сохранить в XML");
            System.out.println("8. Загрузить из XML");
            System.out.println("9. Сохранить в JSON");
            System.out.println("10. Загрузить из JSON");
            System.out.println("11. Зашифровать данные");
            System.out.println("12. Расшифровать данные");
            System.out.println("13. Архивировать TXT в ZIP");
            System.out.println("0. Выход и сохранение в TXT");
            System.out.print("Выбор: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": printAll(listRepo.getAll()); break;
                case "2": printAll(mapRepo.getAll()); break;
                case "3": addItem(listRepo, mapRepo); break;
                case "4": deleteItem(listRepo, mapRepo); break;
                case "5": sortByPrice(listRepo); break;
                case "6": sortByDateDesc(listRepo); break;
                case "7": XmlHandler.writeToXml("furniture.xml", listRepo.getAll()); break;
                case "8":
                    List<FurnitureSet> loadedXml = XmlHandler.readFromXml("furniture.xml");
                    loadedXml.forEach(item -> { listRepo.add(item); mapRepo.add(item); });
                    System.out.println("Загружено из XML: " + loadedXml.size());
                    break;
                case "9": JsonHandler.writeToJson("furniture.json", listRepo.getAll()); break;
                case "10":
                    List<FurnitureSet> loadedJson = JsonHandler.readFromJson("furniture.json");
                    loadedJson.forEach(item -> { listRepo.add(item); mapRepo.add(item); });
                    System.out.println("Загружено из JSON: " + loadedJson.size());
                    break;
                case "11": encryptData(listRepo); break;
                case "12": decryptData(); break;
                case "13": Archiver.zipFile("furniture.txt", "furniture.zip"); break;
                case "0":
                    FileHandler.writeToFile("furniture.txt", listRepo.getAll());
                    System.out.println("Данные сохранены. Выход.");
                    return;
                default: System.out.println("Неверный ввод.");
            }
        }
    }

    private void printAll(List<FurnitureSet> items) {
        if (items.isEmpty()) { System.out.println("Список пуст."); return; }
        items.forEach(System.out::println);
    }

    private void addItem(AbstractRepository<FurnitureSet> listRepo, AbstractRepository<FurnitureSet> mapRepo) {
        try {
            System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Название: "); String name = scanner.nextLine();
            System.out.print("Тип: "); String type = scanner.nextLine();
            System.out.print("Материал: "); String material = scanner.nextLine();
            System.out.print("Цена: "); double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Дата (дд-мм-гггг): "); Date date = dateFormat.parse(scanner.nextLine());
            FurnitureSet item = new FurnitureSet(id, name, type, material, price, date);
            listRepo.add(item); mapRepo.add(item);
            System.out.println("Добавлено!");
        } catch (Exception e) { System.out.println("Ошибка ввода!"); }
    }

    private void deleteItem(AbstractRepository<FurnitureSet> listRepo, AbstractRepository<FurnitureSet> mapRepo) {
        System.out.print("Введите ID для удаления: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            listRepo.delete(id); mapRepo.delete(id);
            System.out.println("Удалено.");
        } catch (Exception e) { System.out.println("Ошибка ID."); }
    }

    private void sortByPrice(AbstractRepository<FurnitureSet> repo) {
        List<FurnitureSet> list = new ArrayList<>(repo.getAll());
        list.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        System.out.println("Отсортировано по цене:"); printAll(list);
    }

    private void sortByDateDesc(AbstractRepository<FurnitureSet> repo) {
        List<FurnitureSet> list = new ArrayList<>(repo.getAll());
        list.sort((a, b) -> b.getReleaseDate().compareTo(a.getReleaseDate()));
        System.out.println("Отсортировано по дате:"); printAll(list);
    }

    private void encryptData(AbstractRepository<FurnitureSet> repo) {
        System.out.print("Ключ (16 символов): ");
        String key = scanner.nextLine();
        StringBuilder data = new StringBuilder();
        for (FurnitureSet item : repo.getAll()) data.append(item).append("\n");
        String enc = EncryptionHandler.encrypt(data.toString(), key);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("encrypted.txt"))) {
            bw.write(enc);
            System.out.println("Зашифровано в encrypted.txt");
        } catch (IOException e) { System.out.println("Ошибка: " + e.getMessage()); }
    }

    private void decryptData() {
        System.out.print("Ключ: ");
        String key = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader("encrypted.txt"))) {
            System.out.println("Расшифровано:\n" + EncryptionHandler.decrypt(br.readLine(), key));
        } catch (Exception e) { System.out.println("Ошибка: " + e.getMessage()); }
    }
}