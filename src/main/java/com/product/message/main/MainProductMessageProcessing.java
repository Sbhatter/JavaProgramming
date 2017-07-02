package com.product.message.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import com.product.message.domain.Product;
import com.product.message.processor.Processor;
import com.product.message.report.Report;

public class MainProductMessageProcessing {

	private static BufferedReader inputMessageFile;

	/**
	 * This is the main method which processes the incoming message and generate
	 * report.
	 * 
	 * @param args
	 *            Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		try {

			Report log = new Report();
			int incomingMessageCount = 0;
			HashMap<String, Product> productHashMap = new HashMap<>();

			/*
			 * Reads the input message file which the contains the details of
			 * the processed products
			 */
			inputMessageFile = new BufferedReader(new FileReader("inputMessage.txt"));

			Processor processor = Processor.newProcessor();
			String message;

			/*
			 * Iterate through the input file line by line and log the report
			 * for every set of 10 message and pauses processing post 50
			 * messages
			 */
			while ((message = inputMessageFile.readLine()) != null) {
				incomingMessageCount++;
				productHashMap.putAll(processor.processInputMessage(message));
				log.generateLog(productHashMap, incomingMessageCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
