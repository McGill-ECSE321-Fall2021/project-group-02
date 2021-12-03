package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class HomeActivity extends Activity {

    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the correct xml layout for this activity
        setContentView(R.layout.home_page);
        setStyle();
    }

    /**
     * Redirects to the view contents page
     *
     * @param view the view that calls the method
     * @author Niilo
     */
    public void viewContentsRedirect(View view){
        Intent i = new Intent(this, ViewContentsActivity.class);
        startActivity(i);
    }

    /**
     * Redirects to the user profile page
     *
     * @param view the view that calls the method
     * @author Niilo
     */
    public void userProfileRedirect(View view){
        Intent i = new Intent(this, UserProfileActivity.class);
        startActivity(i);
    }

    /**
     * Redirects to the borrowed items page
     *
     * @param view the view that calls the method
     * @author Niilo
     */
    public void borrowedItemsRedirect(View view){
        Intent i = new Intent(this, BorrowedItemsActivity.class);
        startActivity(i);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Redirects to the sign out page
     *
     * @param view the view that calls the method
     * @author Niilo
     */
    public void signOutRedirect(View view){
        error = "";
        Intent i = new Intent(this, IntroActivity.class);

        // TODO: get error handling to work
        HttpUtils.post("signOut/",  new RequestParams(), new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();

            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
        startActivity(i);// Should be inside onSuccess(), does not work for some reason
    }

    /**
     * Sets the style of the Activity to hardcoded specifications
     *
     * @author Niilo
     */
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
