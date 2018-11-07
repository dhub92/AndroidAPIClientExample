# Android-Rest-Client-Example
A simple Android project example using an Rest Client called Retrofit, to consume an API Rest. 

## Retrofit
Retrofit is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp. 
You can find more information about this library [here](https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit).

## Prerequisites
* A backend with an API Rest enable. In this project, I used one that I found on Internet and it use [MEAN Stack](https://www.djamware.com/post/5b00bb9180aca726dee1fd6d/mean-stack-angular-6-crud-web-application).
* Android Studio 3.1.2 (version that I used)

## What can the app do?
* See a list a books
* Create a new book (isbn, title, author, book description, publisher, published year, update year)
* Update a book
* Delete a book

**Basically, everything that the Angular app can do.**

## Considerations
* You will see this line of code:
```
Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.150:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
```
Where **http://192.168.100.150:3000** is where the server is located. Repleace it with yours.

* For some reasons, the book list's item divider does not show when the app is running in a Lollipop (API 22) and Marshmallow (API 23) devices. I wil try to fix it.

