package uz.pop.uzbekkoreyssuzlashgich.grammar.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dbhelper.DBHelper;
import uz.pop.uzbekkoreyssuzlashgich.R;
//import uz.pop.uzbekkoreyssuzlashgich;
import uz.pop.uzbekkoreyssuzlashgich.model.TestQuestionsModel;

public class TestQuestions extends AppCompatActivity {
    TextView tvNumber;
    ImageView imageView;
    RadioGroup radioGroup;
    RadioButton radio1, radio2, radio3, radio4, radio;
    Button btnNext;

    Context context;
    DBHelper dbHelper;
    List<TestQuestionsModel> testQuestionsList;
    List<String> krWordsList;
    List<String> uzWordsList;
    List<Integer> imageList;

    int questionId = -1;
    int questonNumber = 0;
    int correct = 0, incorrect = 0;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_questions);

        tvNumber = findViewById(R.id.test_questions_text_number);
        imageView = findViewById(R.id.test_questions_image);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        btnNext = findViewById(R.id.button_next);

        context = this;
        dbHelper = new DBHelper(context);
        testQuestionsList = new ArrayList<>();
        krWordsList = new ArrayList<>();
        uzWordsList = new ArrayList<>();
        imageList = new ArrayList<>();

        final int categoryId = getIntent().getExtras().getInt("categoryId");
        testQuestionsList = dbHelper.getTestQuestions(categoryId);

        if (getIntent() != null){
            questionId = 0;
            questonNumber = 1;
        }

        TestQuestionsModel model = testQuestionsList.get(questionId);
        getObjects(model);




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = findViewById(R.id.radio_group);
                radio = findViewById(radioGroup.getCheckedRadioButtonId());

                if (radio == null){
                    Toast.makeText(context, R.string.javobni_tanlang, Toast.LENGTH_SHORT).show();
                }
                else if (questonNumber <= testQuestionsList.size() && radio != null){

                    if(questonNumber == 1){
                        questionId++;
                        questonNumber++;
                    }

                    TestQuestionsModel model = testQuestionsList.get(questionId);
                    getObjects(model);
                    checkAnswers(model);

                    radioGroup.clearCheck();
                    questionId++;
                    questonNumber++;

                }
                else if (questonNumber > testQuestionsList.size()){
                    Intent intent = new Intent(context, TestAnswerList.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("incorrect", incorrect);
                    intent.putStringArrayListExtra("kr", (ArrayList<String>)krWordsList);
                    intent.putStringArrayListExtra("uz", (ArrayList<String>)uzWordsList);
                    intent.putIntegerArrayListExtra("img", (ArrayList<Integer>) imageList);
                    startActivity(intent);
                    finish();
                }
            }
        });




    }

    private void getObjects(TestQuestionsModel model){
        radio1.setText(model.getAnswerA());
        radio2.setText(model.getAnswerB());
        radio3.setText(model.getAnswerC());
        radio4.setText(model.getAnswerD());
        imageView.setImageResource(model.getImage());
        tvNumber.setText(testQuestionsList.size() +"/"+ questonNumber + getString(R.string.savol));

        krWordsList.add(model.getAnswer());
        uzWordsList.add(model.getTranslatedAnswer());
        imageList.add(model.getImage());

    }

    private void checkAnswers(TestQuestionsModel model){
        answer = radio.getText().toString();
        if (answer.equals(model.getAnswer())){
            correct ++;
        }
        else {
            incorrect ++;
        }
    }
}