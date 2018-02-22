package com.lasalle.mdpa.busybudgeter.view.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.lasalle.mdpa.busybudgeter.database.BudgetingDatabase;
import com.lasalle.mdpa.busybudgeter.database.entity.Budget;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class BudgetViewModel extends AndroidViewModel {

    @Inject BudgetingDatabase budgetingDatabase;
    private MutableLiveData<List<String>> nameList;

    public BudgetViewModel(@NonNull Application application) {
        super(application);
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new Module() {{
            bind(UserLoginViewModel.class);
        }});
        Toothpick.inject(this, scope);

        nameList = new MutableLiveData<>();
    }

    public LiveData<List<String>> getBudgetNameList() {
        return nameList;
    }

    public void retrieveBudgetList() {
        LiveData<List<Budget>> budgetLiveData = budgetingDatabase.getBudgetDao().getAll();

        budgetLiveData.observe(getApplication(), budgetList -> {
            List<String> budgetNameList = new ArrayList<>();

            for(Budget budget : budgetList)
            {
                budgetNameList.add(budget.getName());
            }
        });
    }

    public void insertNewBudget(String budgetName) {
        Budget budget = new Budget();
        budget.setName(budgetName);

        budgetingDatabase.getBudgetDao().insert(budget);
    }

    @Override
    protected void onCleared() {
        Toothpick.closeScope(this);
        super.onCleared();
    }


}
