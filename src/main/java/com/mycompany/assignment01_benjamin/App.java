package com.mycompany.assignment01_benjamin;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    //sample texts
    private final String[] sampleTexts = {
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };
    
    //keyboard keys
    private final Map<KeyCode, Button> keyBtnMap = new HashMap<>();
     
    // first row of keys
    private final Object[][] keys1 = {
        {"`", KeyCode.BACK_QUOTE}, {"1", KeyCode.DIGIT1}, {"2", KeyCode.DIGIT2}, {"3", KeyCode.DIGIT3},
        {"4", KeyCode.DIGIT4}, {"5", KeyCode.DIGIT5}, {"6", KeyCode.DIGIT6}, {"7", KeyCode.DIGIT7},
        {"8", KeyCode.DIGIT8}, {"9", KeyCode.DIGIT9}, {"0", KeyCode.DIGIT0}, {"-", KeyCode.MINUS},
        {"=", KeyCode.PLUS},{"DELETE", KeyCode.BACK_SPACE}
    };
    
    // second row of keys
    private final Object[][] keys2 = {
        {"Q", KeyCode.Q}, {"W", KeyCode.W}, {"E", KeyCode.E}, {"R", KeyCode.R},
        {"T", KeyCode.T}, {"Y", KeyCode.Y}, {"U", KeyCode.U}, {"I", KeyCode.I},
        {"O", KeyCode.O}, {"P", KeyCode.P}, {"[", KeyCode.OPEN_BRACKET}, {"]", KeyCode.CLOSE_BRACKET},
        {"\\", KeyCode.BACK_SLASH}
    };    
    // third row of keys
    private final Object[][] keys3 = {
        {"A", KeyCode.A}, {"S", KeyCode.S}, {"D", KeyCode.D}, {"F", KeyCode.F},
        {"G", KeyCode.G}, {"H", KeyCode.H}, {"J", KeyCode.J}, {"K", KeyCode.K},
        {"L", KeyCode.L}, {";", KeyCode.SEMICOLON}, {"'", KeyCode.QUOTE}, {"ENTER", KeyCode.ENTER},
    };
    
    // fourth row of keys
    private final Object[][] keys4 = {
        {"SHIFT", KeyCode.SHIFT}, {"Z", KeyCode.Z}, {"X", KeyCode.X}, {"C", KeyCode.C},
        {"V", KeyCode.V}, {"B", KeyCode.B}, {"N", KeyCode.N}, {"M", KeyCode.M},
        {",", KeyCode.COMMA}, {".", KeyCode.PERIOD}, {"/", KeyCode.SLASH}
    };
    
    private final String normalKeyStyle = "-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-border-color: #0a0a0a; -fx-border-radius: 4; -fx-background-radius: 4;";
    private final String pressedKeyStyle = "-fx-background-color: #474c4f; -fx-text-fill: white; -fx-border-color: #0a0a0a; -fx-border-radius: 4; -fx-background-radius: 4;";
    
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #c3c2f0");

        // top bar
        HBox topBar = topBar();

        // text area (where the player will see and type the sentence)
        VBox textArea = textArea();

        // keyboard
        VBox keyboard = virtualKeyboard();

        //scene
        root.getChildren().addAll(topBar, textArea, keyboard);

        Scene scene = new Scene(root, 800, 700);

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
    }
    
    private HBox topBar() {
        
    }
    
    private VBox textArea() {
        
    }
    
    private VBox virtualKeyboard() {
        
    }
    
    

    public static void main(String[] args) {
        launch();
    }

}