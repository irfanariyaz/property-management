package com.mycompany.propertymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROPERTY_NAME")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String title;
    private  String description;

    private  Double price;
    private  String address;
    @ManyToOne(fetch = FetchType.LAZY)//it will not fetch user data while fetchind properties
    @JoinColumn(name = "USER_ID",nullable = false)
    private UserEntity userEntity;
}
