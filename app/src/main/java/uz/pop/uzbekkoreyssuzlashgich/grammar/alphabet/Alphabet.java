package uz.pop.uzbekkoreyssuzlashgich.grammar.alphabet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import uz.pop.uzbekkoreyssuzlashgich.MainActivity;
import uz.pop.uzbekkoreyssuzlashgich.R;

public class Alphabet extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet);

        webView = findViewById(R.id.alphabet_web_wiew);
        webView.loadUrl("file:///android_asset/alflotkor.html");
        webView.setBackgroundColor(getResources().getColor(R.color.listrang));
        //zoom funksiyasini ishlatish
        webView.getSettings().setBuiltInZoomControls(true);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // ekranni full skreen qilib beradigan funksiyalar

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            if (hasFocus){
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
            }
        }

    }
   // orqaga qaytarish
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Alphabet.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
