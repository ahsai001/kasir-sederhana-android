package com.ahsailabs.kasirsederhana.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahsailabs.kasirsederhana.R;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmad s on 25/09/20.
 */
class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private List<CategoryItem> categoryItemList;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<CategoryItem> categoryItemList){
        this.categoryItemList = categoryItemList;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(layoutInflater.inflate(R.layout.category_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryItem categoryItem = categoryItemList.get(position);
        holder.tvName.setText(categoryItem.getName());
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvName) TextView tvName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
