package com.example.fctcontrol.base.interfaces;

import com.example.fctcontrol.data.local.daos.VisitsDao;
import com.example.fctcontrol.dto.BusinessResume;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.dto.StudentResume;
import com.example.fctcontrol.ui.alumnos.StudentExpositorFragment;
import com.example.fctcontrol.ui.alumnos.StudentExpositorFragmentDirections;
import com.example.fctcontrol.ui.empresas.BusinessExpositorFragment;
import com.example.fctcontrol.ui.empresas.BusinessExpositorFragmentDirections;
import com.example.fctcontrol.ui.visitas.VisitsExpositorFragment;
import com.example.fctcontrol.ui.visitas.VisitsExpositorFragmentDirections;

import androidx.navigation.NavController;

public class OnClickedItemToNavigateImpl implements OnClickedItemToNavigate {

    private NavController navController;

    public OnClickedItemToNavigateImpl(NavController navController) {
        this.navController = navController;
    }

    @Override
    public void navigate(String className, Object item, String[] visits) {
        if (className.equals(VisitsExpositorFragment.class.getSimpleName())) {
            LastStudentVisit stv = (LastStudentVisit) item;
            if (stv.getVisitId() == 0) {
                navController.navigate(VisitsExpositorFragmentDirections.
                        actionVisitsExpositorFragmentToVisitsDetailFragment().
                        setStudentId(stv.getStudentId()));
            } else {
                //TODO

            }
        } else if (className.equals(BusinessExpositorFragment.class.getSimpleName())) {
            BusinessResume bs = (BusinessResume) item;
            navController.navigate(
                    BusinessExpositorFragmentDirections.
                            actionBusinessExpositorFragmentToBusinessDetailFragment().
                            setBusinessId(bs.getId()));

        } else if (className.equals(StudentExpositorFragment.class.getSimpleName())) {
            StudentResume str = (StudentResume) item;
            navController.navigate(
                    StudentExpositorFragmentDirections.
                            actionStudentExpositorFragmentToDetailStudentFragment().
                            setStudentId(str.getId()));
        }
    }
}
