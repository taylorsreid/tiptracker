package com.tiptracker.api.user;

import com.tiptracker.api.job.JobModel;
import com.tiptracker.api.shift.Shift;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

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

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<Shift> shifts;

    @Nullable
    @OneToMany(mappedBy = "userId")
    private List<JobModel> jobModels;

}
