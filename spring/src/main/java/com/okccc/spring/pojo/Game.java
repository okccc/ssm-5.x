package com.okccc.spring.pojo;

/**
 * @Author: okccc
 * @Date: 2022/10/23 1:34 下午
 * @Desc:
 */
public class Game {

    private Integer gameId;

    private String gameName;

    public Game() {
    }

    public Game(Integer gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
