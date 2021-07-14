package com.javasampleapproach.batchcsvpostgresql.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PrintRandomService {

    public void printRandom() {
        System.out.println(ThreadLocalRandom.current().nextInt());
    }
}