package com.practice.jackchung.lexiangmusicplayer.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.practice.jackchung.lexiangmusicplayer.R;
import com.practice.jackchung.lexiangmusicplayer.activity.MusicPlayActivity;
import com.practice.jackchung.lexiangmusicplayer.adpater.LocalMusicAdapter;
import com.practice.jackchung.lexiangmusicplayer.entity.LocalMusic;
import com.practice.jackchung.lexiangmusicplayer.entity.NetMusic;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalMusicListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,LocalMusicAdapter.OnItemClickListener {


    private SwipeRefreshLayout swipeRefreshLayout_localMusicListFragment;
    private RecyclerView recyclerView_localMusicListFragment;

    private LocalMusicAdapter localMusicAdapter = null;

    private List<LocalMusic> localMusicList = null;

    public LocalMusicListFragment() {
        // Required empty public constructor
    }

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * Fragment对象启动时执行
     */
    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(1,null,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_localmusiclist, container, false);

        setSwipeRefreshLayout(view);
        setRecyclerView(view);

        return view;
    }

    private void setRecyclerView(View view) {
        recyclerView_localMusicListFragment = (RecyclerView) view.findViewById(R.id.recyclerView_LocalMusicListFragment);
        // 优化显示
        recyclerView_localMusicListFragment.setHasFixedSize(true);
        recyclerView_localMusicListFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_localMusicListFragment.setItemAnimator(new DefaultItemAnimator());
        localMusicList = new ArrayList<>();
        localMusicAdapter = new LocalMusicAdapter(getActivity());
        recyclerView_localMusicListFragment.setAdapter(localMusicAdapter);
        localMusicAdapter.setOnItemClickListener(this);
    }

    private void setSwipeRefreshLayout(View view) {
        swipeRefreshLayout_localMusicListFragment = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_LocalMusicListFragment);
        swipeRefreshLayout_localMusicListFragment.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark,null),
                getResources().getColor(android.R.color.holo_orange_light,null),
                getResources().getColor(android.R.color.white,null));
        swipeRefreshLayout_localMusicListFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLoaderManager().restartLoader(1,null,LocalMusicListFragment.this);
                swipeRefreshLayout_localMusicListFragment.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView_localMusicListFragment.setAdapter(localMusicAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id==1){
            return new CursorLoader(
                    getActivity(), //Context context
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,//Uri uri
                    null,//String[] projection
                    null,//String selection
                    null,//String[] selectionArgs
                    MediaStore.Audio.Media.DATE_ADDED+" desc "//String sortOrder:降序排序:注意desc两端的空格
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor==null){
            return;
        } else{
            while(cursor.moveToNext()) {
                localMusicList.add(new LocalMusic(
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                ));
            }
            Log.i("TAG.localMusicList",localMusicList.toString());
            localMusicAdapter.addLocalMusicList(localMusicList,true);
            localMusicAdapter.notifyDataSetChanged();
            //cursor.close();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        localMusicList.clear();
    }

    @Override
    public void onItemClickClick(View view, int position) {
        Toast.makeText(getActivity(),"position="+position,Toast.LENGTH_SHORT).show();
        LocalMusic currentMusic = localMusicList.get(position);
        Intent playIntent = new Intent(getActivity(), MusicPlayActivity.class);
        //设置当前要播放的音乐对象在整个列表中的位置
        playIntent.putExtra("position",position);
        playIntent.putExtra("musicList",(ArrayList)localMusicList);
        startActivity(playIntent);
    }
}
