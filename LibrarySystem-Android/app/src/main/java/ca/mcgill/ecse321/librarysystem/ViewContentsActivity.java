package ca.mcgill.ecse321.librarysystem;

import static android.R.color.darker_gray;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ViewContentsActivity extends Activity {
    private TableLayout books, albums, movies, newspapers, journals;
    private static TableRow row;
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_page);
        setStyle();

        displayBooks(findViewById(R.id.books_info));
        displayAlbums(findViewById(R.id.albums_info));
        displayMovies(findViewById(R.id.movies_info));
        displayNewspapers(findViewById(R.id.newspapers_info));
        displayJournals(findViewById(R.id.journals_info));

        books = findViewById(R.id.books_table);
        albums = findViewById(R.id.albums_table);
        movies = findViewById(R.id.movies_table);
        newspapers = findViewById(R.id.newspapers_table);
        journals = findViewById(R.id.journals_table);
    }

    /**
     * Sets the style of the Activity to hardcoded specifications
     *
     * @author Niilo
     */

    private void setStyle(){
        Window w = this.getWindow();
        w.setStatusBarColor(Color.BLACK);
        ScrollView v = (ScrollView) findViewById(R.id.parent);
        TextView txt = (TextView) findViewById(R.id.header);
        txt.setBackgroundColor(0xA0000000);
    }


    /**
     * Display all the books in the library
     * @author Julie
     */
    public void displayBooks(View v) {
        HttpUtils.get("items/books/", new RequestParams(), new JsonHttpResponseHandler() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {

                    books = (TableLayout) findViewById(R.id.books_table);
                    TableRow tbrow0 = new TableRow(ViewContentsActivity.this);
                    TextView tv0 = new TextView(ViewContentsActivity.this);
                    tv0.setText(" ID ");
                    tv0.setGravity(Gravity.CENTER);
                    tv0.setBackgroundColor(Color.parseColor("#430000"));
                    tv0.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv0);
                    TextView tv1 = new TextView(ViewContentsActivity.this);
                    tv1.setText(" Title ");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setBackgroundColor(Color.parseColor("#430000"));
                    tv1.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv1);
                    TextView tv2 = new TextView(ViewContentsActivity.this);
                    tv2.setText(" Author ");
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setBackgroundColor(Color.parseColor("#430000"));
                    tv2.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv2);
                    TextView tv3 = new TextView(ViewContentsActivity.this);
                    tv3.setText(" Availability ");
                    tv3.setGravity(Gravity.CENTER);
                    tv3.setBackgroundColor(Color.parseColor("#430000"));
                    tv3.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv3);

                    books.addView(tbrow0);

                    for (int i=0; i<response.length(); i++) {
                        String id = response.getJSONObject(i).getString("id");
                        String title = response.getJSONObject(i).getString("title");
                        String author = response.getJSONObject(i).getString("author");
                        String available = String.valueOf(response.getJSONObject(i).getBoolean("isAvailable"));

                        TableRow row = new TableRow(ViewContentsActivity.this);
                        TextView text1 = new TextView(ViewContentsActivity.this);
                        TextView text2 = new TextView(ViewContentsActivity.this);
                        TextView text3 = new TextView(ViewContentsActivity.this);
                        TextView text4 = new TextView(ViewContentsActivity.this);

                        text1.setGravity(Gravity.CENTER);
                        text1.setTextSize(12.0f);
                        text1.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text1.setTypeface(null, Typeface.BOLD);
                        text1.setText(id);
                        row.addView(text1);

                        text2.setGravity(Gravity.CENTER);
                        text2.setTextSize(12.0f);
                        text2.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text2.setTypeface(null, Typeface.BOLD);
                        text2.setText(title);
                        row.addView(text2);

                        text3.setGravity(Gravity.CENTER);
                        text3.setTextSize(12.0f);
                        text3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text3.setTypeface(null, Typeface.BOLD);
                        text3.setText(author);
                        row.addView(text3);

                        text4.setGravity(Gravity.CENTER);
                        text4.setTextSize(12.0f);
                        text4.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text4.setTypeface(null, Typeface.BOLD);
                        text4.setText(available);
                        row.addView(text4);

                        books.addView(row);

                    }
                } catch (Exception e) {
                    error += e.getMessage();
                }
               // refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });
    }
    /**
     * Display all the albums in the library
     * @author Julie
     */
    public void displayAlbums(View v) {
        HttpUtils.get("items/albums/", new RequestParams(), new JsonHttpResponseHandler() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {

                    albums = (TableLayout) findViewById(R.id.albums_table);
                    TableRow tbrow0 = new TableRow(ViewContentsActivity.this);
                    TextView tv0 = new TextView(ViewContentsActivity.this);
                    tv0.setText(" ID ");
                    tv0.setGravity(Gravity.CENTER);
                    tv0.setBackgroundColor(Color.parseColor("#430000"));
                    tv0.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv0);
                    TextView tv1 = new TextView(ViewContentsActivity.this);
                    tv1.setText(" Title ");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setBackgroundColor(Color.parseColor("#430000"));
                    tv1.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv1);
                    TextView tv2 = new TextView(ViewContentsActivity.this);
                    tv2.setText(" Artist ");
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setBackgroundColor(Color.parseColor("#430000"));
                    tv2.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv2);
                    TextView tv3 = new TextView(ViewContentsActivity.this);
                    tv3.setText(" Availability ");
                    tv3.setGravity(Gravity.CENTER);
                    tv3.setBackgroundColor(Color.parseColor("#430000"));
                    tv3.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv3);

                    albums.addView(tbrow0);

                    for (int i=0; i<response.length(); i++) {
                        String id = response.getJSONObject(i).getString("id");
                        String title = response.getJSONObject(i).getString("title");
                        String artist = response.getJSONObject(i).getString("artist");
                        String available = String.valueOf(response.getJSONObject(i).getBoolean("isAvailable"));

                        TableRow row = new TableRow(ViewContentsActivity.this);
                        TextView text1 = new TextView(ViewContentsActivity.this);
                        TextView text2 = new TextView(ViewContentsActivity.this);
                        TextView text3 = new TextView(ViewContentsActivity.this);
                        TextView text4 = new TextView(ViewContentsActivity.this);

                        text1.setGravity(Gravity.CENTER);
                        text1.setTextSize(12.0f);
                        text1.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text1.setTypeface(null, Typeface.BOLD);
                        text1.setText(id);
                        row.addView(text1);

                        text2.setGravity(Gravity.CENTER);
                        text2.setTextSize(12.0f);
                        text2.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text2.setTypeface(null, Typeface.BOLD);
                        text2.setText(title);
                        row.addView(text2);

                        text3.setGravity(Gravity.CENTER);
                        text3.setTextSize(12.0f);
                        text3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text3.setTypeface(null, Typeface.BOLD);
                        text3.setText(artist);
                        row.addView(text3);

                        text4.setGravity(Gravity.CENTER);
                        text4.setTextSize(12.0f);
                        text4.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text4.setTypeface(null, Typeface.BOLD);
                        text4.setText(available);
                        row.addView(text4);

                        albums.addView(row);

                    }
                } catch (Exception e) {
                    error += e.getMessage();
                }
                // refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });

    }
    /**
     * Display all the movies in the library
     * @author Julie
     */
    public void displayMovies(View v) {
        HttpUtils.get("items/movies/", new RequestParams(), new JsonHttpResponseHandler() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {

                    movies = (TableLayout) findViewById(R.id.movies_table);
                    TableRow tbrow0 = new TableRow(ViewContentsActivity.this);
                    TextView tv0 = new TextView(ViewContentsActivity.this);
                    tv0.setText(" ID ");
                    tv0.setGravity(Gravity.CENTER);
                    tv0.setBackgroundColor(Color.parseColor("#430000"));
                    tv0.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv0);
                    TextView tv1 = new TextView(ViewContentsActivity.this);
                    tv1.setText(" Title ");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setBackgroundColor(Color.parseColor("#430000"));
                    tv1.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv1);
                    TextView tv2 = new TextView(ViewContentsActivity.this);
                    tv2.setText(" Director ");
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setBackgroundColor(Color.parseColor("#430000"));
                    tv2.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv2);
                    TextView tv3 = new TextView(ViewContentsActivity.this);
                    tv3.setText(" Availability ");
                    tv3.setGravity(Gravity.CENTER);
                    tv3.setBackgroundColor(Color.parseColor("#430000"));
                    tv3.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv3);

                    movies.addView(tbrow0);

                    for (int i=0; i<response.length(); i++) {
                        String id = response.getJSONObject(i).getString("id");
                        String title = response.getJSONObject(i).getString("title");
                        String director = response.getJSONObject(i).getString("director");
                        String available = String.valueOf(response.getJSONObject(i).getBoolean("isAvailable"));

                        TableRow row = new TableRow(ViewContentsActivity.this);
                        TextView text1 = new TextView(ViewContentsActivity.this);
                        TextView text2 = new TextView(ViewContentsActivity.this);
                        TextView text3 = new TextView(ViewContentsActivity.this);
                        TextView text4 = new TextView(ViewContentsActivity.this);

                        text1.setGravity(Gravity.CENTER);
                        text1.setTextSize(12.0f);
                        text1.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text1.setTypeface(null, Typeface.BOLD);
                        text1.setText(id);
                        row.addView(text1);

                        text2.setGravity(Gravity.CENTER);
                        text2.setTextSize(12.0f);
                        text2.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text2.setTypeface(null, Typeface.BOLD);
                        text2.setText(title);
                        row.addView(text2);

                        text3.setGravity(Gravity.CENTER);
                        text3.setTextSize(12.0f);
                        text3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text3.setTypeface(null, Typeface.BOLD);
                        text3.setText(director);
                        row.addView(text3);

                        text4.setGravity(Gravity.CENTER);
                        text4.setTextSize(12.0f);
                        text4.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text4.setTypeface(null, Typeface.BOLD);
                        text4.setText(available);
                        row.addView(text4);

                        movies.addView(row);

                    }
                } catch (Exception e) {
                    error += e.getMessage();
                }
                // refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });

    }
    /**
     * Display all the newspapers in the library
     * @author Julie
     */
    public void displayNewspapers(View v) {
        HttpUtils.get("items/newspapers/", new RequestParams(), new JsonHttpResponseHandler() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {

                    newspapers = (TableLayout) findViewById(R.id.newspapers_table);
                    TableRow tbrow0 = new TableRow(ViewContentsActivity.this);
                    TextView tv0 = new TextView(ViewContentsActivity.this);
                    tv0.setText(" ID ");
                    tv0.setGravity(Gravity.CENTER);
                    tv0.setBackgroundColor(Color.parseColor("#430000"));
                    tv0.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv0);
                    TextView tv1 = new TextView(ViewContentsActivity.this);
                    tv1.setText(" Name ");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setBackgroundColor(Color.parseColor("#430000"));
                    tv1.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv1);
                    TextView tv2 = new TextView(ViewContentsActivity.this);
                    tv2.setText(" Date ");
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setBackgroundColor(Color.parseColor("#430000"));
                    tv2.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv2);

                    newspapers.addView(tbrow0);

                    for (int i=0; i<response.length(); i++) {
                        String id = response.getJSONObject(i).getString("id");
                        String name = response.getJSONObject(i).getString("name");
                        String date = response.getJSONObject(i).getString("date");

                        TableRow row = new TableRow(ViewContentsActivity.this);
                        TextView text1 = new TextView(ViewContentsActivity.this);
                        TextView text2 = new TextView(ViewContentsActivity.this);
                        TextView text3 = new TextView(ViewContentsActivity.this);

                        text1.setGravity(Gravity.CENTER);
                        text1.setTextSize(12.0f);
                        text1.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text1.setTypeface(null, Typeface.BOLD);
                        text1.setText(id);
                        row.addView(text1);

                        text2.setGravity(Gravity.CENTER);
                        text2.setTextSize(12.0f);
                        text2.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text2.setTypeface(null, Typeface.BOLD);
                        text2.setText(name);
                        row.addView(text2);

                        text3.setGravity(Gravity.CENTER);
                        text3.setTextSize(12.0f);
                        text3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text3.setTypeface(null, Typeface.BOLD);
                        text3.setText(date.substring(0, 10));
                        row.addView(text3);


                        newspapers.addView(row);

                    }
                } catch (Exception e) {
                    error += e.getMessage();
                }
                // refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });

    }
    /**
     * Display all the journals in the library
     * @author Julie
     */
    public void displayJournals(View v) {
        HttpUtils.get("items/journals/", new RequestParams(), new JsonHttpResponseHandler() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {

                    journals = (TableLayout) findViewById(R.id.journals_table);
                    TableRow tbrow0 = new TableRow(ViewContentsActivity.this);
                    TextView tv0 = new TextView(ViewContentsActivity.this);
                    tv0.setText(" ID ");
                    tv0.setGravity(Gravity.CENTER);
                    tv0.setBackgroundColor(Color.parseColor("#430000"));
                    tv0.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv0);
                    TextView tv1 = new TextView(ViewContentsActivity.this);
                    tv1.setText(" Name ");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setBackgroundColor(Color.parseColor("#430000"));
                    tv1.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv1);
                    TextView tv2 = new TextView(ViewContentsActivity.this);
                    tv2.setText(" Date ");
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setBackgroundColor(Color.parseColor("#430000"));
                    tv2.setTextColor(Color.parseColor("#aaaaaa"));
                    tbrow0.addView(tv2);

                    journals.addView(tbrow0);

                    for (int i=0; i<response.length(); i++) {
                        String id = response.getJSONObject(i).getString("id");
                        String name = response.getJSONObject(i).getString("name");
                        String date = response.getJSONObject(i).getString("date");

                        TableRow row = new TableRow(ViewContentsActivity.this);
                        TextView text1 = new TextView(ViewContentsActivity.this);
                        TextView text2 = new TextView(ViewContentsActivity.this);
                        TextView text3 = new TextView(ViewContentsActivity.this);

                        text1.setGravity(Gravity.CENTER);
                        text1.setTextSize(12.0f);
                        text1.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text1.setTypeface(null, Typeface.BOLD);
                        text1.setText(id);
                        row.addView(text1);

                        text2.setGravity(Gravity.CENTER);
                        text2.setTextSize(12.0f);
                        text2.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text2.setTypeface(null, Typeface.BOLD);
                        text2.setText(name);
                        row.addView(text2);

                        text3.setGravity(Gravity.CENTER);
                        text3.setTextSize(12.0f);
                        text3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        text3.setTypeface(null, Typeface.BOLD);
                        text3.setText(date.substring(0, 10));
                        row.addView(text3);


                        journals.addView(row);

                    }
                } catch (Exception e) {
                    error += e.getMessage();
                }
                // refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });

    }


}
