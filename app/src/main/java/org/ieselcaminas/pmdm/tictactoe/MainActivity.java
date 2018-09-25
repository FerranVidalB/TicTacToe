package org.ieselcaminas.pmdm.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int NUM_COLROW = 3;
    private Button[][] buttons;
    //private int nplayer;
    private int turns;
    private boolean gameover;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private TextView winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setColumnCount(3);
        player1 = new Player("Pepe","X");
        player2 = new Player("Popo","0");

        createButtons(gridLayout);
        winner = findViewById(R.id.tvWinner);

        generateCurrentPlayer();



        turns=0;
        gameover=false;



        Button reset=findViewById(R.id.bReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });

    }
    private void restart(){
        turns =0;

        generateCurrentPlayer();

        gameover=false;
        for (int x = 0; x < NUM_COLROW; x++) {

            for (int y = 0; y < NUM_COLROW; y++) {
                buttons[x][y].setText(" ");
                buttons[x][y].setEnabled(true);
            }
        }
    }

    private void generateCurrentPlayer() {
        if(((int)(Math.random()*2)+1)==1){
            currentPlayer=player1;
        }else{
            currentPlayer=player2;
        }
        winner.setText(currentPlayer.name+" turn");
    }

    private void createButtons(GridLayout gridLayout) {
        buttons = new Button[NUM_COLROW][NUM_COLROW];


        for (int x = 0; x < NUM_COLROW; x++) {

            for (int y = 0; y < NUM_COLROW; y++) {
                buttons[x][y] = new Button(this, null);
                buttons[x][y].setText(" ");


                gridLayout.addView(buttons[x][y]);


                buttons[x][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!gameover) {
                            v.setEnabled(false);
                            turns++;
                            Button button = (Button) v;
                            turns++;

                                button.setText(currentPlayer.symbol);
                               
                                if(check(currentPlayer.symbol)){
                                    winner.setText(currentPlayer.name+" win");
                                    gameover=true;
                                }




                                if(!gameover){
                                    if(currentPlayer==player1) {
                                        currentPlayer = player2;
                                    }else{
                                        currentPlayer=player1;
                                    }
                                    winner.setText(currentPlayer.name+" turn");
                                }


                            if(turns==9){
                                gameover=true;
                            }

                        }
                    }
                });

            }
        }
    }
    private boolean check(String player){

        int count =0;

        for (int i = 0; i < NUM_COLROW; i++){
            for (int j = 0; j < NUM_COLROW; j++){
                if (buttons[i][j].getText().toString().equals(player)){
                    count++;
                }
            }
            if (count==3){
                return true;
            }
            count=0;
        }
        for (int i = 0; i < NUM_COLROW; i++){
            for (int j = 0; j < NUM_COLROW; j++){
                if (buttons[j][i].getText().toString().equals(player)){
                    count++;
                }
            }
            if (count==3){
                return true;
            }
            count=0;
        }

        for (int i=0; i<NUM_COLROW; i++){
            if (buttons[i][i].getText().toString().equals(player)) {
                count++;
            }
        }
        if (count ==3){
            return true;
        }
        count=0;
        for (int i=NUM_COLROW-1; i>=0; i--){
            if (buttons[i][NUM_COLROW-i-1].getText().toString().equals(player)) {
                count++;
            }
        }
        if (count ==3){
            return true;
        }


        return false;

    }
}
