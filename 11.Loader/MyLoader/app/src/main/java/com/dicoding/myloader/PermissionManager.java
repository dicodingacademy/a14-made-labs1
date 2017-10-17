package com.dicoding.myloader;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by dicoding on 10/13/2017.
 */


/**
 * Kelas ini digunakan untuk grant permission secara run-time
 * grant permission secara run-time diharuskan untuk permission yang termasuk dalam kategori dangerous
 */
public class PermissionManager {
    public static void check(Activity activity, String permission, int requestCode){
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,new String[]{permission},requestCode);
        }
    }

    public static boolean isGranted(Activity activity, String permission, int requestCode){
        return (ActivityCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_GRANTED);
    }
}
