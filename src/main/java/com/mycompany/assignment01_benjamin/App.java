package com.mycompany.assignment01_benjamin;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private Label counter;
    private Label keyStat;
    private Label statsLabel;
    
    private int currentIdx = 0;
    private int errors = 0;
    private int corrects = 0;
    
    private TextField targetText;
    private TextField userText;
    
    
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
        
        keyStat = new Label("Press any key.");
        keyStat.setStyle("-fx-font-weight: bold; -fx-font-size: 15pt; -fx-text-fill: dark_blue;");

        //scene
        root.getChildren().addAll(topBar, textArea, keyboard);

        Scene scene = new Scene(root, 800, 700);

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
    }
    
    // a top bar containing a next button, a reset button and a counter displaying the amount errors or corrects the user got.
    
    private HBox topBar() {
        HBox topBar = new HBox(15);
        topBar.setAlignment(Pos.CENTER_LEFT);
        
        counter= new Label();
        updateCounter();
        counter.setFont(Font.font("Georgia", FontWeight.BOLD, 15));
        
        Button next = new Button("NEXT");
        next.setOnAction(e -> nextText());
        
        Button reset = new Button("RESET");
        reset.setOnAction(e -> resetApp());
        
        statsLabel = new Label("Correct = 0 || Errors = 0");
        statsLabel.setStyle("-fx-font-weight: bold; -fx-font-family: monospace; -fx-font-size: 15pt; -fx-text-fill: grey;");
        
        topBar.getChildren().addAll(counter, next, reset, statsLabel);
        return topBar;
    }
    
    private void nextText() {
        if (currentIdx < sampleTexts.length - 1) {
            currentIdx++;
        } else {
            currentIdx = 0;
        }
        loadCurrentText();
    }
    
    private void loadCurrentText() {
        targetText.setText(sampleTexts[currentIdx]);
        userText.clear();
        keyStat.setText("Press any key...");
        keyStat.setTextFill(Color.YELLOW);
    }
    
    private void updateCounter() {
        counter.setText((currentIdx + 1) + "of" + sampleTexts.length);
    }
    
    private void resetApp() {
        currentIdx = 0;
        corrects = 0;
        errors = 0;
        loadCurrentText();
    }
    
    // the area where the user will be able to see their target sentence and input their awnser.
    
    private VBox textArea() {
        VBox vbox = new VBox(10);
        
        Label target = new Label("Type :");
        target.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt;");
        
        targetText = new TextField(sampleTexts[currentIdx]);
        targetText.setStyle("-fx-background-color: light_grey; -fx-font-family: monospace; -fx-font-size: 14pt;");
        
        Label response = new Label("Your Awnser: ");
        response.setStyle("-fx-background-color: lavender; -fx-font-family: monospace; -fx-font-size: 15pt;");
        
        userText = new TextField();
        userText.setStyle("-fx-background-color: grey; -fx-font-family: monospace; -fx-font-size: 15pt;");
        
        vbox.getChildren().addAll(target, targetText, response, userText);
        return vbox;
    }
    
    private VBox virtualKeyboard() {
        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setPadding(new Insets(15));
        keyboard.setStyle("-fx-background-color: #363332; -fx-background-radius: 10;");
        
        keyboard.getChildren().add(createRow(keys1, 50));
        keyboard.getChildren().add(createRow(keys2, 50));
        keyboard.getChildren().add(createRow(keys3, 50));
        keyboard.getChildren().add(createRow(keys4, 50));
        
        HBox spaceRow = new HBox(5);
        spaceRow.setAlignment(Pos.CENTER);
        Button spaceButton = new Button("SPACE");
        spaceButton.setPrefSize(300, 50);
        spaceButton.setStyle(normalKeyStyle);
        
        keyBtnMap.put(KeyCode.SPACE, spaceButton);
        
        spaceRow.getChildren().add(spaceButton);
        keyboard.getChildren().add(spaceRow);
        
        return keyboard;
    }
    
    
    private HBox createRow(Object[][] keysRow, double width) {
        HBox row = new HBox(5);
        row.setAlignment(Pos.CENTER);
        
        for (Object[] key : keysRow) {
            String label = (String) key[0];
            KeyCode code = (KeyCode) key[1];
            
            Button btn = new Button(label);
            btn.setStyle(normalKeyStyle);
            
            if (code == KeyCode.BACK_SPACE || code == KeyCode.SHIFT) {
                btn.setPrefSize(100, 50);
            } else {
                btn.setPrefSize(width, 50);
            }
            
            keyBtnMap.put(code, btn);
            
            row.getChildren().add(btn);
        }
        
        return row;
    }
    
    private void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        
        if (keyBtnMap.containsKey(code)) {
            keyBtnMap.get(code).setStyle(pressedKeyStyle);
            keyStat.setText("Key Pressed : " + code.getName());
            keyStat.setStyle("-fx-font-weight: bold; -fx-font-style: monospace; -fx-font-size: 15pt; -fx-text-fill: blue;");
           
        } else {
            keyStat.setText("Not Handled: " + code.getName());
            keyStat.setStyle("-fx-font-weight: bold; -fx-font-style: monospace; -fx-font-size: 15pt; -fx-text-fill: red;");
        }
       
        String currentTyped = userText.getText();
        
        if (code == KeyCode.BACK_SPACE) {
            if (currentTyped.length() > 0) {
                userText.setText(currentTyped.substring(0, currentTyped.length() - 1));
                calculateStats();
            }
        }
    }
    
    private void calculateStats() {
        
    }
//    
//    private void keyReleased(KeyEvent e) {
//        
//        
//    }

    public static void main(String[] args) {
        launch();
    }

}