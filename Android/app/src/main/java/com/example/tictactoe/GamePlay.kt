package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
// import kotlinx.android.synthetic.main.activity_main.*


class GamePlay : AppCompatActivity() {
    var currentPlayer = 0
    var gameInProgress = true
    var gameState = arrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
    var winningPositions = arrayOf(
        arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8),
        arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8),
        arrayOf(0, 4, 8), arrayOf(2, 4, 6))
    var winner = "X"
    var pressedButtonNumber: Int? = 1
//↓ Lietas, kuras vēl nav implementētas, bet plānoju. ↓
//    var userNameText: String = ""
//    var randomNumber: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pressedButtonNumber = getIntent().getExtras()?.getInt("mode")

        val mainMenu = findViewById<Button>(R.id.mainMenu)
        mainMenu.setOnClickListener() {
            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }


    }

    fun play(view: View) {

            val ivClicked = view as ImageView
            ivClicked.alpha = 1.0f
            ivClicked.visibility = View.VISIBLE
            val clickedImageView = ivClicked.tag.toString().toInt()
            if (gameState[clickedImageView] == -1 && gameInProgress) {
                gameState[clickedImageView] = currentPlayer
                if (currentPlayer == 0) {
                    ivClicked.setImageResource(R.drawable.o)
                    currentPlayer = 1
                } else if (pressedButtonNumber==0){
                    ivClicked.setImageResource(R.drawable.x)
                    currentPlayer = 0
                }
//                else {
//                    checkDanger()
//                    currentPlayer = 0
//                }
                ivClicked.visibility = View.VISIBLE
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

    fun playAgain(view: View) {
        winner = "X"
        gameInProgress = true
        currentPlayer = 0
        val layout = findViewById<LinearLayout>(R.id.restartLayout)
        layout.visibility = View.INVISIBLE
        for (i in gameState.indices) {
            gameState[i] = -1
        }
        val gameLayout = findViewById<LinearLayout>(R.id.gameLayout)
        for (i in 0 until gameLayout.childCount) {
            val subView = gameLayout.getChildAt(i)
            if (subView is LinearLayout) {
                for (j in 0 until subView.childCount) {
                    val linearSubView = subView.getChildAt(j)
                    (linearSubView as? ImageView)?.alpha = 0.0f
                }
            }
        }
    }

// Spēlētājs VS Dators režīms vēl nav implementēts


//    fun checkDanger(){
//        when(true) {
//            gameState[1]==0 && gameState[2]==0 || gameState[3]==0 && gameState[6]==0 || gameState[4]==0 && gameState[8]==0 -> gameState[0]=1
//            gameState[0]==0 && gameState[2]==0 || gameState[4]==0 && gameState[7]==0 -> gameState[1]=1
//            gameState[0]==0 && gameState[1]==0 || gameState[5]==0 && gameState[8]==0 || gameState[6]==0 && gameState[4]==0 -> gameState[2]=1
//            gameState[0]==0 && gameState[6]==0 || gameState[4]==0 && gameState[5]==0 -> gameState[3]=1
//            gameState[3]==0 && gameState[5]==0 || gameState[1]==0 && gameState[7]==0 || gameState[6]==0 && gameState[2]==0 || gameState[0]==0 && gameState[8]==0 -> gameState[4]=1
//            gameState[2]==0 && gameState[8]==0 || gameState[3]==0 && gameState[4]==0 -> gameState[5]=1
//            gameState[0]==0 && gameState[3]==0 || gameState[7]==0 && gameState[8]==0 || gameState[2]==0 && gameState[4]==0 -> gameState[6]=1
//            gameState[6]==0 && gameState[8]==0 || gameState[1]==0 && gameState[4]==0 -> gameState[7]=1
//            gameState[6]==0 && gameState[7]==0 || gameState[2]==0 && gameState[5]==0 || gameState[0]==0 && gameState[4]==0 -> gameState[8]=1
//            else -> {
//                for (i in 0..8) {
//                    randomNumber = (0..8).random()
//                    gameState[randomNumber]=1
//                }
//            }
//        }
//    }


}