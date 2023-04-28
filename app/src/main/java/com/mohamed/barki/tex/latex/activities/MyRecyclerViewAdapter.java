package com.mohamed.barki.tex.latex.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.barki.tex.R;
import com.mohamed.barki.tex.mathview.KatexView;
import com.mohamed.barki.tex.mathview.MathJaxView;

import java.util.ArrayList;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

     ArrayList<String> mData;
     LayoutInflater mInflater;
     ItemClickListener mClickListener;
	 Context context;
	 String engin;
    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList<String> data, String engin) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
		this.context = context;
		this.engin = engin;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int recyclerviewId = R.layout.recyclerview_row;
		switch(engin){
            case "MathV2":
                recyclerviewId = R.layout.recyclerview_row_mathjax;
				break;
			case "Text": case "MathV1": default:
                break;
        }
		View view = mInflater.inflate(recyclerviewId, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String cmnd = mData.get(position);
        switch(engin){
			case "Text":
				holder.katexView.setDisplayText(cmnd);
				break;
			case "MathV2":
				holder.mathJaxView.setText("$$"+cmnd+"$$");
				break;
			case "MathV1": default :
				holder.katexView.setDisplayText("$$"+cmnd+"$$");
				break;
		}
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    @SuppressWarnings("deprecation")
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        KatexView katexView;
		MathJaxView mathJaxView;
        ViewHolder(View itemView) {
            super(itemView);
            switch(engin){
				case "MathV2":
					mathJaxView = itemView.findViewById(R.id.formula_view_drawer_mathjax);
					itemView.setOnClickListener(this);
					break;
				case "Text": case "MathV1": default :
					katexView = itemView.findViewById(R.id.formula_view_drawer);
					itemView.setOnClickListener(this);
					break;
			}
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
