package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private  String ownerName;
    private  Long Id;
    @NotNull(message = "Owner Email is mandatory")
    @Size(min = 1,max = 50,message = "Owner email should be between 1 and 50")
    @NotEmpty(message = "Owner Email cannot be empty")
    private  String ownerEmail;
    private  String phone;
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private  String password;
    private  String street;
    private  String city;
    private  String country;
    private  String postalCode;
    private  String houseNo;
}
