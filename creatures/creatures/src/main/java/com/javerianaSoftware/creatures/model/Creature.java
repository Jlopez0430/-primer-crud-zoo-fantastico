package com.javerianaSoftware.creatures.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Entity
@Data // Esto es opcional pueden usarlo o crear los getter y setters
@NoArgsConstructor
public class Creature {

    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private double size;
    private int dangerLevel;
    private String healthStatus;

    //    @ManyToOne
//    @JoinColumn(name = "zone_id")
//    private Zone zone;
}
