package com.example.fctcontrol.data;

import com.example.fctcontrol.data.local.entity.Business;
import com.example.fctcontrol.data.local.entity.Student;
import com.example.fctcontrol.data.local.entity.StudentVisits;
import com.example.fctcontrol.data.local.entity.Visits;
import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.BusinessResume;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.dto.StudentDetail;
import com.example.fctcontrol.dto.StudentResume;
import com.example.fctcontrol.dto.StudentVisitDetail;
import com.example.fctcontrol.dto.VisitsForDialog;

import java.util.List;

import androidx.lifecycle.LiveData;

@SuppressWarnings("DanglingJavadoc")
public interface Repository {

    /*BUSINESS DAO*/
    LiveData<List<BusinessResume>> getAllCompanies();

    LiveData<Integer> getCompaniesCount();

    LiveData<BusinessForDialog[]> getBusinessForDialog();

    LiveData<Business> getBusinessById(long businessId);

    void addBusiness(Business business);

    void deleteBusiness(Business business);

    void updateBusiness(Business business);

    /**************/

    /*STUDENT DAO*/
    LiveData<List<StudentResume>> getAllStudents();

    LiveData<StudentDetail> getStudentById(long studentId);

    void addStudent(Student student);

    void deleteStudent(Student student);

    void updateStudent(Student student);

    LiveData<String> getStudentName(long studentId);

    /*************/

    /*VISITS DAO*/
    LiveData<List<Visits>> getAllVisits();

    LiveData<StudentVisitDetail> getVisitById(long visitId, long studentId);

    void addVisit(Visits visit);

    void deleteVisit(Visits visit);

    void updateVisit(Visits visit);

    LiveData<List<LastStudentVisit>> getLastVisitFromAllStudents();

    LiveData<List<VisitsForDialog>> getAllVisitsByStudentId(long studentId);

    /****************/

    /*STUDENTS-VISITS DAO*/
    //TODO methods from dao
    /**********************/

}
