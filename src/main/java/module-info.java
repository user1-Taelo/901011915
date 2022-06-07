module com.example.taelogame {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.taelogame to javafx.fxml;
    exports com.example.taelogame;
}