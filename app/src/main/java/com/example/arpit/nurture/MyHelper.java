package com.example.arpit.nurture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyHelper extends SQLiteOpenHelper {

    public static final  int VERSION = 1;
    public static final String DATABASE_NAME = "DB";
    public static final String TABLE_USER = "User";
    public static final String TABLE_ADMIN = "Admin";
    public static final String TABLE_COUPON = "Coupon" ;
    public static final String TABLE_REQUEST = "Request";
    public static final String TABLE_IMAGES = "UserImages";
    public static final String NAME = "name";
    public static final String PASS = "password";
    public static final String EMAIL = "email";
    public static final String MOBNO = "mobno";
    public static final String CREDITS = "credits";
    public static final String DP = "dp";
    public static final String IMAGES = "images";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String CREDITS_REQUIRED = "credits_required";
    public static final String CODE = "code";
    public static final String COUPON_IMAGE_ID = "image_id";
    public static final String POSITION ="position";
    public static final String VERIFICATION_STATUS = "status";


    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USER + " (" + NAME + " varchar(20), " + PASS + " varchar(16), " + EMAIL + " varchar(30), " + MOBNO + " varachar(10), " +  CREDITS + " integer, " + DP + " blob);");
        db.execSQL("CREATE TABLE " + TABLE_IMAGES + " (" + EMAIL + " varchar(30), " + IMAGES + " blob, " + POSITION + " integer, " + VERIFICATION_STATUS + " varchar(10) default 'false');");
        db.execSQL("CREATE TABLE " + TABLE_ADMIN + " (" + NAME + " varchar(20), " + PASS + " varchar(16), " + EMAIL + " varchar(30), " + MOBNO + " varachar(10));");
        db.execSQL("CREATE TABLE " + TABLE_COUPON + " (" + TYPE + " varchar(20), " + DESCRIPTION + " varchar(100), " + AMOUNT + " integer, " + CREDITS_REQUIRED + " integer, " + CODE + " varchar(30), " + COUPON_IMAGE_ID + " integr);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> get_Email() throws SQLiteException{
        ArrayList<String> temp = new ArrayList<>();
        SQLiteDatabase dbs = getReadableDatabase();
        String sa[]={EMAIL};
        Cursor c = dbs.query("UserImages",sa,null,null,null,null,null);
        do {
            temp.add(c.getString(c.getColumnIndex("data")));
        }while(c.moveToNext());
        dbs.close();
        return temp;
    }

    public void addUser(User u) throws  SQLiteException{

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        try {
            byte[] dp = DbBitmapUtility.getBytes(u.getProfilePicture());
            cv.put(DP, dp);
        }catch (NullPointerException e)
        {
            Log.i("Exception", e.toString());
            cv.put(DP, (Byte)null);
        }

        cv.put(NAME, u.getName());
        cv.put(PASS, u.getPass());
        cv.put(EMAIL, u.getEmail());
        cv.put(MOBNO, u.getMobNo());
        cv.put(CREDITS, u.getCredits());
        database.insert(TABLE_USER, null, cv);

        database.close();
    }

    public void addAdmin(Admin a) throws  SQLiteException{

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, a.getName());
        cv.put(PASS, a.getPass());
        cv.put(EMAIL, a.getEmail());
        cv.put(MOBNO, a.getMobNo());
        database.insert(TABLE_ADMIN, null, cv);

        database.close();
    }

    public void addCoupon(Coupon c) throws  SQLiteException{

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TYPE, c.getType());
        cv.put(DESCRIPTION, c.getDescription());
        cv.put(AMOUNT, c.getAmount());
        cv.put(CREDITS_REQUIRED, c.getCreditsRequired());
        cv.put(CODE, c.getCode());
        cv.put(COUPON_IMAGE_ID, c.getImageID());
        database.insert(TABLE_COUPON, null, cv);

        database.close();
    }

    //By Default the VERIFICATION_STATUS is false.
    // To set VERIFICATION_STATUS to true call the updateImageStatus(Integer index) method.
    public void addImages(String email, Bitmap image, Integer position){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        try{
            byte[] img = DbBitmapUtility.getBytes(image);
            cv.put(IMAGES, img);
            Log.e("Img_update", "Image uploaded sucessfully");
        }catch(Exception e){
            Log.e("Image_exception", e.toString());
        }

        cv.put(EMAIL, email);
        cv.put(POSITION, position);
        database.insert(TABLE_IMAGES, null, cv);

        database.close();
    }

    public ArrayList<Bitmap> readImages(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String col[] = {EMAIL, IMAGES, VERIFICATION_STATUS};
        String selec_arg[] = {email, "false"};
        ArrayList<Bitmap> temp;

        Cursor c = db.query(TABLE_IMAGES, col, EMAIL + " = ?" + " AND " + VERIFICATION_STATUS + " = ?", selec_arg, null, null, null);

        try{
            temp = new ArrayList<>();
            while(c.moveToNext()){
                temp.add(DbBitmapUtility.getImage(c.getBlob(1)));
            }
            return temp;
        }catch (Exception e){
            Log.e("Image_read_exception", e.toString());
        }

        db.close();

        return null;
    }

    public User readUser(String email_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String col[] = {NAME, PASS, EMAIL, MOBNO, CREDITS, DP};
        String selec_arg[] = {email_id};

        try {
            Cursor c = db.query(TABLE_USER, col, EMAIL + " = ?", selec_arg, null, null, null);
            c.moveToNext();

            User temp = new User();
            try {
                Bitmap img = DbBitmapUtility.getImage(c.getBlob(5));
                temp.setProfilePicture(img);
            } catch (NullPointerException e) {
                Log.i("Exception", e.toString());
                temp.setProfilePicture(null);
            }

            temp.setName(c.getString(0));
            temp.setPass(c.getString(1));
            temp.setEmail(c.getString(2));
            temp.setMobNo(c.getString(3));
            temp.setCredits(c.getInt(4));

            db.close();

            return temp;
        }catch (Exception e){
            return null;
        }
    }

    public Admin readAdmin(String email_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String col[] = {NAME, PASS, EMAIL, MOBNO};
        String selec_arg[] = {email_id};
        Cursor c = db.query(TABLE_USER, col, EMAIL + " = ?" , selec_arg, null, null, null);
        c.moveToLast();

        Admin temp = new Admin();

        temp.setName(c.getString(0));
        temp.setPass(c.getString(1));
        temp.setEmail(c.getString(2));
        temp.setMobNo(c.getString(3));

        db.close();

        return  temp;
    }

    public Coupon readCoupon(String code){
        SQLiteDatabase db = this.getReadableDatabase();
        String col[] = {TYPE, DESCRIPTION, AMOUNT, CREDITS_REQUIRED, CODE, COUPON_IMAGE_ID};
        String selec_arg[] = {code};
        Cursor c = db.query(TABLE_USER, col, CODE + " = ?" , selec_arg, null, null, null);
        c.moveToLast();

        Coupon temp = new Coupon();

        temp.setType(c.getString(0));
        temp.setDescription(c.getString(1));
        temp.setAmount(c.getInt(2));
        temp.setCreditsRequired(c.getInt(3));
        temp.setCode(c.getString(4));
        temp.setImageID(c.getInt(5));

        db.close();

        return  temp;
    }

    public void updateUser(User user, String oldEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        String selec_arg[] = {oldEmail};

        ContentValues cv = new ContentValues();
        byte[] dp;

        try{

            try{
                dp = DbBitmapUtility.getBytes(user.getProfilePicture());
            }catch (Exception e)
            {
                dp = null;
            }

            cv.put(NAME, user.getName());
            cv.put(EMAIL, user.getEmail());
            cv.put(PASS, user.getPass());
            cv.put(MOBNO, user.getMobNo());
            cv.put(DP, dp);

            db.update(TABLE_USER, cv, EMAIL + " = ?", selec_arg);
            db.close();
        }catch (Exception e)
        {
            Log.e("MyException", e.toString());
        }

        db.close();
    }

    public void updateImageStatus(Integer index){
        SQLiteDatabase db = this.getWritableDatabase();
        String selec_arg[] = {index.toString()};
        ContentValues cv = new ContentValues();
        cv.put(VERIFICATION_STATUS, "true");

        db.update(TABLE_IMAGES, cv, POSITION + " = ?", selec_arg);
        db.close();
    }

    public void removeImage(Integer index){
        SQLiteDatabase db = this.getWritableDatabase();
        String selec_arg[] = {index.toString()};

        db.delete(TABLE_IMAGES, POSITION + " = ?", selec_arg);
        Log.e("Image_delete", "deleton sucessful");

        db.close();
    }

}
