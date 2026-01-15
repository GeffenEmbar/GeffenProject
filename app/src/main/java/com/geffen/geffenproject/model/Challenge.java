package com.geffen.geffenproject.model;

import java.util.ArrayList;

public class Challenge {
    protected String id;

    protected String instrument;
    protected String type;
    protected String question;
    protected String correctAnswer;
    protected String false1;
    protected String false2;
    protected String false3;
    protected String difficulty;
    protected String chordType;
    protected boolean completed;




    // פעולות בונות
    public Challenge(String chordType, boolean completed, String correctAnswer, String difficulty, String false1, String false2, String false3, String id, String instrument, String question, String type) {
        this.chordType = chordType;
        this.completed = completed;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
        this.id = id;
        this.instrument = instrument;
        this.question = question;
        this.type = type;
    }
    public Challenge(String correctAnswer, String difficulty, String false1, String false2, String false3, String id, String instrument, String question, String type) {
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
        this.id = id;
        this.instrument = instrument;
        this.question = question;
        this.type = type;
        this.completed = false;
    }

    public Challenge(boolean completed, String correctAnswer, String difficulty, String false1, String false2, String false3, String id, String instrument, String question, String type) {
        this.completed = completed;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.false1 = false1;
        this.false2 = false2;
        this.false3 = false3;
        this.id = id;
        this.instrument = instrument;
        this.question = question;
        this.type = type;
    }
    public Challenge() {
    }
    // פעולות אחזור ושחזור
    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getChordType() {
        return chordType;
    }

    public void setChordType(String chordType) {
        this.chordType = chordType;
    }

    public String getFalse1() {
        return false1;
    }

    public void setFalse1(String false1) {
        this.false1 = false1;
    }

    public String getFalse2() {
        return false2;
    }

    public void setFalse2(String false2) {
        this.false2 = false2;
    }

    public String getFalse3() {
        return false3;
    }

    public void setFalse3(String false3) {
        this.false3 = false3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    // פעולת בדיקת תשובה
    public boolean checkAnswer(String userAnswer)
    {
        return (userAnswer.equals(this.correctAnswer));
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "completed=" + completed +
                ", id='" + id + '\'' +
                ", instrument='" + instrument + '\'' +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", false1='" + false1 + '\'' +
                ", false2='" + false2 + '\'' +
                ", false3='" + false3 + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }


}
