package com.example.asus.probazaizpit;

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

public class FirstFragment extends Fragment implements CatAdapter.CatViewHolder.ButtonClickListener {
    private static CatAdapter catAdapterCats;
    private static ArrayList<Cat> cats = Constants.getCats();
    private RecyclerView recyclerViewCats;
    private static ArrayList<Cat> tempCats = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_layout, container, false);

        recyclerViewCats = view.findViewById(R.id.recycler_view_cats);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerViewCities.setLayoutManager(layoutManager);
        recyclerViewCats.setLayoutManager(new LinearLayoutManager(getContext()));
        Constants.getFillList();
        Constants.getFillCatList();

        ArrayList<Cat> cats = Constants.getCats();

        catAdapterCats = new CatAdapter(this);
        recyclerViewCats.setAdapter(catAdapterCats);
        catAdapterCats.setData(cats);
        setCats(Constants.getCats());

        return view;
    }

    @Override
    public void likeButtonClick(Cat cat, Button button) {

        System.out.println(SecondFragment.getCatViewModel().getAllCats().getValue().size());

        if (!cat.isLiked()) {

            button.setText("DISLIKE");
            cat.setLiked(true);

            for (int k = 0; k < SecondFragment.getCatViewModel().getAllCats().getValue().size(); k++) {

                if (!SecondFragment.getCatViewModel().getAllCats().getValue().get(k).getTxtNameCat().equalsIgnoreCase(cat.getTxtNameCat())) {
                    tempCats.add(SecondFragment.getCatViewModel().getAllCats().getValue().get(k));
                }
            }
            SecondFragment.getCatViewModel().deleteAll();
            for (int j = 0; j < tempCats.size(); j++){
                tempCats.get(j).setLiked(true);
                SecondFragment.getCatViewModel().insert(tempCats.get(j));
            }
            tempCats.clear();

            SecondFragment.getCatViewModel().insert(cat);
            SecondFragment.getMyCatsAdapter().refresh();
            FirstFragment.getCatsAdapter().refresh();

        } else { //true

            for (int i = 0; i < SecondFragment.getCatViewModel().getAllCats().getValue().size(); i++) {
                if (cat.getTxtNameCat().equalsIgnoreCase(SecondFragment.getCatViewModel().getAllCats().getValue().get(i).getTxtNameCat())) {
                    SecondFragment.getCatViewModel().getAllCats().getValue().remove(i);
//                    MyTownsFragment.getTownsViewModel().deleteAll();
                    SecondFragment.getMyCatsAdapter().refresh();
                }
            }
            button.setText("LIKE");
            cat.setLiked(false);
        }
        catAdapterCats.refresh();
    }
    public static void setCats(ArrayList<Cat> kittyDataArrayList) {
        cats = new ArrayList<>();
        for (int i = 0; i < kittyDataArrayList.size(); i++) {
            if (!kittyDataArrayList.get(i).isLiked()) {
                cats.add(kittyDataArrayList.get(i));
            }
        }
        catAdapterCats.setData(cats);
    }


    public static CatAdapter getCatsAdapter() {
        return catAdapterCats;
    }

    public static ArrayList<Cat> getCats() {
        return cats;
    }
}