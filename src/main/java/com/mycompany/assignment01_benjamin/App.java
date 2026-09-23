package com.mycompany.assignment01_benjamin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        
        //first row of keys
        Button accentBtn = new Button("`");
        Button oneBtn = new Button("1");
        Button twoBtn = new Button("2");
        Button threeBtn = new Button("3");
        Button fourBtn = new Button("4");
        Button fiveBtn = new Button("5");
        Button sixBtn = new Button("6");
        Button sevenBtn = new Button("7");
        Button eightBtn = new Button("8");
        Button nineBtn = new Button("9");
        Button zeroBtn = new Button("0");
        Button minBtn = new Button("-");
        Button plusBtn = new Button("+");
        Button deleteBtn = new Button("DELETE");
        
        //second row of keys
        Button tabBtn = new Button("TAB");
        Button qBtn = new Button("q");
        Button wBtn = new Button("w");
        Button eBtn = new Button("e");
        Button rBtn = new Button("r");
        Button tBtn = new Button("t");
        Button yBtn = new Button("y");
        Button uBtn = new Button("u");
        Button iBtn = new Button("i");
        Button oBtn = new Button("o");
        Button pBtn = new Button("p");
        Button openBracketBtn = new Button("[");
        Button clostBracketBtn = new Button("]");
        Button backSlashBtn = new Button("\"");
        
        //third row of keys
        Button aBtn = new Button("a");
        Button sBtn = new Button("s");
        Button dBtn = new Button("d");
        Button fBtn = new Button("f");
        Button gBtn = new Button("g");
        Button hBtn = new Button("h");
        Button jBtn = new Button("j");
        Button kBtn = new Button("k");
        Button lBtn = new Button("l");
        Button towDotBtn = new Button(";");
        Button quoteBtn = new Button("'");
        Button enterBtn = new Button("ENTER");
        
        //fourth row of keys
        Button shift1Btn = new Button("SHIFT");
        Button zBtn = new Button("z");
        Button xBtn = new Button("x");
        Button cBtn = new Button("c");
        Button vBtn = new Button("v");
        Button bBtn = new Button("b");
        Button nBtn = new Button("n");
        Button mBtn = new Button("m");
        Button commaBtn = new Button(",");
        Button periodBtn = new Button(".");
        Button slashBtn = new Button("/");
        Button shift2Btn = new Button("SHIFT");
        
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