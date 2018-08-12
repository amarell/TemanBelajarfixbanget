package com.dark.amarel.temanbelajar;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String NAME="NAME";
    public static final String EMAIL="EMAIL";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences= context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

    }

    public void createSession(String nama, String email){
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, nama);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }
    public void cekLogin(){

    }
}
