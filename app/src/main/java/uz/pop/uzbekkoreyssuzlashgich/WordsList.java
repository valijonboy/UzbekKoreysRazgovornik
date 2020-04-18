package uz.pop.uzbekkoreyssuzlashgich;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.widget.ListView;

import dbhelper.DBHelper;
import uz.pop.uzbekkoreyssuzlashgich.adapter.WordsAdapter;

public class WordsList extends AppCompatActivity {

    ListView listViewWords;
   int categoryId;
   WordsAdapter wordsAdapter;
   Activity context;
   DBHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        context = this;
        dbHelper = new DBHelper(this);
        listViewWords = findViewById(R.id.listview_words);

        categoryId = getIntent().getExtras().getInt("categoryId");
        wordsAdapter = new WordsAdapter(context, dbHelper.allWords(categoryId));
        listViewWords.setAdapter(wordsAdapter);
        initAnimation();



    }
//Animatsiya uchun
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initAnimation(){
        Explode explode = new Explode();
        explode.setDuration(750);
        getWindow().setEnterTransition(explode);
    }
}
