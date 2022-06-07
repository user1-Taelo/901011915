package Buttons;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SubScene extends javafx.scene.SubScene {
    //global variables
    String path = getClass().getResource("/purple.png").toExternalForm();

    private boolean isHidden;

    //creating a sub scene

    public SubScene() {
        super(new AnchorPane(),600,400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(path,600,400,false,true), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(180);
    }

    //create a subScene to correspond with the button clicked
    public void moveSubScene()
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);//this method decides which method should be moved

        if(isHidden) {
            transition.setToX(-676);
            isHidden = false;
        }else {
            transition.setToX(0);
            isHidden = true;
        }
        //calling the transition
        transition.play();
    }

}
