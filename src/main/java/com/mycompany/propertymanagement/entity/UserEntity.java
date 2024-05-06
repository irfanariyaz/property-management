package com.mycompany.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String ownerName;
    @Column(nullable = false, name = "EMAIL")
    private  String ownerEmail;
    private  String phone;
    private  String password;
//    @OneToOne(mappedBy = "userEntity",fetch = FetchType.LAZY)
//    private Address address;

}
