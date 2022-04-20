package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_screen)

        val pressToPlay = findViewById<ImageButton>(R.id.pressToPlay)
        pressToPlay.setOnClickListener() {
            val intent = Intent(this@MainActivity, MainMenu::class.java)
            startActivity(intent)
        }

    }


}