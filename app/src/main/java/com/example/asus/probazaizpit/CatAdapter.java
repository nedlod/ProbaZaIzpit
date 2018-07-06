package com.example.asus.probazaizpit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private CatViewHolder.ButtonClickListener listener;
    private List <Cat> data;

    public CatAdapter(CatViewHolder.ButtonClickListener listener) {
        this.listener = listener;
        this.data = new ArrayList <>();
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.layout_item_cat, parent, false );
        return new CatViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final CatViewHolder holder, final int position) {
        LoadImageTask loadImageTask = new LoadImageTask( holder.imgItemCat );
        loadImageTask.execute( data.get( position ).getImgUrl() );

        holder.txtItemName.setText( data.get( position ).getTxtNameCat() );
        holder.txtItemInfo.setText( data.get( position ).getTxtInfoCat());

        if (data.get( position).isLiked()) {
            holder.btnItemCatLike.setText("DISLIKE");
        } else {
            holder.btnItemCatLike.setText("LIKE" );
        }
        holder.btnItemCatLike.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.likeButtonClick( data.get( position ), holder.btnItemCatLike );
            }
        } );
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public void setData(List <Cat> cats) {
        this.data = cats;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgItemCat;
        private TextView txtItemName;
        private TextView txtItemInfo;
        private Button btnItemCatLike;


        public CatViewHolder(View itemView) {
            super( itemView );

            imgItemCat = itemView.findViewById( R.id.img_item_city );
            txtItemName = itemView.findViewById( R.id.txt_item_city_name );
            txtItemInfo = itemView.findViewById( R.id.txt_item_city_info );
            btnItemCatLike = itemView.findViewById( R.id.btn_item_city_like );
        }

        public interface ButtonClickListener {
            void likeButtonClick(Cat cat, Button button);
        }

        public Button getButton() {
            return btnItemCatLike;
        }
    }
}