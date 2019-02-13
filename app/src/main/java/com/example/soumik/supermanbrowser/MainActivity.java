package com.example.soumik.supermanbrowser;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go,forward,back,reload,clear;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar= (ProgressBar)findViewById(R.id.progressbar);
        brow= (WebView)findViewById(R.id.wv_brow);
        urledit= (EditText)findViewById(R.id.et_url);
        go= (Button)findViewById(R.id.btn_go);
        forward= (Button)findViewById(R.id.btn_fwd);
        back= (Button)findViewById(R.id.btn_bck);
        reload= (Button)findViewById(R.id.btn_rld);
        clear= (Button)findViewById(R.id.btn_clr);

        //when we click on something in our browser this enables to pen in this browser instead of opening another browser
        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    progressBar.setVisibility(view.GONE);
                } else {
                    progressBar.setVisibility(view.VISIBLE);
                }
            }
        });

        WebSettings websettings= brow.getSettings();
        websettings.setJavaScriptEnabled(true);

        brow.loadUrl("https://www.thesouledstore.com");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String edittextvalue = urledit.getText().toString();

            if(!edittextvalue.startsWith("http://"))
                edittextvalue= "http://" + edittextvalue;

            String url= edittextvalue;
            brow.loadUrl(url);

            //hiding keyboard after url
                InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brow.canGoForward())
                brow.goForward();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brow.canGoBack())
                    brow.goBack();

            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            brow.reload();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            brow.clearHistory();
            }
        });

    }
}

