package com.sudedenizsuvar.kitabimcebimde

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityBooksdetailpageBinding

class MySharedPreferences (private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
    }
    private val gson = Gson()

    fun addDataToList(listName: String, data: String) {
        val list = getDataList(listName)
        list.add(data)
        saveDataList(listName, list)
    }

    fun getDataList(listName: String): MutableList<String> {
        val json = sharedPreferences.getString(listName, null)
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    fun saveDataList(listName: String, list: List<String>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(list)
        editor.putString(listName, json)
        editor.apply()
    }

    fun removeData(listName: String) {
        val editor = sharedPreferences.edit()
        editor.remove(listName)
        editor.apply()
    }

}