package com.course.kpop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.course.kpop.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyKpopQuiz.db";
    private static final int DATABASE_VERSION = 2;

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
                QuestionsTable.COLUMN_REAL_ANSWER + " TEXT " +
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
        Question q1 = new Question("4TWR90KJl84", "넥스트레벨","nextlevel","Next Level - Aespa");
        addQuestion(q1);

        Question q2 = new Question("gdZLi9oWNZg", "다이너마이트","dynamite","Dynamite - BTS");
        addQuestion(q2);

        Question q3 = new Question("WMweEpGlu_U", "버터","butter","Butter - BTS");
        addQuestion(q3);

        Question q4 = new Question("jv543Nk5s18", "염라","Karma","염라 - 달의하루");
        addQuestion(q4);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_KOREAN_ANSWER, question.getKoreanAnswer());
        cv.put(QuestionsTable.COLUMN_ENGLISH_ANSWER, question.getEnglishAnswer2());
        cv.put(QuestionsTable.COLUMN_REAL_ANSWER, question.getRealAnswer());
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
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
