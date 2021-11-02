package com.tenriver.kpop;

public class Question {
    public static final int YEAR_TWOTHOUSANDTEN = 2010;
    public static final int YEAR_TWOTHOUSANDFIFTEEN=2015;
    public static final int YEAR_TWOTHOUSANDSIXTEEN=2016;
    public static final int YEAR_TWOTHOUSANDSEVENTEEN=2015;
    public static final int YEAR_TWOTHOUSANDTWENTY = 2020;
    public static final int YEAR_TWOTHOUSANDTWENTYONE = 2021;


    private String question;
    private String koreanAnswer;
    private String englishAnswer2;
    private String realAnswer;
    private int year;

    public Question() {}

    //constructor
    public Question(String question, String koreanAnswer, String englishAnswer2, String realAnswer, int year) {
        this.question = question;
        this.koreanAnswer = koreanAnswer;
        this.englishAnswer2 = englishAnswer2;
        this.realAnswer = realAnswer;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int[] getAllYear() {
        return new int[]{
                2015,
                2016,
                2017,
                2018,
                2019,
                2020,
                2021
        };
    }
}
