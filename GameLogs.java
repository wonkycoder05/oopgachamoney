package com.money;

public class GameLogs {
    private String gamesName;

    private int Spending;

    private int Pity;

    private String CharName;

    private int Day, Month, Year;

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public GameLogs() {

    }


    //getter setter

    public String getCharName() {
        return CharName;
    }

    public void setCharName(String charName) {
        CharName = charName;
    }

    public int getPity() {
        return Pity;
    }

    public void setPity(int pity) {
        Pity = pity;
        if(pity<=0){
            throw new IllegalStateException("value cannot be minus");
        }
    }

    public String getGamesName() {
        return gamesName;
    }

    public int getSpending() {
        return Spending;
    }

    public void setGamesName(String gamesName) {
        this.gamesName = gamesName;

    }

    public void setSpending(int spending) {
        Spending = spending;
        if(spending <0){
            throw new IllegalStateException("value cannot be minus");
        }
    }


    //constructoooor


    public GameLogs(String gamesName, int spending, int pity, String charName, int day, int month, int year) {
        this.gamesName = gamesName;
        Spending = spending;
        Pity = pity;
        CharName = charName;
        Day = day;
        Month = month;
        Year = year;

    }

    @Override
    public String toString() {
        return Day + "/" + Month + "/" + Year;
    }

    public void clear() {
    }

    public void add(GameLogs log) {
    }
}
