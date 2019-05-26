package view;

import game.Ball;
import game.Opponent;
import game.Player;
import game.threads.NewPoint;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import game.ui.PongSubScene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private Thread newPointThread;

    private Font font = Font.loadFont(new FileInputStream(new File("src/view/resources/custom_font.TTF")), 12);
    private Font font2 = Font.loadFont(new FileInputStream(new File("src/view/resources/custom_font.TTF")), 22);

    private PongSubScene scoreSubScene;
    private Label playerScore;
    private Label dashLbl;
    private Label opponentScore;
    private static final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #b206b0;";
    private static final String BUTTON_STYLE = "-fx-background-radius: 8; -fx-spacing: 20; -fx-text-fill: #e41749; " +
                                               "-fx-background-color: linear-gradient(#0f0c29, #302b63, #24243e); -fx-cursor: hand;";
    private static final String TITLE_STYLE = "-fx-spacing: 20; -fx-text-fill: #ff8a5c;";

    private PongSubScene endGameSubScene;
    private Button menuButton;
    private Button replayButton;
    private Label resultLabel;

    private int playerLivesLeft = 3;
    private int opponentLivesLeft = 3;
    private boolean gameDone = false;

    public GameView() throws FileNotFoundException, InterruptedException
    {
        gameStage = new Stage();
        gameStage.setTitle("Pong");
        gameStage.setMaximized(true);
        gameStage.setResizable(false);
        gamePane = new AnchorPane();
        gamePane.setStyle("-fx-background-color: BLACK;");
        gameScene = new Scene(gamePane);
        gameStage.setScene(gameScene);
        gameStage.show();
        createSubScene();
        createScoreSubScene();
        createAnimationTimer();
        createTitleLabel();
    }

    private void createAnimationTimer()
    {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                endGame();
                checkForCollision();
                checkForPoint();
            }
        };
        animationTimer.start();
    }

    private void createTitleLabel()
    {
        Label titleLabel = new Label("PONG");
        titleLabel.setStyle(TITLE_STYLE);
        titleLabel.setFont(font);
        titleLabel.setScaleX(5);
        titleLabel.setScaleY(5);
        titleLabel.setLayoutX(630);
        titleLabel.setLayoutY(40);
        gamePane.getChildren().add(titleLabel);
    }

    private void createEndGameSubScene(boolean didPlayerWin)
    {
        endGameSubScene = new PongSubScene(500, 400, 400, 125);
        endGameSubScene.getPane().setStyle("-fx-background-color: transparent;");

        resultLabel = new Label("You won!");
        if (!didPlayerWin)
        {
            resultLabel.setText("You Lost");
        }
        resultLabel.setScaleX(1.5);
        resultLabel.setScaleY(1.5);
        resultLabel.setStyle(LABEL_STYLE);
        resultLabel.setLayoutX(175);
        resultLabel.setLayoutY(100);
        resultLabel.setFont(font2);

        menuButton = new Button("Menu");
        menuButton.setOnMouseEntered(e ->
        {
            menuButton.setEffect(new DropShadow());
        });
        menuButton.setOnMouseExited(e ->
        {
            menuButton.setEffect(null);
        });
        menuButton.setOnAction(e ->
        {

        });
        menuButton.setStyle(BUTTON_STYLE);
        menuButton.setLayoutX(175);
        menuButton.setLayoutY(150);
        menuButton.setPrefSize(150, 50);
        menuButton.setFont(font2);

        replayButton = new Button("Replay");
        replayButton.setOnMouseEntered(e ->
        {
            replayButton.setEffect(new DropShadow());
        });
        replayButton.setOnMouseExited(e ->
        {
            replayButton.setEffect(null);
        });
        replayButton.setOnAction(e ->
        {
            try
            {
                newGame();
            } catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
        });
        replayButton.setStyle(BUTTON_STYLE);
        replayButton.setLayoutX(175);
        replayButton.setLayoutY(250);
        replayButton.setPrefSize(150, 50);
        replayButton.setFont(font2);

        endGameSubScene.getPane().getChildren().addAll(resultLabel, menuButton, replayButton);
        gamePane.getChildren().add(endGameSubScene);
    }

    private void endGame()
    {
        if (!gameDone)
        {
            if (playerLivesLeft == 0)
            {
                gameDone = true;
                ball.setFill(Color.BLACK);
                ball.setXSpeed(0);
                ball.setYSpeed(0);
                ball.setTranslateX(0);
                ball.setTranslateY(60);
                createEndGameSubScene(false);
            }
            if (opponentLivesLeft == 0)
            {
                gameDone = true;
                ball.setFill(Color.BLACK);
                ball.setXSpeed(0);
                ball.setYSpeed(0);
                ball.setTranslateX(0);
                ball.setTranslateY(60);
                createEndGameSubScene(true);
            }
        }
    }

    private void updateScore(boolean player)
    {
        if (player)
        {
            playerLivesLeft--;
            playerScore.setText(String.valueOf(playerLivesLeft));
        }
        else
        {
            opponentLivesLeft--;
            opponentScore.setText(String.valueOf(opponentLivesLeft));
        }
    }

    private void createScoreSubScene()
    {
        scoreSubScene = new PongSubScene(300, 100, 500, 575);
        playerScore = new Label("3");
        dashLbl = new Label("  -  ");
        opponentScore = new Label("3");

        playerScore.setScaleX(8);
        playerScore.setScaleY(8);
        playerScore.setLayoutX(50);
        playerScore.setLayoutY(40);

        dashLbl.setScaleX(8);
        dashLbl.setScaleY(8);
        dashLbl.setLayoutX(140);
        dashLbl.setLayoutY(40);

        opponentScore.setScaleX(8);
        opponentScore.setScaleY(8);
        opponentScore.setLayoutX(240);
        opponentScore.setLayoutY(40);

        playerScore.setStyle(LABEL_STYLE);
        dashLbl.setStyle(LABEL_STYLE);
        opponentScore.setStyle(LABEL_STYLE);
        playerScore.setFont(font);
        dashLbl.setFont(font);
        opponentScore.setFont(font);

        scoreSubScene.getPane().setStyle("-fx-background-color: transparent;");
        scoreSubScene.getPane().getChildren().addAll(playerScore, dashLbl, opponentScore);
        gamePane.getChildren().add(scoreSubScene);
    }

    private void checkForPoint()
    {
        if (!gameDone)
        {
            if (ball.getTranslateX() <= -600)
            {
                if (!cooldown)
                {
                    updateScore(true);
                    cooldown = true;
                    relocateBall();
                    newPointThread = new Thread(new NewPoint(ball, this));
                    newPointThread.start();
                }
            }
            if (ball.getTranslateX() >= 600)
            {
                if (!cooldown)
                {
                    updateScore(false);
                    cooldown = true;
                    relocateBall();
                    newPointThread = new Thread(new NewPoint(ball, this));
                    newPointThread.start();
                }
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
                ball.setColor(playerColor);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(8);
            }
        }
        else if (ball.getTranslateY() >= player.getTranslateY() + 66 && ball.getTranslateY() <= player.getTranslateY() + 100)
        {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540)
            {
                ball.setColor(playerColor);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(-8);
            }
        }
        else if (ball.getTranslateY() >= player.getTranslateY() + 33 && ball.getTranslateY() <= player.getTranslateY() + 66)
        {
            if (ball.getTranslateX() <= -530 && ball.getTranslateX() >= -540)
            {
                ball.setColor(playerColor);
                ball.setXSpeed(-ball.getXSpeed());
                int randNum = rnd.nextInt(2) + 1;
                if (randNum == 1)
                {
                    ball.setYSpeed(-2);
                }
                else if (randNum + 0 == 2)
                {
                    ball.setYSpeed(2);
                }
            }
        }

        if (ball.getTranslateY() >= opponent.getTranslateY() && ball.getTranslateY() <= opponent.getTranslateY() + 33)
        {
            if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515)
            {
                ball.setColor(opponentColor);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(8);
            }
        }
        else if (ball.getTranslateY() >= opponent.getTranslateY() + 66 && ball.getTranslateY() <= opponent.getTranslateY() + 100)
        {
            if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515)
            {
                ball.setColor(opponentColor);
                ball.setXSpeed(-ball.getXSpeed());
                ball.setYSpeed(-8);
            }
        }
        else if (ball.getTranslateY() >= opponent.getTranslateY() + 33 && ball.getTranslateY() <= opponent.getTranslateY() + 66)
        {
            if (ball.getTranslateX() >= 510 && ball.getTranslateX() <= 515)
            {
                ball.setColor(opponentColor);
                ball.setXSpeed(-ball.getXSpeed());
                int randNum = rnd.nextInt(2) + 1;
                if (randNum == 1)
                {
                    ball.setYSpeed(-2);
                }
                else if (randNum == 2)
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

    public void newGame() throws FileNotFoundException
    {
        gameDone = false;
        playerLivesLeft = 3;
        opponentLivesLeft = 3;
        playerScore.setText("3");
        opponentScore.setText("3");
        gamePane.getChildren().remove(endGameSubScene);
    }

    public void setCooldown(boolean cooldown)
    {
        this.cooldown = cooldown;
    }
}
