package Buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MenuButtons extends Button {

    public MenuButtons(String text)
    {
        setPrefWidth(190);
        setPrefHeight(49);
        setFont(Font.font(23));
        setText(text);
        initializeButtonListeners();
    }

    private void setButtonsPressedStyle()
    {
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonsReleasedStyle()
    {
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners()
    {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                {
                    setButtonsPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                {
                    setButtonsReleasedStyle();
                }
            }
        });


        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }
}
