package com.github.slycucumber7.workoutapp.persistence;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.github.slycucumber7.workoutapp.models.DataSet;

public class SaveLoadFile {

	public SaveLoadFile() {
	}

	public DataSet deserialize(String filePath) throws ClassNotFoundException, IOException {
		try {
			DataSet data = null;
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			data = (DataSet) in.readObject();
			in.close();
			fileIn.close();
			return data;
		} catch (ClassNotFoundException cnfe) {
			throw cnfe;
		} catch (IOException ioe) {
			throw ioe;
		}

	}

	public void serialize(DataSet data, String filePath) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("File not found");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("IO exception");
		}

	}
}
