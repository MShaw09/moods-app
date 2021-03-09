/*
No Edits are needed in Main Activity Kotlin file,
however, changes are still needed in activity_main.xml
 */
package com.android.example.moods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}