package pt.ipbeja.pdm1.bejaroteirospdm2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Mapalocais extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapalocais);


        Intent getintent = getIntent();
        String link = getintent.getStringExtra("linkWeb");
        WebView myWebView = (WebView) findViewById(R.id.WebViewLocais);
        myWebView.loadUrl(link);}
    }

