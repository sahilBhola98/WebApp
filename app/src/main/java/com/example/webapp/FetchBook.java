package com.example.webapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapp.Model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class FetchBook extends AsyncTask<String , Void , String> {

    private BookListAdapter mAdapter;
    Context context;
    TextView mTitleText , mAuthorText;
    private final LinkedList<Book> mBookList= new LinkedList<>();
    private int mBookCount = 0;
    RecyclerView mRecyclerView;

    public FetchBook(TextView mTitleText , TextView mAuthorText, Context context , RecyclerView view){
        this.mTitleText=mTitleText;
        this.mAuthorText=mAuthorText;
        this.context=context;
        this.mRecyclerView=view;
    }
    @Override

    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            for(int i=0;i<itemsArray.length();i++){
                JSONObject book = itemsArray.getJSONObject(i);
                String title = null;
                String author = null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                try{
                    title = volumeInfo.getString("title");
                    author = volumeInfo.getString("authors");
                    
                        mBookList.addLast(new Book(title, author));

                    mAdapter=new BookListAdapter(context,mBookList);

                    mRecyclerView.setAdapter(mAdapter);

                    mRecyclerView.setLayoutManager(new LinearLayoutManager(context));





                }catch (Exception e){
                    e.printStackTrace();
                }
                if(title!=null&&author!=null){
                    mTitleText.setText(title);
                    mAuthorText.setText(author);
                    return;
                }
                mTitleText.setText("No Result Fouhd");
                mAuthorText.setText("");
            }

        } catch (JSONException e) {

            mTitleText.setText("No Result Fouhd");
            mAuthorText.setText("");
            e.printStackTrace();
        }


    }
    public LinkedList<Book> getBook(){
        return mBookList;
    }

}
