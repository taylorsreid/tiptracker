package com.tiptracker.api.shift;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiptracker.api.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Shift {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private BigInteger shiftId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Autowired
    private User user;

    private String jobTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate shiftDate;
    private BigDecimal hoursWorked;
    private BigDecimal hourlyRate;
    private BigDecimal tipsEarned;

}
