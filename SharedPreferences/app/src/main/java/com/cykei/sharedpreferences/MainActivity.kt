package com.cykei.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shared = PreferenceManager.getDefaultSharedPreferences(this)
        val checkBoxValue = shared.getBoolean("key_add_shortcut", false)
        val switchValue = shared.getBoolean("key_switch_on",false)
        val name = shared.getString("key_edit_name","")
        val selected = shared.getString("key_set_item","")

        Log.d("preferences","cb: ${checkBoxValue}, sw: ${switchValue}, name: ${name}, selected: ${selected}")
    }
}