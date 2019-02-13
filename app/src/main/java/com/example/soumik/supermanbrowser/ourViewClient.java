package com.example.soumik.supermanbrowser;

import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class ourViewClient extends WebViewClient {
    //deprecated means that "hey we have something else to offer, you might wana give it a shot
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}

