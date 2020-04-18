package uz.pop.uzbekkoreyssuzlashgich.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import uz.pop.uzbekkoreyssuzlashgich.R;
import uz.pop.uzbekkoreyssuzlashgich.model.Category;

public class CategoryAdapter extends BaseAdapter {

    Activity context;
    List<Category> categoryList;


    public CategoryAdapter(Activity context, List<Category> categoryList) {
       // super(context, R.layout.category_adapter);
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category category = categoryList.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.category_adapter, null, true);
        ViewHolder viewHolder = new ViewHolder(convertView);

        viewHolder.imageView.setImageResource(category.getImage());
        viewHolder.textView.setText(category.getTitle());

        return convertView;
    }

    private static class ViewHolder{
        TextView textView;
        ImageView imageView;

       public ViewHolder(View view){
           textView = view.findViewById(R.id.category_title);
           imageView = view.findViewById(R.id.category_image);
        }
    }
}
