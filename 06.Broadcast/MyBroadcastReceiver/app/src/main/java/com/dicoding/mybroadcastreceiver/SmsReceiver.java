package com.dicoding.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    final String TAG = SmsReceiver.class.getSimpleName();
    final SmsManager sms = SmsManager.getDefault();

    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                if (pdusObj != null) {
                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = getIncomingMessage(pdusObj[i], bundle);
                        String senderNum = currentMessage.getDisplayOriginatingAddress();

                        String message = currentMessage.getDisplayMessageBody();

                        Log.i(TAG, "senderNum: " + senderNum + "; message: " + message);

                        Intent showSmsIntent = new Intent(context, SmsReceiverActivity.class);
                        showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum);
                        showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message);
                        context.startActivity(showSmsIntent);
                    }
                }else {
                    Log.e(TAG, "onReceive: SMS is null" );
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception smsReceiver" +e);

        }
    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= 23) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
}
