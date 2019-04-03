package com.example.fctcontrol.ui.alumnos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.BaseRecyclerViewAdapter;
import com.example.fctcontrol.databinding.ItemAlumnoBinding;
import com.example.fctcontrol.dto.StudentResume;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class StudentExpositorFragmentAdapter extends BaseRecyclerViewAdapter<StudentResume,
        StudentExpositorFragmentAdapter.ViewHolder> {

    private final NavController navController;

    StudentExpositorFragmentAdapter(NavController navController) {
        this.navController = navController;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public StudentExpositorFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_alumno, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentExpositorFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemAlumnoBinding b;

        ViewHolder(@NonNull ItemAlumnoBinding itemView) {
            super(itemView.getRoot());
            b = itemView;
            itemView.getRoot().setOnClickListener(v -> navController.navigate(
                    StudentExpositorFragmentDirections.
                            actionStudentExpositorFragmentToDetailStudentFragment().
                            setStudentId(getItem(getAdapterPosition()).getId())));
        }

        void bind(StudentResume item) {
            b.lblCourse.setText(item.getCourse());
            b.lblEmail.setText(item.getEmail());
            b.lblEmpresa.setText(item.getBusiness());
            b.lblStudentName.setText(item.getName());
        }
    }
}
