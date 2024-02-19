package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Properties;

public class Main extends Application {

    private static final String address = "jdbc:mysql://localhost:3306/CDICollege?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";// database user name
    private static final String pass = "MemeEmad@2001"; // database user name
    @Override
    public void start(Stage primaryStage) throws SQLException{

        Connection con = DriverManager.getConnection(address, user, pass);
        Label labelID = new Label();
        Label labelName = new Label();
        Label labelGPA = new Label();



        // Connect to the database and retrieve the first row of the Student table
        try {

            // Load JDBC driver




            // Execute query
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student LIMIT 1");

            // Retrieve data from the result set
            if (resultSet.next()) {
               int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                String gpa = resultSet.getString("GPA");

                // Display data
                labelID.setText("ID: "+id );
                labelName.setText("Name: " + name );
                labelGPA.setText("GPA: "+gpa );

            } else {
                labelID.setText("No student found.");
            }

            // Close resources
            resultSet.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            labelID.setText("Error: "+ e.getMessage());
        }

        VBox root = new VBox();


        root.getChildren().addAll(labelID,labelName,labelGPA);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFX MySQL Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}