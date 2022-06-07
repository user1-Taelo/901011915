package view;

import Buttons.MenuButtons;
import Buttons.SubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ViewManager {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    //default setting of our buttons
    private final static int menuButtons_start_X = 100;
    private final static int menuButtons_start_Y = 150;
    private final static int Width = 1024;
    private final static int Height = 768;

    private SubScene scoreSubscene;

    List<MenuButtons>menuButtons;
    public ViewManager()
    {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,Width,Height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubscenes();
        createButtons();
        createBackground();


    }

    private void createSubscenes()
    {
        scoreSubscene = new SubScene();
        mainPane.getChildren().add(scoreSubscene);
    }

    //method to return mainStage

    public Stage getMainStage() {
        return mainStage;
    }

    //creating buttons
    private void createButtons()
    {
        createPlay();
        createPause();
        createScore();
        createExit();
    }

    private  void createPlay()
    {
        MenuButtons playButton = new MenuButtons("PLAY");
        addMenuButtons(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewManager gameViewManager = new GameViewManager();
                gameViewManager.createNewGame(mainStage);
            }
        });
    }
    private  void createScore()
    {
        MenuButtons scoreButton = new MenuButtons("SCORE");
        addMenuButtons(scoreButton);

        //connecting the subscene with the corresponding button
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               scoreSubscene.moveSubScene();
            }
        });
    }
    private  void createPause()
    {
        MenuButtons pauseButton = new MenuButtons("PAUSE");
        addMenuButtons(pauseButton);
    }
    private  void createExit()
    {
        MenuButtons exitButton = new MenuButtons("EXIT");
        addMenuButtons(exitButton);
    }

    //creating background
    private void createBackground()
    {
        String backgroundImage = getClass().getResource("/purple.png").toExternalForm();
        Image bgImg = new Image(backgroundImage,256,256,false,true);
        BackgroundImage background = new BackgroundImage(bgImg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
    }

    //adding menu buttons and spacing them
    private void addMenuButtons(MenuButtons button)
    {
        button.setLayoutX(menuButtons_start_X);
        button.setLayoutY(menuButtons_start_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

}
