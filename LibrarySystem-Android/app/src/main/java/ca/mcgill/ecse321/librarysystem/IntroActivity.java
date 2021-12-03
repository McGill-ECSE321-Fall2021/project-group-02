package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends Activity {
    // Where the program starts off: the sign in/sign up page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_page);
        setStyle();

    }

    public void logInRedirect(View view){
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
    }

    public void signUpRedirect(View view){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    private void setStyle(){
        Button btn_tmp = (Button)findViewById(R.id.buttonLogIn);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        btn_tmp = (Button)findViewById(R.id.buttonSignUp);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);

        TextView txt = (TextView) findViewById(R.id.header);
        txt.setBackgroundColor(0xA0000000);
    }
}