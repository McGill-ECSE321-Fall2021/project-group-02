package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.Header;

public class BorrowedItemsActivity extends Activity {
    String error = "";
    int id;
    int curr = 2;

    JSONArray books = new JSONArray();
    JSONArray albums = new JSONArray();
    JSONArray movies = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borroweditems_page);
        try {
            populate();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setStyle();
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

    public void populate() throws JSONException {
        TableLayout stk = (TableLayout) findViewById(R.id.content);

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

//        HttpUtils.get("borrowedItems/books", new RequestParams(id), new JsonHttpResponseHandler() {
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//            System.out.println(statusCode);
//                //                books = response;
//                refreshErrorMessage();
//            }
//
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
        for (int i = 0; i < 4; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("Ok");
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Hi");
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Hello");
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            stk.addView(tbrow, curr);
            curr++;
        }

        curr++;

//        for (int i = 0; i < books.length(); i++) {
//            TableRow tbrow = new TableRow(this);
//            TextView t1v = new TextView(this);
//            t1v.setText(books.getJSONObject(i).getString("title"));
//            t1v.setTextColor(Color.BLACK);
//            t1v.setGravity(Gravity.CENTER);
//            tbrow.addView(t1v);
//            TextView t2v = new TextView(this);
//            t2v.setText(books.getJSONObject(i).getString("author"));
//            t2v.setTextColor(Color.BLACK);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            TextView t3v = new TextView(this);
//            t3v.setText(books.getJSONObject(i).getInt("itemID"));
//            t3v.setTextColor(Color.BLACK);
//            t3v.setGravity(Gravity.CENTER);
//            tbrow.addView(t3v);
//            stk.addView(tbrow, curr);
//            curr++;
//        }
//
//        curr++;

//        HttpUtils.get("borrowedItems/albums", new RequestParams(id), new JsonHttpResponseHandler() {
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                albums = response;
//                refreshErrorMessage();
//            }
//
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
//
//        for (int i = 0; i < albums.length(); i++) {
//            TableRow tbrow = new TableRow(this);
//            TextView t1v = new TextView(this);
//            t1v.setText(albums.getJSONObject(i).getString("title"));
//            t1v.setTextColor(Color.BLACK);
//            t1v.setGravity(Gravity.CENTER);
//            tbrow.addView(t1v);
//            TextView t2v = new TextView(this);
//            t2v.setText(books.getJSONObject(i).getString("artist"));
//            t2v.setTextColor(Color.BLACK);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            TextView t3v = new TextView(this);
//            t3v.setText(books.getJSONObject(i).getInt("itemID"));
//            t3v.setTextColor(Color.BLACK);
//            t3v.setGravity(Gravity.CENTER);
//            tbrow.addView(t3v);
//            stk.addView(tbrow, curr);
//            curr++;
//        }

//        curr++;
//
//        HttpUtils.get("borrowedItems/movies", new RequestParams(id), new JsonHttpResponseHandler() {
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                movies = response;
//                refreshErrorMessage();
//            }
//
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
//
//        for (int i = 0; i < movies.length(); i++) {
//            TableRow tbrow = new TableRow(this);
//            TextView t1v = new TextView(this);
//            t1v.setText(movies.getJSONObject(i).getString("title"));
//            t1v.setTextColor(Color.BLACK);
//            t1v.setGravity(Gravity.CENTER);
//            tbrow.addView(t1v);
//            TextView t2v = new TextView(this);
//            t2v.setText(movies.getJSONObject(i).getString("director"));
//            t2v.setTextColor(Color.BLACK);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            TextView t3v = new TextView(this);
//            t3v.setText(movies.getJSONObject(i).getInt("itemID"));
//            t3v.setTextColor(Color.BLACK);
//            t3v.setGravity(Gravity.CENTER);
//            tbrow.addView(t3v);
//            stk.addView(tbrow, curr);
//            curr++;
//        }
    }

    /**
     * Sets the style of the Activity to hardcoded specifications
     *
     * @author Niilo
     */
    private void setStyle(){
        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);
    }
}
