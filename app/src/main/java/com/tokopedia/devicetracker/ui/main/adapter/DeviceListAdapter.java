package com.tokopedia.devicetracker.ui.main.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.app.MainApp;
import com.tokopedia.devicetracker.database.model.DeviceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {
    private static final String TAG = DeviceListAdapter.class.getSimpleName();

    private List<DeviceData> deviceDataList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;


    private MainApp application;
    private int selectedItem = 0;

    public DeviceListAdapter(OnItemClickListener listener, List<DeviceData> deviceDataList) {
        this.deviceDataList = deviceDataList;
        this.application = MainApp.getInstance();
        this.onItemClickListener = listener;
    }

    public void add(DeviceData data) {
        deviceDataList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_device_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (deviceDataList.get(position).isBorrowed()) {
            holder.setBorrowedTextColor();
        } else {
            holder.setNormalTextColor();
        }
        holder.tvName.setText(deviceDataList.get(position).getDeviceName().toUpperCase());
        holder.tvModel.setText(deviceDataList.get(position).getDeviceModel().toUpperCase());
        holder.tvNumber.setText(String.valueOf(deviceDataList.get(position).getId()).toUpperCase());
        ImageLoader.getInstance().displayImage("assets://" + deviceDataList.get(position).getDevicePicPath(), holder.ivPic, getDisplayImage());
        holder.view.setSelected(position == selectedItem);
    }

    @Override
    public int getItemCount() {
        return deviceDataList.size();
    }

    public DeviceData getItem(int position) {
        return deviceDataList.get(position);
    }

    public void renderItemView(DeviceData newDeviceData) {
        for (int i = 0; i < deviceDataList.size(); i++) {
            if (deviceDataList.get(i).getId() == newDeviceData.getId()) {
                deviceDataList.get(i).setNewData(newDeviceData);
                notifyItemChanged(i);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View view;
        @Bind(R.id.iv_pic)
        ImageView ivPic;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_model)
        TextView tvModel;
        @Bind(R.id.tv_number)
        TextView tvNumber;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundResource(R.drawable.bg_available_device);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onDeviceListItemClicked(v, getAdapterPosition());
            notifyItemChanged(selectedItem);
            selectedItem = getAdapterPosition();
            notifyItemChanged(selectedItem);
        }

        public void setBorrowedTextColor() {
            tvName.setTextColor(application.getResources().getColor(R.color.divider));
            tvNumber.setTextColor(application.getResources().getColor(R.color.divider));
            tvModel.setTextColor(application.getResources().getColor(R.color.divider));
        }

        public void setNormalTextColor() {
            tvName.setTextColor(application.getResources().getColor(R.color.primary_text));
            tvNumber.setTextColor(application.getResources().getColor(R.color.secondary_text));
            tvModel.setTextColor(application.getResources().getColor(R.color.secondary_text));

        }
    }

    private DisplayImageOptions getDisplayImage() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .displayer(new FadeInBitmapDisplayer(1000))
                .build();
    }

    public interface OnItemClickListener {
        void onDeviceListItemClicked(View view, int position);
    }
}
