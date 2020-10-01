package com.panaceasoft.admotors.viewmodel.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.repository.chat.ChatRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.messageHolder.Chat;
import com.panaceasoft.admotors.viewobject.messageHolder.Message;
import com.panaceasoft.admotors.viewobject.messageHolder.UserStatusHolder;

import java.util.List;

import javax.inject.Inject;

public class ChatViewModel extends PSViewModel {

    private final LiveData<Resource<Boolean>> registerUserToFirebaseData;
    private MutableLiveData<TmpDataHolder> registerUserToFirebaseObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> loginUserToFirebaseData;
    private MutableLiveData<TmpDataHolder> loginUserToFirebaseObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> saveMessagesToFirebaseData;
    private MutableLiveData<saveMessageTmpDataHolder> saveMessagesToFirebaseObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> updateMessageOfferStatusToFirebaseData;
    private MutableLiveData<updateMessageTmpDataHolder> updateMessageOfferStatusToFirebaseObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> updateMessageIsSoldStatusToFirebaseData;
    private MutableLiveData<updateMessageTmpDataHolder> updateMessageIsSoldStatusToFirebaseObj = new MutableLiveData<>();

    private final LiveData<Resource<Image>> uploadImageData;
    private MutableLiveData<imageUploadTmpDataHolder> uploadImageObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> fetchMessagesFromConversationData;
    private MutableLiveData<fetchMessagesTmpDataHolder> fetchMessagesFromConversationObj = new MutableLiveData<>();

    private final LiveData<List<Message>> getMessagesFromDatabaseData;
    private MutableLiveData<fetchMessagesTmpDataHolder> getMessagesFromDatabaseObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> userPresenceStatusData;
    private MutableLiveData<UserStatusHolder> userPresenceStatusObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> removeUserPresenceStatusData;
    private MutableLiveData<UserStatusHolder> removeUserPresenceStatusObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> checkReceiverStatusData;
    private MutableLiveData<fetchMessagesTmpDataHolder> checkReceiverStatusObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> uploadChattingWithData;
    private MutableLiveData<Chat> uploadChattingWithObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> updateOfferPriceData;
    private MutableLiveData<ChatViewModel.updateOffsePriceTmpDataHolder> updateOffseObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> syncChatHistoryData;
    private MutableLiveData<AddChatHistoryTmpDataHolder> syncChatHistoryObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> sellItemData;
    private MutableLiveData<SyncChatHistoryTmpDataHolder> sellItemObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> rejectOfferData;
    private MutableLiveData<ChatViewModel.rejectOfferTmpDataHolder> rejectOfferObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> acceptedOfferData;
    private MutableLiveData<ChatViewModel.acceptOffseTmpDataHolder> acceptedOfferObj = new MutableLiveData<>();

    public String receiverId = "";
    public String receiverName = "";
    public String receiverUserImgUrl = "";
    public String itemImagePath = "";
    public String itemName = "";
    public String itemPrice = "0";
    public String offerItemPrice = "0";
    public String itemCurrency = "";
    public String itemConditionName = "";
    public String itemPriceUnit = "";
    public String chatFlag = "";
    public String itemId = "";

    public boolean isFirstMessage = true;

    public Message offerMessage = null;

    @Inject
    ChatViewModel(ChatRepository repository) {

        registerUserToFirebaseData = Transformations.switchMap(registerUserToFirebaseObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.registerUserToFirebase(obj.email, obj.password);

        });

        loginUserToFirebaseData = Transformations.switchMap(loginUserToFirebaseObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.loginUserToFireBase(obj.email, obj.password);

        });

        updateMessageOfferStatusToFirebaseData = Transformations.switchMap(updateMessageOfferStatusToFirebaseObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.updateMessageOfferStatusToFirebaseById(obj.message);

        });

        updateMessageIsSoldStatusToFirebaseData = Transformations.switchMap(updateMessageIsSoldStatusToFirebaseObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.updateisSoldStatusToFirebaseById(obj.message);

        });

        saveMessagesToFirebaseData = Transformations.switchMap(saveMessagesToFirebaseObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.saveMessagesToFirebaseByChatHeadId(obj.message);

        });

        fetchMessagesFromConversationData = Transformations.switchMap(fetchMessagesFromConversationObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getMessagesFromSpecificNode(obj.senderId, obj.receiverId);

        });

        uploadImageData = Transformations.switchMap(uploadImageObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.uploadImage(obj.path, obj.senderId, obj.buyerUserId, obj.sellerUserId, obj.itemId, obj.type);
        });

        getMessagesFromDatabaseData = Transformations.switchMap(getMessagesFromDatabaseObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getMessagesFromDatabase(obj.senderId, obj.receiverId, obj.itemId);
        });

        userPresenceStatusData = Transformations.switchMap(userPresenceStatusObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.uploadActiveStateToFirebase(obj);
        });

        removeUserPresenceStatusData = Transformations.switchMap(removeUserPresenceStatusObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.removeActiveStateToFirebase(obj);
        });

        checkReceiverStatusData = Transformations.switchMap(checkReceiverStatusObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.checkTheUserPresenceStatus(obj.receiverId, obj.senderId, obj.itemId);
        });

//        chatUserListData = Transformations.switchMap(chatUserListObj, obj -> {
//
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//
//            return repository.getChatUserList(obj);
//
//        });

        uploadChattingWithData = Transformations.switchMap(uploadChattingWithObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.uploadChattingWith(obj);
        });

        updateOfferPriceData = Transformations.switchMap(updateOffseObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.updateNegoPrice(obj.itemId, obj.buyerUserId, obj.sellerUserId, obj.negoPrice, obj.type);

        });

        syncChatHistoryData = Transformations.switchMap(syncChatHistoryObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.syncChatHistory(obj.itemId, obj.buyerUserId, obj.sellerUserId, obj.type);

        });

        sellItemData = Transformations.switchMap(sellItemObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.sellItem(obj.itemId, obj.buyerUserId, obj.sellerUserId);

        });

        acceptedOfferData = Transformations.switchMap(acceptedOfferObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.acceptedOffer(obj.itemId, obj.buyerUserId, obj.sellerUserId, obj.price, obj.type);

        });

        rejectOfferData = Transformations.switchMap(rejectOfferObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.rejectOffer(obj.itemId, obj.buyerUserId, obj.sellerUserId, obj.type);

        });


    }

    public void setUpdateOfferPriceObj(String itemId, String buyerUserId, String sellerUserId, String negoPrice, String type) {
        updateOffsePriceTmpDataHolder tmpDataHolder = new updateOffsePriceTmpDataHolder(itemId, buyerUserId, sellerUserId, negoPrice, type);

        this.updateOffseObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getUpdateOfferPriceData() {
        return updateOfferPriceData;
    }

    public void setSyncChatHistoryObj(String itemId, String buyerUserId, String sellerUserId, String type) {
        AddChatHistoryTmpDataHolder tmpDataHolder = new AddChatHistoryTmpDataHolder(itemId, buyerUserId, sellerUserId, type);

        this.syncChatHistoryObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getSyncChatHistoryData() {
        return syncChatHistoryData;
    }

    public void setSellItemObj(String itemId, String buyerUserId, String sellerUserId) {
        SyncChatHistoryTmpDataHolder tmpDataHolder = new SyncChatHistoryTmpDataHolder(itemId, buyerUserId, sellerUserId);

        this.sellItemObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getSellItemData() {
        return sellItemData;
    }

    public void setRejectedOfferObj(String itemId, String buyerUserId, String sellerUserId, String negoPrice, String type) {
        rejectOfferTmpDataHolder tmpDataHolder = new rejectOfferTmpDataHolder(itemId, buyerUserId, sellerUserId, negoPrice, type);

        this.rejectOfferObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getRejectedOfferData() {
        return rejectOfferData;
    }

    public void setAcceptOfferObj(String itemId, String buyerUserId, String sellerUserId, String price, String type) {
        acceptOffseTmpDataHolder tmpDataHolder = new acceptOffseTmpDataHolder(itemId, buyerUserId, sellerUserId, price, type);

        this.acceptedOfferObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getAcceptOfferData() {
        return acceptedOfferData;
    }


    public void setRegisterUserToFirebaseObj(String email, String password) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder(email, password);

        this.registerUserToFirebaseObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getRegisterUserToFirebaseData() {
        return registerUserToFirebaseData;
    }

    public void setLoginUserToFirebaseObj(String email, String password) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder(email, password);

        this.loginUserToFirebaseObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getLoginUserToFirebaseData() {
        return loginUserToFirebaseData;
    }

//    public void setSaveChatToFirebaseObj(Chat chat, String senderId, String receiverId) {
//        saveChatTmpDataHolder tmpDataHolder = new saveChatTmpDataHolder(chat, senderId, receiverId);
//
//        this.saveChatToFirebaseObj.setValue(tmpDataHolder);
//    }
//
//    public LiveData<Resource<Boolean>> getSaveChatToFirebaseData() {
//        return saveChatToFirebaseData;
//    }

    public void setSaveMessagesToFirebaseObj(Message messages, String senderId, String receiverId) {
        saveMessageTmpDataHolder tmpDataHolder = new saveMessageTmpDataHolder(messages, senderId, receiverId);

        this.saveMessagesToFirebaseObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getSaveMessagesToFirebaseData() {
        return saveMessagesToFirebaseData;
    }

    public void setUpdateMessageOfferStatusToFirebaseObj(Message messages) {
        updateMessageTmpDataHolder tmpDataHolder = new updateMessageTmpDataHolder(messages);

        this.updateMessageOfferStatusToFirebaseObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getUpdateMessageOfferStatusToFirebaseData() {
        return updateMessageOfferStatusToFirebaseData;
    }

    public void setUpdateMessageIsSoldStatusToFirebaseObj(Message messages) {
        updateMessageTmpDataHolder tmpDataHolder = new updateMessageTmpDataHolder(messages);

        this.updateMessageIsSoldStatusToFirebaseObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getUpdateMessageIsSoldStatusToFirebaseData() {
        return updateMessageIsSoldStatusToFirebaseData;
    }

    public void setFetchMessagesFromConversationObj(String senderId, String receiverId) {
        if (!isLoading) {
            fetchMessagesTmpDataHolder tmpDataHolder = new fetchMessagesTmpDataHolder(senderId, receiverId, "");

            this.fetchMessagesFromConversationObj.setValue(tmpDataHolder);

            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getFetchMessagesFromConversationData() {
        return fetchMessagesFromConversationData;
    }

    public void setGetMessagesFromDatabaseObj(String senderId, String receiverId, String itemId) {
        fetchMessagesTmpDataHolder tmpDataHolder = new fetchMessagesTmpDataHolder(senderId, receiverId, itemId);

        this.getMessagesFromDatabaseObj.setValue(tmpDataHolder);
    }

    public LiveData<List<Message>> getGetMessagesFromDatabaseData() {
        return getMessagesFromDatabaseData;
    }

    public void setUploadImageObj(String path, String senderId, String buyerUserId, String sellerUserId, String itemId, String type) {

        imageUploadTmpDataHolder tmpDataHolder = new imageUploadTmpDataHolder(path, senderId, buyerUserId, sellerUserId, itemId, type);

        this.uploadImageObj.setValue(tmpDataHolder);

    }

    public LiveData<Resource<Image>> getUploadImageData() {
        return uploadImageData;
    }

    public void setUserPresenceStatusObj(UserStatusHolder userStatusHolder) {
        this.userPresenceStatusObj.setValue(userStatusHolder);
    }

    public LiveData<Resource<Boolean>> getUserPresenceStatusData() {
        return userPresenceStatusData;
    }

    public void setRemoveUserPresenceStatusObj(UserStatusHolder userStatusHolder) {
        this.removeUserPresenceStatusObj.setValue(userStatusHolder);
    }

    public LiveData<Resource<Boolean>> getRemoveUserPresenceStatusData() {
        return removeUserPresenceStatusData;
    }


    public void setCheckReceiverStatusObj(String senderId, String receiverId, String itemId) {
        fetchMessagesTmpDataHolder tmpDataHolder = new fetchMessagesTmpDataHolder(senderId, receiverId, itemId);

        this.checkReceiverStatusObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getCheckReceiverStatusData() {
        return checkReceiverStatusData;
    }

//    public void setChatUserListObj(String userId) {
//        this.chatUserListObj.setValue(userId);
//    }
//
//    public LiveData<Resource<List<ChatUserHolder>>> getChatUserListData() {
//        return chatUserListData;
//    }

    public void setUploadChattingWithObj(Chat chat) {
        this.uploadChattingWithObj.setValue(chat);
    }

    public LiveData<Resource<Boolean>> getUploadChattingWithData() {
        return uploadChattingWithData;
    }

    class TmpDataHolder {

        String email, password;

        TmpDataHolder(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }

    class saveChatTmpDataHolder {

        Chat chat;
        String senderId, receiverId;

        saveChatTmpDataHolder(Chat chat, String senderId, String receiverId) {
            this.chat = chat;
            this.senderId = senderId;
            this.receiverId = receiverId;
        }
    }

    class saveMessageTmpDataHolder {

        Message message;
        String senderId, receiverId;

        saveMessageTmpDataHolder(Message message, String senderId, String receiverId) {
            this.message = message;
            this.senderId = senderId;
            this.receiverId = receiverId;
        }
    }

    class updateMessageTmpDataHolder {

        Message message;

        updateMessageTmpDataHolder(Message message) {
            this.message = message;
        }
    }


    class fetchMessagesTmpDataHolder {

        String senderId, receiverId, itemId;

        fetchMessagesTmpDataHolder(String senderId, String receiverId, String itemId) {
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.itemId = itemId;
        }
    }

    class imageUploadTmpDataHolder {

        String path, senderId, buyerUserId, sellerUserId, itemId, type;

        imageUploadTmpDataHolder(String path, String senderId, String buyerUserId, String sellerUserId, String itemId, String type) {
            this.path = path;
            this.senderId = senderId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
            this.itemId = itemId;
            this.type = type;

        }
    }

    class SyncChatHistoryTmpDataHolder {

        public String itemId, buyerUserId, sellerUserId;

        private SyncChatHistoryTmpDataHolder(String itemId, String buyerUserId, String sellerUserId) {
            this.itemId = itemId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
        }
    }

    class AddChatHistoryTmpDataHolder {

        public String itemId, buyerUserId, sellerUserId, type;

        private AddChatHistoryTmpDataHolder(String itemId, String buyerUserId, String sellerUserId, String type) {
            this.itemId = itemId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
            this.type = type;
        }
    }

    class updateOffsePriceTmpDataHolder {

        public String itemId, buyerUserId, sellerUserId, negoPrice, type;

        private updateOffsePriceTmpDataHolder(String itemId, String buyerUserId, String sellerUserId, String negoPrice, String type) {
            this.itemId = itemId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
            this.negoPrice = negoPrice;
            this.type = type;
        }
    }

    class rejectOfferTmpDataHolder {

        public String itemId, buyerUserId, sellerUserId, negoPrice, type;

        private rejectOfferTmpDataHolder(String itemId, String buyerUserId, String sellerUserId, String negoPrice, String type) {
            this.itemId = itemId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
            this.negoPrice = negoPrice;
            this.type = type;
        }
    }

    class acceptOffseTmpDataHolder {

        public String itemId, buyerUserId, sellerUserId, price, type;

        private acceptOffseTmpDataHolder(String itemId, String buyerUserId, String sellerUserId, String price, String type) {
            this.itemId = itemId;
            this.buyerUserId = buyerUserId;
            this.sellerUserId = sellerUserId;
            this.price = price;
            this.type = type;
        }
    }
}
