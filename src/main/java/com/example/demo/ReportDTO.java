package com.example.demo;

public class ReportDTO {

    private String date;
    private String type;
    private String name;
    private String amount;

    public ReportDTO(String date, String type, String name, String amount) {
        this.date = date;
        this.type = type;
        this.name = name;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}