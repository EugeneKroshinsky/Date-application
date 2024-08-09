package com.example.DateApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindRequest {
    @NotEmpty(message = "type can't be empty")
    private String type;

    @NotEmpty(message = "country can't be empty")
    private String country;

    @NotEmpty(message = "region can't be empty")
    private String region;

    @NotEmpty(message = "city can't be empty")
    private String city;

    @Min(value=0, message = "Price should be greater than 0")
    private int price;
}
