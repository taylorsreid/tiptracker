package com.tiptracker.api.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tiptracker.api.job.Job;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class User {

    @Id
    @NotBlank
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String hashedPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderColumn
    private ArrayList<Job> jobs;

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {

        this.jobs = jobs;

    }

    @Transient //makes it not persist
    public String getNewToken(){

        Dotenv env = Dotenv.load();
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(env.get("PRIVATE_KEY")));

        return JWT.create()
                .withSubject(getUserId())
                .withIssuer(env.get("KEY_ISSUER"))
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(algorithm);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * <p>Sets a HASHED password only.</p>
     * @param plainTextPassword the password in plaintext
     */
    public void setHashedPassword(String plainTextPassword) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.hashedPassword = passwordEncoder.encode(plainTextPassword);
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}