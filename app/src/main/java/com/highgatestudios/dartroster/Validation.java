package com.highgatestudios.dartroster;

/**
 * Created by JBrien on 8/15/2017.
 */

public class Validation {
    public String ValidatePlayerName(String name){
        if(name == null || name.length() == 0)
            return "Name cannot be empty";

        if(name.length() >= 25)
            return "Name must be less than 25 characters";

        return null;
    }
}
