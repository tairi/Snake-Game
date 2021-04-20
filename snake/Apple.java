package com.javarush.games.snake;

import com.javarush.engine.cell.*;


public class Apple extends GameObject {
    private static final String APPLE_SIGN = "\uD83C\uDF4E";
    public boolean isAlive = true;



    public Apple(int x, int y) {
        super(x, y);
    }

    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, APPLE_SIGN, Color.RED, 75);
    }
}

/*. В классе Apple должен существовать публичный void метод draw(Game).
6. В методе draw(Game) должен быть вызван метод setCellValueEx(int, int, Color, String, Color, int) у объекта типа Game с параметрами: , <цвет яблока>, 75. (<цвет яблока> используй какой тебе нравится, например, Color.GREEN).*/