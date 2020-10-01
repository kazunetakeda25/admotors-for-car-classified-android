package com.panaceasoft.admotors.viewmodel.notification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.notification.NotificationRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Noti;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class NotificationsViewModel extends PSViewModel {

    //for recent comment list
    private final LiveData<Resource<List<Noti>>> notificationListData;
    private MutableLiveData<TmpDataHolder> notificationListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageNotificationLoadingData;
    private MutableLiveData<TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    private final LiveData<Resource<Noti>> notificationDetailListData;
    private MutableLiveData<TmpDataHolder> notificationDetailObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> notiPostData;
    private MutableLiveData<TmpDataHolder> notiPostObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> sendChatNotiData;
    private MutableLiveData<SendChatNotiTmpDataHolder> sendChatNotiObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> resetUnreadCountData;
    private MutableLiveData<ResetUnreadCountTmpDataHolder> resetUnreadCountObj = new MutableLiveData<>();

    public String token = "";
    public String notiId = "";

    @Inject
    public NotificationsViewModel(NotificationRepository notificationRepository) {

        notificationListData = Transformations.switchMap(notificationListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("Notification List.");
            return notificationRepository.getNotificationList(Config.API_KEY, obj.userId, obj.limit, obj.offset, obj.deviceToken);
        });

        nextPageNotificationLoadingData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("Notification List.");
            return notificationRepository.getNextPageNotificationList(obj.userId, obj.deviceToken, obj.limit, obj.offset);
        });

        notificationDetailListData = Transformations.switchMap(notificationDetailObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("Notification detail List.");
            return notificationRepository.getNotificationDetail(Config.API_KEY, obj.notificationId);
        });

        notiPostData = Transformations.switchMap(notiPostObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("Notification detail List.");
            return notificationRepository.uploadNotiPostToServer(obj.notificationId, obj.userId, obj.deviceToken);
        });

        sendChatNotiData = Transformations.switchMap(sendChatNotiObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return notificationRepository.sendChatNoti(obj.itemId,obj.senderId, obj.receiverId, obj.message, obj.type);

        });

        resetUnreadCountData = Transformations.switchMap(resetUnreadCountObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return notificationRepository.resetUnreadCount(obj.itemId,obj.buyerUserId, obj.sellerUserId, obj.type);

        });


    }
    //endregion

    //region Getter And Setter for Comment List

    public void setNotificationListObj(String userId, String deviceToken, String limit, String offset) {
        if (!isLoading) {
            NotificationsViewModel.TmpDataHolder tmpDataHolder = new NotificationsViewModel.TmpDataHolder();
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            tmpDataHolder.userId = userId;
            tmpDataHolder.deviceToken = deviceToken;
            notificationListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Noti>>> getNotificationListData() {
        return notificationListData;
    }

    //Get Comment Next Page
    public void setNextPageLoadingStateObj(String userId, String deviceToken, String limit, String offset) {

        if (!isLoading) {
            NotificationsViewModel.TmpDataHolder tmpDataHolder = new NotificationsViewModel.TmpDataHolder();
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            tmpDataHolder.userId = userId;
            tmpDataHolder.deviceToken = deviceToken;
            nextPageLoadingStateObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
        return nextPageNotificationLoadingData;
    }

    //endregion

    //region Getter And Setter for product detail List

    public void setNotificationDetailObj(String notificationId) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.notificationId = notificationId;
            notificationDetailObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Noti>> getNotificationDetailData() {
        return notificationDetailListData;
    }
    //endregion

    //region Getter And Setter for noti post

    public void setNotiReadObj(String notificationId, String userId, String deviceToken) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.notificationId = notificationId;
        tmpDataHolder.userId = userId;
        tmpDataHolder.deviceToken = deviceToken;
        notiPostObj.setValue(tmpDataHolder);

        // start loading
        setLoadingState(true);
    }

    public LiveData<Resource<Boolean>> getNotiReadData() {
        return notiPostData;
    }
    //endregion

    public void setSendChatNotiObj(String itemId, String senderId, String receiverId, String message, String type) {
        SendChatNotiTmpDataHolder tmpDataHolder = new SendChatNotiTmpDataHolder(itemId, senderId, receiverId, message, type);

        this.sendChatNotiObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getSendChatNotiData() {
        return sendChatNotiData;
    }


    public void setResetUnreadCountObj(String itemId, String senderId, String receiverId, String type) {
        ResetUnreadCountTmpDataHolder tmpDataHolder = new ResetUnreadCountTmpDataHolder(itemId, senderId, receiverId, type);

        this.resetUnreadCountObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getResetUnreadCountData() {
        return resetUnreadCountData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
        public Boolean isConnected = false;
        public String notificationId = "";
        public String userId = "";
        public String deviceToken = "";
    }

    class SendChatNotiTmpDataHolder {

        public String senderId, receiverId, message, itemId, type;

        public SendChatNotiTmpDataHolder(String itemId, String senderId, String receiverId, String message, String type) {
            this.itemId = itemId;
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.message = message;
            this.itemId = itemId;
            this.type = type;
        }
    }

    class ResetUnreadCountTmpDataHolder {

        public String buyerUserId, sellerUserId, itemId, type;

        public ResetUnreadCountTmpDataHolder(String itemId, String buyerUserId, String sellerUserId, String type) {
            this.itemId = itemId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
            this.itemId = itemId;
            this.type = type;
        }
    }
}
