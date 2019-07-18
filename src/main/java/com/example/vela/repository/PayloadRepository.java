package com.example.vela.repository;

import com.example.vela.model.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends JpaRepository <Payload, Long>{

}
