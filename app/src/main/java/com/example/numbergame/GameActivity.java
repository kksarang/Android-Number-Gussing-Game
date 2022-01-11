package com.example.numbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast,textViewRight,textViewHint;
    private Button buttonConform;
    private EditText editTextGuss;

    boolean twoDigits,threeDigits,fourDigits;

    Random r = new Random();
    int random;

    int remainingRight = 10;


    ArrayList<Integer> guessList = new ArrayList<>();
    int userAttempts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewLast=findViewById(R.id.textViewLast);
        textViewRight=findViewById(R.id.textView3);

        textViewHint=findViewById(R.id.textViewHint);

        buttonConform=findViewById(R.id.buttonConfirm);

        editTextGuss=findViewById(R.id.editTextGuss);


        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);


        if (twoDigits)
        {
            random = r.nextInt(90)+10;
        }
        if (threeDigits)
        {
            random = r.nextInt(900)+100;
        }
        if (fourDigits)
        {
            random = r.nextInt(9000)+1000;
        }

        buttonConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String guss = editTextGuss.getText().toString();
                if (guss.equals(""))
                {
                    Toast.makeText(GameActivity.this, "please enter a Guss", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    remainingRight--;
                    userAttempts++;



                    int userGuss = Integer.parseInt(guss);

                    guessList.add(userGuss);


                    textViewLast.setText("Your last Guss is :"+guss);

                    textViewRight.setText("Your Remain right is :"+remainingRight);

                    if (random == userGuss)
                    {

                        AlertDialog.Builder builder = new
                                AlertDialog.Builder(GameActivity.this);


                        builder.setTitle("Number Gussing Game");
                        builder.setCancelable(false);

                        builder.setMessage("Congiguration. My guess was "+random +" \n \n You Know My Number In"+
                                userAttempts+" attempts . \n\n Your Guess :"+guessList
                        +"\n\n Would You Like To Play Again ?");


                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                               moveTaskToBack(true);

                               android.os.Process.killProcess(android.os.Process.myPid());

                               System.exit(1);

                            }
                        });

                        builder.create().show();

                    }
                    if (random < userGuss)
                    {
                        textViewHint.setText("Decrease your Guss");

                    }
                    if (random > userGuss)
                    {
                        textViewHint.setText("Increse your Guss");
                    }

                    if (remainingRight == 0)
                    {

                        AlertDialog.Builder builder = new
                                AlertDialog.Builder(GameActivity.this);


                        builder.setTitle("Number Gussing Game");
                        builder.setCancelable(false);

                        builder.setMessage("Sorry Your right to Guss is over "
                                + "\n \n My Guss Was :"+random
                                + "\n\n Your Guess :"+guessList
                                + "\n\n Would You Like To Play Again ?");


                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                moveTaskToBack(true);

                                android.os.Process.killProcess(android.os.Process.myPid());

                                System.exit(1);

                            }
                        });

                        builder.create().show();
                    }

                    editTextGuss.setText("");

                }


            }
        });

    }
}