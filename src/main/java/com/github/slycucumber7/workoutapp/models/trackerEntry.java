package com.github.slycucumber7.workoutapp.models;

public class trackerEntry {
	private String name;
	private double totR, totWt, totRpe, totS, entries, avgR, avgWt, avgRpe, avgS;
	private trackerEntry currentWeekAverages;

	public trackerEntry(String name, double weight, double sets, double reps, double rpe) {
		this.name = name;
		updateEntry(weight, sets, reps, rpe);
	}

	public trackerEntry(String name) {
		this.name = name;
	}

	public trackerEntry(boolean isSubset, String name, double avgWt, double avgS, double avgR, double avgRpe) {
		this.name = name;
		this.avgR = avgR;
		this.avgWt = avgWt;
		this.avgRpe = avgRpe;
		this.avgS = avgS;
	}

	public void updateEntry(double weight, double sets, double reps, double rpe) {
		this.entries++;
		this.totR += (sets * reps);
		this.totWt += (sets * reps * weight);
		this.totRpe += rpe;
		this.totS += sets;
	}

	public void calcAvg() {
		this.avgWt = this.totWt / this.totR;
		this.avgRpe = this.totRpe / this.entries;
		this.avgS = this.totS / this.entries;
		this.avgR = this.totR / this.totS;
	}

	public void setCurrentWeekAverages(trackerEntry tracker) {
		this.currentWeekAverages = tracker;
	}

	public trackerEntry getCurrentWeekAverages() {
		return currentWeekAverages;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public double getAvgR() {
		return avgR;
	}

//	public void setAvgR(int avgR) {
//		this.avgR = avgR;
//	}

	public double getAvgWt() {
		return avgWt;
	}

//	public void setAvgWt(int avgWt) {
//		this.avgWt = avgWt;
//	}

	public double getAvgRpe() {
		return avgRpe;
	}

//	public void setAvgRpe(int avgRpe) {
//		this.avgRpe = avgRpe;
//	}

	public double getAvgS() {
		return avgS;
	}

	@Override
	public String toString() {
		return "trackerEntry [name=" + name + ", totR=" + totR + ", totWt=" + totWt + ", totRpe=" + totRpe + ", totS="
				+ totS + ", entries=" + entries + ", avgR=" + avgR + ", avgWt=" + avgWt + ", avgRpe=" + avgRpe
				+ ", avgS=" + avgS + ", currentWeekAverages=" + currentWeekAverages + "]";
	}

//	public void setAvgS(int avgS) {
//		this.avgS = avgS;
//	}

}
