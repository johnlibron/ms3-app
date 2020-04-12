package com.ms3.app.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ms3.app.model.Record;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Controller
public class UploadController {

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
				// create a reader
				Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				
				// columns name
				String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

				// create a mapping strategy
				ColumnPositionMappingStrategy<Record> strategy = new ColumnPositionMappingStrategy<Record>();
				strategy.setType(Record.class);
				strategy.setColumnMapping(columns);
				
				// create CSV bean reader
				CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader)
						.withMappingStrategy(strategy)
						.withSkipLines(1)
						.withIgnoreLeadingWhiteSpace(true)
						.build();
				
				List<Record> records = new ArrayList<>();
				
				// iterate through list of records
				for (Record record : (Iterable<Record>) csvToBean) {
					if (!record.isEmpty()) {
						records.add(record);
					}
				}

				model.addAttribute("records", records);
				model.addAttribute("status", true);
				
				reader.close();
				
			} catch (Exception e) {
				model.addAttribute("message", "An error occurred while processing the CSV file.");
				model.addAttribute("status", false);
			}
		}
		
		return "upload";
	}

}