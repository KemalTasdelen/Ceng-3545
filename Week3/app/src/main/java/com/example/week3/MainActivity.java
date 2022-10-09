package com.example.week3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    btnPost = (Button) findViewById(R.id.btnPost);
    btnPost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivityForResult(intent, POST_REQUEST);
        }
    });

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POST_REQUEST && resultCode == Activity.RESULT_OK) {
            Post post = new Post();
            post.setMessage(data.getCharSequenceExtra("msg").toString());
            post.setImage((Bitmap) data.getParcelableExtra("bitmap"));
            posts.add(post);
            ((PostAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }



    ListView listView = (ListView) findViewById(R.id.listview);
    PostAdapter postAdapter = new PostAdapter(this, posts);
    listView.setAdapter(postAdapter);

    final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data =result.getData();
                        Post post = new Post();
                        post.setMessage(data.getCharSequenceExtra('msg').toString());
                        post.setImage((Bitmap) data.getParcelableExtra('bitmap'));
                        posts.add(post);
                        ((PostAdapter)) listView.getAdapter()).notifyDataChanged();
                    }
                }
            });

Button btnPost = findViewById(R.id.btnPost);
btnPost.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        launcher.launch(intent);
        }
    });
}

