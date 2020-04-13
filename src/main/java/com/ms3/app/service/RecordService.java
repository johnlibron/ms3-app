package com.ms3.app.service;

import java.util.List;

import com.ms3.app.entity.Record;

public interface RecordService {
	
	public List<Record> saveAll(List<Record> records);
	
}