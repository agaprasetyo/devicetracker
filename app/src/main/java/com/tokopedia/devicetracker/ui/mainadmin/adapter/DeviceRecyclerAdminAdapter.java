package com.tokopedia.devicetracker.ui.mainadmin.adapter;

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
import com.tokopedia.devicetracker.database.model.DeviceData;
import com.tokopedia.devicetracker.ui.baseui.BaseRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class DeviceRecyclerAdminAdapter extends BaseRecyclerViewAdapter<DeviceData, DeviceRecyclerAdminAdapter.ViewHolder> {
    private static final String TAG = DeviceRecyclerAdminAdapter.class.getSimpleName();

    private OnInteractionListener onInteractionListener;
    private Activity activity;


    public DeviceRecyclerAdminAdapter(Activity activity, OnInteractionListener listener) {
        this.onInteractionListener = listener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_device_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(getEntity(position).getDeviceName().toUpperCase());
        holder.tvModel.setText(getEntity(position).getDeviceModel().toUpperCase());
        holder.tvNumber.setText(String.valueOf(getEntity(position).getId()).toUpperCase());
        ImageLoader.getInstance().displayImage("file://" + getEntity(position).getDevicePicPath(), holder.ivPic, getDisplayImage());
    }


    public void renderItemView(DeviceData newDeviceData) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getEntity(i).getId() == newDeviceData.getId()) {
                getEntity(i).setNewData(newDeviceData);
                notifyItemChanged(i);
            }
        }
    }

    public void deleteItemView(DeviceData newDeviceData) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getEntity(i).getId() == newDeviceData.getId()) {
                deleteEntity(i);
            }
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
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
                                    onInteractionListener.onEditDeviceData(getEntity(getAdapterPosition()));
                                    break;
                                case R.id.action_delete:
                                    onInteractionListener.onDeleteDeviceData(getEntity(getAdapterPosition()));
                                    break;
                                case R.id.action_log:
                                    onInteractionListener.onDeviceLogTracking(getEntity(getAdapterPosition()));
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });

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
                .showImageForEmptyUri(R.drawable.default_img)
                .showImageOnFail(R.drawable.default_img)
                .build();
    }

    public interface OnInteractionListener {

        void onItemClick(int position);

        void onDeleteDeviceData(DeviceData deviceData);

        void onEditDeviceData(DeviceData deviceData);

        void onDeviceLogTracking(DeviceData deviceData);
    }
}

