package com.panaceasoft.admotors.viewobject.holder;

import com.panaceasoft.admotors.utils.Constants;

/**
 * Created by Panacea-Soft on 2019-06-25.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class ChatHistoryParameterHolder {

    public String returnType;

    public ChatHistoryParameterHolder() {

        this.returnType = "";
    }

    public ChatHistoryParameterHolder getSellerHistoryList() {
        this.returnType = Constants.CHAT_TYPE_SELLER;

        return this;
    }

    public ChatHistoryParameterHolder getBuyerHistoryList() {
        this.returnType = Constants.CHAT_TYPE_BUYER;

        return this;
    }

    public String getMapKey() {

        String result = "";

        if (!returnType.isEmpty()) {
            result += returnType;
        }

        return result;

    }

}
