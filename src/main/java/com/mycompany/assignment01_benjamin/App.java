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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private final Map<KeyCode, Button> keyBtnMap = new HashMap<>();
     
    // first row of keys
    private final Object[][] keys1 = {
        {"`", KeyCode.bac}, {"1", KeyCode.DIGIT1}, {"2", KeyCode.DIGIT2}, {"3", KeyCode.DIGIT3},
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
    
    
    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        
                
        //fifth row of keys
        Button spaceBtn = new Button(" ");
        
        //text field (what to type)
        TextField sentence = new TextField();
        
        //text field (where the user types)
        TextField userSentence = new TextField();
        
        //error
        
        
        //next Button + page 1 of 6
        Button nextBtn = new Button("NEXT");
        Label page = new Label("1 of 6");
        
        //reset Button
        Button resetBtn = new Button("RESET");
        
        //scene
        var scene = new Scene(gridPane, 800, 700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}