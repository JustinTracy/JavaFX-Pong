import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle
{
    private Scene scene;
    private AnchorPane pane;
    private AnimationTimer animationTimer;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    private static final int SPEED = 5;

    public Player(Scene scene, AnchorPane pane)
    {
        this.scene = scene;
        this.pane = pane;

        createAnimationTimer();
        addActionListeners();

        this.setFill(Color.WHITE);
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
        if (isUpKeyPressed && !isDownKeyPressed && this.getTranslateY() != -225)
        {
            this.setTranslateY(this.getTranslateY() - SPEED);
        }
        if (isDownKeyPressed && !isUpKeyPressed && this.getTranslateY() + 100 != 225)
        {
            this.setTranslateY(this.getTranslateY() + SPEED);
        }
    }

    private void addActionListeners()
    {
        scene.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.UP)
            {
                isUpKeyPressed = true;
            }
            if (e.getCode() == KeyCode.DOWN)
            {
                isDownKeyPressed = true;
            }
        });

        scene.setOnKeyReleased(e ->
        {
            if (e.getCode() == KeyCode.UP)
            {
                isUpKeyPressed = false;
            }
            if (e.getCode() == KeyCode.DOWN)
            {
                isDownKeyPressed = false;
            }
        });
    }
}
