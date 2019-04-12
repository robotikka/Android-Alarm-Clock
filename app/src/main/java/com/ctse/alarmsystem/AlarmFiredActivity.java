package com.ctse.alarmsystem;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmFiredActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirm;

    private QuizDbHelper dbHelper;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_fired);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

//        String ringtone = getIntent().getExtras().getString("ringtone");
//
//        Uri ringtoneUri = Uri.parse(ringtone);
//
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, ringtoneUri);
//        mediaPlayer.start();

        textViewQuestion = findViewById(R.id.text_view_question);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirm = findViewById(R.id.button_confirm);

        dbHelper = new QuizDbHelper(this);

        setQuestionDetails();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                    checkAnswer();
                } else {
                    Toast.makeText(AlarmFiredActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setQuestionDetails() {
        rbGroup.clearCheck();
        question = dbHelper.getAQuestion();
        textViewQuestion.setText(question.getQuestion());
        rb1.setText(question.getOption1());
        rb2.setText(question.getOption2());
        rb3.setText(question.getOption3());
        rb4.setText(question.getOption4());
    }

    private void checkAnswer() {

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answer = rbGroup.indexOfChild(rbSelected) + 1;

        if (answer == question.getAnswer()) {
            finishActivity();
        } else {
            Toast.makeText(this, "Wrong Answer. Please Try Again.", Toast.LENGTH_SHORT).show();
            setQuestionDetails();
        }

    }

    private void finishActivity() {
        finish();
    }
}
