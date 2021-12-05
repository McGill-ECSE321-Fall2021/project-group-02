package ca.mcgill.ecse321.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class ViewContentsActivity extends Activity {
    private TableLayout books, albums, movies, newspapers, journals;
    private String error = "";
    private String id = "";
    private String title = "";
    private String creator = "";
    private String date = "";
    private static TableRow row;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_page);

        books = findViewById(R.id.books_table);
        albums = findViewById(R.id.albums_table);
        movies = findViewById(R.id.movies_table);
        newspapers = findViewById(R.id.newspapers_table);
        journals = findViewById(R.id.journals_table);
    }

    /**
     * Redirects to the homepage
     * @param view
     *
     * @author Julie
     */
    public void homeRedirect(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
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

                        LayoutInflater inflater = LayoutInflater.from(ViewContentsActivity.this);
                        row = (TableRow) inflater.inflate(R.layout.viewcontents_page, null, false);

                        String id = response.getJSONObject(i).getString("id");
                        String title = response.getJSONObject(i).getString("title");
                        String author = response.getJSONObject(i).getString("author");
                        boolean available = response.getJSONObject(i).getBoolean("isBorrowable");

                        TextView text = (TextView) v.findViewById(R.id.book_id);
                        text.setText(id);

                        books.addView(row);

                    }
                } catch (Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

            @Override
            public void onFailure(JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
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

    /**
     * Refresh/clear the error message
     *
     * @author Julie
     */
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
