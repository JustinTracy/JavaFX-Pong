import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuView
{
    private Stage menuStage;
    private AnchorPane menuPane;
    private Scene menuScene;

    public MenuView()
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
    }
}
