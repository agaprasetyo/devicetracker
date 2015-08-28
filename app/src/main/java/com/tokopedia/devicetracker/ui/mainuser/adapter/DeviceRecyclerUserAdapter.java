package com.tokopedia.devicetracker.ui.mainuser.adapter;

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
import com.tokopedia.devicetracker.ui.baseui.BaseRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class DeviceRecyclerUserAdapter extends BaseRecyclerViewAdapter<DeviceData, DeviceRecyclerUserAdapter.ViewHolder> {
    private static final String TAG = DeviceRecyclerUserAdapter.class.getSimpleName();

    private OnInteractionListener onInteractionListener;


    private MainApp application;
    private int selectedItem = 0;

    public DeviceRecyclerUserAdapter(OnInteractionListener listener) {
        this.application = MainApp.getInstance();
        this.onInteractionListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_device_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getEntity(position).isBorrowed()) {
            holder.setBorrowedTextColor();
        } else {
            holder.setNormalTextColor();
        }
        holder.tvName.setText(getEntity(position).getDeviceName().toUpperCase());
        holder.tvModel.setText(getEntity(position).getDeviceModel().toUpperCase());
        holder.tvNumber.setText(String.valueOf(getEntity(position).getId()).toUpperCase());
        ImageLoader.getInstance().displayImage("file://" + getEntity(position).getDevicePicPath(), holder.ivPic, getDisplayImage());
        holder.view.setSelected(position == selectedItem);
    }

    public void renderItemView(DeviceData newDeviceData) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mData.get(i).getId() == newDeviceData.getId()) {
                mData.get(i).setNewData(newDeviceData);
                notifyItemChanged(i);
            }
        }
    }

    public void setInitialDeviceSelected(int i) {
        notifyItemChanged(selectedItem);
        selectedItem = i;
        notifyItemChanged(selectedItem);
    }

    public void performItemClicked(int position) {
        onInteractionListener.onItemClick(position);
        selectedItem = position;
        notifyItemChanged(position);
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
            onInteractionListener.onItemClick(getAdapterPosition());
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
                .showImageForEmptyUri(R.drawable.default_img)
                .showImageOnFail(R.drawable.default_img)
                .build();
    }

    public interface OnInteractionListener {

        void onItemClick(int position);

    }
}
