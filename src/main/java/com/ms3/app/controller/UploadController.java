package com.ms3.app.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ms3.app.Ms3AppApplication;
import com.ms3.app.model.Record;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(Ms3AppApplication.class);

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@PostMapping("/upload-csv")
	public String uploadCsv(@RequestParam("file") MultipartFile file, Model model) {
		
		// validate CSV file
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {
			// parse CSV file to create a list of 'record' objects
			try {
				// columns names
				String[] headerRecord = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
				
				// create a reader
				Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				
				// create CSV reader
				CSVReader csvReader = new CSVReaderBuilder(reader).build();
				
				// create a write
				Writer writer = Files.newBufferedWriter(Paths.get("ms3Interview-bad.csv"));
				
				// create a CSV writer
				ICSVWriter csvWriter = new CSVWriterBuilder(writer).build();
				
				// write header record
				csvWriter.writeNext(headerRecord);
				
				int recordsReceived = 0;
				int recordsSuccessful = 0;
				int recordsFailed = 0;
				
				List<Record> records = new ArrayList<>();
				
				Boolean checkHeader = null;

				// iterate through list of records
				for (String[] record : csvReader.readAll()) {
					if (null == checkHeader) {
						checkHeader = Arrays.equals(record, headerRecord);
						if (!checkHeader) {
							throw new Exception("Header doesn't match the column required.");
						}
					} else {
						if (record.length == headerRecord.length) {
							Record recordObj = new Record();
							recordObj.setA(record[0]);
							recordObj.setB(record[1]);
							recordObj.setC(record[2]);
							recordObj.setD(record[3]);
							recordObj.setE(record[4]);
							recordObj.setF(record[5]);
							recordObj.setG(record[6]);
							recordObj.setH(record[7]);
							recordObj.setI(record[8]);
							recordObj.setJ(record[9]);
							records.add(recordObj);
							recordsSuccessful++;
						} else {
							if (!isRecordEmpty(record)) {
								csvWriter.writeNext(record);
								recordsFailed++;
							}
						}
					}
				}
				
				recordsReceived = recordsSuccessful + recordsFailed;
				
				logger.info(recordsReceived + " of records received");
				logger.info(recordsSuccessful + " of records successful");
				logger.info(recordsFailed + " of records failed");

				model.addAttribute("records", records);
				model.addAttribute("status", true);
				
				csvReader.close();
				reader.close();
				
				csvWriter.close();
				writer.close();
				
			} catch (Exception e) {
				model.addAttribute("message", "An error occurred while processing the CSV file.");
				model.addAttribute("status", false);
			}
		}
		
		return "upload";
	}
	
	private boolean isRecordEmpty(String[] record) {
		boolean isEmpty = true;
		for (String str : record) {
			if (str.length() > 0 || !str.isEmpty()) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}
	
}