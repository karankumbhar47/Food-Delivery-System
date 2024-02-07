package com.example.swiggy_lite.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.History_Fragments.HistoryDetailsFragment;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.HistoryListAdapter;

public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    HistoryListAdapter historyListAdapter;

    public HistoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.order_history_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL,false));
        historyListAdapter = new HistoryListAdapter(DummyData.dummyOrderList,requireContext());
        historyListAdapter.setOnItemClickListener(new HistoryListAdapter.OnItemClickListener() {
            @Override
            public void viewDetails(int position) {
                load(new HistoryDetailsFragment(position));
            }
        });
        recyclerView.setAdapter(historyListAdapter);

        return view;
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}