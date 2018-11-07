package com.example.david.apirestconsumeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.david.apirestconsumeexample.Interfaces.BookService;
import com.example.david.apirestconsumeexample.Models.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDetailsActivity extends AppCompatActivity {


    ImageButton saveBtn;
    ImageButton deleteBtn;
    EditText isbnTxt;
    EditText titleTxt;
    EditText descriptionTxt;
    EditText authorTxt;
    EditText publisherTxt;
    EditText publishedYearTxt;
    List<Book> booksList;
    Switch updateSwitch;

    String isbn;
    String title;
    String description;
    String author;
    String publisher;
    String publishedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        booksList = new ArrayList<>();
        isbnTxt= findViewById(R.id.isbnTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        authorTxt = findViewById(R.id.authorTxt);
        publisherTxt = findViewById(R.id.publisherTxt);
        publishedYearTxt = findViewById(R.id.publishedYearTxt);
        saveBtn = findViewById(R.id.saveBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        updateSwitch = findViewById(R.id.updateSwitch);



        if(getIntent().getStringExtra("isNew").equals("true")){
            clearFields();
            updateSwitch.setEnabled(false);
            deleteBtn.setEnabled(false);
            deleteBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }else{
            isbnTxt.setText(getIntent().getStringExtra("isbn"));
            authorTxt.setText(getIntent().getStringExtra("author"));
            descriptionTxt.setText(getIntent().getStringExtra("description"));
            titleTxt.setText(getIntent().getStringExtra("title"));
            publisherTxt.setText(getIntent().getStringExtra("publisher"));
            publishedYearTxt.setText(getIntent().getStringExtra("publishedYear"));

            disableBookDetailsFields();

            saveBtn.setEnabled(false);
            saveBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            deleteBtn.setEnabled(false);
            deleteBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

            updateSwitch.setOnCheckedChangeListener(switchListener);

        }

    }

    private Switch.OnCheckedChangeListener switchListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.isChecked()){
                enableBookDetailsFields();
                saveBtn.setEnabled(true);
                saveBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                deleteBtn.setEnabled(true);
                deleteBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            else {
                disableBookDetailsFields();
                saveBtn.setEnabled(false);
                saveBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                deleteBtn.setEnabled(false);
                deleteBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    };


    public void getTextFromFields(){
        isbn=isbnTxt.getText().toString().trim();
        title = titleTxt.getText().toString().trim();
        description = descriptionTxt.getText().toString().trim();
        author = authorTxt.getText().toString().trim();
        publisher = publisherTxt.getText().toString().trim();
        publishedYear = publishedYearTxt.getText().toString().trim();

    }

    public void disableBookDetailsFields(){
        isbnTxt.setEnabled(false);
        titleTxt.setEnabled(false);
        descriptionTxt.setEnabled(false);
        authorTxt.setEnabled(false);
        publishedYearTxt.setEnabled(false);
        publisherTxt.setEnabled(false);

    }
    public void enableBookDetailsFields(){
        isbnTxt.setEnabled(true);
        titleTxt.setEnabled(true);
        descriptionTxt.setEnabled(true);
        authorTxt.setEnabled(true);
        publishedYearTxt.setEnabled(true);
        publisherTxt.setEnabled(true);
    }

    public void clearFields(){
        isbnTxt.setText("");
        authorTxt.setText("");
        descriptionTxt.setText("");
        titleTxt.setText("");
        publisherTxt.setText("");
        publishedYearTxt.setText("");
    }

    public void deleteBook(View view){
        String _id = getIntent().getStringExtra("_id");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.150:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService bookService = retrofit.create(BookService.class);
        Call<Book> call = bookService.delete(_id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Toast.makeText(BookDetailsActivity.this,"Book deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(BookDetailsActivity.this,"Cannot delete book",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void updateOrSaveBook(View view){
        String _id = getIntent().getStringExtra("_id");
         if(updateSwitch.isChecked()){
            getTextFromFields();
            Retrofit retrofit = new Retrofit.Builder()
                     .baseUrl("http://192.168.100.150:3000")
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
            BookService bookService = retrofit.create(BookService.class);
            Call<Book> call = bookService.updateBook(_id,new Book(null,isbn,title,description,author,publisher,publishedYear,null));
            call.enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    Log.i("DMO","Succed");
                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
                    Log.i("Error","FAILED: "+t.getMessage());
                }
            });
        }else{
             getTextFromFields();
             savePost(isbn,title,description,author,publisher,publishedYear);
        }
    }

    public void savePost(String isbn, String title, String description, String author, String publisher, String publisedYear){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.150:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService bookService = retrofit.create(BookService.class);
        Call<Book> call = bookService.savePost(isbn,title,description,author,publisher,publisedYear);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    Log.i("DMO","SUCCED:"+response.body().toString());
                    Toast.makeText(BookDetailsActivity.this, "New book saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.i("DMO","ERROR!!"+t.getMessage());
                Toast.makeText(BookDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
