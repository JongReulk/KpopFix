package com.tenriver.kpop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tenriver.kpop.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyKpopQuiz.db";
    private static final int DATABASE_VERSION = 14; // 데이터베이스 버전 항상 다르게 하기 ( 2021.11.26 기준 7)

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
                QuestionsTable.COLUMN_YEAR + " INTEGER, " +
                QuestionsTable.COLUMN_HINT + " TEXT, " +
                QuestionsTable.COLUMN_SINGER + " TEXT " +
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

        Question q1 = new Question("4TWR90KJl84", "넥스트레벨","nextlevel","Next Level - Aespa",2021, "ㄴㅅㅌ ㄹㅂ / n**t l**e*","aespa (에스파)");	addQuestion(q1);
        Question q2 = new Question("WMweEpGlu_U", "버터","butter","Butter - BTS",2021, "ㅂㅌ / b**t**","BTS (방탄소년단)");	addQuestion(q2);
        Question q3 = new Question("Kevp2lFKSOg", "스트로베리문","strawberrymoon","strawberry moon - 아이유",2021, "ㅅㅌㄹㅂㄹ ㅁ / s**a**e**y m**n","IU(아이유)");	addQuestion(q3);
        Question q4 = new Question("SdHQkkRc-hc", "말해뭐해","talklove","말해! 뭐해? (Talk Love) -  K.will(케이윌)",2016, "ㅁㅎㅁㅎ / t**k l**e"," K.will(케이윌)");	addQuestion(q4);
        Question q5 = new Question("cIGgSI1uhKI", "봄이좋냐","whatthespring","봄이 좋냐?? (What The Spring??) - 10cm",2016, " ㅂㅇ ㅈㄴ / w**t t** s**i**","10cm");	addQuestion(q5);
        Question q6 = new Question("_3tIkwvUjJg", "이프유","ifyou","If You - Ailee(에일리)",2016, "ㅇㅍ ㅇ / i* y**","Ailee(에일리)");	addQuestion(q6);
        Question q7 = new Question("ST8O-AeY3Uo", "리바이","rebye","RE-BYE - AKMU",2016, "ㄹㅂㅇ / r* b**","AKMU");	addQuestion(q7);
        Question q8 = new Question("kCSFdRf0210", "내가설렐수있게","onlyone","내가 설렐 수 있게 (Only one) - Apink(에이핑크)",2016, "ㄴㄱ ㅅㄹ ㅅ ㅇㄱ / o**y o**","Apink(에이핑크)");	addQuestion(q8);
        Question q9 = new Question("AMWOLv4Y_0Y", "데이데이","dayday","Day Day (Feat. 박재범) - BewhY",2016, "ㄷㅇㄷㅇ / d** d**","BewhY");	addQuestion(q9);
        Question q10 = new Question("SX3pn5_arII", "포레버","forever","Forever (Prod. by GRAY) - BewhY",2016, "ㅍㄹㅂ / f**e**r","BewhY");	addQuestion(q10);
        Question q11 = new Question("iIPH8LFYFRk", "에라모르겠다","fxxxit","에라 모르겠다 (FXXK IT) - BIGBANG",2016, "ㅇㄹ ㅁㄹㄱㄷ / f**k i*","BIGBANG");	addQuestion(q11);
        Question q12 = new Question("dISNgvVpWlo", "휘파람","whistle","휘파람 (WHISTLE) - BLACKPINK",2016, "ㅎㅍㄹ / w**s**e","BLACKPINK");	addQuestion(q12);
        Question q13 = new Question("bwmSjveL3Lc", "붐바야","boombayah","붐바야 (BOOMBAYAH) - BLACKPINK",2016, "ㅂㅂㅇ / b**m**y**","BLACKPINK");	addQuestion(q13);
        Question q14 = new Question("9pdj4iJD08s", "불장난","playingwithfire","불장난 (Playing With Fire) - BLACKPINK",2016, "ㅂㅈㄴ / p**y**g w**h f**e","BLACKPINK");	addQuestion(q14);
        Question q15 = new Question("9U8uA702xrE", "우주를줄게","galaxy","우주를 줄게 (GALAXY) - BOL4(볼빨간사춘기)",2016, "ㅇㅈㄹ ㅈㄱ / g**a**","BOL4(볼빨간사춘기)");	addQuestion(q15);
        Question q16 = new Question("y5MAgMVwfFs", "좋다고말해","tellmeyoulikeme","좋다고 말해 - BOL4(볼빨간사춘기)",2016, "ㅈㄷㄱ ㅁㅎ / t**l m* y** l**e m*","BOL4(볼빨간사춘기)");	addQuestion(q16);
        Question q17 = new Question("hmE9f-TEutc", "피땀눈물","bloodsweatandtears","피 땀 눈물 (Blood Sweat & Tears) - BTS (방탄소년단)",2016, "ㅍ ㄸ ㄴㅁ / b**o* s**a* a** t**r*","BTS (방탄소년단)");	addQuestion(q17);
        Question q18 = new Question("4ujQOR2DMFM", "불타오르네","fire","불타오르네 (FIRE) - BTS (방탄소년단)",2016, "ㅂㅌㅇㄹㄴ / f**e","BTS (방탄소년단)");	addQuestion(q18);
        Question q19 = new Question("fTc5tuEn6_U", "에브리타임","everytime","Everytime - CHEN(첸)XPunch(펀치)",2016, "ㅇㅂㄹㅌㅇ / e**r* t**e","CHEN(첸)XPunch(펀치)");	addQuestion(q19);
        Question q20 = new Question("FNnYIIdTBhQ", "어떻게지내","fall","어떻게 지내 (fall) - Crush(크러쉬)",2016, "ㅇㄸㄱ ㅈㄴ / f**l","Crush(크러쉬)");	addQuestion(q20);
        Question q21 = new Question("TcytstV1_XE", "잊어버리지마","dontforget","잊어버리지마 (Don’t Forget) - Crush(크러쉬)",2016, "ㅇㅇㅂㄹㅈㅁ / d**t f**g**","Crush(크러쉬)");	addQuestion(q21);
        Question q22 = new Question("XyzaMpAVm3s", "이사랑","thislove","이 사랑 (This Love) - DAVICHI(다비치)",2016, "ㅇ ㅅㄹ / t**s l**e","DAVICHI(다비치)");	addQuestion(q22);
        Question q23 = new Question("eelfrHtmk68", "디","d","D (Half Moon) - Dean",2016, "ㄷ / *","Dean");	addQuestion(q23);
        Question q24 = new Question("RnDSODY7bVE", "엘라이","lie","L.I.E 엘라이 - EXID(이엑스아이디)",2016, "ㅇㄹㅇ / l**","EXID(이엑스아이디)");	addQuestion(q24);
        Question q25 = new Question("KSH-FVVtTf0", "몬스터","monster","Monster - EXO (엑소)",2016, "ㅁㅅㅌ / m**s**r","EXO (엑소)");	addQuestion(q25);
        Question q26 = new Question("0VKcLPdY9lI", "시간을달려서","rough","시간을 달려서 (Rough) - GFRIEND(여자친구)",2016, "ㅅㄱㅇ ㄷㄹㅅ / r**g*","GFRIEND(여자친구)");	addQuestion(q26);
        Question q27 = new Question("ToASX6axGuw", "유아마이에브리띵","youaremyeverything","You Are My Everything - Gummy(거미)",2016, "ㅇㅇㅁㅇㅇㅂㄹㄸ / y** a** m* e**r**h**g","Gummy(거미)");	addQuestion(q27);
        Question q28 = new Question("WYy2fROj7uU", "그대라는사치","amazingyou","그대라는 사치 (Amazing You) - Han Dong Geun(한동근)",2016, "ㄱㄷㄹㄴ ㅅㅊ / a**z**g y**","Han Dong Geun(한동근)");	addQuestion(q28);
        Question q29 = new Question("5iSlfF8TQ9k", "한숨","breathe","한숨 (BREATHE) - LEE HI",2016, "ㅎㅅ / b**a**e","LEE HI");	addQuestion(q29);
        Question q30 = new Question("va5rf20Un24", "어디에도","nomatterwhere","어디에도 (No matter where) - M.C THE MAX(엠씨더맥스)",2016, "ㅇㄷㅇㄷ / n* m**t** w**r*","M.C THE MAX(엠씨더맥스)");	addQuestion(q30);
        Question q31 = new Question("QslJYDX3o8s", "러시안룰렛","russianroulette","러시안 룰렛 (Russian Roulette) - Red Velvet (레드벨벳)",2016, "ㄹㅅㅇ ㄹㄹ / r**s**n r**l**t*","Red Velvet (레드벨벳)");	addQuestion(q31);
        Question q32 = new Question("m7Zt_p9S-yg", "세단어","threewords","세 단어 (THREE WORDS) - SECHSKIES (젝스키스)",2016, "ㅅ ㄷㅇ / t**e* w**d*","SECHSKIES (젝스키스)");	addQuestion(q32);
        Question q33 = new Question("J-wFp43XOrA", "아주나이스","verynice","아주 NICE (VERY NICE) - SEVENTEEN (세븐틴)",2016, "ㅇㅈ ㄴㅇㅅ / v**y n**e","SEVENTEEN (세븐틴)");	addQuestion(q33);
        Question q34 = new Question("Jkf8TrvtjTk", "사랑하자","bymyside","사랑하자 (By My Side) - SG WANNABE(SG워너비)",2016, "ㅅㄹㅎㅈ / b* m* s**e","SG WANNABE(SG워너비)");	addQuestion(q34);
        Question q35 = new Question("eHir_vB1RUI", "레인","rain","Rain - TAEYEON (태연)",2016, "ㄹㅇ / r**n","TAEYEON (태연)");	addQuestion(q35);
        Question q36 = new Question("ePpPVE-GGJw", "티티","tt","TT (티티) - TWICE(트와이스)",2016, "ㅌㅌ / t*","TWICE(트와이스)");	addQuestion(q36);
        Question q37 = new Question("c7rCyll5AeY", "치얼업","cheerup","CHEER UP - TWICE(트와이스)",2016, "ㅊㅇㅇ / c**e* u*","TWICE(트와이스)");	addQuestion(q37);
        Question q38 = new Question("gluvBLJYBjs", "셧업","shutup","Shut Up - Unnies(언니쓰)",2016, "ㅅㅇ / s**t u*","Unnies(언니쓰)");	addQuestion(q38);
        Question q39 = new Question("OV9NJGTLm-4", "센치해","sentimental","센치해(SENTIMENTAL) - WINNER",2016, "ㅅㅊㅎ / s**t**e**a*","WINNER");	addQuestion(q39);
        Question q40 = new Question("PYGODWJgR-c", "와이소론리","whysolonely","Why So Lonely - Wonder Girls(원더걸스)",2016, "ㅇㅇ ㅅ ㄹㄹ / w** s* l**e**","Wonder Girls(원더걸스)");	addQuestion(q40);
        Question q41 = new Question("bstWoRsocaw", "내옆에그대인걸","besideme","내 옆에 그대인걸 (Beside me) - 다비치 (DAVICHI)",2016, "ㄴ ㅇㅇ ㄱㄷㅇㄱ / b* s**e m*","다비치 (DAVICHI)");	addQuestion(q41);
        Question q42 = new Question("QbhmTM1cRro", "넌이즈뭔들","yourethebest","넌 is 뭔들 (You're the best) - 마마무 (MAMAMOO)",2016, "ㄴ ㅇㅈ ㅁㄷ / y**r* t** b**t","마마무 (MAMAMOO)");	addQuestion(q42);
        Question q43 = new Question("y2OFPvYxZuY", "데칼코마니","decalcomanie","데칼코마니 (Décalcomanie)  - 마마무 (MAMAMOO)",2016, "ㄷㅋㅋㅁㄴ / d**a**o**n**","마마무 (MAMAMOO)");	addQuestion(q43);
        Question q44 = new Question("BbQG-S4mU0U", "쏘쏘","soso","쏘쏘 - 백아연",2016, "ㅆㅆ / s* s*","백아연");	addQuestion(q44);
        Question q45 = new Question("QL5KqjOZo6M", "같은곳에서","inthesameplace","같은 곳에서 (In the Same Place) - 소녀온탑 (Girls On Top)",2016, "ㄱㅇ ㄱㅇㅅ / i* t** s**e p**c*","소녀온탑 (Girls On Top)");	addQuestion(q45);
        Question q46 = new Question("WfYgbFBFe1E", "드림","dream","Dream - 수지(Suzy), 백현(BAEKHYUN)",2016, "ㄷㄹ / d**a*","수지(Suzy), 백현(BAEKHYUN)");	addQuestion(q46);
        Question q47 = new Question("LJVUjmNMF8c", "아이라이크댓","ilikethat","I Like That - 씨스타(SISTAR)",2016, "ㅇㅇ ㄹㅇㅋ ㄷ / i l**e t**t","씨스타(SISTAR)");	addQuestion(q47);
        Question q48 = new Question("GdxvD7r58ng", "너무너무너무","veryveryvery","너무너무너무 (Very Very Very) - 아이오아이 (I.O.I)",2016, "ㄴㅁㄴㅁㄴㅁ / v**y v**y v**y","아이오아이 (I.O.I)");	addQuestion(q48);
        Question q49 = new Question("MfYPKZl7W1w", "널사랑하지않아","idontloveyou","널 사랑하지 않아 (I Don't Love You) - 어반자카파(Urban Zakapa)",2016, "ㄴ ㅅㄹㅎㅈ ㅇㅇ / i d**t l**e y**","어반자카파(Urban Zakapa)");	addQuestion(q49);
        Question q50 = new Question("erErBFKPbMY", "봄인가봐","springlove","봄인가 봐 (Spring Love) - 에릭남 X 웬디",2016, "ㅂㅇㄱ ㅂ / s**i** l**e","에릭남 X 웬디");	addQuestion(q50);
        Question q51 = new Question("1ZhDsPdvl6c", "너그리고나","navillera","너 그리고 나 (NAVILLERA) - GFRIEND(여자친구)",2016, "ㄴ ㄱㄹㄱ ㄴ / n**i**e**","GFRIEND(여자친구)");	addQuestion(q51);
        Question q52 = new Question("L-2M_-QLs8k", "내가저지른사랑","theloveicommitted","내가 저지른 사랑 - 임창정",2016, "ㄴㄱ ㅈㅈㄹ ㅅㄹ / t** l**e I c**m**t**","임창정");	addQuestion(q52);
        Question q53 = new Question("nQRBLrtohxA", "사랑에빠졌죠","falleninlove","사랑에 빠졌죠(당신만이) Fallen in Love(Only With You) - 장범준 (Beom June Jang)",2016, "ㅅㄹㅇ ㅃㅈㅈ / f**l** i* l**e","장범준 (Beom June Jang)");	addQuestion(q53);
        Question q54 = new Question("EjHAgoN2KtU", "너였다면","ifitisyou","너였다면 (If It Is You) - 정승환 (Jung Seung-Hwan)",2016, "ㄴㅇㄷㅁ / i* i* i* y**","정승환 (Jung Seung-Hwan)");	addQuestion(q54);
        Question q55 = new Question("xbf2c0JBJic", "너는나나는너","iamyouyouareme","너는 나 나는 너 (I am you, you are me) - 지코 (ZICO)",2016, "ㄴㄴ ㄴ ㄴㄴ ㄴ / i a* y** y** a** m*","지코 (ZICO)");	addQuestion(q55);
        Question q56 = new Question("h9mvVTgfKCs", "사랑이었다","itwaslove","사랑이었다 (Feat. LUNA of f(x)) - 지코 (ZICO)",2016, "ㅅㄹㅇㅇㄷ / i* w** l**e","지코 (ZICO)");	addQuestion(q56);
        Question q57 = new Question("I7EwLNhPgpg", "돌아오지마","dontcomeback","돌아오지마 - 헤이즈 (Heize)",2016, "ㄷㅇㅇㅈㅁ / d**t c**e b**k","헤이즈 (Heize)");	addQuestion(q57);
        Question q58 = new Question("8Oz7DG76ibY", "다이노소어","dinosaur","DINOSAUR - AKMU",2017, "ㄷㅇㄴㅅㅇ / d**o**u*","AKMU");	addQuestion(q58);
        Question q59 = new Question("NRlDo3JuzfI", "익스큐즈미","excuseme","Excuse Me - AOA",2017, "ㅇㅅㅋㅈㅁ / e**u** m*","AOA");	addQuestion(q59);
        Question q60 = new Question("RIWRyggt-QY", "봄의 나라 이야기","aprilstory","봄의 나라 이야기 (April Story) - APRIL(에이프릴)",2017, "ㅂㅇ ㄴㄹ ㅇㅇㄱ / a**i* s**r*","APRIL(에이프릴)");	addQuestion(q60);
        Question q61 = new Question("hZmoMyFXDoI", "썸탈꺼야","some","썸 탈꺼야 (SOME) - BOL4(볼빨간사춘기)",2017, "ㅆㅌㄱㅇ / s**e","BOL4(볼빨간사춘기)");	addQuestion(q61);
        Question q62 = new Question("42A-rFdralM", "무비","movie","MOVIE - BTOB(비투비)",2017, "ㅁㅂ / m**i*","BTOB(비투비)");	addQuestion(q62);
        Question q63 = new Question("MBdVXkSdhwU", "디엔에이","dna","DNA - BTS (방탄소년단)",2017, "ㄷㅇㅇㅇ / d**","BTS (방탄소년단)");	addQuestion(q63);
        Question q64 = new Question("W0cs6ciCt_k", "뷰티풀","beautiful","Beautiful - Crush(크러쉬)",2017, "ㅂㅌㅍ / b**u**f**","Crush(크러쉬)");	addQuestion(q64);
        Question q65 = new Question("wKyMIrBClYw", "인스타그램","instagram","instagram - DEAN",2017, "ㅇㅅㅌㄱㄹ / i**t**r**","DEAN");	addQuestion(q65);
        Question q66 = new Question("wLPgdkLMCKU", "기다렸다가","nosedive","기다렸다 가 (nosedive) - Dynamic Duo(다이나믹 듀오), CHEN(첸)",2017, "ㄱㄷㄹㄷ ㄱ / n**e**v*","Dynamic Duo(다이나믹 듀오), CHEN(첸)");	addQuestion(q66);
        Question q67 = new Question("axVvZrDz60k", "덜덜덜","ddd","덜덜덜(DDD) - EXID(이엑스아이디)",2017, "ㄷㄷㄷ / d**","EXID(이엑스아이디)");	addQuestion(q67);
        Question q68 = new Question("5wEjz3RdnKA", "낮보다는밤","nightratherthanday","낮보다는 밤 (Night Rather Than Day) - EXID(이엑스아이디)",2017, "ㄴㅂㄷㄴ ㅂ / n**h* r**h** t**n d**","EXID(이엑스아이디)");	addQuestion(q68);
        Question q69 = new Question("sGRv8ZBLuW0", "파워","power","Power - EXO (엑소)",2017, "ㅍㅇ / p**e*","EXO (엑소)");	addQuestion(q69);
        Question q70 = new Question("ZsYwEV_ge4Y", "여름비","summerrain","여름비 (Summer Rain) - GFRIEND(여자친구)",2017, "ㅇㄹㅂ / s**m** r**n","GFRIEND(여자친구)");	addQuestion(q70);
        Question q71 = new Question("7crt2Ip93VI", "아이윌비유얼스","illbeyours","I'll be yours - Girl's Day(걸스데이)",2017, "ㅇㅇㅇ ㅂ ㅇㅇㅅ / i** b* y**r*","Girl's Day(걸스데이)");	addQuestion(q71);
        Question q72 = new Question("YwN-CN9EjTg", "홀리데이","holiday","Holiday - Girls' Generation (소녀시대)",2017, "ㅎㄹㄷㅇ / h**i**y","Girls' Generation (소녀시대)");	addQuestion(q72);
        Question q73 = new Question("IZ1t7CwfvEc", "네버에버","neverever","Never Ever - GOT7(갓세븐)",2017, "ㄴㅂ ㅇㅂ / n**e* e**r","GOT7(갓세븐)");	addQuestion(q73);
        Question q74 = new Question("b6l0x9xxk4k", "콜링유","callingyou","CALLING YOU - Highlight(하이라이트)",2017, "ㅋㄹ ㅇ / c**l**g y**","Highlight(하이라이트)");	addQuestion(q74);
        Question q75 = new Question("GeJAee3m3Ik", "블루문","bluemoon","BLUE MOON - HYOLYN, CHANGMO(효린, 창모)",2017, "ㅂㄹㅁ / b**e m**n","HYOLYN, CHANGMO(효린, 창모)");	addQuestion(q75);
        Question q76 = new Question("pC6tPEaAiYU", "톰보이","tomboy","톰보이 (TOMBOY) - HYUKOH(혁오)",2017, "ㅌㅂㅇ / t**b**","HYUKOH(혁오)");	addQuestion(q76);
        Question q77 = new Question("b9xndFqGJ4k", "베베","babe","베베(BABE) - HyunA(현아)",2017, "ㅂㅂ / b*b*","HyunA(현아)");	addQuestion(q77);
        Question q78 = new Question("E9HV6jh6qt0", "사랑이잘","cantloveyouanymore","사랑이 잘 (Can't Love You Anymore)  - IU (아이유) With OHHYUK(오혁)",2017, "ㅅㄹㅇ ㅈ / c**t l**e y** a**m**e","IU (아이유) With OHHYUK(오혁)");	addQuestion(q78);
        Question q79 = new Question("b1kQvZhQ6_M", "좋니","likeit","좋니 (Like it) - Jong Shin Yoon(윤종신)",2017, "ㅈㄴ / l**e i*","Jong Shin Yoon(윤종신)");	addQuestion(q79);
        Question q80 = new Question("aehbDMIEmnM", "휘휘","hwihwi"," 휘휘 (Hwi hwi) - LABOUM(라붐)",2017, "ㅎㅎ / h**h**","LABOUM(라붐)");	addQuestion(q80);
        Question q81 = new Question("qYYJqWsBb1U", "선물","gift","선물 (Gift) - MeloMance(멜로망스)",2017, "ㅅㅁ / g**t","MeloMance(멜로망스)");	addQuestion(q81);
        Question q82 = new Question("7cjZFjWBZI0", "컬러링북","coloringbook","컬러링북 (Coloring Book) - OH MY GIRL(오마이걸)",2017, "ㅋㄹㄹㅂ / c**o**n* b**k","OH MY GIRL(오마이걸)");	addQuestion(q82);
        Question q83 = new Question("wLfHuClrQdI", "위후","weewoo","WEE WOO - PRISTIN(프리스틴)",2017, "ㅇㅎ / w**w**","PRISTIN(프리스틴)");	addQuestion(q83);
        Question q84 = new Question("_wNWJGSFcwg", "나야나","itsme","나야 나 (It's Me) - PRODUCE 101",2017, "ㄴㅇ ㄴ / i** m*","PRODUCE 101");	addQuestion(q84);
        Question q85 = new Question("OwJPPaEyqhI", "뉴페이스","newface","New Face - PSY",2017, "ㄴㅍㅇㅅ / n** f**e","PSY");	addQuestion(q85);
        Question q86 = new Question("6uJf2IT2Zh8", "피카부","peekaboo","피카부 (Peek-A-Boo) - Red Velvet (레드벨벳)",2017, "ㅍㅋㅂ / p**k**o*","Red Velvet (레드벨벳)");	addQuestion(q86);
        Question q87 = new Question("J0h8-OTC38I", "루키","rookie","Rookie - Red Velvet (레드벨벳)",2017, "ㄹㅋ / r**k**","Red Velvet (레드벨벳)");	addQuestion(q87);
        Question q88 = new Question("Nu7OmSqHVng", "우쥬","wouldu","Would U - Red Velvet (레드벨벳)",2017, "ㅇㅈ / w**l* u","Red Velvet (레드벨벳)");	addQuestion(q88);
        Question q89 = new Question("zEkg4GBQumc", "울고싶지않아","dontwannacry","울고 싶지 않아 (Don't Wanna Cry) - SEVENTEEN (세븐틴)",2017, "ㅇㄱ ㅅㅈ ㅇㅇ / d**t w**n* c**","SEVENTEEN (세븐틴)");	addQuestion(q89);
        Question q90 = new Question("CyzEtbG-sxY", "박수","clap","CLAP - SEVENTEEN (세븐틴)",2017, "ㅋㄹ / c**p","SEVENTEEN (세븐틴)");	addQuestion(q90);
        Question q91 = new Question("sUZeYOLp8Ys", "오솔레미오","osolemio","O Sole Mio(오솔레미오) - SF9 (에스에프나인)",2017, "ㅇㅅㄹㅁㅇ / o s**e m**","SF9 (에스에프나인)");	addQuestion(q91);
        Question q92 = new Question("1Q8J5nghxiM", "비가와","rain","비가와 (Rain) - SOYOU(소유), BAEKHYUN(백현)",2017, "ㅂㄱㅇ / r**n","SOYOU(소유), BAEKHYUN(백현)");	addQuestion(q92);
        Question q93 = new Question("rcEyUNeZqmY", "무브","move","MOVE - TAEMIN (태민)",2017, "ㅁㅂ / m**e","TAEMIN (태민)");	addQuestion(q93);
        Question q94 = new Question("TVUqLBRQom8", "메이크미러브유","makemeloveyou","Make Me Love You - TAEYEON (태연)",2017, "ㅁㅇㅋ ㅁ ㄹㅂ ㅇ / m**e m* l**e y**","TAEYEON (태연)");	addQuestion(q94);
        Question q95 = new Question("hsh54g9JmC0", "디스크리스마스","thischristmas","This Christmas - TAEYEON (태연)",2017, "ㄷㅅ ㅋㄹㅅㅁㅅ / t**s c**i**m**","TAEYEON (태연)");	addQuestion(q95);
        Question q96 = new Question("VQtonf1fv_s", "시그널","signal","SIGNAL - TWICE(트와이스)",2017, "ㅅㄱㄴ / s**n**","TWICE(트와이스)");	addQuestion(q96);
        Question q97 = new Question("Nv5_uvyqI9s", "맞지","right","맞지? (Right?) - Unnies(언니쓰)",2017, "ㅁㅈ / r**h*","Unnies(언니쓰)");	addQuestion(q97);
        Question q98 = new Question("ppOWR7ZLl7Q", "러브미러브미","lovemeloveme","LOVE ME LOVE ME - WINNER",2017, "ㄹㅂㅁ ㄹㅂㅁ / l**e m* l**e m*","WINNER");	addQuestion(q98);
        Question q99 = new Question("J7gOqqbBW6w", "너에게닿기를","iwish","너에게 닿기를 (I Wish) - WJSN (우주소녀)",2017, "ㄴㅇㄱ ㄷㄱㄹ / I w**h","WJSN (우주소녀)");	addQuestion(q99);
        Question q100 = new Question("fiGSDywrX1Y", "눈","snow","눈(SNOW) (feat.이문세) - Zion.T",2017, "ㄴ / s**w","Zion.T");	addQuestion(q100);
        Question q101 = new Question("0wlXaHmmOVc", "기억의빈자리","emptinessinmemory","기억의 빈자리 (Emptiness in Memory) - 나얼 (Naul)",2017, "ㄱㅇㅇ ㅂㅈㄹ / e**t**e** i* m**o**","나얼 (Naul)");	addQuestion(q101);
        Question q102 = new Question("vDxD4HwEFdY", "종소리","twinkle","종소리 (Twinkle) - 러블리즈(Lovelyz)",2017, "ㅈㅅㄹ / t**n**e","러블리즈(Lovelyz)");	addQuestion(q102);
        Question q103 = new Question("Ue9NG1hAr78", "나로말할것같으면","yesiam","나로 말할 것 같으면 (Yes I am) - 마마무 (MAMAMOO)",2017, "ㄴㄹ ㅁㅎ ㄱ ㄱㅇㅁ / y** i a*","마마무 (MAMAMOO)");	addQuestion(q103);
        Question q104 = new Question("r1afdZk0qcI", "드라마라마","dramarama","DRAMARAMA - 몬스타엑스(MONSTA X)",2017, "ㄷㄹㅁㄹㅁ / d**m**a**","몬스타엑스(MONSTA X)");	addQuestion(q104);
        Question q105 = new Question("f5Zedh_5DDM", "아름다워","beautiful","아름다워(Beautiful) - 몬스타엑스(MONSTA X)",2017, "ㅇㄹㄷㅇ / b**u**f**","몬스타엑스(MONSTA X)");	addQuestion(q105);
        Question q106 = new Question("dkdoawhm0i4", "비행운","contrail","비행운 (CONTRAIL) - 문문 (MOONMOON)",2017, "ㅂㅎㅇ / c**t**i*","문문 (MOONMOON)");	addQuestion(q106);
        Question q107 = new Question("3q22SInyiX8", "예스터데이","yesterday","YESTERDAY - 블락비 (Block B)",2017, "ㅇㅅㅌㄷㅇ / y**t**d**","블락비 (Block B)");	addQuestion(q107);
        Question q108 = new Question("NHglTopVlKQ", "쉘위댄스","shallwedance","Shall We Dance - 블락비 (Block B)",2017, "ㅅ ㅇ ㄷㅅ / s**l* w* d**c*","블락비 (Block B)");	addQuestion(q108);
        Question q109 = new Question("hvX3wWKOEa8", "터치","touch","터치 (TOUCH) - 신화SHINHWA",2017, "ㅌㅊ / t**c*","신화SHINHWA");	addQuestion(q109);
        Question q110 = new Question("sZVB_zCBlCU", "해피","happy","HAPPY - 우주소녀(WJSN)",2017, "ㅎㅍ / h**p*","우주소녀(WJSN)");	addQuestion(q110);
        Question q111 = new Question("CArrDsjdhbs", "첫눈","thefirstsnow","첫 눈 (The first snow) - 정준일 (Jung Joonil)",2017, "ㅊ ㄴ / t** f**s* s**w","정준일 (Jung Joonil)");	addQuestion(q111);
        Question q112 = new Question("obzb7nlpXZ0", "아티스트","artist","Artist - 지코 (ZICO)",2017, "ㅇㅌㅅㅌ / a**i**","지코 (ZICO)");	addQuestion(q112);
        Question q113 = new Question("vvkWaI91mLM", "널너무모르고","dontknowyou","널 너무 모르고 (Don't know you) - 헤이즈 (Heize)",2017, "ㄴ ㄴㅁ ㅁㄹㄱ / d**t k**w y**","헤이즈 (Heize)");	addQuestion(q113);
        Question q114 = new Question("ntWSnDV0MYs", "빙글뱅글","binglebangle","빙글뱅글 (Bingle Bangle) - AOA",2018, "ㅂㄱㅂㄱ / b**g** b**g**","AOA");	addQuestion(q114);
        Question q115 = new Question("F4oHuML9U2A", "일도없어","imsosick","1도 없어 (I'm so sick) - Apink(에이핑크)",2018, "ㅇㄷ ㅇㅇ / i* s* s**k","Apink(에이핑크)");	addQuestion(q115);
        Question q116 = new Question("VWqxvBQKwKQ", "꽃길","flowerroad","꽃 길 (Flower Road) - BIGBANG",2018, "ㄲ ㄱ / f**w** r**d","BIGBANG");	addQuestion(q116);
        Question q117 = new Question("IHNzOHi8sJs", "뚜두뚜두","ddududdudu","뚜두뚜두 (DDU-DU DDU-DU) - BLACKPINK",2018, "ㄸㄷㄸㄷ / d**d* d**d*","BLACKPINK");	addQuestion(q117);
        Question q119 = new Question("xRbPAVnqtcs", "여행","travel","여행 (Travel) - BOL4(볼빨간사춘기)",2018, "ㅇㅎ / t**v**","BOL4(볼빨간사춘기)");	addQuestion(q119);
        Question q120 = new Question("fHQkdIGue3k", "너없인안된다","onlyoneforme","너 없인 안 된다 (Only one for me) - BTOB(비투비)",2018, "ㄴ ㅇㅇ ㅇ ㄷㄷ / o**y o** f** m*","BTOB(비투비)");	addQuestion(q120);
        Question q121 = new Question("7C2z4GqqS5E", "페이크러브","fakelove","FAKE LOVE - BTS (방탄소년단)",2018, "ㅍㅇㅋ ㄹㅂ / f**e l**e","BTS (방탄소년단)");	addQuestion(q121);
        Question q122 = new Question("pBuZEGYXA6E", "아이돌","idol","IDOL - BTS (방탄소년단)",2018, "ㅇㅇㄷ / i**l","BTS (방탄소년단)");	addQuestion(q122);
        Question q123 = new Question("pSudEWBAYRE", "러브샷","loveshot","Love Shot - EXO (엑소)",2018, "ㄹㅂ ㅅ / l**e s**t","EXO (엑소)");	addQuestion(q123);
        Question q124 = new Question("iwd8N6K-sLk", "템포","tempo","Tempo - EXO (엑소)",2018, "ㅌㅍ / t**p*","EXO (엑소)");	addQuestion(q124);
        Question q125 = new Question("Ib674A1yMtg", "화요일","bloomingday","花요일 (Blooming Day) - EXO-CBX (첸백시)",2018, "ㅎㅇㅇ / b**o**n* d**","EXO-CBX (첸백시)");	addQuestion(q125);
        Question q126 = new Question("_XyBa8QsVQU", "밤","timeforthemoonnight","밤 (Time for the moon night) - GFRIEND(여자친구)",2018, "ㅂ / t**e f** t** m**n**g**","GFRIEND(여자친구)");	addQuestion(q126);
        Question q127 = new Question("9RUeTYiJCyA", "룰라비","lullaby","Lullaby - GOT7(갓세븐)",2018, "ㄹㄹㅂ / l**l**y","GOT7(갓세븐)");	addQuestion(q127);
        Question q128 = new Question("sS0LCjOiIhc", "룩","look","Look - GOT7(갓세븐)",2018, "ㄹ / l**k","GOT7(갓세븐)");	addQuestion(q128);
        Question q129 = new Question("RtRtLf84I2M", "미라클","miracle","Miracle - GOT7(갓세븐)",2018, "ㅁㄹㅋ / m**a**e","GOT7(갓세븐)");	addQuestion(q129);
        Question q130 = new Question("RLpmz17IMEI", "별그대","theonlystar","별, 그대 (The Only Star) - HWANG CHI YEUL(황치열)",2018, "ㅂ ㄱㄷ / t** o**y s**r","HWANG CHI YEUL(황치열)");	addQuestion(q130);
        Question q131 = new Question("vecSVX1QYbQ", "사랑을했다","lovescenario","사랑을 했다(LOVE SCENARIO) - iKON",2018, "ㅅㄹㅇ ㅎㄷ / l**e s**n**i*","iKON");	addQuestion(q131);
        Question q132 = new Question("2O6dRaBbFoo", "이별길","goodbyeroad","이별길(GOODBYE ROAD) - iKON",2018, "ㅇㅂㄱ / g**d**e r**d","iKON");	addQuestion(q132);
        Question q133 = new Question("nM0xDI5R50E", "삐삐","bbibbi","삐삐 (BBIBBI) - IU(아이유)",2018, "ㅃㅃ / b**b**","IU(아이유)");	addQuestion(q133);
        Question q134 = new Question("b73BI9eUkjM", "솔로","solo","SOLO - JENNIE",2018, "ㅅㄹ / s**o","JENNIE");	addQuestion(q134);
        Question q135 = new Question("jt3Vdwrbhig", "트루러브","truelove","True Love - Kim Sung Kyu (김성규)",2018, "ㅌㄹ ㄹㅂ / t**e l**e","Kim Sung Kyu (김성규)");	addQuestion(q135);
        Question q136 = new Question("YfQzz00Oc_M", "시간이들겠지","ittakestime","시간이 들겠지 (It Takes Time) - Loco(로꼬)",2018, "ㅅㄱㅇ ㄷㄱㅈ / i* t**e* t**e","Loco(로꼬)");	addQuestion(q136);
        Question q137 = new Question("NY8VGNft-Zc", "아낙네","fiance","아낙네 (FIANCÉ) - MINO(송민호)",2018, "ㅇㄴㄴ / f**n**","MINO(송민호)");	addQuestion(q137);
        Question q138 = new Question("JQGRg8XBnB4", "뿜뿜","bboombboom","뿜뿜 (BBoom Bboom) - MOMOLAND (모모랜드)",2018, "ㅃㅃ / b**o* b**o*","MOMOLAND (모모랜드)");	addQuestion(q138);
        Question q139 = new Question("txWmd7QKFe8", "뱀","baam","BAAM - MOMOLAND (모모랜드)",2018, "ㅂ / b**m","MOMOLAND (모모랜드)");	addQuestion(q139);
        Question q140 = new Question("MS10Zz49FHE", "슛아웃","shootout","Shoot Out - MONSTA X 몬스타엑스",2018, "ㅅ ㅇㅇ / s**o* o**","MONSTA X 몬스타엑스");	addQuestion(q140);
        Question q141 = new Question("A8-ENEqhQjg", "모든날모든순간","everydayeverymoment","모든 날, 모든 순간 (Every day, Every Moment) - Paul Kim(폴킴)",2018, "ㅁㄷ ㄴ ㅁㄷ ㅅㄱ / e**r* d** e**r* m**e**","Paul Kim(폴킴)");	addQuestion(q141);
        Question q142 = new Question("YBzJ0jmHv-4", "너를만나","meafteryou","너를 만나 (Me After You) - Paul Kim(폴킴)",2018, "ㄴㄹ ㅁㄴ / m* a**e* y**","Paul Kim(폴킴)");	addQuestion(q142);
        Question q143 = new Question("aiHSVQy9xN8", "파워업","powerup","Power Up - Red Velvet (레드벨벳)",2018, "ㅍㅇ ㅇ / p**e* u*","Red Velvet (레드벨벳)");	addQuestion(q143);
        Question q144 = new Question("J_CFBjAyPWE", "배드보이","badboy","Bad Boy - Red Velvet (레드벨벳)",2018, "ㅂㄷ ㅂㅇ / b** b**","Red Velvet (레드벨벳)");	addQuestion(q144);
        Question q145 = new Question("TNWMZIf7eSg", "사이렌","siren","사이렌 (Siren) - SUNMI(선미)",2018, "ㅅㅇㄹ / s**e*","SUNMI(선미)");	addQuestion(q145);
        Question q146 = new Question("Fm5iP0S1z9w", "댄스더나이트어웨이","dancethenightaway","Dance The Night Away - TWICE(트와이스)",2018, "ㄷㅅ ㄷ ㄴㅇㅌ ㅇㅇㅇ / d**c* t** n**h* a**y","TWICE(트와이스)");	addQuestion(q146);
        Question q147 = new Question("i0p1bmr0EmE", "왓이즈러브","whatislove","What is Love? - TWICE(트와이스)",2018, "ㅇ ㅇㅈ ㄹㅂ / w**t i* l**e","TWICE(트와이스)");	addQuestion(q147);
        Question q148 = new Question("mAKsZ26SabQ", "예스올예스","yesoryes","YES or YES - TWICE(트와이스)",2018, "ㅇㅅ ㅇ ㅇㅅ / y** o* y**","TWICE(트와이스)");	addQuestion(q148);
        Question q149 = new Question("pmMjkMtpnTc", "부메랑","boomerang","부메랑 (BOOMERANG) - Wanna One (워너원)",2018, "ㅂㅁㄹ / b**m**a**","Wanna One (워너원)");	addQuestion(q149);
        Question q150 = new Question("FD2mik4V5EE", "봄바람","springbreeze","봄바람 (Spring Breeze) - Wanna One (워너원)",2018, "ㅂㅂㄹ / s**i** b**e**","Wanna One (워너원)");	addQuestion(q150);
        Question q151 = new Question("nqMYG2Riq54", "멋지게인사하는법","hellotutorial","멋지게 인사하는 법(Hello Tutorial) - Zion.T",2018, "ㅁㅈㄱ ㅇㅅㅎㄴ ㅂ / h**l* t**o**a*","Zion.T");	addQuestion(q151);
        Question q152 = new Question("IB6kViGA3rY", "주지마","abovelive","주지마 (Above Live) - 로꼬 (Loco), 화사 (마마무)",2018, "ㅈㅈㅁ / a**v* l**e","로꼬 (Loco), 화사 (마마무)");	addQuestion(q152);
        Question q153 = new Question("SkN_hWI6n28", "그때헤어지면돼","onlythen","그때 헤어지면 돼 (Only then) - 로이킴 (Roy Kim)",2018, "ㄱㄸ ㅎㅇㅈㅁ ㄷ / o**y t**n","로이킴 (Roy Kim)");	addQuestion(q153);
        Question q154 = new Question("fB406grTgz4", "우리그만하자","thehardestpart","우리 그만하자 (The Hardest Part) - 로이킴 (Roy Kim)",2018, "ㅇㄹ ㄱㅁㅎㅈ / t** h**d**t p**t","로이킴 (Roy Kim)");	addQuestion(q154);
        Question q155 = new Question("0FB2EoKTK_Q", "별이빛나는밤","starrynight","별이 빛나는 밤 (Starry Night) - 마마무 (MAMAMOO)",2018, "ㅂㅇ ㅂㄴㄴ ㅂ / s**r** n**h*","마마무 (MAMAMOO)");	addQuestion(q155);
        Question q156 = new Question("pHtxTSiPh5I", "너나해","egotistic","너나 해 (Egotistic) - 마마무 (MAMAMOO)",2018, "ㄴㄴ ㅎ / e**t**t**","마마무 (MAMAMOO)");	addQuestion(q156);
        Question q157 = new Question("rDG9I9nx0QU", "180도","180degree","180도 (180 Degree) - 벤 (BEN)",2018, "180ㄷ / 180d**r**","벤 (BEN)");	addQuestion(q157);
        Question q158 = new Question("WBahioCWfFQ", "열애중","loveing","열애중 Love, ing - 벤 (BEN)",2018, "ㅇㅇㅈ / l**e**g","벤 (BEN)");	addQuestion(q158);
        Question q159 = new Question("amOSaNX7KJg", "웨이백홈","waybackhome","웨이백홈 (Way Back Home) - 숀 (SHAUN)",2018, "ㅇㅇ ㅂ ㅎ / w** b**k h**e","숀 (SHAUN)");	addQuestion(q159);
        Question q160 = new Question("TM-gCW45YHc", "텔미","tellme","Tell Me - 인피니트(INFINITE)",2018, "ㅌ ㅁ / t**l m*","인피니트(INFINITE)");	addQuestion(q160);
        Question q161 = new Question("pW0jpmncut4", "하루도그대를사랑하지않은적이없었다","therehasneverbeenadayihaventlovedyou","하루도 그대를 사랑하지 않은 적이 없었다(There has never been a day I haven't loved you) - 임창정",2018, "ㅎㄹㄷ ㄱㄷㄹ ㅅㄹㅎㅈ ㅇㅇ ㅈㅇ ㅇㅇㄷ / t**r* h** n**e* b**n a d** i h**e** l**e* y**","임창정");	addQuestion(q161);
        Question q162 = new Question("Vl1kO9hObpA", "소울메이트","soulmate","SoulMate - 지코 (ZICO)",2018, "ㅅㅇㅁㅇㅌ / s**l**t*","지코 (ZICO)");	addQuestion(q162);
        Question q163 = new Question("mtLgabce8KQ", "러브유","loveu","Love U - 청하 (CHUNG HA)",2018, "ㄹㅂ  ㅇ / l**e u","청하 (CHUNG HA)");	addQuestion(q163);
        Question q164 = new Question("uw_HR9jIJww", "젠가","jenga","Jenga (Feat. Gaeko) - 헤이즈 (Heize)",2018, "ㅈㄱ / j**g*","헤이즈 (Heize)");	addQuestion(q164);
        Question q165 = new Question("1YZzSkP6KZo", "첫겨울이니까","firstwinter","첫 겨울이니까 (First Winter) - SUNG SI KYUNG(성시경), IU(아이유)",2019, "ㅊ ㄱㅇㅇㄴㄲ / f**s* w**t**"," SUNG SI KYUNG(성시경), IU(아이유)");	addQuestion(q165);
        Question q166 = new Question("2cevbhEqQF4", "세뇨리따","senorita","Senorita - (여자)아이들((G)I-DLE)",2019, "ㅅㄴㄹㄸ / s**o**t*","(여자)아이들((G)I-DLE)");	addQuestion(q166);
        Question q167 = new Question("6oanIo_2Z4Q", "라이언","lion","LION - (여자)아이들((G)I-DLE)",2019, "ㄹㅇㅇ / l**n","(여자)아이들((G)I-DLE)");	addQuestion(q167);
        Question q168 = new Question("I66oFXdf0KU", "우오","uhoh","Uh-Oh - (여자)아이들((G)I-DLE)",2019, "ㅇㅇ / u* o*","(여자)아이들((G)I-DLE)");	addQuestion(q168);
        Question q169 = new Question("hNnoi32CyrA", "그러나","however","그러나 (however) - 10cm",2019, "ㄱㄹㄴ / h**e**r","10cm");	addQuestion(q169);
        Question q170 = new Question("m3DZsBw5bnE", "어떻게이별까지사랑하겠어널사랑하는거지","howcanilovetheheartbreakyouretheoneilove","어떻게 이별까지 사랑하겠어, 널 사랑하는 거지(How can I love the heartbreak, you`re the one I love) - AKMU",2019, "ㅇㄸㄱ ㅇㅂㄲㅈ ㅅㄹㅎㄱㅇ ㄴ ㅅㄹㅎㄴ ㄱㅈ / h** c** i l**e t** h**r**r**k y**r* t** o** i l**e","AKMU");	addQuestion(q170);
        Question q171 = new Question("499YUeNoYVE", "응응","eungeung","응응(%%)(Eung Eung) - Apink(에이핑크)",2019, "ㅇㅇ / e**g e**g","Apink(에이핑크)");	addQuestion(q171);
        Question q172 = new Question("2S24-y0Ij3Y", "킬디스러브","killthislove","Kill This Love - BLACKPINK",2019, "ㅋ ㄷㅅ ㄹㅂ / k**l t**s l**e","BLACKPINK");	addQuestion(q172);
        Question q173 = new Question("mrAIqeULUL0", "워커홀릭","workaholic","워커홀릭 (Workaholic) - BOL4(볼빨간사춘기)",2019, "ㅇㅋㅎㄹ / w**k**o**c","BOL4(볼빨간사춘기)");	addQuestion(q173);
        Question q174 = new Question("AsXxuIdpkWM", "나만봄","bom","나만, 봄 (Bom) - BOL4(볼빨간사춘기)",2019, "ㄴㅁ ㅂ / b*m","BOL4(볼빨간사춘기)");	addQuestion(q174);
        Question q175 = new Question("XsX3ATc3FbA", "작은것들을위한시","boywithluv","작은 것들을 위한 시 (Boy With Luv) - BTS (방탄소년단)",2019, "ㅈㅇ ㄱㄷㅇ ㅇㅎ ㅅ / b** w**h l**","BTS (방탄소년단)");	addQuestion(q175);
        Question q176 = new Question("OoMIAo0a2TA", "나빠","nappa","나빠 (NAPPA) - Crush(크러쉬)",2019, "ㄴㅃ / n**p*","Crush(크러쉬)");	addQuestion(q176);
        Question q177 = new Question("47Vz-ptyKbQ", "술이달다","lovedrunk","술이 달다 (LOVEDRUNK) - EPIK HIGH (에픽하이)",2019, "ㅅㅇ ㄷㄷ / l**e d**n*","EPIK HIGH (에픽하이)");	addQuestion(q177);
        Question q178 = new Question("uxmP4b2a0uY", "옵세션","obsession","Obsession - EXO (엑소)",2019, "ㅇㅅㅅ / o**e**i**","EXO (엑소)");	addQuestion(q178);
        Question q179 = new Question("6tl-MG38-0E", "이클립스","eclipse","ECLIPSE - GOT7(갓세븐)",2019, "ㅇㅋㄹㅅ / e**i**e","GOT7(갓세븐)");	addQuestion(q179);
        Question q180 = new Question("ScSn235gQx0", "멍청이","twit","멍청이 (TWIT) - Hwa Sa(화사)",2019, "ㅁㅊㅇ / t**t","Hwa Sa(화사)");	addQuestion(q180);
        Question q181 = new Question("Hi0skksGeRk", "포장마차","phocha","포장마차 (Phocha) - Inwook Hwang(황인욱)",2019, "ㅍㅈㅁㅊ / p**c**","Inwook Hwang(황인욱)");	addQuestion(q181);
        Question q182 = new Question("zndvqTc4P9I", "아이씨","icy","ICY - ITZY(있지)",2019, "ㅇㅇㅆ / i**","ITZY(있지)");	addQuestion(q182);
        Question q183 = new Question("pNfTK39k55U", "달라달라","dalladalla","달라달라(DALLA DALLA) - ITZY(있지)",2019, "ㄷㄹㄷㄹ / d**l* d**l*","ITZY(있지)");	addQuestion(q183);
        Question q184 = new Question("D1PvIWdJ8xo", "블루밍","blueming","블루밍 (Blueming) - IU(아이유)",2019, "ㅂㄹㅁ / b**e**n*","IU(아이유)");	addQuestion(q184);
        Question q185 = new Question("6eEZ7DJMzuk", "비올레타","violeta","비올레타 (Violeta) - IZ*ONE (아이즈원)",2019, "ㅂㅇㄹㅌ / v**l**a","IZ*ONE (아이즈원)");	addQuestion(q185);
        Question q186 = new Question("XcbEM7j_ARQ", "술이문제야","drunkonlove","술이 문제야 (Drunk On Love) - JANG HYEJIN (장혜진), YUN MIN SOO (윤민수)",2019, "ㅅㅇ ㅁㅈㅇ / d**n* o* l**e","JANG HYEJIN (장혜진), YUN MIN SOO (윤민수)");	addQuestion(q186);
        Question q187 = new Question("GdoNGNe5CSg", "주저하는연인들을위해","forloverswhohesitate","주저하는 연인들을 위해 (for lovers who hesitate) - JANNABI(잔나비)",2019, "ㅈㅈㅎㄴ ㅇㅇㄷㅇ ㅇㅎ / f** l**e** w** h**i**t*","JANNABI(잔나비)");	addQuestion(q187);
        Question q188 = new Question("oDJ4ct59NC4", "벌스데이","birthday","Birthday - JEON SOMI (전소미)",2019, "ㅂㅅㄷㅇ / b**t**a*","JEON SOMI (전소미)");	addQuestion(q188);
        Question q189 = new Question("QUXNXYB1tq0", "십이월이십오일의고백","mychristmaswish","십이월 이십오일의 고백 (My christmas wish) - Jung Seung Hwan(정승환)",2019, "ㅅㅇㅇ ㅇㅅㅇㅇㅇ ㄱㅂ / m* c**i**m** w**h","Jung Seung Hwan(정승환)");	addQuestion(q189);
        Question q190 = new Question("ir7G_H0LFJw", "띵","dding","띵 (DDING) - Jvcki Wai, Young B(영비), Osshun Gum(오션검), Han Yo Han(한요한)",2019, "ㄸ / d**n*","Jvcki Wai, Young B(영비), Osshun Gum(오션검), Han Yo Han(한요한)");	addQuestion(q190);
        Question q191 = new Question("vwYZZmLJ5Kk", "그때가좋았어","thedaywasbeautiful","그때가 좋았어 (The day was beautiful) - Kassy(케이시)",2019, "ㄱㄸㄱ ㅈㅇㅇ / t** d** w** b**u**f**","Kassy(케이시)");	addQuestion(q191);
        Question q192 = new Question("4Sd09Mruhnk", "솔직하게말해서나","tobehonest","솔직하게 말해서 나 (To be honest) - Kim na young(김나영)",2019, "ㅅㅈㅎㄱ ㅁㅎㅅ ㄴ / t* b* h**e**","Kim na young(김나영)");	addQuestion(q192);
        Question q193 = new Question("GQqyCeKf8rw", "누구없소","noone","누구 없소 (NO ONE) - LEE HI",2019, "ㄴㄱ ㅇㅅ / n* o**","LEE HI");	addQuestion(q193);
        Question q194 = new Question("ij0SQZcqnPU", "신청곡","songrequest","신청곡 (Song request) - LeeSoRa(이소라)",2019, "ㅅㅊㄱ / s**g r**u**t","LeeSoRa(이소라)");	addQuestion(q194);
        Question q195 = new Question("kXAvbHPdTZ0", "사랑에연습이있었다면","iftherewaspracticeinlove","사랑에 연습이 있었다면 (If there was practice in love) - Lim Jae Hyun(임재현)",2019, "ㅅㄹㅇ ㅇㅅㅇ ㅇㅇㄷㅁ / i* t**r* w** p**c**c* i* l**e","Lim Jae Hyun(임재현)");	addQuestion(q195);
        Question q196 = new Question("1KFQdzSbbKA", "조금취했어","imalittledrunk","조금 취했어 (I'm a little drunk) - Lim Jae Hyun(임재현)",2019, "ㅈㄱ ㅊㅎㅇ / i* a l**t** d**n*","Lim Jae Hyun(임재현)");	addQuestion(q196);
        Question q197 = new Question("FZq-0Omm22s", "사계","onedayonly","사계 (One Day Only) - M.C THE MAX(엠씨더맥스)",2019, "ㅅㄱ / o** d** o**y","M.C THE MAX(엠씨더맥스)");	addQuestion(q197);
        Question q198 = new Question("Cp56JdkmE9s", "고고베베","gogobebe","고고베베 (gogobebe) - 마마무 (MAMAMOO)",2019, "ㄱㄱㅂㅂ / g**o**b*","마마무 (MAMAMOO)");	addQuestion(q198);
        Question q199 = new Question("tlHTOlnPcbs", "사랑이식었다고말해도돼","mylovehasfadedaway","사랑이 식었다고 말해도 돼 (My love has faded away) - Monday Kiz(먼데이 키즈)",2019, "ㅅㄹㅇ ㅅㅇㄷㄱ ㅁㅎㄷ ㄷ / m* l**e h** f**e* a**y","Monday Kiz(먼데이 키즈)");	addQuestion(q199);
        Question q200 = new Question("3C3hIJg4rHo", "엘리게이터","alligator","Alligator - MONSTA X 몬스타엑스",2019, "ㅇㄹㄱㅇㅌ / a**i**t**","MONSTA X 몬스타엑스");	addQuestion(q200);
        Question q201 = new Question("VpaUh_BGqE0", "옥탑방","rooftop","옥탑방 (Rooftop) - N.Flying(엔플라잉)",2019, "ㅇㅌㅂ / r**f**p","N.Flying(엔플라잉)");	addQuestion(q201);
        Question q202 = new Question("x95oZNxW5Rc", "슈퍼휴먼","superhuman","Superhuman - NCT 127 (엔시티 127)",2019, "ㅅㅍㅎㅁ / s**e* h**a*","NCT 127 (엔시티 127)");	addQuestion(q202);
        Question q203 = new Question("kFhf7pjRRjA", "늦은밤너의집앞골목길에서","latenight","늦은 밤 너의 집 앞 골목길에서 (Late Night) - Noel(노을)",2019, "ㄴㅇ ㅂ ㄴㅇ ㅈ ㅇ ㄱㅁㄱㅇㅅ / l**e n**h*","Noel(노을)");	addQuestion(q203);
        Question q204 = new Question("udGwca1HBM4", "다섯번째계절","thefifthseason","다섯 번째 계절 (The fifth season) - OH MY GIRL(오마이걸)",2019, "ㄷㅅ ㅂㅉ ㄱㅈ / t** f**t* s**s**","OH MY GIRL(오마이걸)");	addQuestion(q204);
        Question q205 = new Question("Xp8Ep1W-azw", "굿바이","goodbye","굿바이 (Goodbye) - Park Hyo Shin(박효신) ",2019, "ㄱㅂㅇ / g**d**e","Park Hyo Shin(박효신) ");	addQuestion(q205);
        Question q206 = new Question("Y99b-ITzPU4", "허전해","empty","허전해 (empty) - Paul Kim(폴킴)",2019, "ㅎㅈㅎ / e**t*","Paul Kim(폴킴)");	addQuestion(q206);
        Question q207 = new Question("fJubafP3IMI", "가끔이러다","sometimes","가끔 이러다 (Sometimes) - Punch(펀치)",2019, "ㄱㄲ ㅇㄹㄷ / s**e**m**","Punch(펀치)");	addQuestion(q207);
        Question q208 = new Question("YBnGBb1wg98", "짐살라빔","zimzalabim","짐살라빔 (Zimzalabim) - Red Velvet (레드벨벳)",2019, "ㅈㅅㄹㅂ /z**z**a**m","Red Velvet (레드벨벳)");	addQuestion(q208);
        Question q209 = new Question("vHS9E6JFja8", "음파음파","umpahumpah","음파음파 (Umpah Umpah) - Red Velvet (레드벨벳)",2019, "ㅇㅍㅇㅍ /u**a* u**a*","Red Velvet (레드벨벳)");	addQuestion(q209);
        Question q210 = new Question("uR8Mrt1IpXg", "싸이코","psycho","Psycho - Red Velvet (레드벨벳)",2019, "ㅆㅇㅋ / p**c**","Red Velvet (레드벨벳)");	addQuestion(q210);
        Question q211 = new Question("LrggmyDhWBo", "날라리","lalalay","날라리 (LALALAY) - SUNMI(선미)",2019, "ㄴㄹㄹ / l**a**y","SUNMI(선미)");	addQuestion(q211);
        Question q213 = new Question("4HG_CJzyX6A", "사계","fourseasons","사계 (Four Seasons) - TAEYEON (태연)",2019, "ㅅㄱ / f**r s**s**s","TAEYEON (태연)");	addQuestion(q213);
        Question q214 = new Question("eP4ga_fNm-E", "불티","spark","불티 (Spark) - TAEYEON (태연)",2019, "ㅂㅌ / s**r*","TAEYEON (태연)");	addQuestion(q214);
        Question q215 = new Question("A0gP4id3Gxc", "서울밤","seoulnight","서울 밤 (Seoul Night) - URBAN ZAKAPA(어반자카파)",2019, "ㅅㅇ ㅂ / s**u* n**h*","URBAN ZAKAPA(어반자카파)");	addQuestion(q215);
        Question q216 = new Question("Pm0_G8Zl0ek", "아예","ahyeah","아예 (AH YEAH) - WINNER",2019, "ㅇㅇ / a* y**h","WINNER");	addQuestion(q216);
        Question q217 = new Question("PALjhRpnfbk", "밀리언스","millions","MILLIONS - WINNER",2019, "ㅁㄹㅇㅅ / m**l**n*","WINNER");	addQuestion(q217);
        Question q218 = new Question("gWHgpJ3m-x8", "비가내리는날에는","onarainyday","비가 내리는 날에는 (On A Rainy Day) - YOUNHA(윤하)",2019, "ㅂㄱ ㄴㄹㄴ ㄴㅇㄴ / o* a r**n* d**","YOUNHA(윤하)");	addQuestion(q218);
        Question q219 = new Question("_-QY40Reub8", "뭐해","whatareyouupto","뭐해 (What are you up to) - 강다니엘(KANGDANIEL)",2019, "ㅁㅎ / w**t a** y** u* t*","강다니엘(KANGDANIEL)");	addQuestion(q219);
        Question q220 = new Question("84R5AS0tfkQ", "너에게못했던내마지막말은","unspokenwords","너에게 못했던 내 마지막 말은 (Unspoken Words) - 다비치 (DAVICHI)",2019, "ㄴㅇㄱ ㅁㅎㄷ ㄴ ㅁㅈㅁ ㅁㅇ / u**p**e* w**d*","다비치 (DAVICHI)");	addQuestion(q220);
        Question q221 = new Question("j2aQ_NqeTNw", "괜찮아도괜찮아","thatsokay","괜찮아도 괜찮아 (That's okay) - 디오 (D.O.)",2019, "ㄱㅊㅇㄷ ㄱㅊㅇ / t**t* o**y","디오 (D.O.)");	addQuestion(q221);
        Question q222 = new Question("KhTeiaCezwM", "힙","hip","HIP - 마마무(MAMAMOO)",2019, "ㅎ / h**","마마무(MAMAMOO)");	addQuestion(q222);
        Question q223 = new Question("dmSUBdk4SY4", "오늘도빛나는너에게","toyoumylight","오늘도 빛나는 너에게(To You My Light) - 마크툽 (MAKTUB)",2019, "ㅇㄴㄷ ㅂㄴㄴ ㄴㅇㄱ / t* y** m* l**h*","마크툽 (MAKTUB)");	addQuestion(q223);
        Question q224 = new Question("5-SBT9AnVdk", "어나더데이","anotherday","Another Day - 먼데이 키즈(Monday Kiz), 펀치(Punch)",2019, "ㅇㄴㄷㄷㅇ / a**t**r d**","먼데이 키즈(Monday Kiz), 펀치(Punch)");	addQuestion(q224);
        Question q225 = new Question("DsouXE31I6k", "봄","spring","봄(Spring) - 박봄(PARK BOM)",2019, "ㅂ / s**i**","박봄(PARK BOM)");	addQuestion(q225);
        Question q226 = new Question("Z8E0apklL2w", "그건아마우리의잘못은아닐거야","maybeitsnotourfault","그건 아마 우리의 잘못은 아닐 거야 (Maybe It’s Not Our Fault) - 백예린 (Yerin Baek)",2019, "ㄱㄱ ㅇㅁ ㅇㄹㅇ ㅈㅁㅇ ㅇㄴㄱㅇ / m**b* i** n** o** f**l*","백예린 (Yerin Baek)");	addQuestion(q226);
        Question q227 = new Question("_ZkUb7iIOqQ", "스퀘어","square","Square(2017) - 백예린 (Yerin Baek)",2019, "ㅅㅋㅇ / s**a**","백예린 (Yerin Baek)");	addQuestion(q227);
        Question q228 = new Question("7tO_eJek6D8", "헤어져줘서고마워","thankyouforgoodbye","헤어져줘서 고마워 (Thank you for Goodbye) - 벤 (BEN)",2019, "ㅎㅇㅈㅈㅅ ㄱㅁㅇ / t**n* y** f** g**d**e","벤 (BEN)");	addQuestion(q228);
        Question q229 = new Question("AtNBhPxVwh0", "왓어라이프","whatalife","What a life - 세훈&찬열 (EXO-SC)",2019, "ㅇ ㅇ ㄹㅇㅍ / w**t a l**e","세훈&찬열 (EXO-SC)");	addQuestion(q229);
        Question q230 = new Question("SKAsHxi1Tlc", "있어희미하게","justus2","있어 희미하게 (Just us 2) - 세훈&찬열 (EXO-SC)",2019, "ㅇㅇ ㅎㅁㅎㄱ / j**t u* 2","세훈&찬열 (EXO-SC)");	addQuestion(q230);
        Question q231 = new Question("eMZmNisWFvM", "니소식","yourregards","니 소식 (Your regards) - 송하예 Ha Yea Song",2019, "ㄴ ㅅㅅ / y**r r**a**s","송하예 Ha Yea Song");	addQuestion(q231);
        Question q232 = new Question("TbPHPX3hSPA", "해야","sunrise","해야 (Sunrise) - GFRIEND(여자친구)",2019, "ㅎㅇ / s**r**e","GFRIEND(여자친구)");	addQuestion(q232);
        Question q233 = new Question("MBSpoTozBdg", "이노래가클럽에서나온다면","fireup","이 노래가 클럽에서 나온다면 (Fire up)  - 우디 (Woody)",2019, "ㅇ ㄴㄹㄱ ㅋㄹㅇㅅ ㄴㅇㄷㅁ / f**e u*","우디 (Woody)");	addQuestion(q233);
        Question q234 = new Question("YBEUXfT7_48", "흔들리는꽃들속에서네샴푸향이느껴진거야","yourshampooscentintheflowers","흔들리는 꽃들 속에서 네 샴푸향이 느껴진거야 (Your Shampoo Scent In The Flowers)  - 장범준 (Beom June Jang)",2019, "ㅎㄷㄹㄴ ㄲㄷㅅㅇㅅ ㄴ ㅅㅍㅎㅇ ㄴㄲㅈㄱㅇ / y**r s**m**o s**n* i* t** f**w**s","장범준 (Beom June Jang)");	addQuestion(q234);
        Question q235 = new Question("yOZNN5xrK2o", "당신과는천천히","everymomentwithyou","당신과는 천천히 (every moment with you) - 장범준 (Beom June Jang)",2019, "ㄷㅅㄱㄴ ㅊㅊㅎ / e**r* m**e** w**h y**","장범준 (Beom June Jang)");	addQuestion(q235);
        Question q236 = new Question("lOrU0MH0bMk", "메테오","meteor","METEOR - 창모 CHANGMO",2019, "ㅁㅌㅇ / m**e**","창모 CHANGMO");	addQuestion(q236);
        Question q237 = new Question("bsgBUM2Mnsw", "밴드","band","BAND - 창모 CHANGMO, Hash Swan, ASH ISLAND, 김효은 Keem Hyo-Eun",2019, "ㅂㄷ / b**d","창모 CHANGMO, Hash Swan, ASH ISLAND, 김효은 Keem Hyo-Eun");	addQuestion(q237);
        Question q238 = new Question("deV_DmHKwjc", "스내핑","snapping","Snapping - 청하 (CHUNG HA)",2019, "ㅅㄴㅍ / s**p**n*","청하 (CHUNG HA)");	addQuestion(q238);
        Question q239 = new Question("JrOrlhjIYVk", "사월이지나면우리헤어져요","beautifulgoodbye","사월이 지나면 우리 헤어져요 (Beautiful goodbye) - 첸 (CHEN)",2019, "ㅅㅇㅇ ㅈㄴㅁ ㅇㄹ ㅎㅇㅈㅇ / b**u**f** g**d**e","첸 (CHEN)");	addQuestion(q239);
        Question q240 = new Question("lHEOj3d7YS4", "그대라는시","allaboutyou","그대라는 시 (All about you) - 태연 (TAEYEON)",2019, "ㄱㄷㄹㄴ ㅅ / a** a**u* y**","태연 (TAEYEON)");	addQuestion(q240);
        Question q241 = new Question("PdDfuWJc9dA", "위올라이","wealllie","We All Lie - 하진 (Ha Jin)",2019, "ㅇ ㅇ ㄹㅇ / w* a** l**","하진 (Ha Jin)");	addQuestion(q241);
        Question q242 = new Question("lpka6ymCkIY", "떨어지는낙엽까지도","fallingleavesarebeautiful","떨어지는 낙엽까지도 (Falling Leaves are Beautiful) - 헤이즈 (Heize)",2019, "ㄸㅇㅈㄴ ㄴㅇㄲㅈㄷ / f**l**g l**v** a** b**u**f**","헤이즈 (Heize)");	addQuestion(q242);
        Question q243 = new Question("ByHNlfmmT-w", "위돈톡투게더","wedonttalktogether","We don't talk together - 헤이즈 (Heize)",2019, "ㅇ ㄷ ㅌ ㅌㄱㄷ / w* d**t t**k t**e**e*","헤이즈 (Heize)");	addQuestion(q243);
        Question q244 = new Question("U_dpIqCDcZk", "헤어지자","goodbye","헤어지자 - 휘인 (Whee In)",2019, "ㅎㅇㅈㅈ / g**d**e","휘인 (Whee In)");	addQuestion(q244);
        Question q245 = new Question("om3n2ni8luE", "오마이갓","ohmygod","Oh my god - (여자)아이들((G)I-DLE)",2020, "ㅇ ㅁㅇ ㄱ / o* m* g**","(여자)아이들((G)I-DLE)");	addQuestion(q245);
        Question q246 = new Question("HPQ5mqovXHo", "덤디덤디","dumdidumdi","덤디덤디 (DUMDi DUMDi) - (여자)아이들((G)I-DLE)",2020, "ㄷㄷㄷㄷ / d**d* d**d*","(여자)아이들((G)I-DLE)");	addQuestion(q246);
        Question q247 = new Question("WqzTRK5GPWQ", "덤더럼","dumhdurum","덤더럼(Dumhdurum) - Apink(에이핑크)",2020, "ㄷㄷㄹ / d**h**r**","Apink(에이핑크)");	addQuestion(q247);
        Question q248 = new Question("cO9DbwTF7rY", "라라리라라","lalalilala","LALALILALA - APRIL(에이프릴)",2020, "ㄹㄹㄹㄹㄹ / l**a**l**a","APRIL(에이프릴)");	addQuestion(q248);
        Question q249 = new Question("d7W56dwrTlE", "크레이지","crazy","Crazy - APRIL(에이프릴)",2020, "ㅋㄹㅇㅈ / c**z*","APRIL(에이프릴)");	addQuestion(q249);
        Question q250 = new Question("ioNng23DkIM", "하우유라이크댓","howyoulikethat","How You Like That - BLACKPINK",2020, "ㅎㅇ ㅇ ㄹㅇㅋ ㄷ / h** y** l**e t**t","BLACKPINK");	addQuestion(q250);
        Question q251 = new Question("dyRsYk0LyA8", "러브식걸즈","lovesickgirls","Lovesick Girls - BLACKPINK",2020, "ㄹㅂㅅ ㄱㅈ / l**e**c* g**l*","BLACKPINK");	addQuestion(q251);
        Question q252 = new Question("vRXZj0DzXIA", "아이스크림","icecream","Ice Cream (with Selena Gomez) - BLACKPINK",2020, "ㅇㅇㅅㅋㄹ / i**c**a*","BLACKPINK");	addQuestion(q252);
        Question q253 = new Question("L8UUYfe6-UA", "나비와고양이","leo","나비와 고양이 (Leo) - BOL4(볼빨간사춘기)",2020, "ㄴㅂㅇ ㄱㅇㅇ / l**","BOL4(볼빨간사춘기)");	addQuestion(q253);
        Question q254 = new Question("qfeoX17dav0", "품","hug","품 (Hug) - BOL4(볼빨간사춘기)",2020, "ㅍ / h**","BOL4(볼빨간사춘기)");	addQuestion(q254);
        Question q255 = new Question("gdZLi9oWNZg", "다이너마이트","dynamite","Dynamite - BTS (방탄소년단)",2020, "ㄷㅇㄴㅁㅇㅌ / d**a**t*","BTS (방탄소년단)");	addQuestion(q255);
        Question q256 = new Question("-5q5mZbe3V8", "라이프고즈온","lifegoeson","Life Goes On - BTS (방탄소년단)",2020, "ㄹㅇㅍ ㄱㅈ ㅇ / l**e g**s o*","BTS (방탄소년단)");	addQuestion(q256);
        Question q257 = new Question("mPVDGOVjRQ0", "온","on","ON - BTS (방탄소년단)",2020, "ㅇ / o*","BTS (방탄소년단)");	addQuestion(q257);
        Question q258 = new Question("EXJfWc4JciQ", "둘만의세상으로가","letusgo","둘만의 세상으로 가 (Let Us Go) - Crush(크러쉬)",2020, "ㄷㅁㅇ ㅅㅅㅇㄹ ㄱ / l** u* g*","Crush(크러쉬)");	addQuestion(q258);
        Question q259 = new Question("vAa8R_ze6ZI", "놓아줘","letmego","놓아줘 (with 태연) - Crush(크러쉬)",2020, "ㄴㅇㅈ / l** m* g*","Crush(크러쉬)");	addQuestion(q259);
        Question q260 = new Question("k8gx-C7GCGU", "좀비","zombie","Zombie - DAY6(데이식스)",2020, "ㅈㅂ / z**b**","DAY6(데이식스)");	addQuestion(q260);
        Question q261 = new Question("kx5TWKPE5sU", "교차로","crossroads","교차로 (Crossroads) - GFRIEND(여자친구)",2020, "ㄱㅊㄹ / c**s**o**s","GFRIEND(여자친구)");	addQuestion(q261);
        Question q262 = new Question("CQ7p6kYal5A", "밤하늘의저별처럼","midnight","밤하늘의 저 별처럼 (Midnight) - Heize(헤이즈), Punch(펀치)",2020, "ㅂㅎㄴㅇ ㅈ ㅂㅊㄹ / m**n**h*","Heize(헤이즈), Punch(펀치)");	addQuestion(q262);
        Question q263 = new Question("fE2h3lGlOsk", "워너비","wannabe","WANNABE - ITZY(있지)",2020, "ㅇㄴㅂ / w**n**e","ITZY(있지)");	addQuestion(q263);
        Question q264 = new Question("TgOu00Mf3kI", "에잇","eight","에잇 (eight) - IU(아이유)",2020, "ㅇㅇ / e**h*","IU(아이유)");	addQuestion(q264);
        Question q265 = new Question("euI-C1YONaU", "마음을드려요","giveyoumyheart","마음을 드려요 (Give You My Heart) - IU(아이유)",2020, "ㅁㅇㅇ ㄷㄹㅇ / g**e y** m* h**r*","IU(아이유)");	addQuestion(q265);
        Question q266 = new Question("nnVjsos40qk", "환상동화","secretstoryoftheswan","환상동화 (Secret Story of the Swan) - IZ*ONE (아이즈원)",2020, "ㅎㅅㄷㅎ / s**r** s**r* o* t** s**n","IZ*ONE (아이즈원)");	addQuestion(q266);
        Question q267 = new Question("eDEFolvLn0A", "피에스타","fiesta","FIESTA - IZ*ONE (아이즈원)",2020, "ㅍㅇㅅㅌ / f**s**","IZ*ONE (아이즈원)");	addQuestion(q267);
        Question q268 = new Question("9T_uq_HpfyQ", "가을밤에든생각","athoughtonanautumnnight","가을밤에 든 생각 (A thought on an autumn night) - JANNABI(잔나비)",2020, "ㄱㅇㅂㅇ ㄷ ㅅㄱ / a t**u**t o* a* a**u** n**h*","JANNABI(잔나비)");	addQuestion(q268);
        Question q269 = new Question("lBYyAQ99ZFI", "왓유웨이팅포","whatyouwaitingfor","What You Waiting For - JEON SOMI (전소미)",2020, "ㅇ ㅇ ㅇㅇㅌ ㅍ / w**t y** w**t**g f**","JEON SOMI (전소미)");	addQuestion(q269);
        Question q270 = new Question("x_JeRI2eK9o", "어웨이","away","AWay - Jeong Eun Ji(정은지)",2020, "ㅇㅇㅇ / a**y","Jeong Eun Ji(정은지)");	addQuestion(q270);
        Question q271 = new Question("tJQaUW36pMw", "눈누난나","nununana","눈누난나 (NUNU NANA) - Jessi (제시)",2020, "ㄴㄴㄴㄴ / n**u n**a","Jessi (제시)");	addQuestion(q271);
        Question q272 = new Question("sLqZxb4xjJw", "이제나만믿어요","trustinme","이제 나만 믿어요 (Trust in Me) - Lim Young Woong(임영웅)",2020, "ㅇㅈ ㄴㅁ ㅁㅇㅇ / t**s* i* m*","Lim Young Woong(임영웅)");	addQuestion(q272);
        Question q273 = new Question("3XUe2PsadEk", "처음처럼","bloom","처음처럼 (BLOOM) - M.C THE MAX(엠씨더맥스)",2020, "ㅊㅇㅊㄹ / b**o*","M.C THE MAX(엠씨더맥스)");	addQuestion(q273);
        Question q274 = new Question("hq9hcJIzB6w", "브이브이에스","vvs","VVS (Feat. JUSTHIS) - Miranni , Munchman , Khundi Panda , MUSHVENOM",2020, "ㅂㅇㅂㅇㅇㅅ / v**","Miranni , Munchman , Khundi Panda , MUSHVENOM");	addQuestion(q274);
        Question q275 = new Question("iDjQSdN_ig8", "살짝설렜어","nonstop","살짝 설렜어 (Nonstop) - OH MY GIRL(오마이걸)",2020, "ㅅㅉ ㅅㄹㅇ / n**s**p","OH MY GIRL(오마이걸)");	addQuestion(q275);
        Question q276 = new Question("oaRTMjLdiDw", "돌핀","dolphin","Dolphin - OH MY GIRL(오마이걸)",2020, "ㄷㅍ / d**p**n","OH MY GIRL(오마이걸)");	addQuestion(q276);
        Question q277 = new Question("rVXeArOQIs4", "어떻게지내","ineedyou","어떻게 지내 (I Need You) - OVAN(오반)",2020, "ㅇㄸㄱ ㅈㄴ / i**e* y**","OVAN(오반)");	addQuestion(q277);
        Question q278 = new Question("FEI_imQ1Eaw", "우리만남이","butillmissyou","우리 만남이 (But I'll Miss You) - Paul Kim(폴킴)",2020, "ㅇㄹ ㅁㄴㅇ / b** i** m**s y**","Paul Kim(폴킴)");	addQuestion(q278);
        Question q279 = new Question("dM5gMsePq-o", "러브쉽","loveship","Loveship - Paul Kim(폴킴), CHUNG HA(청하)",2020, "ㄹㅂㅅ / l**e**i*","Paul Kim(폴킴), CHUNG HA(청하)");	addQuestion(q279);
        Question q280 = new Question("6jzH6_KOsKk", "널디러브","nerdylove","Nerdy Love (Feat. 백예린 Yerin Baek) - pH-1",2020, "ㄴㄷ ㄹㅂ / n**d* l**e","pH-1");	addQuestion(q280);
        Question q281 = new Question("Ujb-gvqsoi0", "몬스터","monster","Monster - Red Velvet - IRENE & SEULGI",2020, "ㅁㅅㅌ / m**s**r","Red Velvet - IRENE & SEULGI");	addQuestion(q281);
        Question q282 = new Question("GTcM3qCeup0", "미래","future","미래 - Red Velvet (레드벨벳)",2020, "ㅁㄹ / f**u**","Red Velvet (레드벨벳)");	addQuestion(q282);
        Question q283 = new Question("PXE2Ykf8fXQ", "돈터치미","donttouchme","DON'T TOUCH ME - REFUND SISTERS(환불원정대)",2020, "ㄷ ㅌㅊ ㅁ / d**t t**c* m*","REFUND SISTERS(환불원정대)");	addQuestion(q283);
        Question q284 = new Question("jZfrmtU6B8k", "새드","sad","Sad - Sonnet Son(손승연)",2020, "ㅅㄷ / s**","Sonnet Son(손승연)");	addQuestion(q284);
        Question q285 = new Question("Ygrv55VRRas", "사랑하자","letslove","사랑, 하자 (Let’s Love) - SUHO 수호",2020, "ㅅㄹㅎㅈ / l**s l**e","SUHO 수호");	addQuestion(q285);
        Question q286 = new Question("bho0m505qVA", "내게들려주고싶은말","dearme","내게 들려주고 싶은 말 (Dear Me) - TAEYEON (태연)",2020, "ㄴㄱ ㄷㄹㅈㄱ ㅅㅇ ㅁ / d**r m*","TAEYEON (태연)");	addQuestion(q286);
        Question q287 = new Question("CM4CkVFmTds", "아이캔스탑미","icantstopme","I CAN'T STOP ME - TWICE(트와이스)",2020, "ㅇㅇ ㅋ ㅅㅌ ㅁ / i c**t s**p m*","TWICE(트와이스)");	addQuestion(q287);
        Question q288 = new Question("mH0_XpSHkZo", "모어앤모어","moreandmore","MORE & MORE - TWICE(트와이스)",2020, "ㅁㅇ ㅇ ㅁㅇ / m**e a** m**e","TWICE(트와이스)");	addQuestion(q288);
        Question q289 = new Question("uqmv4xBLt5k", "시작","startover","시작(Start Over) - 가호 (Gaho)",2020, "ㅅㅈ / s**r* o**r","가호 (Gaho)");	addQuestion(q289);
        Question q290 = new Question("YbVlhdoo13s", "내마음이움찔했던순간","themomentmyheart","내 마음이 움찔했던 순간 - 규현(KYUHYUN)",2020, "ㄴ ㅁㅇㅇ ㅇㅉㅎㄷ ㅅㄱ / t** m**e** m* h**r*","규현(KYUHYUN)");	addQuestion(q290);
        Question q291 = new Question("xYvO_mYfOfk", "그때그아인","somedaytheboy","그때 그 아인 (Someday, The Boy) - 김필 (Kim Feel)",2020, "ㄱㄸ ㄱ ㅇㅇ / s**e**y t** b**","김필 (Kim Feel)");	addQuestion(q291);
        Question q292 = new Question("MRrXRlVd0P0", "테스형","mrtes","테스형!(Mr.Tes!) - 나훈아",2020, "ㅌㅅㅎ / m* t**","나훈아");	addQuestion(q292);
        Question q293 = new Question("dfl9KIX1WpU", "딩가딩가","dingga","딩가딩가 (Dingga) - 마마무 (MAMAMOO)",2020, "ㄷㄱㄷㄱ / d**g**","마마무 (MAMAMOO)");	addQuestion(q293);
        Question q294 = new Question("9OADFEl-QQ0", "보자보자","letssee","보자보자 (Let's see) - 머쉬베놈 (MUSHVENOM)",2020, "ㅂㅈㅂㅈ / l**s s**","머쉬베놈 (MUSHVENOM)");	addQuestion(q294);
        Question q295 = new Question("tLGHKyZs0Gk", "너에게난나에게넌","metoyouyoutome","너에게 난, 나에게 넌 (Me to You, You to Me) - 미도와 파라솔 (Mido and Falasol)",2020, "ㄴㅇㄱ ㄴ ㄴㅇㄱ ㄴ / m* t* y** y** t* m*","미도와 파라솔 (Mido and Falasol)");	addQuestion(q295);
        Question q296 = new Question("zrsBjYukE8s", "웬위디스코","whenwedisco","When We Disco (Duet with 선미) - 박진영 (J.Y. Park)",2020, "ㅇ ㅇ ㄷㅅㅋ / w**n w* d**c*","박진영 (J.Y. Park)");	addQuestion(q296);
        Question q297 = new Question("FFmdTU4Cpr8", "다시난여기","hereiamagain","다시 난, 여기 (Here I Am Again) - 백예린 (Yerin Baek)",2020, "ㄷㅅ ㄴ ㅇㄱ / h**e i a* a**i*","백예린 (Yerin Baek)");	addQuestion(q297);
        Question q298 = new Question("XcpqxEysV4c", "사랑했던날들","thedaysweloved","사랑했던 날들 (The Days We Loved) - 백지영",2020, "ㅅㄹㅎㄷ ㄴㄷ / t** d**s w* l**e*","백지영");	addQuestion(q298);
        Question q299 = new Question("2COAu1l6hUQ", "취기를빌려","slightlytipsy","취기를 빌려 (Slightly Tipsy) - 산들",2020, "ㅊㄱㄹ ㅂㄹ / s**g**l* t**s*","산들");	addQuestion(q299);
        Question q300 = new Question("Is7glC9Jp7Q", "보라빛밤","pporappippam","보라빛 밤 (pporappippam) - 선미 (SUNMI)",2020, "ㅂㄹㅂ ㅂ / p**r**p**p**","선미 (SUNMI)");	addQuestion(q300);
        Question q301 = new Question("ESKfHHtiSjs", "다시여기바닷가","beachagain","다시 여기 바닷가(Beach Again) - 싹쓰리(SSAK3)",2020, "ㄷㅅ ㅇㄱ ㅂㄷㄱ / b**c* a**i*","싹쓰리(SSAK3)");	addQuestion(q301);
        Question q302 = new Question("AAOOKbk-knM", "숲의아이","bonvoyage","숲의 아이 (Bon voyage) - 유아(YooA)",2020, "ㅅㅇ ㅇㅇ / b** v**a**","유아(YooA)");	addQuestion(q302);
        Question q303 = new Question("VdeK_VsG9U0", "홀로","holo","홀로 (HOLO) - 이하이 (LEE HI)",2020, "ㅎㄹ / h**o","이하이 (LEE HI)");	addQuestion(q303);
        Question q304 = new Question("okxbk67WLnk", "어느60대노부부이야기","storyofoldcoupleintheir60s","어느 60대 노부부이야기 (Story of old couple in their 60s) - 임영웅(Lim Yeongung)",2020, "ㅇㄴ 60ㄷ ㄴㅂㅂ ㅇㅇㄱ / s**r* o* o** c**p** i* t**i* 60*","임영웅(Lim Yeongung)");	addQuestion(q304);
        Question q305 = new Question("MLymxEyC_Vo", "힘든건사랑이아니다","loveshouldnotbeharshonyou","힘든 건 사랑이 아니다 (Love should not be harsh on you) - 임창정",2020, "ㅎㄷ ㄱ ㅅㄹㅇ ㅇㄴㄷ / l**e s**u** n** b* h**s* o* y**","임창정");	addQuestion(q305);
        Question q306 = new Question("K0qKtn5VPUE", "잠이오질않네요","cantsleep","잠이 오질 않네요 (can't sleep) - 장범준 (Beom June Jang)",2020, "ㅈㅇ ㅇㅈ ㅇㄴㅇ / c**t s**e*","장범준 (Beom June Jang)");	addQuestion(q306);
        Question q307 = new Question("BfWqUjunXXU", "실버판테온","silverpantheon","실버판테온 (SILVERPantheon) - 장범준 (Beom June Jang)",2020, "ㅅㅂㅍㅌㅇ / s**v**p**t**o*","장범준 (Beom June Jang)");	addQuestion(q307);
        Question q308 = new Question("rOCymN-Rwiw", "사랑하게될줄알았어","iknewilove","사랑하게 될 줄 알았어 (I Knew I Love) - 전미도 (JEON MI DO)",2020, "ㅅㄹㅎㄱ ㄷ ㅈ ㅇㅇㅇ / i k**w i l**e","전미도 (JEON MI DO)");	addQuestion(q308);
        Question q309 = new Question("hoLzH1revMg", "좋은사람있으면소개시켜줘","introducemeagoodperson","좋은 사람 있으면 소개시켜줘 (Introduce me a good person) - 조이 (JOY)",2020, "ㅈㅇ ㅅㄹ ㅇㅇㅁ ㅅㄱㅅㅋㅈ / i**r**u** m* a g**d p**s**","조이 (JOY)");	addQuestion(q309);
        Question q310 = new Question("3DOkxQ3HDXE", "아로하","aloha","아로하 (Aloha) - 조정석 (CHO JUNG SEOK)",2020, "ㅇㄹㅎ / a**h*","조정석 (CHO JUNG SEOK)");	addQuestion(q310);
        Question q311 = new Question("oKUEbsJDvuo", "썸머헤이트","summerhate","Summer Hate (Feat. Rain(비)) - 지코 (ZICO)",2020, "ㅆㅁ ㅎㅇㅌ / s**m** h**e","지코 (ZICO)");	addQuestion(q311);
        Question q312 = new Question("UuV2BmJ1p_I", "아무노래","anysong","아무노래 (Any song) - 지코 (ZICO)",2020, "ㅇㅁ ㄴㄹ / a** s**g","지코 (ZICO)");	addQuestion(q312);
        Question q313 = new Question("YPFIh0dfYfw", "스테이 투나잇","staytonight","Stay Tonight - 청하 (CHUNG HA)",2020, "ㅅㅌㅇ ㅌㄴㅇ / s**y t**i**t","청하 (CHUNG HA)");	addQuestion(q313);
        Question q314 = new Question("4qOT_Aw9IgM", "돌덩이","diamond","돌덩이 (Diamond) - 하현우",2020, "ㄷㄷㅇ / d**m**d","하현우");	addQuestion(q314);
        Question q315 = new Question("brZRDjFIFJs", "마리아","maria","마리아 (Maria) - 화사 (Hwa Sa)",2020, "ㅁㄹㅇ / m**i*","화사 (Hwa Sa)");	addQuestion(q315);
        Question q316 = new Question("z3szNvgQxHo", "화","hwaa","화(火花)(HWAA) - (여자)아이들((G)I-DLE)",2021, "ㅎ / h**a","(여자)아이들((G)I-DLE)");	addQuestion(q316);
        Question q317 = new Question("X3PFu82F_S8", "고백","goback","고백 (Go Back) - 10cm",2021, "ㄱㅂ / g* b**k","10cm");	addQuestion(q317);
        Question q318 = new Question("-6aVQrzja9U", "해야해","makeit","해야 해 (Make it) - 2PM",2021, "ㅎㅇ ㅎ / m**e i*","2PM");	addQuestion(q318);
        Question q319 = new Question("WPdWvnAAurg", "새비지","savage","Savage - aespa (에스파)",2021, "ㅅㅂㅈ / s**a**","aespa (에스파)");	addQuestion(q319);
        Question q320 = new Question("EtiPbWzUY9o", "낙하","nakka","낙하 (NAKKA) (with IU) - AKMU",2021, "ㄴㅎ / n**k*","AKMU");	addQuestion(q320);
        Question q321 = new Question("32kd8oFHoAw", "애프터미드나잇","aftermidnight","After Midnight - ASTRO(아스트로)",2021, "ㅇㅍㅌ ㅁㄷㄴㅇ / a**e* m**n**h*","ASTRO(아스트로)");	addQuestion(q321);
        Question q322 = new Question("8M3WUaeIbOk", "밤비","bambi","Bambi - BAEKHYUN (백현)",2021, "ㅂㅂ / b**b*","BAEKHYUN (백현)");	addQuestion(q322);
        Question q323 = new Question("JrWQdHjOkvw", "나비효과","butterflyeffect","나비효과 (Butterfly Effect) - BOL4(볼빨간사춘기)",2021, "ㄴㅂㅎㄱ / b**t**f** e**e**","BOL4(볼빨간사춘기)");	addQuestion(q323);
        Question q324 = new Question("CuklIb9d3fI", "퍼미션투댄스","permissiontodance","Permission to Dance - BTS (방탄소년단)",2021, "ㅍㅁㅅ ㅌ ㄷㅅ / p**m**s**n t* d**c*","BTS (방탄소년단)");	addQuestion(q324);
        Question q325 = new Question("QMwJtMJLXE0", "스파이시","spicy","SPICY - CL",2021, "ㅅㅍㅇㅅ / s**c*","CL");	addQuestion(q325);
        Question q326 = new Question("_btxV8tJM7w", "로즈","rose","Rose - D.O. (디오)",2021, "ㄹㅈ / r**e","D.O. (디오)");	addQuestion(q326);
        Question q327 = new Question("N2p__-LRBNc", "뚫고지나가요","rightthroughme","뚫고 지나가요 (Right Through Me) - DAY6(데이식스)",2021, "ㄸㄱ ㅈㄴㄱㅇ / r**h* t**o**h m*","DAY6(데이식스)");	addQuestion(q327);
        Question q328 = new Question("PEKkdIT8JPM", "비커즈","because","BEcause - Dreamcatcher(드림캐쳐)",2021, "ㅂㅋㅈ / b**a**e","Dreamcatcher(드림캐쳐)");	addQuestion(q328);
        Question q329 = new Question("FCsLikmxhV0", "로사리오","rosario","Rosario - EPIK HIGH (에픽하이)",2021, "ㄹㅅㄹㅇ / r**a**o","EPIK HIGH (에픽하이)");	addQuestion(q329);
        Question q330 = new Question("ZcnnUoyv-hs", "내얘기같아","basedonatruestory","내 얘기 같아 (Based On A True Story) - EPIK HIGH (에픽하이)",2021, "ㄴ ㅇㄱ ㄱㅇ / b**e* o* a t**e s**r*","EPIK HIGH (에픽하이)");	addQuestion(q330);
        Question q331 = new Question("xBsHAyB73Rk", "페이스아이디","faceid","Face ID - EPIK HIGH (에픽하이)",2021, "ㅍㅇㅅ ㅇㅇㄷ / f**e i*","EPIK HIGH (에픽하이)");	addQuestion(q331);
        Question q332 = new Question("Z3RA7bi5FUM", "퍼스트","first","FIRST - EVERGLOW (에버글로우)",2021, "ㅍㅅㅌ / f**s*","EVERGLOW (에버글로우)");	addQuestion(q332);
        Question q333 = new Question("2IkoKhr6Tss", "돈파이트더필링","dontfightthefeeling","Don't fight the feeling - EXO (엑소)",2021, "ㄷ ㅍㅇㅌ ㄷ ㅍㄹ / d**t f**h* t** f**l**g","EXO (엑소)");	addQuestion(q333);
        Question q334 = new Question("5Kdl9uOmj34", "불어온다","nottheend","불어온다 (NOT THE END) - Highlight(하이라이트)",2021, "ㅂㅇㅇㄷ / n** t** e**","Highlight(하이라이트)");	addQuestion(q334);
        Question q335 = new Question("DslHQto2V7I", "왜왜왜","whywhywhy","왜왜왜 (Why Why Why) - iKON",2021, "ㅇㅇㅇ / w** w** w**","iKON");	addQuestion(q335);
        Question q336 = new Question("_ysomCGaZLw", "마피아인더모닝","mafiainthemorning","마.피.아. In the morning - ITZY(있지)",2021, "ㅁㅍㅇ ㅇ ㄷ ㅁㄴ / m**i* i* t** m**n**g","ITZY(있지)");	addQuestion(q336);
        Question q337 = new Question("MjCZfZfucEc", "로고","loco","LOCO - ITZY(있지)",2021, "ㄹㄱ / l**o","ITZY(있지)");	addQuestion(q337);
        Question q338 = new Question("0-q1KafFCLU", "셀러브리티","celebrity","Celebrity - IU(아이유)",2021, "ㅅㄹㅂㄹㅌ / c**e**i**","IU(아이유)");	addQuestion(q338);
        Question q339 = new Question("v7bnOxV4jAc", "라일락","lilac","라일락 (LILAC) - IU(아이유)",2021, "ㄹㅇㄹ / l**a*","IU(아이유)");	addQuestion(q339);
        Question q340 = new Question("86BST8NIpNM", "코인","coin","Coin - IU(아이유)",2021, "ㅋㅇ / c**n","IU(아이유)");	addQuestion(q340);
        Question q341 = new Question("tg2uF3R_Ozo", "덤덤","dumbdumb","DUMB DUMB - JEON SOMI (전소미)",2021, "ㄷㄷ / d**b d**b","JEON SOMI (전소미)");	addQuestion(q341);
        Question q342 = new Question("1JHOl9CSmXk", "콜드블러디드","coldblooded","Cold Blooded - Jessi (제시)",2021, "ㅋㄷ ㅂㄹㄷㄷ / c**d b**o**d","Jessi (제시)");	addQuestion(q342);
        Question q343 = new Question("OYWWnd9ACMI", "베드러브","badlove","BAD LOVE - KEY (키)",2021, "ㅂㄷ ㄹㅂ / b** l**e","KEY (키)");	addQuestion(q343);
        Question q344 = new Question("SK6Sm2Ki9tI", "신호등","trafficlight","Traffic light(신호등) - Lee Mujin(이무진)",2021, "ㅅㅎㄷ / t**f**c l**h*","Lee Mujin(이무진)");	addQuestion(q344);
        Question q345 = new Question("RIMZ0pZh2uk", "과제곡","theassignmentsong","과제곡 (The Assignment Song) - Lee Mujin(이무진)",2021, "ㄱㅈㄱ / t** a**i**m**t s**g","Lee Mujin(이무진)");	addQuestion(q345);
        Question q346 = new Question("awkkyBH2zEo", "라리사","lalisa","LALISA - LISA",2021, "ㄹㄹㅅ / l**i**","LISA");	addQuestion(q346);
        Question q347 = new Question("1oYWnbTSang", "스티커","sticker","Sticker - NCT 127 (엔시티 127)",2021, "ㅅㅌㅋ / s**c**r","NCT 127 (엔시티 127)");	addQuestion(q347);
        Question q348 = new Question("7uxu4Z2HAnA", "페이버릿","favorite","Favorite (Vampire) - NCT 127 (엔시티 127)",2021, "ㅍㅇㅂㄹ / f**o**t*","NCT 127 (엔시티 127)");	addQuestion(q348);
        Question q349 = new Question("PkKnp4SdE-w", "맛","hotsauce","맛 (Hot Sauce) - NCT DREAM (엔시티 드림)",2021, "ㅁ / h** s**c*","NCT DREAM (엔시티 드림)");	addQuestion(q349);
        Question q350 = new Question("QPUjV7epJqE", "헬로우퓨쳐","hellofuture","Hello Future - NCT DREAM (엔시티 드림)",2021, "ㅎㄹㅇ ㅍㅊ / h**l* f**u**","NCT DREAM (엔시티 드림)");	addQuestion(q350);
        Question q351 = new Question("AIyarEsQvAg", "인사이드아웃","insideout","INSIDE OUT - NU'EST (뉴이스트)",2021, "ㅇㅅㅇㄷ ㅇㅇ / i**i** o**","NU'EST (뉴이스트)");	addQuestion(q351);
        Question q352 = new Question("srWfDwiRVgQ", "와이돈위","whydontwe","WHY DON’T WE - RAIN (비)",2021, "ㅇㅇ ㄷ ㅇ / w** d**t w*","RAIN (비)");	addQuestion(q352);
        Question q353 = new Question("BUjI4XPPfh0", "썸머테이스트","summertaste","Summer Taste - RAIN,MONSTA X,BraveGirls,ATEEZ(비,몬스타엑스,브레이브걸스,에이티즈)",2021, "ㅆㅁ ㅌㅇㅅㅌ / s**m** t**t*","RAIN,MONSTA X,BraveGirls,ATEEZ(비,몬스타엑스,브레이브걸스,에이티즈)");	addQuestion(q353);
        Question q354 = new Question("c9RzZpV460k", "퀸덤","queendom","Queendom - Red Velvet (레드벨벳)",2021, "ㅋㄷ / q**e**o*","Red Velvet (레드벨벳)");	addQuestion(q354);
        Question q355 = new Question("5_Esp-dXcOA", "러브데이","loveday","LOVE DAY (2021) - Romance 101 X Yang Yoseop, Jeong Eun Ji (바른연애 길잡이 X 양요섭, 정은지)",2021, "ㄹㅂ ㄷㅇ / l**e d**","Romance 101 X Yang Yoseop, Jeong Eun Ji (바른연애 길잡이 X 양요섭, 정은지)");	addQuestion(q355);
        Question q356 = new Question("CKZvWhCqx1s", "온더그라운드","ontheground","On The Ground - ROSÉ",2021, "ㅇ ㄷ ㄱㄹㅇㄷ / o* t** g**u**","ROSÉ");	addQuestion(q356);
        Question q357 = new Question("yCvSR4lSqTg", "레디투러브","readytolove","Ready to love - SEVENTEEN (세븐틴)",2021, "ㄹㄷ ㅌ ㄹㅂ / r**d* t* l**e","SEVENTEEN (세븐틴)");	addQuestion(q357);
        Question q358 = new Question("WpuatuzSDK4", "락윗유","rockwithyou","Rock with you - SEVENTEEN (세븐틴)",2021, "ㄹ ㅇ ㅇ / r**k w**h y**","SEVENTEEN (세븐틴)");	addQuestion(q358);
        Question q359 = new Question("ck538udT0b8", "여전히아름다운지","isitstillbeautiful","여전히 아름다운지 (Is It Still Beautiful) - SEVENTEEN (세븐틴)",2021, "ㅇㅈㅎ ㅇㄹㄷㅇㅈ / i* i* s**l* b**u**f**","SEVENTEEN (세븐틴)");	addQuestion(q359);
        Question q360 = new Question("PSYRbJjIT6U", "아틀란티스","atlantis","Atlantis - SHINee (샤이니)",2021, "ㅇㅌㄹㅌㅅ / a**a**i*","SHINee (샤이니)");	addQuestion(q360);
        Question q361 = new Question("p6OoY6xneI0", "돈콜미","dontcallme","Don't Call Me - SHINee 샤이니",2021, "ㄷ ㅋ ㅁ / d**t c**l m*","SHINee 샤이니");	addQuestion(q361);
        Question q362 = new Question("NsY-9MCOIAQ", "에이셉","asap","ASAP - STAYC(스테이씨)",2021, "ㅇㅇㅅ / a**p","STAYC(스테이씨)");	addQuestion(q362);
        Question q363 = new Question("Xmxcnf2v_gs", "색안경","stereotype","색안경 (STEREOTYPE) - STAYC(스테이씨)",2021, "ㅅㅇㄱ / s**r**t**e","STAYC(스테이씨)");	addQuestion(q363);
        Question q364 = new Question("EaswWiwMVs8", "소리꾼","thunderous","소리꾼 (Thunderous) - Stray Kids(스트레이 키즈)",2021, "ㅅㄹㄲ / t**n**r**s","Stray Kids(스트레이 키즈)");	addQuestion(q364);
        Question q365 = new Question("BtJMOVKjhUo", "하우스파티","houseparty","House Party - SUPER JUNIOR (슈퍼주니어)",2021, "ㅎㅇㅅ ㅍㅌ / h**s* p**t*","SUPER JUNIOR (슈퍼주니어)");	addQuestion(q365);
        Question q366 = new Question("RmuL-BPFi2Q", "위켄드","weekend","Weekend - TAEYEON (태연)",2021, "ㅇㅋㄷ / w**k**d","TAEYEON (태연)");	addQuestion(q366);
        Question q367 = new Question("XMs2CIiqRDI", "스릴라이드","thrillride","THRILL RIDE - THE BOYZ(더보이즈)",2021, "ㅅㄹ ㄹㅇㄷ / t**i**r((e","THE BOYZ(더보이즈)");	addQuestion(q367);
        Question q368 = new Question("XA2YEHn-A8Q", "알콜프리","alcoholfree","Alcohol-Free - TWICE(트와이스)",2021, "ㅇㅋㅍㄹ / a**o**l**e*","TWICE(트와이스)");	addQuestion(q368);
        Question q369 = new Question("DZEOt4pQXXk", "홀리데이파티","holidayparty","Holiday Party - Weeekly(위클리)",2021, "ㅎㄹㄷㅇ ㅍㅌ / h**i**y p**t*","Weeekly(위클리)");	addQuestion(q369);
        Question q371 = new Question("FjbCwQ7hliw", "언내추럴","unnatural","UNNATURAL - WJSN(우주소녀)",2021, "ㅇㄴㅊㄹ / u**a**r**","WJSN(우주소녀)");	addQuestion(q371);
        Question q372 = new Question("zLrWIgkvoB0", "파라노이아","paranoia","PARANOIA - 강다니엘(KANGDANIEL)",2021, "ㅍㄹㄴㅇㅇ / p**a**i*","강다니엘(KANGDANIEL)");	addQuestion(q372);
        Question q373 = new Question("sfdJRW5NGyA", "안티도트","antidote","Antidote - 강다니엘(KANGDANIEL)",2021, "ㅇㅌㄷㅌ / a**i**t*","강다니엘(KANGDANIEL)");	addQuestion(q373);
        Question q374 = new Question("ORYgMXQ8Kqg", "그냥안아달란말야","justhugme","그냥 안아달란 말야 (Just hug me) - 다비치 (DAVICHI)",2021, "ㄱㄴ ㅇㅇㄷㄹ ㅁㅇ / j**t h** m*","다비치 (DAVICHI)");	addQuestion(q374);
        Question q375 = new Question("G_SmwQ06TQE", "링링","ringring","Ring Ring - 로켓펀치(Rocket Punch)",2021, "ㄹㄹ / r**g r**g","로켓펀치(Rocket Punch)");	addQuestion(q375);
        Question q376 = new Question("e70PkoJhQYM", "치맛바람","chimatbaram","치맛바람 (Chi Mat Ba Ram) - 브레이브걸스(Brave Girls)",2021, "ㅊㅁㅂㄹ / c** m** b* r**","브레이브걸스(Brave Girls)");	addQuestion(q376);
        Question q377 = new Question("Aui0ZKIaXVc", "꼬리","tail","꼬리(TAIL) - 선미 (SUNMI)",2021, "ㄲㄹ / t**l","선미 (SUNMI)");	addQuestion(q377);
        Question q378 = new Question("HzOjwL7IP_o", "던던댄스","dundundance","Dun Dun Dance - 오마이걸(OH MY GIRL)",2021, "ㄷ ㄷ ㄷㅅ / d** d** d**c*","오마이걸(OH MY GIRL)");	addQuestion(q378);
        Question q379 = new Question("sCmcSBsTxQc", "비와당신","rainandyou","비와 당신 (Rain and You) - 이무진 (Lee Mujin)",2021, "ㅂㅇ ㄷㅅ / r**n a** y**","이무진 (Lee Mujin)");	addQuestion(q379);
        Question q380 = new Question("WHkQtNBCRQo", "가을타나봐","fallinfall","가을 타나 봐 (Fall in Fall) - 이무진 (Lee Mujin)",2021, "ㄱㅇ ㅌㄴ ㅂ / f**l i* f**l","이무진 (Lee Mujin)");	addQuestion(q380);
        Question q381 = new Question("AJPLgrfBiBo", "헤픈우연","happen","헤픈 우연 (HAPPEN) - 헤이즈 (Heize)",2021, "ㅎㅍ ㅇㅇ / h**p**","헤이즈 (Heize)");	addQuestion(q381);
        Question q382 = new Question("fOdML_XhHvQ", "비가오는날엔","onrainyday","비가 오는 날엔 (2021)(On Rainy Day) - 헤이즈 (Heize)",2021, "ㅂㄱ ㅇㄴ ㄴㅇ / o* r**n* d**","헤이즈 (Heize)");	addQuestion(q382);
        Question q383 = new Question("_yXEnhyOTQo", "아임낫쿨","imnotcool","I'm Not Cool - 현아 (HyunA)",2021, "ㅇㅇ ㄴ ㅋ / i* n** c**l","현아 (HyunA)");	addQuestion(q383);
        Question q384 = new Question("nBxnh010LU8", "둘중에골라","summerorsummer","둘 중에 골라 (Summer or Summer) - 효린, 다솜 (HYOLYN, DASOM)",2021, "ㄷ ㅈㅇ ㄱㄹ / s**m** o* s**m**","효린, 다솜 (HYOLYN, DASOM)");	addQuestion(q384);
        Question q385 = new Question("ShFKF2YN7H0", "워터컬러","watercolor","water color - 휘인 (Whee In)",2021, "ㅇㅌ ㅋㄹ / w**e* c**o*","휘인 (Whee In)");	addQuestion(q385);
        Question q118 = new Question("-gibBYpzZbI", "포에버영","foreveryoung","Forever Young - BLACKPINK",2018, "ㅍㄹㅂ ㅇ / f**e**r y**n*","BLACKPINK");	addQuestion(q118);
        Question q212 = new Question("-OfOkiVFmhM", "원트","want","WANT - TAEMIN (태민)",2019, "ㅇㅌ / w**t","TAEMIN (태민)");	addQuestion(q212);
        Question q370 = new Question("-Ih5UArd4zk", "라이크워터","likewater","Like Water - WENDY (웬디)",2021, "ㄹㅇㅋ ㅇㅌ / l**e w**e*","WENDY (웬디)");	addQuestion(q370);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_KOREAN_ANSWER, question.getKoreanAnswer());
        cv.put(QuestionsTable.COLUMN_ENGLISH_ANSWER, question.getEnglishAnswer2());
        cv.put(QuestionsTable.COLUMN_REAL_ANSWER, question.getRealAnswer());
        cv.put(QuestionsTable.COLUMN_YEAR, question.getYear());
        cv.put(QuestionsTable.COLUMN_HINT, question.getHintAll());
        cv.put(QuestionsTable.COLUMN_SINGER, question.getSinger());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_QUESTION)));
                question.setKoreanAnswer(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_KOREAN_ANSWER)));
                question.setEnglishAnswer2(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_ENGLISH_ANSWER)));
                question.setRealAnswer(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_REAL_ANSWER)));
                question.setYear(c.getInt(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_YEAR)));
                question.setHintAll(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_HINT)));
                question.setSinger(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_SINGER)));
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
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_QUESTION)));
                question.setKoreanAnswer(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_KOREAN_ANSWER)));
                question.setEnglishAnswer2(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_ENGLISH_ANSWER)));
                question.setRealAnswer(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_REAL_ANSWER)));
                question.setYear(c.getInt(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_YEAR)));
                question.setHintAll(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_HINT)));
                question.setSinger(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_SINGER)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
