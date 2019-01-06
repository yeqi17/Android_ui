package com.github.nisrulz.senseysample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.leakcanary.LeakTraceElement;

import java.util.List;

public class VisitorFaceAdapter extends BaseAdapter<FaceVisitor,VisitorFaceAdapter.ViewHolder> {

    public VisitorFaceAdapter(Context context) {
        super(context);
    }

    //绑定数据
    @Override
    public void notifyDataSetChanged(List<FaceVisitor> dataList) {
        mDataList = dataList;
    }

    //定义了每一项的布局.
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getInflater().inflate(R.layout.item_visitor_face, parent, false);
        return new ViewHolder(view);
    }

    //数据绑定到布局
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FaceVisitor faceVisitor = mDataList.get(position);
        holder.date.setText(faceVisitor.getDate());
        holder.name.setText(faceVisitor.getName());
        holder.faceImage.setImageResource(faceVisitor.getImageId());
    }
    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View faceView;
        ImageView faceImage;
        EditText name;
        TextView date;

        public ViewHolder(View view) {
            super(view);
            faceView = view;
            faceImage = (ImageView) view.findViewById(R.id.visitor_image);
            name = (EditText) view.findViewById(R.id.visitor_name);
            date = (TextView) view.findViewById(R.id.visitor_date);
        }
    }

}
