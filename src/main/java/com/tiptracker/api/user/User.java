package com.tiptracker.api.user;

import com.tiptracker.api.job.Job;
import com.tiptracker.api.shift.Shift;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String hashedPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToMany(mappedBy = "user")
    private ArrayList<Shift> shifts;

    @OneToMany(mappedBy = "user")
    private ArrayList<Job> jobs;

}
