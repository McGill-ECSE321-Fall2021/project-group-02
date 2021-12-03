package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/** Called when the activity is first created. */
public class LogInActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    public void homeRedirect(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}