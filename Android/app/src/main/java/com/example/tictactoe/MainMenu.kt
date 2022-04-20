package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.system.exitProcess


class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val modePvP = findViewById<Button>(R.id.modePvP)
        modePvP.setOnClickListener() {
            val intent = Intent(this@MainMenu, GamePlay::class.java)
            intent.putExtra("mode", 0)
            startActivity(intent)
        }
        val modePvC = findViewById<Button>(R.id.modePvC)
        modePvC.setOnClickListener() {
            val intent = Intent(this@MainMenu, GamePlay::class.java)
            intent.putExtra("mode", 1)
            startActivity(intent)
        }
        val userName = findViewById<TextInputEditText>(R.id.vardsId)
        val textInput1 = intent.getStringExtra("userName")
        userName.setOnClickListener() {
            val textInput = userName.text.toString().trim();
            val intent1 = Intent(this@MainMenu, GamePlay::class.java)
            intent1.putExtra("userName", textInput)
            startActivity(intent1)
        }

        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener{
            exitProcess(2)
        }
    }



}