package com.tokopedia.devicetracker.ui.mainadmin.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.TrackingData;
import com.tokopedia.devicetracker.ui.baseui.BaseRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Angga.Prasetiyo on 26/08/2015.
 */
public class DeviceLogRecyclerAdapter extends BaseRecyclerViewAdapter<TrackingData, DeviceLogRecyclerAdapter.ViewHolder> {
    private static final String TAG = DeviceRecyclerAdminAdapter.class.getSimpleName();

   // private List<TrackingData> trackingDatas = new ArrayList<>();
    private Activity activity;


    public DeviceLogRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_log_tracking, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTime.setText(getEntity(position).timeString());
        switch (getEntity(position).getActivity()) {
            case TrackingData.ACTIVITY_REMOVE_DEVICE:
                holder.tvAction.setText("Dihapus");
                break;
            case TrackingData.ACTIVITY_BORROW:
                holder.tvAction.setText("Dipinjam");
                break;
            case TrackingData.ACTIVITY_RETURN:
                holder.tvAction.setText("Dikembalikan");
                break;
            case TrackingData.ACTIVITY_ADD_DEVICE:
                holder.tvAction.setText("Ditambahkan");
                break;
            case TrackingData.ACTIVITY_RESTORE_DEVICE:
                holder.tvAction.setText("Diactifkan");
                break;
            case TrackingData.ACTIVITY_UPDATE_DEVICE:
                holder.tvAction.setText("Diubah");
                break;
        }
        holder.tvPersonName.setText(getEntity(position).getPerson().getName());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_log_time)
        TextView tvTime;
        @Bind(R.id.tv_log_action)
        TextView tvAction;
        @Bind(R.id.tv_log_person)
        TextView tvPersonName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
