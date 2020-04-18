package uz.pop.uzbekkoreyssuzlashgich.grammar.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import uz.pop.uzbekkoreyssuzlashgich.R;
import uz.pop.uzbekkoreyssuzlashgich.adapter.TestAnswerAdapter;

public class TestAnswerList extends AppCompatActivity {

    ListView listView;
    TextView tvCorrect, tvInCorrect;
    List<String> krWordsList, uzWordsList;
    List<Integer> imageList;

    int correct, inCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_answer_list);

        listView = findViewById(R.id.test_results_listview);
        tvCorrect = findViewById(R.id.test_results_correct);
        tvInCorrect = findViewById(R.id.test_results_incorrect);

        correct = getIntent().getExtras().getInt("correct");
        inCorrect = getIntent().getExtras().getInt("incorrect");

        krWordsList = getIntent().getStringArrayListExtra("kr");
        uzWordsList = getIntent().getStringArrayListExtra("uz");
        imageList = getIntent().getExtras().getIntegerArrayList("img");

        tvCorrect.setText(getString(R.string.tugri_javoblar) + correct);
        tvInCorrect.setText(getString(R.string.notugri_javob));

        TestAnswerAdapter adapter = new TestAnswerAdapter(this, krWordsList, uzWordsList, imageList);
        listView.setAdapter(adapter);
    }
}
