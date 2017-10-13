package com.dicoding.myloader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sidiqpermana on 10/13/16.
 */

public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_contact, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        if (cursor != null){
            TextView tvName = (TextView)view.findViewById(R.id.tv_item_name);
            CircleImageView imgUser = (CircleImageView) view.findViewById(R.id.img_item_user);
            RelativeLayout rlItem = (RelativeLayout)view.findViewById(R.id.rl_item);

            imgUser.setImageResource(R.drawable.ic_person_black_48dp);
            tvName.setText(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)));
            if (cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI)) != null){
                imgUser.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI))));
            }else{
                imgUser.setImageResource(R.drawable.ic_person_black_48dp);
            }
        }
    }
}
