package com.example.vela.model;

import com.example.vela.contoller.RestController;

public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println(new RestController().getResponse(123));
    }
}
