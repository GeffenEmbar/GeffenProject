package com.geffen.geffenproject.model;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
    private ArrayList<Challenge> sides;
    private Random random;
    // פעולות בונות
    public Dice(Random random, ArrayList<Challenge> sides) {
        this.random = random;
        this.sides = sides;
    }
    public Dice() {
    }
    // פעולות אחזור ושחזור
    public ArrayList<Challenge> getSides() {
        return sides;
    }
    public void setSides(ArrayList<Challenge> sides) {
        this.sides = sides;
    }
    public Random getRandom() {
        return random;
    }
    public void setRandom(Random random) {
        this.random = random;
    }


    // פעולת גלגול
    public String roll()
    {
        int index = random.nextInt(sides.size());
        return sides.get(index).getQuestion();
    }

    // פעולת הדפסה
    @Override
    public String toString() {
        return "Dice{" +
                "sides=" + sides +
                ", random=" + random +
                '}';
    }
}
