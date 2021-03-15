import processing.core.*;
import java.util.ArrayList;

public class SnakeGame extends PApplet {

    ArrayList<Integer> x = new ArrayList<Integer>(), y = new ArrayList<Integer>();
    int w = 30, h = 30, blocks = 20;
    int direction = 2;
    int[] xDirection = {0, 0, 1, -1}, yDirection = {1, -1, 0, 0};
    int foodX = 15, foodY = 15;
    int foodColor1 = 255, foodColor2 = 255, foodColor3 = 255;
    int speed = 10;
    boolean gameOver = false;

    public static void main(String[] args) {
        PApplet.main("SnakeGame");
    }

    public void settings() {
        size(600, 600);
        x.add(0);
        y.add(15);
    }

    public void keyPressed() {
        int newDirection = -1;
        if(keyCode == DOWN) {
            newDirection = 0;
        }
        else if (keyCode == UP) {
            newDirection = 1;
        }
        else if (keyCode == RIGHT) {
            newDirection = 2;
        }
        else if (keyCode == LEFT) {
            newDirection = 3;
        }
        if (newDirection != -1) {
            direction = newDirection;
        }
    }

    public void draw() {
        background(61, 56, 84);
        fill(56, 168, 50);
        for (int i = 0; i < width/blocks; i++) {
            line((i + 1) * blocks, height, (i + 1) * blocks, 0);
        }
        for (int i = 0; i < height / blocks; i++) {
            line(0, (i + 1) * blocks, width, (i + 1) * blocks);
        }
        for (int i = 0; i < x.size(); i++) {
            rect(x.get(i)*blocks, y.get(i)*blocks, blocks, blocks);
        }
        if(!gameOver) {
            fill(foodColor1, foodColor2, foodColor3);
            ellipse(foodX*blocks + 10, foodY*blocks + 10, blocks, blocks);
            textAlign(LEFT);
            textSize(25);
            fill(255);
            text("Score: " + x.size(), 10, 10, width - 20, 50);
        }
        else {
            fill(219, 186, 18);
            textSize(30);
            textAlign(CENTER);
            text("GAME OVER \n Your Score is: " + x.size() + "\n Press Enter to try again!", width/2, height/2);
            if(keyCode == ENTER) {
                x.clear();
                y.clear();
                x.add(0);
                y.add(15);
                direction = 2;
                speed = 10;
                gameOver = false;
            }
        }
        if(frameCount % speed == 0) {
            x.add(0, x.get(0) + xDirection[direction]);
            y.add(0, y.get(0) + yDirection[direction]);
            if(x.get(0) < 0 || y.get(0) < 0 || x.get(0) >= w || y.get(0) >= h) {
                gameOver = true;
            }
            for (int i = 1; i < x.size(); i++) {
                if (x.get(0) == x.get(i) && y.get(0) == y.get(i)) {
                    gameOver = true;
                }
            }
            if (x.get(0) == foodX && y.get(0) == foodY) {
                if (x.size() % 5 == 0 && speed >= 2) {
                    speed--;
                }
                foodX = (int) random(0, w);
                foodY = (int) random(0, h);
                foodColor1 = (int) random(255);
                foodColor2 = (int) random(255);
                foodColor3 = (int) random(255);
            }
            else {
                x.remove(x.size() - 1);
                y.remove(y.size() - 1);
            }
        }
    }
}