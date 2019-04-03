package com.example.fctcontrol.data.local.daos;

import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.dto.StudentResume;

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

    @Query("SELECT st.id, st.course, st.email, b.name AS business, st.name" +
            " FROM student st INNER JOIN business b ON st.businessId = b.id")
    LiveData<List<StudentResume>> getAllStudents();

    @Query("SELECT st.id, st.name, st.phone, st.email, st.course, b.name AS businessName, st.tutor, st.tutor_phone, st.tutor_schedule" +
            " FROM student st INNER JOIN business b ON st.businessId = b.id WHERE st.id = :studentId")
    LiveData<StudentDetail> getStudentById(long studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateStudent(Student student);

}
