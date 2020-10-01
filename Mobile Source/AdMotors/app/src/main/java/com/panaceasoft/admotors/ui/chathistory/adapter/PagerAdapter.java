package com.panaceasoft.admotors.ui.chathistory.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.panaceasoft.admotors.ui.chathistory.BuyerFragment;
import com.panaceasoft.admotors.ui.chathistory.SellerFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    public SellerFragment sellerFragment;
    public BuyerFragment buyerFragment;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                sellerFragment = new SellerFragment();
                return sellerFragment;
            case 1:
                buyerFragment = new BuyerFragment();
                return buyerFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
