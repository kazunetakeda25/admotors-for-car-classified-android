package com.panaceasoft.admotors.ui.manufacturer.manufacturerfilter;

import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Grouping {

    public String manufacturerId, modelId;
    public LinkedHashMap<Manufacturer, List<Model>> map = new LinkedHashMap<>();

    public LinkedHashMap<Manufacturer, List<Model>> group(List<Manufacturer> manufacturerList, List<Model> modelList) {
        map.clear();

        for (int i = 0; i < manufacturerList.size(); i++) {

            List<Model> subCategories = new ArrayList<>();

            Model subCategory = new Model(Constants.ZERO, "",Constants.MANUFACTURER_ALL, "", "", "", "", "", "",  null, null);
            subCategories.add(0, subCategory);

            manufacturerId = manufacturerList.get(i).id;

            for (int j = 0; j < modelList.size(); j++) {
                modelId = modelList.get(j).manufacturerId;

                if (manufacturerId.equals(modelId)) {
                    subCategories.add(modelList.get(j));
                }
            }

            if (!subCategories.isEmpty()) {

                map.put(manufacturerList.get(i), subCategories);
            }
        }

        return map;
    }
}
