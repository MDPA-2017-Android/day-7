package com.lasalle.mdpa.busybudgeter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.lasalle.mdpa.busybudgeter.database.BudgetingDatabase;
import com.lasalle.mdpa.busybudgeter.database.dao.BudgetDao;
import com.lasalle.mdpa.busybudgeter.database.entity.Budget;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class BudgetDatabaseTest {

    private BudgetDao budgetDao;
    private BudgetingDatabase budgetingDatabase;

    @Before
    public void CreateDatabase() {
        Context context = InstrumentationRegistry.getTargetContext();
        budgetingDatabase = Room.inMemoryDatabaseBuilder(context, BudgetingDatabase.class).build();
        budgetDao = budgetingDatabase.getBudgetDao();
    }

    @After
    public void CloseDatabase() {
        budgetingDatabase.close();
    }

    @Test
    public void writeNewBudgetIntoDatabase() {
        Budget budget = new Budget();
        budget.setName("Manolete manolete");

        budgetDao.insert(budget);

        List<Budget> budgetList = budgetDao.getAll();
        assertEquals(budget.getName(), budgetList.get(0).getName());
    }

}
