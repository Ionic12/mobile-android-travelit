package com.example.mobile.cashier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.cashier.model.ReviewModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private List<ReviewModel> listReview = new ArrayList<>();

    public ReviewAdapter(List<ReviewModel> listReview) {
        this.listReview = listReview;
    }
    //
    private OnReviewClickListener listener;

    public interface OnReviewClickListener {
        public void onClick(View view, int position);
    }

    public void setListener(OnReviewClickListener listener) {
        this.listener = listener;
    }
    //
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vh = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.activity_review_adapter,viewGroup,false);
        ReviewViewHolder viewHolder = new ReviewViewHolder(vh);
        return viewHolder;
    }
    //
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder ReviewViewHolder, int i) {

        ReviewModel item = listReview.get(i);
        ReviewViewHolder.txtReview.setText(item.getReview());
        ReviewViewHolder.txtName.setText(item.getName());
    }
    @Override
    public int getItemCount() {
        return listReview.size();
    }
    //
    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtReview = itemView.findViewById(R.id.txtReview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}

