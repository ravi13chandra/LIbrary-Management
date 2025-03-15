package com.example.Libary_Management_System.repository;

import com.example.Libary_Management_System.enity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
}
