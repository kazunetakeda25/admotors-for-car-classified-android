package com.panaceasoft.admotors.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.viewobject.ItemCategory;

import java.util.List;

/**
 * Created by Panacea-Soft on 7/25/15.
 * Contact Email : teamps.is.cool@gmail.com
 */
public class PSPopupSingleSelectionView extends LinearLayout {

    public SelectListener onSelectListener;
    private TextView mTextView;
    private int selectedIndex = 0;
    private CharSequence[] items;
    private String title = "";
    private List<ItemCategory> categories;

    public PSPopupSingleSelectionView(Context context) {
        super(context);
        Utils.psLog("1***");
        initUI(context);
    }

    public PSPopupSingleSelectionView(Context context, String title, List<ItemCategory> categories) {
        super(context, null);
        Utils.psLog("2***");
        this.title = title;
        setDataArrayList(categories);
        initUI(context);

    }

    public PSPopupSingleSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Utils.psLog("3***");
        initUI(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UIPopup,
                0, 0);

        try {
            items = a.getTextArray(R.styleable.UIPopup_items);
            title = a.getString(R.styleable.UIPopup_pTitle);

        } finally {
            a.recycle();
        }
    }

    /**
     * Inflate the UI for the layout
     *
     * @param context for the view
     */
    private void initUI(Context context) {
        Utils.psLog("initUI" + context.toString());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.ui_based_pop_up_chooser_view, this);
        }
        onFinishInflateCustom();
    }

    protected void onFinishInflateCustom() {
        super.onFinishInflate();
        Utils.psLog("Inflate ***");
        RelativeLayout mLayout = findViewById(R.id.mLayout);
        ImageView imageView = findViewById(R.id.downImg);
        mTextView = findViewById(R.id.mText);

        if (!title.equals("")) {
            mTextView.setText(title);
        }

        imageView.setOnClickListener(view -> {
            onClicked();
        });

        mLayout.setOnClickListener(view -> {
            onClicked();
        });
    }

    private void onClicked() {
        try {
            if (categories != null && categories.size() > 0) {

                new AlertDialog.Builder(getContext())
                        .setTitle(title)
                        .setIcon(R.drawable.ic_category)
                        .setSingleChoiceItems(items, selectedIndex, null)
                        .setPositiveButton("OK", (DialogInterface dialog, int whichButton) -> {
                            dialog.dismiss();
                            int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                            String selectedText = (String) ((AlertDialog) dialog).getListView().getItemAtPosition(selectedPosition);
                            // Do something useful withe the position of the selected radio button

                            Utils.psLog("Selected : " + selectedPosition);
                            mTextView.setText(selectedText);
                            selectedIndex = selectedPosition;

                            onSelectListener.Select(selectedPosition, selectedText);

                            if (categories != null && categories.size() > 0) {
                                onSelectListener.Select(selectedPosition, selectedText, categories.get(selectedPosition).id);
                            }
                        })

                        .show();

            } else {
                Toast.makeText(getContext(), "There is no city to select.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Utils.psErrorLog("Error in Popup Dialog.", e);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOnSelectListener(SelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public void setDataArrayList(List<ItemCategory> objArrayList) {
        int i = 0;
        this.categories = objArrayList;
        try {
            this.items = new CharSequence[objArrayList.size()];
            Utils.psLog("Setting up Data List." + items.length);

            if (objArrayList.size() > 0) {
                for (ItemCategory obj : objArrayList) {
                    this.items[i++] = obj.name;
                }
            }
        } catch (Exception e) {
            Utils.psLog("Error");
        }

    }

}

/*
    public CharSequence[] getItems() {
        return items;
    }

    public void setItems(CharSequence[] items) {
        this.items = items;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public SelectListener getOnSelectListener() {
        return onSelectListener;
    }
*/
