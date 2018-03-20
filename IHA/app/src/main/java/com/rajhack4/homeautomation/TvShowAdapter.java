package com.rajhack4.homeautomation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by dbot_5 on 19-03-2018.
 */
public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder>  {
    List<Tvshow> mList;
    Context mContext;
    private RecyclerViewClickListener mClickListener;



    public TvShowAdapter(Context context,List<Tvshow> list,RecyclerViewClickListener clickListener) {
        super();
        this.mList=list;
        this.mClickListener=clickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_list,parent,false);
        return new ViewHolder(view,mClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int imageID = mList.get(position).getImage();
        holder.imageView.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        private RecyclerViewClickListener mListener;


        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.showImage);
            mListener = listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerViewClickListener {

        void onClick(int position);
    }
}
