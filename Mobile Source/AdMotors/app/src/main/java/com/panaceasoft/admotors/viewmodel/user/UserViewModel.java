package com.panaceasoft.admotors.viewmodel.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.user.UserRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.ApiStatus;
import com.panaceasoft.admotors.viewobject.User;
import com.panaceasoft.admotors.viewobject.UserLogin;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.holder.UserParameterHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Panacea-Soft on 12/12/17.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class UserViewModel extends PSViewModel {


    //region Variables

    public UserParameterHolder userHolder = new UserParameterHolder();

    private final MutableLiveData<Boolean> loadingState = new MutableLiveData<>();
    public boolean isLoading = false;
    public String profileImagePath = "";
    public User user;

    private final UserRepository repository;

    // for Login
    private final LiveData<Resource<UserLogin>> doUserLoginData;
    private MutableLiveData<User> doUserLoginObj = new MutableLiveData<>();

    // for get User
    private final LiveData<Resource<User>> userData;
    private MutableLiveData<DeleteUserTmpDataHolder> userObj = new MutableLiveData<>();

    // for get User
    private final LiveData<Resource<User>> otherUserData;
    private MutableLiveData<OtherUserTmpDataHolder> otherUserObj = new MutableLiveData<>();

    // for register
    private final LiveData<Resource<User>> registerUserData;
    private MutableLiveData<UserTmpDataHolder> registerUserObj = new MutableLiveData<>();

    // for register FB
    private final LiveData<Resource<UserLogin>> registerFBUserData;
    private MutableLiveData<TmpDataHolder> registerFBUserObj = new MutableLiveData<>();

    // for phone login
    private final LiveData<Resource<UserLogin>> phoneLoginData;
    private MutableLiveData<TmpDataHolder> phoneLoginObj = new MutableLiveData<>();

    // for phone login
    private final LiveData<Resource<UserLogin>> googleLoginData;
    private MutableLiveData<TmpDataHolder> googleLoginObj = new MutableLiveData<>();

    // for getting login user from db
    private final LiveData<List<UserLogin>> userLoginData;
    private MutableLiveData<String> userLoginObj = new MutableLiveData<>();

    // for update user
    private final LiveData<Resource<ApiStatus>> updateUserData;
    private MutableLiveData<User> updateUserObj = new MutableLiveData<>();

    // for forgot password
    private final LiveData<Resource<ApiStatus>> forgotpasswordData;
    private MutableLiveData<String> forgotPasswordObj = new MutableLiveData<>();

    // for password update
    private final LiveData<Resource<ApiStatus>> passwordUpdateData;
    private MutableLiveData<TmpDataHolder> passwordUpdateObj = new MutableLiveData<>();

    // for image upload
    private MutableLiveData<String> imgObj = new MutableLiveData<>();

    //endregion

    //for gettingUserById
    private final LiveData<Resource<User>> fetchUserByIdData;
    private MutableLiveData<String> fetchUserByIdObj = new MutableLiveData<>();
    //endregion

    //for verification code
    private final LiveData<Resource<UserLogin>> verificationEmailData;
    private MutableLiveData<TmpDataHolder> verificationEmailObj = new MutableLiveData<>();


    //for resent verification code
    private final LiveData<Resource<Boolean>> resentVerifyCodeData;
    private MutableLiveData<UserViewModel.resentCodeTmpDataHolder> resentVerifyCodeObj = new MutableLiveData<>();

    private final LiveData<Resource<List<User>>> userSearchListByKeyData;
    private final MutableLiveData<UserViewModel.UserSearchTmpDataHolder> userSearchListByKeyObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageUserListByKeyData;
    private final MutableLiveData<UserViewModel.UserSearchTmpDataHolder> nextPageUserListByKeyObj = new MutableLiveData<>();

    //user follow post
    private final LiveData<Resource<Boolean>> userFollowPostData;
    private MutableLiveData<UserViewModel.UserFollowPostTmpDataHolder> userFollowPostObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> deleteUserData;
    private final MutableLiveData<DeleteUserTmpDataHolder> deleteUserObj = new MutableLiveData<>();

    public String isFollowed = Constants.EMPTY_STRING;
    public String otherUserId = Constants.EMPTY_STRING;

    //region Constructor
    @Inject
    public UserViewModel(UserRepository repository) {

        this.repository = repository;

        // Get other User Data
        otherUserData = Transformations.switchMap(otherUserObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : userLoginData");
            return repository.getOtherUser(Config.API_KEY, obj.userId, obj.otherUserId);
        });

        // Login User
        doUserLoginData = Transformations.switchMap(doUserLoginObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : doUserLoginData");
            return repository.doLogin(Config.API_KEY, obj.userEmail, obj.userPassword, obj.deviceToken);
        });

        // Register User
        registerUserData = Transformations.switchMap(registerUserObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : registerUserData");
            return repository.registerUser(Config.API_KEY, obj.user.userName, obj.user.userEmail, obj.user.userPassword, obj.user.deviceToken);
        });

        // Register FB User
        registerFBUserData = Transformations.switchMap(registerFBUserObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : registerFBUserData");
            return repository.registerFBUser(Config.API_KEY, obj.fbId, obj.name, obj.email, obj.imageUrl, obj.deviceToken);
        });

        // phone login User
        phoneLoginData = Transformations.switchMap(phoneLoginObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : phoneLoginData");
            return repository.postPhoneLogin(Config.API_KEY, obj.phoneId, obj.name, obj.phone, obj.deviceToken);
        });

        // google login User
        googleLoginData = Transformations.switchMap(googleLoginObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : googleLoginData");
            return repository.postGoogleLogin(Config.API_KEY, obj.googleId, obj.name, obj.email, obj.imageUrl, obj.deviceToken);
        });

        // Get User Data
        userLoginData = Transformations.switchMap(userLoginObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : userLoginData");
            return repository.getLoginUser();
        });

        // Get Login User Data
        userData = Transformations.switchMap(userObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : userLoginData");
            return repository.getLoginUser(Config.API_KEY, obj.userId);
        });

        deleteUserData = Transformations.switchMap(deleteUserObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.deletePostUser(obj.userId);

        });

        // Update User
        updateUserData = Transformations.switchMap(updateUserObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : updateUserData");
            return repository.updateUser(Config.API_KEY, updateUserObj.getValue());
        });

        // Forgot Password
        forgotpasswordData = Transformations.switchMap(forgotPasswordObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : forgotPasswordData");
            return repository.forgotPassword(Config.API_KEY, obj);
        });

        // Password Update
        passwordUpdateData = Transformations.switchMap(passwordUpdateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("UserViewModel : passwordUpdateData");
            return repository.passwordUpdate(Config.API_KEY, obj.loginUserId, obj.password);
        });

        fetchUserByIdData = Transformations.switchMap(fetchUserByIdObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getUserById(obj);
        });

        verificationEmailData = Transformations.switchMap(verificationEmailObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.verificationCodeForUser(obj.loginUserId, obj.code);
        });

        resentVerifyCodeData = Transformations.switchMap(resentVerifyCodeObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.resentCodeForUser(obj.userEmail);

        });

        userFollowPostData = Transformations.switchMap(userFollowPostObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.uploadUserFollowPostToServer(obj.userId, obj.followUserId);

        });

        userSearchListByKeyData = Transformations.switchMap(userSearchListByKeyObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getUserListByKey(obj.loginUserId, obj.limit, obj.offset, obj.userParameterHolder);

        });

        nextPageUserListByKeyData = Transformations.switchMap(nextPageUserListByKeyObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageUserListByKey(obj.userParameterHolder, obj.loginUserId, obj.limit, obj.offset);

        });
    }

    //endregion


    //region Methods

    //delete user

    public void setDeleteUserObj(String userId) {

        DeleteUserTmpDataHolder deleteUserTmpDataHolder = new DeleteUserTmpDataHolder(userId);

        this.deleteUserObj.setValue(deleteUserTmpDataHolder);

    }

    public LiveData<Resource<Boolean>> getDeleteUserStatus() {
        return deleteUserData;
    }

    //endregion

    //user search by key

    public void setUserListByKeyObj(String loginUserId, String limit, String offset, UserParameterHolder parameterHolder) {
        if (!isLoading) {
            UserSearchTmpDataHolder tmpDataHolder = new UserSearchTmpDataHolder(limit, offset, loginUserId, parameterHolder);

            this.userSearchListByKeyObj.setValue(tmpDataHolder);
            setLoadingState(true);

        }
    }

    public LiveData<Resource<List<User>>> getUserListByKeyData() {
        return userSearchListByKeyData;
    }

    public void setNextPageUserListByKeyObj(String loginUserId, String limit, String offset, UserParameterHolder parameterHolder) {

        if (!isLoading) {
            UserSearchTmpDataHolder tmpDataHolder = new UserSearchTmpDataHolder(limit, offset, loginUserId, parameterHolder);

            setLoadingState(true);

            this.nextPageUserListByKeyObj.setValue(tmpDataHolder);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageUserListByKeyData() {
        return nextPageUserListByKeyData;
    }

    //endregion

    // For loading status
    public void setLoadingState(Boolean state) {
        isLoading = state;
        loadingState.setValue(state);
    }

    public MutableLiveData<Boolean> getLoadingState() {
        return loadingState;
    }

    // For Login User
    public void setUserLogin(User obj) {
        setLoadingState(true);
        this.doUserLoginObj.setValue(obj);
    }

    public LiveData<Resource<UserLogin>> getUserLoginStatus() {
        return doUserLoginData;
    }


    // For Getting Login User Data
    public LiveData<List<UserLogin>> getLoginUser() {
        userLoginObj.setValue("load");

        return userLoginData;
    }

    // For Login User Data

    public void setUserObj(String userId) {

        DeleteUserTmpDataHolder deleteUserTmpDataHolder = new DeleteUserTmpDataHolder(userId);

        this.userObj.setValue(deleteUserTmpDataHolder);

    }

    public LiveData<Resource<User>> getUserData() {
        return userData;
    }

//    public LiveData<Resource<User>> getUser(String userId) {
//        userObj.setValue(userId);
//
//        return userData;
//    }

    // For other  User Data
    public LiveData<Resource<User>> getOtherUser(String userId, String otherUserId) {
        OtherUserTmpDataHolder holder = new OtherUserTmpDataHolder(userId, otherUserId);

        otherUserObj.setValue(holder);

        return otherUserData;
    }

    // For Delete Login User
    public LiveData<Resource<Boolean>> deleteUserLogin(User user) {

        if (user == null) {
            return AbsentLiveData.create();
        }

        return this.repository.delete(user);
    }


    // Upload Image
    public LiveData<Resource<User>> uploadImage(String filePath, String userId) {

        imgObj.setValue("PS");

        return Transformations.switchMap(imgObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return this.repository.uploadImage(filePath, userId, Constants.PLATFORM);
        });

    }


    // Update User

    public void setUpdateUserObj(User user) {
        updateUserObj.setValue(user);
    }

    public LiveData<Resource<ApiStatus>> getUpdateUserData() {

        return updateUserData;
    }


    // Register User
//    public LiveData<Resource<UserLogin>> registerUser(User user) {
//        registerUserObj.setValue(user);
//        return registerUserData;
//    }
    public void setRegisterUser(User user) {
        UserTmpDataHolder tmpDataHolder = new UserTmpDataHolder(user);

        this.registerUserObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<User>> getRegisterUser() {
        return registerUserData;
    }

    // Register User
    public void registerFBUser(String fbId, String name, String email, String imageUrl, String deviceToken) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.fbId = fbId;
        tmpDataHolder.name = name;
        tmpDataHolder.email = email;
        tmpDataHolder.imageUrl = imageUrl;
        tmpDataHolder.deviceToken = deviceToken;
        registerFBUserObj.setValue(tmpDataHolder);

    }

    public LiveData<Resource<UserLogin>> getRegisterFBUserData() {
        return registerFBUserData;
    }

    // phone login User
    public void setPhoneLoginUser(String phoneId, String name, String phone, String deviceToken) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.phoneId = phoneId;
        tmpDataHolder.name = name;
        tmpDataHolder.phone = phone;
        tmpDataHolder.deviceToken = deviceToken;
        phoneLoginObj.setValue(tmpDataHolder);

    }

    public LiveData<Resource<UserLogin>> getPhoneLoginData() {
        return phoneLoginData;
    }

    // phone login User
    public void setGoogleLoginUser(String googleId, String name, String email,String imageUrl, String deviceToken) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.googleId = googleId;
        tmpDataHolder.name = name;
        tmpDataHolder.email = email;
        tmpDataHolder.imageUrl = imageUrl;
        tmpDataHolder.deviceToken = deviceToken;
        googleLoginObj.setValue(tmpDataHolder);

    }

    public LiveData<Resource<UserLogin>> getGoogleLoginData() {
        return googleLoginData;
    }

    // Forgot password
    public LiveData<Resource<ApiStatus>> forgotPassword(String email) {
        forgotPasswordObj.setValue(email);
        return forgotpasswordData;
    }

    // Forgot password
    public LiveData<Resource<ApiStatus>> passwordUpdate(String loginUserId, String password) {

        TmpDataHolder holder = new TmpDataHolder();
        holder.loginUserId = loginUserId;
        holder.password = password;

        passwordUpdateObj.setValue(holder);
        return passwordUpdateData;
    }

    //endregion

    public void setFetchUserByIdObj(String id) {
        this.fetchUserByIdObj.setValue(id);
    }

    public LiveData<Resource<User>> getFetchUserByIdData() {
        return fetchUserByIdData;
    }


    public void setEmailVerificationUser(String loginUserId, String code) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.loginUserId = loginUserId;
        tmpDataHolder.code = code;
        this.verificationEmailObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<UserLogin>> getEmailVerificationUser() {
        return verificationEmailData;
    }


    public void setResentVerifyCodeObj(String userEmail) {
        resentCodeTmpDataHolder tmpDataHolder = new resentCodeTmpDataHolder(userEmail);

        this.resentVerifyCodeObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getResentVerifyCodeData() {
        return resentVerifyCodeData;
    }

    //user follow post

    public void setUserFollowPostObj(String userId, String followUserId) {
        if (!isLoading) {
            UserFollowPostTmpDataHolder tmpDataHolder = new UserFollowPostTmpDataHolder(userId, followUserId);
            this.userFollowPostObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getUserFollowPostData() {
        return userFollowPostData;
    }

    //region Tmp Holder

    class TmpDataHolder {

        public String loginUserId = "";
        public String password = "";
        public String fbId = "";
        public String googleId = "";
        public String name = "";
        public String email = "";
        public String imageUrl = "";
        public String code = "";
        public String deviceToken = "";
        public String phoneId = "";
        public String phone = "";


    }

    class UserTmpDataHolder {
        User user;

        UserTmpDataHolder(User user) {
            this.user = user;
        }
    }

    class DeleteUserTmpDataHolder {

        public String userId;

        private DeleteUserTmpDataHolder(String userId) {
            this.userId = userId;

        }
    }

    private class resentCodeTmpDataHolder {

        public String userEmail;

        private resentCodeTmpDataHolder(String userEmail) {
            this.userEmail = userEmail;
        }
    }

    private class UserFollowPostTmpDataHolder {

        private String userId, followUserId;

        private UserFollowPostTmpDataHolder(String userId, String followUserId) {
            this.userId = userId;
            this.followUserId = followUserId;
        }
    }

    private class OtherUserTmpDataHolder {

        private String userId, otherUserId;

        private OtherUserTmpDataHolder(String userId, String otherUserId) {
            this.userId = userId;
            this.otherUserId = otherUserId;
        }
    }

    private class UserSearchTmpDataHolder {

        private String limit, offset, loginUserId;
        private UserParameterHolder userParameterHolder;

        private UserSearchTmpDataHolder(String limit, String offset, String loginUserId, UserParameterHolder userParameterHolder) {
            this.limit = limit;
            this.offset = offset;
            this.loginUserId = loginUserId;
            this.userParameterHolder = userParameterHolder;
        }
    }

    //endregion

}
