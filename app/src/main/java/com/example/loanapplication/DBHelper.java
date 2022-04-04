package com.example.loanapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.sql.Date;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    public static final String Databasename="Loandatabases.db";

    public static final String TABLE_USER = "UserDetail";
    public static final String USER_COL_1 = "firstName";
    public static final String USER_COL_2 = "lastName";
    public static final String USER_COL_3 = "mobileno";
    public static final String USER_COL_4 = "password";
    public static final String USER_COL_5="email";

    public static final String TABLE_LOANAPP = "LoanApplication";
    public static final String LOANAPPCOL_1 = "loanappID";
    public static final String LOANAPPCOL_2 = "loantype";
    public static final String LOANAPPCOL_3 = "email";
    public static final String LOANAPPCOL_4 = "priniciple";
    public static final String LOANAPPCOL_5= "tenure";
    public static final String LOANAPPCOL_6= "rate";
    public static final String LOANAPPCOL_7= "totalinterest";
    public static final String LOANAPPCOL_8= "totalpayment";
    public static final String LOANAPPCOL_9= "emi";
    public static final String LOANAPPCOL_10= "status";

    public static final String TABLE_KYC_AadherCard = "KYCAadherCard";
    public static final String KYCAadherCardCOL_1 = "aadherCard";
    public static final String KYCAadherCardCOL_2 = "IsaadherCard";
    public static final String KYCAadherCardCOL_3 = "email";

    public static final String TABLE_KYC_PanCard = "KYCPanCard";
    public static final String KYCPanCardCOL_1 = "panCard";
    public static final String KYCPanCardCOL_2 = "IspanCard";
    public static final String KYCPanCardCOL_3 = "email";

    public static final String TABLE_KYC_Bank = "KYCBank";
    public static final String KYCBankCOL_1 = "bank";
    public static final String KYCBankCOL_2 = "Isbank";
    public static final String KYCBankCOL_3 = "email";

    public static final String TABLE_KYC_Selfie = "KYCSelfie";
    public static final String KYCSelfieCOL_1 = "selfie";
    public static final String KYCSelfieCOL_2 = "Isselfie";
    public static final String KYCSelfieCOL_3 = "email";

    public static final String TABLE_Esign = "Esign";
    public static final String EsignCOL_1 = "Isesign";
    public static final String EsignCOL_2 = "email";


    public static final String TABLE_USERPersonal = "PersonalInformation";
    public static final String USERPersonal_COL_1 = "firstName ";
    public static final String USERPersonal_COL_2 = "lastName";
    public static final String USERPersonal_COL_3 = "dateofBirth ";
    public static final String USERPersonal_COL_4 = "gender";
    public static final String USERPersonal_COL_5="nationality";
    public static final String USERPersonal_COL_6="mstatus";
    public static final String USERPersonal_COL_7="isCompleted";
    public static final String USERPersonal_COL_8 = "email";

    public static final String TABLE_USERContact = "ContactInformation";
    public static final String USERContact_COL_1 = "address";
    public static final String USERContact_COL_2 = "city";
    public static final String USERContact_COL_3 = "state";
    public static final String USERContact_COL_4 = "postalCode";
    public static final String USERContact_COL_5="isCompleted";
    public static final String USERContact_COL_6 = "email";

    public static final String TABLE_USERAdd = "AddressInformation";
    public static final String USERAdd_COL_1 = "apartmentdetails";
    public static final String USERAdd_COL_2 = "durationofstay";
    public static final String USERAdd_COL_3 = "isCompleted";
    public static final String USERAdd_COL_4 = "email";

    public static final String TABLE_USERBank= "BankDetails";
    public static final String USERBank_COL_1 = "bankname";
    public static final String USERBank_COL_2 = "accountno";
    public static final String USERBank_COL_3 = "ifsccode";
    public static final String USERBank_COL_4 = "isCompleted";
    public static final String USERBank_COL_5 = "email";


    public static final String TABLE_USERIncome = "IncomeDetails";
    public static final String USERIncome_COL_1 = "occupations";
    public static final String USERIncome_COL_2 = "frequencyofincome";
    public static final String USERIncome_COL_3 = "paymentmethod";
    public static final String USERIncome_COL_4 = "employertype";
    public static final String USERIncome_COL_5 = "jobtype";
    public static final String USERIncome_COL_6 = "employerdetails";
    public static final String USERIncome_COL_7 = "yearexp";
    public static final String USERIncome_COL_8 = "isCompleted";
    public static final String USERIncome_COL_9 = "email";

    public static final String TABLE_LoanType = "LoanTypes";
    public static final String LoanTypeCOL_1 = "loantypename";
    public static final String LoanTypeCOL_2 = "maxamount";
    public static final String LoanTypeCOL_3 = "loantenure";
    public static final String LoanTypeCOL_4 = "loanrate";



  public DBHelper(Context context) {
        super(context,Databasename, null   , 2);
        context=this.context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDb) {

        sqlDb.execSQL("create table " + TABLE_KYC_AadherCard + "(aadherCard  blob not null, " +
                "IsaadherCard  boolean not null," +
                "email varchar(30) primary key);");

        sqlDb.execSQL("create table " + TABLE_KYC_PanCard + "(panCard  blob not null, " +
                "IspanCard  boolean not null," +
                "email varchar(30) primary key);");

        sqlDb.execSQL("create table " + TABLE_KYC_Bank + "(bank  blob not null, " +
                "Isbank  boolean not null," +
                "email varchar(30) primary key);");

        sqlDb.execSQL("create table " + TABLE_KYC_Selfie + "(selfie blob not null, " +
                "IsSelfie  boolean not null," +
                "email varchar(30) primary key);");

        sqlDb.execSQL("create table " + TABLE_Esign + "(Isesign boolean not null, " +
                "email varchar(30) primary key);");





        sqlDb.execSQL("create table " + TABLE_LoanType + "(loantypename varchar(30) primary key, " +
                "maxamount long not null," +
                "loantenure int(5) not null," +
                "loanrate float not null);");

        sqlDb.execSQL("create table " + TABLE_LOANAPP + "(loanappID int primary key , " +
                "loantype varchar(30) not null," +
                "email varchar(30)," +
                "priniciple double not null," +
                "tenure int(2) not null," +
                "rate float not null," +
                "totalinterest double not null," +
                "totalpayment double not null,"+
                "emi double not null,"+
                "status varchar(10) not null);");


        sqlDb.execSQL("create table " + TABLE_USERPersonal + "(firstName varchar(30) not null, " +
                "lastName varchar(30) not null," +
                "dateofBirth varchar(30)," +
                "gender varchar(40) not null," +
                "nationality varchar(10) not null," +
                "mstatus varchar(50) not null," +
                "isCompleted boolean not null," +
                "email varchar(10) primary key);");

        sqlDb.execSQL("create table " + TABLE_USER + "(firstName varchar(30) not null, " +
                "lastName varchar(30) not null," +
                "mobileno varchar(30)," +
                "password varchar(40) not null," +
                "email varchar(10) primary key);");

        sqlDb.execSQL("create table " + TABLE_USERContact + "(address varchar(50) not null, " +
                "city varchar(30) not null," +
                "state varchar(30)," +
                "postalCode varchar(40) not null," +
                "isCompleted boolean not null," +
                "email varchar(10) primary key);");

        sqlDb.execSQL("create table " + TABLE_USERAdd + "(apartmentdetails varchar(30) not null, " +
                "durationofstay varchar(30) not null," +
                "isCompleted boolean not null," +
                "email varchar(10) primary key);");

        sqlDb.execSQL("create table " + TABLE_USERIncome + "(occupations varchar(30) not null, " +
                "frequencyofincome varchar(30) not null," +
                "paymentmethod varchar(30)," +
                "employertype varchar(40) not null," +
                "jobtype varchar(10) not null," +
                "employerdetails varchar(50) not null," +
                "yearexp int(2) not null," +
                "isCompleted boolean not null," +
                "email varchar(10) primary key);");

        sqlDb.execSQL("create table " + TABLE_USERBank + "(bankname varchar(50) not null, " +
                "accountno int(10) not null," +
                "ifsccode varchar(30) not null," +
                "isCompleted boolean not null," +
                "email varchar(10) primary key);");

    }

    @Override


    public void onUpgrade(SQLiteDatabase sqlDb, int i, int i1) {
        sqlDb.execSQL("drop Table if exists "+TABLE_USER);
        sqlDb.execSQL("drop Table if exists "+TABLE_USERPersonal);
        sqlDb.execSQL("drop Table if exists "+TABLE_USERContact);
        sqlDb.execSQL("drop Table if exists "+TABLE_USERAdd);
        sqlDb.execSQL("drop Table if exists "+TABLE_USERIncome);
        sqlDb.execSQL("drop Table if exists "+TABLE_USERBank);
        sqlDb.execSQL("drop Table if exists "+TABLE_LoanType);
        sqlDb.execSQL("drop Table if exists "+TABLE_KYC_AadherCard);
        sqlDb.execSQL("drop Table if exists "+TABLE_KYC_PanCard);
        sqlDb.execSQL("drop Table if exists "+TABLE_KYC_Selfie);
        sqlDb.execSQL("drop Table if exists "+TABLE_KYC_Bank);
        sqlDb.execSQL("drop Table if exists "+TABLE_Esign);
        sqlDb.execSQL("drop Table if exists "+TABLE_LOANAPP);


        onCreate(sqlDb);

    }


    public boolean insertLoanApp(int loanAppId,String loanType, String email,double priniciple,int tenure,float rate,double totalInterest,double totalPayment,double emi,String status) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(LOANAPPCOL_1, loanAppId);
        con.put(LOANAPPCOL_2, loanType);
        con.put(LOANAPPCOL_3, email);
        con.put(LOANAPPCOL_4, priniciple);
        con.put(LOANAPPCOL_5, tenure);
        con.put(LOANAPPCOL_6, rate);
        con.put(LOANAPPCOL_7, totalInterest);
        con.put(LOANAPPCOL_8, totalPayment);
        con.put(LOANAPPCOL_9, emi);
        con.put(LOANAPPCOL_10, status);
        long result = sqlDb.insert(TABLE_LOANAPP, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertEsign(boolean IsEsign,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(EsignCOL_1, IsEsign);
        con.put(EsignCOL_2, email);
        long result = sqlDb.insert(TABLE_Esign, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean insertKYCAadherCard(byte[] aadherCard,boolean IsaadherCard,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(KYCAadherCardCOL_1, aadherCard);
        con.put(KYCAadherCardCOL_2, IsaadherCard);
        con.put(KYCAadherCardCOL_3, email);
        long result = sqlDb.insert(TABLE_KYC_AadherCard, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertKYCPanCard(byte[] panCard,boolean IspanCard,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(KYCPanCardCOL_1, panCard);
        con.put(KYCPanCardCOL_2, IspanCard);
        con.put(KYCPanCardCOL_3, email);
        long result = sqlDb.insert(TABLE_KYC_PanCard, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertKYCBank(byte[] bank,boolean Isbank,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(KYCBankCOL_1, bank);
        con.put(KYCBankCOL_2, Isbank);
        con.put(KYCBankCOL_3, email);
        long result = sqlDb.insert(TABLE_KYC_Bank, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertKYCSelfie(byte[] selfie,boolean Isselfie,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(KYCSelfieCOL_1, selfie);
        con.put(KYCSelfieCOL_2, Isselfie);
        con.put(KYCSelfieCOL_3, email);
        long result = sqlDb.insert(TABLE_KYC_Selfie, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean insertLoanTypes(String loanTypeNamedb, long maxLoanAmountdb, int loanTenuredb, float loanRatedb) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(LoanTypeCOL_1, loanTypeNamedb);
        con.put(LoanTypeCOL_2, maxLoanAmountdb);
        con.put(LoanTypeCOL_3, loanTenuredb);
        con.put(LoanTypeCOL_4, loanRatedb);
        long result = sqlDb.insert(TABLE_LoanType, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertAddDetails(String Apartmentdetails, String Durationofstay,Boolean Iscompleted, String Email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(USERAdd_COL_1, Apartmentdetails);
        con.put(USERAdd_COL_2, Durationofstay);
        con.put(USERAdd_COL_3, Iscompleted);
        con.put(USERAdd_COL_4, Email);
        long result = sqlDb.insert(TABLE_USERAdd, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertBankDetails(String Bankname, long Accountno, String Ifsccode,Boolean Iscompleted, String Email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(USERBank_COL_1, Bankname);
        con.put(USERBank_COL_2, Accountno);
        con.put(USERBank_COL_3, Ifsccode);
        con.put(USERBank_COL_4, Iscompleted);
        con.put(USERBank_COL_5, Email);
        long result = sqlDb.insert(TABLE_USERBank, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertPersonalInformation(String FirstName, String LastName, String DateofBirth,String Gender,String Nationality,String Mstatus,Boolean Iscompleted, String Email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(USERPersonal_COL_1, FirstName);
        con.put(USERPersonal_COL_2, LastName);
        con.put(USERPersonal_COL_3, DateofBirth);
        con.put(USERPersonal_COL_4,  Gender);
        con.put(USERPersonal_COL_5, Nationality);
        con.put(USERPersonal_COL_6, Mstatus);
        con.put(USERPersonal_COL_7, Iscompleted);
        con.put(USERPersonal_COL_8, Email);
        long result = sqlDb.insert(TABLE_USERPersonal, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertIncomeDetails(String Occupations, String Frequencyofincome, String Paymentmethod,String Employertype,String Jobtype,String Employerdetails, int Yearexp,Boolean Iscompleted, String Email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(USERIncome_COL_1, Occupations);
        con.put(USERIncome_COL_2, Frequencyofincome);
        con.put(USERIncome_COL_3, Paymentmethod);
        con.put(USERIncome_COL_4, Employertype);
        con.put(USERIncome_COL_5, Jobtype);
        con.put(USERIncome_COL_6, Employerdetails);
        con.put(USERIncome_COL_7, Yearexp);
        con.put(USERIncome_COL_8, Iscompleted);
        con.put(USERIncome_COL_9, Email);
        long result = sqlDb.insert(TABLE_USERIncome, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertUserDetail(String fName, String lName, String mobile, String password,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(USER_COL_1, fName);
        con.put(USER_COL_2, lName);
        con.put(USER_COL_3, mobile);
        con.put(USER_COL_4,  password);
        con.put(USER_COL_5, email);
        long result = sqlDb.insert(TABLE_USER, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertContactDetail(String address, String city, String state, String postalCode,Boolean Iscompleted,String email) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(USERContact_COL_1, address);//(col name,user data)
        con.put(USERContact_COL_2, city);
        con.put(USERContact_COL_3, state);
        con.put(USERContact_COL_4, postalCode);
        con.put(USERContact_COL_5,Iscompleted);
        con.put(USERContact_COL_6, email);
        long result = sqlDb.insert(TABLE_USERContact, null, con);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean loginUser(String username,String password){
        SQLiteDatabase sqlDb=this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE email = ? AND password = ?",new String[]{username,password});
        if(result.getCount()==1){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getKYCAadherCard(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_KYC_AadherCard+" WHERE email = ?", new String[]{email});
        return result;
    }
    public Cursor getEsign(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_Esign+" WHERE email = ?", new String[]{email});
        return result;
    }
    public Cursor getKYCPanCard(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_KYC_PanCard+" WHERE email = ?", new String[]{email});
        return result;
    }
    public Cursor getKYCBank(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_KYC_Bank+" WHERE email = ?", new String[]{email});
        return result;
    }

    public Cursor getKYCSelfie(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_KYC_Selfie+" WHERE email = ?", new String[]{email});
        return result;
    }

    public Cursor getUserDetailUsername(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result=sqlDb.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE email = ?", new String[]{email});
        return result;
    }

    public Cursor getUserDetails()
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_USER,null,null);
       return result;
    }

    public Cursor getLoanType()
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_LoanType,null,null);
        return result;
    }

    public Cursor getLoanTypeByLoanName(String loanName)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_LoanType+" WHERE loantypename = ?",new String[]{loanName});
        return result;
    }
    public Cursor getPersonalInformation(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_USERPersonal+" WHERE email = ?",new String[]{email});
        return result;
    }
    public Cursor getIncomeDetails(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_USERIncome+" WHERE email = ?",new String[]{email});
        return result;
    }
    public Cursor getBankDetails(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_USERBank+" WHERE email = ?",new String[]{email});
        return result;
    }
    public Cursor getContactInformation(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_USERContact+" WHERE email = ?",new String[]{email});
        return result;
    }
    public Cursor getAddressInformation(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_USERAdd+" WHERE email = ?",new String[]{email});
        return result;
    }
    public Cursor getLoanApplicationData(String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_LOANAPP+" WHERE email = ?",new String[]{email});
        return result;
    }

    public Cursor getLoanApplicationUser()
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT DISTINCT "+LOANAPPCOL_3+" FROM "+TABLE_LOANAPP,null,null);
        return result;
    }

    public Cursor getLoanApplicationDataByLoanTypeName(String loanTypeName,String email)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_LOANAPP+" WHERE email = '"+email+"' and loantype = '"+loanTypeName+"'",null,null);
        return result;
    }
    public Cursor getpendingLoanApplicationData(String email ,String status){
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor result= sqlDb.rawQuery("SELECT * FROM "+TABLE_LOANAPP+" WHERE email = '"+email+"' and status = '"+status+"'",null,null);
        return result;
    }

    public boolean updateLoanApplicationStatus(String loanTypeName,String email,String status){
        SQLiteDatabase sqlDb= this.getWritableDatabase();
        sqlDb.execSQL(" Update "+TABLE_LOANAPP+" Set "+LOANAPPCOL_10+"="+"'"+status+"'"+" Where "+LOANAPPCOL_2+"="+"'"+loanTypeName+"'"+" and "+LOANAPPCOL_3+"="+"'"+email+"'");
    return true;
  }


        public boolean updateLoanTypeByRate(String loanType,float loanRate){
            SQLiteDatabase sqlDb= this.getWritableDatabase();
            sqlDb.execSQL(" Update "+TABLE_LoanType+" Set "+LoanTypeCOL_4+"="+loanRate+" Where "+LoanTypeCOL_1+"="+"'"+loanType+"'");
            return true;
            /*ContentValues con=new ContentValues();

            con.put(LoanTypeCOL_4, loanRate);
            Cursor cursor=sqlDb.rawQuery("Select * from "+TABLE_LOANAPP+" where "+LoanTypeCOL_1+"=?",new String[]{loanType});
            if(cursor.getCount()>0) {
                long result = sqlDb.update(TABLE_LOANAPP, con, "email=?", new float[]{loanRate});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }else{
            return false;
        }*/
    }
}
