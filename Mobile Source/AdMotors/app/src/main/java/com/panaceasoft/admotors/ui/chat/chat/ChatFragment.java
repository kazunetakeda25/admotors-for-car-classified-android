package com.panaceasoft.admotors.ui.chat.chat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentChatBinding;
import com.panaceasoft.admotors.databinding.ItemRatingEntryBinding;
import com.panaceasoft.admotors.ui.chat.adapter.ChatListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.chat.ChatViewModel;
import com.panaceasoft.admotors.viewmodel.chathistory.ChatHistoryViewModel;
import com.panaceasoft.admotors.viewmodel.item.ItemViewModel;
import com.panaceasoft.admotors.viewmodel.notification.NotificationsViewModel;
import com.panaceasoft.admotors.viewmodel.rating.RatingViewModel;
import com.panaceasoft.admotors.viewmodel.user.UserViewModel;
import com.panaceasoft.admotors.viewobject.ChatHistory;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;
import com.panaceasoft.admotors.viewobject.messageHolder.Chat;
import com.panaceasoft.admotors.viewobject.messageHolder.Message;
import com.panaceasoft.admotors.viewobject.messageHolder.UserStatusHolder;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChatFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ChatViewModel chatViewModel;
    private ChatHistoryViewModel chatHistoryViewModel;
    private UserViewModel userViewModel;
    private NotificationsViewModel notificationViewModel;
    private RatingViewModel ratingViewModel;
    private ItemViewModel itemViewModel;
    private Dialog dialog;

    private LinearLayoutManager layoutManager1;
    private boolean active = false;
    private EditText itemOfferPriceEditText;
    private PSDialogMsg psDialogRatingMsg, psDialogMsg;

    @VisibleForTesting
    private AutoClearedValue<FragmentChatBinding> binding;
    private AutoClearedValue<ChatListAdapter> adapter;
    private AutoClearedValue<Dialog> reviewDialog;
    private AutoClearedValue<ProgressDialog> prgDialog;
    private AutoClearedValue<ItemRatingEntryBinding> itemRatingEntryBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentChatBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        return binding.get().getRoot();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RESULT_CODE__IMAGE_CATEGORY && resultCode == Constants.RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            if (getContext() != null) {

                if (getActivity() != null && selectedImage != null) {
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);

                    if (cursor != null) {
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imagePath = cursor.getString(columnIndex);
                        cursor.close();

                        if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {
                            chatViewModel.setUploadImageObj(imagePath, loginUserId, chatViewModel.receiverId, loginUserId, chatViewModel.itemId, Constants.CHAT_TO_BUYER);
                        } else {
                            chatViewModel.setUploadImageObj(imagePath, chatViewModel.receiverId, loginUserId, chatViewModel.receiverId, chatViewModel.itemId, Constants.CHAT_TO_SELLER);
                        }

                        binding.get().progressBar2.setVisibility(View.VISIBLE);
                        binding.get().editText.setClickable(false);
                        binding.get().imageButton.setClickable(false);
                        binding.get().sendButton.setClickable(false);
                    }
                }
            }
        }
    }


    @Override
    protected void initUIAndActions() {

        if (!connectivity.isConnected()) {
            Toast.makeText(getContext(), R.string.no_internet_error, Toast.LENGTH_SHORT).show();
        }
        loginUserId = pref.getString(Constants.USER_ID, Constants.EMPTY_STRING);// not to crash sometime

        psDialogRatingMsg = new PSDialogMsg(getActivity(), false);
        psDialogMsg = new PSDialogMsg(getActivity(), false);

        prgDialog = new AutoClearedValue<>(this, new ProgressDialog(getActivity()));
        prgDialog.get().setMessage((Utils.getSpannableString(getContext(), getString(R.string.message__please_wait), Utils.Fonts.MM_FONT)));
        prgDialog.get().setCancelable(false);

        LinearLayoutManager layoutManager = (LinearLayoutManager)
                binding.get().chatListRecyclerView.getLayoutManager();

        if (layoutManager != null) {

            layoutManager.setReverseLayout(true);
        }

        binding.get().itemCardView.setOnClickListener(v -> navigationController.navigateToItemDetailActivity(getActivity(), chatViewModel.itemId, chatViewModel.itemName));


        binding.get().sendButton.setOnClickListener(v -> {


            if (!connectivity.isConnected()) {
                Toast.makeText(getContext(), R.string.no_internet_error, Toast.LENGTH_SHORT).show();
                return;
            }

            if (!binding.get().editText.getText().toString().isEmpty()) {
                chatViewModel.setSaveMessagesToFirebaseObj(
                        new Message(
                                Utils.generateKeyForChatHeadId(loginUserId, chatViewModel.receiverId),
                                chatViewModel.itemId,
                                binding.get().editText.getText().toString().trim(),
                                Constants.CHAT_TYPE_TEXT,
                                loginUserId,
                                Constants.CHAT_STATUS_NULL,
                                false
                        ), loginUserId, chatViewModel.receiverId);


                if (!connectivity.isConnected()) {
                    binding.get().editText.getText().clear();
                } else {
                    if (chatViewModel.isFirstMessage) {
                        if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {

                            chatViewModel.setSyncChatHistoryObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId, Constants.CHAT_TO_BUYER);

                        } else {

                            chatViewModel.setSyncChatHistoryObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, Constants.CHAT_TO_SELLER);

                        }
                    }

                }

            }

        });

        binding.get().imageButton.setOnClickListener(v -> navigationController.getImageFromGallery(getActivity()));

        if (loginUserEmail.isEmpty() && loginUserPwd.isEmpty() || loginUserPwd == null) {
            if (userIdToVerify.isEmpty()) {
                if (loginUserId.equals("")) {
                    navigationController.navigateToUserLoginActivity(getActivity());
                }
            }
        }

        binding.get().offerButton.setOnClickListener(v -> callOfferDialog());

//        binding.get().markAsSoldButton.setOnClickListener(v -> {
//            //call server
//            if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {
//
//                chatViewModel.setSellItemObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId);
//
//            } else {
//
//                chatViewModel.setSellItemObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId);
//
//            }
//
//        });

//        binding.get().rejectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //call server
//                chatViewModel.setRejectedOfferObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, Constants.ZERO);
//            }
//        });


        binding.get().chatListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                layoutManager1 = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (layoutManager1 != null) {

                    chatViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                    int firstPosition = layoutManager1
                            .findLastVisibleItemPosition();

                    if (firstPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !chatViewModel.forceEndLoading) {

                            chatViewModel.setFetchMessagesFromConversationObj(loginUserId, chatViewModel.receiverId);
                        }

                    }

                }

            }
        });

    }

    private void callOfferDialog() {
        if (getContext() != null) {
            dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.custom_dialog_offer);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            if (dialog.getWindow() != null) {

                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                ImageView itemImagePath = dialog.findViewById(R.id.itemImageView);
                TextView itemName = dialog.findViewById(R.id.itemNameTextView);
                TextView itemPriceTextView = dialog.findViewById(R.id.priceTextView);
                TextView itemCurrencyTextView = dialog.findViewById(R.id.currencyTextView);
                TextView itemBackCurrencyTextView = dialog.findViewById(R.id.backCurrencyTextView);
                TextView lakhTextView = dialog.findViewById(R.id.lakhTextView);
                itemOfferPriceEditText = dialog.findViewById(R.id.offerPriceEditText);
                TextView makeOfferButton = dialog.findViewById(R.id.offerButton);

                itemName.setText(chatViewModel.itemName);
                String currencySymbol = chatViewModel.itemCurrency;
                String price;
                try {
                    price = Utils.format(Double.parseDouble(chatViewModel.itemPrice));
                } catch (Exception e) {
                    price = chatViewModel.itemPrice;
                }

                String currencyPrice;
                if (Config.SYMBOL_SHOW_FRONT) {
                    itemCurrencyTextView.setVisibility(View.VISIBLE);
                    itemBackCurrencyTextView.setVisibility(View.GONE);
                    currencyPrice = currencySymbol + " " + price;
                } else {
                    currencyPrice = price + " " + currencySymbol;
                    itemCurrencyTextView.setVisibility(View.GONE);
                    itemBackCurrencyTextView.setVisibility(View.VISIBLE);
                }

                if(chatViewModel.itemPriceUnit.equals("")){
                    lakhTextView.setVisibility(View.GONE);
                }else{
                    lakhTextView.setVisibility(View.VISIBLE);
                    lakhTextView.setText(chatViewModel.itemPriceUnit);
                }
                itemPriceTextView.setText(currencyPrice);
                itemCurrencyTextView.setText(chatViewModel.itemCurrency);
                itemBackCurrencyTextView.setText(chatViewModel.itemCurrency);
                itemOfferPriceEditText.setText(chatViewModel.itemPrice);

                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(itemImagePath, chatViewModel.itemImagePath);

                makeOfferButton.setOnClickListener(v -> {

                    if (itemOfferPriceEditText.getText().toString().trim().equals(Constants.ZERO)) {

                        psDialogMsg.showWarningDialog(getString(R.string.item_entry_offer_not_zero), getString(R.string.app__ok));
                        psDialogMsg.show();

                    } else if (itemOfferPriceEditText.getText().toString().trim().isEmpty()) {

                        psDialogMsg.showWarningDialog(getString(R.string.chat__item_space_error), getString(R.string.app__ok));
                        psDialogMsg.show();

                    } else {

                        dialog.dismiss();
                        //call api
                        chatViewModel.offerItemPrice = itemOfferPriceEditText.getText().toString().trim();

                        if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {

                            chatViewModel.setUpdateOfferPriceObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId, chatViewModel.offerItemPrice, Constants.CHAT_TO_BUYER);

                        } else {

                            chatViewModel.setUpdateOfferPriceObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, chatViewModel.offerItemPrice, Constants.CHAT_TO_SELLER);

                        }
                    }


                });
                dialog.show();

                dialog.getWindow().setAttributes(lp);
            }


        }

    }

    @Override
    protected void initViewModels() {

        chatViewModel = new ViewModelProvider(this, viewModelFactory).get(ChatViewModel.class);
        chatHistoryViewModel = new ViewModelProvider(this, viewModelFactory).get(ChatHistoryViewModel.class);
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
        notificationViewModel = new ViewModelProvider(this, viewModelFactory).get(NotificationsViewModel.class);
        ratingViewModel = new ViewModelProvider(this, viewModelFactory).get(RatingViewModel.class);
        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);

    }

    @Override
    protected void initAdapters() {

        if (getActivity() != null) {
            chatViewModel.itemId = getActivity().getIntent().getStringExtra(Constants.ITEM_ID);
            chatViewModel.receiverId = getActivity().getIntent().getStringExtra(Constants.RECEIVE_USER_ID);
            chatViewModel.receiverName = getActivity().getIntent().getStringExtra(Constants.RECEIVE_USER_NAME);
            chatViewModel.receiverUserImgUrl = getActivity().getIntent().getStringExtra(Constants.RECEIVE_USER_IMG_URL);
            chatViewModel.itemImagePath = getActivity().getIntent().getStringExtra(Constants.IMAGE_PATH);
            chatViewModel.itemName = getActivity().getIntent().getStringExtra(Constants.ITEM_NAME);
            chatViewModel.itemPrice = getActivity().getIntent().getStringExtra(Constants.ITEM_PRICE);
            chatViewModel.itemCurrency = getActivity().getIntent().getStringExtra(Constants.ITEM_CURRENCY);
            chatViewModel.itemConditionName = getActivity().getIntent().getStringExtra(Constants.ITEM_CONDITION_TYPE_NAME);
            chatViewModel.itemPriceUnit = getActivity().getIntent().getStringExtra(Constants.ITEM_PRICE_UNIT);
            chatViewModel.chatFlag = getActivity().getIntent().getStringExtra(Constants.CHAT_FLAG);

            bindItemData();

            if (chatViewModel.itemImagePath.equals(Constants.EMPTY_STRING) || chatViewModel.itemName.equals(Constants.EMPTY_STRING) || chatViewModel.itemPrice.equals(Constants.EMPTY_STRING)
                    || chatViewModel.itemCurrency.equals(Constants.EMPTY_STRING) || chatViewModel.itemConditionName.equals(Constants.EMPTY_STRING)) {

                itemViewModel.setItemDetailObj(chatViewModel.itemId, Constants.ZERO, chatViewModel.receiverId);
            }
        }


        ChatListAdapter chatListAdapter = new ChatListAdapter(dataBindingComponent, this, loginUserId, new ChatListAdapter.clickCallBack() {
            @Override
            public void onImageClicked(Message message) {
                if (message.type == Constants.CHAT_TYPE_IMAGE) {
                    navigationController.navigateToImageFullScreen(getActivity(), message.message);
                }
            }

            @Override
            public void onAcceptButtonClicked(Message message) {

                psDialogMsg.showConfirmDialog(getString(R.string.item_entry_accept_confirm), getString(R.string.app__ok), getString(R.string.app__cancel));
                psDialogMsg.show();

                psDialogMsg.okButton.setOnClickListener(v -> {

                    psDialogMsg.cancel();
                    message.offerStatus = Constants.CHAT_STATUS_ACCEPT;
                    chatViewModel.offerMessage = message;
                    chatViewModel.offerItemPrice = message.getPriceOnly();

                    if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {

                        chatViewModel.setAcceptOfferObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId, message.getPriceOnly(), Constants.CHAT_TO_BUYER);

                    } else {

                        chatViewModel.setAcceptOfferObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, message.getPriceOnly(), Constants.CHAT_TO_SELLER);

                    }
                });

                psDialogMsg.cancelButton.setOnClickListener(v -> psDialogMsg.cancel());

            }

            @Override
            public void onRejectButtonClicked(Message message) {

                psDialogMsg.showConfirmDialog(getString(R.string.item_entry_reject_confirm), getString(R.string.app__ok), getString(R.string.app__cancel));
                psDialogMsg.show();

                psDialogMsg.okButton.setOnClickListener(v -> {

                    psDialogMsg.cancel();
                    message.offerStatus = Constants.CHAT_STATUS_REJECT;
                    chatViewModel.offerMessage = message;
                    chatViewModel.offerItemPrice = message.getPriceOnly();


                    if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {

                        chatViewModel.setRejectedOfferObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId, Constants.ZERO, Constants.CHAT_TO_BUYER);

                    } else {

                        chatViewModel.setRejectedOfferObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, Constants.ZERO, Constants.CHAT_TO_SELLER);

                    }
                });
                psDialogMsg.cancelButton.setOnClickListener(v -> psDialogMsg.cancel());

            }

            @Override
            public void onMarkAsSoldButtonClicked(Message message) {

                message.offerStatus = Constants.CHAT_STATUS_ACCEPT;
                message.isSold = true;
                chatViewModel.offerMessage = message;
                chatViewModel.offerItemPrice = message.getPriceOnly();

                if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {

                    chatViewModel.setSellItemObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId);

                } else {

                    chatViewModel.setSellItemObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId);

                }

            }

            @Override
            public void onGiveReviewClicked() {

                Utils.navigateOnUserVerificationActivity(userIdToVerify, loginUserId, psDialogMsg, getActivity(), navigationController, () -> getCustomLayoutDialog());
            }

            @Override
            public void onProfileClicked() {

                navigationController.navigateToUserDetail(getActivity(), chatViewModel.receiverId, chatViewModel.receiverName);
            }

        }, chatViewModel.itemId, chatViewModel.receiverUserImgUrl);

        this.adapter = new AutoClearedValue<>(this, chatListAdapter);
        binding.get().chatListRecyclerView.setAdapter(chatListAdapter);
    }

    private void bindingButtonText(String chatFlag) {
        if (chatFlag.equals(Constants.CHAT_FROM_BUYER)) {
            binding.get().offerButton.setVisibility(View.GONE);
            binding.get().soldTextView.setVisibility(View.VISIBLE);

        } else {
            binding.get().offerButton.setVisibility(View.VISIBLE);
            binding.get().soldTextView.setVisibility(View.GONE);

        }
    }


    @Override
    public void onPause() {

        super.onPause();

        chatViewModel.setRemoveUserPresenceStatusObj(new UserStatusHolder(loginUserId, loginUserName));

    }

    @Override
    public void onResume() {
        super.onResume();
        chatViewModel.setUserPresenceStatusObj(new UserStatusHolder(loginUserId, loginUserName));
    }

    @Override
    public void onDestroy() {
        if (getActivity() != null) {
            navigationController.navigateBackToChatHistoryListFragment(getActivity());
        }
        super.onDestroy();
    }

    @Override
    protected void initData() {

        getItemDetailData();

        chatViewModel.setRegisterUserToFirebaseObj(loginUserEmail, loginUserPwd);

        userViewModel.setFetchUserByIdObj(loginUserId);

//        chatViewModel.setSaveChatToFirebaseObj(new Chat(loginUserId, chatViewModel.receiverId, chatViewModel.itemId), loginUserId, chatViewModel.receiverId);

        chatViewModel.setFetchMessagesFromConversationObj(loginUserId, chatViewModel.receiverId);

        chatViewModel.setUserPresenceStatusObj(new UserStatusHolder(loginUserId, loginUserName));

        chatViewModel.setCheckReceiverStatusObj(loginUserId, chatViewModel.receiverId, chatViewModel.itemId);

        chatViewModel.setUploadChattingWithObj(new Chat(loginUserId, chatViewModel.receiverId, chatViewModel.itemId));

        readMessage();

        loadChatHistory();

        getRatingPostData();

        chatHistoryViewModel.getChatHistoryData().observe(this, listResource -> {

            if (listResource != null) {

                Utils.psLog("Got Data" + listResource.message + listResource.toString());

                switch (listResource.status) {
                    case LOADING:
                        // Loading State
                        // Data are from Local DB

                        if (listResource.data != null) {
                            //fadeIn Animation
                            fadeIn(binding.get().getRoot());

                            // Update the data
                            replaceChatHistoryData(listResource.data);

                        }

                        break;

                    case SUCCESS:
                        // Success State
                        // Data are from Server

                        if (listResource.data != null) {
                            // Update the data
                            replaceChatHistoryData(listResource.data);
                        }

                        chatHistoryViewModel.setLoadingState(false);

                        break;

                    case ERROR:
                        // Error State

                        chatHistoryViewModel.setLoadingState(false);

                        break;
                    default:
                        // Default

                        break;
                }

            } else {

                // Init Object or Empty Data
                Utils.psLog("Empty Data");

                if (chatHistoryViewModel.offset > 1) {
                    // No more data for this list
                    // So, Block all future loading
                    chatHistoryViewModel.forceEndLoading = true;
                }

            }

        });

        chatViewModel.getUserPresenceStatusData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case ERROR:
                        break;

                    case SUCCESS:

//                        if (result.message != null) {
//                            if (result.message.equals(Config.SUCCESSFULLY_SAVED)) {
//                                Toast.makeText(getActivity(), Config.SUCCESSFULLY_SAVED, Toast.LENGTH_SHORT).show();
//                            } else if (result.message.equals(Config.SUCCESSFULLY_DELETED)) {
//                                Toast.makeText(getActivity(), Config.SUCCESSFULLY_DELETED, Toast.LENGTH_SHORT).show();
//                            }
//                        }

                        break;
                }
            }
        });

        chatViewModel.getRemoveUserPresenceStatusData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case ERROR:
                        break;

                    case SUCCESS:

//                        if (result.message != null) {
//                            if (result.message.equals(Config.SUCCESSFULLY_SAVED)) {
//                                Toast.makeText(getActivity(), Config.SUCCESSFULLY_SAVED, Toast.LENGTH_SHORT).show();
//                            } else if (result.message.equals(Config.SUCCESSFULLY_DELETED)) {
//                                Toast.makeText(getActivity(), Config.SUCCESSFULLY_DELETED, Toast.LENGTH_SHORT).show();
//                            }
//                        }

                        break;
                }
            }
        });

        chatViewModel.getRegisterUserToFirebaseData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        chatViewModel.setLoginUserToFirebaseObj(loginUserEmail, loginUserPwd);
                        break;

                    case ERROR:

                        chatViewModel.setLoginUserToFirebaseObj(loginUserEmail, loginUserPwd);
                        break;
                }
            }

        });

        chatViewModel.getLoginUserToFirebaseData().observe(this, booleanResource -> {

            if (booleanResource != null) {
                switch (booleanResource.status) {
                    case SUCCESS:
                        break;

                    case ERROR:

//                        psDialogMsg.showConfirmDialog(booleanResource.message + " " + getString(R.string.chat__retry_message), getString(R.string.chat__retry_retry), getString(R.string.chat__retry_no));
//                        psDialogMsg.show();
//
//                        psDialogMsg.okButton.setOnClickListener(v -> {
//                            chatViewModel.setRegisterUserToFirebaseObj(loginUserEmail, loginUserPwd);
//                            psDialogMsg.cancel();
//                        });
//
//                        if(getActivity() != null)
//                        {
//                            psDialogMsg.cancelButton.setOnClickListener(v -> ChatFragment.this.getActivity().finish());
//                        }

                        break;
                }
            }
        });

//        chatViewModel.getSaveChatToFirebaseData().observe(this, booleanResource -> {
//
//            if (booleanResource != null) {
//                switch (booleanResource.status) {
//                    case ERROR:
//                        break;
//
//                    case SUCCESS:
//                        break;
//                }
//            }
//        });

        chatViewModel.getSaveMessagesToFirebaseData().observe(this, booleanResource -> {

            if (booleanResource != null) {
                switch (booleanResource.status) {
                    case ERROR:

                        binding.get().progressBar2.setVisibility(View.GONE);
//                        binding.get().editText.setClickable(true);
                        binding.get().imageButton.setClickable(true);
                        binding.get().sendButton.setClickable(true);

                        break;

                    case SUCCESS:

                        pushNoti();

                        binding.get().editText.getText().clear();

                        binding.get().progressBar2.setVisibility(View.GONE);
//                        binding.get().editText.setClickable(true);
                        binding.get().imageButton.setClickable(true);
                        binding.get().sendButton.setClickable(true);

                        break;
                }
            }
        });

        chatViewModel.getUpdateMessageOfferStatusToFirebaseData().observe(this, booleanResource -> {

            if (booleanResource != null) {
                switch (booleanResource.status) {
                    case ERROR:

                        binding.get().progressBar2.setVisibility(View.GONE);
                        binding.get().imageButton.setClickable(true);
                        binding.get().sendButton.setClickable(true);

                        break;

                    case SUCCESS:

                        pushNoti();

                        binding.get().editText.getText().clear();

                        String currencySymbol = chatViewModel.itemCurrency;
                        String price;
                        try {
                            price = Utils.format(Double.parseDouble(chatViewModel.offerItemPrice));
                        } catch (Exception e) {
                            price = chatViewModel.offerItemPrice;
                        }
                        String currencyPrice;
                        if (Config.SYMBOL_SHOW_FRONT) {
                            currencyPrice = currencySymbol + " " + price;
                        } else {
                            currencyPrice = price + " " + currencySymbol;
                        }

                        if (chatViewModel.offerMessage.offerStatus == Constants.CHAT_STATUS_ACCEPT) {
                            // accept message


                            chatViewModel.setSaveMessagesToFirebaseObj(
                                    new Message(
                                            Utils.generateKeyForChatHeadId(loginUserId, chatViewModel.receiverId),
                                            chatViewModel.itemId,
                                            currencyPrice,
                                            Constants.CHAT_TYPE_TEXT,
                                            loginUserId,
                                            Constants.CHAT_STATUS_ACCEPT,
                                            false
                                    ), loginUserId, chatViewModel.receiverId);

                        } else {
                            // reject message
                            chatViewModel.setSaveMessagesToFirebaseObj(
                                    new Message(
                                            Utils.generateKeyForChatHeadId(loginUserId, chatViewModel.receiverId),
                                            chatViewModel.itemId,
                                            currencyPrice,
                                            Constants.CHAT_TYPE_TEXT,
                                            loginUserId,
                                            Constants.CHAT_STATUS_REJECT,
                                            false
                                    ), loginUserId, chatViewModel.receiverId);
                        }

                        binding.get().progressBar2.setVisibility(View.GONE);
                        binding.get().imageButton.setClickable(true);
                        binding.get().sendButton.setClickable(true);

                        break;
                }
            }
        });

        chatViewModel.getUpdateMessageIsSoldStatusToFirebaseData().observe(this, booleanResource -> {

            if (booleanResource != null) {
                switch (booleanResource.status) {
                    case ERROR:

                        binding.get().progressBar2.setVisibility(View.GONE);
                        binding.get().imageButton.setClickable(true);
                        binding.get().sendButton.setClickable(true);

                        break;

                    case SUCCESS:

                        pushNoti();

                        binding.get().editText.getText().clear();

                        String currencySymbol = chatViewModel.itemCurrency;
                        String price;
                        try {
                            price = Utils.format(Double.parseDouble(chatViewModel.offerItemPrice));
                        } catch (Exception e) {
                            price = chatViewModel.offerItemPrice;
                        }

                        String currencyPrice;
                        if (Config.SYMBOL_SHOW_FRONT) {
                            currencyPrice = currencySymbol + " " + price;
                        } else {
                            currencyPrice = price + " " + currencySymbol;
                        }

                        // accept message
                        chatViewModel.setSaveMessagesToFirebaseObj(
                                new Message(
                                        Utils.generateKeyForChatHeadId(loginUserId, chatViewModel.receiverId),
                                        chatViewModel.itemId,
                                        currencyPrice,
                                        Constants.CHAT_TYPE_SOLD,
                                        loginUserId,
                                        Constants.CHAT_STATUS_ACCEPT,
                                        true
                                ), loginUserId, chatViewModel.receiverId);

                        binding.get().progressBar2.setVisibility(View.GONE);
                        binding.get().imageButton.setClickable(true);
                        binding.get().sendButton.setClickable(true);

                        break;
                }
            }
        });


        chatViewModel.getFetchMessagesFromConversationData().observe(this, listResource -> {

            if (listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:

                        chatViewModel.setGetMessagesFromDatabaseObj(loginUserId, chatViewModel.receiverId, chatViewModel.itemId);

                        chatViewModel.setLoadingState(false);
                        break;

                    case ERROR:

                        chatViewModel.setGetMessagesFromDatabaseObj(loginUserId, chatViewModel.receiverId, chatViewModel.itemId);

                        chatViewModel.setLoadingState(false);
                        chatViewModel.forceEndLoading = true;

                        break;
                }
            }
        });

        chatViewModel.getGetMessagesFromDatabaseData().observe(this, listresource -> {

            if (listresource != null) {
                if (listresource.size() > 0) {
                    replaceData(listresource);
                }
            }
        });

        userViewModel.getFetchUserByIdData().observe(this, userResource -> {

            if (userResource != null) {
                switch (userResource.status) {
                    case ERROR:
                        break;

                    case SUCCESS:
                        //this.sender = userResource.data;
                        break;
                }
            }
        });

        chatViewModel.getUploadImageData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case ERROR:
                        break;

                    case SUCCESS:

                        if (result.data != null) {
                            chatViewModel.setSaveMessagesToFirebaseObj(
                                    new Message(
                                            Utils.generateKeyForChatHeadId(loginUserId, chatViewModel.receiverId),
                                            chatViewModel.itemId,
                                            result.data.imgPath,
                                            Constants.CHAT_TYPE_IMAGE,
                                            loginUserId,
                                            Constants.CHAT_STATUS_NULL,
                                            false

                                    )
                                    , loginUserId, chatViewModel.receiverId);
//                                    new Message(
//                                            loginUserId,
//                                            sender.userName,
//                                            result.data.imgPath,
//                                            Utils.generateCurrentTime(),
//                                            Constants.IMAGE,
//                                            "",
//                                            chatViewModel.itemId, 0)

                        }

                        break;
                }
            }
        });

        chatViewModel.getCheckReceiverStatusData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {

                    case ERROR:

                        if (getActivity() != null) {
                            //callback.changeText(result.message);
                            ((ChatActivity) getActivity()).changeText(result.message);
                        }

                        active = false;

                        break;

                    case SUCCESS:

                        if (getActivity() != null) {
                            //callback.changeText(result.message);
                            ((ChatActivity) getActivity()).changeText(result.message);
                        }

                        active = true;

                        break;
                }
            }
        });

        chatViewModel.getLoadingState().observe(this, loadingState -> binding.get().setLoadingMore(chatViewModel.isLoading));

        notificationViewModel.getResetUnreadCountData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:
                        break;

                    case ERROR:
                        break;
                }
            }
        });

        notificationViewModel.getSendChatNotiData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:
                        break;

                    case ERROR:
                        break;
                }
            }
        });

        chatViewModel.getSyncChatHistoryData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //update first message
                        chatViewModel.isFirstMessage = false;

                        break;

                    case ERROR:
                        break;
                }
            }
        });

        chatViewModel.getSellItemData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        Toast.makeText(getContext(), "Item Sold!", Toast.LENGTH_SHORT).show();
                        addItemIsSoldText();

                        break;

                    case ERROR:
                        break;
                }
            }
        });

        chatViewModel.getUpdateOfferPriceData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //add offer text
                        addOfferText();

                        break;

                    case ERROR:
                        break;
                }
            }
        });

        chatViewModel.getRejectedOfferData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //add offer text
                        addRejectedText();

                        break;

                    case ERROR:
                        break;
                }
            }
        });

        chatViewModel.getAcceptOfferData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //add offer text
                        addAcceptedText();

                        break;

                    case ERROR:

                        psDialogMsg.showErrorDialog(getString(R.string.error_message__already_accept), getString(R.string.app__ok));
                        psDialogMsg.show();

                        break;
                }
            }
        });

        chatViewModel.getUploadChattingWithData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {

                    case SUCCESS:
                        break;

                    case ERROR:
                        break;
                }
            }
        });
    }


    private void getItemDetailData() {

        LiveData<Resource<Item>> itemDetail = itemViewModel.getItemDetailData();
        if (itemDetail != null) {
            itemDetail.observe(this, listResource -> {
                if (listResource != null) {

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {

                                chatViewModel.itemImagePath = listResource.data.defaultPhoto.imgPath;
                                chatViewModel.itemName = listResource.data.title;
                                chatViewModel.itemPrice = listResource.data.price;
                                chatViewModel.itemCurrency = listResource.data.itemCurrency.currencySymbol;
                                chatViewModel.itemConditionName = listResource.data.itemCondition.name;

                                bindItemData();
                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {

                                chatViewModel.itemImagePath = listResource.data.defaultPhoto.imgPath;
                                chatViewModel.itemName = listResource.data.title;
                                chatViewModel.itemPrice = listResource.data.price;
                                chatViewModel.itemCurrency = listResource.data.itemCurrency.currencySymbol;
                                chatViewModel.itemConditionName = listResource.data.itemCondition.name;

                                bindItemData();

                            }

                            itemViewModel.setLoadingState(false);

                            break;

                        case ERROR:

                            // Error State
                            itemViewModel.setLoadingState(false);

                            break;

                        default:
                            // Default

                            break;
                    }

                } else {

                    itemViewModel.setLoadingState(false);

                }
            });
        }
    }

    private void bindItemData() {
        bindingButtonText(chatViewModel.chatFlag);
        binding.get().itemTextView.setText(chatViewModel.itemName);

        if (!chatViewModel.itemCurrency.equals("") && !chatViewModel.itemPrice.equals("")) {

            String currencySymbol = chatViewModel.itemCurrency;
            String price;
            try {
                price = Utils.format(Double.parseDouble(chatViewModel.itemPrice));

            } catch (Exception e) {
                price = chatViewModel.itemPrice;
            }

            Utils.psLog("**********" + price);

            String currencyPrice;
            if (Config.SYMBOL_SHOW_FRONT) {
                currencyPrice = currencySymbol + " " + price;
            } else {
                currencyPrice = price + " " + currencySymbol;
            }

            if (chatViewModel.itemPrice.isEmpty()) {

                binding.get().priceTextView.setText(currencyPrice);
            } else {

                binding.get().priceTextView.setText(currencyPrice);
            }
        }
//        else{
//Utils.psLog("Reach else price and currency are null");
//        }
        dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().itemImageView, chatViewModel.itemImagePath);
        binding.get().conditionTextView.setText(getString(R.string.item_condition__type, chatViewModel.itemConditionName));

        if(chatViewModel.itemPriceUnit.equals("")){
            binding.get().lakhTextView.setVisibility(View.GONE);
        }else{
            binding.get().lakhTextView.setVisibility(View.VISIBLE);
            binding.get().lakhTextView.setText(chatViewModel.itemPriceUnit);
        }
    }

    private void getRatingPostData() {
        //get rating post method
        ratingViewModel.getRatingPostData().observe(this, result -> {
            if (result != null) {
                if (result.status == Status.SUCCESS) {
                    if (this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        ratingViewModel.setLoadingState(false);
                        prgDialog.get().cancel();
                        reviewDialog.get().cancel();

                        Toast.makeText(getContext(), "Successed Rating.", Toast.LENGTH_SHORT).show();
                    }

                } else if (result.status == Status.ERROR) {
                    if (this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        ratingViewModel.setLoadingState(false);
                        prgDialog.get().cancel();
                        reviewDialog.get().cancel();
                    }
                }
            }
        });
    }

    private void addOfferText() {

        String currencySymbol = chatViewModel.itemCurrency;
        String price;
        try {
            price = Utils.format(Double.parseDouble(chatViewModel.offerItemPrice));

        } catch (Exception e) {
            price = chatViewModel.offerItemPrice;
        }
        String currencyPrice;
        if (Config.SYMBOL_SHOW_FRONT) {
            currencyPrice = currencySymbol + " " + price;
        } else {
            currencyPrice = price + " " + currencySymbol;
        }

        chatViewModel.setSaveMessagesToFirebaseObj(new Message(
                Utils.generateKeyForChatHeadId(loginUserId, chatViewModel.receiverId),
                chatViewModel.itemId,
                currencyPrice,
                Constants.CHAT_TYPE_OFFER,
                loginUserId,
                Constants.CHAT_STATUS_OFFER,
                false
        ), loginUserId, chatViewModel.receiverId);

    }

    private void addItemIsSoldText() {

        chatViewModel.setUpdateMessageIsSoldStatusToFirebaseObj(chatViewModel.offerMessage);

    }

    private void addAcceptedText() {

        chatViewModel.setUpdateMessageOfferStatusToFirebaseObj(chatViewModel.offerMessage);

    }

    private void addRejectedText() {

        chatViewModel.setUpdateMessageOfferStatusToFirebaseObj(chatViewModel.offerMessage);

    }


    private List<Message> regenerateMessageList(List<Message> messageList) {

        String currentDate = "";
        for (int i = 0; i < messageList.size(); i++) {

            // Date Checking
//            Date date = Utils.getDate(messageList.get(i).timestamp);
            Date date = Utils.getDateCurrentTimeZone(messageList.get(i).addedDate);
            String dateString = Utils.getDateString(date, "yyyy.MM.dd");
            String timeString = Utils.getDateString(date, "hh:mm a");

            messageList.get(i).date = date;
            messageList.get(i).dateString = dateString;
            messageList.get(i).time = timeString;

            if (!currentDate.isEmpty() && !currentDate.equals(dateString)) {

                Message obj = messageList.get(i);

                // add new message
                messageList.add(i, new Message(
                        "",
                        obj.itemId,
                        currentDate,
                        Constants.CHAT_TYPE_DATE,
                        obj.sendByUserId,
                        obj.offerStatus,
                        false
                ));

                messageList.get(i).date = date;
                messageList.get(i).dateString = dateString;


                currentDate = dateString;
            }

            if (currentDate.isEmpty()) {
                currentDate = dateString;
            }

            //offer//accept//reject

//            if(messageList.get(i).sendByUserId.equals(loginUserId)) {//sender

//                if (!messageList.get(i).message.isEmpty() && messageList.get(i).message.length() > 5 ) {
//                    // Offer Checking
//
//                    switch (messageList.get(i).message.substring(0, 6)) {
//                        case Constants.MAKE_OFFER_KEY:
//                            messageList.get(i).message = messageList.get(i).message.substring(6);//10
//                            //messageList.get(i).sessionId = String.valueOf(Constants.CHAT_SENDER_OFFER_UI);
//                            break;
//                        case Constants.REJECT_KEY:
//                            messageList.get(i).message = messageList.get(i).message.substring(6);
//                            //messageList.get(i).chatId = String.valueOf(Constants.CHAT_SENDER_REJECT_UI);
//                            break;
//                        case Constants.ACCEPT_KEY:
//                            messageList.get(i).message = messageList.get(i).message.substring(6);
//                            //messageList.get(i).chatId = String.valueOf(Constants.CHAT_SENDER_ACCEPT_UI);
//
//                            break;
//                        default:
//                            messageList.get(i).message = messageList.get(i).message;
//                            break;
//                    }
//                }

//            }else {//receiver
//
//                if (!messageList.get(i).message.isEmpty() && messageList.get(i).message.length() > 5 ) {
//                    // Offer Checking
//
//                    switch (messageList.get(i).message.substring(0, 6)) {
//                        case Constants.MAKE_OFFER_KEY:
//                            messageList.get(i).message = messageList.get(i).message.substring(6);//10
//                            messageList.get(i).chatId = String.valueOf(Constants.CHAT_RECEIVER_OFFER_UI);
//                            break;
//                        case Constants.REJECT_KEY:
//                            messageList.get(i).message = messageList.get(i).message.substring(6);
//                            messageList.get(i).chatId = String.valueOf(Constants.CHAT_RECEIVER_REJECT_UI);
//                            break;
//                        case Constants.ACCEPT_KEY:
//                            messageList.get(i).message = messageList.get(i).message.substring(6);
//                            messageList.get(i).chatId = String.valueOf(Constants.CHAT_RECEIVER_ACCEPT_UI);
//
//                            break;
//                        default:
//                            messageList.get(i).message = messageList.get(i).message;
//                            break;
//                    }
//                }
//            }

        }

        try {
            if (messageList.size() > 0 && messageList.get(0).offerStatus == Constants.CHAT_STATUS_REJECT) {
                loadChatHistory();
            }

            if (messageList.size() > 0 && messageList.get(0).offerStatus == Constants.CHAT_STATUS_ACCEPT) {
                loadChatHistory();
            }
        } catch (Exception e) {
            Utils.psErrorLog("", e);
        }

        //endregion

        return messageList;
    }

    private void getCustomLayoutDialog() {
        reviewDialog = new AutoClearedValue<>(this, new Dialog(binding.get().getRoot().getContext()));
        itemRatingEntryBinding = new AutoClearedValue<>(this, DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.item_rating_entry, null, false, dataBindingComponent));

        reviewDialog.get().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //reviewDialog.setContentView(R.layout.item_rating_entry);
        reviewDialog.get().setContentView(itemRatingEntryBinding.get().getRoot());

        itemRatingEntryBinding.get().ratingBarDialog.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingViewModel.numStar = rating;
            itemRatingEntryBinding.get().ratingBarDialog.setRating(rating);
        });

        itemRatingEntryBinding.get().cancelButton.setOnClickListener(v -> {
            reviewDialog.get().dismiss();
            reviewDialog.get().cancel();
        });

        itemRatingEntryBinding.get().submitButton.setOnClickListener(v -> {

            if (itemRatingEntryBinding.get().titleEditText.getText().toString().isEmpty() ||
                    itemRatingEntryBinding.get().messageEditText.getText().toString().isEmpty() || String.valueOf(itemRatingEntryBinding.get().ratingBarDialog.getRating()).equals("0.0")) {

                psDialogRatingMsg.showErrorDialog(getString(R.string.error_message__rating), getString(R.string.app__ok));
                psDialogRatingMsg.show();
                psDialogRatingMsg.okButton.setOnClickListener(v1 -> psDialogRatingMsg.cancel());
            } else {
                ratingViewModel.setRatingPostObj(itemRatingEntryBinding.get().titleEditText.getText().toString(),
                        itemRatingEntryBinding.get().messageEditText.getText().toString(),
                        ratingViewModel.numStar + "",
                        loginUserId, chatViewModel.receiverId);

                prgDialog.get().show();

            }

        });


        Window window = reviewDialog.get().getWindow();
        if (reviewDialog != null && window != null) {
            WindowManager.LayoutParams params = getLayoutParams(reviewDialog.get());
            if (params != null) {
                window.setAttributes(params);
            }
        }

        if (reviewDialog != null) {
            reviewDialog.get().show();
        }

    }

    private WindowManager.LayoutParams getLayoutParams(@NonNull Dialog dialog) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (dialog.getWindow() != null) {
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
        }
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return layoutParams;
    }

    private void readMessage() {

        if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {
            notificationViewModel.setResetUnreadCountObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId, Constants.CHAT_TO_SELLER);
        } else {
            notificationViewModel.setResetUnreadCountObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, Constants.CHAT_TO_BUYER);
        }

    }

    private void loadChatHistory() {
        if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {
            chatHistoryViewModel.setChatHistoryObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId);
        } else {
            chatHistoryViewModel.setChatHistoryObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId);
        }
    }

    private void replaceChatHistoryData(ChatHistory chatHistory) {
        chatHistoryViewModel.chatHistory = chatHistory;

        if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {
            if (chatHistory.item.isSoldOut.equals(Constants.ZERO)) {
                binding.get().soldTextView.setVisibility(View.GONE);
            } else {
                binding.get().soldTextView.setVisibility(View.VISIBLE);
            }
        } else {
            if (chatHistory.isOffer.equals(Constants.ONE)) {

                binding.get().offerButton.setVisibility(View.GONE);
                binding.get().offerButton.setEnabled(false);

            } else {
                binding.get().offerButton.setVisibility(View.VISIBLE);
                binding.get().offerButton.setEnabled(true);
            }

            if (chatHistory.item.isSoldOut.equals(Constants.ONE)) {
                binding.get().soldTextView.setVisibility(View.VISIBLE);
            } else {
                binding.get().soldTextView.setVisibility(View.GONE);
            }
        }
    }

    public void replaceData(List<Message> messageList) {

        Collections.reverse(messageList);

        List<Message> updatedMessages = regenerateMessageList(messageList);

        adapter.get().replaceMessageList(updatedMessages);

        binding.get().executePendingBindings();

    }

    @Override
    public void onDispatched() {

        if (chatViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().chatListRecyclerView != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().chatListRecyclerView.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    private void pushNoti() {
        if (!active) {

            if (chatViewModel.chatFlag.equals(Constants.CHAT_FROM_BUYER)) {

                notificationViewModel.setSendChatNotiObj(chatViewModel.itemId, chatViewModel.receiverId, loginUserId, binding.get().editText.getText().toString().trim(), Constants.CHAT_TO_BUYER);

            } else {

                notificationViewModel.setSendChatNotiObj(chatViewModel.itemId, loginUserId, chatViewModel.receiverId, binding.get().editText.getText().toString().trim(), Constants.CHAT_TO_SELLER);

            }
        }
    }


}

