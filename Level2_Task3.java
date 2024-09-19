package Level2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task3 extends Application {

    private static final int SHIFT = 3; 

    @Override
    public void start(Stage primaryStage) {
        
        Label actionLabel = new Label("Choose Action:");
        ToggleGroup actionGroup = new ToggleGroup();
        RadioButton encryptButton = new RadioButton("Encrypt");
        RadioButton decryptButton = new RadioButton("Decrypt");
        encryptButton.setToggleGroup(actionGroup);
        decryptButton.setToggleGroup(actionGroup);

        Label fileLabel = new Label("Choose File:");
        Button chooseFileButton = new Button("Select File");
        TextField filePathField = new TextField();
        filePathField.setEditable(false);

        Button processButton = new Button("Process File");
        Label statusLabel = new Label();


        VBox vbox = new VBox(10, actionLabel, encryptButton, decryptButton, fileLabel, chooseFileButton, filePathField, processButton, statusLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20,700,20,700));

    
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("File Encryptor/Decryptor");
         Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();


    
        FileChooser fileChooser = new FileChooser();
        
        chooseFileButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                filePathField.setText(file.getAbsolutePath());
            }
        });

        processButton.setOnAction(e -> {
            String filePath = filePathField.getText();
            if (filePath.isEmpty() || actionGroup.getSelectedToggle() == null) {
                statusLabel.setText("Please select a file and choose an action.");
                return;
            }
            
            boolean encrypt = encryptButton.isSelected();
            try {
                processFile(filePath, encrypt);
                statusLabel.setText("File processed successfully.");
            } catch (IOException ex) {
                statusLabel.setText("Error processing file: " + ex.getMessage());
            }
        });
    }

    
    private void processFile(String filePath, boolean encrypt) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        String processedContent = encrypt ? encrypt(content) : decrypt(content);

        File outputFile = new File(filePath + (encrypt ? ".enc" : ".dec"));
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(processedContent);
        }
    }

    private String encrypt(String text) {
        return shiftText(text, SHIFT);
    }


    private String decrypt(String text) {
        return shiftText(text, -SHIFT);
    }

    
    private String shiftText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char i : text.toCharArray()) {
            if (Character.isLetter(i)) {
                char base = Character.isLowerCase(i) ? 'a' : 'A';
                result.append((char) ((i - base + shift + 26) % 26 + base));
            } else {
                result.append(i);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

