package pfa.dev.recruitmentservice.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    private Long interviewerId; // Employee ID from Employee Service
    private LocalDateTime interviewDate;
    private String type; // HR, TECHNICAL, MANAGERIAL
    private String status; // PLANNED, DONE, CANCELED
    private String feedback;
    private Integer rating;

    // getters and setters
}