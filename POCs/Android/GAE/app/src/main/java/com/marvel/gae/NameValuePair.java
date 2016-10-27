package com.marvel.gae;

/**
 * Created by sujsudha on 01/10/16.
 */
public class NameValuePair {
    private String name;
    private String value;
    NameValuePair(String name, String value){
        this.name = name;
        this.value = value;
    }
    public String getValue(){
      return value;
    }

    public String getName(){
        return name;
    }
}
