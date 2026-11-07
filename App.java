package com.mycompany.inventorymanager;

import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12));
        pane.setHgap(5);
        pane.setVgap(5);

       
        Label lblHeading = new Label("Enter product information:");
        pane.add(lblHeading, 0, 0, 2, 1);

        Label lblNumber = new Label("Number:");
        TextField tfNumber = new TextField();
        pane.add(lblNumber, 0, 1);
        pane.add(tfNumber, 1, 1);

        Label lblDescription = new Label("Description:");
        TextField tfDescription = new TextField();
        pane.add(lblDescription, 0, 2);
        pane.add(tfDescription, 1, 2);

        Label lblPrice = new Label("Price:");
        TextField tfPrice = new TextField();
        pane.add(lblPrice, 0, 3);
        pane.add(tfPrice, 1, 3);

      
        Button btQuit = new Button("Quit");
        Button btSave = new Button("Save");

        btQuit.setPrefWidth(90);
        btSave.setPrefWidth(90);

        pane.add(btQuit, 0, 4);
        pane.add(btSave, 1, 4);
        GridPane.setHalignment(btSave, javafx.geometry.HPos.RIGHT);

        Label lblStatus = new Label();
        pane.add(lblStatus, 0, 5, 2, 1);

        
        btSave.setOnAction(event -> {
            String text = tfNumber.getText() + "\t"
                    + tfDescription.getText() + "\t"
                    + tfPrice.getText();

            try {
                writeToFile(text);

                tfNumber.clear();
                tfDescription.clear();
                tfPrice.clear();

                lblStatus.setText("Product saved.");
                tfNumber.requestFocus();
            } catch (IllegalStateException ex) {
                lblStatus.setText("Could not save product.");
            }
        });

        btQuit.setOnAction(event -> Platform.exit());

        
        Scene scene = new Scene(pane, 420, 280);
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }

   
    private void writeToFile(String text) {
        try (FileWriter writer = new FileWriter("Products.txt", true)) {
            writer.write(text);
            writer.write(System.lineSeparator());
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to write to Products.txt", ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}