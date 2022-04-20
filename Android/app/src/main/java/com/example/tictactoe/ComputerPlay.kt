package com.example.tictactoe

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ComputerPlay : AppCompatActivity() {
    var currentPlayer = 0
    var gameInProgress = true
    var gameState = arrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
    var winningPositions = arrayOf(
        arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8),
        arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8),
        arrayOf(0, 4, 8), arrayOf(2, 4, 6))
    var winner = "X"
    var pressedButtonNumber: Int? = 1
    var randomNumber: Int = -1


    fun something(view: View){
        val ivClicked = view as ImageView
        ivClicked.alpha = 1.0f
        ivClicked.visibility = View.VISIBLE

        val clickedImageView = ivClicked.tag.toString().toInt()
        if (currentPlayer == 0) {
            ivClicked.setImageResource(R.drawable.o)
            currentPlayer = 1
        } else {
            checkDanger()
            currentPlayer = 0
        }


        if (gameState[clickedImageView] == -1 && gameInProgress) {
            gameState[clickedImageView] = currentPlayer
            for (winningPosition in winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != -1) {
                    gameInProgress = false
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "0"
                    }
                    val gameOverWindow = findViewById<TextView>(R.id.tvMessage)
                    gameOverWindow.text = "$winner ir uzvarētājs!"
                    val layout = findViewById<LinearLayout>(R.id.restartLayout)
                    layout.visibility = View.VISIBLE
                } else {
                    var gameOver = true
                    for (currentPlayer in gameState) {
                        if (currentPlayer == -1) {
                            gameOver = false
                        }
                    }
                    if (gameOver) {
                        val tvMessage = findViewById<TextView>(R.id.tvMessage)
                        tvMessage.text = "neizšķirts!"
                        val layout = findViewById<LinearLayout>(R.id.restartLayout)
                        layout.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
    fun checkDanger(){
        when(true) {
            gameState[1]==0 && gameState[2]==0 || gameState[3]==0 && gameState[6]==0 || gameState[4]==0 && gameState[8]==0 -> gameState[0]=1
            gameState[0]==0 && gameState[2]==0 || gameState[4]==0 && gameState[7]==0 -> gameState[1]=1
            gameState[0]==0 && gameState[1]==0 || gameState[5]==0 && gameState[8]==0 || gameState[6]==0 && gameState[4]==0 -> gameState[2]=1
            gameState[0]==0 && gameState[6]==0 || gameState[4]==0 && gameState[5]==0 -> gameState[3]=1
            gameState[3]==0 && gameState[5]==0 || gameState[1]==0 && gameState[7]==0 || gameState[6]==0 && gameState[2]==0 || gameState[0]==0 && gameState[8]==0 -> gameState[4]=1
            gameState[2]==0 && gameState[8]==0 || gameState[3]==0 && gameState[4]==0 -> gameState[5]=1
            gameState[0]==0 && gameState[3]==0 || gameState[7]==0 && gameState[8]==0 || gameState[2]==0 && gameState[4]==0 -> gameState[6]=1
            gameState[6]==0 && gameState[8]==0 || gameState[1]==0 && gameState[4]==0 -> gameState[7]=1
            gameState[6]==0 && gameState[7]==0 || gameState[2]==0 && gameState[5]==0 || gameState[0]==0 && gameState[4]==0 -> gameState[8]=1
            else -> {
                for (i in 0..8) {
                    randomNumber = (0..8).random()
                    gameState[randomNumber]=1
                }
            }
        }
    }
}