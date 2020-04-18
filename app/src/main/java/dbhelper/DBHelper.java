package dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uz.pop.uzbekkoreyssuzlashgich.model.Category;
import uz.pop.uzbekkoreyssuzlashgich.model.TestCategoryModel;
import uz.pop.uzbekkoreyssuzlashgich.model.TestQuestionsModel;
import uz.pop.uzbekkoreyssuzlashgich.model.Words;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_PATH = "/data/data/uz.pop.uzbekkoreyssuzlashgich/databases/";
    private static final String DB_NAME = "uzkrdictionary.db";
    private static final int DB_VERSION = 5;
    private Context context;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     if (newVersion < oldVersion){
         try {
             copyDB();
         }
         catch (Exception e){
             e.printStackTrace();
         }
     }
    }

    public void copyDB() throws IOException {
        InputStream dbInput = context.getAssets().open(DB_NAME);
        String outFiles = DB_PATH + DB_NAME;
        OutputStream dbOutput = new FileOutputStream(outFiles);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = dbInput.read(buffer)) > 0){
            dbOutput.write(buffer, 0, length);
        }
        dbOutput.flush();
        dbOutput.close();
        dbInput.close();
    }

    public boolean checkDB(){
        boolean statusDB = false;
        SQLiteDatabase check = null;
        try {
            String dbPath = DB_PATH + DB_NAME;
            check = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (check != null){
            check.close();
            statusDB = true;
        }else {
            statusDB = false;
        }
        return statusDB;
    }

    public void creatDB() {
        boolean dbExist = checkDB();
        this.getReadableDatabase();
        this.close();
        if (!dbExist){
            try {
                copyDB();
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }

    public List<Category> allCategories(){
        SQLiteDatabase database = getReadableDatabase();
       // Category category = new Category();
        List<Category> categoryList = new ArrayList<>();

        String query = "SELECT * FROM category  WHERE parent_id = 0";

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                Category category = new Category();
                int imageId = context.getResources().getIdentifier(context.getPackageName() + ":drawable/"
                        + cursor.getString(6), null, null );

                category.setId(cursor.getInt(0));
                category.setParent_id(cursor.getInt(1));
                category.setTitle(cursor.getString(4));
                category.setImage(imageId);
                categoryList.add(category);
            }while (cursor.moveToNext());
        }database.close();
        return categoryList;
    }

    public List<Words> allWords(int categoryId){
        SQLiteDatabase database = getReadableDatabase();
        List<Words> wordsList = new ArrayList<>();
        String query = "SELECT * FROM words where category_id = " + categoryId;

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                Words words = new Words();
                words.setId(cursor.getInt(0));
                words.setKr(cursor.getString(2));
                words.setLotin(cursor.getString(5));
                words.setLotinPronounce(cursor.getString(6));
                words.setFavourite(cursor.getInt(7));
                wordsList.add(words);
            }while (cursor.moveToNext());
        }
        database.close();
        return wordsList;
    }
    public void setFavourites(int id, int favorite){
        SQLiteDatabase database = getWritableDatabase();
        int currentId;
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM words where _id = " + id;
        Cursor cursor = database.rawQuery(query, null );
        if (cursor.moveToFirst()){
            currentId = cursor.getInt(0);
            values.put("favorite", favorite);
            database.update("words", values, "_id =?", new String[]{String.valueOf(currentId)});
        }while (cursor.moveToNext());
        database.close();
    }

    public List<TestCategoryModel> AllTesatCategories(){
        SQLiteDatabase database = getReadableDatabase();
        List<TestCategoryModel> testCategoryList = new ArrayList<>();
        String query = "select * from test_cats";

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                TestCategoryModel model = new TestCategoryModel();
                //rasmni nomini id ga aylantirish
                int imgId = context.getResources().getIdentifier(context.getPackageName()
                + ":drawable/" + cursor.getString(2), null, null);
                model.setId(cursor.getInt(0));
                model.setTitle(cursor.getString(1));
                model.setImage(imgId);
                testCategoryList.add(model);
            }while (cursor.moveToNext());
        }database.close();
        return testCategoryList;
    }
    public List<TestQuestionsModel> getTestQuestions(int categoryId){
        SQLiteDatabase database = getReadableDatabase();
        List<TestQuestionsModel> testQuestionsList = new ArrayList<>();
        String query = "select * from test_savol where test_cat_name_id = " + categoryId;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                TestQuestionsModel model = new TestQuestionsModel();
                int imgId = context.getResources().getIdentifier(context.getPackageName()
                + ":drawable/" + cursor.getString(18), null, null);

                model.setId(cursor.getInt(0));
                model.setCategoryId(cursor.getInt(1));
                model.setAnswerA(cursor.getString(3));
                model.setAnswerB(cursor.getString(4));
                model.setAnswerC(cursor.getString(5));
                model.setAnswerD(cursor.getString(6));
                model.setAnswer(cursor.getString(7));
                model.setTranslatedAnswer(cursor.getString(15));
                model.setImage(imgId);
                testQuestionsList.add(model);
            }while (cursor.moveToNext());
        }database.close();
        return testQuestionsList;
    }

}
