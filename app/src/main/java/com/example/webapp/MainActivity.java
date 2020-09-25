package com.example.webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webapp.Model.Book;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    EditText editTextView;
    TextView textNameView , textAuthorView;
    Context context = this;
    private RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextView = findViewById(R.id.bookInput);
        textNameView = findViewById(R.id.titleText);
        textAuthorView = findViewById(R.id.authorText);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }
    public  void searchBooks(View view){
        String bookToSearch = editTextView.getText().toString();
        new FetchBook(textNameView,textAuthorView,context,mRecyclerView).execute(bookToSearch);
    }


}