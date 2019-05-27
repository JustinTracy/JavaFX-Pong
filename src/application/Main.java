package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MenuView;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MenuView menuView = new MenuView();
        primaryStage = menuView.getMainStage();
        primaryStage.setTitle("SpaceRunner");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
