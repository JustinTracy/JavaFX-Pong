package view;

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
    private static final String BUTTON_STYLE = "-fx-background-radius: 8; -fx-spacing: 20; -fx-text-fill: #e41749; " +
            "-fx-background-color: linear-gradient(#0f0c29, #302b63, #24243e); -fx-cursor: hand;";
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

    private void createButtons()
    {
        Button singlePlayerBtn = new Button("Single Player");
        singlePlayerBtn.setStyle(BUTTON_STYLE);
        singlePlayerBtn.setFont(font);
        singlePlayerBtn.setPrefSize(250, 50);
        singlePlayerBtn.setLayoutX(650);
        singlePlayerBtn.setLayoutY(175);
        singlePlayerBtn.setOnAction(e ->
        {
            menuStage.close();
            try
            {
                GameView gameView = new GameView();
            }
            catch (FileNotFoundException | InterruptedException ex)
            {
                ex.printStackTrace();
            }
        });

        Button twoPlayerBtn = new Button("Two Player");
        twoPlayerBtn.setStyle(BUTTON_STYLE);
        twoPlayerBtn.setFont(font);
        twoPlayerBtn.setPrefSize(250, 50);
        twoPlayerBtn.setLayoutX(650);
        twoPlayerBtn.setLayoutY(275);


        Button helpBtn = new Button("Help");
        helpBtn.setStyle(BUTTON_STYLE);
        helpBtn.setFont(font);
        helpBtn.setPrefSize(250, 50);
        helpBtn.setLayoutX(650);
        helpBtn.setLayoutY(375);


        Button quitBtn = new Button("Exit");
        quitBtn.setStyle(BUTTON_STYLE);
        quitBtn.setFont(font);
        quitBtn.setPrefSize(250, 50);
        quitBtn.setLayoutX(650);
        quitBtn.setLayoutY(475);
        quitBtn.setOnAction(e ->
        {
            menuStage.close();
        });

        menuPane.getChildren().addAll(singlePlayerBtn, twoPlayerBtn, helpBtn, quitBtn);
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

