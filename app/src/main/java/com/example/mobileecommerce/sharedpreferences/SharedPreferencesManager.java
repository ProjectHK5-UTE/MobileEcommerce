package com.example.mobileecommerce.sharedpreferences;

import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private final SharedPreferences prefs;
   private final SharedPreferences.Editor editor;
   private static SharedPreferencesManager INSTANCE  = null;


   public static synchronized SharedPreferencesManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new SharedPreferencesManager(prefs);
        }
        return INSTANCE;
    }
   private SharedPreferencesManager(SharedPreferences prefs) {
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

    public void saveEmail(String email) {
        if(email == null) return;
        editor.putString("email",email);
        editor.apply();
    }

    public void removeEmail() {
        editor.remove("email");
        editor.apply();
    }

    public String getEmail(){
        String email = prefs.getString("email",null);
        return email;
    }
    public void saveUsername(String Username) {
        if(Username == null) return;
        editor.putString("Username",Username);
        editor.apply();
    }

    public void removeUsername() {
        editor.remove("Username");
        editor.apply();
    }

    public String getUsername(){
        String email = prefs.getString("Username",null);
        return email;
    }
}
