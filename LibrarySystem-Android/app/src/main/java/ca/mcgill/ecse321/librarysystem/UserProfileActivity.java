package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.os.Bundle;

public class UserProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_page);
    }
}
