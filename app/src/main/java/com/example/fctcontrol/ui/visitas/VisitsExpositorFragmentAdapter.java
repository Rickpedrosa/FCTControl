package com.example.fctcontrol.ui.visitas;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.BaseRecyclerViewAdapter;
import com.example.fctcontrol.databinding.ItemVisitasBinding;
import com.example.fctcontrol.dto.LastStudentVisit;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class VisitsExpositorFragmentAdapter extends BaseRecyclerViewAdapter<LastStudentVisit, VisitsExpositorFragmentAdapter.ViewHolder> {

    private NavController navController;

    VisitsExpositorFragmentAdapter(NavController navController) {
        this.navController = navController;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public VisitsExpositorFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_visitas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsExpositorFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemVisitasBinding b;

        ViewHolder(@NonNull ItemVisitasBinding itemView) {
            super(itemView.getRoot());
            b = itemView;
        }

        void bind(LastStudentVisit item) {
            b.lblLastVisit.setText(item.getDay() == null ? "No visits yet" : item.getDay());
            b.lblStudentName.setText(item.getStudentName());
            b.constraintChildVisits.setOnClickListener(v -> {
                if (item.getDay() == null) {
                    navController.navigate(R.id.visitsDetailFragment);
                }
            });
        }
    }
}
