package com.dionpapas.drink_more_water.fragments;

import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dionpapas.drink_more_water.R;
import com.dionpapas.drink_more_water.adapters.DairyAdapter;
import com.dionpapas.drink_more_water.database.AppDatabase;
import com.dionpapas.drink_more_water.database.WaterEntry;
import androidx.fragment.app.Fragment;
import com.dionpapas.drink_more_water.viewmodels.DairyFragmentViewModel;

import java.util.List;


public class DairyFragment extends Fragment {

    private DairyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private static AppDatabase mDb;
    private  DairyFragmentViewModel dairyFragmentViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDb = AppDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set the RecyclerView to its corresponding view
        // Set title bar
      //  ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.progress));
        mRecyclerView = view.findViewById(R.id.recyclerViewWaterEntries);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new DairyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //DividerItemDecoration decoration = new DividerItemDecoration(getContext(), VERTICAL);
        //mRecyclerView.addItemDecoration(decoration);
        //getAllEntries();
        dairyFragmentViewModel = ViewModelProviders.of(this).get(DairyFragmentViewModel.class);

        dairyFragmentViewModel.getAllWaterEntries().observe(this, new Observer<List<WaterEntry>>() {
            @Override
            public void onChanged(List<WaterEntry> waterEntries) {
                mAdapter.submitList(waterEntries);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
       // ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.progress));
    }
}
