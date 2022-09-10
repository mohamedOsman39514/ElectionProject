package com.example.elections.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reset_password")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = Voter.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Voter voter;

    @Column
    private Date expiryDate;
}
