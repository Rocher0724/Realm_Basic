package com.example.visualcamp.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

  TextView tv;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_answer);

    tv = (TextView) findViewById(R.id.tv);

    Intent intent = getIntent();
    String answer = intent.getExtras().getString("answer");
    if (answer != null) {
      tv.setText(answer);
    }
  }
}
