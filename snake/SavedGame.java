package com.javarush.games.snake;

import java.io.Serializable;
 //НЕ РАБОТАЕТ!!!
public class SavedGame implements Serializable {

    private static final long serialVersionUID = 1L;

    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;


    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }



    public int getTurnDelay() {
        return turnDelay;
    }

    public void setTurnDelay(int turnDelay) {
        this.turnDelay = turnDelay;
    }


    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }


    public boolean isGameStopped() {
        return isGameStopped;
    }

    public void setGameStopped(boolean gameStopped) {
        isGameStopped = gameStopped;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public SavedGame(Snake snake, int turnDelay, Apple apple, boolean isGameStopped, int score) {
        this.snake = snake;
        this.turnDelay = turnDelay;
        this.apple = apple;
        this.isGameStopped = isGameStopped;
        this.score = score;
    }


}
