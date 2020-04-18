package uz.pop.uzbekkoreyssuzlashgich.grammar.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import dbhelper.DBHelper;
import uz.pop.uzbekkoreyssuzlashgich.R;
import uz.pop.uzbekkoreyssuzlashgich.adapter.TestCategoryAdapter;
import uz.pop.uzbekkoreyssuzlashgich.model.TestCategoryModel;

public class TestCategoryList extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_category_list);
        listView = findViewById(R.id.test_category_listview);
        dbHelper = new DBHelper(this);

        TestCategoryAdapter adapter = new TestCategoryAdapter(this,dbHelper.AllTesatCategories());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<TestCategoryModel> categoryModelList = dbHelper.AllTesatCategories();
                TestCategoryModel model = categoryModelList.get(position);
                int categoryId = model.getId();
                Intent intent = new Intent(TestCategoryList.this, TestQuestions.class);
                intent.putExtra("categoryId", categoryId);
                startActivity(intent);
            }
        });

    }
}
