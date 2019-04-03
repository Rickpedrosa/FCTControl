package com.example.fctcontrol.data.local;

import android.content.Context;

import com.example.fctcontrol.data.local.daos.BusinessDao;
import com.example.fctcontrol.data.local.daos.StudentDao;
import com.example.fctcontrol.data.local.daos.StudentVisitsDao;
import com.example.fctcontrol.data.local.daos.VisitsDao;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.data.local.entity.StudentVisits;
import com.example.fctcontrol.data.local.entity.Visits;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities =
        {Student.class,
                Visits.class,
                Business.class,
                StudentVisits.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "fct_rick_pedrosa";
    //UN CAMPO INTEGER PRIMARY KEY SIEMPRE SERÁ AUTO_INCREMENT DE FORMA IMPLÍCITA LOL
//    private static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//
//        }
//    };

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance =
                            Room.databaseBuilder(
                                    context.getApplicationContext(), AppDatabase.class,
                                    DATABASE_NAME)
                                    //.addMigrations(MIGRATION_1_TO_2)
                                    .build();
                }
            }
        }
        return instance;
    }

    public abstract BusinessDao businessDao();

    public abstract StudentDao studentDao();

    public abstract VisitsDao visitsDao();

    public abstract StudentVisitsDao studentVisitsDao();
}
