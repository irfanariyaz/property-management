package com.mycompany.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String street;
    private  String city;
    private  String country;
    private  String postalCode;
    private  String houseNo;
    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private  UserEntity userEntity;
}
