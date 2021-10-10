package com.course.kpop;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "kpop_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_KOREAN_ANSWER = "korean_answer";
        public static final String COLUMN_ENGLISH_ANSWER = "english_answer";
        public static final String COLUMN_REAL_ANSWER = "real_answer";
    }
}
