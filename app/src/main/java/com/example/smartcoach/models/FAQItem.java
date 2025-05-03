package com.example.smartcoach.models;

public class FAQItem {
    private String question;
    private String answer;

    // Конструктор
    public FAQItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Геттери
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}