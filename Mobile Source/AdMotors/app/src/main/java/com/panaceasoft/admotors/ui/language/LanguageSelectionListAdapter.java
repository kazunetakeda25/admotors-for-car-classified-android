package com.panaceasoft.admotors.ui.language;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.utils.LanguageData;

import java.util.List;

public class LanguageSelectionListAdapter extends ArrayAdapter<LanguageData> {

    private List<LanguageData> dataSet;
    private int selectedPosition;


    private static class ViewHolder {
        TextView languageNameTextView;
        ImageView languageSelectedImageView;
    }

    LanguageSelectionListAdapter(List<LanguageData> data, Context context, int selectedPosition) {
        super(context, R.layout.item_language_selection_list_adapter, data);
        this.selectedPosition = selectedPosition;
        this.dataSet = data;
    }

    @Override
    public int getCount() {
        return dataSet != null ? dataSet.size() : 0;

    }

    @Override
    public LanguageData getItem(int item) {
        LanguageData gi;

        gi = dataSet != null ? dataSet.get(item) : null;

        return gi;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        LanguageData dataModel = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_language_selection_list_adapter, parent, false);
            viewHolder.languageNameTextView = convertView.findViewById(R.id.languageNameTextView);
            viewHolder.languageSelectedImageView = convertView.findViewById(R.id.languageSelectedImageView);
            if (dataModel != null) {
                viewHolder.languageNameTextView.setText(dataModel.languageName);
            }
            if (selectedPosition == position) {
                viewHolder.languageSelectedImageView.setVisibility(View.VISIBLE);
            } else {
                viewHolder.languageSelectedImageView.setVisibility(View.GONE);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.languageNameTextView = convertView.findViewById(R.id.languageNameTextView);
            viewHolder.languageSelectedImageView = convertView.findViewById(R.id.languageSelectedImageView);
            if (dataModel != null) {
                viewHolder.languageNameTextView.setText(dataModel.languageName);
            }
            if (selectedPosition == position) {
                viewHolder.languageSelectedImageView.setVisibility(View.VISIBLE);
            } else {
                viewHolder.languageSelectedImageView.setVisibility(View.GONE);
            }
        }

        // Return the completed view to render on screen
        return convertView;
    }
}