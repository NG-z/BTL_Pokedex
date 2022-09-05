package com.example.btlpokedex.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

public class CacheSizeTask extends AsyncTask<File, Long, Long> {
    private Context context;
    private AlertDialog dialog;

    public CacheSizeTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Cache");
        builder.setMessage("Calculating...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide glide = Glide.get(context);
                        glide.clearDiskCache();
                    }
                }).start();
                Toast.makeText(context, "Clearing cache...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    protected Long doInBackground(File... files) {
        long totalSize = 0;
        try {
            for (File file : files) totalSize += calculateSize(file);
        } catch (Exception ex) {
        }
        return totalSize;
    }

    @Override
    protected void onPostExecute(Long size) {
        String sizeText = android.text.format.Formatter.formatFileSize(context, size);
        dialog.setMessage("Found " + sizeText + " cache. Do you want to clean up?");
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
    }

    private long calculateSize(File file) {
        long result = 0;
        if (file == null) return 0;
        if (!file.isDirectory()) return file.length();
        File[] listFile = file.listFiles();
        if (listFile == null) return 0;
        for (File f : listFile) result += calculateSize(f);
        return result;
    }
}