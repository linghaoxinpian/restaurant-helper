package linghaoxinpian.restaurant.utils;

import android.content.Context;
import android.content.SharedPreferences;

//封装 SharedPreferences
public class PrefUtils {
    public static boolean getBoolean(Context ctx,String key,boolean defValue){
        SharedPreferences sp= ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    public static String getString(Context ctx,String key,String defValue){
        SharedPreferences sp= ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    public static int getInt(Context ctx,String key,int defValue){
        SharedPreferences sp= ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }

    public static  void setInt(Context ctx,String key,int value){
        SharedPreferences sp =ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static  void setBoolean(Context ctx,String key,boolean value){
        SharedPreferences sp =ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static  void setString(Context ctx,String key,String value){
        SharedPreferences sp =ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
