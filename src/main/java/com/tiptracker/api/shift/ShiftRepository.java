package com.tiptracker.api.shift;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called shiftRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Integer> {

    @Query(value =
            """
            SELECT * FROM shift where shift.user_id = :userId AND shift.shift_date >= :startDate AND shift.shift_date <= :endDate
            """,
            nativeQuery = true
            )
    List<Shift> findShiftsByDateRange(String userId, LocalDate startDate, LocalDate endDate);

}
