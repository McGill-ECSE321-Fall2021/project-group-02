package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.os.Bundle;

public class ViewContentsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_page);
    }
}
