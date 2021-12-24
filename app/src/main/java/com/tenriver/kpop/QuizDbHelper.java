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
    private static final int DATABASE_VERSION = 18; // 데이터베이스 버전 항상 다르게 하기 ( 2021.12.24 기준 18)

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

        Question q1 = new Question("WYy2fROj7uU", "그대라는사치","amazingyou","그대라는 사치 (Amazing You) - Han Dong Geun(한동근)",2016, "ㄱㄷㄹㄴ ㅅㅊ / a**z**g y**","Han Dong Geun(한동근)");	addQuestion(q1);
        Question q2 = new Question("kCSFdRf0210", "내가설렐수있게","onlyone","내가 설렐 수 있게 (Only one) - Apink(에이핑크)",2016, "ㄴㄱ ㅅㄹ ㅅ ㅇㄱ / o**y o**","Apink(에이핑크)");	addQuestion(q2);
        Question q3 = new Question("L-2M_-QLs8k", "내가저지른사랑","theloveicommitted","내가 저지른 사랑(The love I committed) - 임창정",2016, "ㄴㄱ ㅈㅈㄹ ㅅㄹ / t** l**e I c**m**t**","임창정");	addQuestion(q3);
        Question q4 = new Question("bstWoRsocaw", "내옆에그대인걸","besideme","내 옆에 그대인걸 (Beside me) - 다비치 (DAVICHI)",2016, "ㄴ ㅇㅇ ㄱㄷㅇㄱ / b* s**e m*","다비치 (DAVICHI)");	addQuestion(q4);
        Question q5 = new Question("1ZhDsPdvl6c", "너그리고나","navillera","너 그리고 나 (NAVILLERA) - GFRIEND(여자친구)",2016, "ㄴ ㄱㄹㄱ ㄴ / n**i**e**","GFRIEND(여자친구)");	addQuestion(q5);
        Question q6 = new Question("xbf2c0JBJic", "너는나나는너","iamyouyouareme","너는 나 나는 너 (I am you, you are me) - 지코 (ZICO)",2016, "ㄴㄴ ㄴ ㄴㄴ ㄴ / i a* y** y** a** m*","지코 (ZICO)");	addQuestion(q6);
        Question q7 = new Question("GdxvD7r58ng", "너무너무너무","veryveryvery","너무너무너무 (Very Very Very) - 아이오아이 (I.O.I)",2016, "ㄴㅁㄴㅁㄴㅁ / v**y v**y v**y","아이오아이 (I.O.I)");	addQuestion(q7);
        Question q8 = new Question("QbhmTM1cRro", "넌이즈뭔들","yourethebest","넌 is 뭔들 (You're the best) - 마마무 (MAMAMOO)",2016, "ㄴ ㅇㅈ ㅁㄷ / y**r* t** b**t","마마무 (MAMAMOO)");	addQuestion(q8);
        Question q9 = new Question("MfYPKZl7W1w", "널사랑하지않아","idontloveyou","널 사랑하지 않아 (I Don't Love You) - 어반자카파(Urban Zakapa)",2016, "ㄴ ㅅㄹㅎㅈ ㅇㅇ / i d**t l**e y**","어반자카파(Urban Zakapa)");	addQuestion(q9);
        Question q10 = new Question("y2OFPvYxZuY", "데칼코마니","decalcomanie","데칼코마니 (Décalcomanie)  - 마마무 (MAMAMOO)",2016, "ㄷㅋㅋㅁㄴ / d**a**o**n**","마마무 (MAMAMOO)");	addQuestion(q10);
        Question q11 = new Question("WfYgbFBFe1E", "드림","dream","Dream - 수지(Suzy), 백현(BAEKHYUN)",2016, "ㄷㄹ / d**a*","수지(Suzy), 백현(BAEKHYUN)");	addQuestion(q11);
        Question q12 = new Question("eelfrHtmk68", "디","d","D (Half Moon) - Dean",2016, "ㄷ / *","Dean");	addQuestion(q12);
        Question q13 = new Question("QslJYDX3o8s", "러시안룰렛","russianroulette","러시안 룰렛 (Russian Roulette) - Red Velvet (레드벨벳)",2016, "ㄹㅅㅇ ㄹㄹ / r**s**n r**l**t*","Red Velvet (레드벨벳)");	addQuestion(q13);
        Question q14 = new Question("eHir_vB1RUI", "레인","rain","Rain - TAEYEON (태연)",2016, "ㄹㅇ / r**n","TAEYEON (태연)");	addQuestion(q14);
        Question q15 = new Question("ST8O-AeY3Uo", "리바이","rebye","RE-BYE - AKMU",2016, "ㄹㅂㅇ / r* b**","AKMU");	addQuestion(q15);
        Question q16 = new Question("KSH-FVVtTf0", "몬스터","monster","Monster - EXO (엑소)",2016, "ㅁㅅㅌ / m**s**r","EXO (엑소)");	addQuestion(q16);
        Question q17 = new Question("cIGgSI1uhKI", "봄이좋냐","whatthespring","봄이 좋냐?? (What The Spring??) - 10cm",2016, " ㅂㅇ ㅈㄴ / w**t t** s**i**","10cm");	addQuestion(q17);
        Question q18 = new Question("erErBFKPbMY", "봄인가봐","springlove","봄인가 봐 (Spring Love) - 에릭남 X 웬디",2016, "ㅂㅇㄱ ㅂ / s**i** l**e","에릭남 X 웬디");	addQuestion(q18);
        Question q19 = new Question("9pdj4iJD08s", "불장난","playingwithfire","불장난 (Playing With Fire) - BLACKPINK",2016, "ㅂㅈㄴ / p**y**g w**h f**e","BLACKPINK");	addQuestion(q19);
        Question q20 = new Question("4ujQOR2DMFM", "불타오르네","fire","불타오르네 (FIRE) - BTS (방탄소년단)",2016, "ㅂㅌㅇㄹㄴ / f**e","BTS (방탄소년단)");	addQuestion(q20);
        Question q21 = new Question("bwmSjveL3Lc", "붐바야","boombayah","붐바야 (BOOMBAYAH) - BLACKPINK",2016, "ㅂㅂㅇ / b**m**y**","BLACKPINK");	addQuestion(q21);
        Question q22 = new Question("h9mvVTgfKCs", "사랑이었다","itwaslove","사랑이었다 (Feat. LUNA of f(x)) - 지코 (ZICO)",2016, "ㅅㄹㅇㅇㄷ / i* w** l**e","지코 (ZICO)");	addQuestion(q22);
        Question q23 = new Question("OV9NJGTLm-4", "센치해","sentimental","센치해(SENTIMENTAL) - WINNER",2016, "ㅅㅊㅎ / s**t**e**a*","WINNER");	addQuestion(q23);
        Question q24 = new Question("gluvBLJYBjs", "셧업","shutup","Shut Up - Unnies(언니쓰)",2016, "ㅅㅇ / s**t u*","Unnies(언니쓰)");	addQuestion(q24);
        Question q25 = new Question("0VKcLPdY9lI", "시간을달려서","rough","시간을 달려서 (Rough) - GFRIEND(여자친구)",2016, "ㅅㄱㅇ ㄷㄹㅅ / r**g*","GFRIEND(여자친구)");	addQuestion(q25);
        Question q26 = new Question("BbQG-S4mU0U", "쏘쏘","soso","쏘쏘(SO-SO) - 백아연",2016, "ㅆㅆ / s* s*","백아연");	addQuestion(q26);
        Question q27 = new Question("LJVUjmNMF8c", "아이라이크댓","ilikethat","I Like That - 씨스타(SISTAR)",2016, "ㅇㅇ ㄹㅇㅋ ㄷ / i l**e t**t","씨스타(SISTAR)");	addQuestion(q27);
        Question q28 = new Question("J-wFp43XOrA", "아주나이스","verynice","아주 NICE (VERY NICE) - SEVENTEEN (세븐틴)",2016, "ㅇㅈ ㄴㅇㅅ / v**y n**e","SEVENTEEN (세븐틴)");	addQuestion(q28);
        Question q29 = new Question("va5rf20Un24", "어디에도","nomatterwhere","어디에도 (No matter where) - M.C THE MAX(엠씨더맥스)",2016, "ㅇㄷㅇㄷ / n* m**t** w**r*","M.C THE MAX(엠씨더맥스)");	addQuestion(q29);
        Question q30 = new Question("FNnYIIdTBhQ", "어떻게지내","fall","어떻게 지내 (fall) - Crush(크러쉬)",2016, "ㅇㄸㄱ ㅈㄴ / f**l","Crush(크러쉬)");	addQuestion(q30);
        Question q31 = new Question("iIPH8LFYFRk", "에라모르겠다","fxxxit","에라 모르겠다 (FXXK IT) - BIGBANG",2016, "ㅇㄹ ㅁㄹㄱㄷ / f**k i*","BIGBANG");	addQuestion(q31);
        Question q32 = new Question("RnDSODY7bVE", "엘라이","lie","L.I.E 엘라이 - EXID(이엑스아이디)",2016, "ㅇㄹㅇ / l**","EXID(이엑스아이디)");	addQuestion(q32);
        Question q33 = new Question("PYGODWJgR-c", "와이소론리","whysolonely","Why So Lonely - Wonder Girls(원더걸스)",2016, "ㅇㅇ ㅅ ㄹㄹ / w** s* l**e**","Wonder Girls(원더걸스)");	addQuestion(q33);
        Question q34 = new Question("9U8uA702xrE", "우주를줄게","galaxy","우주를 줄게 (GALAXY) - BOL4(볼빨간사춘기)",2016, "ㅇㅈㄹ ㅈㄱ / g**a**","BOL4(볼빨간사춘기)");	addQuestion(q34);
        Question q35 = new Question("_3tIkwvUjJg", "이프유","ifyou","If You - Ailee(에일리)",2016, "ㅇㅍ ㅇ / i* y**","Ailee(에일리)");	addQuestion(q35);
        Question q36 = new Question("TcytstV1_XE", "잊어버리지마","dontforget","잊어버리지마 (Don’t Forget) - Crush(크러쉬)",2016, "ㅇㅇㅂㄹㅈㅁ / d**t f**g**","Crush(크러쉬)");	addQuestion(q36);
        Question q37 = new Question("y5MAgMVwfFs", "좋다고말해","tellmeyoulikeme","좋다고 말해(Tell me you like me) - BOL4(볼빨간사춘기)",2016, "ㅈㄷㄱ ㅁㅎ / t**l m* y** l**e m*","BOL4(볼빨간사춘기)");	addQuestion(q37);
        Question q38 = new Question("c7rCyll5AeY", "치얼업","cheerup","CHEER UP - TWICE(트와이스)",2016, "ㅊㅇㅇ / c**e* u*","TWICE(트와이스)");	addQuestion(q38);
        Question q39 = new Question("ePpPVE-GGJw", "티티","tt","TT (티티) - TWICE(트와이스)",2016, "ㅌㅌ / t*","TWICE(트와이스)");	addQuestion(q39);
        Question q40 = new Question("hmE9f-TEutc", "피땀눈물","bloodsweatandtears","피 땀 눈물 (Blood Sweat & Tears) - BTS (방탄소년단)",2016, "ㅍ ㄸ ㄴㅁ / b**o* s**a* a** t**r*","BTS (방탄소년단)");	addQuestion(q40);
        Question q41 = new Question("5iSlfF8TQ9k", "한숨","breathe","한숨 (BREATHE) - LEE HI",2016, "ㅎㅅ / b**a**e","LEE HI");	addQuestion(q41);
        Question q42 = new Question("dISNgvVpWlo", "휘파람","whistle","휘파람 (WHISTLE) - BLACKPINK",2016, "ㅎㅍㄹ / w**s**e","BLACKPINK");	addQuestion(q42);
        Question q43 = new Question("JtjxZoa_LHM", "토이","toy","Toy - 블락비 (Block B)",2016, "ㅌㅇ / t**","블락비 (Block B)");	addQuestion(q43);
        Question q44 = new Question("nzDO6tAB6ng", "하늘바라기","hopefullysky","하늘바라기 (Hopefully sky) - Jeong Eun Ji(정은지)",2016, "ㅎㄴㅂㄹㄱ / h**e**l** s**","Jeong Eun Ji(정은지)");	addQuestion(q44);
        Question q45 = new Question("MOuzcBERbsc", "굿","good","GOOD (Feat. ELO) - 로꼬, GRAY (그레이)",2016, "ㄱ / g**d","로꼬, GRAY (그레이)");	addQuestion(q45);
        Question q46 = new Question("8avNw5EWBYs", "달고나","sugarandme","달고나 (Sugar and Me) - San E, Raina(레이나)",2016, "ㄷㄱㄴ / s**a* a** m*","San E, Raina(레이나)");	addQuestion(q46);
        Question q47 = new Question("5JbVVlqrreE", "나비잠","sweetdream","나비잠 (Sweet Dream) - 희철 X 민경훈",2016, "ㄴㅂㅈ / s**e* d**a*","희철 X 민경훈");	addQuestion(q47);
        Question q48 = new Question("XUR8QByF2As", "저별","star","저 별 (Star) - 헤이즈 (Heize)",2016, "ㅈ ㅂ / s**r","헤이즈 (Heize)");	addQuestion(q48);
        Question q49 = new Question("ZHoLaLlL5lA", "버뮤다트라이앵글","bermudatriangle","BERMUDA TRIANGLE- 지코 (ZICO)",2016, "ㅂㅁㄷ ㅌㄹㅇㅇㄱ / b**m**a t**a**l*","지코 (ZICO)");	addQuestion(q49);
        Question q50 = new Question("tr6Xi0DNWj8", "또하루","lonelynight","또 하루 (Lonely Night)(feat. GAEKO(개코)) - GARY(개리)",2016, "ㄸ ㅎㄹ / l**e** n**h*","GARY(개리)");	addQuestion(q50);
        Question q51 = new Question("oBKpJiVEcnU", "숨","breath","숨(Breath) - 박효신(Park Hyo Shin)",2016, "ㅅ / b**a**","박효신(Park Hyo Shin)");	addQuestion(q51);
        Question q52 = new Question("XsOGiTSZ_cg", "프레스유어넘버","pressyournumber","Press Your Number - TAEMIN 태민",2016, "ㅍㄹㅅ ㅇㅇ ㄴㅂ / p**s* y**r n**b**","TAEMIN (태민)");	addQuestion(q52);
        Question q53 = new Question("9xWiro_tS1k", "칠월칠일","oneofthesenights","7월 7일 (One Of These Nights) - Red Velvet (레드벨벳)",2016, "ㅊㅇ ㅊㅇ / o** o* t**s* n**h**","Red Velvet (레드벨벳)");	addQuestion(q53);
        Question q54 = new Question("vcnv3jjWgSc", "봄날의기억","rememberthat","봄날의 기억(Remember that) - BTOB(비투비)",2016, "ㅂㄴㅇ ㄱㅇ / r**e**e* t**t","BTOB(비투비)");	addQuestion(q54);
        Question q55 = new Question("Q4vFviZ4qw0", "플라이","fly","Fly - GOT7(갓세븐)",2016, "ㅍㄹㅇ / f**","GOT7(갓세븐)");	addQuestion(q55);
        Question q56 = new Question("qqqRn0l0PKE", "몇년후에","afewyearslater","몇 년 후에 (A Few Years Later) - BTOB(비투비)",2016, "ㅁ ㄴ ㅎㅇ / a f** y**r* l**e*","BTOB(비투비)");	addQuestion(q56);
        Question q57 = new Question("4bnIb1JJHdA", "다이너마이트","dynamite","다이너마이트 (Dynamite) - VIXX 빅스",2016, "ㄷㅇㄴㅁㅇㅌ / d**a**t*","VIXX 빅스");	addQuestion(q57);
        Question q58 = new Question("eNmL4JiGxZQ", "스타라이트","starlight","Starlight (Feat. DEAN) - TAEYEON (태연)",2016, "ㅅㅌㄹㅇㅌ / s**r**g**","TAEYEON (태연)");	addQuestion(q58);
        Question q59 = new Question("tbe3pe2BtwA", "로또","lotto","Lotto - EXO (엑소)",2016, "ㄹㄸ / l**t*","EXO (엑소)");	addQuestion(q59);
        Question q60 = new Question("y882AFjrSOM", "어때","howsthis","어때? (How's this?) - HyunA(현아)",2016, "ㅇㄸ / h**s t**s","HyunA(현아)");	addQuestion(q60);
        Question q61 = new Question("pcKR0LPwoYs", "스테이위드미","staywithme","Stay With Me - 찬열, 펀치",2016, "ㅅㅌㅇ ㅇㄷ ㅁ / s**y w**h m*","찬열, 펀치");	addQuestion(q61);
        Question q62 = new Question("O57jr1oZDIw", "하드캐리","hardcarry","하드캐리 - GOT7(갓세븐)",2016, "ㅎㄷㅋㄹ / h**d c**r*","GOT7(갓세븐)");	addQuestion(q62);
        Question q63 = new Question("WJua7KEP_oE", "원옵원","oneofone","1 of 1 - SHINee 샤이니",2016, "ㅇㅇㅇ / o** o* o**","SHINee 샤이니");	addQuestion(q63);
        Question q64 = new Question("DTiZ0aCm3rs", "포라이프","forlife","For Life - EXO (엑소)",2016, "ㅍ ㄹㅇㅍ / f** l**e","EXO (엑소)");	addQuestion(q64);
        Question q65 = new Question("AL2E1JDw2cA", "자격지심","inferioritycomplex","자격지심 (Inferiority Complex)",2016, "ㅈㄱㅈㅅ / i**e**o**t* c**p**x","박경 (Park Kyung)");	addQuestion(q65);
        Question q66 = new Question("JwdmM8XY-nk", "겨울동화","winterstory","겨울동화 (Winter Story) - LABOUM(라붐)",2016, "ㄱㅇㄷㅎ / w**t** s**r*","LABOUM(라붐)");	addQuestion(q66);
        Question q67 = new Question("S_IBk0RCsOo", "데스티니","destiny","Destiny (나의 지구) - 러블리즈(Lovelyz)",2016, "ㄷㅅㅌㄴ / d**t**y","러블리즈(Lovelyz)");	addQuestion(q67);
        Question q68 = new Question("iLlLLBuuvVU", "나만안되는연애","hardtolove","나만 안되는 연애(Hard To Love) - BOL4(볼빨간사춘기)",2016, "ㄴㅁ ㅇㄷㄴ ㅇㅇ / h**d t* l**e","BOL4(볼빨간사춘기)");	addQuestion(q68);
        Question q69 = new Question("1ri7I32Auhg", "싸운날","fightday","싸운날 - BOL4(볼빨간사춘기)",2016, "ㅆㅇㄴ / f**h* d**","BOL4(볼빨간사춘기)");	addQuestion(q69);
        Question q70 = new Question("LNZKqhXCv5c", "라이어라이어","liarliar","LIAR LIAR - 오마이걸(OH MY GIRL)",2016, "ㄹㅇㅇ ㄹㅇㅇ / l**r l**r","OH MY GIRL(오마이걸)");	addQuestion(q70);
        Question q71 = new Question("wLPgdkLMCKU", "기다렸다가","nosedive","기다렸다 가 (nosedive) - Dynamic Duo(다이나믹 듀오), CHEN(첸)",2017, "ㄱㄷㄹㄷ ㄱ / n**e**v*","Dynamic Duo(다이나믹 듀오), CHEN(첸)");	addQuestion(q71);
        Question q72 = new Question("0wlXaHmmOVc", "기억의빈자리","emptinessinmemory","기억의 빈자리 (Emptiness in Memory) - 나얼 (Naul)",2017, "ㄱㅇㅇ ㅂㅈㄹ / e**t**e** i* m**o**","나얼 (Naul)");	addQuestion(q72);
        Question q73 = new Question("Ue9NG1hAr78", "나로말할것같으면","yesiam","나로 말할 것 같으면 (Yes I am) - 마마무 (MAMAMOO)",2017, "ㄴㄹ ㅁㅎ ㄱ ㄱㅇㅁ / y** i a*","마마무 (MAMAMOO)");	addQuestion(q73);
        Question q74 = new Question("5wEjz3RdnKA", "낮보다는밤","nightratherthanday","낮보다는 밤 (Night Rather Than Day) - EXID(이엑스아이디)",2017, "ㄴㅂㄷㄴ ㅂ / n**h* r**h** t**n d**","EXID(이엑스아이디)");	addQuestion(q74);
        Question q75 = new Question("J7gOqqbBW6w", "너에게닿기를","iwish","너에게 닿기를 (I Wish) - WJSN (우주소녀)",2017, "ㄴㅇㄱ ㄷㄱㄹ / I w**h","WJSN (우주소녀)");	addQuestion(q75);
        Question q76 = new Question("vvkWaI91mLM", "널너무모르고","dontknowyou","널 너무 모르고 (Don't know you) - 헤이즈 (Heize)",2017, "ㄴ ㄴㅁ ㅁㄹㄱ / d**t k**w y**","헤이즈 (Heize)");	addQuestion(q76);
        Question q77 = new Question("IZ1t7CwfvEc", "네버에버","neverever","Never Ever - GOT7(갓세븐)",2017, "ㄴㅂ ㅇㅂ / n**e* e**r","GOT7(갓세븐)");	addQuestion(q77);
        Question q78 = new Question("OwJPPaEyqhI", "뉴페이스","newface","New Face - PSY",2017, "ㄴㅍㅇㅅ / n** f**e","PSY");	addQuestion(q78);
        Question q79 = new Question("8Oz7DG76ibY", "다이노소어","dinosaur","DINOSAUR - AKMU",2017, "ㄷㅇㄴㅅㅇ / d**o**u*","AKMU");	addQuestion(q79);
        Question q80 = new Question("axVvZrDz60k", "덜덜덜","ddd","덜덜덜(DDD) - EXID(이엑스아이디)",2017, "ㄷㄷㄷ / d**","EXID(이엑스아이디)");	addQuestion(q80);
        Question q81 = new Question("r1afdZk0qcI", "드라마라마","dramarama","DRAMARAMA - 몬스타엑스(MONSTA X)",2017, "ㄷㄹㅁㄹㅁ / d**m**a**","몬스타엑스(MONSTA X)");	addQuestion(q81);
        Question q82 = new Question("hsh54g9JmC0", "디스크리스마스","thischristmas","This Christmas - TAEYEON (태연)",2017, "ㄷㅅ ㅋㄹㅅㅁㅅ / t**s c**i**m**","TAEYEON (태연)");	addQuestion(q82);
        Question q83 = new Question("MBdVXkSdhwU", "디엔에이","dna","DNA - BTS (방탄소년단)",2017, "ㄷㅇㅇㅇ / d**","BTS (방탄소년단)");	addQuestion(q83);
        Question q84 = new Question("ppOWR7ZLl7Q", "러브미러브미","lovemeloveme","LOVE ME LOVE ME - WINNER",2017, "ㄹㅂㅁ ㄹㅂㅁ / l**e m* l**e m*","WINNER");	addQuestion(q84);
        Question q85 = new Question("J0h8-OTC38I", "루키","rookie","Rookie - Red Velvet (레드벨벳)",2017, "ㄹㅋ / r**k**","Red Velvet (레드벨벳)");	addQuestion(q85);
        Question q86 = new Question("TVUqLBRQom8", "메이크미러브유","makemeloveyou","Make Me Love You - TAEYEON (태연)",2017, "ㅁㅇㅋ ㅁ ㄹㅂ ㅇ / m**e m* l**e y**","TAEYEON (태연)");	addQuestion(q86);
        Question q87 = new Question("rcEyUNeZqmY", "무브","move","MOVE - TAEMIN (태민)",2017, "ㅁㅂ / m**e","TAEMIN (태민)");	addQuestion(q87);
        Question q88 = new Question("42A-rFdralM", "무비","movie","MOVIE - BTOB(비투비)",2017, "ㅁㅂ / m**i*","BTOB(비투비)");	addQuestion(q88);
        Question q89 = new Question("CyzEtbG-sxY", "박수","clap","CLAP - SEVENTEEN (세븐틴)",2017, "ㅋㄹ / c**p","SEVENTEEN (세븐틴)");	addQuestion(q89);
        Question q90 = new Question("b9xndFqGJ4k", "베베","babe","베베(BABE) - HyunA(현아)",2017, "ㅂㅂ / b*b*","HyunA(현아)");	addQuestion(q90);
        Question q91 = new Question("RIWRyggt-QY", "봄의나라이야기","aprilstory","봄의 나라 이야기 (April Story) - APRIL(에이프릴)",2017, "ㅂㅇ ㄴㄹ ㅇㅇㄱ / a**i* s**r*","APRIL(에이프릴)");	addQuestion(q91);
        Question q92 = new Question("W0cs6ciCt_k", "뷰티풀","beautiful","Beautiful - Crush(크러쉬)",2017, "ㅂㅌㅍ / b**u**f**","Crush(크러쉬)");	addQuestion(q92);
        Question q93 = new Question("GeJAee3m3Ik", "블루문","bluemoon","BLUE MOON - HYOLYN, CHANGMO(효린, 창모)",2017, "ㅂㄹㅁ / b**e m**n","HYOLYN, CHANGMO(효린, 창모)");	addQuestion(q93);
        Question q94 = new Question("1Q8J5nghxiM", "비가와","rain","비가와 (Rain) - SOYOU(소유), BAEKHYUN(백현)",2017, "ㅂㄱㅇ / r**n","SOYOU(소유), BAEKHYUN(백현)");	addQuestion(q94);
        Question q95 = new Question("qYYJqWsBb1U", "선물","gift","선물 (Gift) - MeloMance(멜로망스)",2017, "ㅅㅁ / g**t","MeloMance(멜로망스)");	addQuestion(q95);
        Question q96 = new Question("NHglTopVlKQ", "쉘위댄스","shallwedance","Shall We Dance - 블락비 (Block B)",2017, "ㅅ ㅇ ㄷㅅ / s**l* w* d**c*","블락비 (Block B)");	addQuestion(q96);
        Question q97 = new Question("VQtonf1fv_s", "시그널","signal","SIGNAL - TWICE(트와이스)",2017, "ㅅㄱㄴ / s**n**","TWICE(트와이스)");	addQuestion(q97);
        Question q98 = new Question("hZmoMyFXDoI", "썸탈꺼야","some","썸 탈꺼야 (SOME) - BOL4(볼빨간사춘기)",2017, "ㅆㅌㄱㅇ / s**e","BOL4(볼빨간사춘기)");	addQuestion(q98);
        Question q99 = new Question("f5Zedh_5DDM", "아름다워","beautiful","아름다워(Beautiful) - 몬스타엑스(MONSTA X)",2017, "ㅇㄹㄷㅇ / b**u**f**","몬스타엑스(MONSTA X)");	addQuestion(q99);
        Question q100 = new Question("7crt2Ip93VI", "아이윌비유얼스","illbeyours","I'll be yours - Girl's Day(걸스데이)",2017, "ㅇㅇㅇ ㅂ ㅇㅇㅅ / i** b* y**r*","Girl's Day(걸스데이)");	addQuestion(q100);
        Question q101 = new Question("obzb7nlpXZ0", "아티스트","artist","Artist - 지코 (ZICO)",2017, "ㅇㅌㅅㅌ / a**i**","지코 (ZICO)");	addQuestion(q101);
        Question q102 = new Question("ZsYwEV_ge4Y", "여름비","summerrain","여름비 (Summer Rain) - GFRIEND(여자친구)",2017, "ㅇㄹㅂ / s**m** r**n","GFRIEND(여자친구)");	addQuestion(q102);
        Question q103 = new Question("3q22SInyiX8", "예스터데이","yesterday","YESTERDAY - 블락비 (Block B)",2017, "ㅇㅅㅌㄷㅇ / y**t**d**","블락비 (Block B)");	addQuestion(q103);
        Question q104 = new Question("sUZeYOLp8Ys", "오솔레미오","osolemio","O Sole Mio(오솔레미오) - SF9 (에스에프나인)",2017, "ㅇㅅㄹㅁㅇ / o s**e m**","SF9 (에스에프나인)");	addQuestion(q104);
        Question q105 = new Question("Nu7OmSqHVng", "우쥬","wouldu","Would U - Red Velvet (레드벨벳)",2017, "ㅇㅈ / w**l* u","Red Velvet (레드벨벳)");	addQuestion(q105);
        Question q106 = new Question("zEkg4GBQumc", "울고싶지않아","dontwannacry","울고 싶지 않아 (Don't Wanna Cry) - SEVENTEEN (세븐틴)",2017, "ㅇㄱ ㅅㅈ ㅇㅇ / d**t w**n* c**","SEVENTEEN (세븐틴)");	addQuestion(q106);
        Question q107 = new Question("wLfHuClrQdI", "위후","weewoo","WEE WOO - PRISTIN(프리스틴)",2017, "ㅇㅎ / w**w**","PRISTIN(프리스틴)");	addQuestion(q107);
        Question q108 = new Question("NRlDo3JuzfI", "익스큐즈미","excuseme","Excuse Me - AOA",2017, "ㅇㅅㅋㅈㅁ / e**u** m*","AOA");	addQuestion(q108);
        Question q109 = new Question("wKyMIrBClYw", "인스타그램","instagram","instagram - DEAN",2017, "ㅇㅅㅌㄱㄹ / i**t**r**","DEAN");	addQuestion(q109);
        Question q110 = new Question("vDxD4HwEFdY", "종소리","twinkle","종소리 (Twinkle) - 러블리즈(Lovelyz)",2017, "ㅈㅅㄹ / t**n**e","러블리즈(Lovelyz)");	addQuestion(q110);
        Question q111 = new Question("b1kQvZhQ6_M", "좋니","likeit","좋니 (Like it) - Jong Shin Yoon(윤종신)",2017, "ㅈㄴ / l**e i*","Jong Shin Yoon(윤종신)");	addQuestion(q111);
        Question q112 = new Question("7cjZFjWBZI0", "컬러링북","coloringbook","컬러링북 (Coloring Book) - OH MY GIRL(오마이걸)",2017, "ㅋㄹㄹㅂ / c**o**n* b**k","OH MY GIRL(오마이걸)");	addQuestion(q112);
        Question q113 = new Question("b6l0x9xxk4k", "콜링유","callingyou","CALLING YOU - Highlight(하이라이트)",2017, "ㅋㄹ ㅇ / c**l**g y**","Highlight(하이라이트)");	addQuestion(q113);
        Question q114 = new Question("hvX3wWKOEa8", "터치","touch","터치 (TOUCH) - 신화SHINHWA",2017, "ㅌㅊ / t**c*","신화SHINHWA");	addQuestion(q114);
        Question q115 = new Question("pC6tPEaAiYU", "톰보이","tomboy","톰보이 (TOMBOY) - HYUKOH(혁오)",2017, "ㅌㅂㅇ / t**b**","HYUKOH(혁오)");	addQuestion(q115);
        Question q116 = new Question("6uJf2IT2Zh8", "피카부","peekaboo","피카부 (Peek-A-Boo) - Red Velvet (레드벨벳)",2017, "ㅍㅋㅂ / p**k**o*","Red Velvet (레드벨벳)");	addQuestion(q116);
        Question q117 = new Question("sZVB_zCBlCU", "해피","happy","HAPPY - 우주소녀(WJSN)",2017, "ㅎㅍ / h**p*","우주소녀(WJSN)");	addQuestion(q117);
        Question q118 = new Question("YwN-CN9EjTg", "홀리데이","holiday","Holiday - Girls' Generation (소녀시대)",2017, "ㅎㄹㄷㅇ / h**i**y","Girls' Generation (소녀시대)");	addQuestion(q118);
        Question q119 = new Question("aehbDMIEmnM", "휘휘","hwihwi"," 휘휘 (Hwi hwi) - LABOUM(라붐)",2017, "ㅎㅎ / h**h**","LABOUM(라붐)");	addQuestion(q119);
        Question q120 = new Question("d9IxdwEFk1c", "팔레트","palette","Palette(팔레트) - IU(아이유)",2017, "ㅍㄹㅌ / p**e**e","IU(아이유)");	addQuestion(q120);
        Question q121 = new Question("afxLaQiLu-o", "비도오고그래서","youcloudrain","비도 오고 그래서 (You,Clouds,Rain) - 헤이즈 (Heize)",2017, "ㅂㄷ ㅇㄱ ㄱㄹㅅ / y**c**u**r**n","헤이즈 (Heize)");	addQuestion(q121);
        Question q122 = new Question("1kcwvcbO8MI", "얼굴찌푸리지말아요","plzdontbesad","얼굴 찌푸리지 말아요 (Plz Don’t Be Sad) - 하이라이트 (Highlight)",2017, "ㅇㄱ ㅉㅍㄹㅈ ㅁㄹㅇ / p** d**t b* s**","하이라이트 (Highlight)");	addQuestion(q122);
        Question q123 = new Question("BzYnNdJhZQw", "밤편지","throughthenight","밤편지 (Through the Night) - IU(아이유)",2017, "ㅂㅍㅈ / t**o**h t** n**h*","IU(아이유)");	addQuestion(q123);
        Question q124 = new Question("Xvjnoagk6GU", "아이러브잇","iluvit","I LUV IT - PSY",2017, "ㅇㅇㄹㅂㅇ / i l** i*","PSY");	addQuestion(q124);
        Question q125 = new Question("KbXV2R_Yd1E", "론리","lonely","LONELY - 씨스타(SISTAR)",2017, "ㄹㄹ / l**e**","씨스타(SISTAR)");	addQuestion(q125);
        Question q126 = new Question("9kaCAbIXuyg", "무제","untitled","무제 (Untitled, 2014) - G-DRAGON",2017, "ㅁㅈ / u**i**e*","G-DRAGON");	addQuestion(q126);
        Question q127 = new Question("Z1pGxkXyDvc", "남이될수있을까","weloved","남이 될 수 있을까(We Loved) - 볼빨간사춘기, 스무살(BOL4, 20 Years Of Age)",2017, "ㄴㅇ ㄷㅅ ㅇㅇㄲ / w* l**e*","볼빨간사춘기, 스무살(BOL4, 20 Years Of Age)");	addQuestion(q127);
        Question q128 = new Question("Z3INNjAEqHk", "연애소설","lovestory","연애소설 (LOVE STORY) - 에픽하이 (EPIK HIGH)",2017, "ㅇㅇㅅㅅ / l**e s**r*","에픽하이 (EPIK HIGH)");	addQuestion(q128);
        Question q129 = new Question("rRzxEiBLQCA", "하트쉐이커","heartshaker","Heart Shaker - 트와이스(TWICE)",2017, "ㅎㅌㅅㅇㅋ / h**r* s**k**","트와이스(TWICE)");	addQuestion(q129);
        Question q130 = new Question("Amq-qlqbjYA", "마지막처럼","asifitsyourlast","마지막처럼 (AS IF IT'S YOUR LAST) - BLACKPINK",2017, "ㅁㅈㅁㅊㄹ / a* i* i** y**r l**t","BLACKPINK");	addQuestion(q130);
        Question q131 = new Question("b22Ed7f0D-0", "파이브","five","FIVE - 에이핑크(Apink)",2017, "ㅍㅇㅂ / f**e","에이핑크(Apink)");	addQuestion(q131);
        Question q132 = new Question("IdssuxDdqKk", "코코밥","kokobop","Ko Ko Bop - EXO(엑소)",2017, "ㅋㅋㅂ / k**o**p","EXO(엑소)");	addQuestion(q132);
        Question q133 = new Question("lnXXfYA91Y8", "귀를기울이면","lovewhisper","귀를 기울이면(LOVE WHISPER) - 여자친구(GFRIEND)",2017, "ㄱㄹ ㄱㅇㅇㅁ / l**e w**s**r ","여자친구(GFRIEND)");	addQuestion(q133);
        Question q134 = new Question("EVaV7AwqBWg", "에너제틱","energetic","에너제틱 (Energetic) - 워너원(Wanna One)",2017, "ㅇㄴㅈㅌ / e**r**t**","워너원(Wanna One)");	addQuestion(q134);
        Question q135 = new Question("V2hlQkVJZhE", "라이키","likey","LIKEY - 트와이스(TWICE)",2017, "ㄹㅇㅋ / l**e*","트와이스(TWICE)");	addQuestion(q135);
        Question q136 = new Question("8A2t_tAjMz8", "낙낙","knockknock","KNOCK KNOCK - 트와이스(TWICE)",2017, "ㄴㄴ / k**c* k**c*","트와이스(TWICE)");	addQuestion(q136);
        Question q137 = new Question("EHgeGRU3wDI", "와이돈츄노","whydontyouknow","Why Don’t You Know - 청하 (CHUNG HA)",2017, "ㅇㅇㄷㅊㄴ / w** d**t y** k**w","청하 (CHUNG HA)");	addQuestion(q137);
        Question q138 = new Question("kbdW2LaKlnw", "소나기","downpour","소나기 (DOWNPOUR) - 아이오아이 (I.O.I)",2017, "ㅅㄴㄱ / d**n**u*","아이오아이 (I.O.I)");	addQuestion(q138);
        Question q139 = new Question("i1n_1jrUEjU", "핑거팁","fingertip","FINGERTIP - 여자친구 (GFRIEND)",2017, "ㅍㄱㅌ / f**g**t**","여자친구 (GFRIEND)");	addQuestion(q139);
        Question q140 = new Question("mOo8bVzN9M8", "폰서트","phonecert","폰서트 (Phonecert) - 10cm",2017, "ㅍㅅㅌ / p**n**e**","10cm");	addQuestion(q140);
        Question q141 = new Question("O136JYv3weQ", "좋아해","loveyou","좋아해 (Love You) - CHEEZE(치즈)",2017, "ㅈㅇㅎ / l**e y**","CHEEZE(치즈)");	addQuestion(q141);
        Question q142 = new Question("OaG575r_Dyo", "유후","youwho","유후 (You, Who?) - 에릭남X소미 (Eric Nam X Somi)",2017, "ㅇㅎ / y** w**","에릭남X소미 (Eric Nam X Somi)");	addQuestion(q142);
        Question q143 = new Question("Rh5ok0ljrzA", "이런엔딩","endingscene","이런 엔딩 (Ending Scene) - IU(아이유)",2017, "ㅇㄹ ㅇㄷ / e**i** s**n*","IU(아이유)");	addQuestion(q143);
        Question q144 = new Question("wMCoQaE0LvQ", "지금우리","nowwe","지금, 우리 (Now, We) - 러블리즈(Lovelyz)",2017, "ㅈㄱ ㅇㄹ / n** w*","러블리즈(Lovelyz)");	addQuestion(q144);
        Question q145 = new Question("5e8-4mBiCSI", "웨얼유엣","whereyouat","WHERE YOU AT - NU'EST W(뉴이스트 W)",2017, "ㅇㅇ ㅇ ㅇ / w**r* y** a*","NU'EST W(뉴이스트 W)");	addQuestion(q145);
        Question q146 = new Question("ur0hCdne2-s", "가시나","gashina","가시나 (Gashina) - 선미(SUNMI)",2017, "ㄱㅅㄴ / g**h**a","선미(SUNMI)");	addQuestion(q146);
        Question q147 = new Question("vcqImqOVE2U", "립앤힙","lipandhip","Lip & Hip - HyunA(현아)",2017, "ㄹㅇㅎ / l** a** h**","HyunA(현아)");	addQuestion(q147);
        Question q148 = new Question("v6_GwXU1lkg", "그날처럼","goodolddays","그날처럼 (Good old days) - 장덕철(JANG DEOK CHEOL)",2017, "ㄱㄴㅊㄹ / g**d o** d**s","장덕철(JANG DEOK CHEOL)");	addQuestion(q148);
        Question q149 = new Question("CbNmRJCkwQs", "올오브마이라이프","allofmylife","all of my life - PARK WON(박원)",2017, "ㅇ ㅇㅂ ㅁㅇ ㄹㅇㅍ / a** o* m* l**e","PARK WON(박원)");	addQuestion(q149);
        Question q150 = new Question("NMPg8xVcjVs", "식스틴","sixteen","Sixteen (Feat. Changmo) - Samuel(사무엘)",2017, "ㅅㅅㅌ / s**t**n","Samuel(사무엘)");	addQuestion(q150);
        Question q151 = new Question("tz23WFb8HM0", "유인미","youinme","You In Me - KARD",2017, "ㅇㅇㅁ / y** i* m*","KARD");	addQuestion(q151);
        Question q152 = new Question("nhRUyVMkaNI", "구찌","gucci","Gucci - Jessi (제시)",2017, "ㄱㅉ / g**c*","Jessi (제시)");	addQuestion(q152);
        Question q153 = new Question("rDG9I9nx0QU", "180도","180degree","180도 (180 Degree) - 벤 (BEN)",2018, "180ㄷ / 180d**r**","벤 (BEN)");	addQuestion(q153);
        Question q154 = new Question("SkN_hWI6n28", "그때헤어지면돼","onlythen","그때 헤어지면 돼 (Only then) - 로이킴 (Roy Kim)",2018, "ㄱㄸ ㅎㅇㅈㅁ ㄷ / o**y t**n","로이킴 (Roy Kim)");	addQuestion(q154);
        Question q155 = new Question("VWqxvBQKwKQ", "꽃길","flowerroad","꽃 길 (Flower Road) - BIGBANG",2018, "ㄲ ㄱ / f**w** r**d","BIGBANG");	addQuestion(q155);
        Question q156 = new Question("pHtxTSiPh5I", "너나해","egotistic","너나 해 (Egotistic) - 마마무 (MAMAMOO)",2018, "ㄴㄴ ㅎ / e**t**t**","마마무 (MAMAMOO)");	addQuestion(q156);
        Question q157 = new Question("YBzJ0jmHv-4", "너를만나","meafteryou","너를 만나 (Me After You) - Paul Kim(폴킴)",2018, "ㄴㄹ ㅁㄴ / m* a**e* y**","Paul Kim(폴킴)");	addQuestion(q157);
        Question q158 = new Question("fHQkdIGue3k", "너없인안된다","onlyoneforme","너 없인 안 된다 (Only one for me) - BTOB(비투비)",2018, "ㄴ ㅇㅇ ㅇ ㄷㄷ / o**y o** f** m*","BTOB(비투비)");	addQuestion(q158);
        Question q159 = new Question("Fm5iP0S1z9w", "댄스더나이트어웨이","dancethenightaway","Dance The Night Away - TWICE(트와이스)",2018, "ㄷㅅ ㄷ ㄴㅇㅌ ㅇㅇㅇ / d**c* t** n**h* a**y","TWICE(트와이스)");	addQuestion(q159);
        Question q160 = new Question("IHNzOHi8sJs", "뚜두뚜두","ddududdudu","뚜두뚜두 (DDU-DU DDU-DU) - BLACKPINK",2018, "ㄸㄷㄸㄷ / d**d* d**d*","BLACKPINK");	addQuestion(q160);
        Question q161 = new Question("pSudEWBAYRE", "러브샷","loveshot","Love Shot - EXO (엑소)",2018, "ㄹㅂ ㅅ / l**e s**t","EXO (엑소)");	addQuestion(q161);
        Question q162 = new Question("mtLgabce8KQ", "러브유","loveu","Love U - 청하 (CHUNG HA)",2018, "ㄹㅂ  ㅇ / l**e u","청하 (CHUNG HA)");	addQuestion(q162);
        Question q163 = new Question("sS0LCjOiIhc", "룩","look","Look - GOT7(갓세븐)",2018, "ㄹ / l**k","GOT7(갓세븐)");	addQuestion(q163);
        Question q164 = new Question("9RUeTYiJCyA", "룰라비","lullaby","Lullaby - GOT7(갓세븐)",2018, "ㄹㄹㅂ / l**l**y","GOT7(갓세븐)");	addQuestion(q164);
        Question q165 = new Question("nqMYG2Riq54", "멋지게인사하는법","hellotutorial","멋지게 인사하는 법(Hello Tutorial) - Zion.T",2018, "ㅁㅈㄱ ㅇㅅㅎㄴ ㅂ / h**l* t**o**a*","Zion.T");	addQuestion(q165);
        Question q166 = new Question("RtRtLf84I2M", "미라클","miracle","Miracle - GOT7(갓세븐)",2018, "ㅁㄹㅋ / m**a**e","GOT7(갓세븐)");	addQuestion(q166);
        Question q167 = new Question("_XyBa8QsVQU", "밤","timeforthemoonnight","밤 (Time for the moon night) - GFRIEND(여자친구)",2018, "ㅂ / t**e f** t** m**n**g**","GFRIEND(여자친구)");	addQuestion(q167);
        Question q168 = new Question("J_CFBjAyPWE", "배드보이","badboy","Bad Boy - Red Velvet (레드벨벳)",2018, "ㅂㄷ ㅂㅇ / b** b**","Red Velvet (레드벨벳)");	addQuestion(q168);
        Question q169 = new Question("txWmd7QKFe8", "뱀","baam","BAAM - MOMOLAND (모모랜드)",2018, "ㅂ / b**m","MOMOLAND (모모랜드)");	addQuestion(q169);
        Question q170 = new Question("0FB2EoKTK_Q", "별이빛나는밤","starrynight","별이 빛나는 밤 (Starry Night) - 마마무 (MAMAMOO)",2018, "ㅂㅇ ㅂㄴㄴ ㅂ / s**r** n**h*","마마무 (MAMAMOO)");	addQuestion(q170);
        Question q171 = new Question("FD2mik4V5EE", "봄바람","springbreeze","봄바람 (Spring Breeze) - Wanna One (워너원)",2018, "ㅂㅂㄹ / s**i** b**e**","Wanna One (워너원)");	addQuestion(q171);
        Question q172 = new Question("pmMjkMtpnTc", "부메랑","boomerang","부메랑 (BOOMERANG) - Wanna One (워너원)",2018, "ㅂㅁㄹ / b**m**a**","Wanna One (워너원)");	addQuestion(q172);
        Question q173 = new Question("ntWSnDV0MYs", "빙글뱅글","binglebangle","빙글뱅글 (Bingle Bangle) - AOA",2018, "ㅂㄱㅂㄱ / b**g** b**g**","AOA");	addQuestion(q173);
        Question q174 = new Question("JQGRg8XBnB4", "뿜뿜","bboombboom","뿜뿜 (BBoom Bboom) - MOMOLAND (모모랜드)",2018, "ㅃㅃ / b**o* b**o*","MOMOLAND (모모랜드)");	addQuestion(q174);
        Question q175 = new Question("nM0xDI5R50E", "삐삐","bbibbi","삐삐 (BBIBBI) - IU(아이유)",2018, "ㅃㅃ / b**b**","IU(아이유)");	addQuestion(q175);
        Question q176 = new Question("vecSVX1QYbQ", "사랑을했다","lovescenario","사랑을 했다(LOVE SCENARIO) - iKON",2018, "ㅅㄹㅇ ㅎㄷ / l**e s**n**i*","iKON");	addQuestion(q176);
        Question q177 = new Question("TNWMZIf7eSg", "사이렌","siren","사이렌 (Siren) - SUNMI(선미)",2018, "ㅅㅇㄹ / s**e*","SUNMI(선미)");	addQuestion(q177);
        Question q178 = new Question("Vl1kO9hObpA", "소울메이트","soulmate","SoulMate - 지코 (ZICO)",2018, "ㅅㅇㅁㅇㅌ / s**l**t*","지코 (ZICO)");	addQuestion(q178);
        Question q179 = new Question("b73BI9eUkjM", "솔로","solo","SOLO - JENNIE",2018, "ㅅㄹ / s**o","JENNIE");	addQuestion(q179);
        Question q180 = new Question("MS10Zz49FHE", "슛아웃","shootout","Shoot Out - MONSTA X 몬스타엑스",2018, "ㅅ ㅇㅇ / s**o* o**","MONSTA X 몬스타엑스");	addQuestion(q180);
        Question q181 = new Question("YfQzz00Oc_M", "시간이들겠지","ittakestime","시간이 들겠지 (It Takes Time) - Loco(로꼬)",2018, "ㅅㄱㅇ ㄷㄱㅈ / i* t**e* t**e","Loco(로꼬)");	addQuestion(q181);
        Question q182 = new Question("NY8VGNft-Zc", "아낙네","fiance","아낙네 (FIANCÉ) - MINO(송민호)",2018, "ㅇㄴㄴ / f**n**","MINO(송민호)");	addQuestion(q182);
        Question q183 = new Question("pBuZEGYXA6E", "아이돌","idol","IDOL - BTS (방탄소년단)",2018, "ㅇㅇㄷ / i**l","BTS (방탄소년단)");	addQuestion(q183);
        Question q184 = new Question("xRbPAVnqtcs", "여행","travel","여행 (Travel) - BOL4(볼빨간사춘기)",2018, "ㅇㅎ / t**v**","BOL4(볼빨간사춘기)");	addQuestion(q184);
        Question q185 = new Question("WBahioCWfFQ", "열애중","loveing","열애중 Love, ing - 벤 (BEN)",2018, "ㅇㅇㅈ / l**e**g","벤 (BEN)");	addQuestion(q185);
        Question q186 = new Question("mAKsZ26SabQ", "예스올예스","yesoryes","YES or YES - TWICE(트와이스)",2018, "ㅇㅅ ㅇ ㅇㅅ / y** o* y**","TWICE(트와이스)");	addQuestion(q186);
        Question q187 = new Question("i0p1bmr0EmE", "왓이즈러브","whatislove","What is Love? - TWICE(트와이스)",2018, "ㅇ ㅇㅈ ㄹㅂ / w**t i* l**e","TWICE(트와이스)");	addQuestion(q187);
        Question q188 = new Question("fB406grTgz4", "우리그만하자","thehardestpart","우리 그만하자 (The Hardest Part) - 로이킴 (Roy Kim)",2018, "ㅇㄹ ㄱㅁㅎㅈ / t** h**d**t p**t","로이킴 (Roy Kim)");	addQuestion(q188);
        Question q189 = new Question("2O6dRaBbFoo", "이별길","goodbyeroad","이별길(GOODBYE ROAD) - iKON",2018, "ㅇㅂㄱ / g**d**e r**d","iKON");	addQuestion(q189);
        Question q190 = new Question("F4oHuML9U2A", "일도없어","imsosick","1도 없어 (I'm so sick) - Apink(에이핑크)",2018, "ㅇㄷ ㅇㅇ / i* s* s**k","Apink(에이핑크)");	addQuestion(q190);
        Question q191 = new Question("uw_HR9jIJww", "젠가","jenga","Jenga (Feat. Gaeko) - 헤이즈 (Heize)",2018, "ㅈㄱ / j**g*","헤이즈 (Heize)");	addQuestion(q191);
        Question q192 = new Question("IB6kViGA3rY", "주지마","abovelive","주지마 (Above Live) - 로꼬 (Loco), 화사 (마마무)",2018, "ㅈㅈㅁ / a**v* l**e","로꼬 (Loco), 화사 (마마무)");	addQuestion(q192);
        Question q193 = new Question("TM-gCW45YHc", "텔미","tellme","Tell Me - 인피니트(INFINITE)",2018, "ㅌ ㅁ / t**l m*","인피니트(INFINITE)");	addQuestion(q193);
        Question q194 = new Question("iwd8N6K-sLk", "템포","tempo","Tempo - EXO (엑소)",2018, "ㅌㅍ / t**p*","EXO (엑소)");	addQuestion(q194);
        Question q195 = new Question("jt3Vdwrbhig", "트루러브","truelove","True Love - Kim Sung Kyu (김성규)",2018, "ㅌㄹ ㄹㅂ / t**e l**e","Kim Sung Kyu (김성규)");	addQuestion(q195);
        Question q196 = new Question("aiHSVQy9xN8", "파워업","powerup","Power Up - Red Velvet (레드벨벳)",2018, "ㅍㅇ ㅇ / p**e* u*","Red Velvet (레드벨벳)");	addQuestion(q196);
        Question q197 = new Question("7C2z4GqqS5E", "페이크러브","fakelove","FAKE LOVE - BTS (방탄소년단)",2018, "ㅍㅇㅋ ㄹㅂ / f**e l**e","BTS (방탄소년단)");	addQuestion(q197);
        Question q198 = new Question("pW0jpmncut4", "하루도그대를사랑하지않은적이없었다","therehasneverbeenadayihaventlovedyou","하루도 그대를 사랑하지 않은 적이 없었다(There has never been a day I haven't loved you) - 임창정",2018, "ㅎㄹㄷ ㄱㄷㄹ ㅅㄹㅎㅈ ㅇㅇ ㅈㅇ ㅇㅇㄷ / t**r* h** n**e* b**n a d** i h**e** l**e* y**","임창정");	addQuestion(q198);
        Question q199 = new Question("Ib674A1yMtg", "화요일","bloomingday","花요일 (Blooming Day) - EXO-CBX (첸백시)",2018, "ㅎㅇㅇ / b**o**n* d**","EXO-CBX (첸백시)");	addQuestion(q199);
        Question q200 = new Question("hhXDFl6tmVY", "첫눈에","firstsight","첫눈에 (First Sight) - 헤이즈 (Heize)",2018, "ㅊㄴㅇ / f**s* s**h*","헤이즈 (Heize)");	addQuestion(q200);
        Question q201 = new Question("WZwr2a_lFWY", "라비앙로즈","lavieenrose","라비앙로즈 (La Vie en Rose) - 아이즈원 (IZ*ONE)",2018, "ㄹㅂㅇㄹㅈ / l* v** e* r**e","아이즈원 (IZ*ONE)");	addQuestion(q201);
        Question q202 = new Question("IWJUPY-2EIM", "알비비","rbb","RBB (Really Bad Boy) - Red Velvet (레드벨벳)",2018, "ㅇㅂㅂ / r**","Red Velvet (레드벨벳)");	addQuestion(q202);
        Question q203 = new Question("L15ZZX9n56M", "벌써열두시","gottago","벌써 12시 (Gotta Go) - 청하 (CHUNG HA)",2018, "ㅂㅆ ㅇㄷㅅ / g**t* g*","청하 (CHUNG HA)");	addQuestion(q203);
        Question q204 = new Question("Nso7yIr8QVM", "너는어땠을까","howaboutyou","너는 어땠을까 (How about you) - Noel(노을)",2018, "ㄴㄴ ㅇㄸㅇㄲ / h** a**u* y**","Noel(노을)");	addQuestion(q204);
        Question q205 = new Question("oT8eXpXymmA", "레트로퓨처","retrofuture","RETRO FUTURE - Triple H(트리플 H)",2018, "ㄹㅌㄹ ㅍㅊ / r**r* f**u**","Triple H(트리플 H)");	addQuestion(q205);
        Question q206 = new Question("3K38Fc1SV5c", "한","hann","HANN (Alone)(한(一)) - (여자)아이들((G)I-DLE)",2018, "ㅎ / h**n","(여자)아이들((G)I-DLE)");	addQuestion(q206);
        Question q207 = new Question("H8NCOA2bK6k", "윈드플라워","windflower","Wind flower - 마마무 (MAMAMOO)",2018, "ㅇㄷ ㅍㄹㅇ / w**d f**w**","마마무 (MAMAMOO)");	addQuestion(q207);
        Question q208 = new Question("I5_BQAtwHws", "유앤아이","youandi","YOU AND I - Dreamcatcher(드림캐쳐)",2018, "ㅇ ㅇ ㅇㅇ / y** a** i","Dreamcatcher(드림캐쳐)");	addQuestion(q208);
        Question q209 = new Question("F4qfN5UeFvQ", "주인공","heroine","주인공 (Heroine) - SUNMI(선미)",2018, "ㅈㅇㄱ / h**o**e","SUNMI(선미)");	addQuestion(q209);
        Question q210 = new Question("rW9r_1ys2ec", "바나나알러지원숭이","bananaallergymonkey","바나나 알러지 원숭이 (Banana allergy monkey) - OH MY GIRL BANHANA(오마이걸 반하나)",2018, "ㅂㄴㄴ ㅇㄹㅈ ㅇㅅㅇ / b**a** a**e**y m**k**","OH MY GIRL BANHANA(오마이걸 반하나)");	addQuestion(q210);
        Question q211 = new Question("LbWt67vVNgc", "매일봐요","everyday","매일 봐요 (Everyday) - 마마무 (MAMAMOO)",2018, "ㅁㅇ ㅂㅇ / e**r* d**","마마무 (MAMAMOO)");	addQuestion(q211);
        Question q212 = new Question("0N9U2hs03Tc", "러브레시피","loverecipe","Love Recipe - 벤 (BEN)",2018, "ㄹㅂ ㄹㅅㅍ / l**e r**i**","벤 (BEN)");	addQuestion(q212);
        Question q213 = new Question("9mQk7Evt6Vs", "라타타","latata","LATATA -  (G)I-DLE ((여자)아이들)",2018, "ㄹㅌㅌ / l**a**","(여자)아이들((G)I-DLE)");	addQuestion(q213);
        Question q214 = new Question("hF6Wds75rjg", "블랙드레스","blackdress","BLACK DRESS - CLC (씨엘씨)",2018, "ㅂㄹ ㄷㄹㅅ / b**c* d**s*","CLC (씨엘씨)");	addQuestion(q214);
        Question q215 = new Question("jd2DxTR0znU", "인디고","indigo","IndiGO - JUSTHIS(저스디스), Kid Milli, NO:EL, Young B(영비)",2018, "ㅇㄷㄱ / i**i**","JUSTHIS(저스디스), Kid Milli, NO:EL, Young B(영비)");	addQuestion(q215);
        Question q216 = new Question("8n6pRkpVsyU", "노에어","noair","No Air - THE BOYZ (더보이즈)",2018, "ㄴ ㅇㅇ / n* a**","THE BOYZ (더보이즈)");	addQuestion(q216);
        Question q217 = new Question("1icPJAhI2TA", "미안해","lie","미안해 (lie) - 양다일 (Yang Da Il)",2018, "ㅁㅇㅎ / l** ","양다일 (Yang Da Il)");	addQuestion(q217);
        Question q218 = new Question("Y90uGW5z2MM", "청개구리","naughtyboy","청개구리 (Naughty boy) - 펜타곤(PENTAGON)",2018, "ㅊㄱㄱㄹ / n**g**y b**","펜타곤(PENTAGON)");	addQuestion(q218);
        Question q219 = new Question("uexk7jWXYmU", "꽃이야","myflower","꽃이야 (Myflower) - JBJ",2018, "ㄲㅇㅇ / m**l**e*","JBJ");	addQuestion(q219);
        Question q220 = new Question("9iPLjmz3_U4", "여름여름해","sunnysummer","여름여름해 (Sunny Summer) - 여자친구 (GFRIEND)",2018, "ㅇㄹㅇㄹㅎ / s**n* s**m**","여자친구 (GFRIEND)");	addQuestion(q220);
        Question q221 = new Question("42iMZrYDEM4", "이별하러가는길","thewayyosaygoodbye","이별하러 가는 길 (The Way To Say Goodbye) - 임한별(Onestar)",2018, "ㅇㅂㅎㄹ ㄱㄴ ㄱ / t** w** t* s** g**d**e","임한별(Onestar)");	addQuestion(q221);
        Question q222 = new Question("QIN5_tJRiyY", "비밀정원","secretgarden","비밀정원 (Secret Garden) - 오마이걸 (OH MY GIRL)",2018, "ㅂㅁㅈㅇ / s**r** g**d**","오마이걸 (OH MY GIRL)");	addQuestion(q222);
        Question q223 = new Question("QG8bUKBT9FI", "셀피쉬","selfish","SELFISH (Feat. SEULGI(슬기) Of Red Velvet) - Moon Byul (문별)",2018, "ㅅㅍㅅ / s**f**h","Moon Byul (문별)");	addQuestion(q223);
        Question q224 = new Question("kMRLzSQorK0", "답장","reply","답장 (Reply) - 김동률 (KIM DONG RYUL)",2018, "ㄷㅈ / r**l*","김동률 (KIM DONG RYUL)");	addQuestion(q224);
        Question q225 = new Question("3w5iMGSHvsE", "첫사랑","firstlove","첫사랑 (first love) - 에피톤 프로젝트 (Epitone Project)",2018, "ㅊㅅㄹ / f**s* l**e","에피톤 프로젝트 (Epitone Project)");	addQuestion(q225);
        Question q226 = new Question("RrvdjyIL0fA", "불꽃놀이","rememberme","불꽃놀이 (REMEMBER ME) - 오마이걸 (OH MY GIRL)",2018, "ㅂㄲㄴㅇ / r**e**e* m*","오마이걸 (OH MY GIRL)");	addQuestion(q226);
        Question q227 = new Question("AJqhKWo89FQ", "윈디데이","windyday","WINDY DAY - 오마이걸 (OH MY GIRL)",2018, "ㅇㄷ ㄷㅇ / w**d* d**","오마이걸 (OH MY GIRL)");	addQuestion(q227);
        Question q228 = new Question("D2MhwXZ8IgM", "예쁜게죄","ohmymistake","예쁜 게 죄 (Oh! My mistake) - 에이프릴(APRIL)",2018, "ㅇㅃ ㄱ ㅈ / o* m* m**t**e","에이프릴(APRIL)");	addQuestion(q228);
        Question q229 = new Question("Ks6I2rtI4ms", "부를게","callyourname","부를게 (Call Your Name) - JBJ",2018, "ㅂㄹㄱ / c**l y**r n**e","JBJ");	addQuestion(q229);
        Question q230 = new Question("Uh_6PY9am_0", "파랑새","thebluebird","파랑새 (The Blue Bird) - 에이프릴 (APRIL)",2018, "ㅍㄹㅅ / t** b**e b**d","에이프릴 (APRIL)");	addQuestion(q230);
        Question q231 = new Question("3Uyl_ifBiLE", "잇츠유","itsyou","It's you (Feat.ZICO) - 샘김(Sam Kim)",2018, "ㅇㅊㅇ / i** y**","샘김(Sam Kim)");	addQuestion(q231);
        Question q232 = new Question("Nu2yQ1zYDYU", "빛나리","shine","빛나리(Shine) - 펜타곤(PENTAGON)",2018, "ㅂㄴㄹ / s**n*","펜타곤(PENTAGON)");	addQuestion(q232);
        Question q233 = new Question("fJubafP3IMI", "가끔이러다","sometimes","가끔 이러다 (Sometimes) - Punch(펀치)",2019, "ㄱㄲ ㅇㄹㄷ / s**e**m**","Punch(펀치)");	addQuestion(q233);
        Question q234 = new Question("Cp56JdkmE9s", "고고베베","gogobebe","고고베베 (gogobebe) - 마마무 (MAMAMOO)",2019, "ㄱㄱㅂㅂ / g**o**b*","마마무 (MAMAMOO)");	addQuestion(q234);
        Question q235 = new Question("j2aQ_NqeTNw", "괜찮아도괜찮아","thatsokay","괜찮아도 괜찮아 (That's okay) - 디오 (D.O.)",2019, "ㄱㅊㅇㄷ ㄱㅊㅇ / t**t* o**y","디오 (D.O.)");	addQuestion(q235);
        Question q236 = new Question("Xp8Ep1W-azw", "굿바이","goodbye","굿바이 (Goodbye) - Park Hyo Shin(박효신) ",2019, "ㄱㅂㅇ / g**d**e","박효신(Park Hyo Shin)");	addQuestion(q236);
        Question q237 = new Question("lHEOj3d7YS4", "그대라는시","allaboutyou","그대라는 시 (All about you) - 태연 (TAEYEON)",2019, "ㄱㄷㄹㄴ ㅅ / a** a**u* y**","태연 (TAEYEON)");	addQuestion(q237);
        Question q238 = new Question("hNnoi32CyrA", "그러나","however","그러나 (however) - 10cm",2019, "ㄱㄹㄴ / h**e**r","10cm");	addQuestion(q238);
        Question q239 = new Question("AsXxuIdpkWM", "나만봄","bom","나만, 봄 (Bom) - BOL4(볼빨간사춘기)",2019, "ㄴㅁ ㅂ / b*m","BOL4(볼빨간사춘기)");	addQuestion(q239);
        Question q240 = new Question("OoMIAo0a2TA", "나빠","nappa","나빠 (NAPPA) - Crush(크러쉬)",2019, "ㄴㅃ / n**p*","Crush(크러쉬)");	addQuestion(q240);
        Question q241 = new Question("LrggmyDhWBo", "날라리","lalalay","날라리 (LALALAY) - SUNMI(선미)",2019, "ㄴㄹㄹ / l**a**y","SUNMI(선미)");	addQuestion(q241);
        Question q242 = new Question("84R5AS0tfkQ", "너에게못했던내마지막말은","unspokenwords","너에게 못했던 내 마지막 말은 (Unspoken Words) - 다비치 (DAVICHI)",2019, "ㄴㅇㄱ ㅁㅎㄷ ㄴ ㅁㅈㅁ ㅁㅇ / u**p**e* w**d*","다비치 (DAVICHI)");	addQuestion(q242);
        Question q243 = new Question("GQqyCeKf8rw", "누구없소","noone","누구 없소 (NO ONE) - LEE HI",2019, "ㄴㄱ ㅇㅅ / n* o**","LEE HI");	addQuestion(q243);
        Question q244 = new Question("kFhf7pjRRjA", "늦은밤너의집앞골목길에서","latenight","늦은 밤 너의 집 앞 골목길에서 (Late Night) - Noel(노을)",2019, "ㄴㅇ ㅂ ㄴㅇ ㅈ ㅇ ㄱㅁㄱㅇㅅ / l**e n**h*","Noel(노을)");	addQuestion(q244);
        Question q245 = new Question("eMZmNisWFvM", "니소식","yourregards","니 소식 (Your regards) - 송하예 Ha Yea Song",2019, "ㄴ ㅅㅅ / y**r r**a**s","송하예 Ha Yea Song");	addQuestion(q245);
        Question q246 = new Question("udGwca1HBM4", "다섯번째계절","thefifthseason","다섯 번째 계절 (The fifth season) - OH MY GIRL(오마이걸)",2019, "ㄷㅅ ㅂㅉ ㄱㅈ / t** f**t* s**s**","OH MY GIRL(오마이걸)");	addQuestion(q246);
        Question q247 = new Question("pNfTK39k55U", "달라달라","dalladalla","달라달라(DALLA DALLA) - ITZY(있지)",2019, "ㄷㄹㄷㄹ / d**l* d**l*","ITZY(있지)");	addQuestion(q247);
        Question q248 = new Question("yOZNN5xrK2o", "당신과는천천히","everymomentwithyou","당신과는 천천히 (every moment with you) - 장범준 (Beom June Jang)",2019, "ㄷㅅㄱㄴ ㅊㅊㅎ / e**r* m**e** w**h y**","장범준 (Beom June Jang)");	addQuestion(q248);
        Question q249 = new Question("lpka6ymCkIY", "떨어지는낙엽까지도","fallingleavesarebeautiful","떨어지는 낙엽까지도 (Falling Leaves are Beautiful) - 헤이즈 (Heize)",2019, "ㄸㅇㅈㄴ ㄴㅇㄲㅈㄷ / f**l**g l**v** a** b**u**f**","헤이즈 (Heize)");	addQuestion(q249);
        Question q250 = new Question("ir7G_H0LFJw", "띵","dding","띵 (DDING) - Jvcki Wai, Young B(영비), Osshun Gum(오션검), Han Yo Han(한요한)",2019, "ㄸ / d**n*","Jvcki Wai, Young B(영비), Osshun Gum(오션검), Han Yo Han(한요한)");	addQuestion(q250);
        Question q251 = new Question("6oanIo_2Z4Q", "라이언","lion","LION - (여자)아이들((G)I-DLE)",2019, "ㄹㅇㅇ / l**n","(여자)아이들((G)I-DLE)");	addQuestion(q251);
        Question q252 = new Question("ScSn235gQx0", "멍청이","twit","멍청이 (TWIT) - Hwa Sa(화사)",2019, "ㅁㅊㅇ / t**t","Hwa Sa(화사)");	addQuestion(q252);
        Question q253 = new Question("lOrU0MH0bMk", "메테오","meteor","METEOR - 창모 CHANGMO",2019, "ㅁㅌㅇ / m**e**","창모 CHANGMO");	addQuestion(q253);
        Question q254 = new Question("_-QY40Reub8", "뭐해","whatareyouupto","뭐해 (What are you up to) - 강다니엘(KANGDANIEL)",2019, "ㅁㅎ / w**t a** y** u* t*","강다니엘(KANGDANIEL)");	addQuestion(q254);
        Question q255 = new Question("PALjhRpnfbk", "밀리언스","millions","MILLIONS - WINNER",2019, "ㅁㄹㅇㅅ / m**l**n*","WINNER");	addQuestion(q255);
        Question q256 = new Question("oDJ4ct59NC4", "벌스데이","birthday","Birthday - JEON SOMI (전소미)",2019, "ㅂㅅㄷㅇ / b**t**a*","JEON SOMI (전소미)");	addQuestion(q256);
        Question q257 = new Question("DsouXE31I6k", "봄","spring","봄(Spring) - 박봄(PARK BOM)",2019, "ㅂ / s**i**","박봄(PARK BOM)");	addQuestion(q257);
        Question q258 = new Question("eP4ga_fNm-E", "불티","spark","불티 (Spark) - TAEYEON (태연)",2019, "ㅂㅌ / s**r*","TAEYEON (태연)");	addQuestion(q258);
        Question q259 = new Question("D1PvIWdJ8xo", "블루밍","blueming","블루밍 (Blueming) - IU(아이유)",2019, "ㅂㄹㅁ / b**e**n*","IU(아이유)");	addQuestion(q259);
        Question q260 = new Question("gWHgpJ3m-x8", "비가내리는날에는","onarainyday","비가 내리는 날에는 (On A Rainy Day) - YOUNHA(윤하)",2019, "ㅂㄱ ㄴㄹㄴ ㄴㅇㄴ / o* a r**n* d**","YOUNHA(윤하)");	addQuestion(q260);
        Question q261 = new Question("6eEZ7DJMzuk", "비올레타","violeta","비올레타 (Violeta) - IZ*ONE (아이즈원)",2019, "ㅂㅇㄹㅌ / v**l**a","IZ*ONE (아이즈원)");	addQuestion(q261);
        Question q262 = new Question("4HG_CJzyX6A", "사계","fourseasons","사계 (Four Seasons) - TAEYEON (태연)",2019, "ㅅㄱ / f**r s**s**s","TAEYEON (태연)");	addQuestion(q262);
        Question q263 = new Question("kXAvbHPdTZ0", "사랑에연습이있었다면","iftherewaspracticeinlove","사랑에 연습이 있었다면 (If there was practice in love) - Lim Jae Hyun(임재현)",2019, "ㅅㄹㅇ ㅇㅅㅇ ㅇㅇㄷㅁ / i* t**r* w** p**c**c* i* l**e","Lim Jae Hyun(임재현)");	addQuestion(q263);
        Question q264 = new Question("tlHTOlnPcbs", "사랑이식었다고말해도돼","mylovehasfadedaway","사랑이 식었다고 말해도 돼 (My love has faded away) - Monday Kiz(먼데이 키즈)",2019, "ㅅㄹㅇ ㅅㅇㄷㄱ ㅁㅎㄷ ㄷ / m* l**e h** f**e* a**y","Monday Kiz(먼데이 키즈)");	addQuestion(q264);
        Question q265 = new Question("JrOrlhjIYVk", "사월이지나면우리헤어져요","beautifulgoodbye","사월이 지나면 우리 헤어져요 (Beautiful goodbye) - 첸 (CHEN)",2019, "ㅅㅇㅇ ㅈㄴㅁ ㅇㄹ ㅎㅇㅈㅇ / b**u**f** g**d**e","첸 (CHEN)");	addQuestion(q265);
        Question q266 = new Question("A0gP4id3Gxc", "서울밤","seoulnight","서울 밤 (Seoul Night) - URBAN ZAKAPA(어반자카파)",2019, "ㅅㅇ ㅂ / s**u* n**h*","URBAN ZAKAPA(어반자카파)");	addQuestion(q266);
        Question q267 = new Question("2cevbhEqQF4", "세뇨리따","senorita","Senorita - (여자)아이들((G)I-DLE)",2019, "ㅅㄴㄹㄸ / s**o**t*","(여자)아이들((G)I-DLE)");	addQuestion(q267);
        Question q268 = new Question("4Sd09Mruhnk", "솔직하게말해서나","tobehonest","솔직하게 말해서 나 (To be honest) - Kim na young(김나영)",2019, "ㅅㅈㅎㄱ ㅁㅎㅅ ㄴ / t* b* h**e**","Kim na young(김나영)");	addQuestion(q268);
        Question q269 = new Question("47Vz-ptyKbQ", "술이달다","lovedrunk","술이 달다 (LOVEDRUNK) - EPIK HIGH (에픽하이)",2019, "ㅅㅇ ㄷㄷ / l**e d**n*","EPIK HIGH (에픽하이)");	addQuestion(q269);
        Question q270 = new Question("XcbEM7j_ARQ", "술이문제야","drunkonlove","술이 문제야 (Drunk On Love) - JANG HYEJIN (장혜진), YUN MIN SOO (윤민수)",2019, "ㅅㅇ ㅁㅈㅇ / d**n* o* l**e","JANG HYEJIN (장혜진), YUN MIN SOO (윤민수)");	addQuestion(q270);
        Question q271 = new Question("x95oZNxW5Rc", "슈퍼휴먼","superhuman","Superhuman - NCT 127 (엔시티 127)",2019, "ㅅㅍㅎㅁ / s**e* h**a*","NCT 127 (엔시티 127)");	addQuestion(q271);
        Question q272 = new Question("deV_DmHKwjc", "스내핑","snapping","Snapping - 청하 (CHUNG HA)",2019, "ㅅㄴㅍ / s**p**n*","청하 (CHUNG HA)");	addQuestion(q272);
        Question q273 = new Question("ij0SQZcqnPU", "신청곡","songrequest","신청곡 (Song request) - LeeSoRa(이소라)",2019, "ㅅㅊㄱ / s**g r**u**t","LeeSoRa(이소라)");	addQuestion(q273);
        Question q274 = new Question("QUXNXYB1tq0", "십이월이십오일의고백","mychristmaswish","십이월 이십오일의 고백 (My christmas wish) - Jung Seung Hwan(정승환)",2019, "ㅅㅇㅇ ㅇㅅㅇㅇㅇ ㄱㅂ / m* c**i**m** w**h","Jung Seung Hwan(정승환)");	addQuestion(q274);
        Question q275 = new Question("uR8Mrt1IpXg", "싸이코","psycho","Psycho - Red Velvet (레드벨벳)",2019, "ㅆㅇㅋ / p**c**","Red Velvet (레드벨벳)");	addQuestion(q275);
        Question q276 = new Question("Pm0_G8Zl0ek", "아예","ahyeah","아예 (AH YEAH) - WINNER",2019, "ㅇㅇ / a* y**h","WINNER");	addQuestion(q276);
        Question q277 = new Question("zndvqTc4P9I", "아이씨","icy","ICY - ITZY(있지)",2019, "ㅇㅇㅆ / i**","ITZY(있지)");	addQuestion(q277);
        Question q278 = new Question("m3DZsBw5bnE", "어떻게이별까지사랑하겠어널사랑하는거지","howcanilovetheheartbreakyouretheoneilove","어떻게 이별까지 사랑하겠어, 널 사랑하는 거지(How can I love the heartbreak, you`re the one I love) - AKMU",2019, "ㅇㄸㄱ ㅇㅂㄲㅈ ㅅㄹㅎㄱㅇ ㄴ ㅅㄹㅎㄴ ㄱㅈ / h** c** i l**e t** h**r**r**k y**r* t** o** i l**e","AKMU");	addQuestion(q278);
        Question q279 = new Question("3C3hIJg4rHo", "엘리게이터","alligator","Alligator - MONSTA X 몬스타엑스",2019, "ㅇㄹㄱㅇㅌ / a**i**t**","MONSTA X 몬스타엑스");	addQuestion(q279);
        Question q280 = new Question("VpaUh_BGqE0", "옥탑방","rooftop","옥탑방 (Rooftop) - N.Flying(엔플라잉)",2019, "ㅇㅌㅂ / r**f**p","N.Flying(엔플라잉)");	addQuestion(q280);
        Question q281 = new Question("uxmP4b2a0uY", "옵세션","obsession","Obsession - EXO (엑소)",2019, "ㅇㅅㅅ / o**e**i**","EXO (엑소)");	addQuestion(q281);
        Question q282 = new Question("AtNBhPxVwh0", "왓어라이프","whatalife","What a life - 세훈&찬열 (EXO-SC)",2019, "ㅇ ㅇ ㄹㅇㅍ / w**t a l**e","세훈&찬열 (EXO-SC)");	addQuestion(q282);
        Question q283 = new Question("I66oFXdf0KU", "우오","uhoh","Uh-Oh - (여자)아이들((G)I-DLE)",2019, "ㅇㅇ / u* o*","(여자)아이들((G)I-DLE)");	addQuestion(q283);
        Question q284 = new Question("mrAIqeULUL0", "워커홀릭","workaholic","워커홀릭 (Workaholic) - BOL4(볼빨간사춘기)",2019, "ㅇㅋㅎㄹ / w**k**o**c","BOL4(볼빨간사춘기)");	addQuestion(q284);
        Question q285 = new Question("ByHNlfmmT-w", "위돈톡투게더","wedonttalktogether","We don't talk together - 헤이즈 (Heize)",2019, "ㅇ ㄷ ㅌ ㅌㄱㄷ / w* d**t t**k t**e**e*","헤이즈 (Heize)");	addQuestion(q285);
        Question q286 = new Question("PdDfuWJc9dA", "위올라이","wealllie","We All Lie - 하진 (Ha Jin)",2019, "ㅇ ㅇ ㄹㅇ / w* a** l**","하진 (Ha Jin)");	addQuestion(q286);
        Question q287 = new Question("vHS9E6JFja8", "음파음파","umpahumpah","음파음파 (Umpah Umpah) - Red Velvet (레드벨벳)",2019, "ㅇㅍㅇㅍ /u**a* u**a*","Red Velvet (레드벨벳)");	addQuestion(q287);
        Question q288 = new Question("499YUeNoYVE", "응응","eungeung","응응(%%)(Eung Eung) - Apink(에이핑크)",2019, "ㅇㅇ / e**g e**g","Apink(에이핑크)");	addQuestion(q288);
        Question q289 = new Question("MBSpoTozBdg", "이노래가클럽에서나온다면","fireup","이 노래가 클럽에서 나온다면 (Fire up)  - 우디 (Woody)",2019, "ㅇ ㄴㄹㄱ ㅋㄹㅇㅅ ㄴㅇㄷㅁ / f**e u*","우디 (Woody)");	addQuestion(q289);
        Question q290 = new Question("6tl-MG38-0E", "이클립스","eclipse","ECLIPSE - GOT7(갓세븐)",2019, "ㅇㅋㄹㅅ / e**i**e","GOT7(갓세븐)");	addQuestion(q290);
        Question q291 = new Question("SKAsHxi1Tlc", "있어희미하게","justus2","있어 희미하게 (Just us 2) - 세훈&찬열 (EXO-SC)",2019, "ㅇㅇ ㅎㅁㅎㄱ / j**t u* 2","세훈&찬열 (EXO-SC)");	addQuestion(q291);
        Question q292 = new Question("XsX3ATc3FbA", "작은것들을위한시","boywithluv","작은 것들을 위한 시 (Boy With Luv) - BTS (방탄소년단)",2019, "ㅈㅇ ㄱㄷㅇ ㅇㅎ ㅅ / b** w**h l**","BTS (방탄소년단)");	addQuestion(q292);
        Question q293 = new Question("1KFQdzSbbKA", "조금취했어","imalittledrunk","조금 취했어 (I'm a little drunk) - Lim Jae Hyun(임재현)",2019, "ㅈㄱ ㅊㅎㅇ / i* a l**t** d**n*","Lim Jae Hyun(임재현)");	addQuestion(q293);
        Question q294 = new Question("GdoNGNe5CSg", "주저하는연인들을위해","forloverswhohesitate","주저하는 연인들을 위해 (for lovers who hesitate) - JANNABI(잔나비)",2019, "ㅈㅈㅎㄴ ㅇㅇㄷㅇ ㅇㅎ / f** l**e** w** h**i**t*","JANNABI(잔나비)");	addQuestion(q294);
        Question q295 = new Question("YBnGBb1wg98", "짐살라빔","zimzalabim","짐살라빔 (Zimzalabim) - Red Velvet (레드벨벳)",2019, "ㅈㅅㄹㅂ /z**z**a**m","Red Velvet (레드벨벳)");	addQuestion(q295);
        Question q296 = new Question("1YZzSkP6KZo", "첫겨울이니까","firstwinter","첫 겨울이니까 (First Winter) - SUNG SI KYUNG(성시경), IU(아이유)",2019, "ㅊ ㄱㅇㅇㄴㄲ / f**s* w**t**"," SUNG SI KYUNG(성시경), IU(아이유)");	addQuestion(q296);
        Question q297 = new Question("2S24-y0Ij3Y", "킬디스러브","killthislove","Kill This Love - BLACKPINK",2019, "ㅋ ㄷㅅ ㄹㅂ / k**l t**s l**e","BLACKPINK");	addQuestion(q297);
        Question q298 = new Question("8MAYpc7Tjt0", "포장마차","phocha","포장마차 (Phocha) - Inwook Hwang(황인욱)",2019, "ㅍㅈㅁㅊ / p**c**","Inwook Hwang(황인욱)");	addQuestion(q298);
        Question q299 = new Question("TbPHPX3hSPA", "해야","sunrise","해야 (Sunrise) - GFRIEND(여자친구)",2019, "ㅎㅇ / s**r**e","GFRIEND(여자친구)");	addQuestion(q299);
        Question q300 = new Question("Y99b-ITzPU4", "허전해","empty","허전해 (empty) - Paul Kim(폴킴)",2019, "ㅎㅈㅎ / e**t*","Paul Kim(폴킴)");	addQuestion(q300);
        Question q301 = new Question("7tO_eJek6D8", "헤어져줘서고마워","thankyouforgoodbye","헤어져줘서 고마워 (Thank you for Goodbye) - 벤 (BEN)",2019, "ㅎㅇㅈㅈㅅ ㄱㅁㅇ / t**n* y** f** g**d**e","벤 (BEN)");	addQuestion(q301);
        Question q302 = new Question("U_dpIqCDcZk", "헤어지자","goodbye","헤어지자(Good bye) - 휘인 (Whee In)",2019, "ㅎㅇㅈㅈ / g**d**e","휘인 (Whee In)");	addQuestion(q302);
        Question q303 = new Question("YBEUXfT7_48", "흔들리는꽃들속에서네샴푸향이느껴진거야","yourshampooscentintheflowers","흔들리는 꽃들 속에서 네 샴푸향이 느껴진거야 (Your Shampoo Scent In The Flowers)  - 장범준 (Beom June Jang)",2019, "ㅎㄷㄹㄴ ㄲㄷㅅㅇㅅ ㄴ ㅅㅍㅎㅇ ㄴㄲㅈㄱㅇ / y**r s**m**o s**n* i* t** f**w**s","장범준 (Beom June Jang)");	addQuestion(q303);
        Question q304 = new Question("KhTeiaCezwM", "힙","hip","HIP - 마마무(MAMAMOO)",2019, "ㅎ / h**","마마무(MAMAMOO)");	addQuestion(q304);
        Question q305 = new Question("tsN-MkpiZB0", "암쏘핫","imsohot","I'm So Hot - MOMOLAND (모모랜드)",2019, "ㅇㅆㅎ / i* s* h**","MOMOLAND (모모랜드)");	addQuestion(q305);
        Question q306 = new Question("Zx58FMoPZEI", "바나나차차","bananachacha","바나나차차 (BANANA CHACHA) - 모모랜드 (MOMOLAND)",2019, "ㅂㄴㄴㅊㅊ / b**a** c**c**","MOMOLAND (모모랜드)");	addQuestion(q306);
        Question q307 = new Question("Y44vb5I3OAU", "밤밤","bombbomb","Bomb Bomb (밤밤) - KARD",2019, "ㅂㅂ / b**b b**b","KARD");	addQuestion(q307);
        Question q308 = new Question("Zll7O1v63aY", "열대야","fever","열대야 (Fever) - 여자친구 (GFRIEND)",2019, "ㅇㄷㅇ / f**e*","GFRIEND(여자친구)");	addQuestion(q308);
        Question q309 = new Question("CNeNwplE_aw", "누아르","noir","누아르 (Noir) - 선미(SUNMI)",2019, "ㄴㅇㄹ / n**r","선미(SUNMI)");	addQuestion(q309);
        Question q310 = new Question("k6msd9uh8nA", "발키리","valkyrie","발키리 (Valkyrie) - 원어스(ONEUS)",2019, "ㅂㅋㄹ / v**k**i*","원어스(ONEUS)");	addQuestion(q310);
        Question q311 = new Question("QTD_yleCK9Y", "번지","bungee","BUNGEE (Fall in Love) - OH MY GIRL (오마이걸)",2019, "ㅂㅈ / b**g**","OH MY GIRL (오마이걸)");	addQuestion(q311);
        Question q312 = new Question("ZvhpjERxJlQ", "노","no","No - CLC",2019, "ㄴ / n*","CLC");	addQuestion(q312);
        Question q313 = new Question("R3Fwdnij49o", "시간의바깥","abovethetime","시간의 바깥 (above the time) - 아이유(IU)",2019, "ㅅㄱㅇ ㅂㄲ / a**v* t** t**e","아이유(IU)");	addQuestion(q313);
        Question q314 = new Question("QOdbtSqv12g", "미라클","me","Me - CLC",2019, "ㅁ / m*","CLC");	addQuestion(q314);
        Question q315 = new Question("nUOeg1LYF7Y", "빔밤붐","bimbambum","빔밤붐 (BIM BAM BUM) - 로켓펀치 (Rocket Punch)",2019, "ㅂㅂㅂ / b** b** b**","로켓펀치 (Rocket Punch)");	addQuestion(q315);
        Question q316 = new Question("8n9wklIG9qU", "별보러갈래","starsoverme","별 보러 갈래? (Stars over me) - 볼빨간사춘기(BOL4)",2019, "ㅂ ㅂㄹ ㄱㄹ / s**r* o**r m*","BOL4(볼빨간사춘기)");	addQuestion(q316);
        Question q317 = new Question("3ymwOvzhwHs", "필스페셜","feelspecial","Feel Special - TWICE (트와이스)",2019, "ㅍ ㅅㅍㅅ / f**l s**c**l","TWICE(트와이스)");	addQuestion(q317);
        Question q318 = new Question("9T_uq_HpfyQ", "가을밤에든생각","athoughtonanautumnnight","가을밤에 든 생각 (A thought on an autumn night) - JANNABI(잔나비)",2020, "ㄱㅇㅂㅇ ㄷ ㅅㄱ / a t**u**t o* a* a**u** n**h*","JANNABI(잔나비)");	addQuestion(q318);
        Question q319 = new Question("L8UUYfe6-UA", "나비와고양이","leo","나비와 고양이 (Leo) - BOL4(볼빨간사춘기)",2020, "ㄴㅂㅇ ㄱㅇㅇ / l**","BOL4(볼빨간사춘기)");	addQuestion(q319);
        Question q320 = new Question("bho0m505qVA", "내게들려주고싶은말","dearme","내게 들려주고 싶은 말 (Dear Me) - TAEYEON (태연)",2020, "ㄴㄱ ㄷㄹㅈㄱ ㅅㅇ ㅁ / d**r m*","TAEYEON (태연)");	addQuestion(q320);
        Question q321 = new Question("tLGHKyZs0Gk", "너에게난나에게넌","metoyouyoutome","너에게 난, 나에게 넌 (Me to You, You to Me) - 미도와 파라솔 (Mido and Falasol)",2020, "ㄴㅇㄱ ㄴ ㄴㅇㄱ ㄴ / m* t* y** y** t* m*","미도와 파라솔 (Mido and Falasol)");	addQuestion(q321);
        Question q322 = new Question("vAa8R_ze6ZI", "놓아줘","letmego","놓아줘 (with 태연) - Crush(크러쉬)",2020, "ㄴㅇㅈ / l** m* g*","Crush(크러쉬)");	addQuestion(q322);
        Question q323 = new Question("tJQaUW36pMw", "눈누난나","nununana","눈누난나 (NUNU NANA) - Jessi (제시)",2020, "ㄴㄴㄴㄴ / n**u n**a","Jessi (제시)");	addQuestion(q323);
        Question q324 = new Question("FFmdTU4Cpr8", "다시난여기","hereiamagain","다시 난, 여기 (Here I Am Again) - 백예린 (Yerin Baek)",2020, "ㄷㅅ ㄴ ㅇㄱ / h**e i a* a**i*","백예린 (Yerin Baek)");	addQuestion(q324);
        Question q325 = new Question("ESKfHHtiSjs", "다시여기바닷가","beachagain","다시 여기 바닷가(Beach Again) - 싹쓰리(SSAK3)",2020, "ㄷㅅ ㅇㄱ ㅂㄷㄱ / b**c* a**i*","싹쓰리(SSAK3)");	addQuestion(q325);
        Question q326 = new Question("gdZLi9oWNZg", "다이너마이트","dynamite","Dynamite - BTS (방탄소년단)",2020, "ㄷㅇㄴㅁㅇㅌ / d**a**t*","BTS (방탄소년단)");	addQuestion(q326);
        Question q327 = new Question("WqzTRK5GPWQ", "덤더럼","dumhdurum","덤더럼(Dumhdurum) - Apink(에이핑크)",2020, "ㄷㄷㄹ / d**h**r**","Apink(에이핑크)");	addQuestion(q327);
        Question q329 = new Question("PXE2Ykf8fXQ", "돈터치미","donttouchme","DON'T TOUCH ME - REFUND SISTERS(환불원정대)",2020, "ㄷ ㅌㅊ ㅁ / d**t t**c* m*","REFUND SISTERS(환불원정대)");	addQuestion(q329);
        Question q330 = new Question("oaRTMjLdiDw", "돌핀","dolphin","Dolphin - OH MY GIRL(오마이걸)",2020, "ㄷㅍ / d**p**n","OH MY GIRL(오마이걸)");	addQuestion(q330);
        Question q331 = new Question("EXJfWc4JciQ", "둘만의세상으로가","letusgo","둘만의 세상으로 가 (Let Us Go) - Crush(크러쉬)",2020, "ㄷㅁㅇ ㅅㅅㅇㄹ ㄱ / l** u* g*","Crush(크러쉬)");	addQuestion(q331);
        Question q332 = new Question("dfl9KIX1WpU", "딩가딩가","dingga","딩가딩가 (Dingga) - 마마무 (MAMAMOO)",2020, "ㄷㄱㄷㄱ / d**g**","마마무 (MAMAMOO)");	addQuestion(q332);
        Question q333 = new Question("cO9DbwTF7rY", "라라리라라","lalalilala","LALALILALA - APRIL(에이프릴)",2020, "ㄹㄹㄹㄹㄹ / l**a**l**a","APRIL(에이프릴)");	addQuestion(q333);
        Question q334 = new Question("-5q5mZbe3V8", "라이프고즈온","lifegoeson","Life Goes On - BTS (방탄소년단)",2020, "ㄹㅇㅍ ㄱㅈ ㅇ / l**e g**s o*","BTS (방탄소년단)");	addQuestion(q334);
        Question q335 = new Question("dM5gMsePq-o", "러브쉽","loveship","Loveship - Paul Kim(폴킴), CHUNG HA(청하)",2020, "ㄹㅂㅅ / l**e**i*","Paul Kim(폴킴), CHUNG HA(청하)");	addQuestion(q335);
        Question q336 = new Question("dyRsYk0LyA8", "러브식걸즈","lovesickgirls","Lovesick Girls - BLACKPINK",2020, "ㄹㅂㅅ ㄱㅈ / l**e**c* g**l*","BLACKPINK");	addQuestion(q336);
        Question q337 = new Question("brZRDjFIFJs", "마리아","maria","마리아 (Maria) - 화사 (Hwa Sa)",2020, "ㅁㄹㅇ / m**i*","화사 (Hwa Sa)");	addQuestion(q337);
        Question q338 = new Question("mH0_XpSHkZo", "모어앤모어","moreandmore","MORE & MORE - TWICE(트와이스)",2020, "ㅁㅇ ㅇ ㅁㅇ / m**e a** m**e","TWICE(트와이스)");	addQuestion(q338);
        Question q339 = new Question("Ujb-gvqsoi0", "몬스터","monster","Monster - Red Velvet - IRENE & SEULGI",2020, "ㅁㅅㅌ / m**s**r","Red Velvet - IRENE & SEULGI");	addQuestion(q339);
        Question q340 = new Question("CQ7p6kYal5A", "밤하늘의저별처럼","midnight","밤하늘의 저 별처럼 (Midnight) - Heize(헤이즈), Punch(펀치)",2020, "ㅂㅎㄴㅇ ㅈ ㅂㅊㄹ / m**n**h*","Heize(헤이즈), Punch(펀치)");	addQuestion(q340);
        Question q341 = new Question("Is7glC9Jp7Q", "보라빛밤","pporappippam","보라빛 밤 (pporappippam) - 선미 (SUNMI)",2020, "ㅂㄹㅂ ㅂ / p**r**p**p**","선미 (SUNMI)");	addQuestion(q341);
        Question q342 = new Question("9OADFEl-QQ0", "보자보자","letssee","보자보자 (Let's see) - 머쉬베놈 (MUSHVENOM)",2020, "ㅂㅈㅂㅈ / l**s s**","머쉬베놈 (MUSHVENOM)");	addQuestion(q342);
        Question q343 = new Question("rOCymN-Rwiw", "사랑하게될줄알았어","iknewilove","사랑하게 될 줄 알았어 (I Knew I Love) - 전미도 (JEON MI DO)",2020, "ㅅㄹㅎㄱ ㄷ ㅈ ㅇㅇㅇ / i k**w i l**e","전미도 (JEON MI DO)");	addQuestion(q343);
        Question q344 = new Question("Ygrv55VRRas", "사랑하자","letslove","사랑, 하자 (Let’s Love) - SUHO 수호",2020, "ㅅㄹㅎㅈ / l**s l**e","SUHO 수호");	addQuestion(q344);
        Question q345 = new Question("iDjQSdN_ig8", "살짝설렜어","nonstop","살짝 설렜어 (Nonstop) - OH MY GIRL(오마이걸)",2020, "ㅅㅉ ㅅㄹㅇ / n**s**p","OH MY GIRL(오마이걸)");	addQuestion(q345);
        Question q346 = new Question("AAOOKbk-knM", "숲의아이","bonvoyage","숲의 아이 (Bon voyage) - 유아(YooA)",2020, "ㅅㅇ ㅇㅇ / b** v**a**","유아(YooA)");	addQuestion(q346);
        Question q347 = new Question("YPFIh0dfYfw", "스테이투나잇","staytonight","Stay Tonight - 청하 (CHUNG HA)",2020, "ㅅㅌㅇ ㅌㄴㅇ / s**y t**i**t","청하 (CHUNG HA)");	addQuestion(q347);
        Question q348 = new Question("BfWqUjunXXU", "실버판테온","silverpantheon","실버판테온 (SILVERPantheon) - 장범준 (Beom June Jang)",2020, "ㅅㅂㅍㅌㅇ / s**v**p**t**o*","장범준 (Beom June Jang)");	addQuestion(q348);
        Question q349 = new Question("oKUEbsJDvuo", "썸머헤이트","summerhate","Summer Hate (Feat. Rain(비)) - 지코 (ZICO)",2020, "ㅆㅁ ㅎㅇㅌ / s**m** h**e","지코 (ZICO)");	addQuestion(q349);
        Question q350 = new Question("3DOkxQ3HDXE", "아로하","aloha","아로하 (Aloha) - 조정석 (CHO JUNG SEOK)",2020, "ㅇㄹㅎ / a**h*","조정석 (CHO JUNG SEOK)");	addQuestion(q350);
        Question q351 = new Question("UuV2BmJ1p_I", "아무노래","anysong","아무노래 (Any song) - 지코 (ZICO)",2020, "ㅇㅁ ㄴㄹ / a** s**g","지코 (ZICO)");	addQuestion(q351);
        Question q352 = new Question("vRXZj0DzXIA", "아이스크림","icecream","Ice Cream (with Selena Gomez) - BLACKPINK",2020, "ㅇㅇㅅㅋㄹ / i**c**a*","BLACKPINK");	addQuestion(q352);
        Question q353 = new Question("CM4CkVFmTds", "아이캔스탑미","icantstopme","I CAN'T STOP ME - TWICE(트와이스)",2020, "ㅇㅇ ㅋ ㅅㅌ ㅁ / i c**t s**p m*","TWICE(트와이스)");	addQuestion(q353);
        Question q354 = new Question("rVXeArOQIs4", "어떻게지내","ineedyou","어떻게 지내 (I Need You) - OVAN(오반)",2020, "ㅇㄸㄱ ㅈㄴ / i**e* y**","OVAN(오반)");	addQuestion(q354);
        Question q355 = new Question("x_JeRI2eK9o", "어웨이","away","AWay - Jeong Eun Ji(정은지)",2020, "ㅇㅇㅇ / a**y","Jeong Eun Ji(정은지)");	addQuestion(q355);
        Question q356 = new Question("TgOu00Mf3kI", "에잇","eight","에잇 (eight) - IU(아이유)",2020, "ㅇㅇ / e**h*","IU(아이유)");	addQuestion(q356);
        Question q357 = new Question("om3n2ni8luE", "오마이갓","ohmygod","Oh my god - (여자)아이들((G)I-DLE)",2020, "ㅇ ㅁㅇ ㄱ / o* m* g**","(여자)아이들((G)I-DLE)");	addQuestion(q357);
        Question q358 = new Question("mPVDGOVjRQ0", "온","on","ON - BTS (방탄소년단)",2020, "ㅇ / o*","BTS (방탄소년단)");	addQuestion(q358);
        Question q359 = new Question("lBYyAQ99ZFI", "왓유웨이팅포","whatyouwaitingfor","What You Waiting For - JEON SOMI (전소미)",2020, "ㅇ ㅇ ㅇㅇㅌ ㅍ / w**t y** w**t**g f**","JEON SOMI (전소미)");	addQuestion(q359);
        Question q360 = new Question("FEI_imQ1Eaw", "우리만남이","butillmissyou","우리 만남이 (But I'll Miss You) - Paul Kim(폴킴)",2020, "ㅇㄹ ㅁㄴㅇ / b** i** m**s y**","Paul Kim(폴킴)");	addQuestion(q360);
        Question q361 = new Question("fE2h3lGlOsk", "워너비","wannabe","WANNABE - ITZY(있지)",2020, "ㅇㄴㅂ / w**n**e","ITZY(있지)");	addQuestion(q361);
        Question q362 = new Question("zrsBjYukE8s", "웬위디스코","whenwedisco","When We Disco (Duet with 선미) - 박진영 (J.Y. Park)",2020, "ㅇ ㅇ ㄷㅅㅋ / w**n w* d**c*","박진영 (J.Y. Park)");	addQuestion(q362);
        Question q363 = new Question("sLqZxb4xjJw", "이제나만믿어요","trustinme","이제 나만 믿어요 (Trust in Me) - Lim Young Woong(임영웅)",2020, "ㅇㅈ ㄴㅁ ㅁㅇㅇ / t**s* i* m*","Lim Young Woong(임영웅)");	addQuestion(q363);
        Question q364 = new Question("k8gx-C7GCGU", "좀비","zombie","Zombie - DAY6(데이식스)",2020, "ㅈㅂ / z**b**","DAY6(데이식스)");	addQuestion(q364);
        Question q365 = new Question("hoLzH1revMg", "좋은사람있으면소개시켜줘","introducemeagoodperson","좋은 사람 있으면 소개시켜줘 (Introduce me a good person) - 조이 (JOY)",2020, "ㅈㅇ ㅅㄹ ㅇㅇㅁ ㅅㄱㅅㅋㅈ / i**r**u** m* a g**d p**s**","조이 (JOY)");	addQuestion(q365);
        Question q366 = new Question("3XUe2PsadEk", "처음처럼","bloom","처음처럼 (BLOOM) - M.C THE MAX(엠씨더맥스)",2020, "ㅊㅇㅊㄹ / b**o*","M.C THE MAX(엠씨더맥스)");	addQuestion(q366);
        Question q367 = new Question("d7W56dwrTlE", "크레이지","crazy","Crazy - APRIL(에이프릴)",2020, "ㅋㄹㅇㅈ / c**z*","APRIL(에이프릴)");	addQuestion(q367);
        Question q368 = new Question("qfeoX17dav0", "품","hug","품 (Hug) - BOL4(볼빨간사춘기)",2020, "ㅍ / h**","BOL4(볼빨간사춘기)");	addQuestion(q368);
        Question q369 = new Question("eDEFolvLn0A", "피에스타","fiesta","FIESTA - IZ*ONE (아이즈원)",2020, "ㅍㅇㅅㅌ / f**s**","IZ*ONE (아이즈원)");	addQuestion(q369);
        Question q370 = new Question("ioNng23DkIM", "하우유라이크댓","howyoulikethat","How You Like That - BLACKPINK",2020, "ㅎㅇ ㅇ ㄹㅇㅋ ㄷ / h** y** l**e t**t","BLACKPINK");	addQuestion(q370);
        Question q371 = new Question("VdeK_VsG9U0", "홀로","holo","홀로 (HOLO) - 이하이 (LEE HI)",2020, "ㅎㄹ / h**o","이하이 (LEE HI)");	addQuestion(q371);
        Question q372 = new Question("nnVjsos40qk", "환상동화","secretstoryoftheswan","환상동화 (Secret Story of the Swan) - IZ*ONE (아이즈원)",2020, "ㅎㅅㄷㅎ / s**r** s**r* o* t** s**n","IZ*ONE (아이즈원)");	addQuestion(q372);
        Question q373 = new Question("zRq_DlEzygk", "오늘의기분","todaysmood","Today's Mood(오늘의 기분) - CHEEZE(치즈)",2020, "ㅇㄴㅇ ㄱㅂ / t**a** m**d","CHEEZE(치즈)");	addQuestion(q373);
        Question q374 = new Question("5cZ4EDEZ0XQ", "잘할게","iwill","잘할게 (I will) - 이승기 (LEE SEUNG GI)",2020, "ㅈㅎㄱ / i w**l","이승기 (LEE SEUNG GI)");	addQuestion(q374);
        Question q375 = new Question("wUUiBCput2E", "스펠","spell","Spell - 효린(Hyolyn)",2020, "ㅅㅍ / s**l*","효린 (Hyolyn)");	addQuestion(q375);
        Question q376 = new Question("M33FPNp8-XE", "우아","wooah"," woo!ah!(우아!) _ Bad Girl",2020, "ㅇㅇ / w** a*","Bad Girl");	addQuestion(q376);
        Question q377 = new Question("SUKQpVGnKAM", "달링","darling","Darling - 양다일 (Yang Da Il)",2020, "ㄷㄹ / d**l**g","양다일 (Yang Da Il)");	addQuestion(q377);
        Question q378 = new Question("jv543Nk5s18", "염라","karma","염라(Karma) - 달의하루(Dareharu)",2020, "ㅇㄹ / k**m*","달의하루(Dareharu)");	addQuestion(q378);
        Question q379 = new Question("3vhA8njtoQg", "너로피어오라","flowering","너로피어오라(Flowering)",2020, "ㄴㄹㅍㅇㅇㄹ / f**w**i**","달의하루(Dareharu)");	addQuestion(q379);
        Question q380 = new Question("b-kGspYdntY", "이노래가뭐라고","thissong","이 노래가 뭐라고 (This Song) - 임한별 (Onestar)",2020, "ㅇ ㄴㄹㄱ ㅁㄹㄱ / t**s s**g","임한별 (Onestar)");	addQuestion(q380);
        Question q381 = new Question("LQOifePx_XQ", "라디오","radio","RADIO - HENRY(헨리)",2020, "ㄹㄷㅇ / r**i*","HENRY(헨리)");	addQuestion(q381);
        Question q382 = new Question("uJEKfFUbu0c", "뻔한남자","theordinaryman","뻔한남자 (The Ordinary Man) - 이승기 (LEE SEUNG GI)",2020, "ㅃㅎㄴㅈ / t** o**i**r* m**","이승기 (LEE SEUNG GI)");	addQuestion(q382);
        Question q383 = new Question("NuTNPV72rFo", "밤하늘의별을","shinystar","밤하늘의 별을 (Shiny Star) - 경서 (KyoungSeo)",2020, "ㅂㅎㄴㅇ ㅂㅇ / s**n* s**r","경서 (KyoungSeo)");	addQuestion(q383);
        Question q384 = new Question("lcRV2gyEfMo", "쏘배드","sobad","SO BAD - STAYC(스테이씨)",2020, "ㅆ ㅂㄷ / s* b**","STAYC(스테이씨)");	addQuestion(q384);
        Question q385 = new Question("ljdwsoYTq7I", "갓댓붐","gotthatboom","Got That Boom - 시크릿넘버 (SECRET NUMBER)",2020, "ㄱ ㄷ ㅂ / g** t**t b**m","시크릿넘버 (SECRET NUMBER)");	addQuestion(q385);
        Question q386 = new Question("ZcwORBWKl-U", "댄싱카툰","dancingcartoon","Dancing Cartoon - BOL4(볼빨간사춘기)",2020, "ㄷㅅ ㅋㅌ / d**c**g c**t**n","BOL4(볼빨간사춘기)");	addQuestion(q386);
        Question q387 = new Question("NEAwZaYIQ4A", "히어로","hero","HERO - Lim Young Woong(임영웅)",2020, "ㅎㅇㄹ / h**o","Lim Young Woong(임영웅)");	addQuestion(q387);
        Question q388 = new Question("jD_qe-ew-O0", "아야","aya","AYA - 마마무 (MAMAMOO)",2020, "ㅇㅇ / a**","마마무 (MAMAMOO)");	addQuestion(q388);
        Question q389 = new Question("UOCPm8qa0Lc", "러브킬라","lovekilla","Love Killa - 몬스타엑스(MONSTA X)",2020, "ㄹㅂ ㅋㄹ / l**e k**l*","몬스타엑스(MONSTA X)");	addQuestion(q389);
        Question q390 = new Question("Ktmd_xTQaU8", "노스텔지아","nostalgia","Nostalgia - DRIPPIN(드리핀)",2020, "ㄴㅅㅌㅈㅇ / n**t**g**","DRIPPIN(드리핀)");	addQuestion(q390);
        Question q391 = new Question("3QDIjIZPSiM", "너도아는","hangover","Hangover(너도 아는) - Paul Kim(폴킴)",2020, "ㄴㄷ ㅇㄴ / h**g**e*","Paul Kim(폴킴)");	addQuestion(q391);
        Question q392 = new Question("0-snXdhDs1w", "와이낫","whynot","Why Not - LOONA (이달의 소녀)",2020, "ㅇㅇ ㄴ / w** n**","LOONA (이달의 소녀)");	addQuestion(q392);
        Question q393 = new Question("1FqAoADnId4", "지그재그","zigzag","Zig Zag - Weeekly(위클리)",2020, "ㅈㄱ ㅈㄱ / z** z**","Weeekly(위클리)");	addQuestion(q393);
        Question q394 = new Question("nE3jUnYNFds", "펌프잇업","pumpitup","Pump It Up - Golden Child(골든차일드)",2020, "ㅍㅍ ㅇ ㅇ / p**p i* u*","Golden Child(골든차일드)");	addQuestion(q394);
        Question q395 = new Question("2swgQFESu9A", "트와일라잇","twilight","TWILIGHT - WEi(위아이)",2020, "ㅌㅇㅇㄹㅇ / t**l**h*","WEi(위아이)");	addQuestion(q395);
        Question q396 = new Question("IBMiaPKJCeI", "더스틸러","thestealer","The Stealer -THE BOYZ(더보이즈)",2020, "ㄷ ㅅㅌㄹ / t** s**a**r","THE BOYZ(더보이즈)");	addQuestion(q396);
        Question q397 = new Question("KBuvtmMfdN8", "워너비마이셀프","wannabemyself","WANNA BE MYSELF - 마마무 (MAMAMOO)",2020, "ㅇㄴㅂ ㅁㅇ ㅅㅍ / w**n* b* m**e**","마마무 (MAMAMOO)");	addQuestion(q397);
        Question q398 = new Question("WHkQtNBCRQo", "가을타나봐","fallinfall","가을 타나 봐 (Fall in Fall) - 이무진 (Lee Mujin)",2021, "ㄱㅇ ㅌㄴ ㅂ / f**l i* f**l","이무진 (Lee Mujin)");	addQuestion(q398);
        Question q399 = new Question("X3PFu82F_S8", "고백","goback","고백 (Go Back) - 10cm",2021, "ㄱㅂ / g* b**k","10cm");	addQuestion(q399);
        Question q400 = new Question("RIMZ0pZh2uk", "과제곡","theassignmentsong","과제곡 (The Assignment Song) - Lee Mujin(이무진)",2021, "ㄱㅈㄱ / t** a**i**m**t s**g","Lee Mujin(이무진)");	addQuestion(q400);
        Question q401 = new Question("ORYgMXQ8Kqg", "그냥안아달란말야","justhugme","그냥 안아달란 말야 (Just hug me) - 다비치 (DAVICHI)",2021, "ㄱㄴ ㅇㅇㄷㄹ ㅁㅇ / j**t h** m*","다비치 (DAVICHI)");	addQuestion(q401);
        Question q402 = new Question("Aui0ZKIaXVc", "꼬리","tail","꼬리(TAIL) - 선미 (SUNMI)",2021, "ㄲㄹ / t**l","선미 (SUNMI)");	addQuestion(q402);
        Question q403 = new Question("JrWQdHjOkvw", "나비효과","butterflyeffect","나비효과 (Butterfly Effect) - BOL4(볼빨간사춘기)",2021, "ㄴㅂㅎㄱ / b**t**f** e**e**","BOL4(볼빨간사춘기)");	addQuestion(q403);
        Question q404 = new Question("EtiPbWzUY9o", "낙하","nakka","낙하 (NAKKA) (with IU) - AKMU",2021, "ㄴㅎ / n**k*","AKMU");	addQuestion(q404);
        Question q405 = new Question("ZcnnUoyv-hs", "내얘기같아","basedonatruestory","내 얘기 같아 (Based On A True Story) - EPIK HIGH (에픽하이)",2021, "ㄴ ㅇㄱ ㄱㅇ / b**e* o* a t**e s**r*","EPIK HIGH (에픽하이)");	addQuestion(q405);
        Question q406 = new Question("4TWR90KJl84", "넥스트레벨","nextlevel","Next Level - Aespa",2021, "ㄴㅅㅌ ㄹㅂ / n**t l**e*","aespa (에스파)");	addQuestion(q406);
        Question q407 = new Question("HzOjwL7IP_o", "던던댄스","dundundance","Dun Dun Dance - 오마이걸(OH MY GIRL)",2021, "ㄷ ㄷ ㄷㅅ / d** d** d**c*","오마이걸(OH MY GIRL)");	addQuestion(q407);
        Question q408 = new Question("tg2uF3R_Ozo", "덤덤","dumbdumb","DUMB DUMB - JEON SOMI (전소미)",2021, "ㄷㄷ / d**b d**b","JEON SOMI (전소미)");	addQuestion(q408);
        Question q409 = new Question("p6OoY6xneI0", "돈콜미","dontcallme","Don't Call Me - SHINee 샤이니",2021, "ㄷ ㅋ ㅁ / d**t c**l m*","SHINee 샤이니");	addQuestion(q409);
        Question q410 = new Question("2IkoKhr6Tss", "돈파이트더필링","dontfightthefeeling","Don't fight the feeling - EXO (엑소)",2021, "ㄷ ㅍㅇㅌ ㄷ ㅍㄹ / d**t f**h* t** f**l**g","EXO (엑소)");	addQuestion(q410);
        Question q411 = new Question("nBxnh010LU8", "둘중에골라","summerorsummer","둘 중에 골라 (Summer or Summer) - 효린, 다솜 (HYOLYN, DASOM)",2021, "ㄷ ㅈㅇ ㄱㄹ / s**m** o* s**m**","효린, 다솜 (HYOLYN, DASOM)");	addQuestion(q411);
        Question q412 = new Question("N2p__-LRBNc", "뚫고지나가요","rightthroughme","뚫고 지나가요 (Right Through Me) - DAY6(데이식스)",2021, "ㄸㄱ ㅈㄴㄱㅇ / r**h* t**o**h m*","DAY6(데이식스)");	addQuestion(q412);
        Question q413 = new Question("awkkyBH2zEo", "라리사","lalisa","LALISA - LISA",2021, "ㄹㄹㅅ / l**i**","LISA");	addQuestion(q413);
        Question q414 = new Question("v7bnOxV4jAc", "라일락","lilac","라일락 (LILAC) - IU(아이유)",2021, "ㄹㅇㄹ / l**a*","IU(아이유)");	addQuestion(q414);
        Question q415 = new Question("WpuatuzSDK4", "락윗유","rockwithyou","Rock with you - SEVENTEEN (세븐틴)",2021, "ㄹ ㅇ ㅇ / r**k w**h y**","SEVENTEEN (세븐틴)");	addQuestion(q415);
        Question q416 = new Question("yCvSR4lSqTg", "레디투러브","readytolove","Ready to love - SEVENTEEN (세븐틴)",2021, "ㄹㄷ ㅌ ㄹㅂ / r**d* t* l**e","SEVENTEEN (세븐틴)");	addQuestion(q416);
        Question q417 = new Question("MjCZfZfucEc", "로꼬","loco","LOCO - ITZY(있지)",2021, "ㄹㄲ / l**o","ITZY(있지)");	addQuestion(q417);
        Question q418 = new Question("FCsLikmxhV0", "로사리오","rosario","Rosario - EPIK HIGH (에픽하이)",2021, "ㄹㅅㄹㅇ / r**a**o","EPIK HIGH (에픽하이)");	addQuestion(q418);
        Question q419 = new Question("_btxV8tJM7w", "로즈","rose","Rose - D.O. (디오)",2021, "ㄹㅈ / r**e","D.O. (디오)");	addQuestion(q419);
        Question q420 = new Question("G_SmwQ06TQE", "링링","ringring","Ring Ring - 로켓펀치(Rocket Punch)",2021, "ㄹㄹ / r**g r**g","로켓펀치(Rocket Punch)");	addQuestion(q420);
        Question q421 = new Question("_ysomCGaZLw", "마피아인더모닝","mafiainthemorning","마.피.아. In the morning - ITZY(있지)",2021, "ㅁㅍㅇ ㅇ ㄷ ㅁㄴ / m**i* i* t** m**n**g","ITZY(있지)");	addQuestion(q421);
        Question q422 = new Question("PkKnp4SdE-w", "맛","hotsauce","맛 (Hot Sauce) - NCT DREAM (엔시티 드림)",2021, "ㅁ / h** s**c*","NCT DREAM (엔시티 드림)");	addQuestion(q422);
        Question q423 = new Question("8M3WUaeIbOk", "밤비","bambi","Bambi - BAEKHYUN (백현)",2021, "ㅂㅂ / b**b*","BAEKHYUN (백현)");	addQuestion(q423);
        Question q424 = new Question("WMweEpGlu_U", "버터","butter","Butter - BTS",2021, "ㅂㅌ / b**t**","BTS (방탄소년단)");	addQuestion(q424);
        Question q425 = new Question("OYWWnd9ACMI", "베드러브","badlove","BAD LOVE - KEY (키)",2021, "ㅂㄷ ㄹㅂ / b** l**e","KEY (키)");	addQuestion(q425);
        Question q426 = new Question("5Kdl9uOmj34", "불어온다","nottheend","불어온다 (NOT THE END) - Highlight(하이라이트)",2021, "ㅂㅇㅇㄷ / n** t** e**","Highlight(하이라이트)");	addQuestion(q426);
        Question q427 = new Question("fOdML_XhHvQ", "비가오는날엔","onrainyday","비가 오는 날엔 (2021)(On Rainy Day) - 헤이즈 (Heize)",2021, "ㅂㄱ ㅇㄴ ㄴㅇ / o* r**n* d**","헤이즈 (Heize)");	addQuestion(q427);
        Question q428 = new Question("sCmcSBsTxQc", "비와당신","rainandyou","비와 당신 (Rain and You) - 이무진 (Lee Mujin)",2021, "ㅂㅇ ㄷㅅ / r**n a** y**","이무진 (Lee Mujin)");	addQuestion(q428);
        Question q429 = new Question("PEKkdIT8JPM", "비커즈","because","BEcause - Dreamcatcher(드림캐쳐)",2021, "ㅂㅋㅈ / b**a**e","Dreamcatcher(드림캐쳐)");	addQuestion(q429);
        Question q430 = new Question("WPdWvnAAurg", "새비지","savage","Savage - aespa (에스파)",2021, "ㅅㅂㅈ / s**a**","aespa (에스파)");	addQuestion(q430);
        Question q431 = new Question("Xmxcnf2v_gs", "색안경","stereotype","색안경 (STEREOTYPE) - STAYC(스테이씨)",2021, "ㅅㅇㄱ / s**r**t**e","STAYC(스테이씨)");	addQuestion(q431);
        Question q432 = new Question("0-q1KafFCLU", "셀러브리티","celebrity","Celebrity - IU(아이유)",2021, "ㅅㄹㅂㄹㅌ / c**e**i**","IU(아이유)");	addQuestion(q432);
        Question q433 = new Question("EaswWiwMVs8", "소리꾼","thunderous","소리꾼 (Thunderous) - Stray Kids(스트레이 키즈)",2021, "ㅅㄹㄲ / t**n**r**s","Stray Kids(스트레이 키즈)");	addQuestion(q433);
        Question q434 = new Question("XMs2CIiqRDI", "스릴라이드","thrillride","THRILL RIDE - THE BOYZ(더보이즈)",2021, "ㅅㄹ ㄹㅇㄷ / t**i**r((e","THE BOYZ(더보이즈)");	addQuestion(q434);
        Question q435 = new Question("sqgxcCjD04s", "스트로베리문","strawberrymoon","strawberry moon - 아이유",2021, "ㅅㅌㄹㅂㄹ ㅁ / s**a**e**y m**n","IU(아이유)");	addQuestion(q435);
        Question q436 = new Question("1oYWnbTSang", "스티커","sticker","Sticker - NCT 127 (엔시티 127)",2021, "ㅅㅌㅋ / s**c**r","NCT 127 (엔시티 127)");	addQuestion(q436);
        Question q437 = new Question("QMwJtMJLXE0", "스파이시","spicy","SPICY - CL",2021, "ㅅㅍㅇㅅ / s**c*","CL");	addQuestion(q437);
        Question q438 = new Question("SK6Sm2Ki9tI", "신호등","trafficlight","Traffic light(신호등) - Lee Mujin(이무진)",2021, "ㅅㅎㄷ / t**f**c l**h*","Lee Mujin(이무진)");	addQuestion(q438);
        Question q439 = new Question("BUjI4XPPfh0", "썸머테이스트","summertaste","Summer Taste - RAIN,MONSTA X,BraveGirls,ATEEZ(비,몬스타엑스,브레이브걸스,에이티즈)",2021, "ㅆㅁ ㅌㅇㅅㅌ / s**m** t**t*","RAIN,MONSTA X,BraveGirls,ATEEZ(비,몬스타엑스,브레이브걸스,에이티즈)");	addQuestion(q439);
        Question q440 = new Question("_yXEnhyOTQo", "아임낫쿨","imnotcool","I'm Not Cool - 현아 (HyunA)",2021, "ㅇㅇ ㄴ ㅋ / i* n** c**l","현아 (HyunA)");	addQuestion(q440);
        Question q441 = new Question("PSYRbJjIT6U", "아틀란티스","atlantis","Atlantis - SHINee (샤이니)",2021, "ㅇㅌㄹㅌㅅ / a**a**i*","SHINee (샤이니)");	addQuestion(q441);
        Question q442 = new Question("sfdJRW5NGyA", "안티도트","antidote","Antidote - 강다니엘(KANGDANIEL)",2021, "ㅇㅌㄷㅌ / a**i**t*","강다니엘(KANGDANIEL)");	addQuestion(q442);
        Question q443 = new Question("XA2YEHn-A8Q", "알콜프리","alcoholfree","Alcohol-Free - TWICE(트와이스)",2021, "ㅇㅋㅍㄹ / a**o**l**e*","TWICE(트와이스)");	addQuestion(q443);
        Question q444 = new Question("32kd8oFHoAw", "애프터미드나잇","aftermidnight","After Midnight - ASTRO(아스트로)",2021, "ㅇㅍㅌ ㅁㄷㄴㅇ / a**e* m**n**h*","ASTRO(아스트로)");	addQuestion(q444);
        Question q445 = new Question("FjbCwQ7hliw", "언내추럴","unnatural","UNNATURAL - WJSN(우주소녀)",2021, "ㅇㄴㅊㄹ / u**a**r**","WJSN(우주소녀)");	addQuestion(q445);
        Question q446 = new Question("NsY-9MCOIAQ", "에이셉","asap","ASAP - STAYC(스테이씨)",2021, "ㅇㅇㅅ / a**p","STAYC(스테이씨)");	addQuestion(q446);
        Question q447 = new Question("ck538udT0b8", "여전히아름다운지","isitstillbeautiful","여전히 아름다운지 (Is It Still Beautiful) - SEVENTEEN (세븐틴)",2021, "ㅇㅈㅎ ㅇㄹㄷㅇㅈ / i* i* s**l* b**u**f**","SEVENTEEN (세븐틴)");	addQuestion(q447);
        Question q448 = new Question("CKZvWhCqx1s", "온더그라운드","ontheground","On The Ground - ROSÉ",2021, "ㅇ ㄷ ㄱㄹㅇㄷ / o* t** g**u**","ROSÉ");	addQuestion(q448);
        Question q449 = new Question("srWfDwiRVgQ", "와이돈위","whydontwe","WHY DON’T WE - RAIN (비)",2021, "ㅇㅇ ㄷ ㅇ / w** d**t w*","RAIN (비)");	addQuestion(q449);
        Question q450 = new Question("DslHQto2V7I", "왜왜왜","whywhywhy","왜왜왜 (Why Why Why) - iKON",2021, "ㅇㅇㅇ / w** w** w**","iKON");	addQuestion(q450);
        Question q451 = new Question("ShFKF2YN7H0", "워터컬러","watercolor","water color - 휘인 (Whee In)",2021, "ㅇㅌ ㅋㄹ / w**e* c**o*","휘인 (Whee In)");	addQuestion(q451);
        Question q452 = new Question("RmuL-BPFi2Q", "위켄드","weekend","Weekend - TAEYEON (태연)",2021, "ㅇㅋㄷ / w**k**d","TAEYEON (태연)");	addQuestion(q452);
        Question q453 = new Question("AIyarEsQvAg", "인사이드아웃","insideout","INSIDE OUT - NU'EST (뉴이스트)",2021, "ㅇㅅㅇㄷ ㅇㅇ / i**i** o**","NU'EST (뉴이스트)");	addQuestion(q453);
        Question q454 = new Question("e70PkoJhQYM", "치맛바람","chimatbaram","치맛바람 (Chi Mat Ba Ram) - 브레이브걸스(Brave Girls)",2021, "ㅊㅁㅂㄹ / c** m** b* r**","브레이브걸스(Brave Girls)");	addQuestion(q454);
        Question q455 = new Question("86BST8NIpNM", "코인","coin","Coin - IU(아이유)",2021, "ㅋㅇ / c**n","IU(아이유)");	addQuestion(q455);
        Question q456 = new Question("1JHOl9CSmXk", "콜드블러디드","coldblooded","Cold Blooded - Jessi (제시)",2021, "ㅋㄷ ㅂㄹㄷㄷ / c**d b**o**d","Jessi (제시)");	addQuestion(q456);
        Question q457 = new Question("c9RzZpV460k", "퀸덤","queendom","Queendom - Red Velvet (레드벨벳)",2021, "ㅋㄷ / q**e**o*","Red Velvet (레드벨벳)");	addQuestion(q457);
        Question q458 = new Question("zLrWIgkvoB0", "파라노이아","paranoia","PARANOIA - 강다니엘(KANGDANIEL)",2021, "ㅍㄹㄴㅇㅇ / p**a**i*","강다니엘(KANGDANIEL)");	addQuestion(q458);
        Question q459 = new Question("CuklIb9d3fI", "퍼미션투댄스","permissiontodance","Permission to Dance - BTS (방탄소년단)",2021, "ㅍㅁㅅ ㅌ ㄷㅅ / p**m**s**n t* d**c*","BTS (방탄소년단)");	addQuestion(q459);
        Question q460 = new Question("Z3RA7bi5FUM", "퍼스트","first","FIRST - EVERGLOW (에버글로우)",2021, "ㅍㅅㅌ / f**s*","EVERGLOW (에버글로우)");	addQuestion(q460);
        Question q461 = new Question("7uxu4Z2HAnA", "페이버릿","favorite","Favorite (Vampire) - NCT 127 (엔시티 127)",2021, "ㅍㅇㅂㄹ / f**o**t*","NCT 127 (엔시티 127)");	addQuestion(q461);
        Question q462 = new Question("xBsHAyB73Rk", "페이스아이디","faceid","Face ID - EPIK HIGH (에픽하이)",2021, "ㅍㅇㅅ ㅇㅇㄷ / f**e i*","EPIK HIGH (에픽하이)");	addQuestion(q462);
        Question q463 = new Question("BtJMOVKjhUo", "하우스파티","houseparty","House Party - SUPER JUNIOR (슈퍼주니어)",2021, "ㅎㅇㅅ ㅍㅌ / h**s* p**t*","SUPER JUNIOR (슈퍼주니어)");	addQuestion(q463);
        Question q464 = new Question("-6aVQrzja9U", "해야해","makeit","해야 해 (Make it) - 2PM",2021, "ㅎㅇ ㅎ / m**e i*","2PM");	addQuestion(q464);
        Question q465 = new Question("AJPLgrfBiBo", "헤픈우연","happen","헤픈 우연 (HAPPEN) - 헤이즈 (Heize)",2021, "ㅎㅍ ㅇㅇ / h**p**","헤이즈 (Heize)");	addQuestion(q465);
        Question q466 = new Question("QPUjV7epJqE", "헬로우퓨쳐","hellofuture","Hello Future - NCT DREAM (엔시티 드림)",2021, "ㅎㄹㅇ ㅍㅊ / h**l* f**u**","NCT DREAM (엔시티 드림)");	addQuestion(q466);
        Question q467 = new Question("DZEOt4pQXXk", "홀리데이파티","holidayparty","Holiday Party - Weeekly(위클리)",2021, "ㅎㄹㄷㅇ ㅍㅌ / h**i**y p**t*","Weeekly(위클리)");	addQuestion(q467);
        Question q468 = new Question("z3szNvgQxHo", "화","hwaa","화(火花)(HWAA) - (여자)아이들((G)I-DLE)",2021, "ㅎ / h**a","(여자)아이들((G)I-DLE)");	addQuestion(q468);
        Question q469 = new Question("UZeWM-hfZBQ", "별의조각","stardust","별의 조각 (Stardust) - YOUNHA(윤하)",2021, "ㅂㅇㅈㄱ / s**r**s*","YOUNHA(윤하)");	addQuestion(q469);
        Question q470 = new Question("qfVuRQX0ydQ", "애프터스쿨","afterschool","After School - Weeekly (위클리)",2021, "ㅇㅍㅌ ㅅㅋ / a**e* s**o**","Weeekly (위클리)");	addQuestion(q470);
        Question q471 = new Question("vPwaXytZcgI", "사이언티스트","scientist","SCIENTIST - TWICE (트와이스)",2021, "ㅅㅇㅇㅌㅅㅌ / s**e**i**","TWICE(트와이스)");	addQuestion(q471);
        Question q472 = new Question("sQTGAggO0Gw", "매버릭","maverick","MAVERICK - THE BOYZ (더보이즈)",2021, "ㅁㅂㄹ / m**e**c*","THE BOYZ(더보이즈)");	addQuestion(q472);
        Question q473 = new Question("qRdTyoZd3rg", "러시아워","rushhour","Rush Hour - MONSTA X (몬스타엑스)",2021, "ㄹㅅ ㅇㅇ / r**h h**r","MONSTA X (몬스타엑스)");	addQuestion(q473);
        Question q474 = new Question("1HesnepeVkU", "트라우마","trauma","Trauma - SF9",2021, "ㅌㄹㅇㅁ / t**u**","SF9");	addQuestion(q474);
        Question q475 = new Question("57n4dZAPxNY", "크리스마스이블","christmasevel","Christmas EveL - Stray Kids (스트레이 키즈)",2021, "ㅋㄹㅅㅁㅅ ㅇㅂ / c**i**m** e**l","Stray Kids (스트레이 키즈)");	addQuestion(q475);
        Question q476 = new Question("H8kqPkEXP_E", "엑소엑소","xoxo","XOXO - JEON SOMI (전소미)",2021, "ㅇㅅㅇㅅ / x**o","JEON SOMI (전소미)");	addQuestion(q476);
        Question q477 = new Question("mLCsbacHxA8", "카운팅스타","countingstars","Counting Stars (feat. Beenzino) - BE'O (비오)",2021, "ㅋㅇㅌ ㅅㅌ / c**n**n* s**s","BE'O (비오)");	addQuestion(q477);
        Question q478 = new Question("VAEEblk-qDU", "탕","tang","탕!♡ (TANG!♡) - MINO",2021, "ㅌ / t**g","MINO");	addQuestion(q478);
        Question q479 = new Question("be5yMhqtdyQ", "피티티","ptt","PTT (Paint The Town) - LOONA (이달의 소녀)",2021, "ㅍㅌㅌ / p**","LOONA (이달의 소녀)");	addQuestion(q479);
        Question q480 = new Question("--zku6TB5NY", "라스트댄스","lastdance","LAST DANCE - BIGBANG",2016, "ㄹㅅㅌ ㄷㅅ / l**t d**c*","BIGBANG");	addQuestion(q480);
        Question q481 = new Question("-e7CIlMRayY", "사랑했나봐","loved","사랑했나봐 (Loved) - Highlight(하이라이트)",2018, "ㅅㄹㅎㄴㅂ / l**e*","Highlight(하이라이트)");	addQuestion(q481);
        Question q482 = new Question("-OfOkiVFmhM", "원트","want","WANT - TAEMIN (태민)",2019, "ㅇㅌ / w**t","TAEMIN (태민)");	addQuestion(q482);
        Question q483 = new Question("-Ih5UArd4zk", "라이크워터","likewater","Like Water - WENDY (웬디)",2021, "ㄹㅇㅋ ㅇㅌ / l**e w**e*","WENDY (웬디)");	addQuestion(q483);
        Question q484 = new Question("7tkbzxa8MFQ", "일레븐","eleven","ELEVEN - IVE (아이브)",2021, "ㅇㄹㅂ / e**v**","IVE (아이브)");	addQuestion(q484);

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
