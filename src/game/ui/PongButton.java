package game.ui;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PongButton extends Button
{
    private static final String MENU = "-fx-border-width: 2px; -fx-text-fill: white; -fx-border-color: #145374; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    private static final String MENU_HOVER = "-fx-border-width: 2px; -fx-text-fill: white; -fx-border-color: #00bd56; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    private static final String GAME = "-fx-border-width: 2px; -fx-text-fill: black; -fx-border-color: #145374; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    private static final String GAME_HOVER = "-fx-border-width: 2px; -fx-text-fill: black; -fx-border-color: #ff502f; -fx-border-style: solid;" +
            "-fx-border-radius: 24px; -fx-background-color: transparent; -fx-cursor: hand;";

    private Font font;
    private boolean isMenu;

    public PongButton(String text, int x, int y, boolean isMenu) throws FileNotFoundException
    {
        this.isMenu = isMenu;
        this.setText(text);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setPrefSize(350, 50);
        loadFont();
        this.setFont(font);
        addListeners();

        if (isMenu)
        {
            this.setStyle(MENU);
        }
        else
        {
            this.setStyle(GAME);
        }
    }

    private void loadFont() throws FileNotFoundException
    {
        font = Font.loadFont(new FileInputStream(new File("src/view/resources/custom_font.TTF")), 22);
    }

    private void addListeners()
    {
        this.setOnMouseEntered(e ->
        {
            if (isMenu)
            {
                this.setStyle(MENU_HOVER);
            }
            else
            {
                this.setStyle(GAME_HOVER);
            }
        });

        this.setOnMouseExited(e ->
        {
            if (isMenu)
            {
                this.setStyle(MENU);
            }
            else
            {
                this.setStyle(GAME);
            }
        });
    }
}
