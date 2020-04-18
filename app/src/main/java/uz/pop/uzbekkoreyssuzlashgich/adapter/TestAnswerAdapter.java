package uz.pop.uzbekkoreyssuzlashgich.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import uz.pop.uzbekkoreyssuzlashgich.R;
import uz.pop.uzbekkoreyssuzlashgich.grammar.test.TestQuestions;
import uz.pop.uzbekkoreyssuzlashgich.model.Words;

public class TestAnswerAdapter extends BaseAdapter {

    Context context;
    List<String> krWordsList;
    List<String> uzWordsList;
    List<Integer> imageList;
    Animation animation;
    TextToSpeech tts;
    List<Words> wordsList;

    public TestAnswerAdapter(Context context, List<String> krWordsList, List<String> uzWordsList, List<Integer> imageList) {
        this.context = context;
        this.krWordsList = krWordsList;
        this.uzWordsList = uzWordsList;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return krWordsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.adapter_test_answer, parent, false);
        final ViewHolder holder = new ViewHolder(convertView);
       animation = AnimationUtils.loadAnimation(context, R.anim.my_animation);
        holder.imageView.setImageResource(imageList.get(position));
        holder.tvKr.setText(krWordsList.get(position));
        holder.tvUz.setText(uzWordsList.get(position));
        //shu yerga soundni yaratishim kk, oldingiga qarab
        holder.sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sound.startAnimation(animation);
                spellText(position);
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        ImageView imageView, sound;
        TextView tvKr, tvUz;

        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.adapter_test_answer_img);
            tvKr = view.findViewById(R.id.adapter_test_answer_kr);
            tvUz = view.findViewById(R.id.adapter_test_answer_uz);
            sound = view.findViewById(R.id.adapter_test_answer_sound);
        }
    }
    //so'zni talaffuz qilish
    private void onStopTTS(){
        if (tts != null){
            tts.stop();
            tts.shutdown();
        }
    }
    private void spellText(final int position){
        onStopTTS();
        final Words words = wordsList.get(position);

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.KOREA);
                    if (result != TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED){
                        tts.speak(words.getKr(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });
    }

}
