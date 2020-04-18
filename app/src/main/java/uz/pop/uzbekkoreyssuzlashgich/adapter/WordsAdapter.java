package uz.pop.uzbekkoreyssuzlashgich.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;

import dbhelper.DBHelper;
import uz.pop.uzbekkoreyssuzlashgich.R;
import uz.pop.uzbekkoreyssuzlashgich.model.Words;

public class WordsAdapter extends BaseAdapter {
    Activity context;
    List<Words> wordsList;
    DBHelper dbHelper;
    TextToSpeech tts;
    Animation animation;

    public WordsAdapter(Activity context, List<Words> wordsList){
        this.context = context;
        this.wordsList = wordsList;
    }
    @Override
    public int getCount() {
        return wordsList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final Words words = wordsList.get(position);
        convertView = inflater.inflate(R.layout.words_adapter, null, false);

        final ViewHolder viewHolder = new ViewHolder(convertView);

        animation = AnimationUtils.loadAnimation(context, R.anim.my_animation);
        dbHelper = new DBHelper(context);

        viewHolder.tvKr.setText(words.getKr());
        viewHolder.tvPronounce.setText(words.getLotinPronounce());
        viewHolder.tvUz.setText(words.getLotin());
//sound
        viewHolder.imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.imgSound.startAnimation(animation);
                spellText(position);
               //Toast.makeText(context, words.getKr(), Toast.LENGTH_SHORT).show();
            }
        });
                //favorite

        if (words.getFavourite() == 0){
            viewHolder.imgFavourite.setImageResource(R.drawable.favourite_off);
        }
        else if (words.getFavourite() == 1){
            viewHolder.imgFavourite.setImageResource(R.drawable.favourite_on);
        }
        viewHolder.imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.imgFavourite.startAnimation(animation);
                int checkFavorite = words.getFavourite();
                if (checkFavorite == 0){
                    checkFavorite = 1;
                    words.setFavourite(checkFavorite);
                    viewHolder.imgFavourite.setImageResource(R.drawable.favourite_on);

                }else if (checkFavorite == 1){
                    checkFavorite = 0;
                    words.setFavourite(checkFavorite);
                    viewHolder.imgFavourite.setImageResource(R.drawable.favourite_off);
                }
                dbHelper.setFavourites(words.getId(), words.getFavourite());
            }
        });

        viewHolder.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.imgShare.startAnimation(animation); //animatsiya uchun
                Intent sharingIntent = new Intent(Intent.ACTION_SEND); //actionlarni biriktirish
                sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,
                        viewHolder.tvKr.getText().toString()
                        + "\n" + viewHolder.tvPronounce.getText().toString()
                        + "\n" + viewHolder.tvUz.getText().toString());
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
   //nusxalash
        viewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewHolder.linearLayout.startAnimation(animation);
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                CharSequence copiedData = words.getKr();
                ClipData clipData = ClipData.newPlainText("Source text", copiedData);
                clipboardManager.setPrimaryClip(clipData);

//                Snackbar.make(v, R.string.malumot_nusxalandi, Snackbar.LENGTH_SHORT).show(); //qisqa usuli
                Snackbar snackbar = Snackbar.make(v, R.string.malumot_nusxalandi, Snackbar.LENGTH_SHORT);
                snackbar.show();

                return true;
            }
        });

        return convertView;
    }
    //so'zni talaffuz qildirish

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





    private  class ViewHolder{
        TextView tvKr, tvPronounce, tvUz;
        ImageView imgSound, imgShare, imgFavourite;
        LinearLayout linearLayout;

        private ViewHolder(View view){
            tvKr =  view.findViewById(R.id.words_txt_kr);
            tvPronounce =  view.findViewById(R.id.words_txt_pronounce);
            tvUz =  view.findViewById(R.id.words_txt_uz);
            imgSound = view.findViewById(R.id.words_img_sound);
            imgShare = view.findViewById(R.id.words_img_share);
            imgFavourite = view.findViewById(R.id.words_img_favourite);

            linearLayout = view.findViewById(R.id.words_adapter_liner);
        }
    }
}
