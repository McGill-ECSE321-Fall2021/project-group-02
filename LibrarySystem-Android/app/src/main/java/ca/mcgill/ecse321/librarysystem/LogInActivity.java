package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogInActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    /**
     * Redirects to the homepage (used to test home page layout)
     *
     * @author Niilo
     */
    public void homeRedirect(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}