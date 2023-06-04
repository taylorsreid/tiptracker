package com.tiptracker.api.shift;

import com.tiptracker.api.GenericResponse;
import com.tiptracker.api.user.User;
import com.tiptracker.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

            //new list to contain complete shift objects, preallocated size to match that of the shift requests to avoid resizing
            List<Shift> shiftList = new ArrayList<>(shiftRequestList.size());

            //get the requesting user one time outside the loop instead of making multiple calls to the db inside the loop
            User requestingUser = userRepository.findByUserId(userId);

            //turn each shift request into a complete shift object by adding the user
            shiftRequestList.forEach(postShiftRequest -> {
                Shift shiftToAdd = new Shift();
                shiftToAdd.setUser(requestingUser);
                shiftToAdd.setJobTitle(postShiftRequest.getJobTitle());
                shiftToAdd.setShiftDate(postShiftRequest.getShiftDate());
                shiftToAdd.setHoursWorked(postShiftRequest.getHoursWorked());
                shiftToAdd.setHourlyRate(postShiftRequest.getHourlyRate());
                shiftToAdd.setTipsEarned(postShiftRequest.getTipsEarned());
                shiftList.add(shiftToAdd);
            });

            //saves a batch instead of making multiple requests inside the loop
            shiftRepository.saveAll(shiftList);
            return ResponseEntity.ok(new GenericResponse(true, "Shifts saved.").toString());
        }
        catch (Exception e){
            return new ResponseEntity<>(new GenericResponse(false, "INTERNAL SERVER ERROR").toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getShifts(String userId, GetShiftRequest getShiftRequest) {

        try{
            List<Shift> shifts = shiftRepository
                    .findShiftsByDateRange(
                            userId,
                            getShiftRequest.getStartDate(),
                            getShiftRequest.getEndDate()
                    );
            if (shifts.isEmpty()){
                return ResponseEntity.ok(new GenericResponse(false, "Request ok but nothing found for those search parameters.").toString());
            }
            else {
                return ResponseEntity.ok(new GenericResponse(true, shifts).toString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new GenericResponse(false, "INTERNAL SERVER ERROR").toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
