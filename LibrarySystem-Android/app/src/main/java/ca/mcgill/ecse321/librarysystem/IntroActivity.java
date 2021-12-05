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
        // Set the correct xml layout for this activity
        setContentView(R.layout.intro_page);
        setStyle();

    }

    /**
     * Overrides the back button to do nothing when pressed
     *
     * @author Niilo
     */
    @Override
    public void onBackPressed() {
        // Do nothing on back press
    }

    /**
     * Redirects to the log in page
     *
     * @param view the view that calls the method
     * @author Niilo
     */
    public void logInRedirect(View view){
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
    }

    /**
     * Redirects to the sign up page
     *
     * @param view the view that calls the method
     * @author Niilo
     */
    public void signUpRedirect(View view){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    /**
     * Redirects to the sign up existing user page
     *
     * @param view the view that calls the method
     * @author Sami Ait Ouahmane
     */
    public void signUpExistingRedirect(View view){
        Intent i = new Intent(this, SignUpExistingActivity.class);
        startActivity(i);
    }

    /**
     * Sets the style of the Activity to hardcoded specifications
     *
     * @author Niilo
     */
    private void setStyle(){
        Button btn_tmp = (Button)findViewById(R.id.buttonLogIn);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        btn_tmp = (Button)findViewById(R.id.buttonSignUp);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        btn_tmp = (Button)findViewById(R.id.buttonSignUp2);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);

        TextView txt = (TextView) findViewById(R.id.header);
        txt.setBackgroundColor(0xA0000000);
    }
}