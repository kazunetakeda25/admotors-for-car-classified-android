package com.panaceasoft.admotors.utils;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;

/**
 * Created by Panacea-Soft on 4/2/19.
 * Contact Email : teamps.is.cool@gmail.com
 */


@GlideModule
public class CustomCachingGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * Config.IMAGE_CACHE_LIMIT; // 250mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes));
        builder.setDefaultRequestOptions(requestOptions(context));

        //builder
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
//        Currently Not Used
//------------------------------------
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(20, TimeUnit.SECONDS)
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .build();
//
//        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
//
//        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);
    }

    private static RequestOptions requestOptions(Context context) {
        return new RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .format(PREFER_ARGB_8888)
                .dontTransform();

//        Currently Not Used
//------------------------------------
//        .skipMemoryCache(false)
//.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//        .signature(new ObjectKey(
//                System.currentTimeMillis() / (24 * 60 * 60 * 1000)))

//        return new RequestOptions()
//                .signature(new ObjectKey(
//                        System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
//                .override(200, 200)
//                .centerCrop()
//                .placeholder(R.drawable.placeholder_image)
//                .encodeFormat(Bitmap.CompressFormat.PNG)
//                .encodeQuality(100)
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .format(PREFER_ARGB_8888)
//                .skipMemoryCache(false);
    }
}
