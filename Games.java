package com.money;//project notes:
/*
- make username(unique)
- login using user ID and password(your password can be any length)
- unique user ID can also add their games and game ID(or misc)(input string instead of combobox)
- use machine date
- interfaced date

 */

public class Games {

    private String GameName;
    private int GameID;

    public String getGameName() {
        return GameName;
    }

    public int getGameID() {
        return GameID;
    }



    public void setGameName(String gameName) {
        this.GameName = gameName;
    }

    public void setGameID(int gameID) {
        this.GameID = gameID;
    }



    public Games(){

    }
    //constructooooor
    public Games(String gameName, int gameID) {
        this.GameName = gameName;
        this.GameID = gameID;

    }

    @Override
    public String toString() {
        return GameName + " - " + GameID;
    }
}
