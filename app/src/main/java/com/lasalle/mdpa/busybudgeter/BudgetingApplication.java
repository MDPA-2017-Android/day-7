package com.lasalle.mdpa.busybudgeter;

import android.app.Application;

import com.lasalle.mdpa.busybudgeter.manager.UserManager;
import com.lasalle.mdpa.busybudgeter.manager.impl.UserManagerImpl;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class BudgetingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Scope applicationScope = Toothpick.openScope(this);
        installToothPickModules(applicationScope);
    }

    public void installToothPickModules(Scope scope) {
        scope.installModules(new Module() {{
            bind(UserManager.class).to(UserManagerImpl.class);
        }});
    }
}
