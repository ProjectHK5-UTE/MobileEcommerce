package com.example.mobileecommerce.retrofit;

import android.content.SharedPreferences;

public class ProfileManager {

    private final SharedPreferences prefs;
   private final SharedPreferences.Editor editor;
   private static ProfileManager INSTANCE  = null;


   public static synchronized ProfileManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new ProfileManager(prefs);
        }
        return INSTANCE;
    }
   private ProfileManager(SharedPreferences prefs) {
       this.prefs = prefs;
       this.editor = prefs.edit();
   }

   public void saveJWT(String JWT) {
       if(JWT == null) return;
       editor.putString("jwt",JWT);
       editor.apply();
   }

   public void removeJWT() {
        editor.remove("jwt");
        editor.apply();
   }

   public String getJWT(){
       String token = prefs.getString("jwt",null);
       return token;
   }
}
