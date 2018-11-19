package com.virtualfittingroom;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferencesHelper {

	private static SharedPreferences prefs;

	public static void initPrefs(Context con){
		prefs = PreferenceManager.getDefaultSharedPreferences(con );	
		Editor editor = prefs.edit();
		editor.putString("settingsInit", "False");
		editor.putString("servicestatus", "StandBy");
		editor.commit();
	}
	
	public static boolean firstTimeLog()
	 {
		
		return prefs.contains("FirstTimeLog");		
	 }
	
	public static void saveUserPass(String user , String Pass)
	 {
		Editor editor = prefs.edit();
		editor.putString("FirstTimeLog", "true"); 
		editor.putString("user", user); 
		editor.putString("pass", Pass);

		editor.commit();
	 }
	
	public static void saveUserPassGender(String user , String Pass, String Gender)
	 {
		Editor editor = prefs.edit();
		editor.putString("FirstTimeLog", "true"); 
		editor.putString("user", user); 
		editor.putString("pass", Pass);
		editor.putString("gender", Gender);

		editor.commit();
	 }
	
	public static String getSavedUser()
	{
	     return prefs.getString("user", "");
	}
	
	public static String getSavedPass()
	{
		 return prefs.getString("pass", "");
	}
	
	public static String getSavedGender()
	{
		 return prefs.getString("gender", "");
	}

	public static void saveMeasures(String measures) {
		// TODO Auto-generated method stub
		Editor editor = prefs.edit();
		editor.putString("measures", measures); 
		
		editor.commit();
	}
	
	public static String getSavedMeasures()
	{
		 return prefs.getString("measures", "");
	}
	
	public static void saveBrand(String brand) {
		// TODO Auto-generated method stub
		Editor editor = prefs.edit();
		editor.putString("brand", brand); 		
		editor.commit();
	}
	
	public static String getSavedBrand()
	{
		 return prefs.getString("brand", "");
	}
	
	public static void saveFashion(String fashion) {
		// TODO Auto-generated method stub
		Editor editor = prefs.edit();
		editor.putString("fashion", fashion); 		
		editor.commit();
	}
	
	public static String getSavedFashion()
	{
		 return prefs.getString("fashion", "");
	}
	
	public static void saveUpperMeasure(String measure) {
		// TODO Auto-generated method stub
		Editor editor = prefs.edit();
		editor.putString("uppermeasure", measure); 		
		editor.commit();
	}
	
	public static String getSavedUpperMeasure()
	{
		 return prefs.getString("uppermeasure", "");
	}
	
	public static void saveLowerMeasure(String measure) {
		// TODO Auto-generated method stub
		Editor editor = prefs.edit();
		editor.putString("lowermeasure", measure); 		
		editor.commit();
	}
	
	public static String getSavedLowerMeasure()
	{
		 return prefs.getString("lowermeasure", "");
	}
	
	public static void saveSettings(String grabberIP,String loaderIP,String grabberPort,String loaderPort) {
		// TODO Auto-generated method stub
		Editor editor = prefs.edit();
		editor.putString("grabberIP", grabberIP); 	
		editor.putString("loaderIP", loaderIP); 	
		editor.putString("grabberPort", grabberPort); 	
		editor.putString("loaderPort", loaderPort); 		
		editor.commit();
	}
	
	public static void saveSettingsState(String state)
	{
		Editor editor = prefs.edit();
		editor.putString("settingsInit", state);	
		editor.commit();
	}
	
	public static String getSettingsState()
	{
		 return prefs.getString("settingsInit", "");
	}
	
	public static String getSavedGrabberIP()
	{
		 return prefs.getString("grabberIP", "");
	}
	
	public static String getSavedLoaderIP()
	{
		 return prefs.getString("loaderIP", "");
	}
	public static String getSavedGrabberPort()
	{
		 return prefs.getString("grabberPort", "");
	}
	public static String getSavedLoaderPort()
	{
		 return prefs.getString("loaderPort", "");
	}
	public static void saveServiceStatus(String status)
	{
		Editor editor = prefs.edit();
		editor.putString("servicestatus", status);	
		editor.commit();
	}
	public static String getSaveServiceStatus()
	{
		 return prefs.getString("servicestatus", "");
	}
}
