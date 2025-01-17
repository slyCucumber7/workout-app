package com.github.slycucumber7.workoutapp.controllers;

import java.io.IOException;

import java.time.LocalDate;

import com.github.slycucumber7.workoutapp.models.singleEntry;
import com.github.slycucumber7.workoutapp.persistence.SaveLoadFile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Scene1Controller {
	private com.github.slycucumber7.workoutapp.models.Model model;

	public Scene1Controller() {

	}

	@FXML
	VBox rootBox;
	@FXML
	DatePicker dateBox;
	@FXML
	Button prevDay, nextDay, addEntry, saveData;

	public void setModel(com.github.slycucumber7.workoutapp.models.Model model) {
		if (model == null) {
			System.out.println("empty model");
			return;
		}
		this.model = model;
		dateBox.setValue(model.getDate());

		drawEntries();
	}

	public void testDate(ActionEvent e) {
		changeDate(dateBox.getValue());
	}

	public void changeDate(LocalDate newDate) {
		// run this if newDate is valid
		if (model.getDate() != null && !newDate.equals(model.getDate())) { // if the date is not null and the new date
																			// is not the old date
			LocalDate oldDate = model.getDate();
			if (model.getDayList(oldDate).isEmpty()) { // remove the current list if it is empty
				model.removeDayList(oldDate);
			}
		}

		if (!model.getDate().equals(newDate)) { // if the old date is not equal to the new date, set the current date to
												// the new date
			model.setDate(newDate);
		}

		dateBox.setValue(newDate);
		drawEntries();
	}

	public void nextDay(ActionEvent e) {
		LocalDate nextDay = model.getDate().plusDays(1);
		changeDate(nextDay);
	}

	public void prevDay(ActionEvent e) {
		LocalDate prevDay = model.getDate().minusDays(1);
		changeDate(prevDay);
	}

	// scene 1 logic
	public void drawEntry(singleEntry e) {
		// create a container for this entry
		HBox container = new HBox();
		container.getStyleClass().add("entryContainer");
		// create a box for the buttons
		VBox buttonHolder = new VBox();
		buttonHolder.getStyleClass().add("buttonHolder");
		// initialize the buttons
		// Button add = new Button("Add new entry"); ///remove this
		Button edit = new Button("Edit entry");
		Button remove = new Button("Delete entry");
		Button stats = new Button("Stats");
		edit.getStyleClass().add("entryButton");
		remove.getStyleClass().add("entryButton");
		stats.getStyleClass().add("entryButton");

		// associate buttons with methods
		remove.setOnAction(event -> removeClicked(e, container));
		edit.setOnAction(event -> editClicked(e, container));
		stats.setOnAction(event -> statsClicked(e));
		// add buttons to holder
		buttonHolder.getChildren().addAll(edit, remove, stats);
		// create exercise description
		Text exerciseDesc = new Text(e.toString());
		exerciseDesc.getStyleClass().add("entryText");
		// add button box and exercise description to entry container
		container.getChildren().addAll(buttonHolder, exerciseDesc);
		// add entry container to scrollpane's VBox
		rootBox.getChildren().addAll(container);
	}

	public void removeEntry(singleEntry e, HBox entryBox) {
		model.getDayList().remove(e);
		rootBox.getChildren().remove(entryBox);
	}

	public void drawEntries() {
		clearAllEntries();
		for (int i = 0; i < model.getDayList().size(); i++) {
			if (model.getDayList().get(i) != null) {
				drawEntry(model.getDayList().get(i));
			}
		}
	}

	public void clearAllEntries() {
		rootBox.getChildren().clear();
	}

	public void removeClicked(singleEntry e, HBox entryBox) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scene3.fxml"));
			Parent root = loader.load();
			Scene3Controller scene3Controller = loader.getController();
			// scene3Controller.setGraphics(this);
			scene3Controller.setModel(model);
			scene3Controller.setCurrentEntry(e);
			scene3Controller.setEntryNode(entryBox);
			Scene scene3 = new Scene(root, 400, 400);
			Stage stage3 = new Stage();
			stage3.setScene(scene3);
			stage3.setTitle("Workout Tracker- Remove Entry");
			stage3.show();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	@FXML
	public void addClicked(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scene2.fxml"));
			Parent root = loader.load();
			Scene2Controller scene2Controller = loader.getController();
			// scene2Controller.setGraphics(this);
			scene2Controller.setModel(model);
			Scene scene2 = new Scene(root, 400, 400);
			Stage stage2 = new Stage();
			stage2.setScene(scene2);
			stage2.setTitle("Workout Tracker- Add New Entry");
			stage2.show();

		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void editClicked(singleEntry e, HBox container) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scene2.fxml"));
			Parent root = loader.load();
			Scene2Controller scene2Controller = loader.getController();
			// scene2Controller.setGraphics(this);
			scene2Controller.setModel(model);
			scene2Controller.setEdit(true);
			scene2Controller.setEntryRoot(container);
			scene2Controller.setEntry(e);
			Scene scene2 = new Scene(root, 400, 400);
			Stage stage2 = new Stage();
			stage2.setScene(scene2);
			stage2.setTitle("Workout Tracker- Modify Entry");
			stage2.show();
		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void statsClicked(singleEntry e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scene4.fxml"));
			Parent root = loader.load();
			Scene4Controller scene4Controller = loader.getController();
			scene4Controller.setModel(model);
			scene4Controller.setEntryName(e.getExerciseName());
			Scene scene2 = new Scene(root, 600, 400);
			Stage stage2 = new Stage();
			stage2.setScene(scene2);
			stage2.setTitle("Workout Tracker- Exercise Stats");
			stage2.show();
		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	public void saveClicked(ActionEvent e) {
		SaveLoadFile saver = new SaveLoadFile();
		String filePath = "saveFile.ser";
		saver.serialize(model.getMemory(), filePath);
	}

}
