package com.starwarcasting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starwarcasting.model.DataStarWarCast;

import java.util.ArrayList;

/**
 * This is adapter class for Star War character listing
 */
public class AdapterCastListing extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * This is used to inflate rows for Listing of Character
     */
    private LayoutInflater             inflater;
    /**
     * This is callback object and is used to  pass selected object to calling Activity
     */
    private Callback                   callback;
    /**
     * This is data variable holding list of Star war characters
     */
    private ArrayList<DataStarWarCast> dataList;

    public AdapterCastListing(Context context, ArrayList<DataStarWarCast> listVendor, Callback callback) {
        this.callback = callback;
        this.dataList = listVendor;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_starwar_cast_list, parent, false);
        return new HolderCastList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        HolderCastList holder = (HolderCastList) viewHolder;
        holder.tvName.setText(dataList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * This is View holder class to show rows for List of Characters screen
     */
    class HolderCastList extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;

        HolderCastList(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (callback != null) {
                callback.onSelected(dataList.get(getAdapterPosition()));
            }
        }
    }

    /**
     * This is callback interface to return selected value object
     */
    public interface Callback {

        void onSelected(DataStarWarCast data);
    }

}
