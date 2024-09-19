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

public class Task2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        
        Label inputLabel = new Label("Enter Word or Phrase:");
        gridPane.add(inputLabel, 0, 0);
        TextField inputField = new TextField();
        gridPane.add(inputField, 1, 0);
        Button check = new Button("Check");
        gridPane.add(check, 1, 1);
        Label result = new Label("Result:");
        gridPane.add(result, 1, 2);     
        

        VBox grid = new VBox(gridPane);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(0,750,0,750));

        
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Palindrome Checker");
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();

        
        check.setOnAction(e -> {
            String inputText = inputField.getText();
            boolean isPalindrome = isPalindrome(inputText);
            result.setText("Result: " + (isPalindrome ? "Palindrome" : "Not a palindrome"));
        });
    }

    
    private boolean isPalindrome(String inputText) {
        
        String cleaned = inputText.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int length = cleaned.length();
        for (int i = 0; i < length / 2; i++) {
            if (cleaned.charAt(i) != cleaned.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
