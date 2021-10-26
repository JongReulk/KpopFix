package com.course.kpop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.course.kpop.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyKpopQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        // 주의 깊게 코딩
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                // QuizContract에 있는 부분
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_KOREAN_ANSWER + " TEXT, " +
                QuestionsTable.COLUMN_ENGLISH_ANSWER + " TEXT, " +
                QuestionsTable.COLUMN_REAL_ANSWER + " TEXT, " +
                QuestionsTable.COLUMN_YEAR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("4TWR90KJl84", "넥스트레벨","nextlevel","Next Level - Aespa",2021);
        addQuestion(q1);

        Question q2 = new Question("gdZLi9oWNZg", "다이너마이트","dynamite","Dynamite - BTS",2020);
        addQuestion(q2);

        Question q3 = new Question("WMweEpGlu_U", "버터","butter","Butter - BTS",2021);
        addQuestion(q3);

        Question q4 = new Question("c7rCyll5AeY", "치얼업","cheerup","CHEER UP - TWICE",2016);
        addQuestion(q4);

        Question q5 = new Question("8A2t_tAjMz8", "낙낙","knockknock","KNOCK KNOCK - TWICE",2017);
        addQuestion(q5);

        Question q6 = new Question("7C2z4GqqS5E", "페이크러브","fakelove","FAKE LOVE - BTS",2018);
        addQuestion(q6);

        Question q7 = new Question("kOHB85vDuow", "팬시","fancy","FANCY - TWICE",2019);
        addQuestion(q7);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_KOREAN_ANSWER, question.getKoreanAnswer());
        cv.put(QuestionsTable.COLUMN_ENGLISH_ANSWER, question.getEnglishAnswer2());
        cv.put(QuestionsTable.COLUMN_REAL_ANSWER, question.getRealAnswer());
        cv.put(QuestionsTable.COLUMN_YEAR, question.getYear());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setKoreanAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_KOREAN_ANSWER)));
                question.setEnglishAnswer2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ENGLISH_ANSWER)));
                question.setRealAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_REAL_ANSWER)));
                question.setYear(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_YEAR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public List<Question> getQuestions(String year) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        /*
        for(int i = 0; i < year.length; i++){
            String[] selectionArgs = new String[]{year[i]};
        }*/


        String[] selectionArgs = new String[]{year};
        Log.e("Year", " : " + selectionArgs.length);
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_YEAR + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setKoreanAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_KOREAN_ANSWER)));
                question.setEnglishAnswer2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ENGLISH_ANSWER)));
                question.setRealAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_REAL_ANSWER)));
                question.setYear(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_YEAR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
