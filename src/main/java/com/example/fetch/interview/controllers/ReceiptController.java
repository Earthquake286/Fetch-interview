package com.example.fetch.interview.controllers;

import com.example.fetch.interview.models.Receipt;
import com.example.fetch.interview.models.ReceiptId;
import com.example.fetch.interview.models.ReceiptPoints;
import com.example.fetch.interview.scorer.ReceiptScorer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ReceiptController {
    private static Map<ReceiptId, Receipt> cache = new HashMap<>();

    @PostMapping(path = "/receipt/process",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReceiptId getReceiptId(@RequestBody Receipt receipt) {
        ReceiptId id = new ReceiptId();
        id.setId(UUID.randomUUID());
        cache.put(id, receipt);
        return id;
    }

    @GetMapping("/receipt/{id}/points")
    public ResponseEntity<ReceiptPoints> getReceiptPoints(@PathVariable("id") String id) {
        ReceiptId receiptId = new ReceiptId();
        receiptId.setId(UUID.fromString(id));

        if (cache.containsKey(receiptId)) {
            Receipt receipt = cache.get(receiptId);
            return  ResponseEntity.ok(ReceiptScorer.scoreReceipt(receipt));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReceiptPoints());
    }

}
