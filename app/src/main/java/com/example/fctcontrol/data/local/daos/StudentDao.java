package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.data.local.entity.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM student WHERE id = :studentId")
    LiveData<Student> getStudentById(long studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateStudent(Student student);

}
