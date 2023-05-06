package edu.aku.hassannaqvi.hfplisting.ui.sections;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.listings;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.selectedArea;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.selectedCluster;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.selectedTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.hfplisting.MainActivity;
import edu.aku.hassannaqvi.hfplisting.R;
import edu.aku.hassannaqvi.hfplisting.core.MainApp;
import edu.aku.hassannaqvi.hfplisting.database.DatabaseHelper;
import edu.aku.hassannaqvi.hfplisting.databinding.ActivitySectionABinding;
import edu.aku.hassannaqvi.hfplisting.models.Cluster;
import edu.aku.hassannaqvi.hfplisting.models.Listings;

public class SectionAActivity extends AppCompatActivity {
    private static final String TAG = "SectionAActivity";
    ActivitySectionABinding bi;
    String st = "";
    private DatabaseHelper db;
    private ArrayList<String> ebCode, districtNames, tehsilNames, headHH;
    private ArrayList<String> distNames, distCodes, areaNames, areaCodes, facilityNames, facilityCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        selectedCluster = new Cluster();
        selectedArea = new Listings();
        listings = new Listings();
        bi.setListings(listings);
        st = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime());
        setSupportActionBar(bi.toolbar);
        populateSpinner();
    }

    private void populateSpinner() {

        Collection<Cluster> clusters = db.getAllDistricts(MainApp.user.getDist_id());
        distNames = new ArrayList<>();
        distCodes = new ArrayList<>();
        distNames.add("...");
        distCodes.add("...");

        for (Cluster cluster : clusters) {
            distNames.add(cluster.getDistName());
            distCodes.add(cluster.getDistId());
        }

        if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("user")) {
            distNames.add("Test Dist 9");
            distNames.add("Test Dist 8");
            distNames.add("Test Dist 7");
            distCodes.add("9");
            distCodes.add("8");
            distCodes.add("7");
        }

        // Apply the adapter to the spinner
        bi.hh01a.setAdapter(new ArrayAdapter<>(SectionAActivity.this, R.layout.custom_spinner, distNames));


        bi.hh01a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bi.openForm.setVisibility(View.GONE);
                bi.hh01b.setAdapter(null);
                bi.hh01c.setAdapter(null);
                if (position == 0) return;
                Collection<Cluster> clusters = db.getHealthFacilityByCluster(MainApp.user.getDist_id());
                facilityNames = new ArrayList<>();
                facilityCodes = new ArrayList<>();
                facilityNames.add("...");
                facilityCodes.add("...");

                MainApp.selectedDistrictName = (distNames.get(bi.hh01a.getSelectedItemPosition()));
                MainApp.selectedDistrictCode = (distCodes.get(bi.hh01a.getSelectedItemPosition()));

                for (Cluster cluster : clusters) {
                    facilityNames.add(cluster.getHf_name());
                    facilityCodes.add(cluster.getHf_code());
                }
                if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("user")) {

                    facilityNames.add("Test Facility 1 " + distNames.get(position));
                    facilityNames.add("Test Facility 2 " + distNames.get(position));
                    facilityNames.add("Test Facility 3 " + distNames.get(position));
                    facilityCodes.add(distCodes.get(position) + "001");
                    facilityCodes.add(distCodes.get(position) + "002");
                    facilityCodes.add(distCodes.get(position) + "003");
                }

                // Apply the adapter to the spinner
                bi.hh01b.setAdapter(new ArrayAdapter<>(SectionAActivity.this, R.layout.custom_spinner, facilityNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


        bi.hh01b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bi.openForm.setVisibility(View.GONE);
                bi.hh01c.setAdapter(null);
                if (position == 0) return;
                Collection<Cluster> clusters = db.getAreaByHealthFacility(facilityCodes.get(position));
                areaNames = new ArrayList<>();
                areaCodes = new ArrayList<>();
                areaNames.add("...");
                areaCodes.add("...");

                for (Cluster cluster : clusters) {
                    areaNames.add(cluster.getArea());
                    areaCodes.add(cluster.getArea_code());
                }
                if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("user")) {

                    areaNames.add("Test Area 1 " + facilityNames.get(position));
                    areaNames.add("Test Area 2 " + facilityNames.get(position));
                    areaNames.add("Test Area 3 " + facilityNames.get(position));
                    areaCodes.add(facilityCodes.get(position) + "001");
                    areaCodes.add(facilityCodes.get(position) + "002");
                    areaCodes.add(facilityCodes.get(position) + "003");
                }

                MainApp.selectedFacilityName = (facilityNames.get(bi.hh01b.getSelectedItemPosition()));
                MainApp.selectedFacilityCode = (facilityCodes.get(bi.hh01b.getSelectedItemPosition()));

                // Apply the adapter to the spinner
                bi.hh01c.setAdapter(new ArrayAdapter<>(SectionAActivity.this, R.layout.custom_spinner, areaNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        bi.hh01c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bi.openForm.setVisibility(View.GONE);
                bi.fldGrpCVhh03.setVisibility(View.GONE);
                if (position == 0) {
                    //bi.fldGrpCVhh03.setVisibility(View.GONE);
                } else {
                    MainApp.selectedAreaName = areaNames.get(bi.hh01c.getSelectedItemPosition());
                    MainApp.selectedAreaCode = areaCodes.get(bi.hh01c.getSelectedItemPosition());
                    listings.setAreaCode(MainApp.selectedAreaCode);
                    listings.setCluster(MainApp.selectedAreaCode);
                    listings.setHh01(MainApp.selectedAreaCode);
                    bi.openForm.setVisibility(View.VISIBLE);
                    bi.openForm.setEnabled(true);
                    searchArea();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }

    public void searchArea() {
        bi.hh03.clearCheck();
        bi.fldGrpCVhh03.setVisibility(View.GONE);

        try {
            selectedArea = db.getArea(MainApp.selectedAreaCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (selectedArea != null) {
            listings.setGeoArea(selectedArea.getGeoArea());
            bi.fldGrpCVhh03.setVisibility(View.GONE);

            /*MainApp.clusterInfo = sharedPref.getString(selectedArea.getAreaCode(), "0|0").split("\\|");
            MainApp.maxStructure = Integer.parseInt(MainApp.clusterInfo[0]);

            if (!MainApp.clusterInfo[0].equals("0")) {
                if (MainApp.clusterInfo[1].equals("A")) {
                    listings.setHh03("1");
                    bi.fldGrpCVhh03.setVisibility(View.GONE);
                } else if (MainApp.clusterInfo[1].equals("B")) {
                    listings.setHh03("2");
                    bi.fldGrpCVhh03.setVisibility(View.GONE);
                }
                selectedTab = listings.getTabNo();
            } else {
                bi.hh03.clearCheck();
                listings.setHh03("");
                bi.fldGrpCVhh03.setVisibility(View.VISIBLE);
            }
            MainApp.selectedTab = MainApp.clusterInfo[1];*/

//            bi.ebMsg.setText("Existing structures: " + listings.getHh04());
        } else {
            bi.hh03.clearCheck();
            listings.setHh03("");
            bi.ebMsg.setText("");
            bi.fldGrpCVhh03.setVisibility(View.VISIBLE);
        }
    }

    public void btnContinue(View view) {
        if (!formValidation()) return;

        if (bi.fldGrpCVhh03.getVisibility() == View.VISIBLE)
            selectedTab = bi.hh031.isChecked() ? "A" : "B";
        else
            selectedTab = selectedArea.getTabNo();
        listings.setTabNo(selectedTab);
        finish();
        startActivity(new Intent(this, SectionBActivity.class));

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
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        //  startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
    }
}