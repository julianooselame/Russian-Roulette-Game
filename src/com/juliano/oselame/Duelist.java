package com.juliano.oselame;

public class Duelist {
    String name;
    boolean alive = true;

    public String getName() {
        return name;
    }

    public Duelist setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isAlive() {
        return alive;
    }

    public Duelist setAlive(boolean alive) {
        this.alive = alive;
        return this;
    }
}
