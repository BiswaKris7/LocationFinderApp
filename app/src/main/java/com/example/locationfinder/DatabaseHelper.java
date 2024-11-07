package com.example.locationfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LocationFinder.db";
    private static final int DATABASE_VERSION = 2;  // Update the version to force onUpgrade
    private static final String TABLE_NAME = "location";
    private static final String COL_ID = "id";
    private static final String COL_ADDRESS = "address";
    private static final String COL_LATITUDE = "latitude";
    private static final String COL_LONGITUDE = "longitude";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ADDRESS + " TEXT, " +
                COL_LATITUDE + " REAL, " +
                COL_LONGITUDE + " REAL)";
        db.execSQL(createTable);

        Log.d("DatabaseHelper", "onCreate: Table created, inserting sample locations.");
        insertSampleLocations(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertSampleLocations(SQLiteDatabase db) {

        String[] loc = {
                "Oshawa", "Ajax", "Pickering", "Scarborough", "Downtown Toronto",
                "Mississauga", "Brampton", "Markham", "Richmond Hill", "Vaughan",
                "Etobicoke", "North York", "York", "East York", "Whitby",
                "Brooklin", "Clarington", "Uxbridge", "Whitchurch-Stouffville", "Aurora",
                "Newmarket", "Georgina", "King City", "Caledon", "Bolton",
                "Milton", "Burlington", "Oakville", "Halton Hills", "Acton",
                "Mississauga Valley", "Port Credit", "Malton", "Meadowvale", "Erin Mills",
                "Dixie", "Clarkson", "Cooksville", "Streetsville", "Long Branch",
                "Islington", "Weston", "Downsview", "Willowdale", "Lawrence Park",
                "Forest Hill", "Leaside", "Danforth", "The Beaches", "Leslieville",
                "Roncesvalles", "High Park", "Swansea", "Lakeshore", "Queensway",
                "Kensington", "St. Lawrence", "Cabbagetown", "Regent Park", "Riverdale",
                "Liberty Village", "King West", "Queen West", "Little Italy", "Little Portugal",
                "Dovercourt", "Dufferin Grove", "Parkdale", "Bloor West Village", "Junction",
                "Rexdale", "Thistletown", "Mount Dennis", "Rockcliffe-Smythe", "Keelesdale",
                "Runnymede", "Lambton", "The Annex", "Yorkville", "Rosedale",
                "Moore Park", "Summerhill", "Forest Hill North", "Casa Loma", "Hillcrest",
                "Wychwood", "Humewood", "Cedarvale", "Corso Italia", "Bloordale Village",
                "Wallace Emerson", "Junction Triangle", "Mimico", "New Toronto", "Alderwood",
                "Stonegate", "The Elms", "Kingsview Village", "Richview", "Martingrove Gardens" };

        double[][] coordi = { {43.8978, -78.8590}, {43.8505, -79.0202}, {43.8484, -79.0972}, {43.7368, -79.2050}, {43.6532, -79.3832},
                {43.5890, -79.6441}, {43.7315, -79.7626}, {43.8755, -79.3370}, {43.8828, -79.4403}, {43.8361, -79.4983},
                {43.6205, -79.5132}, {43.7615, -79.4111}, {43.6898, -79.4535}, {43.6897, -79.3356}, {43.8971, -78.9429},
                {43.9796, -78.9497}, {43.9358, -78.6071}, {44.1086, -79.1207}, {43.9634, -79.2471}, {44.0065, -79.4504},
                {44.0567, -79.4706}, {44.2541, -79.4405}, {43.9297, -79.5287}, {43.8621, -79.8789}, {43.8759, -79.7370},
                {43.5183, -79.8774}, {43.3255, -79.7990}, {43.4675, -79.6877}, {43.6326, -79.9504}, {43.6333, -79.8756},
                {43.5834, -79.6069}, {43.5511, -79.5869}, {43.7219, -79.7164}, {43.5987, -79.6464}, {43.5828, -79.7125},
                {43.6571, -79.6341}, {43.5054, -79.6686}, {43.5715, -79.6142}, {43.5934, -79.6627}, {43.5923, -79.5434},
                {43.6884, -79.5158}, {43.7109, -79.5158}, {43.7702, -79.4099}, {43.7415, -79.3933}, {43.7259, -79.4158},
                {43.6784, -79.4126}, {43.7012, -79.3665}, {43.6864, -79.3196}, {43.6727, -79.2956}, {43.6661, -79.3258},
                {43.6487, -79.4506}, {43.6488, -79.4646}, {43.6507, -79.4707}, {43.6368, -79.4707}, {43.6344, -79.5096},
                {43.6558, -79.3775}, {43.6468, -79.3717}, {43.6591, -79.3697}, {43.6599, -79.3568}, {43.6617, -79.3511},
                {43.6428, -79.4182}, {43.6421, -79.4021}, {43.6420, -79.4002}, {43.6484, -79.3953}, {43.6478, -79.4128},
                {43.6574, -79.4444}, {43.6611, -79.4626}, {43.6567, -79.4323}, {43.6673, -79.4623}, {43.6696, -79.4798},
                {43.7251, -79.5498}, {43.6888, -79.4926}, {43.6929, -79.5161}, {43.7049, -79.4895}, {43.7138, -79.5192},
                {43.7007, -79.4855}, {43.6798, -79.4166}, {43.6775, -79.3825}, {43.6725, -79.3736}, {43.6926, -79.4256},
                {43.6922, -79.4124}, {43.6816, -79.3993}, {43.6781, -79.4075}, {43.6781, -79.3858}, {43.6816, -79.3923},
                {43.6911, -79.4104}, {43.6944, -79.4421}, {43.6893, -79.4825}, {43.6834, -79.4867}, {43.6717, -79.4816},
                {43.6733, -79.5007}, {43.6604, -79.5132}, {43.6389, -79.5067}, {43.6363, -79.4976}, {43.6254, -79.5132} };
        for (int i = 0; i < loc.length; i++) {

            ContentValues contentVal = new ContentValues();
            contentVal.put(COL_ADDRESS, loc[i]);
            contentVal.put(COL_LATITUDE, coordi[i][0]);
            contentVal.put(COL_LONGITUDE, coordi[i][1]);
            db.insert(TABLE_NAME, null, contentVal);
            Log.d("DatabaseHelper", "Inserted sample location: " + loc[i]);

        }
    }

    public boolean insertLocation(String address, double latitude, double longitude) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_LATITUDE, latitude);
        contentValues.put(COL_LONGITUDE, longitude);

        long res = db.insert(TABLE_NAME, null, contentValues);
        return res != -1;

    }

    public Cursor queryLocation(String address) {

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ADDRESS + " = ?", new String[]{address});
    }

    public boolean updateLocation(String address, double latitude, double longitude) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_LATITUDE, latitude);
        contentValues.put(COL_LONGITUDE, longitude);
        int res = db.update(TABLE_NAME, contentValues, COL_ADDRESS + " = ?", new String[]{address});
        return res > 0;
    }

    public boolean deleteLocation(String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME, COL_ADDRESS + " = ?", new String[]{address});
        return res > 0;
    }
}
