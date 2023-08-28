package com.example.fetch.interview.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Item {
    public Item(){

    }
    private String shortDescription;
    private Float price;
}
