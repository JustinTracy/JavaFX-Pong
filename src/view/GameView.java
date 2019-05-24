package view;

import game.Ball;
import game.Opponent;
import game.Player;
import game.threads.NewPoint;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.ui.PongSubScene;

import java.util.Random;

public class GameView
{
    private Stage gameStage;
    private AnchorPane gamePane;
    private Scene gameScene;

    private PongSubScene pongSubScene;
    private Player player;
    private Opponent opponent;
    private Ball ball;

    private AnimationTimer animationTimer;

    private Color playerColor = Color.rgb(178,34,34);
    private Color opponentColor = Color.rgb(50,122,178);

    private boolean cooldown = false;
    private Random rnd = new Random();

    // When the player hits the ball as they are going up or down, it will change the y speed of the ball
    // Make it so that every time that the ball bounces off the ceiling or ground, the x and y speeds with revert to default

    public GameView()
    {
        gameStage = new Stage();
        gameStage.setTitle("Pong");
        gameStage.setMaximized(true);
        gameStage.setResizable(true);
        gamePane = new AnchorPane();
        gamePane.setStyle("-fx-background-color: BLACK;");
        gameScene = new Scene(gamePane);
        gameStage.setScene(gameScene);
        gameStage.show();

        createSubScene();
        createAnimationTimer();
    }

    private void createAnimationTimer()
    {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                checkForCollision();
                checkForPoint();
            }
        };
        animationTimer.start();
    }

    private void checkForPoint()
    {
        if (ball.getTranslateX() <= -600)
        {
            if (!cooldown)
            {
                cooldown = true;
                relocateBall();
                Thread thread = new Thread(new NewPoint(ball, this));
                thread.start();
            }
        }
        if (ball.getTranslateX() >= 600)
        {
            if (!cooldown)
            {
                cooldown = true;
                relocateBall();
                Thread thread = new Thread(new NewPoint(ball, this));
                thread.start();
            }
        }
    }

    private void relocateBall()
    {
        ball.setFill(Color.BLACK);
        ball.setXSpeed(0);
        ball.setYSpeed(0);
        ball.setTranslateX(0);
        ball.setTranslateY(0);
    }

    private void checkForCollision()
    {
        if (ball.getTranslateY() >= player.getTranslateY() && ball.getTranslateY() <= player.getTranslateY() + 33)
        {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540)
            {
                ball.setColor(Color.RED);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(8);
            }
        }
        else if (ball.getTranslateY() >= player.getTranslateY() + 66 && ball.getTranslateY() <= player.getTranslateY() + 100)
        {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540)
            {
                ball.setColor(Color.RED);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(-8);
            }
        }
        else if (ball.getTranslateY() >= player.getTranslateY() + 33 && ball.getTranslateY() <= player.getTranslateY() + 66)
        {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540)
            {
                ball.setColor(Color.RED);
                ball.setXSpeed(-ball.getXSpeed());
                if (rnd.nextInt(1) == 0)
                {
                    ball.setYSpeed(-2);
                }
                else
                {
                    ball.setYSpeed(2);
                }
            }
        }

        if (ball.getTranslateY() >= opponent.getTranslateY() && ball.getTranslateY() <= opponent.getTranslateY() + 33)
        {
            if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515)
            {
                ball.setColor(Color.RED);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(8);
            }
        }
        else if (ball.getTranslateY() >= opponent.getTranslateY() + 66 && ball.getTranslateY() <= opponent.getTranslateY() + 100)
        {
            if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515)
            {
                ball.setColor(Color.RED);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(-8);
            }
        }
        else if (ball.getTranslateY() >= opponent.getTranslateY() + 33 && ball.getTranslateY() <= opponent.getTranslateY() + 66)
        {
            if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515)
            {
                ball.setColor(Color.RED);
                ball.setXSpeed(-ball.getXSpeed());
                if (rnd.nextInt(1) == 0)
                {
                    ball.setYSpeed(-2);
                }
                else
                {
                    ball.setYSpeed(2);
                }
            }
        }
    }

    private void createSubScene()
    {
        pongSubScene = new PongSubScene(1150, 450, 100, 100);

        player = new Player(gameScene, gamePane);
        player.setLayoutX(40);
        player.setLayoutY(225);

        ball = new Ball(pongSubScene);
        ball.setLayoutX(575);
        ball.setLayoutY(225);

        opponent = new Opponent(ball);
        opponent.setLayoutX(1090);
        opponent.setLayoutY(225);

        pongSubScene.getPane().getChildren().addAll(ball, player, opponent);
        gamePane.getChildren().add(pongSubScene);
    }

    public void setCooldown(boolean cooldown)
    {
        this.cooldown = cooldown;
    }
}
