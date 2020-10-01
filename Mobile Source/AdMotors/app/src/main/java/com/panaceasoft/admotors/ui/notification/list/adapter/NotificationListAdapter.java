package com.panaceasoft.admotors.ui.notification.list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemNotificationListAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Noti;

import androidx.databinding.DataBindingUtil;

public class NotificationListAdapter extends DataBoundListAdapter<Noti, ItemNotificationListAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private NotificationClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface;

    public NotificationListAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                   NotificationClickCallback callback,
                                   DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemNotificationListAdapterBinding createBinding(ViewGroup parent) {
        ItemNotificationListAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_notification_list_adapter, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            Noti notification = binding.getNotification();
            if (notification != null && callback != null) {
                callback.onClick(notification);
            }
        });
        return binding;
    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemNotificationListAdapterBinding binding, Noti item) {
        binding.setNotification(item);
        if (item.isRead.equals(Constants.ONE)) {
            binding.notiConstraintLayout.setBackgroundColor(binding.getRoot().getResources().getColor(R.color.md_grey_200));
        } else {
            binding.notiConstraintLayout.setBackgroundColor(binding.getRoot().getResources().getColor(R.color.md_white_1000));
        }

        binding.messageTextView.setText(item.message);
        binding.dateTextView.setText(item.addedDateStr);

    }

    @Override
    protected boolean areItemsTheSame(Noti oldItem, Noti newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.message.equals(newItem.message)
                && oldItem.isRead.equals(newItem.isRead)
                && oldItem.addedDateStr.equals(newItem.addedDateStr);
    }

    @Override
    protected boolean areContentsTheSame(Noti oldItem, Noti newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.message.equals(newItem.message)
                && oldItem.isRead.equals(newItem.isRead)
                && oldItem.addedDateStr.equals(newItem.addedDateStr);
    }


    public interface NotificationClickCallback {
        void onClick(Noti notification);
    }
}
