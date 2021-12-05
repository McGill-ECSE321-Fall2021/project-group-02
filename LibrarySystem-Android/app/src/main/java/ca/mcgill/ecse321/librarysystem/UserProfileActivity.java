package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserProfileActivity extends Activity {
    private TextView nameTextView, usernameTextView, emailTextView, balanceTextView;
    private ImageView userImageView, homeButton;
    private Button changePasswordButton;
    private String error = "";
    private String name = "";
    private String username = "";
    private String email = "";
    private String accountId = "";
    private String userId = "";
    private String balance = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_page);
        userImageView = findViewById(R.id.user_imageView);
        homeButton = findViewById(R.id.home_imageView);
        setStyle();
        getUserInfo();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeRedirect(view);
            }
        });


    }

    /**
     * Redirect to hompage
     * @param view view
     */
    public void homeRedirect(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    /**
     * Refresh error message
     */
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
     * Gets logged in user information and displays on screen.
     */
    private void getUserInfo(){
        HttpUtils.get("onlineAccountLoggedIn/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                name = "";
                username = "";
                email = "";
                accountId = "";
                userId = "";
                balance = "";
                try {
                    TextView tv = (TextView)findViewById(R.id.name_textView);

                    tv.setTextColor(Color.BLACK);
                    String s =(String)response.getString("firstName") + " " +(String) response.getString("lastName");
                    tv.setText(s);

                    tv = (TextView)findViewById(R.id.username_textView);
                    tv.setText(response.getString("username"));
                    tv.setTextColor(Color.BLACK);

                    tv = (TextView)findViewById(R.id.email_textView);
                    tv.setText(response.getString("email"));
                    tv.setTextColor(Color.BLACK);

                    tv = (TextView)findViewById(R.id.balance_textView);
                    tv.setText("$" + response.getString("balance"));
                    tv.setTextColor(Color.BLACK);

                } catch (Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }
    private void setStyle(){
        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);
    }
}
