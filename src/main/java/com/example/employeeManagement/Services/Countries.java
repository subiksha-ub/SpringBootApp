package com.example.employeeManagement.Services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "name",
        "Iso2",
        "Iso3"
})
public class Countries {

    @JsonProperty("name")
    private String name;

    @JsonProperty("Iso2")
    private String Iso2;

    @JsonProperty("Iso3")
    private String Iso3;
}
