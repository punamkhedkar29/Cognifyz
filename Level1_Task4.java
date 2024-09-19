package Level1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.security.SecureRandom;

public class Task4 extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label lengthLabel = new Label("Enter Password Length:");
        gridPane.add(lengthLabel, 0, 0);
        TextField lengthField = new TextField();
        gridPane.add(lengthField, 1, 0);
        CheckBox Numbers = new CheckBox("Include Numbers");
        gridPane.add(Numbers, 1, 1);
        CheckBox Lowercase = new CheckBox("Include Lowercase Letters");
        gridPane.add(Lowercase, 1, 2);
        CheckBox Uppercase = new CheckBox("Include Uppercase Letters");
        gridPane.add(Uppercase, 1, 3);
        CheckBox SpecialChar = new CheckBox("Include Special Characters");
        gridPane.add(SpecialChar, 1, 4);
        Button generate = new Button("Generate Password");
        gridPane.add(generate, 1, 5);
        Label passwordLabel = new Label("Generated Password:");
        gridPane.add(passwordLabel, 1, 6);

        VBox vbox = new VBox(gridPane);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0,750,0,750));


       
    
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Password Generator");
        
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    

        
        generate.setOnAction(e -> {
            String lengthText = lengthField.getText();
            try {
                int length = Integer.parseInt(lengthText);
                if (length > 0) {
                    boolean useNumbers = Numbers.isSelected();
                    boolean useLowercase = Lowercase.isSelected();
                    boolean useUppercase = Uppercase.isSelected();
                    boolean useSpecial = SpecialChar.isSelected();

                    String password = generatePassword(length, useNumbers, useLowercase, useUppercase, useSpecial);
                    passwordLabel.setText("Generated Password: " + password);
                } else {
                    passwordLabel.setText("Password length must be greater than zero.");
                }
            } catch (NumberFormatException ex) {
                passwordLabel.setText("Please enter a valid number.");
            }
        });
    }

    
    private String generatePassword(int length, boolean useNumbers, boolean useLowercase, boolean useUppercase, boolean useSpecial) {
        String numbers = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special = "!@#$%^&*()-_=+<>?";

        StringBuilder characterPool = new StringBuilder();
        if (useNumbers) characterPool.append(numbers);
        if (useLowercase) characterPool.append(lowercase);
        if (useUppercase) characterPool.append(uppercase);
        if (useSpecial) characterPool.append(special);

        if (characterPool.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be selected.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
