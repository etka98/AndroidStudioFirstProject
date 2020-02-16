package com.example.tiktaktooo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean finishGame = false;
    int playerTurn = 0;
    int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4 ,7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] arr = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    public void moveDown(View view){
        ImageView selectedOne = (ImageView) view;
        int flag = Integer.parseInt(view.getTag().toString());

        if(arr[flag] == -1 && finishGame == false){
            selectedOne.setTranslationY(-1500);
            if(playerTurn == 0){
                selectedOne.setImageResource(R.drawable.yellow);
                arr[flag] = playerTurn;
                if(checkWin()){
                    Toast.makeText(this, "yellow win", Toast.LENGTH_LONG).show();
                }
                playerTurn = 1;
            }
            else{
                selectedOne.setImageResource(R.drawable.red);
                arr[flag] = playerTurn;
                if(checkWin()){
                    Toast.makeText(this, "red win", Toast.LENGTH_LONG).show();
                }
                playerTurn = 0;
            }
            selectedOne.animate().translationYBy(1500).setDuration(300);
        }

        if(finishGame){
            Button play = (Button)findViewById(R.id.playAgainButton);
            TextView text = (TextView) findViewById(R.id.textView);
            play.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);

        }

    }

    public boolean checkWin(){
        for(int i = 0; i < win.length; i++){
                if((arr[win[i][0]] == arr[win[i][1]]) && (arr[win[i][1]] == arr[win[i][2]]) && (arr[win[i][1]] == playerTurn)){
                    finishGame = true;
                    return true;
                }
        }
        return false;
    }

    public void playAgain(View view){
        Button play = (Button)findViewById(R.id.playAgainButton);
        TextView text = (TextView) findViewById(R.id.textView);
        play.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < grid.getChildCount(); i++) {
            ImageView selectedOne = (ImageView) grid.getChildAt(i);

            selectedOne.setImageDrawable(null);
        }
        finishGame = false;
        playerTurn = 0;
        for(int i = 0; i < arr.length; i++){
            arr[i] = -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
