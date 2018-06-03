package edu.handong.csee.java.Chatcounter;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 
 * This class reads a file.
 * 
 * @author miin
 *
 */
public class DataReader {

	static ArrayList<String> names = new ArrayList(); // txt file name arrayList, names.
	static ArrayList<String> names2 = new ArrayList(); // ArrayList that is filtered overlapping names. names2
	static HashMap<String, ArrayList<String>> Chatmessage = new HashMap<String, ArrayList<String>>(); // save the
																										// message,
																										// Chatmessage.
	static HashMap<String, Integer> Chatcounter = new HashMap<String, Integer>(); // count the message, ChatCounter.
	static ArrayList<String> listentry = new ArrayList<String>(); // save the key of Hashmap, listentry
	int b;
	int a;


	public static void main(String[] args) throws IOException {

		DataReader dataread = new DataReader();

		try {
			CLIPrinter clip = new CLIPrinter();
			clip.run(args);
			if (clip.getfilepath() == null) {
				// throw new NullPointerException();
			}
			dataread.run(clip.getfilepath());
			Chatcounter = dataread.sortvalue(Chatcounter);
			FileWriter fw = new FileWriter();
			fw.Printoutput(Chatcounter, clip.getfilename());
		}

		catch (IOException e) {
			System.out.println(e);
		}

	}


	public void run(String r1) throws IOException {
		DataReader mydata = new DataReader();
		Messages mes = new Messages();
		ArrayList<String> r2 = mydata.getdata(r1);

		DataReaderForTXT datafortxt = new DataReaderForTXT();
		DataReaderForCSV dataforcsv = new DataReaderForCSV();

		for (int i = 0; i < r2.size(); i++) {
			String r3 = r2.get(i).substring(r2.get(i).length() - 3, r2.get(i).length());
			// System.out.println(r3);

			if (r3.equals("csv")) {
				File file = new File(r2.get(i));
				dataforcsv.readcsv(file.getPath());
				mes.WhatFiles(dataforcsv);
				dataforcsv.addHashmap();
			}

		}
		for (int j = 0; j < r2.size(); j++) {
			String r3 = r2.get(j).substring(r2.get(j).length() - 3, r2.get(j).length());
			if (r3.equals("txt")) {

				File file = new File(r2.get(j));
				datafortxt.readtxt(file.getPath());
				mes.WhatFiles(datafortxt);
				datafortxt.addHashMaptxt();
			}
		}
		for (int d = 0; d < listentry.size(); d++) {
			Chatcounter.put(listentry.get(d), Chatmessage.get(listentry.get(d)).size());
		}

	}

	
	public ArrayList<String> getdata(String strDir) {
		CLIPrinter clip = new CLIPrinter();
		// 1. get directory.
		File myDir = getDirectory(strDir);
		// 2. get list of files from directory
		File[] files = getListOffilesFromDirectory(myDir);

		ArrayList<String> messages = readFiles(files);
		for (int t = 0; t < messages.size(); t++) {
			messages.set(t, strDir + "\\" + messages.get(t));

		}
		return messages;
	}

	
	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		return myDirectory;
	}

	public File[] getListOffilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}


	public ArrayList<String> readFiles(File[] files) {
		ArrayList<String> message = new ArrayList<String>();
		int i = 0;
		while (i < files.length) {
			message.add(files[i].getName());
			i++;
		}

		return message;

	}

	public HashMap<String, Integer> sortvalue(HashMap<String, Integer> ChatCounter) {
		int x = 0;
		int[] tempnum = new int[39];
		int tempnum2;
		String tpname;
		LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		Iterator iterator = Chatmessage.entrySet().iterator();
		Entry entry;
		String[] tempname = new String[39];
		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();
			tempname[x] = (String) entry.getKey();
			tempnum[x] = ChatCounter.get(tempname[x]);
			x++;
		}

		for (int y = 0; y < tempnum.length; y++) {
			for (int z = y + 1; z < tempnum.length; z++) {
				if (tempnum[y] < tempnum[z]) {
					tempnum2 = tempnum[y];
					tempnum[y] = tempnum[z];
					tempnum[z] = tempnum2;

					tpname = tempname[y];
					tempname[y] = tempname[z];
					tempname[z] = tpname;
				}
			}
		}
		for (int n = 0; n < tempnum.length; n++) {
			temp.put(tempname[n], tempnum[n]);
		}
		return temp;
	}
	
}

