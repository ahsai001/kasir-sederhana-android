package com.ahsailabs.kasirsederhana.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahsailabs.kasirsederhana.CategoryRepoResponse;
import com.ahsailabs.kasirsederhana.LoginActivity;
import com.ahsailabs.kasirsederhana.MainViewModel;
import com.ahsailabs.kasirsederhana.MenuRepoResponse;
import com.ahsailabs.kasirsederhana.R;
import com.ahsailabs.kasirsederhana.ui.category.CategoryFragment;
import com.ahsailabs.kasirsederhana.ui.category.models.CategoryItem;
import com.ahsailabs.kasirsederhana.ui.menu.models.MenuItem;
import com.ahsailabs.kasirsederhana.utils.InfoUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuFragment extends Fragment {
    private MainViewModel mainViewModel;

    @BindView(R.id.ibtnPhoto) ImageButton ibtnPhoto;
    @BindView(R.id.tilName) TextInputLayout tilName;
    @BindView(R.id.tieName) TextInputEditText tieName;
    @BindView(R.id.tilPrice) TextInputLayout tilPrice;
    @BindView(R.id.tiePrice) TextInputEditText tiePrice;
    @BindView(R.id.spCategory) Spinner spCategory;

    @BindView(R.id.rvMenu)
    RecyclerView rvMenu;

    private int categoryId;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItemList = new ArrayList<>();

    private ArrayAdapter<String> categoryItemArrayAdapter;
    private List<CategoryItem> categoryItemList = new ArrayList<>();
    private List<String> categoryItemNameList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        categoryItemArrayAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                        categoryItemNameList);
        categoryItemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryItemArrayAdapter);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(categoryItemList.size() > 0) {
                    CategoryItem categoryItem = categoryItemList.get(position);
                    categoryId = categoryItem.getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CategoryRepoResponse categoryRepoResponse = mainViewModel.loadCategoryData();
        categoryRepoResponse.listLiveData.observe(getActivity(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItemList) {
                for(CategoryItem item : categoryItemList){
                    categoryItemNameList.add(item.getName());
                }
                categoryItemArrayAdapter.notifyDataSetChanged();

            }
        });

        categoryRepoResponse.infoLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                InfoUtil.showToast(getActivity(), s);
            }
        });

        categoryRepoResponse.statusLiveData.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 401){
                    LoginActivity.start(getActivity());
                    getActivity().finish();
                }
            }
        });


        rvMenu.setLayoutManager(new GridLayoutManager(getActivity(),3));
        menuAdapter = new MenuAdapter(getActivity(), menuItemList);
        rvMenu.setAdapter(menuAdapter);


        MenuRepoResponse menuRepoResponse = mainViewModel.loadMenuData();
        menuRepoResponse.listLiveData.observe(getActivity(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                MenuFragment.this.menuItemList.clear();
                MenuFragment.this.menuItemList.addAll(menuItems);
                menuAdapter.notifyDataSetChanged();
            }
        });
        menuRepoResponse.infoLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                InfoUtil.showToast(getActivity(), s);
            }
        });
        menuRepoResponse.statusLiveData.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 401){
                    LoginActivity.start(getActivity());
                    getActivity().finish();
                }
            }
        });

    }

    private void loadViews(View view) {
        ButterKnife.bind(this, view);
    }

}