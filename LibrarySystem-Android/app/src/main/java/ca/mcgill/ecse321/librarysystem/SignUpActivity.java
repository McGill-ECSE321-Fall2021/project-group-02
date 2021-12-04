package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignUpActivity extends Activity {

    private String error=null;

    /**
     * Sets the error message
     * @author Sami Ait Ouahmane
     */
    private void refreshErrorMessage() {
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Allows the user to log in
     * @param v
     * @author Sami Ait Ouahmane
     */
    public void SignUp(View v) {
        error = "";
        final TextView tvfN = (TextView) findViewById(R.id.firstNameUser);
        final TextView tvlN = (TextView) findViewById(R.id.lastNameUser);
        final TextView tvAd = (TextView) findViewById(R.id.address);
        final TextView tvCity = (TextView) findViewById(R.id.city);
        final TextView tvUsername = (TextView) findViewById(R.id.username);
        final TextView tvPassword = (TextView) findViewById(R.id.password);
        final TextView tvEmail = (TextView) findViewById(R.id.email);
        HttpUtils.post("onlineAccountNew/"+tvfN.getText().toString()+'/'+tvlN.getText().toString()+'/'+tvAd.getText().toString()+'/'+tvCity.getText().toString()+'/'+tvUsername.getText().toString()+'/'+tvPassword.getText().toString()+'/'+tvEmail.getText().toString()+'/',new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                tvfN.setText("");
                tvlN.setText("");
                tvAd.setText("");
                tvCity.setText("");
                tvUsername.setText("");
                tvPassword.setText("");
                tvEmail.setText("");
                introRedirect(v);
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

    /**
     * Sets the layout to the signup page and shows the error message
     * @param savedInstanceState
     * @author Sami Ait Ouahmane
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        // initialize error message text view
        refreshErrorMessage();
    }

    /**
     * Redirects to the intro page
     *
     * @param view the view that calls the method
     * @author Sami Ait Ouahmane
     */
    public void introRedirect(View view){
        Intent i = new Intent(this, IntroActivity.class);
        startActivity(i);
    }


}
