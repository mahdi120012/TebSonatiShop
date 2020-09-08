package com.tebsonatishop.customClasses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tebsonatishop.activities.AfterPayment;
import com.tebsonatishop.activities.MainActivity;

public class WebClient extends WebViewClient
{
    Context c;
    public WebClient(Context c){
       this.c =c;
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

        Log.e("Page Started", ""+url);

        super.onPageStarted(view, url, favicon);



        if(url.contains("tavasi_tebsonati_success_payment")) {

            Log.e("Getting Success Request", "Test");

            Intent i = new Intent(c, AfterPayment.class);

            //PAYPAL_WEB_BACK = "fulfilled";
            c.startActivity(i);
            //c.finish();

        } /*else if(url.equalsIgnoreCase(failureUrl)) {

            Intent i = new Intent(PaypalWebActivity.this, TabhostActivity.class);

            PAYPAL_WEB_BACK = "fulfilled";
            startActivity(i);
            finish();

        }*/
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        Log.e("Override Url", ""+url);

        view.loadUrl(url);
        return true;

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);

        Log.e("Finished Url :", "" + url);

       /* if(dialog.isShowing()){
            dialog.dismiss();
        }*/
    }
}