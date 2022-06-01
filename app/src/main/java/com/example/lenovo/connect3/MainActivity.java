package com.example.lenovo.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // deriv 0: yellow, deriv 1: red, [deriv = identitiy of active color in a place]
    int deriv = 0;
    boolean gameActive =true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions ={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        Log.i("tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = deriv;


            if (deriv == 0) {
                deriv = 1;

                counter.setTranslationY(-1500);
                counter.setImageResource(R.drawable.yellow1);
                counter.animate().translationYBy(1500).setDuration(200);

            } else if (deriv == 1) {

                deriv = 0;
                counter.setTranslationY(-1500);
                counter.setImageResource(R.drawable.red1);
                counter.animate().translationYBy(1500).setDuration(200);


            }
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2 && gameState[winningPosition[1]] != 2 && gameState[winningPosition[2]] != 2) {
                    // someone has won

                    gameActive = false;
                    String winner = "";

                    if (deriv == 0) {
                        winner = "Red";
                    } else if (deriv == 1) {
                        winner = "Yellow";

                    }

                    Toast.makeText(this,  " Game Over", Toast.LENGTH_SHORT).show();

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    winnerTextView.setText(winner+ " has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);

                }else if( gameState[0] != 2 && gameState[1] != 2 &&gameState[2] != 2 &&gameState[3] != 2 &&gameState[4] != 2 &&gameState[5] != 2 &&gameState[6] != 2 &&gameState[7] != 2 &&gameState[8] != 2 &&gameState[winningPosition[0]] != gameState[winningPosition[1]] && gameState[winningPosition[1]] != gameState[winningPosition[2]] ){
                    // None has won
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    winnerTextView.setText(" None has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view){
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i< gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view

            counter.setImageDrawable(null);

        }


        for(int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }


        deriv = 0;

        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
