package com.github.slycucumber7.workoutapp.models;

import java.io.Serializable;

import com.github.slycucumber7.workoutapp.exceptions.invalidInputException;

public class singleEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6429535517008967687L;
	String exerciseName;
	short sets, reps, weight, intensity;

	public singleEntry(String name, int sets, int reps, int weight, int intensity) {
		this.exerciseName = name;
		this.sets = (short) sets;
		this.reps = (short) reps;
		this.weight = (short) weight;
		this.intensity = (short) intensity;
	}

	public String getExerciseName() {
		return exerciseName;
	}
	
	public boolean isEmpty() {
		if(sets < 1 || exerciseName.isBlank()) {
			return true;
		}
		return false;
	}

	public void setExerciseName(String exerciseName) {
		if (exerciseName.isBlank()) {
			this.exerciseName = "Null.";
			return;
		}
		this.exerciseName = exerciseName;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(short sets) {
		if (sets < 0 || sets > 32767) {
			throw new invalidInputException("Input is outside of acceptable range.");
		}
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(short reps) {
		if (sets < 0 || sets > 32767) {
			throw new invalidInputException("Input is outside of acceptable range.");
		}
		this.reps = reps;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		if (sets < 0 || sets > 32767) {
			throw new invalidInputException("Input is outside of acceptable range.");
		}
		this.weight = weight;
	}

	public int getIntensity() {
		if (sets < 0 || sets > 32767) {
			throw new invalidInputException("Input is outside of acceptable range.");
		}
		return intensity;
	}

	public void setIntensity(short intensity) {
		if (sets < 0 || sets > 32767) {
			throw new invalidInputException("Input is outside of acceptable range.");
		}
		this.intensity = intensity;
	}

	public String toString() {
		String format = "[%s] Sets: %d Reps: %d Weight: %d RPE: %d";
		String output = String.format(format, this.getExerciseName(), this.getSets(), this.getReps(), this.getWeight(),
				this.getIntensity());
		return output;
	}
}
