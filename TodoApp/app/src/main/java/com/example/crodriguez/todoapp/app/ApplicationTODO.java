package co.edu.ucc.todolist.app;

import android.app.Application;

import com.example.crodriguez.todoapp.repository.AppDB;

/**
 * Created by jggomez on 03-Oct-17.
 */

public class ApplicationTODO extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppDB.init(getApplicationContext());
    }
}
