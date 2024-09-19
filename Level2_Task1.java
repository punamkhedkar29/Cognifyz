package Level2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Task1 extends Application {

    private Button[][] buttons = new Button[3][3];
    private char current = 'X';

    @Override
    public void start(Stage primaryStage) {
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button("");
                buttons[row][col].setPrefSize(100, 100);
                buttons[row][col].setOnAction(e -> handleMove((Button) e.getSource()));
                gridPane.add(buttons[row][col], col, row);
            }
        }

        
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> resetBoard());

        VBox vbox = new VBox(10, gridPane, resetButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

    
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic-Tac-Toe");
         Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }

    
    private void handleMove(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(String.valueOf(current));
            if (checkWin()) {
                showAlert(current + " Wins!!!");
            } else if (isBoardFull()) {
                showAlert("It's a draw!!!");
            } else {
                current = (current == 'X') ? 'O' : 'X';
            }
        }
    }

    
    private boolean checkWin() {
        
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(current)) &&
                buttons[i][1].getText().equals(String.valueOf(current)) &&
                buttons[i][2].getText().equals(String.valueOf(current))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(current)) &&
                buttons[1][i].getText().equals(String.valueOf(current)) &&
                buttons[2][i].getText().equals(String.valueOf(current))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(current)) &&
            buttons[1][1].getText().equals(String.valueOf(current)) &&
            buttons[2][2].getText().equals(String.valueOf(current))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(current)) &&
            buttons[1][1].getText().equals(String.valueOf(current)) &&
            buttons[2][0].getText().equals(String.valueOf(current))) {
            return true;
        }
        return false;
    }


    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    
    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        current = 'X';
    }

    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
        resetBoard();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
