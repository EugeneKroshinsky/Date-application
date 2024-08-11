package com.example.DateApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindRequest {
    @NotEmpty(message = "type can't be empty")
    private String type;

    private String country;

    private String region;

    private String city;

    private String isAnyPrice;

    @Min(value=0, message = "Price should be greater than 0")
    private int price;

    @Override
    public String toString() {
        return new StringJoiner(", ", FindRequest.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("country='" + country + "'")
                .add("region='" + region + "'")
                .add("city='" + city + "'")
                .add("isAnyPrice='" + isAnyPrice + "'")
                .add("price=" + price)
                .toString();
    }
}
