package com.tiptracker.api;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class GenericResponseBody {
    private boolean success;
    private String message;
    private Object body;

   public GenericResponseBody(boolean success, String message, Object body){
       this.success = success;
       this.message = message;
       this.body = body;
   }

}
