import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ball extends Rectangle
{
    private Random rnd = new Random();

    private AnimationTimer animationTimer;

    private static final int SPEED = 5;

    public Ball()
    {
        this.setFill(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        this.setWidth(50);
        this.setHeight(50);
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
        
    }

    public void changeColor()
    {
        this.setFill(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
    }
}
