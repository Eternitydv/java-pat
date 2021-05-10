package com.example.Practice17;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Departures")
@Getter
@Setter
@RequiredArgsConstructor
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String type;
    @Column
    private String departureDate;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="postOffice_id")
    public PostOffice postOffice;


}
