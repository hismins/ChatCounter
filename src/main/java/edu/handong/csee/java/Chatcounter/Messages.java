package edu.handong.csee.java.Chatcounter;

import java.io.FileNotFoundException;


/**
 * This class filters the overlapping variable(name).
 * 
 * @author miin
 *
 */
public class Messages extends DataReader {

	DataReader data = new DataReader();

	public void WhatFiles(DataReader data) throws FileNotFoundException {
		int n;
		int N = 0;
		if (data instanceof DataReaderForCSV) {
			DataReaderForCSV dataforcsv = new DataReaderForCSV();
			if (names2.size() == 0) {
				data.names2.add(dataforcsv.user.get(0));
			}
			while (true) {
				String a = dataforcsv.user.get(N);
				for (n = 0; n < data.names2.size(); n++) {
					if (a.equals(data.names2.get(n))) {
						break;
					}
				}

				if (n >= names2.size()) {
					names2.add(dataforcsv.user.get(N));
				}
				N++;
				if (N >= dataforcsv.user.size())
					break;
			}

		}

		else if (data instanceof DataReaderForTXT) {

			DataReaderForTXT d2 = new DataReaderForTXT();
			if (names2.size() == 0) {
				data.names2.add(data.names.get(0));
			}
			while (true) {
				String a = data.names.get(N);
				for (n = 0; n < data.names2.size(); n++) {
					if (a.equals(data.names2.get(n))) {
						break;
					}

				}

				if (n >= names2.size()) {
					names2.add(data.names.get(N));

				}
				N++;
				if (N >= data.names.size())
					break;
			}

		}

	}

}