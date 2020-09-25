package com.ahsailabs.kasirsederhana.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahsailabs.kasirsederhana.LoginActivity;
import com.ahsailabs.kasirsederhana.R;
import com.ahsailabs.kasirsederhana.configs.Config;
import com.ahsailabs.kasirsederhana.ui.category.models.AddCategoryResponse;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryItem;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryResponse;
import com.ahsailabs.kasirsederhana.utils.HttpUtil;
import com.ahsailabs.kasirsederhana.utils.InfoUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {
    private CategoryViewModel categoryViewModel;

    @BindView(R.id.tieName) TextInputEditText tieName;
    @BindView(R.id.mbtnSave) MaterialButton mbtnSave;
    @BindView(R.id.rvCategory) RecyclerView rvCategory;
    @BindView(R.id.tilName) TextInputLayout tilName;

    private List<CategoryItem> categoryItemList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =  new ViewModelProvider(this).get(CategoryViewModel.class);
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadViews(view);
        setupViews(view);
    }

    private void setupViews(View view) {
        categoryAdapter = new CategoryAdapter(view.getContext(), categoryItemList);
        rvCategory.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvCategory.setAdapter(categoryAdapter);


        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSendData();
            }
        });
    }

    private void validateAndSendData() {
        String strName = tieName.getText().toString();
        if(strName.isEmpty()){
            tilName.setError("nama category tidak boleh kosong");
            return;
        }

        sendData(strName);
    }

    private void sendData(String strName) {
        AndroidNetworking.post(Config.getAddCategoryUrl())
                .setOkHttpClient(HttpUtil.getCLient(getActivity()))
                .addBodyParameter("name", strName)
                .setPriority(Priority.HIGH)
                .setTag("add-category")
                .build()
                .getAsObject(AddCategoryResponse.class, new ParsedRequestListener<AddCategoryResponse>() {
                    @Override
                    public void onResponse(AddCategoryResponse response) {
                        if(response.getStatus()==1){
                            CategoryItem categoryItem = response.getData();
                            categoryItemList.add(0, categoryItem);
                            categoryAdapter.notifyItemInserted(0);
                        } else {
                            InfoUtil.showToast(getActivity(), response.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        InfoUtil.showToast(getActivity(), anError.getMessage());
                    }
                });
    }

    private void loadViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadData();
    }

    private void loadData() {
        AndroidNetworking.get(Config.getCategoryListUrl())
                .setOkHttpClient(HttpUtil.getCLient(getActivity()))
                .setPriority(Priority.HIGH)
                .setTag("category-list")
                .build()
                .getAsObject(CategoryResponse.class, new ParsedRequestListener<CategoryResponse>() {
                    @Override
                    public void onResponse(CategoryResponse response) {
                        if(response.getStatus()==1){
                            categoryItemList.addAll(response.getData());
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            InfoUtil.showToast(getActivity(), response.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        InfoUtil.showToast(getActivity(), anError.getMessage());
                        if(anError.getErrorCode()==401){
                            LoginActivity.start(getActivity());
                            getActivity().finish();
                        }
                    }
                });

    }
}