package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class GameViewManager {

    //declaration of global variables
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;
    String path1 = getClass().getResource("/playerShip2_blue.png").toExternalForm();


    ImageView [] clouds1;
    ImageView [] clouds2;
    Random randomPositionGenerator;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private int angle;

    ImageView ship;
    private AnimationTimer gameTimer;



    private GridPane gridPane1;
    private GridPane gridPane2;

    //GameView constructor
    public GameViewManager()
    {
        randomPositionGenerator = new Random();
        initializeStage();
        createKeyListeners();
    }

    //creating clouds
    private void createGameElements()
    {
        String path5 = getClass().getResource("/cld.png").toExternalForm();
        clouds1 = new ImageView[4];

        for(int i = 0; i < clouds1.length;i++)
        {
            clouds1[i] = new ImageView(new Image(path5));
            setNewPosition(clouds1[i]);
            gamePane.getChildren().add(clouds1[i]);
        }

        clouds2 = new ImageView[4];
        for(int j = 0; j < clouds2.length;j++)
        {
            clouds2[j] = new ImageView(new Image(path5));
            setNewPosition(clouds2[j]);
            gamePane.getChildren().add(clouds2[j]);
        }
    }

    private void moveElements()
    {
        for(int i = 0; i < clouds1.length;i++)
        {
            clouds1[i].setLayoutX(clouds1[i].getLayoutX() - 7);
        }

        for(int j = 0; j < clouds2.length;j++)
        {
            clouds2[j].setLayoutX(clouds2[j].getLayoutX() - 7);
        }
    }

    private void checkElementsPosition()
    {
        for(int i = 0; i< clouds1.length;i++)
        {
            if(clouds1[i].getLayoutX()>600)
            {
                setNewPosition(clouds1[i]);
            }
        }

        for(int j = 0; j < clouds2.length;j++)
        {
            if(clouds2[j].getLayoutX()>600)
            {
                setNewPosition(clouds2[j]);
            }
        }
    }

    private void setNewPosition(ImageView image)
    {
        image.setLayoutX(randomPositionGenerator.nextInt(370));
        image.setLayoutX((randomPositionGenerator.nextInt(3200) + 600));
    }

    //creating key Listeners for the ship's movement
    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode() == KeyCode.LEFT)
                {
                    isLeftKeyPressed = true;
                }else if(keyEvent.getCode() == KeyCode.UP){
                    isUpKeyPressed = true;
                }else if(keyEvent.getCode() == KeyCode.RIGHT)
                {
                    isRightKeyPressed = true;
                }else if(keyEvent.getCode() == KeyCode.DOWN)
                {
                    isDownKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode() == KeyCode.LEFT)
                {
                    isLeftKeyPressed = false;
                }else if(keyEvent.getCode() == KeyCode.UP){
                    isUpKeyPressed = false;
                }else if(keyEvent.getCode() == KeyCode.RIGHT)
                {
                    isRightKeyPressed = false;
                }else if(keyEvent.getCode() == KeyCode.DOWN)
                {
                    isDownKeyPressed = false;
                }

            }
        });
    }

    //Here we create a new Window when play button is clicked
    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,800,600);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    //hiding the menu then displaying the new window
    public void createNewGame(Stage menuStage)
    {

        this.menuStage = menuStage;
        this.menuStage.hide();
        createMovingBackground();
        createGameElements();
        createGameLoop();
        gameStage.show();
    }

    private void createGameLoop()
    {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveShip();
                moveElements();
                checkElementsPosition();
                moveBackground();
            }
        };
        gameTimer.start();
    }

    private void moveShip()
    {
        if(isLeftKeyPressed && !isRightKeyPressed && !isDownKeyPressed && !isUpKeyPressed)
        {

            if(ship.getLayoutX() > 20)
            {
                ship.setLayoutX(ship.getLayoutX() - 5);
            }
        }
        if(isRightKeyPressed && !isLeftKeyPressed && !isDownKeyPressed && !isUpKeyPressed)
        {

            if(ship.getLayoutX() <  700)
            {
                ship.setLayoutX(ship.getLayoutX() + 5);
            }
        }
        if(isDownKeyPressed  && !isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed)
        {
            ship.setLayoutY(ship.getLayoutY() + 5);
        }
        if(isUpKeyPressed && !isRightKeyPressed && !isLeftKeyPressed && !isDownKeyPressed)
        {

            ship.setLayoutY(ship.getLayoutY() - 5);
        }
        if(isUpKeyPressed && isRightKeyPressed && isLeftKeyPressed && isDownKeyPressed)
        {
            if(angle < 0)
            {
                angle += 5;
            }else if( angle > 0){
                angle -= 5;
            }

            ship.setRotate(angle);
        }
        if(!isUpKeyPressed && !isRightKeyPressed && !isLeftKeyPressed && !isDownKeyPressed)
        {
                if(angle < 0)
                {
                    angle += 5;
                }else if( angle > 0){
                    angle -= 5;
                }

                ship.setRotate(angle);
        }

    }

    //creating a moving background to make an illusion of a moving plane
    private void createMovingBackground()
    {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        ship = new ImageView(new Image(path1));
        ship.setLayoutX(10);
        ship.setLayoutY(200);

        for(int i = 0; i< 24; i++) {
            String backgroundImage = getClass().getResource("/purple.png").toExternalForm();
            ImageView backgroundImage1 = new ImageView(new Image(backgroundImage));
            ImageView backgroundImage2 = new ImageView(new Image(backgroundImage));

            GridPane.setConstraints(backgroundImage1,i % 3, i/3);
            GridPane.setConstraints(backgroundImage2,i % 3, i/3);

            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
        gridPane2.setLayoutX(700);

        gamePane.getChildren().addAll(gridPane1,gridPane2,ship);
    }

    private void moveBackground()
    {
        gridPane1.setLayoutX(gridPane1.getLayoutX() - 0.05);
        gridPane2.setLayoutX(gridPane2.getLayoutX() - 0.05);

        if(gridPane1.getLayoutX() >= 800)
        {
            gridPane2.setLayoutX(-800);
        }
        if(gridPane2.getLayoutX() >= 800)
        {
            gridPane2.setLayoutX(-800);
        }
    }


}
