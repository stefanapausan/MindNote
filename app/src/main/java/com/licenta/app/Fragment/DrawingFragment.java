package com.licenta.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;
import com.licenta.app.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

public class DrawingFragment extends Fragment {

    private int defColor;
    private SignatureView signatureView;
    private TextView tvPenSize;

    private static String filename;
    private final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/My Drawings");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawing, container, false);


        signatureView = view.findViewById(R.id.signature_view);
        SeekBar seekBar = view.findViewById(R.id.sb_pen_size);
        tvPenSize = view.findViewById(R.id.tv_pen_size);
        ImageButton btnEraser = view.findViewById(R.id.btn_eraser);
        ImageButton btnColor = view.findViewById(R.id.btn_color);
        ImageButton btnMore = view.findViewById(R.id.btn_more);

        defColor = ContextCompat.getColor(requireActivity(), R.color.black);

        askPermission();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String date = format.format(new Date());
        filename = path + "/" + date + ".png";
        if (!path.exists()) //noinspection ResultOfMethodCallIgnored
            path.mkdir();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvPenSize.setText(i+"px");
                signatureView.setPenSize(i);
                seekBar.setMax(50);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        btnEraser.setOnClickListener(view1 -> signatureView.clearCanvas());
        btnColor.setOnClickListener(view12 -> openColorPicker());

        View v = view.findViewById(R.id.anchor_menu);
        btnMore.setOnClickListener(view13 -> popupMenu(v));

        return view;
    }

    private void popupMenu(View view) {
        PopupMenu popup = new PopupMenu(requireActivity(), view, Gravity.CENTER);
        popup.getMenuInflater().inflate(R.menu.menu_drawing, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId()==R.id.action_save) {
                checkStorage();
                try {
                    saveImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (item.getItemId()==R.id.action_folder) {
                Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory() + "/My Paints/");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(selectedUri, "image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            return true;
        });

        popup.show();
    }

    private void saveImage() throws IOException {
        File file = new File(filename);
        Bitmap bitmap = signatureView.getSignatureBitmap();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapData = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapData);
        fos.flush();
        fos.close();

        Toast.makeText(requireActivity(), "Saved in gallery!", Toast.LENGTH_SHORT).show();
    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(requireActivity(), defColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) { }
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defColor = color;
                signatureView.setPenColor(color);
            }
        });
        ambilWarnaDialog.show();

    }

    private void askPermission() {
        Dexter.withContext(getActivity())
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        checkStorage();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    private void checkStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()){
                new MaterialAlertDialogBuilder(requireActivity())
                        .setMessage("Above Android 11 need to allow access to manage files!")
                        .setPositiveButton("ALLOW", (dialogInterface, i) -> {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        })
                        .setNegativeButton("CANCEL", null)
                        .show();

            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}