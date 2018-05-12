package com.forkbuddy.forkbuddy;

import android.net.Uri;

public class ForkHistory {
    String nameSurname;
    Uri profilePicture;
    String foodType;

    public ForkHistory(String nameSurname, Uri profilePicture, String foodType){
        this.nameSurname = nameSurname;
        this.profilePicture = profilePicture;
        this.foodType = foodType;
    }

    public String getNameSurname(){
        return nameSurname;
    }

    public Uri getProfilePicture(){
        return profilePicture;
    }

    public String getFoodType(){
        return foodType;
    }
}

