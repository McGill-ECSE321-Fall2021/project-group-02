package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        setStyle();
    }

    public void viewContentsRedirect(View view){
        Intent i = new Intent(this, ViewContentsActivity.class);
        startActivity(i);
    }

    public void userProfileRedirect(View view){
        Intent i = new Intent(this, UserProfileActivity.class);
        startActivity(i);
    }

    public void borrowedItemsRedirect(View view){
        Intent i = new Intent(this, BorrowedItemsActivity.class);
        startActivity(i);
    }

    public void signOutRedirect(View view){
        Intent i = new Intent(this, IntroActivity.class);
        startActivity(i);
    }

    private void setStyle(){
        Button btn_tmp = (Button)findViewById(R.id.buttonSignOut);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        btn_tmp = (Button)findViewById(R.id.buttonBorrowedItems);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        btn_tmp = (Button)findViewById(R.id.buttonUserProfile);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        btn_tmp = (Button)findViewById(R.id.buttonViewContents);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);

        TextView txt = (TextView) findViewById(R.id.header);
        txt.setBackgroundColor(0xA0000000);
    }
}
