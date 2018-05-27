package com.example.rudy.mediaplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Rudy on 10.04.2018.
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<Song> itemList;
    private LayoutInflater inflater;
    private int lastPlayed;
    private Button lastUsed;

    public ListViewAdapter(Context context, ArrayList<Song> itemList){
        this.itemList = itemList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflater = LayoutInflater.from(context); //tu moze byc problem
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.single_list_item, null);
            holder = new ViewHolder();
            setHolderViews(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        setTags(holder, convertView);

        holder.sampleButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //View tempView = (View) holder.sampleButton.getTag(R.integer.button);
                //buttonService(holder.sampleButton, position);
                playSong(position, v);
            }
        });

        setHolderFields(holder, position);

        return convertView;
    }

    public void setHolderViews(ViewHolder holder, View convertView){
        holder.sampleTitle = convertView.findViewById(R.id.textViewTitleList);
        holder.sampleDescription = convertView.findViewById(R.id.textViewAuthor);
        holder.sampleTime = convertView.findViewById(R.id.textViewTime);
        holder.sampleButton = convertView.findViewById(R.id.buttonPlay);

    }

    public void setHolderFields(ViewHolder holder, int position){
        holder.sampleTitle.setText(itemList.get(position).getSongTitle());
        holder.sampleDescription.setText(itemList.get(position).getSongAuthor());
        holder.sampleTime.setText(itemList.get(position).getTime());
    }

    public void setTags(ViewHolder holder, View convertView){
        holder.sampleButton.setTag(R.integer.button, convertView);
    }

    public void playSong(int songIndex, View v){
        MainActivity.titleTextView.setText(itemList.get(songIndex).getSongTitle());
        if(BackgroundServiceInstance.getInstance().isPlaying()){
            BackgroundServiceInstance.getInstance().stopSong();
            BackgroundServiceInstance.getInstance().playSong(v.getContext(), songIndex); //v.getContext() setMediaPlayer(MediaPlayer.create(v.getContext(), itemList.get(songIndex).getId()));
            lastPlayed = songIndex;
            MainActivity.playStopButton.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp);
        }
        else {
            BackgroundServiceInstance.getInstance().playSong();
            if(lastPlayed != songIndex) lastPlayed = songIndex;
            MainActivity.playStopButton.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp);
        }
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    static class ViewHolder{
        TextView sampleTitle, sampleDescription, sampleTime;
        Button sampleButton;
    }

}
