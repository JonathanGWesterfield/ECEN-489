package com.MachineProblem.part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Integer arg1;
    private Integer arg2;
    private int opChoice;
    private boolean opChosen;
    // 1 - Add, 2 - Sub, 3 - Mult
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.editText = (EditText) findViewById(R.id.editText);
        this.opChosen = false;
    }

    public void btn1Click(View view)
    {
        this.editText.append("1");
    }

    public void btn2Click(View view)
    {
        this.editText.append("2");
    }

    public void btn3Click(View view)
    {
        this.editText.append("3");
    }

    public void btn4Click(View view)
    {
        this.editText.append("4");
    }

    public void btn5Click(View view)
    {
        this.editText.append("5");
    }

    public void btn6Click(View view)
    {
        this.editText.append("6");
    }

    public void btn7Click(View view)
    {
        this.editText.append("7");
    }

    public void btn8Click(View view)
    {
        this.editText.append("8");
    }

    public void btn9Click(View view)
    {
        this.editText.append("9");
    }

    public void btn0Click(View view)
    {
        this.editText.append("0");
    }

    public void btnClear(View view)
    {
        this.editText.setText("");
    }

    public void btnClear()
    {
        this.editText.setText("");
    }

    public void btnAddClick(View view)
    {
        if(this.editText.getText().toString().equalsIgnoreCase(""))
        {
            this.editText.setText("Err: Must put in first arg");
            btnClear();
            return;
        }

        this.arg1 = Integer.parseInt(this.editText.getText().toString());
        btnClear();
        this.opChosen = true;
        this.opChoice = 1;
        return;
    }
    public void btnSubClick(View view)
    {
        if(this.editText.getText().toString().equalsIgnoreCase(""))
        {
            this.editText.setText("Err: Must put in first arg");
            btnClear();
            return;
        }

        this.arg1 = Integer.parseInt(this.editText.getText().toString());
        btnClear();
        this.opChosen = true;
        this.opChoice = 2;
        return;
    }

    public void btnMultiplyClick(View view)
    {
        if(this.editText.getText().toString().equalsIgnoreCase(""))
        {
            this.editText.setText("Err: Must put in first arg");
            btnClear();
            return;
        }

        this.arg1 = Integer.parseInt(this.editText.getText().toString());
        btnClear();
        this.opChosen = true;
        this.opChoice = 3;
        return;
    }

    public void btnEquals(View view)
    {
        if(this.editText.getText().toString().equalsIgnoreCase(""))
        {
            this.editText.setText("Err: Must put in first arg");
            btnClear();
            return;
        }

        int answer = 0;
        this.arg2 = Integer.parseInt(this.editText.getText().toString());

        if(this.opChoice == 1)
        {
            answer = this.arg1 + this.arg2;
        }
        else if(this.opChoice == 2)
        {
            answer = this.arg1 - this.arg2;
        }
        else if(this.opChoice == 3)
        {
            answer = this.arg1 * this.arg2;
        }

        this.editText.setText(Integer.toString(answer));
        this.opChoice = 0;
        this.opChosen = false;
    }
}
