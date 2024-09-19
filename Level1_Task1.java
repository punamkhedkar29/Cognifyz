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

public class Task1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        
        Label temp = new Label("Enter Temperature:");
        gridPane.add(temp, 0, 0);
        TextField input = new TextField();
        gridPane.add(input, 1, 0);
        Label unit = new Label("Unit (C/F):");
        gridPane.add(unit, 0, 1);
        ComboBox<String> unitField = new ComboBox<>();
        unitField.getItems().addAll("C","F");
        gridPane.add(unitField, 1, 1);
        Button convert = new Button("Convert");
        gridPane.add(convert, 1, 2);
        Label result = new Label("Converted Temperature:");
        gridPane.add(result, 1, 3);
        
        
        VBox grid = new VBox(gridPane);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(0,750,0,750));


        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Temperature Converter");
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();

        convert.setOnAction(e -> {
            String inputText = input.getText();
            String unitText = unitField.getValue();
            double convertedTemp = convertTemp(inputText, unitText);
            result.setText("Converted Temperature: " + convertedTemp);
        });
    }

    
    private double convertTemp(String inputText, String unitText) {
        double inputTemp = Double.parseDouble(inputText);
        if (unitText.equalsIgnoreCase("C")) {
            return (inputTemp * 9/5) + 32; 
        } else if (unitText.equalsIgnoreCase("F")) {
            return (inputTemp - 32) * 5/9; 
        }
        return inputTemp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
