package com.tiptracker.api.shift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @ResponseBody
    public ResponseEntity<String> createShifts(String userId, List<PostShiftRequest> shiftRequestList){
        try{
            User userToSave = userRepository.findByUserId(userId);
            for (PostShiftRequest shiftRequest : shiftRequestList) {
                shiftRepository.save(new Shift(userToSave, shiftRequest));
            }
            return new ResponseEntity<>(
                    "{\"success\": \"true\"," +
                            "\"message\": \"Shifts saved.\"}",
                        HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "{\"success\": \"false\"," +
                            "\"message\": \"Internal server error.\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getShifts(String userId, GetShiftRequest getShiftRequest) {
        try{
            ArrayList<Shift> shifts = shiftRepository
                    .findByUser_UserIdAndUser_Shifts_ShiftDateGreaterThanEqualAndUser_Shifts_ShiftDateLessThanEqual(
                            userId,
                            getShiftRequest.getStartDate(),
                            getShiftRequest.getEndDate()
                    );

            StringBuffer shiftsAsStringBuffer = new StringBuffer();

            shifts.forEach(shift -> {
                try {
                    shiftsAsStringBuffer.append(ow.writeValueAsString(shift)).append(",");
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

            return new ResponseEntity<>(
                    "{\"success\": \"true\"," +
                            "\"message\":" + shiftsAsStringBuffer + "}",
                    HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "{\"success\": \"false\"," +
                            "\"message\": \"Nothing found.\"}",
                    HttpStatus.NO_CONTENT);
        }
    }
}
