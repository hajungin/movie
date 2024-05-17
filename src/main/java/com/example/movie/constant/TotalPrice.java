package com.example.movie.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalPrice {
    private String title;
    private Long price;
    private LocalDate localDate;

    public TotalPrice(LocalDate localDate, Long price) {
        this.localDate = localDate;
        this.price = price;
    }

    public TotalPrice(String title, Long price) {
        this.title = title;
        this.price = price;
    }
}
