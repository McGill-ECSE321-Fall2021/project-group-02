package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.Header;

public class BorrowedItemsActivity extends Activity {
    String error = "";
    int id;
    List<JSONObject> books = new ArrayList<JSONObject>();
    List<JSONObject> albums = new ArrayList<JSONObject>();
    List<JSONObject> movies = new ArrayList<JSONObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borroweditems_page);
        HttpUtils.get("onlineAccountLoggedInID", new RequestParams(), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, int response) {
                id = response;
                refreshErrorMessage();
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try{
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });

        HttpUtils.get("borrowedItems/books", new RequestParams(id), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, List<JSONObject> response) {
                books = response;
                refreshErrorMessage();
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try{
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
        HttpUtils.get("borrowedItems/albums", new RequestParams(id), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, List<JSONObject> response) {
                albums = response;
                refreshErrorMessage();
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try{
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
        HttpUtils.get("borrowedItems/movies", new RequestParams(id), new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, List<JSONObject> response) {
                movies = response;
                refreshErrorMessage();
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try{
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
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
}
