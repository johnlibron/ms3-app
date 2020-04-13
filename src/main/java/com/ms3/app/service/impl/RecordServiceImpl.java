package com.ms3.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms3.app.entity.Record;
import com.ms3.app.repository.RecordRepository;
import com.ms3.app.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordRepository recordRepository;

	@Override
	public List<Record> saveAll(List<Record> records) {
		return recordRepository.saveAll(records);
	}

}