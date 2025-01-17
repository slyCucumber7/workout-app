package com.github.slycucumber7.workoutapp.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class DataSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2903283175090861899L;
	private HashMap<LocalDate, LinkedList<singleEntry>> memory;

	public DataSet() {
		this.memory = new HashMap<LocalDate, LinkedList<singleEntry>>();
	}

	public HashMap<LocalDate, LinkedList<singleEntry>> getMemory() {
		return memory;
	}

	public void setMemory(HashMap<LocalDate, LinkedList<singleEntry>> memory) {
		this.memory = memory;
	}

	public boolean isEmpty() {
		return memory == null || memory.isEmpty();
	}

	public LinkedList<singleEntry> getDayList(LocalDate date) {
		return memory.get(date);

	}

}
