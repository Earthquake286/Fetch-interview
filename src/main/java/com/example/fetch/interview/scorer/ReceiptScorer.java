package com.example.fetch.interview.scorer;

import com.example.fetch.interview.models.Item;
import com.example.fetch.interview.models.Receipt;
import com.example.fetch.interview.models.ReceiptPoints;

import java.time.LocalTime;

public class ReceiptScorer {
    public static ReceiptPoints scoreReceipt(Receipt receipt) {
        ReceiptPoints points = new ReceiptPoints();
        double score = 0L;

//        One point for every alphanumeric character in the retailer name.
        score += receipt.getRetailer().chars().filter(Character::isLetterOrDigit).count();

//        50 points if the total is a round dollar amount with no cents.
        if (receipt.getTotal() == Math.floor(receipt.getTotal())) {
            score += 50;
        }

//        25 points if the total is a multiple of 0.25.
        if (isMultipleOfPoint25(receipt.getTotal())) {
            score += 25;
        }

//        5 points for every two items on the receipt.
        score += (receipt.getItems().size() / 2) * 5L;

//        If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        for (Item item : receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 ==0) {
                score += Math.ceil(item.getPrice() * 0.2);
            }
        }

//        6 points if the day in the purchase date is odd.
        if (receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            score += 6;
        }

//        10 points if the time of purchase is after 2:00pm and before 4:00pm.
        if (isBetween2pmAnd4pm(receipt.getPurchaseTime())) {
            score += 10;
        }

        points.setPoints(score);
        return points;
    }

    private static boolean isMultipleOfPoint25(double number) {
        double result = number / 0.25;
        return result == Math.floor(result);
    }

    public static boolean isBetween2pmAnd4pm(LocalTime time) {
        LocalTime start = LocalTime.of(14, 0); // 2pm
        LocalTime end = LocalTime.of(16, 0);   // 4pm

        return !time.isBefore(start) && !time.isAfter(end);
    }
}
