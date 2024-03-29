package edu.aku.hassannaqvi.hfplisting.ui.sections;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.editor;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.listings;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.maxStructure;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.selectedAreaCode;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.sharedPref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.hfplisting.MainActivity;
import edu.aku.hassannaqvi.hfplisting.R;
import edu.aku.hassannaqvi.hfplisting.contracts.TableContracts;
import edu.aku.hassannaqvi.hfplisting.core.MainApp;
import edu.aku.hassannaqvi.hfplisting.database.DatabaseHelper;
import edu.aku.hassannaqvi.hfplisting.databinding.ActivitySectionBBinding;

public class SectionBActivity extends AppCompatActivity {
    private static final String TAG = "SectionBActivity";
    ActivitySectionBBinding bi;
    String st = "";
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        bi.setListings(listings);
        st = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime());
        setupSkips();
        setGPS();
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;

//        maxStructure++;
//        MainApp.clusterInfo = sharedPref.getString(selectedCluster.getEbcode(), "0|0").split("\\|");
//        MainApp.clusterInfo = sharedPref.getString(selectedAreaCode, "0|0").split("\\|");
        String structureStr = null;
        int listingCount = db.getListingsCountByArea(selectedAreaCode);
        if (listingCount > 0) {
            for (int i = 0; i < listingCount; i++) {
//                Listings _listings = db.getStructureFromAreaCode(selectedAreaCode, i);
                boolean isQueryNotEmpty = db.getStructureFromAreaCode(selectedAreaCode, i);
                if (!isQueryNotEmpty)
                    break;
                if (listings.getHh04() != null && !listings.getHh04().equals("")) {
                    structureStr = listings.getHh04();
                    break;
                }
            }
        }
//        String structureStr = db.getStructureFromAreaCode(selectedAreaCode, 0);
        if (structureStr != null && !structureStr.equals(""))
            MainApp.clusterInfo = (new String[]{structureStr + "|" + listings.getTabNo()})[0].split("\\|");
        else
            MainApp.clusterInfo = sharedPref.getString(selectedAreaCode, "0|0").split("\\|");

        int structure = Integer.parseInt(MainApp.clusterInfo[0]);
        if (selectedAreaCode.equals("1204") && structure < 479) {
            structure = 479;
            editor.putString(selectedAreaCode, String.valueOf(structure));
            editor.apply();
        }
        maxStructure = structure + 1;

        MainApp.hhid = 0;
        MainApp.hhid_char = "X";

        listings.setHh04(String.valueOf(maxStructure));
        listings.setHh02a("");
        listings.setHh02b("");
        listings.setHh07("");
        listings.setHh0717x("");
        listings.setHh08("");
        listings.setHh09("");
        listings.setHh10("");
        listings.setHh05("");
        listings.setHh11("");
        listings.setHh12("");
        listings.setHh12a("");
        listings.setHh12b("");
        listings.setHh12c("");
        listings.setHh12d("");
        listings.setHh12e("");
        listings.setHh12f1("");
        listings.setHh12f2("");
        listings.setHh12f3("");
        listings.setHh13("");
        listings.setHh13a("");
        listings.setHh14("");
        listings.setHh14a("");
        listings.setHh15("");
        listings.setHh16("");
        listings.setHh17("");

        bi.hhid.setText(MainApp.selectedFacilityName + " | " + MainApp.selectedAreaName + "\n" + "HFP-" +
                MainApp.selectedAreaCode + "\n" + String.format("%s-%04d", MainApp.selectedTab, maxStructure));
        Toast.makeText(this, "Staring Structure", Toast.LENGTH_SHORT).show();

    }

    private void setupSkips() {

        bi.hh07.setOnCheckedChangeListener((radioGroup, i) -> {
            if (bi.hh0701.isChecked()) {
                Clear.clearAllFields(bi.fldGrpCVhh08);
            } else {
                Clear.clearAllFields(bi.fldGrpCVhh09);
                Clear.clearAllFields(bi.fldGrpCVhh10);
            }

            if (bi.hh0701.isChecked() || bi.hh0712.isChecked() || bi.hh0713.isChecked() || bi.hh0714.isChecked() ||
                    bi.hh0715.isChecked() || bi.hh0716.isChecked() || bi.hh0717.isChecked()) {
                Clear.clearAllFields(bi.fldGrpCVhh08);
                Clear.clearAllFields(bi.fldGrpCVhh09);
                Clear.clearAllFields(bi.fldGrpCVhh10);
                bi.btnContinue.setText("Continue to Next");
            }

            if (bi.hh0718.isChecked() || bi.hh0719.isChecked()) {
                Clear.clearAllFields(bi.fldGrpCVhh08);
                Clear.clearAllFields(bi.fldGrpCVhh09);
                Clear.clearAllFields(bi.fldGrpCVhh10);
                bi.btnContinue.setText("Close Listing");
            }

        });

        bi.hh0902.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVhh10));
    }


    private boolean insertRecord() throws JSONException {
        long rowId = 0;
        MainApp.listings.populateMeta();
        rowId = db.addListings(listings);

        if (rowId > 0) {
            long updCount = 0;

            listings.setId(String.valueOf(rowId));
            listings.setUid(listings.getDeviceId() + listings.getId());

            updCount = db.updateFormColumn(TableContracts.ListingsTable.COLUMN_UID, listings.getUid());

            if (updCount > 0) {

//                editor.putString(selectedCluster.getEbcode(), maxStructure + "|" + listings.getTabNo());
                editor.putString(selectedAreaCode, maxStructure + "|" + listings.getTabNo());
                editor.apply();

                return true;
            }

        } else {
            Toast.makeText(this, "Updating Database… ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }


    public void btnContinue(View view) {
        if (!formValidation()) return;
        if (bi.hh0718.isChecked() || bi.hh0719.isChecked()) {
            maxStructure--;
            listings.setHh04("");
        }

//        editor.putInt("new_max", maxStructure);
//        editor.apply();

        try {
            if (insertRecord()) {
                finish();
                Intent i = null;
                if (bi.hh0718.isChecked() || bi.hh0719.isChecked()) {
                    i = new Intent(this, MainActivity.class);

                } else if (listings.getHh08().equals("1")) {
                    i = new Intent(this, FamilyListingActivity.class);
                    MainApp.hhid = 0;
                    MainApp.hhid_char = "X";
                    MainApp.childNumber = 0;

                } else {
                    i = new Intent(this, SectionBActivity.class);
                }

                if (listings.getHh09().equals("1")) {
                    MainApp.hhid_char = "A";
                }

                startActivity(i);
            } else Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSONException(Listings):" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Points set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            listings.setGpsLat(lat);
            listings.setGpsLng(lang);
            listings.setGpsAcc(acc);
            listings.setGpsDT(date); // Timestamp is converted to date above

//            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
    }
}