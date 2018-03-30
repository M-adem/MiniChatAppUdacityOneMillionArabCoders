package com.minichatapp.android.minichatapp;

/**
 * Created by mery on 27/03/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;

    public static final int SENDER = 0;
    public static final int RECIPIENT = 1;

    public MessageAdapter(Context context, List<Message> messages) {
        messageList = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mTextDate;
        public ImageView mTextIcon;
        public ImageView mPhotoMessage;

        public ViewHolder(LinearLayout v) {
            super(v);
            mTextView = v.findViewById(R.id.mTextMessage);
            mTextDate = v.findViewById(R.id.mTextDate);
            mTextIcon = v.findViewById(R.id.mTextIcon);
            mPhotoMessage = v.findViewById(R.id.mPhotoMessage);

        }
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_rec, parent, false);
            ViewHolder vh = new ViewHolder((LinearLayout) v);
            return vh;
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_send, parent, false);
            ViewHolder vh = new ViewHolder((LinearLayout) v);
            return vh;
        }
    }

    public void remove(int pos) {
        int position = pos;
        messageList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, messageList.size());

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(messageList.get(position).getMessage());
        holder.mTextDate.setText(AppTools.getDateStringMessageText(messageList.get(position).getSendDate()));
        holder.mPhotoMessage.setImageBitmap(messageList.get(position).getPhoto());

        int stat=messageList.get(position).getStat();
        if(stat==1){
            holder.mTextIcon.setImageResource(R.drawable.msg_status_gray_waiting);
        }
        if(stat==2){
            holder.mTextIcon.setImageResource(R.drawable.msg_status_client_received);
        }
        if(stat==3){
            holder.mTextIcon.setImageResource(R.drawable.msg_status_client_read);
        }
        if(stat!=3){
            messageList.get(position).setStat(stat+1);
        }


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        if (message.getSenderName().equals("user1")) {
            return SENDER;
        } else {
            return RECIPIENT;
        }

    }

}