package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.Gender;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "profiles")
@Entity
public class Profile{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_gen")
    @SequenceGenerator(name ="profile_gen", sequenceName = "profile_seq", allocationSize = 1)
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String biography;

    public Profile(String fullName, LocalDate dateOfBirth, Gender gender, String biography) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.biography = biography;
    }
}
