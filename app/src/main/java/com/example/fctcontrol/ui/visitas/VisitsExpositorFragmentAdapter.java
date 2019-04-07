package com.example.fctcontrol.ui.visitas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.BaseRecyclerViewAdapter;
import com.example.fctcontrol.databinding.ItemVisitasBinding;
import com.example.fctcontrol.dto.LastStudentVisit;
import com.example.fctcontrol.ui.main.MainActivityViewModel;
import com.example.fctcontrol.utils.TimeCustomUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class VisitsExpositorFragmentAdapter extends BaseRecyclerViewAdapter<LastStudentVisit, VisitsExpositorFragmentAdapter.ViewHolder> {

    private MainActivityViewModel viewModel;
    private OnVisitClicked onVisitClicked;

    VisitsExpositorFragmentAdapter(
            MainActivityViewModel viewModel,
            OnVisitClicked onVisitClicked) {
        this.viewModel = viewModel;
        this.onVisitClicked = onVisitClicked;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public VisitsExpositorFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_visitas, parent, false));
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
            itemView.getRoot().setOnClickListener(v -> {
                viewModel.triggerClickDialog(getItem(getAdapterPosition()).getStudentId());
                onVisitClicked.navigateToDetail(getItem(getAdapterPosition()));
            });
        }

        void bind(LastStudentVisit item) {
            b.lblLastVisit.setText(
                    item.getDay() == null ? itemView.getContext().getString(R.string.no_visits_yet) :
                            itemView.getContext().getString(R.string.last_visit, item.getDay()));

            b.lblNextVisit.setText(viewModel.getNextVisitDay(viewModel.getVisitTime(), item.getDay()));

            b.lblVisitDay.setVisibility(TimeCustomUtils.willBeToday(item.getDay(), viewModel.getVisitTime()) ?
                    View.VISIBLE : View.INVISIBLE);

            b.lblStudentName.setText(item.getStudentName());
        }


    }
}
