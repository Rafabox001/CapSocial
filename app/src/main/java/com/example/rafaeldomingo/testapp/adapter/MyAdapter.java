
package com.example.rafaeldomingo.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaeldomingo.testapp.R;
import com.example.rafaeldomingo.testapp.data.ItemData;
import com.example.rafaeldomingo.testapp.listener.ItemOnClickHandler;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private ItemData[] datas;

    private ItemOnClickHandler itemOnClickHandler;

    public MyAdapter(ItemOnClickHandler itemOnClickHandler) {
        this.itemOnClickHandler = itemOnClickHandler;
    }

    public void setItemData(ItemData... datas){
        this.datas = datas;
        notifyDataSetChanged();

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.content_home,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemData itemData = this.datas[position];
        holder.bind(itemData);
    }


    @Override
    public int getItemCount() {
        if(this.datas == null) return 0;
        return datas.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemData itemData;

        ImageView mImageView;
        TextView tvTitle;
        TextView tvDescription;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.mImageView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClickHandler.onClick(itemData);

        }

        public void bind(ItemData itemData) {
            this.itemData = itemData;
            mImageView.setImageBitmap(itemData.getImageBitmap());
            tvTitle.setText(itemData.getTitle());
            tvDescription.setText(itemData.getDescription());
        }
    }

}
