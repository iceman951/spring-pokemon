package com.icesoft.demo.models;

public class Pokemon {
    private int id;
    private String name;
    private int level;
    private String type;

    public Pokemon(int id, String name, int level, String type) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
