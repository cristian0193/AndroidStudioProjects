package cv.yeison.apptodolist.app;

import android.app.Application;

import cv.yeison.apptodolist.repository.AppDB;

/**
 * Created by andres on 03/10/2017.
 */

public class ApplicationTODO extends Application {

    @Override
    public  void onCreate(){
        super.onCreate();
        AppDB.init(getApplicationContext());
    }

}
