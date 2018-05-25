package com.pet.wiseapp.db;

import android.content.Context;

import com.pet.wiseapp.db.model.AphorismModel;
import com.pet.wiseapp.db.model.DaoMaster;
import com.pet.wiseapp.db.model.DaoSession;
import com.pet.wiseapp.utils.Constants;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class DbUtils {

    private static DaoSession daoSession;

    public static void initDB(Context context){
        if (daoSession == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DB_NAME);
            Database db = helper.getWritableDb();
            daoSession = new DaoMaster(db).newSession();
        }
    }

    public static String[] loadAllAphorisms(){
        List<AphorismModel> aphorismModels = daoSession.getAphorismModelDao().loadAll();
        if (aphorismModels != null && aphorismModels.size() > 0){
            String[] aphorisms = new String[aphorismModels.size()];
            for (int i = 0; i < aphorismModels.size(); i++){
                aphorisms[i] = aphorismModels.get(i).getText();
            }
            return aphorisms;
        } else {
            return new String[0];
        }
    }

    public static void addNewAphorism(String newAphorismText) {
        AphorismModel aphorismModel = new AphorismModel();
        aphorismModel.setText(newAphorismText);
        daoSession.insertOrReplace(aphorismModel);
    }
}
