package com.example.david.apirestconsumeexample.Interfaces;
import com.example.david.apirestconsumeexample.Models.Book;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;


public interface BookService {
    String API_ROUTE ="/api";

    @GET(API_ROUTE)
    Call<List<Book>> getBooks();

    @POST(API_ROUTE)
    @FormUrlEncoded
    Call<Book> savePost(@Field("isbn")String isbn,
                        @Field("title")String title,
                        @Field("description")String description,
                        @Field("author")String author,
                        @Field("publisher")String publisher,
                        @Field("published_year")String published_year);

    @PUT(API_ROUTE+"/{id}")
    Call<Book> updateBook(@Path("id")String bookId,
                          @Body Book book);

    @DELETE(API_ROUTE+"/{id}")
    Call<Book> delete(@Path("id") String bookId);
}
