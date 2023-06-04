package com.tiptracker.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tiptracker.api.shift.Shift;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@NoArgsConstructor
@ResponseBody
public class GenericResponse {

    @Setter
    private boolean success;
    private StringBuffer stringBufferBody = new StringBuffer();
    private List<Shift> shiftBody;

    public GenericResponse(boolean success, String stringBody){
        this.success = success;
        stringBufferBody.append(stringBody);
    }

    public GenericResponse(boolean success, List<Shift> shiftBody){
        this.success = success;
        this.shiftBody = shiftBody;
    }

    public void appendBody(String newMessage){
        stringBufferBody.append(newMessage);
    }

    @Override
    public String toString() {
       if(shiftBody != null){
           ObjectMapper mapper = new ObjectMapper();
           mapper.registerModule(new JavaTimeModule());
           try {
               return "{\"success\":" + success + "," +
                       "\"body\":" + mapper.writeValueAsString(shiftBody) + "}";
           } catch (JsonProcessingException e) {
               return "{\"success\":" + success + "," +
                       "\"body\": \" 500 INTERNAL SERVER ERROR \"}";
           }
       }
       else {
           return "{\"success\":" + success + "," +
                   "\"body\": \"" + stringBufferBody + "\"}";
       }
    }

}
