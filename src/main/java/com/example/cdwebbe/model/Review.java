package com.example.cdwebbe.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Đã chuyển int -> Long

//    @NotBlank
//    private int productId;

    @Nullable
    private String content;

//    @NotBlank
//    private int UserId;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;


}
