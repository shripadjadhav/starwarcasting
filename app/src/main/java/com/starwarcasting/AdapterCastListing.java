package com.starwarcasting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starwarcasting.model.DataStarWarCast;

import java.util.ArrayList;

public class AdapterCastListing extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // UI control
    private Context                    context;
    private LayoutInflater             inflater;
    // Callback
    private Callback                   callback;
    // data variable
    private ArrayList<DataStarWarCast> dataList;

    public AdapterCastListing(Context context, ArrayList<DataStarWarCast> listVendor, Callback callback) {
        this.context = context;
        this.callback = callback;
        this.dataList = listVendor;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_starwar_cast_list, parent, false);
        return new HolderCastList(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HolderCastList holder = (HolderCastList) viewHolder;
        holder.tvName.setText(dataList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

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

    public interface Callback {

        void onSelected(DataStarWarCast data);
    }

}
