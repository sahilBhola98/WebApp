package com.example.webapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapp.Model.Book;

import java.util.LinkedList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private final LinkedList<Book> bookList;
    private LayoutInflater mInflater;


    public BookListAdapter(Context context  , LinkedList<Book> bookList){
        mInflater = LayoutInflater.from(context);
        this.bookList=bookList;
    }
    class BookViewHolder extends RecyclerView.ViewHolder{
        public final TextView mBookText , mBookAuthor;
        final BookListAdapter mAdapter;

        public BookViewHolder(@NonNull View itemView , BookListAdapter adapter) {
            super(itemView);
            mBookText = (TextView) itemView.findViewById(R.id.titleList);
            mBookAuthor = (TextView) itemView.findViewById(R.id.authorList);
            this.mAdapter=adapter;
        }

    }
    @Override
    @NonNull
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mBookView = mInflater.inflate(R.layout.book_list_item,parent,false);
        return new BookViewHolder(mBookView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book mCurrent = bookList.get(position);
        holder.mBookText.setText(mCurrent.getTitle());
        holder.mBookAuthor.setText(mCurrent.getAuthor());
    }



    @Override
    public int getItemCount() {
        return bookList.size();
    }

}
