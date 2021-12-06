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

    /**
     *
     * Populates the table with the borrowed item of the current logged in user
     *
     * @throws JSONException
     *
     * @author Vy-Kha Huynh
     */
    public void populate() throws JSONException {
        HttpUtils.get("onlineAccountLoggedIn/", new RequestParams(), new JsonHttpResponseHandler() {
            /**
             * Return the logged in user id
             *
             * @param statusCode
             * @param headers
             * @param response
             *
             * @author Vy-Kha Huynh
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    id = response.getInt("userId");
                    RequestParams rp = new RequestParams();
                    rp.add("id",Integer.toString(id));
                    HttpUtils.get("borrowedItems/books", rp , new JsonHttpResponseHandler() {
                        /**
                         *
                         * Returns all the borrowed books of the user
                         *
                         * @param statusCode
                         * @param headers
                         * @param response
                         *
                         * @author Vy-Kha Huynh
                         */
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                                books = response;
                                TableLayout stk = (TableLayout) findViewById(R.id.books_table);
                                for (int i = 0; i < books.length(); i++) {
                                    TableRow tbrow = new TableRow(BorrowedItemsActivity.this);
                                    TextView t1v = new TextView(BorrowedItemsActivity.this);
                                    t1v.setText(books.getJSONObject(i).getString("id"));
                                    t1v.setTextColor(Color.BLACK);
                                    t1v.setGravity(Gravity.CENTER);
                                    t1v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t1v);
                                    TextView t2v = new TextView(BorrowedItemsActivity.this);
                                    t2v.setText(books.getJSONObject(i).getString("title"));
                                    t2v.setTextColor(Color.BLACK);
                                    t2v.setGravity(Gravity.CENTER);
                                    t2v.setWidth(findViewById(R.id.title_header).getWidth());
                                    tbrow.addView(t2v);
                                    TextView t3v = new TextView(BorrowedItemsActivity.this);
                                    t3v.setText(books.getJSONObject(i).getString("author"));
                                    t3v.setTextColor(Color.BLACK);
                                    t3v.setGravity(Gravity.CENTER);
                                    t3v.setWidth(findViewById(R.id.author_header).getWidth());
                                    tbrow.addView(t3v);
                                    stk.addView(tbrow);
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

                    HttpUtils.get("borrowedItems/albums", rp , new JsonHttpResponseHandler() {
                        /**
                         *
                         * Returns all the borrowed albums of the user
                         *
                         * @param statusCode
                         * @param headers
                         * @param response
                         *
                         * @author Vy-Kha Huynh
                         */
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                                albums = response;
                                TableLayout stk = (TableLayout) findViewById(R.id.albums_table);
                                for (int i = 0; i < albums.length(); i++) {
                                    TableRow tbrow = new TableRow(BorrowedItemsActivity.this);
                                    TextView t1v = new TextView(BorrowedItemsActivity.this);
                                    t1v.setText(albums.getJSONObject(i).getString("id"));
                                    t1v.setTextColor(Color.BLACK);
                                    t1v.setGravity(Gravity.CENTER);
                                    t1v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t1v);
                                    TextView t2v = new TextView(BorrowedItemsActivity.this);
                                    t2v.setText(albums.getJSONObject(i).getString("title"));
                                    t2v.setTextColor(Color.BLACK);
                                    t2v.setGravity(Gravity.CENTER);
                                    t2v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t2v);
                                    TextView t3v = new TextView(BorrowedItemsActivity.this);
                                    t3v.setText(albums.getJSONObject(i).getString("artist"));
                                    t3v.setTextColor(Color.BLACK);
                                    t3v.setGravity(Gravity.CENTER);
                                    t3v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t3v);
                                    stk.addView(tbrow);
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

                    HttpUtils.get("borrowedItems/movies", rp , new JsonHttpResponseHandler() {
                        /**
                         *
                         * Returns all the borrowed movies of the user
                         *
                         * @param statusCode
                         * @param headers
                         * @param response
                         *
                         * @author Vy-Kha Huynh
                         */
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                                movies = response;
                                TableLayout stk = (TableLayout) findViewById(R.id.movies_table);
                                for (int i = 0; i < movies.length(); i++) {
                                    TableRow tbrow = new TableRow(BorrowedItemsActivity.this);
                                    TextView t1v = new TextView(BorrowedItemsActivity.this);
                                    t1v.setText(movies.getJSONObject(i).getString("id"));
                                    t1v.setTextColor(Color.BLACK);
                                    t1v.setGravity(Gravity.CENTER);
                                    t1v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t1v);
                                    TextView t2v = new TextView(BorrowedItemsActivity.this);
                                    t2v.setText(movies.getJSONObject(i).getString("title"));
                                    t2v.setTextColor(Color.BLACK);
                                    t2v.setGravity(Gravity.CENTER);
                                    t2v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t2v);
                                    TextView t3v = new TextView(BorrowedItemsActivity.this);
                                    t3v.setText(movies.getJSONObject(i).getString("director"));
                                    t3v.setTextColor(Color.BLACK);
                                    t3v.setGravity(Gravity.CENTER);
                                    t3v.setWidth(findViewById(R.id.id_header).getWidth());
                                    tbrow.addView(t3v);
                                    stk.addView(tbrow);
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
