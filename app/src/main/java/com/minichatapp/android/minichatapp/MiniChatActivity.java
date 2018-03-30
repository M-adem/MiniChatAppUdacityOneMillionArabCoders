package com.minichatapp.android.minichatapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MiniChatActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private List messageList = new ArrayList();
    private EditText editText;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_chat);

        Toolbar mTopToolbar = findViewById(R.id.my_toolbar);

        this.setSupportActionBar(mTopToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String t= AppTools.getCurrentDefaultLocaleStr(this);

        mRecyclerView = findViewById(R.id.listView_messages_chat);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MessageAdapter(getBaseContext(), messageList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final Context context = this;

        scrollToLast();

        FrameLayout sendButton = findViewById(R.id.frame);
        editText= findViewById(R.id.messageEditText);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
                if(!msg.equals("")){
                    messageList.add(new Message(messageList.size()+1,msg, "user1"));
                    editText.setText("");
                    scrollToLast();

                    final Handler ha2=new Handler();
                    ha2.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Message send = (Message) messageList.get(messageList.size()-1);
                            Message sendClient = new Message(send.getId()+1,send.getMessage(),"user2");
                            sendClient.setStat(1);
                            sendClient.setSendDate(new Date());

                            messageList.add(sendClient);
                            editText.setText("");
                            scrollToLast();

                        }
                    }, 5000);

                }
            }
        });


        ImageView camVideoButton = findViewById(R.id.camVideoButton);
        camVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //show dialog cam or video
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);

                TextView photo = dialog.findViewById(R.id.photo);
                TextView video = dialog.findViewById(R.id.video);


                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                         dialog.dismiss();
                    }
                });

                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        refrechList();


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            String msg = editText.getText().toString();

                Message messageSend=new Message(messageList.size() + 1, msg, "user1");
                messageSend.setPhoto(photo);
                messageList.add(messageSend);
                editText.setText("");
                scrollToLast();

            final Handler ha2=new Handler();
            ha2.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Message send = (Message) messageList.get(messageList.size()-1);
                    Message sendClient = new Message(send.getId()+1,send.getMessage(),"user2");
                    sendClient.setPhoto(send.getPhoto());

                    sendClient.setStat(1);
                    sendClient.setSendDate(new Date());

                    messageList.add(sendClient);
                    editText.setText("");
                    scrollToLast();

                }
            }, 5000);
        }
    }
    private void refrechList(){
        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                mAdapter.notifyDataSetChanged();

                ha.postDelayed(this, 3000);
            }
        }, 3000);
    }
    private void scrollToLast(){
        mRecyclerView.scrollToPosition(messageList.size()-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            Toast.makeText(this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
