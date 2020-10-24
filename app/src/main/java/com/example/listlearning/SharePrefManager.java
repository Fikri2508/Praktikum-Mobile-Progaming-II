package com.example.listlearning;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharePrefManager(Context context){
        sharedPreferences = context.getSharedPreferences("Latihan Share Preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveString(String value){
        editor.putString("ini_string", value);
        editor.commit();
    }
    public  String getString(){
        return sharedPreferences.getString("ini_string", "");
    }


}
