package edu.handong.csee.java.Chatcounter1;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * This is public class, DataWriter.
 * This class will implement write file in reference to hashmap.
 * 
 * @author miin
 *
 */
public class DataWriter {

	
	public ArrayList<String> Printoutput(HashMap<String, Integer> lines, String targetFileName) throws IOException {
		ArrayList<String> Print = new ArrayList<String>();
		try {
			File file = new File(targetFileName);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bufferwriter = new BufferedWriter(filewriter);
			PrintWriter printwriter = new PrintWriter(bufferwriter);

			Iterator iterator = lines.entrySet().iterator();
			Entry entry;

			while (iterator.hasNext()) {
				entry = (Entry) iterator.next();
				String name = (String) entry.getKey();
				printwriter.println(name + ":" + lines.get(name));
				Print.add(name + ":" + lines.get(name));
			}
			printwriter.flush();
			printwriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Print;
	}

}