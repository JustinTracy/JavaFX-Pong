package view;

import game.ui.PongButton;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuView
{
    private Stage menuStage;
    private AnchorPane menuPane;
    private Scene menuScene;

    private AnimationTimer animationTimer;

    private Font font;
    private static final String LABEL_STYLE = "-fx-spacing: 20; -fx-text-fill: #b206b0;";
    private static final String TITLE_STYLE = "-fx-spacing: 20; -fx-text-fill: #ff8a5c;";

    public MenuView() throws FileNotFoundException
    {
        menuStage = new Stage();
        menuStage.setTitle("Pong");
        menuStage.setMaximized(true);
        menuStage.setResizable(false);
        menuPane = new AnchorPane();
        menuPane.setStyle("-fx-background-color: BLACK;");
        menuScene = new Scene(menuPane);
        menuStage.setScene(menuScene);
        menuStage.show();

        loadFont();
        createTitle();
        createButtons();
    }

    private void createButtons() throws FileNotFoundException
    {
        PongButton singlePlayerButton = new PongButton("Single Player", 650, 175, true);
        singlePlayerButton.setOnAction(e ->
        {
            menuStage.close();
            try
            {
                GameView gameView = new GameView();
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        });

        PongButton twoPlayerButton = new PongButton("Two Player", 650, 275, true);

        PongButton helpButton = new PongButton("Help", 650, 375, true);

        PongButton exitButton = new PongButton("Exit", 650, 475, true);
        exitButton.setOnAction(e -> menuStage.close());

        menuPane.getChildren().addAll(singlePlayerButton, twoPlayerButton, helpButton, exitButton);
    }

    private void createTitle()
    {
        Label titleLbl = new Label("PONG");
        titleLbl.setFont(font);
        titleLbl.setStyle(TITLE_STYLE);
        titleLbl.setScaleX(5);
        titleLbl.setScaleY(5);
        titleLbl.setLayoutX(225);
        titleLbl.setLayoutY(375);
        menuPane.getChildren().add(titleLbl);
    }

    private void loadFont() throws FileNotFoundException
    {
        font = Font.loadFont(new FileInputStream(new File("src/view/resources/custom_font.TTF")), 22);
    }
}

