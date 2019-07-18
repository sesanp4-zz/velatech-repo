package com.example.vela.service;

import com.example.vela.repository.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayloadService {

    @Autowired
    PayloadRepository payloadRepository;

    public PayloadRepository getPayloadRepository() {
        return payloadRepository;
    }
}
