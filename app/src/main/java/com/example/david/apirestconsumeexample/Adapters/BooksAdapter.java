package com.example.david.apirestconsumeexample.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.apirestconsumeexample.Models.Book;
import com.example.david.apirestconsumeexample.Interfaces.OnItemClickListener;
import com.example.david.apirestconsumeexample.R;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{

    private Context context;
    private ArrayList<Book> booksList;
    private final OnItemClickListener listener;



    public BooksAdapter(Context context, ArrayList<Book> booksList, OnItemClickListener listener) {
        this.context = context;
        this.booksList = booksList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BooksAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_details_layout,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.BookViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.bind(book,listener);
        holder.titleTV.setText(book.getTitle());
        holder.authorTV.setText(book.getAuthor());
        holder.publishedYearTV.setText(book.getPublished_year());

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder{

        TextView titleTV,authorTV,publishedYearTV;

        public BookViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            authorTV = itemView.findViewById(R.id.authorTV);
            publishedYearTV = itemView.findViewById(R.id.publishedYearTV);

        }

        public void bind(final Book book,final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(book);
                }
            });
        }
    }
}
