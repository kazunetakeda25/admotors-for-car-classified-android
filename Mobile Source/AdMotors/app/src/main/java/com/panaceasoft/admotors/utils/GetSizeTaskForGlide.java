package com.panaceasoft.admotors.utils;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;

public class GetSizeTaskForGlide extends AsyncTask<File, Long, Long> {
    public final TextView resultView;
    public GetSizeTaskForGlide(TextView resultView) { this.resultView = resultView; }
    @Override protected void onPreExecute() { resultView.setText("Calculating..."); }
    @Override protected void onProgressUpdate(Long... values) { /* onPostExecute(values[values.length - 1]); */ }
    @Override protected Long doInBackground(File... dirs) {
        try {
            long totalSize = 0;
            for (File dir : dirs) {
                publishProgress(totalSize);
                totalSize += calculateSize(dir);
            }
            return totalSize;
        } catch (RuntimeException ex) {
            final String message = String.format("Can't get size of %s: %s", Arrays.toString(dirs), ex);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override public void run() {
                    resultView.setText("error");
                    Toast.makeText(resultView.getContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }
        return 0L;
    }

    @Override protected void onPostExecute(Long size) {
        String sizeText = android.text.format.Formatter.formatFileSize(resultView.getContext(), size);
        resultView.setText(sizeText);
    }
    private static long calculateSize(File dir) {
        if (dir == null) return 0;
        if (!dir.isDirectory()) return dir.length();
        long result = 0;
        File[] children = dir.listFiles();
        if (children != null)
            for (File child : children)
                result += calculateSize(child);
        return result;
    }
}
