package com.example.asus.probazaizpit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment implements CatAdapter.CatViewHolder.ButtonClickListener{

    private static CatAdapter myCatAdapter;
    private static ArrayList<Cat> myCats = Constants.getMyCats();
    private RecyclerView recyclerView;

    private static CatViewModel catViewModel;
    private static ArrayList<Cat> tempMyCat = new ArrayList<>();


 //   public MyCatsFragment() {
   // }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_layout, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_mycats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myCatAdapter = new CatAdapter(this);
        recyclerView.setAdapter(myCatAdapter);
        myCatAdapter.setData(myCats);

        initiateCatsViewModel();
        return view;
    }

    public void initiateCatsViewModel() {
        catViewModel = ViewModelProviders.of(this).get(CatViewModel.class);
        catViewModel.getAllCats().observe(this, new Observer<List<Cat>>() {
            @Override
            public void onChanged(@Nullable List<Cat> cats) {
                myCatAdapter.setData(cats);
            }
        });


    }


    @Override
    public void likeButtonClick(Cat cat, Button button) {

        for (int i = 0; i < FirstFragment.getCats().size(); i++) {
            if (FirstFragment.getCats().get(i).getTxtNameCat().equalsIgnoreCase(cat.getTxtNameCat())) {

                for (int k = 0; k < catViewModel.getAllCats().getValue().size(); k++) {

                    if (!catViewModel.getAllCats().getValue().get(k).getTxtNameCat().equalsIgnoreCase(cat.getTxtNameCat())) {
                        tempMyCat.add(catViewModel.getAllCats().getValue().get(k));
                    }
                }
                catViewModel.deleteAll();
                for (int j = 0; j < tempMyCat.size(); j++) {
                    SecondFragment.getCatViewModel().insert(tempMyCat.get(j));
                }
                tempMyCat.clear();

                FirstFragment.getCats().get(i).setLiked(false);
                myCatAdapter.refresh();
                FirstFragment.getCatsAdapter().refresh();
            }
        }
    }

    public static ArrayList<Cat> getMyCats() {
        return myCats;
    }

    public static CatAdapter getMyCatsAdapter() {
        return myCatAdapter;
    }

    public static CatViewModel getCatViewModel() {
        return catViewModel;
    }

}
