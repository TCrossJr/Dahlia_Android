package com.example.dahlia_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahlia_android.MainActivity;
import com.example.dahlia_android.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    protected HomeFeedAdapter feed_adapter;
    protected LinearLayoutManager layoutManager;

    private RecyclerView rView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
//        homeViewModel =
//                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView testImg = root.findViewById(R.id.imageView2);

        rView = root.findViewById(R.id.feed_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        feed_adapter = new HomeFeedAdapter(MainActivity._feed);
        feed_adapter.notifyDataSetChanged();

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(feed_adapter);

        return root;
    }
}