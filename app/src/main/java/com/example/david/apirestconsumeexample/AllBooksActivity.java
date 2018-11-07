package com.example.david.apirestconsumeexample;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.david.apirestconsumeexample.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.david.apirestconsumeexample.Adapters.BooksAdapter;
import com.example.david.apirestconsumeexample.Interfaces.OnItemClickListener;
import com.example.david.apirestconsumeexample.Interfaces.BookService;
import com.example.david.apirestconsumeexample.Models.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllBooksActivity extends AppCompatActivity {
    FloatingActionButton refreshFB;
    ArrayList<Book> booksArrayList;

    Intent goToDetailsActivity;
    String isNew;

    BooksAdapter booksAdapter;
    RecyclerView bookRCV;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.book_options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.newBook:
                isNew="true";
                goToDetailsActivity.putExtra("isNew",isNew);
                startActivity(goToDetailsActivity);
                return true;
            default:
                return false;
        }
    }

    public void refreshBookList(View view){
        booksArrayList.clear();
        getBooks();
        Snackbar.make(refreshFB,"Book list updated",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        isNew = "true";

        booksArrayList = new ArrayList<>();
        goToDetailsActivity = new Intent(this,BookDetailsActivity.class);
        refreshFB = findViewById(R.id.refreshFB);
        bookRCV = findViewById(R.id.bookRCV);
        getBooks();
        bookRCV.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));

   }

    private void getBooks() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.150:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService bookService = retrofit.create(BookService.class);
        Call<List<Book>> call = bookService.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                for(Book book:response.body())
                {
                    booksArrayList.add(new Book(book.get_id(),
                            book.getIsbn(),
                            book.getTitle(),
                            book.getDescription(),
                            book.getAuthor(),
                            book.getPublisher(),
                            book.getPublished_year(),
                            book.getUpdated_date()));

                }
                booksAdapter = new BooksAdapter(getApplicationContext(),booksArrayList, listener);
                bookRCV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                bookRCV.setAdapter(booksAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.i("DMO",t.getMessage());

            }
        });
    }

    private OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(Book book) {
            isNew="";
            goToDetailsActivity.putExtra("_id",book.get_id());
            goToDetailsActivity.putExtra("isbn",book.getIsbn());
            goToDetailsActivity.putExtra("title",book.getTitle());
            goToDetailsActivity.putExtra("description",book.getDescription());
            goToDetailsActivity.putExtra("author",book.getAuthor());
            goToDetailsActivity.putExtra("publisher",book.getPublisher());
            goToDetailsActivity.putExtra("publishedYear",book.getPublished_year());
            goToDetailsActivity.putExtra("isNew",isNew);
            startActivity(goToDetailsActivity);
        }
    };
}
