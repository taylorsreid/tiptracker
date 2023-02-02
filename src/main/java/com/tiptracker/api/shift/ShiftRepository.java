package com.tiptracker.api.shift;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Integer> {
    @Query("""
            select s from Shift s inner join s.user.shifts shifts
            where s.user.userId = ?1 and shifts.shiftDate >= ?2 and shifts.shiftDate <= ?3""")
    List<Shift> findByUser_UserIdAndUser_Shifts_ShiftDateGreaterThanEqualAndUser_Shifts_ShiftDateLessThanEqual(String userId, LocalDate shiftDate, LocalDate shiftDate1);
    
}
