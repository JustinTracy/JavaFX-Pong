package game.ui;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

public class PongSubScene extends SubScene
{
    public PongSubScene(int width, int height, int x, int y)
    {
        super(new AnchorPane(), width, height);
        this.prefWidth(width);
        this.prefHeight(height);
        this.setLayoutX(x);
        this.setLayoutY(y);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setStyle("-fx-background-color: #beeef7;");
    }

    public AnchorPane getPane()
    {
        return (AnchorPane) this.getRoot();
    }
}
