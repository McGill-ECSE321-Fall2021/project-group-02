package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LogInActivity extends Activity {
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
     * Sets the layout to the signup page and shows the error message
     * @param savedInstanceState
     * @author Sami Ait Ouahmane
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setStyle();
    }

    /**
     * Allows the user to log in
     * @param v
     * @author Sami Ait Ouahmane
     */
    public void logIn(View v) {
        error = "";
        final TextView tv1 = (TextView) findViewById(R.id.usernameUser);
        final TextView tv2 = (TextView) findViewById(R.id.passwordUser);
        HttpUtils.post("logIn/" + tv1.getText().toString()+"/"+tv2.getText().toString(),new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                tv1.setText("");
                tv2.setText("");
                homeRedirect(v);
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
     * Redirects to the homepage (used to test home page layout)
     *
     * @author Niilo
     */
    public void homeRedirect(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    /**
     * Sets the style of the Activity to hardcoded specifications for log in
     *
     * @author Sami Ait Ouahmane
     */
    private void setStyle(){
        Button btn_tmp = (Button)findViewById(R.id.logIn);
        btn_tmp.setBackgroundColor(0xFF961919);
        btn_tmp.setTextColor(Color.WHITE);

        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);

        TextView txt = (TextView) findViewById(R.id.header);
        txt.setBackgroundColor(0xA0000000);

        EditText ed1 = (EditText) findViewById(R.id.passwordUser);
        ed1.setBackgroundColor(0xA0000000);

        EditText ed2 = (EditText) findViewById(R.id.usernameUser);
        ed2.setBackgroundColor(0xA0000000);
    }
}