package com.github.slycucumber7.workoutapp.main;


import java.io.File;


import java.io.IOException;

import com.github.slycucumber7.workoutapp.models.DataSet;
import com.github.slycucumber7.workoutapp.models.Model;
import com.github.slycucumber7.workoutapp.persistence.SaveLoadFile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scene1.fxml"));
			Parent root = loader.load();
			com.github.slycucumber7.workoutapp.controllers.Scene1Controller controller = loader.getController();

			// Checking for save file; loading if it exists.
			String filePath = "saveFile.ser";
			File saveFile = new File(filePath);
			DataSet data = new DataSet();
			if (saveFile.exists()) {
				SaveLoadFile serializer = new SaveLoadFile();
				data = serializer.deserialize(filePath);
			}
			// Initializing the model
			Model model = new Model(data);
			controller.setModel(model);
			model.setGraphics(controller);

			// drawing the window
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Workout Tracker- Overview");
			primaryStage.show();

		} catch (IOException e0) { //Displays error page if file corrupted.
			try {
				FXMLLoader corLoader = new FXMLLoader(getClass().getResource("/fxml/corruptedSave.fxml"));
				Parent corRoot = corLoader.load();
				Scene corScene = new Scene(corRoot, 600, 400);
				Stage corStage = new Stage();
				corStage.setScene(corScene);
				corStage.setTitle("Workout Tracker- Corrupted Save File");
				corStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found");
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Exception.");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}