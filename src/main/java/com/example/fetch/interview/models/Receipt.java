package com.example.fetch.interview.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Data
public class Receipt {
    public Receipt() {

    }
    private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private List<Item> items;
    private Double total;
}
