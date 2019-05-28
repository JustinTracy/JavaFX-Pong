package game.threads;

import game.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class MultiPlayerMove implements Runnable
{
    private Scene scene;
    private boolean player1;

    private AnimationTimer animationTimer;

    private static final int SPEED = 5;
    private boolean isWKeyPressed;
    private boolean isSKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private Player player;
    private Player player2;

    public MultiPlayerMove(Scene scene, Player player, Player player2, boolean player1)
    {
        this.player2 = player2;
        this.player = player;
        this.scene = scene;
        this.player1 = player1;
        createAnimationTimer();
        if (player1)
        {
            addPlayer1Listeners();

        }
//        if (!player1)
//        {
//            addPlayer2Listeners();
//        }
    }

    @Override
    public void run()
    {

    }

    private void createAnimationTimer()
    {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                movePlayer();
            }
        };
        animationTimer.start();
    }

    private void movePlayer()
    {
        if (isUpKeyPressed && !isDownKeyPressed && isWKeyPressed && !isSKeyPressed)
        {
            player.setTranslateY(player.getTranslateY() - SPEED);
            player2.setTranslateY(player2.getTranslateY() - SPEED);
        }
        if (!isUpKeyPressed && isDownKeyPressed && !isWKeyPressed && isSKeyPressed)
        {
            player.setTranslateY(player.getTranslateY() + SPEED);
            player2.setTranslateY(player2.getTranslateY() + SPEED);
        }
        if (isUpKeyPressed && !isDownKeyPressed && !isWKeyPressed && isSKeyPressed)
        {
            player.setTranslateY(player.getTranslateY() - SPEED);
            player2.setTranslateY(player2.getTranslateY() + SPEED);
        }
        if (!isUpKeyPressed && isDownKeyPressed && isWKeyPressed && !isSKeyPressed)
        {
            player.setTranslateY(player.getTranslateY() + SPEED);
            player2.setTranslateY(player2.getTranslateY() - SPEED);
        }
    }

    private void addPlayer1Listeners()
    {
        scene.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.W)
            {
                isWKeyPressed = true;
            }
            if (e.getCode() == KeyCode.S)
            {
                isSKeyPressed = true;
            }
        });

        scene.setOnKeyReleased(e ->
        {
            if (e.getCode() == KeyCode.W)
            {
                isWKeyPressed = false;
            }
            if (e.getCode() == KeyCode.S)
            {
                isSKeyPressed = false;
            }
        });
    }
}
