package com.panaceasoft.admotors.repository.chat;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.ChatHistory;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.messageHolder.Chat;
import com.panaceasoft.admotors.viewobject.messageHolder.Message;
import com.panaceasoft.admotors.viewobject.messageHolder.UserStatusHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ChatRepository extends PSRepository {

    private long lastChildTimeStamp = 0;
    private boolean loadMore = true;
    private boolean deleteAll = true;

    /**
     * Constructor of PSRepository
     *
     * @param psApiService Panacea-Soft API Service Instance
     * @param appExecutors Executors Instance
     * @param db           Panacea-Soft DB
     */
    @Inject
    ChatRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);
    }

    public LiveData<Resource<Boolean>> registerUserToFirebase(String email, String password) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        appExecutors.diskIO().execute(() -> firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(value -> {

            if (value.isSuccessful()) {
                statusLiveData.postValue(Resource.success(true));

            } else {

                if (value.getException() != null) {
                    try {
                        statusLiveData.postValue(Resource.error(value.getException().getMessage(), false));
                    } catch (Exception e) {
                        Utils.psErrorLog("EERROR", e);
                    }
                }

            }

        }));

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> loginUserToFireBase(String email, String password) {
        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        appExecutors.networkIO().execute(() -> {

            try {

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        statusLiveData.postValue(Resource.success(true));

                    } else {

                        if (task.getException() != null) {
                            statusLiveData.postValue(Resource.error(task.getException().getMessage(), null));

                            Utils.psLog(task.getException().getMessage());
                        }

                    }
                });

            } catch (Exception e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), null));
            }
        });

        return statusLiveData;
    }

//    public LiveData<Resource<Boolean>> saveChatToFirebase(Chat chat, String senderId, String receiverId) {
//        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
//
//        String key = Utils.generateKeyForChatHeadId(senderId, receiverId);
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//        reference.child(Config.CHAT).child(senderId).child(key).setValue(chat).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//
//                statusLiveData.postValue(Resource.success(true));
//
//            } else {
//
//                if (task.getException() != null) {
//                    statusLiveData.postValue(Resource.error(task.getException().getMessage(), null));
//
//                    Utils.psLog(task.getException().getMessage());
//                }
//
//            }
//        });
//
//        reference.child(Config.CHAT).child(receiverId).child(key).setValue(chat).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//
//                statusLiveData.postValue(Resource.success(true));
//
//            } else {
//
//                if (task.getException() != null) {
//                    statusLiveData.postValue(Resource.error(task.getException().getMessage(), null));
//
//                    Utils.psLog(task.getException().getMessage());
//                }
//
//            }
//        });
//
//        return statusLiveData;
//
//    }

    public LiveData<Resource<Boolean>> updateMessageOfferStatusToFirebaseById(Message message) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        appExecutors.diskIO().execute(() -> db.runInTransaction(() -> db.messageDao().deleteMessageById(message.id)));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference pushedPostRef = reference.child(Config.MESSAGE).child(message.sessionId).child(message.id).child(Constants.CHAT_OFFER_STATUS);

        pushedPostRef.setValue(message.offerStatus).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {

                        statusLiveData.postValue(Resource.success(true));

                    } else {

                        if (task.getException() != null) {
                            statusLiveData.postValue(Resource.error(task.getException().getMessage(), null));

                            Utils.psLog(task.getException().getMessage());
                        }
                    }
                });


        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> updateisSoldStatusToFirebaseById(Message message) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        appExecutors.diskIO().execute(() -> db.runInTransaction(() -> db.messageDao().deleteMessageById(message.id)));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference pushedPostRef = reference.child(Config.MESSAGE).child(message.sessionId).child(message.id).child(Constants.CHAT_IS_SOLD);

        pushedPostRef.setValue(message.isSold).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {

                        statusLiveData.postValue(Resource.success(true));

                    } else {

                        if (task.getException() != null) {
                            statusLiveData.postValue(Resource.error(task.getException().getMessage(), null));

                            Utils.psLog(task.getException().getMessage());
                        }
                    }
                });


        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> saveMessagesToFirebaseByChatHeadId(Message message) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        // Save
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        // Generate a reference to a new location and add some data using push()
        DatabaseReference pushedPostRef = reference.child(Config.MESSAGE).child(message.sessionId).push();

        String key = pushedPostRef.getKey();

        if (key != null) {

            message.id = key;

            pushedPostRef.setValue(message).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    statusLiveData.postValue(Resource.success(true));

                } else {

                    if (task.getException() != null) {
                        statusLiveData.postValue(Resource.error(task.getException().getMessage(), null));

                        Utils.psLog(task.getException().getMessage());
                    }

                }

            });
        } else {

            statusLiveData.postValue(Resource.error("", null));

        }

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> getMessagesFromSpecificNode(String senderId, String receiverId) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        if (loadMore) {

            String key = Utils.generateKeyForChatHeadId(senderId, receiverId);
            Query query;

            List<Message> messageList = new ArrayList<>();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            if (lastChildTimeStamp == 0 ) {

                query = databaseReference.child(Config.MESSAGE).child(key).orderByChild("addedDate").limitToLast(10);

            } else {

                query = databaseReference.child(Config.MESSAGE).child(key).orderByChild("addedDate").limitToLast(10).endAt(lastChildTimeStamp);

            }

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    messageList.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Utils.psLog("KEY : " + snapshot.getKey());

                        Message message = snapshot.getValue(Message.class);
                        messageList.add(message);
                    }

                    if (messageList.size() > 0) {

//                        if (lastChildTimeStamp != 0) {
//                            if (!lastChildTimeStamp.equals(messageList.get(0).timestamp) || messageList.size() != 1) {
//
//                                appExecutors.diskIO().execute(() -> db.runInTransaction(() -> {
//
//                                    if (deleteAll) {
//                                        db.messageDao().deleteAll(Utils.generateKeyForChatHeadId(senderId, receiverId));
//
//                                        deleteAll = false;
//                                    }
//
//                                    db.messageDao().insertAll(messageList);
//                                }));
//
//                                lastChildTimeStamp = messageList.get(0).timestamp;
//                            } else {
//                                loadMore = false;
//                            }

                        if (lastChildTimeStamp != messageList.get(0).addedDate || messageList.size() != 1) {

                            appExecutors.diskIO().execute(() -> db.runInTransaction(() -> {

                                if (deleteAll) {
                                    db.messageDao().deleteAll(Utils.generateKeyForChatHeadId(senderId, receiverId));

                                    deleteAll = false;
                                }

                                db.messageDao().insertAll(messageList);
                            }));

                            lastChildTimeStamp = messageList.get(0).addedDate;
                        } else {
                            loadMore = false;
                        }
//                        }

                    }

                    statusLiveData.postValue(Resource.success(true));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    statusLiveData.postValue(Resource.error("error", null));
                }
            });

        } else {

            statusLiveData.postValue(Resource.error("error", null));

        }

        return statusLiveData;
    }

    public LiveData<List<Message>> getMessagesFromDatabase(String senderId, String receiverId, String itemId) {
        return db.messageDao().getMessages(Utils.generateKeyForChatHeadId(senderId, receiverId), itemId);
    }

    public LiveData<Resource<Image>> uploadImage(String filePath, String senderId,String buyerUserId,String sellerUserId , String itemId ,String type) {

        final MediatorLiveData<Resource<Image>> statusLiveData = new MediatorLiveData<>();

        //Init File
        File file = new File(filePath);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file news_title
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // add another part within the multipart request

        RequestBody useIdRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), senderId);

        RequestBody buyerUserIdRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), buyerUserId);

        RequestBody sellerUserIdRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), sellerUserId);

        RequestBody itemIdRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), itemId);

        RequestBody typeRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), type);


        LiveData<ApiResponse<Image>> apiResponse = psApiService.chatImageUpload( Config.API_KEY, useIdRB, body, buyerUserIdRB, sellerUserIdRB, itemIdRB , typeRB);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            appExecutors.diskIO().execute(() -> {

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.successWithMsg("success", response.body));
                } else {
                    statusLiveData.postValue(Resource.error("error", null));
                }

            });

        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> removeActiveStateToFirebase(UserStatusHolder userStatusHolder) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.child(Config.USER_PRESENCE).child(userStatusHolder.userId).removeValue().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    statusLiveData.postValue(Resource.successWithMsg(Config.SUCCESSFULLY_SAVED, true));

                } else {

                    if (task.getException() != null) {
                        statusLiveData.postValue(Resource.error(task.getException().getMessage(), false));

                    } else {
                        statusLiveData.postValue(Resource.error("error", false));

                    }
                }

            });
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> uploadActiveStateToFirebase(UserStatusHolder userStatusHolder) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.child(Config.USER_PRESENCE).child(userStatusHolder.userId).onDisconnect().removeValue().addOnCompleteListener(task -> statusLiveData.postValue(Resource.successWithMsg(Config.SUCCESSFULLY_DELETED, true)));

            databaseReference.child(Config.USER_PRESENCE).child(userStatusHolder.userId).setValue(userStatusHolder).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    statusLiveData.postValue(Resource.successWithMsg(Config.SUCCESSFULLY_SAVED, true));

                } else {

                    if (task.getException() != null) {
                        statusLiveData.postValue(Resource.error(task.getException().getMessage(), false));

                    } else {
                        statusLiveData.postValue(Resource.error("error", false));

                    }
                }

            });
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> checkTheUserPresenceStatus(String receiverId, String senderId, String itemId) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            reference.child(Config.USER_PRESENCE).child(receiverId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        statusLiveData.postValue(Resource.successWithMsg(Config.INACTIVE, true));

                        reference.child(Config.CHAT_WITH).child(receiverId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.exists()) {
                                    if (dataSnapshot.getValue() != null) {

                                        Chat chat = dataSnapshot.getValue(Chat.class);

                                        if (chat != null) {
                                            if (chat.getReceiver_id().equals(senderId) && chat.getItemId().equals(itemId)) {
                                                statusLiveData.postValue(Resource.successWithMsg(Config.ACTIVE, true));
                                            } else {
                                                statusLiveData.postValue(Resource.successWithMsg(Config.INACTIVE, true));
                                            }
                                        }

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {
                        statusLiveData.postValue(Resource.error(Config.OFFLINE, false));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });

        return statusLiveData;
    }

//    public LiveData<Resource<List<ChatUserHolder>>> getChatUserList(String userId) {
//        final MutableLiveData<Resource<List<ChatUserHolder>>> statusLiveData = new MutableLiveData<>();
//
//        List<ChatUserHolder> userHolders = new ArrayList<>();
//
//        appExecutors.networkIO().execute(() -> {
//
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//            reference.child(Config.CHAT).child(userId).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
//                        ChatUserHolder chatUserHolder = new ChatUserHolder();
//
//                        Chat chat = dataSnapshot.getValue(Chat.class);
//
//                        if (chat != null) {
//                            chatUserHolder.userId = chat.getReceiver_id();
//                        }
//
//                        reference.child(Config.MESSAGE).child(String.valueOf(snapshot.getValue())).limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                chatUserHolder.lastMessage = String.valueOf(dataSnapshot.getValue());
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        reference.child(Config.USER_PRESENCE).child(chatUserHolder.userId).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                UserStatusHolder userStatusHolder;
//
//                                if (dataSnapshot.exists()) {
//                                    chatUserHolder.userStatus = Config.ACTIVE;
//
//                                    userStatusHolder = dataSnapshot.getValue(UserStatusHolder.class);
//
//                                    if (userStatusHolder != null) {
//                                        chatUserHolder.userName = userStatusHolder.userName;
//                                    }
//
//                                } else {
//                                    chatUserHolder.userStatus = Config.INACTIVE;
//
//                                    userStatusHolder = dataSnapshot.getValue(UserStatusHolder.class);
//
//                                    if (userStatusHolder != null) {
//                                        chatUserHolder.userName = userStatusHolder.userName;
//                                    }
//
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        userHolders.add(chatUserHolder);
//
//                    }
//
//                    statusLiveData.postValue(Resource.success(userHolders));
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    statusLiveData.postValue(Resource.error("error", null));
//
//                }
//            });
//        });
//
//        return statusLiveData;
//    }

    public LiveData<Resource<Boolean>> uploadChattingWith(Chat chat) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.child(Config.CHAT_WITH).child(chat.getSender_id()).onDisconnect().removeValue().addOnCompleteListener(task -> statusLiveData.postValue(Resource.successWithMsg(Config.SUCCESSFULLY_DELETED, true)));

            databaseReference.child(Config.CHAT_WITH).child(chat.getSender_id()).setValue(chat).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    statusLiveData.postValue(Resource.successWithMsg(Config.SUCCESSFULLY_SAVED, true));

                } else {

                    if (task.getException() != null) {
                        statusLiveData.postValue(Resource.error(task.getException().getMessage(), false));

                    } else {
                        statusLiveData.postValue(Resource.error("error", false));

                    }
                }

            });
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> sellItem(String itemId, String buyerUserId, String sellerUserId) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ChatHistory> response;

            try {
                response = psApiService.sellItem(Config.API_KEY, itemId, buyerUserId, sellerUserId).execute();

                db.runInTransaction(() -> {

                    try {

                        db.chatHistoryDao().deleteChatHistory(itemId, buyerUserId, sellerUserId);

                        db.chatHistoryDao().insert(response.body());

                    } catch (Exception e) {
                        Utils.psErrorLog("Error in doing transaction of syncChatHistory.", e);
                    }
                });

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error("error", false));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> syncChatHistory(String itemId, String buyerUserId, String sellerUserId ,String type) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ChatHistory> response;

            try {
                response = psApiService.syncChatHistory(Config.API_KEY, itemId, buyerUserId, sellerUserId, type).execute();

                db.runInTransaction(() -> {

                    try {

                        db.chatHistoryDao().deleteChatHistory(itemId, buyerUserId, sellerUserId);

                        db.chatHistoryDao().insert(response.body());

                    } catch (Exception e) {
                        Utils.psErrorLog("Error in doing transaction of syncChatHistory.", e);
                    }
                });

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error("error", false));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> updateNegoPrice(String itemId, String buyerUserId, String sellerUserId, String negoPrice, String type) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ChatHistory> response;

            try {
                response = psApiService.updateNegoPrice(Config.API_KEY, itemId, buyerUserId, sellerUserId, negoPrice, type).execute();

                db.runInTransaction(() -> {

                    try {

                        db.chatHistoryDao().deleteChatHistory(itemId, buyerUserId, sellerUserId);

                        db.chatHistoryDao().insert(response.body());

                    } catch (Exception e) {
                        Utils.psErrorLog("Error in doing transaction of syncChatHistory.", e);
                    }
                });

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error("error", false));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> acceptedOffer(String itemId, String buyerUserId, String sellerUserId, String price, String type) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ChatHistory> response;

            try {
                response = psApiService.acceptedOffer(Config.API_KEY, itemId, buyerUserId, sellerUserId, price, type).execute();

                db.runInTransaction(() -> {

                    try {

                        if(response.body() != null) {

                            db.chatHistoryDao().deleteChatHistory(itemId, buyerUserId, sellerUserId);

                            db.chatHistoryDao().insert(response.body());

                            db.itemDao().insert(response.body().item);
                        }

                    } catch (Exception e) {
                        Utils.psErrorLog("Error in doing transaction of syncChatHistory.", e);
                    }
                });

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error("error", false));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return statusLiveData;
    }

    public LiveData<Resource<Boolean>> rejectOffer(String itemId, String buyerUserId, String sellerUserId,String type) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ChatHistory> response;

            try {
                response = psApiService.updateNegoPrice(Config.API_KEY, itemId, buyerUserId, sellerUserId, Constants.ZERO, type).execute();

                db.runInTransaction(() -> {

                    try {

                        db.chatHistoryDao().deleteChatHistory(itemId, buyerUserId, sellerUserId);

                        db.chatHistoryDao().insert(response.body());

                    } catch (Exception e) {
                        Utils.psErrorLog("Error in doing transaction of syncChatHistory.", e);
                    }
                });

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error("error", false));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return statusLiveData;
    }
}
