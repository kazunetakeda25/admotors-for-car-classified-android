package com.panaceasoft.admotors.ui.common;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.viewobject.common.Resource;

/**
 * Created by Panacea-Soft on 12/5/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class BackgroundTaskHandler implements Observer<Resource<Boolean>> {

    @Nullable
    protected LiveData<Resource<Boolean>> holdLiveData;
    protected final MutableLiveData<LoadingState> loadingState = new MutableLiveData<>();
    protected String limit;
    protected String offset;
    protected PSRepository repository;
    private boolean hasMore;

    protected BackgroundTaskHandler(PSRepository repository) {
        this.repository = repository;
        reset();
    }

    public BackgroundTaskHandler() {
        reset();
    }

    public void save(Object obj) {
        if(obj == null) {
            return;
        }

        unregister();

        holdLiveData = repository.save(obj);
        loadingState.setValue(new LoadingState(true, null));
        //noinspection ConstantConditions
        holdLiveData.observeForever(this);
    }

    public void delete(Object obj) {
        if(obj == null) {
            return;
        }

        unregister();

        holdLiveData = repository.delete(obj);
        loadingState.setValue(new LoadingState(true, null));
        //noinspection ConstantConditions
        holdLiveData.observeForever(this);
    }

    @Override
    public void onChanged(@Nullable Resource<Boolean> result) {
        if (result == null) {
            reset();
        } else {
            switch (result.status) {
                case SUCCESS:
                    hasMore = Boolean.TRUE.equals(result.data);
                    unregister();
                    loadingState.setValue(new LoadingState(false, null));
                    break;
                case ERROR:
                    hasMore = true;
                    unregister();
                    loadingState.setValue(new LoadingState(false,
                            result.message));
                    break;
            }
        }
    }

    public void unregister() {
        if (holdLiveData != null) {
            holdLiveData.removeObserver(this);
            holdLiveData = null;
            if (hasMore) {
                limit = null;
                offset = null;
            }
        }
    }

    public void reset() {
        unregister();
        hasMore = true;
        loadingState.setValue(new LoadingState(false, null));
    }

    public MutableLiveData<LoadingState> getLoadingState() {
        return loadingState;
    }

    public static class LoadingState {
        private final boolean running;
        private final String errorMessage;
        private boolean handledError = false;

        public LoadingState(boolean running, String errorMessage) {
            this.running = running;
            this.errorMessage = errorMessage;
        }


        public boolean isRunning() {
            return running;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String getErrorMessageIfNotHandled() {
            if (handledError) {
                return null;
            }
            handledError = true;
            return errorMessage;
        }
    }
}