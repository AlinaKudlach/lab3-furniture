package com.lab3.model;

import java.util.Date;

public class FurnitureSet extends AbstractEntity {
    private int id;
    private String name;
    private String type;
    private String material;
    private double price;
    private Date releaseDate;

    public FurnitureSet() {
        this.releaseDate = new Date();
    }

    public FurnitureSet(int id, String name, String type, String material, double price, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.material = material;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    @Override public int getId() { return id; }
    @Override public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    @Override
    public String toString() {
        return "ID: " + id + " | Название: " + name + " | Тип: " + type +
                " | Материал: " + material + " | Цена: " + price +
                " | Дата: " + releaseDate;
    }
}