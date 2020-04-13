package com.ms3.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms3.app.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
	
}