package com.geffen.geffenproject.model;

import java.util.ArrayList;

public class Challenge {
    protected String id;



    public Challenge(boolean completed, String correctAnswer, Integer difficulty, String id, String instrument, ArrayList<String> options, String question, String type) {
        this.completed = completed;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.id = id;
        this.instrument = instrument;
        this.options = options;
        this.question = question;
        this.type = type;
    }

    protected String instrument, type, question, correctAnswer;
    protected ArrayList<String> options;
    protected Integer difficulty;
    protected boolean completed;



    // פעולות בונות
    public Challenge(String instrument, String type, String question, String correctAnswer, ArrayList<String> options, Integer difficulty, boolean completed) {
        this.instrument = instrument;
        this.type = type;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.difficulty = difficulty;
        this.completed = completed;
    }
    public Challenge(String instrument, String type, String question, String correctAnswer, ArrayList<String> options, Integer difficulty) {
        this.instrument = instrument;
        this.type = type;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.difficulty = difficulty;
        this.completed = false;
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

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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



    // פעולת הדפסה
    @Override
    public String toString() {
        return "Challenge{" +
                "instrument='" + instrument + '\'' +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", options=" + options +
                ", difficulty=" + difficulty +
                ", completed=" + completed +
                '}';
    }
}
