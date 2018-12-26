package com.dark.amarel.temanbelajar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String NAME="NAME";
    public static final String EMAIL="EMAIL";
    public static final String ID_MURID="ID_MURID";
    public static final String TOKEN="TOKEN";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences= context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }

    public void createSession(String nama, String email, String id, String token){
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, nama);
        editor.putString(EMAIL, email);
        editor.putString(ID_MURID, id);
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }
    public void cekLogin(){
        if(!this.isLogin()){
            Intent intent = new Intent(context, login_murid.class);
            context.startActivity(intent);
            ((Home) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String > user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID_MURID, sharedPreferences.getString(ID_MURID, null));
        user.put(TOKEN, sharedPreferences.getString(TOKEN, null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, login_murid.class);
        context.startActivity(intent);
        ((Dashboard) context).finish();
    }
}
