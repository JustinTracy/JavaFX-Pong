import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameView
{
    private Stage gameStage;
    private AnchorPane gamePane;
    private Scene gameScene;

    private PongSubScene pongSubScene;
    private Ball ball;

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
    }

    private void createSubScene()
    {
        pongSubScene = new PongSubScene(1150, 450, 100, 100);
        ball = new Ball(pongSubScene);
        ball.setLayoutX(575);
        ball.setLayoutY(225);

        pongSubScene.getPane().getChildren().add(ball);
        gamePane.getChildren().add(pongSubScene);
    }
}
