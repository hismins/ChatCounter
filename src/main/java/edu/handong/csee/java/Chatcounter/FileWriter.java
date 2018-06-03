package edu.handong.csee.java.Chatcounter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will implement write file in reference to HashMap.
 * 
 * @author miin
 *
 */
public class FileWriter {


	public void writeAFile(ArrayList<String> line, String targetFileName) {
		try {
			File file = new File(targetFileName);
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);

			for(String line:lines) {
				dos.write((line+"\n").getBytes());
			}
			dos.close();
			fos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void Printoutput(HashMap<String, Integer> chatcounter, String getfilename) {
		// TODO Auto-generated method stub

	}
}