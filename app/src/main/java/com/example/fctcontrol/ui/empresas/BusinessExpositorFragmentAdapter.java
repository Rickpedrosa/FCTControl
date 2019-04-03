package com.example.fctcontrol.ui.empresas;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.fctcontrol.R;
import com.example.fctcontrol.base.BaseRecyclerViewAdapter;
import com.example.fctcontrol.databinding.ItemEmpresaBinding;
import com.example.fctcontrol.dto.BusinessResume;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessExpositorFragmentAdapter extends BaseRecyclerViewAdapter<BusinessResume,
        BusinessExpositorFragmentAdapter.ViewHolder> {

    private NavController navController;

    BusinessExpositorFragmentAdapter(NavController navController) {
        this.navController = navController;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public BusinessExpositorFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_empresa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessExpositorFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemEmpresaBinding b;

        ViewHolder(@NonNull ItemEmpresaBinding itemView) {
            super(itemView.getRoot());
            b = itemView;
            itemView.getRoot().setOnClickListener(v -> navController.navigate(
                    BusinessExpositorFragmentDirections.
                            actionBusinessExpositorFragmentToBusinessDetailFragment().
                            setBusinessId(getItem(getAdapterPosition()).getId())));
        }

        public void bind(BusinessResume item) {
            Picasso.with(itemView.getContext()).load(item.getUrl_logo())
                    .noFade()
//                    .placeholder(R.drawable.ic_event_seat_black_24dp)
                    .error(R.drawable.ic_delete_black_24dp)
                    .into(b.empresaImg);
            b.lblBusinessName.setText(item.getName());
            b.lblBusinessPhone.setText(String.valueOf(item.getPhone()));
        }
    }
}
