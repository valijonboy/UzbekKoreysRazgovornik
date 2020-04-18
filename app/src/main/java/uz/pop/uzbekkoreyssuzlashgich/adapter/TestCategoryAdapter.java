package uz.pop.uzbekkoreyssuzlashgich.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uz.pop.uzbekkoreyssuzlashgich.model.TestCategoryModel;
import uz.pop.uzbekkoreyssuzlashgich.R;

public class TestCategoryAdapter extends BaseAdapter {
    Context context;
    List<TestCategoryModel> testCategoryList;
    //birinchi qadam metodlarni implement qilish, construktorni yaratish
    public TestCategoryAdapter(Context context, List<TestCategoryModel> testCategoryList){
        this.context = context;
        this.testCategoryList = testCategoryList;
    }
    //2-qadam list size ni qaytarish
    @Override
    public int getCount() {
        return testCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
//4- qadam getView ni yozish
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.adapter_test_category, parent, false);

        TestCategoryModel model = testCategoryList.get(position);
        ViewHolder holder = new ViewHolder(convertView);
        holder.textView.setText(model.getTitle());
        holder.imageView.setImageResource(model.getImage());


        return convertView;
    }
    // 3- qadam ViewHolder classini yaratish

    private static class ViewHolder{
        ImageView imageView;
        TextView textView;

        private ViewHolder(View view){
            imageView = view.findViewById(R.id.test_category_image);
            textView = view.findViewById(R.id.test_category_title);
        }
    }
}
