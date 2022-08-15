package se.arctosoft.vault.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;
import java.util.List;

import se.arctosoft.vault.R;

public class Dialogs {
    private static final String TAG = "Dialogs";

    public static void showImportGalleryChooseDestinationDialog(Context context, Settings settings, IOnDirectorySelected onDirectorySelected) {
        List<Uri> directories = settings.getGalleryDirectoriesAsUri();
        String[] names = new String[directories.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = FileStuff.getFilenameWithPathFromUri(directories.get(i));
        }
        Log.e(TAG, "showImportGalleryChooseDestinationDialog: " + Arrays.toString(names));
        new MaterialAlertDialogBuilder(context)
                .setTitle(context.getString(R.string.dialog_import_to_title))
                .setItems(names, (dialog, which) -> onDirectorySelected.onDirectorySelected(DocumentFile.fromTreeUri(context, directories.get(which))))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    public interface IOnDirectorySelected {
        void onDirectorySelected(@NonNull DocumentFile directory);
    }

}