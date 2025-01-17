package com.github.slycucumber7.workoutapp.controllers;

import com.github.slycucumber7.workoutapp.exceptions.invalidInputException;
import com.github.slycucumber7.workoutapp.models.Model;
import com.github.slycucumber7.workoutapp.models.singleEntry;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Scene2Controller {
	@FXML
	TextField nameF, weightF, repF, setF, rpeF, errorField;
	@FXML
	Button conBut, cloBut;
	private Model model;
	private singleEntry currentEntry;
	private HBox currentEntryRoot;
	private boolean isEdit;

	public Scene2Controller() {

	}

	@FXML
	public void confirm(ActionEvent e) {

		try {

			if (!isEdit) {
				String name = nameF.getText();
				int sets = Integer.parseInt(setF.getText());
				int reps = Integer.parseInt(repF.getText());
				int weight = Integer.parseInt(weightF.getText());
				int rpe = Integer.parseInt(rpeF.getText());
				if (sets < 0 || reps < 0 || weight < 0 || rpe < 0) {
					throw new invalidInputException();
				}
				if (!errorField.getText().isBlank()) {
					errorField.clear();
				}
				singleEntry newEntry = new singleEntry(name, sets, reps, weight, rpe);
				model.addEntry(newEntry);
				model.getGraphics().drawEntry(newEntry);
			} else {
				// code for edit goes here:
				String name = nameF.getText();
				int sets = Integer.parseInt(setF.getText());
				int reps = Integer.parseInt(repF.getText());
				int weight = Integer.parseInt(weightF.getText());
				int rpe = Integer.parseInt(rpeF.getText());
				if (sets < 0 || reps < 0 || weight < 0 || rpe < 0) {
					throw new invalidInputException();
				}
				if (!errorField.getText().isBlank()) {
					errorField.clear();
				}
				singleEntry newEntry = new singleEntry(name, sets, reps, weight, rpe);
				model.addEntry(newEntry);
				model.getGraphics().removeEntry(currentEntry, currentEntryRoot);
				model.getGraphics().drawEntry(newEntry);
				cancel(e);
			}
		} catch (NumberFormatException nfe) {
			errorField.setText("Weight, sets, reps, and rpe fields only accept whole number inputs.");
			return;
		} catch (invalidInputException iie) {
			errorField.setText("All numerical inputs must be non-negative.");
			return;
		}

	}

	@FXML
	public void cancel(ActionEvent e) {
		Stage stage = (Stage) cloBut.getScene().getWindow();
		stage.close();
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Model getModel() {
		return this.model;
	}

	public void setEdit(boolean val) {
		this.isEdit = val;
	}

	public boolean getEdit() {
		return this.isEdit;
	}

	public singleEntry getEntry() {
		return this.currentEntry;
	}

	public void setEntry(singleEntry entry) {
		this.currentEntry = entry;
		nameF.setText(entry.getExerciseName());
		weightF.setText(entry.getWeight() + "");
		repF.setText(entry.getReps() + "");
		setF.setText(entry.getSets() + "");
		rpeF.setText(entry.getIntensity() + "");
	}

	public void setEntryRoot(HBox root) {
		this.currentEntryRoot = root;
	}

	public HBox getEntryRoot() {
		return this.currentEntryRoot;
	}
}
