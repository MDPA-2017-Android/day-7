package com.lasalle.mdpa.busybudgeter.view;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.adapter.BudgetRecyclerViewAdapater;
import com.lasalle.mdpa.busybudgeter.view.model.BudgetViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.budget_list) RecyclerView recyclerView;

    private BudgetViewModel budgetViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        budgetViewModel = ViewModelProviders.of(getActivity()).get(BudgetViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        setupRecyclerView();

        budgetViewModel.retrieveBudgetList();

        return view;
    }

    private void setupRecyclerView() {
        BudgetRecyclerViewAdapater budgetRecyclerViewAdapater = new BudgetRecyclerViewAdapater(new ArrayList<String>() {});
        recyclerView.setAdapter(budgetRecyclerViewAdapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        budgetViewModel.getBudgetNameList().observe(this, budgetNameList -> {
            budgetRecyclerViewAdapater.setBudgetNameList(budgetNameList);
            budgetRecyclerViewAdapater.notifyDataSetChanged();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {

        }
    }
}
