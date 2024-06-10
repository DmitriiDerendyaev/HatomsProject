package com.example.tinkhatoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tinkhatoms.bottomnav.levels.LevelsFragment;

public class game extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView scoreTextView = findViewById(R.id.scoretextView);
        TextView countTextView = findViewById(R.id.counttextView);
        ImageButton backBtn = findViewById(R.id.exitBtn);
        // Устанавливаем выравнивание по центру
        scoreTextView.setGravity(Gravity.CENTER);
        countTextView.setGravity(Gravity.CENTER);

        // Получение значения score из Intent
        int receivedScore = getIntent().getIntExtra("score", 0);
        int receivedcount_true = getIntent().getIntExtra("count_true", 1);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // записываем на экране
        scoreTextView.setText("+ " + String.valueOf(receivedScore));
        countTextView.setText(String.valueOf(receivedcount_true) + " / 16");
    }
}
