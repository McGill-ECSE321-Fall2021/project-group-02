package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.os.Bundle;

public class BorrowedItemsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borroweditems_page);
    }
}
