package com.live.tasknew.viewmodel

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.live.tasknew.ImageResponseClass


object SharedPreferencesUtil {
    private const val PREFS_NAME = "my_prefs"
    private const val KEY_IMAGE_RESPONSE = "image_response"

    fun saveImageResponse(context: Context, imageResponse: ImageResponseClass) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(imageResponse)
        editor.putString(KEY_IMAGE_RESPONSE, json)
        editor.apply()
    }

    fun getImageResponse(context: Context): ImageResponseClass? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString(KEY_IMAGE_RESPONSE, null)
        val type = object : TypeToken<ImageResponseClass>() {}.type
        return gson.fromJson(json, type)
    }


    fun clearSharedPreferences(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}

