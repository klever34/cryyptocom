package com.app.crypto.cryptoalc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.crypto.cryptoalc.R;
import com.app.crypto.cryptoalc.model.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 26/Oct/2017.
 */

public class BTCAdapter extends RecyclerView.Adapter<BTCAdapter.BTCHolder> {
    private List<ListItem> listData;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback{
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback){
        this.itemClickCallback = itemClickCallback;
    }

    public void setListData(ArrayList<ListItem> exerciseList){
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    public BTCAdapter(List<ListItem> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public BTCHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new BTCHolder(view);
    }

    @Override
    public void onBindViewHolder(BTCAdapter.BTCHolder holder, int position) {
    ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class BTCHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView subTitle;
        private View container;

        public BTCHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.lbl_item_text);
            subTitle = (TextView) itemView.findViewById(R.id.lbl_item_sub_title);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.cont_item_root){
                itemClickCallback.onItemClick(getAdapterPosition());
            }else {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }
}
