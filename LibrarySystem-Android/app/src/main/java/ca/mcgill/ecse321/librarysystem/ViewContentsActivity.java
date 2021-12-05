package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

        displayBooks(findViewById(R.id.books_info));

        books = findViewById(R.id.books_table);
        albums = findViewById(R.id.albums_table);
        movies = findViewById(R.id.movies_table);
        newspapers = findViewById(R.id.newspapers_table);
        journals = findViewById(R.id.journals_table);
    }

    /**
     * Display all the books in the library
     * @author Julie
     */
    public void displayBooks(View v) {
        HttpUtils.get("items/books/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                try {
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
                        text2.setTextSize(12.0f);
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

    public void displayAlbums() {

    }

    public void displayMovies() {

    }

    public void displayNewspapers() {

    }

    public void displayJournals() {

    }
}
