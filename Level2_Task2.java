package Level2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Task2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Label password = new Label("Enter Password:");
        PasswordField passField = new PasswordField();
        Button check = new Button("Check Strength");
        Label result = new Label();

    
        VBox vbox = new VBox(10, password, passField, check, result);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20,700,20,700));

        
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Password Strength Checker");
         Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();

        
        check.setOnAction(e -> {
            String passwords = passField.getText();
            String feedback = analyze(passwords);
            result.setText(feedback);
        });
    }
    private String analyze(String password) {
        if (password.isEmpty()) {
            return "Password cannot be empty!!";
        }

        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        boolean isLongEnough = password.length() >= 8;

        if (hasUppercase && hasLowercase && hasDigit && hasSpecialChar && isLongEnough) {
            return "Strong password!!";
        } else if (hasUppercase && hasLowercase && hasDigit && isLongEnough) {
            return "Moderate password. Consider adding special characters.";
        } else if (hasUppercase && hasLowercase && isLongEnough) {
            return "Weak password. Consider adding numbers and special characters.";
        } else if (hasUppercase && isLongEnough) {
            return "Very weak password. Consider adding lowercase letters, numbers, and special characters.";
        } else {
            return "Very weak password. Password is too short or missing critical elements.";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
