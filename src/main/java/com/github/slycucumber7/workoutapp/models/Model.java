package com.github.slycucumber7.workoutapp.models;

import java.time.LocalDate;

import java.util.LinkedList;

import com.github.slycucumber7.workoutapp.controllers.Scene1Controller;

public class Model  {
	DataSet memory;
	LinkedList<singleEntry> dayList;
	LocalDate date;
	Scene1Controller graphics;	

	public Model(DataSet memory) {
		this.memory = memory; 
		this.date = LocalDate.now();
		if (!memory.getMemory().containsKey(date)) {
			LinkedList<singleEntry> newList = new LinkedList<singleEntry>(); // creates a new list for the date if it DNE
			memory.getMemory().put(date, newList); // adds new list to memory object
			this.dayList = newList; // sets model list attribute to new list
		} else {
			this.dayList = memory.getDayList(date);
		}
	}

	public void setDate(LocalDate date) {
		if (!memory.getMemory().containsKey(date)) {
			LinkedList<singleEntry> newList = new LinkedList<singleEntry>();
			memory.getMemory().put(date, newList);
			this.dayList = newList;
			this.date = date;
		} else {
			this.date = date;
			this.dayList = memory.getMemory().get(date);
		}
	}

	public LocalDate getDate() {
		return this.date;
	}

	public LinkedList<singleEntry> getDayList() {
		return this.dayList;
	}

	public LinkedList<singleEntry> getDayList(LocalDate date) {
		return memory.getMemory().get(date);
	}

	public void setDayList(LinkedList<singleEntry> list) {
		this.dayList = list;
	}

	public void reLoadDayList() {
		this.dayList = memory.getDayList(date);
	}

	public void removeDayList(LocalDate date) {
		memory.getMemory().remove(date);
	}

	public void setMemory(DataSet newMem) {
		this.memory = newMem;
	}

	public DataSet getMemory() {
		return this.memory;
	}

	public void setGraphics(Scene1Controller graphics) {
		this.graphics = graphics;
	}
	
	public Scene1Controller getGraphics() {
		return this.graphics;
	}
	
	public void changeDay(LocalDate newDate) { // changes date and re loads day list all in one action
		setDate(newDate);
		reLoadDayList();
	}

	public boolean addEntry(singleEntry entry) {
		if (entry.isEmpty()) {
			return false;
		}
		dayList.add(entry);
		return true;
	}

	public trackerEntry getStatsAsTracker(String entryName) {
		trackerEntry result = new trackerEntry(entryName.toLowerCase().trim());
		addMonthlyAveragesToEntry(entryName, this.date, result);
		result.calcAvg();
		return result;
	}

	private void addDailyAveragesToEntry(String entryName, trackerEntry tracker, LinkedList<singleEntry> dayList) {
		if (dayList != null) {
			for (singleEntry e : dayList) {
				if (e.getExerciseName().toLowerCase().trim().equals(tracker.getName())) {
					tracker.updateEntry(e.getWeight(), e.getSets(), e.getReps(), e.getIntensity());
				}
			}
		}
	}

	private void addWeeklyAveragesToEntry(String entryName, LocalDate date, trackerEntry tracker) {
		for (LocalDate current = date; current.isAfter(date.minusWeeks(1)); current = current.minusDays(1)) {
			LinkedList<singleEntry> currentDayList = getDayList(current);
			addDailyAveragesToEntry(entryName, tracker, currentDayList);
		}
	}

	private void addMonthlyAveragesToEntry(String entryName, LocalDate date, trackerEntry tracker) {
		if (tracker.getName().isBlank()) {
			System.out.println("Entry name is blank.");
			return;
		}
		for (LocalDate current = date; current.isAfter(date.minusWeeks(4)); current = current.minusWeeks(1)) {
			addWeeklyAveragesToEntry(entryName, current, tracker);
			if (current.isEqual(date)) {
				tracker.calcAvg();
				tracker.setCurrentWeekAverages(new trackerEntry(true, entryName + "copy", tracker.getAvgWt(),
						tracker.getAvgS(), tracker.getAvgR(), tracker.getAvgRpe()));
			}
		}
	}

}
