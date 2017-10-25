package ru.askar.appatlantteam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.askar.appatlantteam.loaders.LoadItem;
import ru.askar.appatlantteam.manager.RecyclerViewAdapter;
import ru.askar.appatlantteam.models.Comment;
import ru.askar.appatlantteam.models.Photo;
import ru.askar.appatlantteam.models.Post;
import ru.askar.appatlantteam.models.Todo;
import ru.askar.appatlantteam.models.User;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public static final int LOAD_POST = 1;
    public static final int LOAD_COMMENT = 2;
    public static final int LOAD_PHOTO = 3;
    public static final int LOAD_TODO = 4;
    public static final int LOAD_USER = 5;
    public static final int LOAD_USERS = 6;

    EditText etPostID, etCommentID;
    TextView tvTextPost, tvTextComment, tvTextTodo;
    Button btnPost, btnComment;
    Button btnBack, btnNext;
    ImageView image;
    View fragment;
    Post post, postComment;
    Comment comment;
    User user;
    User userPost, userTodo;
    ArrayList<User> users;
    Photo photo;
    Todo todo;

    private int postID;
    private int commentID;
    private int userID = 1;
    private int photoID = 3;
    private int todoID;

    private RecyclerView rvUsers;
    private RecyclerViewAdapter adapter;
    private RecyclerView.ItemAnimator itemAnimator;
    private LinearLayoutManager layoutManager;

    Bundle bundle = null;
    Loader<String> loader;
    List<Loader<String>> loaders;
    boolean flgStartPost, flgStartComment, flgStartUser, flgStartTodo, flgStartUsers = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPostID = (EditText) findViewById(R.id.etPost);
        etCommentID = (EditText) findViewById(R.id.etComment);
        tvTextPost = (TextView) findViewById(R.id.tvTextPost);
        tvTextComment = (TextView) findViewById(R.id.tvTextComment);
        tvTextTodo = (TextView) findViewById(R.id.tvTextTodo);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnComment = (Button) findViewById(R.id.btnComment);
        rvUsers = (RecyclerView) findViewById(R.id.rvUsers);
        image = (ImageView) findViewById(R.id.ivImage);

        fragment = findViewById(R.id.fragment1);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPost();
            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadComment();
            }
        });
        bundle = new Bundle();
        etPostID.clearFocus();
        btnBack.setClickable(false);
        btnNext.setClickable(true);
        btnNext.setBackgroundColor(Color.rgb(0,255,0));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveNext();
            }
        });

        if (savedInstanceState == null) {
            users = new ArrayList<>();
            loader = getSupportLoaderManager().initLoader(LOAD_USERS, bundle, this);
            loader.forceLoad();

            loader = getSupportLoaderManager().initLoader(LOAD_PHOTO, bundle, this);
            loader.forceLoad();

            Random random = new Random();
            todoID = random.nextInt(200);
        }else{
            users.add((User) savedInstanceState.getSerializable("user1"));
            users.add((User) savedInstanceState.getSerializable("user2"));
            users.add((User) savedInstanceState.getSerializable("user3"));
            users.add((User) savedInstanceState.getSerializable("user4"));
            users.add((User) savedInstanceState.getSerializable("user5"));
            userPost = (User) savedInstanceState.getSerializable("userPost");
            userTodo = (User) savedInstanceState.getSerializable("userTodo");
            postComment = (Post) savedInstanceState.getSerializable("postComment");
            post = (Post) savedInstanceState.getSerializable("post");
            comment = (Comment) savedInstanceState.getSerializable("comment");
            photo = (Photo) savedInstanceState.getSerializable("photo");
            todo = (Todo) savedInstanceState.getSerializable("todo");

            adapter = new RecyclerViewAdapter(users);
            layoutManager = new LinearLayoutManager(this);
            itemAnimator = new DefaultItemAnimator();

            rvUsers.setAdapter(adapter);
            rvUsers.setLayoutManager(layoutManager);
            rvUsers.setItemAnimator(itemAnimator);
            if (postComment != null)
                tvTextComment.setText("post title: " + postComment.getTitle() + "\nCOMMENT:\nname: " + comment.getName() + "\ntext: " + comment.getText());
            if (userPost != null)
                tvTextPost.setText("user: " + userPost.getName() + "\ntitle: " + post.getTitle() + "\ntext: " + post.getText());
            if (userTodo != null)
                tvTextTodo.setText("user: " + userTodo.getName() + "\ntitle: " + todo.getTitle() + "\ncompleted: " + todo.getCompleted());
            Picasso.with(this).load(photo.getThumbnailUrl()).into(image);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        flgStartUsers = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("user1", users.get(0));
        outState.putSerializable("user2", users.get(1));
        outState.putSerializable("user3", users.get(2));
        outState.putSerializable("user4", users.get(3));
        outState.putSerializable("user5", users.get(4));
        outState.putSerializable("userPost", userPost);
        outState.putSerializable("userTodo", userTodo);
        outState.putSerializable("postComment", postComment);
        outState.putSerializable("post", post);
        outState.putSerializable("comment", comment);
        outState.putSerializable("photo", photo);
        outState.putSerializable("todo", todo);
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (id == LOAD_POST)
            loader = new LoadItem(this, "posts/" + postID);
        if (id >= LOAD_USER)
            loader = new LoadItem(this, "users/" + userID);
        if (id == LOAD_COMMENT)
            loader = new LoadItem(this, "comments/" + commentID);
        if (id == LOAD_PHOTO)
            loader = new LoadItem(this, "photos/" + photoID);
        if (id == LOAD_TODO)
            loader = new LoadItem(this, "todos/" + todoID);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Gson gson = new Gson();
        switch (loader.getId()){
            case LOAD_POST: {
                flgStartPost = true;
                post = gson.fromJson(data, Post.class);
                if (post == null){
                    Toast.makeText(this, "Нет данных по указанному номеру поста", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!flgStartComment) {
                    userID = post.getUserId();
                    loader = getSupportLoaderManager().initLoader(LOAD_USER, bundle, this);
                    loader.forceLoad();
                }else{
                    postComment = new Post(post.getUserId(), post.getId(), post.getTitle(), post.getText());
                    tvTextComment.setText("post title: " + post.getTitle() + "\nCOMMENT:\nname: " + comment.getName() + "\ntext: " + comment.getText());
                    flgStartComment = false;
                }
            }break;
            case LOAD_USER: {
                flgStartUser = true;
                user = gson.fromJson(data, User.class);
                if (user == null){
                    Toast.makeText(this, "Нет данных по указанному пользователю", Toast.LENGTH_LONG).show();
                    return;
                }
                if (flgStartPost && !flgStartUsers) {
                    userPost = new User(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getAddress(), user.getPhone(), user.getWebsite(), user.getCompany());
                    tvTextPost.setText("user: " + user.getName() + "\ntitle: " + post.getTitle() + "\ntext: " + post.getText());
                    flgStartPost = false;
                }
                if (flgStartTodo && !flgStartUsers) {
                    userTodo = new User(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getAddress(), user.getPhone(), user.getWebsite(), user.getCompany());
                    tvTextTodo.setText("user: " + user.getName() + "\ntitle: " + todo.getTitle() + "\ncompleted: " + todo.getCompleted());
                    flgStartTodo = false;
                }
            }break;
            case LOAD_TODO: {
                flgStartTodo = true;
                todo = gson.fromJson(data, Todo.class);
                if (todo == null){
                    Toast.makeText(this, "Нет данных по указанномой задаче", Toast.LENGTH_LONG).show();
                    return;
                }
                userID = todo.getUserId();
                loader = getSupportLoaderManager().initLoader(LOAD_USER, bundle, this);
                loader.forceLoad();

            }break;
            case LOAD_COMMENT: {
                flgStartComment = true;
                comment = gson.fromJson(data, Comment.class);
                if (comment == null){
                    Toast.makeText(this, "Нет данных по указанному комментарию", Toast.LENGTH_LONG).show();
                    return;
                }
                postID = comment.getPostId();
                loader = getSupportLoaderManager().initLoader(LOAD_POST, bundle, this);
                loader.forceLoad();
            }break;
            case LOAD_PHOTO: {
                photo = gson.fromJson(data, Photo.class);
                if (photo == null){
                    Toast.makeText(this, "Нет данных по указанному фото", Toast.LENGTH_LONG).show();
                    return;
                }
                Picasso.with(this).load(photo.getThumbnailUrl()).into(image);
            }break;
            default: {
                user = gson.fromJson(data, User.class);
                if (user == null){
                    Toast.makeText(this, "Нет данных по указанному пользователю", Toast.LENGTH_LONG).show();
                    return;
                }
                users.add(user);
                if (userID == 5){
                    adapter = new RecyclerViewAdapter(users);
                    layoutManager = new LinearLayoutManager(this);
                    itemAnimator = new DefaultItemAnimator();

                    rvUsers.setAdapter(adapter);
                    rvUsers.setLayoutManager(layoutManager);
                    rvUsers.setItemAnimator(itemAnimator);

                    loader = getSupportLoaderManager().initLoader(LOAD_TODO, bundle, this);
                    loader.forceLoad();
                }else{
                    userID++;
                    loader = getSupportLoaderManager().initLoader(LOAD_USER + userID, bundle, this);
                    loader.forceLoad();
                }
            }
        }

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void loadPost() {
        postID = Integer.parseInt(etPostID.getText().toString());
        if (postID > 100) {
            Toast.makeText(this, "Номер поста не должен превышать 100", Toast.LENGTH_LONG).show();
            return;
        }
        loader = getSupportLoaderManager().initLoader(LOAD_POST, bundle, this);
        loader.forceLoad();
    }

    public void loadComment(){
        commentID = Integer.parseInt(etCommentID.getText().toString());
        if (commentID > 500) {
            Toast.makeText(this, "Номер коментария не должен превышать 500", Toast.LENGTH_LONG).show();
            return;
        }
        loader = getSupportLoaderManager().initLoader(LOAD_COMMENT, bundle, this);
        loader.forceLoad();
    }

    public void loadDefault(){

    }

    public void moveNext(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}
