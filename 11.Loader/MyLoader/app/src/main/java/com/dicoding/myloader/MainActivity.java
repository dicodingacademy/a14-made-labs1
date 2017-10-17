package com.dicoding.myloader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener{

    public static final String TAG = "ContactApp";

    ListView lvContact;
    ProgressBar progressBar;

    private ContactAdapter mAdapter;

    private final int CONTACT_LOAD_ID = 110;
    private final int CONTACT_PHONE_ID = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = (ListView)findViewById(R.id.lv_contact);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        lvContact.setVisibility(View.INVISIBLE);

        mAdapter = new ContactAdapter(MainActivity.this, null, true);
        lvContact.setAdapter(mAdapter);
        lvContact.setOnItemClickListener(this);

        if(PermissionManager.isGranted(this, Manifest.permission.READ_CONTACTS,CONTACT_REQUEST_CODE))
        {
            getSupportLoaderManager().initLoader(CONTACT_LOAD_ID, null, this);
            progressBar.setVisibility(View.VISIBLE);

        }else {
            PermissionManager.check(this, Manifest.permission.READ_CONTACTS,CONTACT_REQUEST_CODE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader mCursorLoader = null;
        if (id == CONTACT_LOAD_ID){
            String[] projectionFields = new String[] {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.PHOTO_URI};
            mCursorLoader = new CursorLoader(MainActivity.this,
                    ContactsContract.Contacts.CONTENT_URI,
                    projectionFields,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
                    null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        }

        if (id == CONTACT_PHONE_ID){
            String[] phoneProjectionFields = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            mCursorLoader = new CursorLoader(MainActivity.this,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    phoneProjectionFields,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                            ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE + " AND " +
                            ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + "=1",
                    new String[]{args.getString("id")},
                    null);
        }

        return mCursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "LoadFinished");

        if (loader.getId() == CONTACT_LOAD_ID){
            if (data.getCount() > 0){
                lvContact.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                mAdapter.swapCursor(data);
            }
        }

        if (loader.getId() == CONTACT_PHONE_ID){
            String contactNumber = null;
            if (data.moveToFirst()) {
                contactNumber = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:"+contactNumber));
            startActivity(dialIntent);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (loader.getId() == CONTACT_LOAD_ID){
            lvContact.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            mAdapter.swapCursor(null);
            Log.d(TAG, "LoaderReset");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(CONTACT_LOAD_ID);
        getSupportLoaderManager().destroyLoader(CONTACT_PHONE_ID);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = mAdapter.getCursor();
        // Posisi dari cursor pindah ke position
        cursor.moveToPosition(position);
        // Ambil data dari index 0 yaitu contactId
        long mContactId = cursor.getLong(0);

        Log.d(TAG, "Position : "+position+" "+mContactId);

        getPhoneNumber(String.valueOf(mContactId));
    }

    private void getPhoneNumber(String contactID){
        Bundle bundle = new Bundle();
        bundle.putString("id", contactID);

        getSupportLoaderManager().restartLoader(CONTACT_PHONE_ID, bundle, this);
    }

    final int CONTACT_REQUEST_CODE = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CONTACT_REQUEST_CODE){
            if(grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getSupportLoaderManager().initLoader(CONTACT_LOAD_ID, null, this);
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Grant permission contact berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Contact permission di tolak", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
