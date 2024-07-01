package com.example.campus_recruitment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start : Button = findViewById(R.id.start)
            start.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.start ->{
                val moveIntent = Intent(this, MenuActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}