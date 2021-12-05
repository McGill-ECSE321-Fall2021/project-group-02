package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ChangePasswordActivity extends Activity {
    private EditText passwordEditText, newPasswordEditText;
    private ImageView homeButton, closeButton;
    private Button confirmButton;
    String accountId = "";
    String error = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_page);

        passwordEditText = findViewById(R.id.editTextTextPassword);
        newPasswordEditText = findViewById(R.id.editTextTextNewPassword);
        homeButton = findViewById(R.id.home_imageView);
        confirmButton = findViewById(R.id.confirm_button2);
        closeButton = findViewById(R.id.close_imageView);
        setStyle();
        getUserInfo();



        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeRedirect();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfileRedirect();
            }
        });

    }

    public void homeRedirect(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void userProfileRedirect(){
        Intent i = new Intent(this, UserProfileActivity.class);
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

    public void getUserInfo(){
        HttpUtils.get("onlineAccountLoggedIn/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                accountId = "";
                try {
                    accountId = response.getJSONObject("accountId").toString();
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

    public void changePassword(){
        String pass = findViewById(R.id.editTextTextPassword).toString();
        String newPass = findViewById(R.id.editTextTextNewPassword).toString();
//        String pass = passwordEditText.toString();
//        String newPass = newPasswordEditText.toString();
        RequestParams rp = new RequestParams();
        rp.add("id", accountId);
        rp.add("password", pass);
        rp.add("newPassword", newPass);

        HttpUtils.put("changePassword/", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
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