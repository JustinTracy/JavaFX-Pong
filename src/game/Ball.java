package game;

import game.ui.PongSubScene;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ball extends Rectangle
{
    private Random rnd = new Random();
    private AnimationTimer animationTimer;
    private PongSubScene pongSubScene;

    private static final int SIZE = 20;
    private static int xSpeed = -10;
    private static int ySpeed = 8;

    public Ball(PongSubScene pongSubScene)
    {
        this.pongSubScene = pongSubScene;
        this.setFill(Color.BLACK);
        this.setWidth(SIZE);
        this.setHeight(SIZE);

        createAnimationTimer();
    }

    private void createAnimationTimer()
    {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                move();
            }
        };
        animationTimer.start();
    }

    private void move()
    {
        if (this.getTranslateY() <= -230)
        {
            ySpeed = -ySpeed;
        }
        if (this.getTranslateY() >= 210)
        {
            ySpeed = -ySpeed;
        }

        this.setTranslateY(this.getTranslateY() - ySpeed);
        this.setTranslateX(this.getTranslateX() - xSpeed);
    }

    public void setXSpeed(int speed)
    {
        xSpeed = speed;
    }

    public void setYSpeed(int speed)
    {
        ySpeed = speed;
    }

    public int getXSpeed()
    {
        return xSpeed;
    }

    public void setColor(Color color)
    {
        this.setFill(color);
    }
}
