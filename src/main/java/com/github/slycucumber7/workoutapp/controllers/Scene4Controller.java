package com.github.slycucumber7.workoutapp.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class Scene4Controller {
	private com.github.slycucumber7.workoutapp.models.Model model;
	@FXML
	TextField wkSetF, wkRepF, wkWtF, wkRpeF, moSetF, moRepF, moWtF, moRpeF, entryNameField;

	public Scene4Controller() {

	}

	public void setModel(com.github.slycucumber7.workoutapp.models.Model model) {
		this.model = model;
	}

	public void setEntryName(String entryName) {
		entryNameField.setText(entryName);
		com.github.slycucumber7.workoutapp.models.trackerEntry monthTracker = model.getStatsAsTracker(entryName);
		com.github.slycucumber7.workoutapp.models.trackerEntry weekTracker = monthTracker.getCurrentWeekAverages();

		// wkSetF.setText(weekTracker.getAvgS() + "");
		String format = "%.2f";
		wkSetF.setText(String.format(format, weekTracker.getAvgS()));

		// wkRepF.setText(weekTracker.getAvgR() + "");
		wkRepF.setText(String.format(format, weekTracker.getAvgR()));

		// wkWtF.setText(weekTracker.getAvgWt() + "");
		wkWtF.setText(String.format(format, weekTracker.getAvgWt()));

		// wkRpeF.setText(weekTracker.getAvgRpe() + "");
		wkRpeF.setText(String.format(format, weekTracker.getAvgRpe()));

		// moSetF.setText(monthTracker.getAvgS() + "");
		moSetF.setText(String.format(format, monthTracker.getAvgS()));

		// moRepF.setText(monthTracker.getAvgR() + "");
		moRepF.setText(String.format(format, monthTracker.getAvgR()));

		// moWtF.setText(monthTracker.getAvgWt() + "");
		moWtF.setText(String.format(format, monthTracker.getAvgWt()));

		// moRpeF.setText(monthTracker.getAvgRpe() + "");
		moRpeF.setText(String.format(format, monthTracker.getAvgRpe()));
		
	}

}
