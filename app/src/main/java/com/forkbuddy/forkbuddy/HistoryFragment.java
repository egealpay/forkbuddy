package com.forkbuddy.forkbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    ListView forkHistoryListView;
    public static List<ForkHistory> forkHistoryList;
    public static ForkHistoryAdapter forkHistoryAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        forkHistoryListView = view.findViewById(R.id.listView_History);

        forkHistoryList = new ArrayList<ForkHistory>();
        forkHistoryList.add(new ForkHistory("Ege Alpay", null, "Pizza"));
        forkHistoryList.add(new ForkHistory("Baran Sürücü", null, "Salad"));
        forkHistoryList.add(new ForkHistory("Omer Zakaria", null, "Japanese Cuisine"));
        forkHistoryList.add(new ForkHistory("Berk Aktuğ", null, "Indian Cuisine"));


        forkHistoryAdapter = new ForkHistoryAdapter(getActivity(), forkHistoryList);
        forkHistoryListView.setAdapter(forkHistoryAdapter);


        //// SWIPE TO REFRESH ////
        swipeRefreshLayout = view.findViewById(R.id.historyLayout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_3,
                R.color.refresh_progress_3,
                R.color.refresh_progress_3); //CHANGE COLOR SCHEME

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                forkHistoryAdapter.notifyDataSetChanged(); // REFRESH THE LIST (I GUESS :P)
                swipeRefreshLayout.setRefreshing(false); // STOP ANIMATION
            }
        });
        //// END OF SWIPE TO REFRESH ///

        return view;
    }

}
