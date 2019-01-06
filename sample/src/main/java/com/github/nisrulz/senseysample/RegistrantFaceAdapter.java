package com.github.nisrulz.senseysample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

public class RegistrantFaceAdapter extends BaseAdapter<FaceRegistrant,RegistrantFaceAdapter.ViewHolder>  {

    public RegistrantFaceAdapter(Context context) {
        super(context);
    }

    @Override
    public void notifyDataSetChanged(List<FaceRegistrant> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getInflater().inflate(R.layout.item_registrant_face, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FaceRegistrant faceRegistrant = mDataList.get(position);
        holder.name.setText(faceRegistrant.getName());
        holder.faceImage.setImageResource(faceRegistrant.getImageId());
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View faceView;
        ImageView faceImage;
        EditText name;

        public ViewHolder(View view) {
            super(view);
            faceView = view;
            faceImage = (ImageView) view.findViewById(R.id.visitor_image);
            name = (EditText) view.findViewById(R.id.visitor_name);
        }
    }
}
