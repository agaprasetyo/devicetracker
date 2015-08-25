package com.tokopedia.devicetracker.ui.administration.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class DeviceRecyclerAdapter extends RecyclerView.Adapter<DeviceRecyclerAdapter.ViewHolder> {
    private static final String TAG = DeviceRecyclerAdapter.class.getSimpleName();

    private List<DeviceData> deviceDataList = new ArrayList<>();
    private OnInteractionListener onInteractionListener;
    private Activity activity;


    private MainApp application;

    public DeviceRecyclerAdapter(Activity activity, OnInteractionListener listener, List<DeviceData> deviceDataList) {
        this.deviceDataList = deviceDataList;
        this.application = MainApp.getInstance();
        this.onInteractionListener = listener;
        this.activity = activity;
    }

    public void add(DeviceData data) {
        deviceDataList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_device_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(deviceDataList.get(position).getDeviceName().toUpperCase());
        holder.tvModel.setText(deviceDataList.get(position).getDeviceModel().toUpperCase());
        holder.tvNumber.setText(String.valueOf(deviceDataList.get(position).getId()).toUpperCase());
        ImageLoader.getInstance().displayImage("assets://" + deviceDataList.get(position).getDevicePicPath(), holder.ivPic, getDisplayImage());
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

    public void deleteItemView(DeviceData newDeviceData) {
        for (int i = 0; i < deviceDataList.size(); i++) {
            if (deviceDataList.get(i).getId() == newDeviceData.getId()) {
                deviceDataList.remove(i);
                notifyDataSetChanged();
            }
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.iv_pic)
        ImageView ivPic;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_model)
        TextView tvModel;
        @Bind(R.id.tv_number)
        TextView tvNumber;
        @Bind(R.id.iv_1)
        ImageView ivPopUp;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundResource(R.drawable.bg_available_device);
            ButterKnife.bind(this, itemView);
            //itemView.setOnClickListener(this);
            ivPopUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(activity, ivPopUp);
                    Menu menu = popupMenu.getMenu();
                    popupMenu.getMenuInflater().inflate(R.menu.card_view_menu, menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_edit:
                                    break;
                                case R.id.action_delete:
                                    onInteractionListener.onDeleteDeviceData(deviceDataList.get(getAdapterPosition()));
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });


        }

        @Override
        public void onClick(View v) {
            onInteractionListener.onDeviceListItemClicked(getAdapterPosition());
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

    public interface OnInteractionListener {
        void onDeviceListItemClicked(int position);

        void onDeleteDeviceData(DeviceData deviceData);
    }
}

