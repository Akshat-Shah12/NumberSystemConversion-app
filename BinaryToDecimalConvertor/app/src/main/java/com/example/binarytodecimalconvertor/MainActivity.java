package com.example.binarytodecimalconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public long store;
    public String storeString;
    TextView output;
    EditText editText;

    public void buttonClicked(View view){
        output.setText("");
        editText.setText("");
    }
    public void toDecimal(){
        if(selected1=="binary1" && selected2=="decimal2") {
            try {
                Log.i("check","running till decimal2 and binary1");
                long currentValue = store;
                long decimalNumber = 0;
                boolean check = true;
                int length = storeString.length();
                for (int i = 0; i < length; i++) {
                    decimalNumber += (currentValue % 10) * Math.pow(2, i);
                    if (currentValue % 10 != 0 && currentValue % 10 != 1) {
                        check = false;
                    }
                    currentValue = currentValue / 10;
                }
                if (check == false) {
                    output.setText("Invalid Input");
                } else {
                    output.setText(String.valueOf(decimalNumber));
                }
            } catch (Exception e) {
                output.setText("There is something wrong");
            }
        }
        else if(selected1=="decimal1" && selected2=="binary2"){
            try {
                Log.i("check","running till decimal1 and binary2");
                String answer="";
                long quotent=store;
                while(quotent!=1){
                    answer= String.valueOf(quotent%2)+answer;
                    quotent=quotent/2;
                }
                answer="1"+answer;
                output.setText(answer);
            } catch (Exception e) {
                output.setText("invalid input");
            }
        }
        else if(selected1!="none" && selected2!="none"){

            output.setText(storeString);
        }
        else{
            output.setText("waiting for proper selection");
        }


    }
    public String selected1 = "none";
    public String selected2 = "none";

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.decimal1:
                if (checked)
                    selected1="decimal1";
                    toDecimal();
                    Log.i("check","checking decimal1");
                    break;
            case R.id.binary1:
                if (checked)
                    selected1="binary1";
                    toDecimal();
                    break;
            case R.id.useless1:
                if (checked)
                    selected1="none";
                toDecimal();
                break;
        }
    }

    public void onRadioButtonClicked2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.decimal2:
                if (checked)
                    selected2="decimal2";
                    toDecimal();
                    Log.i("check","checking decimal2");
                break;
            case R.id.binary2:
                if (checked)
                    selected2="binary2";
                    toDecimal();
                break;
            case R.id.useless2:
                if (checked)
                    selected2="none";
                    toDecimal();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView)findViewById(R.id.output);
        editText = (EditText)findViewById(R.id.binaryNum);
//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                Log.i("check12",String.valueOf(v.getText()));
//                return true;
//            }
//        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                storeString = String.valueOf(s);
                try {
                    store = Long.parseLong(String.valueOf(s));
                    toDecimal();
                } catch (NumberFormatException e) {
                    output.setText("You may have entered other than a number");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}