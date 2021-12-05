package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignUpExistingActivity extends Activity {

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
     * Allows the user to sign up
     * @param v
     * @author Sami Ait Ouahmane
     */
    public void SignUpExisting(View v) {
        error = "";
        final TextView tvID = (TextView) findViewById(R.id.id);
        final TextView tvUsername = (TextView) findViewById(R.id.username1);
        final TextView tvPassword = (TextView) findViewById(R.id.password1);
        final TextView tvEmail = (TextView) findViewById(R.id.email1);
        HttpUtils.post("onlineAccountNew/"+tvID.getText().toString()+'/'+tvUsername.getText().toString()+'/'+tvPassword.getText().toString()+'/'+tvEmail.getText().toString(),new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                tvID.setText("");
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
        setContentView(R.layout.signupexisting_page);
        // initialize error message text view
        refreshErrorMessage();
        setStyle();
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
    /**
     * Sets the style of the Activity to hardcoded specifications for sign up nonexisting
     *
     * @author Sami Ait Ouahmane
     */
    private void setStyle(){
        Button btn_tmp = (Button)findViewById(R.id.signUp);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        TextView txt = (TextView) findViewById(R.id.header);
        txt.setBackgroundColor(0xA0000000);

        EditText ed1 = (EditText) findViewById(R.id.password1);
        ed1.setBackgroundColor(0xA0000000);

        EditText ed2 = (EditText) findViewById(R.id.username1);
        ed2.setBackgroundColor(0xA0000000);

        EditText ed3 = (EditText) findViewById(R.id.email1);
        ed3.setBackgroundColor(0xA0000000);

        EditText ed7 = (EditText) findViewById(R.id.id);
        ed7.setBackgroundColor(0xA0000000);
    }
}
