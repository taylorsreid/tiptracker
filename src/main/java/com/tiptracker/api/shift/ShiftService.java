package com.tiptracker.api.shift;

import com.tiptracker.api.GenericResponse;
import com.tiptracker.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    public ResponseEntity<String> createShifts(String userId, List<PostShiftRequest> shiftRequestList){
        try{
            shiftRequestList.forEach(postShiftRequest -> {
                Shift shift = new Shift();
                shift.setUser(userRepository.findByUserId(userId));
                shift.setJobTitle(postShiftRequest.getJobTitle());
                shift.setShiftDate(postShiftRequest.getShiftDate());
                shift.setHoursWorked(postShiftRequest.getHoursWorked());
                shift.setHourlyRate(postShiftRequest.getHourlyRate());
                shift.setTipsEarned(postShiftRequest.getTipsEarned());
                shiftRepository.save(shift);
            });
            return new ResponseEntity<>(GenericResponse.get(true, "Shifts saved."), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(GenericResponse.get(false, "INTERNAL SERVER ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getShifts(String userId, GetShiftRequest getShiftRequest) {

        try{
            List<Shift> shifts = shiftRepository
                    .findByUser_UserIdAndUser_Shifts_ShiftDateGreaterThanEqualAndUser_Shifts_ShiftDateLessThanEqual(
                            userId,
                            getShiftRequest.getStartDate(),
                            getShiftRequest.getEndDate()
                    );
            if (shifts.isEmpty()){
                return new ResponseEntity<>(GenericResponse.get(false, "Nothing found for those search parameters."), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(GenericResponse.get(true, shifts), HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponse.get(false, "INTERNAL SERVER ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
