package com.mycompany.assignment01_benjamin;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private Label counter;
    private Label keyStat;
    private Label statsLabel;
    private Label targetText;
    private Label userText;
    
    private int currentIdx = 0;
    private int errorCount = 0;
    private int correctCount = 0;
    
    private VBox root;
    
    // sample texts
    private final String[] sampleTexts = {
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };
    
    // keyboard keys
    private final Map<KeyCode, Button> keyBtnMap = new HashMap<>();
     
    // first row of keys
    private final Object[][] keyRow1 = {
        {"`", KeyCode.BACK_QUOTE}, {"1", KeyCode.DIGIT1}, {"2", KeyCode.DIGIT2}, {"3", KeyCode.DIGIT3},
        {"4", KeyCode.DIGIT4}, {"5", KeyCode.DIGIT5}, {"6", KeyCode.DIGIT6}, {"7", KeyCode.DIGIT7},
        {"8", KeyCode.DIGIT8}, {"9", KeyCode.DIGIT9}, {"0", KeyCode.DIGIT0}, {"-", KeyCode.MINUS},
        {"=", KeyCode.PLUS},{"DELETE", KeyCode.BACK_SPACE}
    };
    
    // second row of keys
    private final Object[][] keyRow2 = {
        {"Q", KeyCode.Q}, {"W", KeyCode.W}, {"E", KeyCode.E}, {"R", KeyCode.R},
        {"T", KeyCode.T}, {"Y", KeyCode.Y}, {"U", KeyCode.U}, {"I", KeyCode.I},
        {"O", KeyCode.O}, {"P", KeyCode.P}, {"[", KeyCode.OPEN_BRACKET}, {"]", KeyCode.CLOSE_BRACKET},
        {"\\", KeyCode.BACK_SLASH}
    };    
    // third row of keys
    private final Object[][] keyRow3 = {
        {"A", KeyCode.A}, {"S", KeyCode.S}, {"D", KeyCode.D}, {"F", KeyCode.F},
        {"G", KeyCode.G}, {"H", KeyCode.H}, {"J", KeyCode.J}, {"K", KeyCode.K},
        {"L", KeyCode.L}, {";", KeyCode.SEMICOLON}, {"'", KeyCode.QUOTE}, {"ENTER", KeyCode.ENTER},
    };
    
    // fourth row of keys
    private final Object[][] keyRow4 = {
        {"SHIFT", KeyCode.SHIFT}, {"Z", KeyCode.Z}, {"X", KeyCode.X}, {"C", KeyCode.C},
        {"V", KeyCode.V}, {"B", KeyCode.B}, {"N", KeyCode.N}, {"M", KeyCode.M},
        {",", KeyCode.COMMA}, {".", KeyCode.PERIOD}, {"/", KeyCode.SLASH}
    };
    
    private final String normalKeyStyle = "-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-border-color: #0a0a0a; -fx-border-radius: 4; -fx-background-radius: 4;";
    private final String pressedKeyStyle = "-fx-background-color: #474c4f; -fx-text-fill: white; -fx-border-color: #0a0a0a; -fx-border-radius: 4; -fx-background-radius: 4;";
    
    @Override
    public void start(Stage stage) {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: white");

        HBox topBar = topBar();

        VBox textArea = textArea();

        VBox keyboard = virtualKeyboard();
        
        keyStat = new Label("Press any key...");
        keyStat.setStyle("-fx-font-weight: bold; -fx-font-size: 15pt; -fx-text-fill: darkblue;");

        root.getChildren().addAll(topBar, keyStat, textArea, keyboard);

        Scene scene = new Scene(root, 900, 625);
        scene.setOnKeyPressed(e -> keyPressed(e));
        scene.setOnKeyReleased(e -> keyReleased(e));
        scene.setOnKeyTyped(e -> keyTyped(e));

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
        
        root.requestFocus();
    }
    
    /**
     * Creates the top bar containing the counter, the next button, the RESET button and the statics (amount of errors and corrects). 
     * @return the topBar.
     */
    private HBox topBar() {
        HBox topBar = new HBox(15);
        topBar.setAlignment(Pos.CENTER_LEFT);
        
        counter = new Label();
        updateCounter();
        counter.setStyle("-fx-font-weight: bold; -fx-font-family: monospace; -fx-font-size: 15pt; -fx-text-fill: blue;");
        
        Button next = new Button("NEXT");
        next.setStyle("-fx-font-weight: bold; -fx-font-family: monospace; -fx-font-size: 9pt; -fx-text-fill: green;");
        next.setOnAction(e -> {
            nextText();
            root.requestFocus();
        });
        
        Button reset = new Button("RESET");
        reset.setStyle("-fx-font-weight: bold; -fx-font-family: monospace; -fx-font-size: 9pt; -fx-text-fill: red;");
        reset.setOnAction(e -> {
            resetApp();
            root.requestFocus();
                });
        
        statsLabel = new Label("Correct = 0 || Errors = 0");
        statsLabel.setStyle("-fx-font-weight: bold; -fx-font-family: monospace; -fx-font-size: 15pt; -fx-text-fill: black;");
        
        topBar.getChildren().addAll(counter, next, reset, statsLabel);
        return topBar;
    }
    
    /**
     * Creates the textArea where the user will be able to see what sentence to type and to type it in the designated field below.
     * @return the textArea.
     */
    private VBox textArea() {
        VBox vbox = new VBox(10);
        
        Label target = new Label("Type :");
        target.setStyle("-fx-font-weight: bold; -fx-font-family: monospace; -fx-font-size: 13pt; -fx-text-fill: black;");
        
        targetText = new Label(sampleTexts[currentIdx]);
        targetText.setStyle("-fx-text-fill: black; -fx-font-family: monospace; -fx-font-size: 15pt;");
        
        Label response = new Label("Your Answer: ");
        response.setStyle("-fx-font-weight: bold; -fx-background-fill: lighgrey; -fx-text-fill: black; -fx-font-family: monospace; -fx-font-size: 13pt;");
        
        userText = new Label();
        userText.setStyle("-fx-font-family: monospace; -fx-font-size: 15pt;");
        
        vbox.getChildren().addAll(target, targetText, response, userText);
        return vbox;
    }
    
    /**
     * Creates the virtual keyboard.
     * @return the virtual keyboard.
     */
    private VBox virtualKeyboard() {
        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setPadding(new Insets(15));
        keyboard.setStyle("-fx-font-weight: bold; -fx-background-color: grey; -fx-background-radius: 10;");
        
        keyboard.getChildren().add(createRow(keyRow1, 50));
        keyboard.getChildren().add(createRow(keyRow2, 50));
        keyboard.getChildren().add(createRow(keyRow3, 50));
        keyboard.getChildren().add(createRow(keyRow4, 50));
        
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
    
    /**
     * A helper method that creates a row for the keyboard.
     * @param keysRow the row.
     * @param width the default width of a key.
     * @return a row of the keyboard.
     */
    private HBox createRow(Object[][] keysRow, double width) {
        HBox row = new HBox(5);
        row.setAlignment(Pos.CENTER);
        
        for (Object[] key : keysRow) {
            String label = (String) key[0];
            KeyCode code = (KeyCode) key[1];
            
            Button btn = new Button(label);
            btn.setStyle(normalKeyStyle);
            
            if (code == KeyCode.BACK_SPACE || code == KeyCode.SHIFT || code == KeyCode.ENTER) {
                btn.setPrefSize(100, 50);
            } else {
                btn.setPrefSize(width, 50);
            }
            
            keyBtnMap.put(code, btn);
            
            row.getChildren().add(btn);
        }
        
        return row;
    }
    
    /**
     * Displays the key pressed and checks if it could be handled or not. Makes BACK_SPACE able to delete a character.
     * @param e the keyEvent.
     */
    private void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        
        if (keyBtnMap.containsKey(code) ) {
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
    
    /**
     * Types the key unto the label.
     * @param e the keyEvent.
     */
    private void keyTyped(KeyEvent e) {
        String character = e.getCharacter();
        
        if (character.length() > 0) {
            char c = character.charAt(0);
            
            if (c >= 32) {
                String currentType = userText.getText();
                userText.setText(currentType + character);
                calculateStats();
            }
        }
    }
    
    /**
     * When the key is released, it returns it back its normal key style.
     * @param e the keyEvent.
     */
    private void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();
        
        if (keyBtnMap.containsKey(code)) {
            keyBtnMap.get(code).setStyle(normalKeyStyle);
        }   
    }
    
    /**
     * A helper method that goes to the next sentence.
     */
    private void nextText() {
        if (currentIdx < sampleTexts.length - 1) {
            currentIdx++;
        } else {
            currentIdx = 0;
        }
        
        correctCount = 0;
        errorCount = 0;
        
        updateStats();
        loadCurrentText();
    }
    
    /**
     * A helper method that displays the new sentence, and refreshes the user text box and the key status.
     */
    private void loadCurrentText() {
        targetText.setText(sampleTexts[currentIdx]);
        userText.setText("");
        keyStat.setText("Press any key...");
        updateCounter();
    }
    
    /**
     * A helper method that updates the page number.
     */
    private void updateCounter() {
        counter.setText((currentIdx + 1) + " of " + sampleTexts.length);
    }
    
    /**
     * A helper method resetting all values to their default value.
     */
    private void resetApp() {
        currentIdx = 0;
        correctCount = 0;
        errorCount = 0;
        
        updateStats();
        loadCurrentText();
    }
    
    /**
     * A helper method that keeps track of the amount of errors or correct letters that the user has inputted.
     */
    private void calculateStats() {
        String target = targetText.getText();
        String response = userText.getText();
        
        correctCount = 0;
        errorCount = 0;
        
        for (int i = 0; i < response.length(); i++) {
            if (i < target.length()) {
                if (response.charAt(i) == target.charAt(i)) {
                        correctCount++;
                    } else {
                        errorCount++;
                    }
            } else {
                errorCount++;
            }
        }
        
        updateStats();
    }
    
    /**
     * A helper method that updates the display of the amount of errors or correct letters.
     */
    private void updateStats() {
        statsLabel.setText("Correct: " + correctCount + " | Errors: " + errorCount);
    }
  
    public static void main(String[] args) {
        launch(args);
    }

}