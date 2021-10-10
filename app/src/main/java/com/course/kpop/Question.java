package com.course.kpop;

public class Question {
    private String question;
    private String koreanAnswer;
    private String englishAnswer2;
    private String realAnswer;

    public Question() {}

    //constructor
    public Question(String question, String koreanAnswer, String englishAnswer2, String realAnswer) {
        this.question = question;
        this.koreanAnswer = koreanAnswer;
        this.englishAnswer2 = englishAnswer2;
        this.realAnswer = realAnswer;
    }


    //getter and setter
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getKoreanAnswer() {
        return koreanAnswer;
    }

    public void setKoreanAnswer(String koreanAnswer) {
        this.koreanAnswer = koreanAnswer;
    }

    public String getEnglishAnswer2() {
        return englishAnswer2;
    }

    public void setEnglishAnswer2(String englishAnswer2) {
        this.englishAnswer2 = englishAnswer2;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }
}
