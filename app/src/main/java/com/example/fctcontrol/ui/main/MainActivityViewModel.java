package com.example.fctcontrol.ui.main;

import android.app.Application;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.SharedPreferencesStringLiveData;
import com.example.fctcontrol.data.Repository;
import com.example.fctcontrol.data.RepositoryImpl;
import com.example.fctcontrol.data.local.AppDatabase;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.data.local.entity.StudentVisits;
import com.example.fctcontrol.data.local.entity.Visits;
import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.BusinessResume;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.dto.StudentResume;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<String> startDestination;
    private final Repository repo;
    private String studentCourse;
    private String studentCompany;
    private long studentBusinessId;

    MainActivityViewModel(@NonNull Application application, AppDatabase database) {
        super(application);
        this.repo = new RepositoryImpl(database.studentDao(), database.businessDao(), database.visitsDao(),
                database.studentVisitsDao());
        startDestination = new SharedPreferencesStringLiveData(
                PreferenceManager.getDefaultSharedPreferences(application),
                application.getString(R.string.start_destiny_key),
                application.getString(R.string.start_destiny_defaultValue));
    }

    LiveData<String> getStartDestination() {
        return startDestination;
    }

    /*BUSINESS FRAGMENTS*/
    public LiveData<List<BusinessResume>> getAllCompanies() {
        return repo.getAllCompanies();
    }

    public void addCompany(Business business) {
        repo.addBusiness(business);
    }

    public void updateCompany(Business business) {
        repo.updateBusiness(business);
    }

    public void deleteCompany(Business business) {
        repo.deleteBusiness(business);
    }

    public LiveData<Business> getBusiness(long id) {
        return repo.getBusinessById(id);
    }
    /*END OF BUSINESS FRAGMENTS*/

    /*STUDENT FRAGMENTS*/
    public LiveData<List<StudentResume>> getAllStudents() {
        return repo.getAllStudents();
    }

    public void addStudent(Student student) {
        repo.addStudent(student);
    }

    public void updateStudent(Student student) {
        repo.updateStudent(student);
    }

    public void deleteStudent(Student student) {
        repo.deleteStudent(student);
    }

    public LiveData<StudentDetail> getStudent(long id) {
        return repo.getStudentById(id);
    }

    public LiveData<BusinessForDialog[]> getBusinessForDialog() {
        return repo.getBusinessForDialog();
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentCompany() {
        return studentCompany;
    }

    public void setStudentCompany(String studentCompany) {
        this.studentCompany = studentCompany;
    }

    public long getStudentBusinessId() {
        return studentBusinessId;
    }

    public void setStudentBusinessId(long studentBusinessId) {
        this.studentBusinessId = studentBusinessId;
    }

    public LiveData<Integer> getCompaniesCount() {
        return repo.getCompaniesCount();
    }
    /*END OF STUDENT FRAGMENTS*/

    /*VISITS FRAGMENT*/
    public LiveData<List<LastStudentVisit>> getLastVisitFromStudents() {
        return repo.getLastVisitFromStudents();
    }

    public void addVisit(Visits visits, StudentVisits studentVisits) {
        repo.addVisit(visits);
        repo.addVisitRelation(studentVisits);
    }

    /*END OF VISITS FRAGMENT*/
}
