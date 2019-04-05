package com.example.fctcontrol.data;

import android.os.AsyncTask;

import com.example.fctcontrol.data.local.daos.BusinessDao;
import com.example.fctcontrol.data.local.daos.StudentDao;
import com.example.fctcontrol.data.local.daos.StudentVisitsDao;
import com.example.fctcontrol.data.local.daos.VisitsDao;
import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.data.local.entity.StudentVisits;
import com.example.fctcontrol.data.local.entity.Visits;
import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.BusinessResume;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.dto.StudentResume;
import com.example.fctcontrol.dto.VisitsForDialog;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {

    private final StudentDao studentDao;
    private final BusinessDao businessDao;
    private final VisitsDao visitsDao;
    //TODO 4th dao impl
    @SuppressWarnings("FieldCanBeLocal")
    private final StudentVisitsDao studentVisitsDao;

    public RepositoryImpl(StudentDao studentDao,
                          BusinessDao businessDao,
                          VisitsDao visitsDao,
                          StudentVisitsDao studentVisitsDao) {
        this.studentDao = studentDao;
        this.businessDao = businessDao;
        this.visitsDao = visitsDao;
        this.studentVisitsDao = studentVisitsDao;
    }

    @Override
    public LiveData<List<BusinessResume>> getAllCompanies() {
        return businessDao.getAllCompanies();
    }

    @Override
    public LiveData<Integer> getCompaniesCount() {
        return businessDao.getCompaniesCount();
    }

    @Override
    public LiveData<BusinessForDialog[]> getBusinessForDialog() {
        return businessDao.getBusinessForDialog();
    }

    @Override
    public LiveData<Business> getBusinessById(long businessId) {
        return businessDao.getBusinessById(businessId);
    }

    @Override
    public void addBusiness(Business business) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> businessDao.addBusiness(business));
    }

    @Override
    public void deleteBusiness(Business business) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> businessDao.deleteBusiness(business));
    }

    @Override
    public void updateBusiness(Business business) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> businessDao.updateBusiness(business));
    }

    @Override
    public LiveData<List<StudentResume>> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public LiveData<StudentDetail> getStudentById(long studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public void addStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.addStudent(student));
    }

    @Override
    public void deleteStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.deleteStudent(student));
    }

    @Override
    public void updateStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.updateStudent(student));
    }

    @Override
    public LiveData<List<Visits>> getAllVisits() {
        return visitsDao.getAllVisits();
    }

    @Override
    public LiveData<Visits> getVisitById(long visitId) {
        return visitsDao.getVisitById(visitId);
    }

    @Override
    public void addVisit(Visits visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitsDao.addVisit(visit));
    }

    @Override
    public void deleteVisit(Visits visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitsDao.deleteVisit(visit));
    }

    @Override
    public void updateVisit(Visits visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> visitsDao.updateVisit(visit));
    }

    @Override
    public LiveData<List<LastStudentVisit>> getLastVisitFromAllStudents() {
        return visitsDao.getLastVisitFromAllStudents();
    }

    @Override
    public LiveData<List<VisitsForDialog>> getAllVisitsByStudentId(long studentId) {
        return visitsDao.getAllVisitsByStudentId(studentId);
    }

}
