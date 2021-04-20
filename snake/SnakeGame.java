package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class SnakeGame extends Game implements Serializable {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;


    @Override
    public void initialize() { //рисуем поле
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void drawScene(){    //нынешняя сцена
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKSEAGREEN,"");  //очистили поле от лишнего
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    private void createNewApple() {
        Apple newApple;
        do {
            int xAp = getRandomNumber(WIDTH);
            int yAp = getRandomNumber(HEIGHT);
            newApple = new Apple(xAp, yAp);
        }  while (snake.checkCollision(newApple));

        apple = newApple;
    }

    @Override
    public void onTurn(int step) {  //устанавливаем движение змейки
        snake.move(apple);
        if (!apple.isAlive) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (snake.getLength()>GOAL){
            win();
        }
        drawScene();
    }

   /* private boolean pause = true;*/

    @Override
    public void onKeyPress(Key key) {  //ПАУЗА НЕ РАБОТАЕТ!! Ошибки не выводит
       /* if (key == Key.SPACE && !isGameStopped && pause==true) { //теоретически - это пауза с сохранением в фаил, поискать сохранение во временную память
            pause = false;
            SavedGame savedGame = new SavedGame(snake,turnDelay,apple, isGameStopped,  score);

            try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Username\\Desktop\\save.ser");
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
                // сохраняем игру в файл
                objectOutputStream.writeObject(savedGame);
            }
            catch (IOException e) {}
            //закрываем поток и освобождаем ресурсы

        } else
        if (key == Key.SPACE && !isGameStopped && pause==false) { //теоретически отмена паузы
            try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Username\\Desktop\\save.ser");
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                SavedGame savedGame = (SavedGame) objectInputStream.readObject();
                System.out.println(savedGame);
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        } else*/
        if (key == Key.SPACE && isGameStopped) {
            createGame();
        }

        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
    }
    
    private void createGame(){ //cтартовые условия игры
        snake = new Snake(WIDTH/2, HEIGHT/2); //рисуем змейку
        createNewApple(); //рисуем яблоко
        isGameStopped = false; //флаг на остановку игры
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        score = 0;
        setScore(score);

    }

    private void gameOver (){
        stopTurnTimer();
        isGameStopped = true;

      showMessageDialog(Color.BLACK, "Game over!", Color.RED, 50);
  /* String tablRes = String.format("%s%n%s%n%d%n%d%n%d%n%d%n%d%n%d%n","Game over!", "Таблица резельтатов", Rekords().get(0), Rekords().get(1), Rekords().get(2), Rekords().get(3), Rekords().get(4), Rekords().get(5),  Rekords().get(6));
        showMessageDialog(Color.BLACK, tablRes, Color.GREEN, 50);     */
    }


    private void win() {
        stopTurnTimer();
        isGameStopped = true;

        showMessageDialog(Color.BLACK, "YOU WIN", Color.GREEN, 50);
        //добавить нажатие клавиши для смены окна -???
/*        String tablRes = String.format("%s%n%d%n%d%n%d%n%d%n%d%n%d%n","Таблица резельтатов", Rekords().get(0), Rekords().get(1), Rekords().get(2), Rekords().get(3), Rekords().get(4), Rekords().get(5),  Rekords().get(6));
        showMessageDialog(Color.BLACK, tablRes, Color.GREEN, 50);*/
//String s1 = String.format("%s%n%s", "Ля-ля-ля ля-ля-ля", "Ля-ля-ля ля-ля-ля");

    }

   /* private TreeSet<Integer> Rekords() {   //создать таблицу рекордов - новое окно
        TreeSet<Integer> tabl = new TreeSet<>();
        tabl.add(score);
        return tabl;
    }*/


/* добавить нормальное закрытие игры в оконном режиме*/
}



