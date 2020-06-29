package com.example.appuser;

public class Question {
    private int id;
    private int views;
    private String title;
    private String author;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;
    private String description;

//    private Answer[] answers;
    public Question(){}

    public Question(String category, String title, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.views=0;
    }

//    getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
//
//    public Answer[] getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(Answer[] answers) {
//        this.answers = answers;
//    }
}
