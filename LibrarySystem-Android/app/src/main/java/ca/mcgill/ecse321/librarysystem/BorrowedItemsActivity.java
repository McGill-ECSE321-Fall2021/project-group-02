package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;



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

//    private void refreshErrorMessage() {
//        // set the error message
//        TextView tvError = (TextView) findViewById(R.id.error);
//        tvError.setText(error);
//
//        if (error == null || error.length() == 0) {
//            tvError.setVisibility(View.GONE);
//        } else {
//            tvError.setVisibility(View.VISIBLE);
//        }
//    }

    public void populate() throws JSONException {
        TableLayout stk = (TableLayout) findViewById(R.id.content);
        HttpUtils.get("onlineAccountLoggedIn/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    id = response.getInt("userId");
                    RequestParams rp = new RequestParams();
                    rp.add("id",Integer.toString(id));
                    HttpUtils.get("borrowedItems/books", rp , new JsonHttpResponseHandler() {
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                                books = response;
                                for (int i = 0; i < books.length(); i++) {
                                    TableRow tbrow = new TableRow(BorrowedItemsActivity.this);
                                    TextView t1v = new TextView(BorrowedItemsActivity.this);
                                    t1v.setText(books.getJSONObject(i).getString("title"));
                                    t1v.setTextColor(Color.BLACK);
                                    t1v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t1v);
                                    TextView t2v = new TextView(BorrowedItemsActivity.this);
                                    t2v.setText(books.getJSONObject(i).getString("author"));
                                    t2v.setTextColor(Color.BLACK);
                                    t2v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t2v);
                                    TextView t3v = new TextView(BorrowedItemsActivity.this);
                                    t3v.setText(books.getJSONObject(i).getString("id"));
                                    t3v.setTextColor(Color.BLACK);
                                    t3v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t3v);
                                    stk.addView(tbrow, curr);
                                    curr++;
                                }
                                curr++;
                                curr++;

                            }catch(JSONException e){
                                error += e.getMessage();
                            }
                        }

                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                                error += errorResponse.get("message").toString();
                            } catch (JSONException e) {
                                error += e.getMessage();
                            }
                        }
                    });

                    HttpUtils.get("borrowedItems/albums", rp , new JsonHttpResponseHandler() {
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                                albums = response;
                                for (int i = 0; i < albums.length(); i++) {
                                    TableRow tbrow = new TableRow(BorrowedItemsActivity.this);
                                    TextView t1v = new TextView(BorrowedItemsActivity.this);
                                    t1v.setText(albums.getJSONObject(i).getString("title"));
                                    t1v.setTextColor(Color.BLACK);
                                    t1v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t1v);
                                    TextView t2v = new TextView(BorrowedItemsActivity.this);
                                    t2v.setText(albums.getJSONObject(i).getString("artist"));
                                    t2v.setTextColor(Color.BLACK);
                                    t2v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t2v);
                                    TextView t3v = new TextView(BorrowedItemsActivity.this);
                                    t3v.setText(albums.getJSONObject(i).getString("id"));
                                    t3v.setTextColor(Color.BLACK);
                                    t3v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t3v);
                                    stk.addView(tbrow, curr);
                                    curr++;
                                }

                                curr++;
                                curr++;

                            }catch(JSONException e){
                                error += e.getMessage();
                            }
                        }

                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                                error += errorResponse.get("message").toString();
                            } catch (JSONException e) {
                                error += e.getMessage();
                            }
                        }
                    });

                    HttpUtils.get("borrowedItems/movies", rp , new JsonHttpResponseHandler() {
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                                movies = response;
                                for (int i = 0; i < movies.length(); i++) {
                                    TableRow tbrow = new TableRow(BorrowedItemsActivity.this);
                                    TextView t1v = new TextView(BorrowedItemsActivity.this);
                                    t1v.setText(movies.getJSONObject(i).getString("title"));
                                    t1v.setTextColor(Color.BLACK);
                                    t1v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t1v);
                                    TextView t2v = new TextView(BorrowedItemsActivity.this);
                                    t2v.setText(movies.getJSONObject(i).getString("director"));
                                    t2v.setTextColor(Color.BLACK);
                                    t2v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t2v);
                                    TextView t3v = new TextView(BorrowedItemsActivity.this);
                                    t3v.setText(movies.getJSONObject(i).getString("id"));
                                    t3v.setTextColor(Color.BLACK);
                                    t3v.setGravity(Gravity.CENTER);
                                    tbrow.addView(t3v);
                                    stk.addView(tbrow,curr);
                                    curr++;
                                }

                            }catch(JSONException e){
                                error += e.getMessage();
                            }
                        }

                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                                error += errorResponse.get("message").toString();
                            } catch (JSONException e) {
                                error += e.getMessage();
                            }
                        }
                    });


                } catch (JSONException e){
                    error = e.getMessage();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try{
                    error += errorResponse.get("message").toString();
                } catch (JSONException e){
                    error += e.getMessage();
                }
            }
        });
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
