package com.example.quizapp.UI.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
//    btn_state(questions);

//        @SuppressLint("ResourceAsColor")
//        public void reset() {
//            first.setBackgroundResource(R.drawable.button_rounded_quiz);
//            second.setBackgroundResource(R.drawable.button_rounded_quiz);
//            third.setBackgroundResource(R.drawable.button_rounded_quiz);
//            fourth.setBackgroundResource(R.drawable.button_rounded_quiz);
//            b_true.setBackgroundResource(R.drawable.button_rounded_quiz);
//            b_false.setBackgroundResource(R.drawable.button_rounded_quiz);
//
//            first.setTextColor(itemView.getResources().getColor(R.color.Black));
//            second.setTextColor(itemView.getResources().getColor(R.color.Black));
//            third.setTextColor(itemView.getResources().getColor(R.color.Black));
//            fourth.setTextColor(itemView.getResources().getColor(R.color.Black));
//            b_true.setTextColor(itemView.getResources().getColor(R.color.Black));
//            b_false.setTextColor(itemView.getResources().getColor(R.color.Black));
//        }



//        @SuppressLint("ResourceAsColor")
//        public void btn_state(Questions questions) {
//            if (questions.getSelectAnswerPosition() != null) {
//                switch (questions.getSelectAnswerPosition()) {
//                    case 0:
//                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(0))) {
//                            first.setBackgroundResource(R.color.Green);
//                            b_true.setBackgroundResource(R.color.Green);
//                            first.setTextColor(R.color.White);
//                            b_true.setTextColor(R.color.White);
//                        } else {
//                            blueState(questions);
//                            first.setBackgroundResource(R.color.Red);
//                            b_true.setBackgroundResource(R.color.Red);
//                            first.setTextColor(R.color.White);
//                            b_true.setTextColor(R.color.White);
//
//                        }
//                        break;
//                    case 1:
//                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(1))) {
//                            second.setBackgroundResource(R.color.Green);
//                            b_false.setBackgroundResource(R.color.Green);
//                            second.setTextColor(R.color.White);
//                            b_false.setTextColor(R.color.White);
//                        } else {
//                            blueState(questions);
//                            second.setBackgroundResource(R.color.Red);
//                            b_false.setBackgroundResource(R.color.Red);
//                            second.setTextColor(R.color.White);
//                            b_false.setTextColor(R.color.White);
//
//                        }
//                        break;
//                    case 2:
//                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(2))) {
//                            third.setBackgroundResource(R.color.Green);
//                            third.setTextColor(R.color.White);
//
//                        } else {
//                            blueState(questions);
//                            third.setBackgroundResource(R.color.Red);
//                            third.setTextColor(R.color.White);
//
//                        }
//                        break;
//                    case 3:
//                        if (questions.getCorrectAnswers().equals(questions.getAnswers().get(3))) {
//                            fourth.setBackgroundResource(R.color.Green);
//                            fourth.setTextColor(R.color.White);
//                        } else {
//                            blueState(questions);
//                            fourth.setBackgroundResource(R.color.Red);
//                            fourth.setTextColor(R.color.White);
//
//                        }
//                        break;
//                }
//            }
//        }
//        @SuppressLint("ResourceAsColor")
//        void blueState(Questions questions) {
//            if (questions.getCorrectAnswers().equals(questions.getAnswers().get(0))) {
//                first.setBackgroundResource(R.color.Green);
//                b_true.setBackgroundResource(R.color.Green);
//                first.setTextColor(R.color.White);
//                b_true.setTextColor(R.color.White);
//            } else if (questions.getCorrectAnswers().equals(questions.getAnswers().get(1))) {
//                second.setBackgroundResource(R.color.Green);
//                b_false.setBackgroundResource(R.color.Green);
//                second.setTextColor(R.color.White);
//                b_false.setTextColor(R.color.White);
//            } else if (questions.getCorrectAnswers().equals(questions.getAnswers().get(2))) {
//                third.setBackgroundResource(R.color.Green);
//                third.setTextColor(R.color.White);
//            } else {
//                fourth.setBackgroundResource(R.color.Green);
//                fourth.setTextColor(R.color.White);
//            }
//        }
//    }