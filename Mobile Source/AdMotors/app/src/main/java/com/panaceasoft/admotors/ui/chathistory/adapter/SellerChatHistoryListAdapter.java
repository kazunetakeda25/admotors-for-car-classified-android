package com.panaceasoft.admotors.ui.chathistory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemSellerChatHistoryListAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.ChatHistory;

public class SellerChatHistoryListAdapter extends DataBoundListAdapter<ChatHistory, ItemSellerChatHistoryListAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ChatHistoryClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface;

    public SellerChatHistoryListAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                        ChatHistoryClickCallback callback,
                                        DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemSellerChatHistoryListAdapterBinding createBinding(ViewGroup parent) {
        ItemSellerChatHistoryListAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_seller_chat_history_list_adapter, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {

            ChatHistory chatHistory = binding.getChatHistory();

            if (chatHistory != null && callback != null) {
                callback.onClick(chatHistory, chatHistory.id);
            }
        });
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemSellerChatHistoryListAdapterBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemSellerChatHistoryListAdapterBinding binding, ChatHistory chatHistory) {
        binding.setChatHistory(chatHistory);

        if (!chatHistory.item.itemCurrency.currencySymbol.equals("") && !chatHistory.item.price.equals("")) {
            String currencySymbol = chatHistory.item.itemCurrency.currencySymbol;
            String price;
            try {
               price = Utils.format(Double.parseDouble(chatHistory.item.price));
            }
            catch (Exception e){
                price = chatHistory.item.price;
            }
            String currencyPrice;
            if (Config.SYMBOL_SHOW_FRONT) {
                currencyPrice = currencySymbol + " " + price;
            } else {
                currencyPrice = price + " " + currencySymbol;
            }
            binding.priceTextView.setText(currencyPrice);
        }
            binding.itemConditionTextView.setText(binding.getRoot().getResources().getString(R.string.item_condition__type, chatHistory.item.itemCondition.name));

            if (chatHistory.buyerUnreadCount.equals(Constants.ZERO)) {
                binding.countTextView.setVisibility(View.GONE);
            } else {
                binding.countTextView.setVisibility(View.VISIBLE);
            }

            if (chatHistory.item.isSoldOut.equals(Constants.ONE)) {
                binding.soldTextView.setVisibility(View.VISIBLE);
            } else {
                binding.soldTextView.setVisibility(View.GONE);
            }
        }


    @Override
    protected boolean areItemsTheSame(ChatHistory oldItem, ChatHistory newItem) {
        return Objects.equals(oldItem.id, newItem.id) &&
                oldItem.addedDate.equals(newItem.addedDate) &&
                oldItem.buyerUnreadCount.equals(newItem.buyerUnreadCount);
    }

    @Override
    protected boolean areContentsTheSame(ChatHistory oldItem, ChatHistory newItem) {
        return Objects.equals(oldItem.id, newItem.id) &&
                oldItem.addedDate.equals(newItem.addedDate) &&
                oldItem.buyerUnreadCount.equals(newItem.buyerUnreadCount);
    }

    public interface ChatHistoryClickCallback {
        void onClick(ChatHistory chatHistory, String id);
    }


}
