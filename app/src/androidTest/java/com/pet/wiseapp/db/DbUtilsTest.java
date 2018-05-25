package com.pet.wiseapp.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.pet.wiseapp.db.model.AphorismModel;
import com.pet.wiseapp.db.model.DaoMaster;
import com.pet.wiseapp.db.model.DaoSession;
import com.pet.wiseapp.utils.Constants;

import org.greenrobot.greendao.database.Database;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

public class DbUtilsTest {

    private DaoSession daoSession;
    private AphorismModel testModel;

    @Before
    public void setup(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(appContext, Constants.DB_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        DbUtils.initDB(appContext);
    }

    @Test
    public void addNewAphorism() {
        String testAphorismText = "TEST_APHORISM";
        List<AphorismModel> aphorismsBeforeAdding = daoSession.getAphorismModelDao().loadAll();
        DbUtils.addNewAphorism(testAphorismText);
        List<AphorismModel> aphorismsAfterAdding = daoSession.getAphorismModelDao().loadAll();
        assertNotNull(aphorismsAfterAdding);
        assertFalse(aphorismsBeforeAdding.size() >= aphorismsAfterAdding.size());
        for (AphorismModel aphorismModel: aphorismsAfterAdding){
            if (aphorismModel.getText().equals(testAphorismText)){
                this.testModel = aphorismModel;
                break;
            }
        }
        daoSession.getAphorismModelDao().delete(testModel);
    }

    @Test
    public void loadAllAphorisms() {
        String[] aphorisms = DbUtils.loadAllAphorisms();
        assertNotNull(aphorisms);
    }
}