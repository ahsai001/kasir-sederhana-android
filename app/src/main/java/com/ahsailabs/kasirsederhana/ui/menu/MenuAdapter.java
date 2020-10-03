package com.ahsailabs.kasirsederhana.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahsailabs.kasirsederhana.R;
import com.ahsailabs.kasirsederhana.configs.Config;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryItem;
import com.ahsailabs.kasirsederhana.ui.menu.models.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmad s on 25/09/20.
 */
class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{
    private List<MenuItem> menuItemList;
    private LayoutInflater layoutInflater;

    public MenuAdapter(Context context, List<MenuItem> menuItemList){
        this.menuItemList = menuItemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(layoutInflater.inflate(R.layout.menu_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItemList.get(position);
        holder.tvName.setText(menuItem.getName());
        holder.tvPrice.setText("Rp "+menuItem.getHarga());
        holder.tvCategory.setText(menuItem.getCategoryItem().getName());
        Picasso.get().load(Config.getImageUrl(menuItem.getPhoto())).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }


    public static class MenuViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ivImage) ImageView ivImage;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvPrice) TextView tvPrice;
        @BindView(R.id.tvCategory) TextView tvCategory;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
