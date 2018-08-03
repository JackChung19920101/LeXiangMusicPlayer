package com.practice.jackchung.lexiangmusicplayer.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.practice.jackchung.lexiangmusicplayer.R;
import com.practice.jackchung.lexiangmusicplayer.entity.LocalMusic;
import com.practice.jackchung.lexiangmusicplayer.utils.DurationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Dell on 2018/7/27.
 */

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.MyLocalMusicViewHolder> implements View.OnClickListener {

    public Context context;
    //声明自定义的监听接口
    public OnItemClickListener mOnItemClickListener;
    private int position;

    public LocalMusicAdapter(Context context) {
        this.context = context;
    }



    private List<LocalMusic> localMusicList = new ArrayList<>();

    public void addLocalMusicList(List<LocalMusic> newLocalMusicList, boolean isClear){
        if(isClear){
            this.localMusicList.clear();
            this.notifyItemRangeChanged(0,newLocalMusicList.size(),null);
            Log.i("TAG","localMusicList is empty ? "+this.localMusicList.isEmpty());
        }
        this.localMusicList.addAll(newLocalMusicList);
        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        //将监听传递给自定义接口
        position = ((int) v.getTag());
        mOnItemClickListener.onItemClickClick(v, position);
    }

    public interface OnItemClickListener{
        // 监听自定义的接口
        void onItemClickClick(View view, int position);
    }

    // 设置监听自定义的接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

    private long getPosition(){
        return position;
    }

    @Override
    public MyLocalMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.inflate_localmusic_item_layout,parent,false);
        MyLocalMusicViewHolder holder = new MyLocalMusicViewHolder(itemView);
        //这里 我们可以拿到点击的item的view 对象，所以在这里给view设置点击监听，
        itemView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyLocalMusicViewHolder holder, int position) {
        LocalMusic localMusic = localMusicList.get(position);
        Log.i("TAG","onBindViewHolder");
        Log.i("TAG","localMusic="+localMusic.toString());
        holder.textView_MusicName.setText(localMusic.getTitle());
        holder.textView_SingerName.setText(localMusic.getArtist());
        holder.textView_TotalTime.setText(DurationUtil.formatTime(localMusic.getTotalTime()));

        holder.itemView.setTag(position);//给iremView设置tag以作为参数传递给监听回调方法中

    }

    @Override
    public int getItemCount() {
        return localMusicList.size();
    }

    public static class MyLocalMusicViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_MusicName;
        public TextView textView_SingerName;
        public TextView textView_TotalTime;

        public MyLocalMusicViewHolder(View itemView) {
            super(itemView);
            textView_MusicName = itemView.findViewById(R.id.textView_LocalMusic_ItemLayout_MusicName);
            textView_SingerName = itemView.findViewById(R.id.textView_LocalMusic_ItemLayout_Singer);
            textView_TotalTime = itemView.findViewById(R.id.textView_LocalMusic_ItemLayout_TotalTime);
        }
    }

}
