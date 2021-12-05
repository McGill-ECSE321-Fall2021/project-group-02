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

        nameTextView = findViewById(R.id.name_textView);
        usernameTextView = findViewById(R.id.username_textView);
        emailTextView = findViewById(R.id.email_textView);
        balanceTextView = findViewById(R.id.balance_textView);
        userImageView = findViewById(R.id.user_imageView);
        changePasswordButton = findViewById(R.id.changePassword_button);
        homeButton = findViewById(R.id.home_imageView);
        setStyle();
        getUserInfo();



        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePasswordRedirect(view);
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeRedirect(view);
            }
        });

        nameTextView.setText(name);
        usernameTextView.setText(username);
        emailTextView.setText(email);
        balanceTextView.setText(balance);

    }

    public void homeRedirect(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void changePasswordRedirect(View view){
        Intent i = new Intent(this, ChangePasswordActivity.class);
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
                    name += response.getJSONObject("firstName").toString();
                    name += " ";
                    name += response.getJSONObject("lastName").toString();
                    username = response.getJSONObject("username").toString();
                    email = response.getJSONObject("email").toString();
                    accountId = response.getJSONObject("accountId").toString();
                    userId = response.getJSONObject("userId").toString();
                    balance = response.getJSONObject("balance").toString();
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
