package com.example.campus_recruitment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var about : Button = findViewById(R.id.id_about)
        about.setOnClickListener(this)
        var dataset : Button = findViewById(R.id.id_dataset)
        dataset.setOnClickListener(this)
        var fitur : Button = findViewById(R.id.id_fitur)
        fitur.setOnClickListener(this)
        var model : Button = findViewById(R.id.id_model)
        model.setOnClickListener(this)
        var simulasi : Button = findViewById(R.id.id_simulasi)
        simulasi.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.id_simulasi ->{
                val moveIntent = Intent(this, SimulasiModelActivity::class.java)
                startActivity(moveIntent)
            }
        }

        when(v?.id){
            R.id.id_about ->{
                val moveIntent = Intent(this, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }

        when(v?.id){
            R.id.id_dataset ->{
                val moveIntent = Intent(this, DatasetActivity::class.java)
                startActivity(moveIntent)
            }
        }

        when(v?.id){
            R.id.id_fitur ->{
                val moveIntent = Intent(this, FiturActivity::class.java)
                startActivity(moveIntent)
            }
        }

        when(v?.id){
            R.id.id_model ->{
                val moveIntent = Intent(this, ModelActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}