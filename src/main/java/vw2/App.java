package vw2;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PFont;

public class App extends PApplet {

    // Required to pass the tests
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        String[] appArgs = {"Basketball"};
        App mySketch = new App();
        PApplet.runSketch(appArgs, mySketch);
    }

    int basketballOrange = color(250, 131, 32);

    int xSize = 800;
    int ySize = 450;
    int ballsize = 40;
    float x, y;

    float mass = 0.567f;
    float gravity;
    float velocity = 0;

    int points = 0;
    int lifes = 3;
    PFont myFont;

    boolean gameOver = false;

    public void settings() {
        size(xSize, ySize);
    }

    public void setup() {
        //second law of Newton: F=m*a
        gravity = mass + 9.81f;
        myFont = createFont("Arial", 32);
        background(color(0,255,255));
        x = random(0+20, 400-20);
        y = 0-20;
        paintNewBall();
    }

    public void paintNewBoard(){
        fill(color(255,255,200));
        rect(mouseX - ballsize*1.5f, ySize - 50 - ballsize*1.5f, ballsize * 3, ballsize*2, 7);
        strokeWeight(2);
        rect(mouseX - ballsize*0.75f, ySize - 50 - ballsize, ballsize * 1.5f, ballsize, 5);
        strokeWeight(1);
    }

    public void paintNewBall() {
        fill(basketballOrange);
        ellipse(x, y, ballsize, ballsize);
        //noFill();
        noFill();
        stroke(0);
        arc(x, y, ballsize, ballsize/2, -PI, 4);
    }
    
    public void paintNewHoop() {
        fill(color(255,0,0));
        rect(mouseX - ballsize * 1.5f / 2, ySize - 50, ballsize * 1.5f, 6, 7);
        noFill();
        stroke(color(225,225,225));
        strokeWeight(3);
        beginShape(TRIANGLE_STRIP);
        vertex(mouseX - ballsize * 1.5f / 2, ySize - 42);
        vertex(mouseX - ballsize * 1.5f / 4, ySize);
        vertex(mouseX, ySize - 42);
        vertex(mouseX + ballsize * 1.5f / 4, ySize);
        vertex(mouseX + ballsize * 1.5f / 2, ySize - 42);
        endShape();
        strokeWeight(1);
    }

    public void draw() {
        if(!gameOver){
            background(color(0,255,255));
            velocity += gravity * 0.01; //scale 100:1
            y += velocity;
            //y=100f;
            paintNewBoard();
            paintNewHoop();
            paintNewBall();
            if(y > ySize + ballsize / 2){
                if(x > mouseX - ballsize * 1.5f / 2 && x < mouseX + ballsize * 1.5f){
                    points += 100;
                }
                else{
                    lifes -= 1;
                }
                y = 0 - ballsize / 2;
                x = random(0 + ballsize / 2, xSize - ballsize / 2);
                velocity = 0;
            }
            //Show Points on screen top left
            fill(color(0,0,0));
            rect(0, 0, 60, 20);
            fill(color(255,0,0));
            text(points, 10, 15);

            //show lifes on screen top right
            fill(color(0,0,0));
            rect(xSize-60, 0, xSize, 20);
            fill(color(255,0,0));
            text(lifes, xSize-15, 15);
            gravity += gravity * 0.001; //increase difficulty
            if(lifes == 0){
                gameOver = true;
                background(color(0,0,0));
                fill(color(255,0,0));
                textFont(myFont);
                textAlign(CENTER, CENTER);
                text("GAME OVER!", xSize/2, ySize/2);
                text("Final Score:", xSize/2, ySize/2+32);
                text(points, xSize/2, ySize/2+64);
            }
        }             
    }
}