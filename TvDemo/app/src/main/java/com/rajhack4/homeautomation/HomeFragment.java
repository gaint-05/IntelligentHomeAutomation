package com.rajhack4.homeautomation;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import static com.rajhack4.homeautomation.IRPattern.EIGHT_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.FIVE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.FOUR_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.NINE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.ONE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.SEVEN_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.SIX_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.THREE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.TWO_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.ZERO_PATTERN;


/**
 * Created by dbot_5 on 19-03-2018.
 */

public class HomeFragment extends Fragment {

    private static final String LOG_CAT = HomeFragment.class.getName() ;

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("HOME");
       final ConsumerIrManager irManager = (ConsumerIrManager) getActivity().getSystemService(Context.CONSUMER_IR_SERVICE);
        RecyclerView topRecylerView = view.findViewById(R.id.topRecyclerView);
        RecyclerView watchNextRecylerView = view.findViewById(R.id.watchNextRecyclerView);
        RecyclerView comedyRecylerView = view.findViewById(R.id.comedyRecyclerView);
        RecyclerView dramaRecylerView = view.findViewById(R.id.dramaRecyclerView);
        RecyclerView knowledgeRecyclerView = view.findViewById(R.id.knowledgeRecyclerView);
        RecyclerView musicRecyclerView = view.findViewById(R.id.musicRecyclerView);

        RecyclerView.LayoutManager topLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager watchNextLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager comedyLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager dramaLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager knowledgeLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager musicLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        TvShowAdapter.RecyclerViewClickListener topRecyclerListner = new TvShowAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                List<Tvshow> list = Utils.createTopList();
                Log.i("click",String.valueOf(list.get(position).getChannelNo()));
                setChannel(String.valueOf(list.get(position).getChannelNo()),irManager);
            }
        };
        TvShowAdapter.RecyclerViewClickListener watchRecyclerListner = new TvShowAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                List<Tvshow> list = Utils.createKidsList();
                Log.i("click",String.valueOf(list.get(position).getChannelNo()));
                setChannel(String.valueOf(list.get(position).getChannelNo()),irManager);
            }
        };
        TvShowAdapter.RecyclerViewClickListener comedyRecyclerListner = new TvShowAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                List<Tvshow> list = Utils.createComedyList();
                Log.i("click",String.valueOf(list.get(position).getChannelNo()));
                setChannel(String.valueOf(list.get(position).getChannelNo()),irManager);
            }
        };
        TvShowAdapter.RecyclerViewClickListener dramaRecyclerListner = new TvShowAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                List<Tvshow> list = Utils.createDramaList();
                Log.i("click",String.valueOf(list.get(position).getChannelNo()));
                setChannel(String.valueOf(list.get(position).getChannelNo()),irManager);
            }
        };
        TvShowAdapter.RecyclerViewClickListener knowledgeRecyclerListner = new TvShowAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                List<Tvshow> list = Utils.createKnowledgeList();
                Log.i("click",String.valueOf(list.get(position).getChannelNo()));
                setChannel(String.valueOf(list.get(position).getChannelNo()),irManager);
            }
        };
        TvShowAdapter.RecyclerViewClickListener musicRecyclerListner = new TvShowAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                List<Tvshow> list = Utils.createMusicList();
                Log.i("click",String.valueOf(list.get(position).getChannelNo()));
                setChannel(String.valueOf(list.get(position).getChannelNo()),irManager);
            }
        };


        TvShowAdapter topAdapter = new TvShowAdapter(getActivity(),Utils.createTopList(),topRecyclerListner);
        TvShowAdapter watchNextAdapter = new TvShowAdapter(getActivity(),Utils.createKidsList(),watchRecyclerListner);
        TvShowAdapter comedyAdapter = new TvShowAdapter(getActivity(),Utils.createComedyList(),comedyRecyclerListner);
        TvShowAdapter dramaAdapter = new TvShowAdapter(getActivity(),Utils.createDramaList(),dramaRecyclerListner);
        TvShowAdapter knowledgeAdapter = new TvShowAdapter(getActivity(),Utils.createKnowledgeList(),knowledgeRecyclerListner);
        TvShowAdapter musicAdapter = new TvShowAdapter(getActivity(),Utils.createMusicList(),musicRecyclerListner);

        topRecylerView.setLayoutManager(topLayoutManager);
        watchNextRecylerView.setLayoutManager(watchNextLayoutManager);
        comedyRecylerView.setLayoutManager(comedyLayoutManager);
        dramaRecylerView.setLayoutManager(dramaLayoutManager);
        knowledgeRecyclerView.setLayoutManager(knowledgeLayoutManager);
        musicRecyclerView.setLayoutManager(musicLayoutManager);

        topRecylerView.setAdapter(topAdapter);
        watchNextRecylerView.setAdapter(watchNextAdapter);
        comedyRecylerView.setAdapter(comedyAdapter);
        dramaRecylerView.setAdapter(dramaAdapter);
        knowledgeRecyclerView.setAdapter(knowledgeAdapter);
        musicRecyclerView.setAdapter(musicAdapter);

        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(topRecylerView);

        return view;
    }

    public  void setnum0Button(ConsumerIrManager irManager){
        irManager.transmit(36000, ZERO_PATTERN);
        Log.e(LOG_CAT,"ZeroButton");
    }
    public  void setnum1Button(ConsumerIrManager irManager){
        irManager.transmit(36000, ONE_PATTERN);
        Log.e(LOG_CAT,"OneButton");
    }
    public  void setnum2Button(ConsumerIrManager irManager){
        irManager.transmit(36000, TWO_PATTERN);
        Log.e(LOG_CAT,"TwoButton");
    }
    public   void setnum3Button(ConsumerIrManager irManager){
        irManager.transmit(36000, THREE_PATTERN);
        Log.e(LOG_CAT,"ThreeButton");
    }
    public  void setnum4Button(ConsumerIrManager irManager){
        irManager.transmit(36000, FOUR_PATTERN);
        Log.e(LOG_CAT,"FourButton");
    }
    public  void setnum5Button(ConsumerIrManager irManager){
        irManager.transmit(36000, FIVE_PATTERN);
        Log.e(LOG_CAT,"FiveButton");
    }
    public  void setnum6Button(ConsumerIrManager irManager){
        irManager.transmit(36000, SIX_PATTERN);
        Log.e(LOG_CAT,"SixButton");
    }
    public  void setnum7Button(ConsumerIrManager irManager){
        irManager.transmit(36000,SEVEN_PATTERN);
        Log.e(LOG_CAT,"SevenButton");
    }
    public  void setnum8Button(ConsumerIrManager irManager){
        irManager.transmit(36000,EIGHT_PATTERN);
        Log.e(LOG_CAT,"EightButton");

    }
    public   void setnum9Button(ConsumerIrManager irManager){
        irManager.transmit(36000, NINE_PATTERN);
        Log.e(LOG_CAT,"NineButton");
    }

    public  void setChannel(String s, ConsumerIrManager irManager){
        for(int i=0;i< s.length();i++){
            if(s.charAt(i) == '1')
                setnum1Button(irManager);
            else if(s.charAt(i) == '2')
                setnum2Button(irManager);
            else if(s.charAt(i) == '3')
                setnum3Button(irManager);
            else if(s.charAt(i) == '4')
                setnum4Button(irManager);
            else if(s.charAt(i) == '5')
                setnum5Button(irManager);
            else if(s.charAt(i) == '6')
                setnum6Button(irManager);
            else if(s.charAt(i) == '7')
                setnum7Button(irManager);
            else if(s.charAt(i) == '8')
                setnum8Button(irManager);
            else if(s.charAt(i) == '9')
                setnum9Button(irManager);
            else if(s.charAt(i) == '0')
                setnum0Button(irManager);

        }

    }
}
