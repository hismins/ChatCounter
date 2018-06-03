package edu.handong.csee.java.Chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class will read the chat in txt.
 *  
 * @author miin.
 *
 */
public class DataReaderForTXT extends DataReader {

	String date; // date in txt file.
	String message; // message in txt file.
	String currentstate; // current state in txt file(currentstate)
	String currenthour; // current hour in txt file(currenthour)
	String currentminute; // current minute in txt file(currentminute)
	static ArrayList<String> messagelisttxt = new ArrayList<String>(); // add ArrayList to the message in txt file(
	// messagelisttxt)
	static ArrayList<String> datefortxt = new ArrayList<String>(); // add ArrayList to the date in txt file(
	// datelisttxt)
	static ArrayList<String> userfortxt = new ArrayList<String>(); // add ArrayList to the name in txt file(
	// messagelisttxt)
	static ArrayList<String> strMessagefortxt = new ArrayList<String>(); // add ArrayList to the message in txt file(
	// messagelisttxt)
	int i = 0;
	int c = 0;
	int b = 0;

	public void readtxt(String r1) throws IOException {
		String[] namestxt = new String[1000];
		try {
			File file = new File(r1);
			BufferedReader br = new BufferedReader(new FileReader(file));

			String Line;
			br.readLine();
			br.readLine();
			br.readLine();
			int currentYear = -1;
			int currentMonth = -1;
			int currentDay = -1;
			String currentDate = "";
			while ((Line = br.readLine()) != null) {
				if (Line.matches("-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s-+")) {
					String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s-+";
					Pattern r = Pattern.compile(pattern);

					Matcher m = r.matcher(Line);

					if (m.find()) {
						currentYear = Integer.parseInt(m.group(1));
						currentMonth = Integer.parseInt(m.group(2));
						currentDay = Integer.parseInt(m.group(3));
					}

					currentDate = currentYear + "" + currentMonth + "" + currentDay;
					continue;
				}

				if (Line.matches("\\[(.+)\\]\\s(\\[.+\\])\\s(.+)")) {
					String name = "";
					String pattern = "\\[(.+)\\]\\s(\\[.+\\])\\s(.+)";

					Pattern r = Pattern.compile(pattern);
					Matcher m = r.matcher(Line);
					if (m.find()) {
						name = m.group(1);
						date = m.group(2);
						message = m.group(3);

						names.add(name);
						datefortxt.add(date);
						strMessagefortxt.add(message);
					}
				}
				i++;
			}
			br.close();
		}

		catch (FileNotFoundException e) {
			System.out.println("There is not file.");
			System.exit(0);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}


	public String nametxt(String Line) {
		try {

			String g = "[";
			if (Line.substring(0, 1).equals(g)) {
				String name = "";
				String pattern = "\\[(.+)\\]\\s(\\[.+\\])\\s(.+)";

				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(Line);
				if (m.find()) {
					name = m.group(1);
					date = m.group(2);
					message = m.group(3);

					names.add(name);
				}

			} else {
				String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s-+";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(Line);

				if (m.find()) {
					String year = m.group(1);
					return year;
				}
			}
		}

		catch (NullPointerException e) {
			message = "";
		}
		return message;
	}

	/**
	 * This method is datetxt method.</br>
	 * This method return the time.</br>
	 * If the state is "오후", the time is added by 12 but if the state is "오전",
	 * nothing changes.
	 * 
	 * @param Line
	 * @return
	 */
	public String datetxt(String Line) {
		try {

			if (Line.matches("\\[..\\s([0-9]+):([0-9]+)\\]")) {

				String pattern = "\\[(.+)\\s([0-9]+):([0-9]+)\\]";

				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(Line);

				if (m.find()) {
					currentstate = m.group(1);
					currenthour = m.group(2);
					currentminute = m.group(3);
				}

				int a = Integer.parseInt(currenthour);

				if (currentstate.equals("오전")) {
					return changetime(a, currentminute);
				}

				else if (currentstate.equals("오후")) {
					return changetime(a + 12, currentminute);
				}

			}
		}

		catch (NullPointerException e) {
			return "";
		}
		return currenthour;
	}

	/**
	 * This is changetime method.</br>
	 * This method return time variable.</br>
	 * If the time is "12", time is "00". and if the time is "24", time is
	 * "12".</br>
	 * Also, if the number is larger than 0 and less than 9, time is "0" and
	 * digit.</br>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String changetime(int a, String b) {
		String time;
		if (a == 12) {
			time = "00:" + b;
			return time;
		}

		if (a == 24) {
			time = "12:" + b;
			return time;
		}

		else if (a < 10) {
			time = "0" + a + ":" + b;
			return time;
		} else
			time = a + ":" + b;
		return time;
	}

	/**
	 * This is messagetxt method.</br>
	 * This method returns revised message.</br>
	 * 
	 * @param time
	 * @param message
	 * @return
	 */
	public String messagetxt(String time, String message) {
		try {
			if (message.equals("사진")) {
				message = "Photo";
			}

			return "" + time + "" + " " + message + "";
		}

		catch (NullPointerException e) {
			message = "";
		}
		return message;
	}

	/**
	 * This method will add message to HashMap(Chatmessage).
	 */
	public void addHashMaptxt() {
		for (int a = 0; a < names.size(); a++) {
			if (!Chatmessage.containsKey(names.get(a))) {
				Chatmessage.put(names.get(a), new ArrayList<String>());
				listentry.add(names.get(a));
			}
		}

		for (i = 0; i < names.size(); i++) {
			for (b = 0; b < listentry.size(); b++) {
				if (names.get(i).equals(listentry.get(b))) {
					for (c = 0; c < Chatmessage.get(listentry.get(b)).size(); c++) {
						if (changecsvtxt(Chatmessage.get(listentry.get(b)).get(c))
								.equals(messagetxt(datetxt(datefortxt.get(i)), strMessagefortxt.get(i)))) {
							break;
						}
					}

					if (c >= Chatmessage.get(listentry.get(b)).size()) {
						Chatmessage.get(names.get(i))
						.add(messagetxt(datetxt(datefortxt.get(i)), strMessagefortxt.get(i)));
						break;
					}
				}

			}
		}
	}

	/**
	 * This is changetxt method.</br>
	 * This method change message if the message matches
	 * pattern(([0-9]+):([0-9]+):([0-9]+)\s(.+)).</br>
	 * Then, the revised message return.
	 * 
	 * @param Line
	 * @return
	 */
	public String changecsvtxt(String Line) {
		String csvtotxt = "";
		if (Line.matches("([0-9]+):([0-9]+):([0-9]+)\\s(.+)")) {
			String pattern = "([0-9]+):([0-9]+):([0-9]+)\\s(.+)";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(Line);
			if (m.find()) {
				csvtotxt = m.group(1) + ":" + m.group(2) + " " + m.group(4);
				return csvtotxt;
			}

		}
		return Line;
	}
}