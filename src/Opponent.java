import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Opponent extends Rectangle
{
    private AnimationTimer animationTimer;

    private Ball ball;

    private static int speed = 6;

    private boolean isThreadActive = false;

    public Opponent(Ball ball)
    {
        this.ball = ball;

        createAnimationTimer();

        this.setFill(Color.BLUE);
        this.setWidth(20);
        this.setHeight(100);
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
        if (!isThreadActive)
        {
            isThreadActive = true;
            Thread thread = new Thread(new SpeedChanger(this, isThreadActive));
            thread.start();
        }

        if (ball.getTranslateY() < this.getTranslateY() + 50)
        {
            this.setTranslateY(this.getTranslateY() - speed);
        }

        if (ball.getTranslateY() > this.getTranslateY() + 50)
        {
            this.setTranslateY(this.getTranslateY() + speed);
        }
    }

    public void setIsThreadActive(boolean running)
    {
        isThreadActive = running;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}
