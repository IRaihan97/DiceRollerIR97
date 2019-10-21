package com.example.cs18iir.diceroller;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score = 0; //score field to store score in and increment during runtime
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onButtonClick(View view){
        EditText inputNumber;
        TextView msg = (TextView) this.findViewById(R.id.message);//Referencing to text views with respective IDs
        TextView scoreTxt = (TextView) this.findViewById(R.id.score);
        int number;
        inputNumber = (EditText) findViewById(R.id.inputNumber);//Referencing to input field named inputNumber
        String check = inputNumber.getText().toString();//Getting value entered and casting to string
        if(check.isEmpty()){
            showToast("Make sure You Entered A Number");
            inputNumber.getText().clear();//clears input field
        }
        else{
            number = Integer.valueOf(inputNumber.getText().toString());//casting value entered to integer
            if (number < 0){
                showToast("Number Cannot Be Smaller than 0");
                inputNumber.getText().clear();
            }
            else if(number > 6){
                showToast("Number Entered Cannot be Greater than 6");
                inputNumber.getText().clear();
            }

            else{
                Random r = new Random();
                int rand = r.nextInt(6);//randomizing a value up to 6
                if(number == rand){
                    score++;
                    scoreTxt.setText("You Have Guessed Right " + score +  " Times");
                    msg.setText("Good Job The correct number is " + number);
                    inputNumber.getText().clear();
                    closeKeyboard();
                }

                else {
                    msg.setText("Wrong! The Number Was: " + Integer.toString(rand));
                    inputNumber.getText().clear();
                    closeKeyboard();
                }
            }
        }
    }

    //Toast will display the string passed into it in a small pop up
    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    private void closeKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);//Gets current keyboard of the system
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);//hides the keyboard
    }
}
