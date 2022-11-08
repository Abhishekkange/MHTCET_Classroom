package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PdfReader extends AppCompatActivity {

   // WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_reader);


       /* String url = getIntent().getStringExtra("url");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ url);

        */
    }
}