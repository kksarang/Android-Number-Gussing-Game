package com.example.numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast,textViewRight,textViewHint;
    private Button buttonConform;
    private EditText editTextGuss;

    boolean twoDigits,threeDigits,fourDigits;

    Random r = new Random();
    int random;

    int remainingRight = 10;

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


                    int userGuss = Integer.parseInt(guss);

                    textViewLast.setText(guss);

                    textViewRight.setText(remainingRight);

                    if (random == userGuss)
                    {

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

                    }

                    editTextGuss.setText("");

                }


            }
        });

    }
}