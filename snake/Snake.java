package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake (int x, int y){
        GameObject snake1 = new GameObject(x,y);
        GameObject snake2 = new GameObject(x+1,y);
        GameObject snake3 = new GameObject(x+2,y);
        snakeParts.add(snake1);
        snakeParts.add(snake2);
        snakeParts.add(snake3);

    }

    public void draw(Game game){
        Color color = isAlive ? Color.AQUA : Color.RED;

        for (int i=0;i<snakeParts.size();i++) {
            GameObject part = snakeParts.get(i);
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            game.setCellValueEx(part.x, part.y, Color.NONE, sign, color,75);
        }
    }

    public void setDirection(Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT)
                && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN)
                && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }

        if (direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }
        this.direction = direction;
    } //6. В классе Snake метод setDirection(Direction) не должен изменять направление движения змейки, если параметр метода противоположен текущему направлению.


    public void move(Apple apple){
        GameObject cNewHead = createNewHead();

        if (cNewHead.x>=SnakeGame.WIDTH||cNewHead.x<0||cNewHead.y>=SnakeGame.HEIGHT||cNewHead.y<0){
            isAlive = false;
            return; //проверили на столкновение с границей поля
        }
        if (checkCollision(cNewHead)){
            isAlive = false;
            return; //проверили на самоукус
        }

        snakeParts.add(0, cNewHead);

        if ((apple.x == cNewHead.x) && (apple.y == cNewHead.y)){
            apple.isAlive = false;  //съели яблоко
        } else {
            removeTail();} //просто движение

    }

    public GameObject createNewHead() {
        GameObject oldHead = snakeParts.get(0);
        if (direction == Direction.LEFT) {
            return new GameObject(oldHead.x - 1, oldHead.y);
        } else if (direction == Direction.RIGHT) {
            return new GameObject(oldHead.x + 1, oldHead.y);
        } else if (direction == Direction.UP) {
            return new GameObject(oldHead.x, oldHead.y - 1);
        } else {
            return new GameObject(oldHead.x, oldHead.y + 1);
        }

        /*
        - если змейка движется влево, new GameObject(headX-1, headY);
        - если змейка движется вниз, new GameObject(headX, headY + 1)
        Здесь headX — координата головы змеи x, headY — координата головы змеи y.*/
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size()-1);
    }

    public boolean checkCollision(GameObject gameObject){
        for (GameObject part : snakeParts) {
            if (part.x == gameObject.x && part.y == gameObject.y) {
                return true;
            }
        }
        return false;
    }
    public int getLength() {
        int getL = snakeParts.size();
        return getL;
    }
}


