package com.tiptracker.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tiptracker.api.shift.Shift;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@ResponseBody
public class GenericResponse {

    //for getting string messages
   public static String get(boolean success, String message){
       return "{\"success\": \"" + success + "\"," +
               "\"message\": \"" + message + "\"}";
   }

    //for getting list of shift messages
    public static String get(boolean success, List<Shift> list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return "{\"success\":" + success + "," +
                "\"message\":" + mapper.writeValueAsString(list) + "}";
    }

//   //for getting list messages
//    public static String get(boolean success, List<?> list) throws JsonProcessingException {
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        return "{\"success\":" + success + "," +
//                "\"message\":" + ow.writeValueAsString(list) + "}";
//    }

}
