package com.example.im2017.signansmaster;

/**
 * Created by admin on 6/28/2018.
 */

public class signansMaster {
    String id;
   String mode;
   String language;

   public signansMaster(){

   }

    public signansMaster(String id, String mode, String language) {
        this.id = id;
        this.mode = mode;
        this.language = language;
    }


    public String getMode() {
        return mode;
    }

    public String getLanguage() {
        return language;
    }
}
