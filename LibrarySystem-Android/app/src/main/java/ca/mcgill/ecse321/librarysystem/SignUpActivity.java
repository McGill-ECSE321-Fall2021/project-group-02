package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
    }

    /**
     * Redirects to the intro page
     *
     * @param view the view that calls the method
     * @author Sami Ait Ouahmane
     */
    public void introRedirect(View view){
        Intent i = new Intent(this, IntroActivity.class);
        startActivity(i);
    }
}
