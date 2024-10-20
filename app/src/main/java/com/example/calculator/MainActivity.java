package com.example.calculator;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonAddition, buttonSubtraction, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        buttonC = assignId(R.id.button_c);
        buttonBracketOpen = assignId(R.id.button_open_bracket);
        buttonBracketClose = assignId(R.id.button_close_bracket);
        buttonDivide = assignId(R.id.button_divide);
        buttonMultiply = assignId(R.id.button_multiply);
        buttonAddition = assignId(R.id.button_addition);
        buttonSubtraction = assignId(R.id.button_subtraction);
        buttonEquals = assignId(R.id.button_equals);
        button0 = assignId(R.id.button_0);
        button1 = assignId(R.id.button_1);
        button2 = assignId(R.id.button_2);
        button3 = assignId(R.id.button_3);
        button4 = assignId(R.id.button_4);
        button5 = assignId(R.id.button_5);
        button6 = assignId(R.id.button_6);
        button7 = assignId(R.id.button_7);
        button8 = assignId(R.id.button_8);
        button9 = assignId(R.id.button_9);
        buttonAC = assignId(R.id.button_AC);
        buttonDot = assignId(R.id.button_dot);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    MaterialButton assignId(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
        return btn;
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataTC = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")) {
            if (!dataTC.isEmpty()) {
                dataTC = dataTC.substring(0, dataTC.length() - 1);
            }
        } else {
            dataTC = dataTC + buttonText;
        }

        solutionTv.setText(dataTC);

        String finalresult = getresult(dataTC);

        if (!finalresult.equals("Err")) {
            resultTv.setText(finalresult);
        }
    }

    String getresult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finalresult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
