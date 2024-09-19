package Level1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Task3 extends Application {

    @Override
    public void start(Stage primaryStage) {
    
        Label grades = new Label("Enter the number of grades:");
        TextField gradesField = new TextField();
        Button submit = new Button("Submit");
        Label result = new Label();
        VBox vbox = new VBox(10,grades, gradesField, submit, result);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0,750,0,750));


        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Average Grade Calculator");
         Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();

    
        submit.setOnAction(e -> {
            try {
                int numGrades = Integer.parseInt(gradesField.getText());
                if (numGrades <= 0) {
                    result.setText("Number of grades must be greater than zero.");
                    return;
                }
                double[] grade = new double[numGrades];
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter Grades");
                dialog.setHeaderText("Enter the grades separated by commas:");
                dialog.setContentText("Grades:");

                String gradesInput = dialog.showAndWait().orElse("");
                String[] gradesArray = gradesInput.split(",");
                if (gradesArray.length != numGrades) {
                    result.setText("Number of grades entered does not match the initial count.");
                    return;
                }
                double sum = 0;
                for (int i = 0; i < numGrades; i++) {
                    grade[i] = Double.parseDouble(gradesArray[i].trim());
                    sum += grade[i];
                }
                double average = sum / numGrades;
                result.setText(String.format("Average Grade: %.2f", average));
            } catch (NumberFormatException ex) {
                result.setText("Invalid input!!");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
