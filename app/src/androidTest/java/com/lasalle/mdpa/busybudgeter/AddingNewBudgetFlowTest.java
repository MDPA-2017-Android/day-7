package com.lasalle.mdpa.busybudgeter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.lasalle.mdpa.busybudgeter.database.BudgetingDatabase;
import com.lasalle.mdpa.busybudgeter.database.dao.BudgetDao;
import com.lasalle.mdpa.busybudgeter.view.HomeFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class AddingNewBudgetFlowTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public FragmentTestRule<?, HomeFragment> fragmentTestRule = FragmentTestRule.create(HomeFragment.class, false, false);

    private BudgetDao budgetDao;
    private BudgetingDatabase budgetingDatabase;

    @Before
    public void SetupTest() {
        // Create Database
        Context context = InstrumentationRegistry.getTargetContext();
        budgetingDatabase = Room.inMemoryDatabaseBuilder(context, BudgetingDatabase.class).build();
        budgetDao = budgetingDatabase.getBudgetDao();

        // Inject database into application

        // Start activity
    }

    @After
    public void CloseDatabase() {
        budgetingDatabase.close();
    }
}
