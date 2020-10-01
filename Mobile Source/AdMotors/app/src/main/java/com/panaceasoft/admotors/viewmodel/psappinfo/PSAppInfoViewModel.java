package com.panaceasoft.admotors.viewmodel.psappinfo;

//public class PSAppInfoViewModel extends PSViewModel {
//
//    private final LiveData<Resource<PSAppInfo>> deleteHistoryData;
//    private MutableLiveData<TmpDataHolder> deleteHistoryObj = new MutableLiveData<>();
//
//    public String appSettingLat;
//    public String appSettingLng;
//
//    @Inject
//    PSAppInfoViewModel(AppInfoRepository repository) {
//        deleteHistoryData = Transformations.switchMap(deleteHistoryObj, obj -> {
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//            Utils.psLog("PSAppInfoViewModel");
//            return repository.deleteTheSpecificObjects(obj.startDate, obj.endDate);
//        });
//    }
//
//    public void setDeleteHistoryObj(String startDate, String endDate) {
//
//        TmpDataHolder tmpDataHolder = new TmpDataHolder(startDate, endDate);
//
//        this.deleteHistoryObj.setValue(tmpDataHolder);
//    }
//
//    public LiveData<Resource<PSAppInfo>> getDeleteHistoryData() {
//        return deleteHistoryData;
//    }
//
//    class TmpDataHolder {
//        String startDate, endDate;
//
//        private TmpDataHolder(String startDate, String endDate) {
//            this.startDate = startDate;
//            this.endDate = endDate;
//        }
//    }
//
//}
