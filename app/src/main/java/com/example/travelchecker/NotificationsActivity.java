package com.example.travelchecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.model.Message;
import com.example.travelchecker.Model.Presentor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private MenuItem mSettingsItem;
    private MenuItem mNotificationsItem;
    private MenuItem mLocatorItem;

    private Presentor presentor = new Presentor();

    private RecyclerView mMessageRecyclerView;
    private MessageAdapter mMessageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        getSupportActionBar(). hide();

        mMessageRecyclerView = (RecyclerView)findViewById(R.id.message_recycler_view);
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu);
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mSettingsItem = mBottomNavigationView.getMenu().getItem(0);
        mSettingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(NotificationsActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        mNotificationsItem = mBottomNavigationView.getMenu().getItem(1);
        mNotificationsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        mLocatorItem = mBottomNavigationView.getMenu().getItem(2);
        mLocatorItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(NotificationsActivity.this, LocatorActivity.class);
                startActivity(intent);
                return true;
            }
        });


        updateMessageUI();//sets up the person recycler view

    }

    private void updateMessageUI(){
        mMessageAdapter = new MessageAdapter(presentor.getMessages());
        mMessageRecyclerView.setAdapter(mMessageAdapter);
    }
    
    private class MessageAdapter extends RecyclerView.Adapter<MessageHolder>{
        private List<Message> messageList = new ArrayList<>();
        public MessageAdapter(List<Message> messages){//gets list of needed messages
            for(Message x : messages){
                messageList.add(0, x);
            }
        }
        @Override
        public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new MessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MessageHolder messageHolder, int position) {
            Message message = messageList.get(position);//gets wanted Message
            messageHolder.bind(message);
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }
    }

    private class MessageHolder extends RecyclerView.ViewHolder{

        TextView mMessageUsername;
        TextView mMessageTime;
        TextView mMessageMessage;

        private Message mMessage;

        ViewGroup mParent;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent){//wires up the widgets
            super(inflater.inflate(R.layout.message_list, parent, false));
            mParent = parent;
            mMessageUsername = itemView.findViewById(R.id.message_username);
            mMessageTime = itemView.findViewById(R.id.message_time);
            mMessageMessage = (TextView)itemView.findViewById(R.id.message);
        }

        public void bind(Message message){//sets view to display correct data
            mMessage = message;
            mMessageUsername.setText(mMessage.getUsername());
            mMessageTime.setText(mMessage.getTime());
            mMessageMessage.setText(mMessage.getMessage());
        }
    }
        
        
}
