package edu.aku.hassannaqvi.hfplisting.ui.sections;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.listings;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.selectedCluster;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.selectedTab;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.sharedPref;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    private ArrayList<String> distNames, distCodes, areaNames, cityNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        selectedCluster = new Cluster();
        listings = new Listings();
        bi.setListings(listings);
        st = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime());
        setupSkips();
        setSupportActionBar(bi.toolbar);

        populateSpinner();

        bi.hh01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*bi.hh01a.setText(null);
                bi.hh01b.setText(null);
                bi.hh01c.setText(null);*/
                bi.hh02.setChecked(false);
                bi.hh03.clearCheck();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 9) {
                    bi.searchEB.setBackgroundColor(getResources().getColor(R.color.greenLight));
                    bi.searchEB.setEnabled(true);
                } else {
                    bi.searchEB.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    bi.searchEB.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*bi.hh01a.setText(null);
                bi.hh01b.setText(null);
                bi.hh01c.setText(null);
                bi.hh01a.setError(null);
                bi.hh01b.setError(null);
                bi.hh01c.setError(null);*/
                bi.hh02.setChecked(false);
                bi.hh03.clearCheck();
                bi.openForm.setEnabled(false);
                bi.openForm.setVisibility(View.GONE);
                bi.fldGrpCVhh02.setVisibility(View.GONE);
                bi.fldGrpCVhh03.setVisibility(View.GONE);
                bi.ebMsg.setText(null);

            }
        });

        bi.hh02.setOnCheckedChangeListener((compoundButton, b) -> {
            if (!selectedCluster.getEbcode().equals("") && b) {
                bi.openForm.setEnabled(true);
//                bi.openForm.setVisibility(View.VISIBLE);

            } else {
                bi.openForm.setEnabled(false);
                bi.openForm.setVisibility(View.GONE);
            }
        });

    }


    private void setupSkips() {
    }

    private void populateSpinner() {

        Collection<Cluster> clusters = db.getAllClusters(MainApp.user.getDist_id());
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

                if (position == 0) return;
                Collection<Cluster> clusters = db.getCityByCluster(MainApp.user.getDist_id());
                cityNames = new ArrayList<>();
                cityNames.add("...");

                for (Cluster cluster : clusters) {
                    cityNames.add(cluster.getCity());
                }
                if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("user")) {

                    cityNames.add("Test City 1 " + distNames.get(position));
                    cityNames.add("Test City 2 " + distNames.get(position));
                    cityNames.add("Test City 3 " + distNames.get(position));
                }

                // Apply the adapter to the spinner
                bi.hh01b.setAdapter(new ArrayAdapter<>(SectionAActivity.this, R.layout.custom_spinner, cityNames));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


        bi.hh01b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;
                Collection<Cluster> clusters = db.getAreaByCity(MainApp.user.getDist_id());
                areaNames = new ArrayList<>();
                areaNames.add("...");

                for (Cluster cluster : clusters) {
                    areaNames.add(cluster.getArea());
                }
                if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("user")) {

                    areaNames.add("Test Area 1 " + cityNames.get(position));
                    areaNames.add("Test Area 2 " + cityNames.get(position));
                    areaNames.add("Test Area 3 " + cityNames.get(position));
                }

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
                bi.openForm.setEnabled(true);
                bi.openForm.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


        List<Cluster> clustersList = db.getClusters();
        ebCode = new ArrayList<>();
        districtNames = new ArrayList<>();
        tehsilNames = new ArrayList<>();
        cityNames = new ArrayList<>();
        for (Cluster c : clustersList) {
            ebCode.add(c.getEbcode());
            String[] geoArea = c.getGeoarea().split("\\|");
            districtNames.add(geoArea[1]);
            tehsilNames.add(geoArea[2]);
            cityNames.add(geoArea[3]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ebCode);
    }

    public void btnContinue(View view) {
        if (!formValidation()) return;

        selectedTab = listings.getTabNo();
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

    public void searchEB(View view) {
        /*bi.hh01a.setText("");
        bi.hh01b.setText("");
        bi.hh01c.setText("");*/
        bi.hh02.setChecked(false);
        bi.hh03.clearCheck();
        bi.openForm.setEnabled(false);
        bi.fldGrpCVhh02.setVisibility(View.GONE);
        bi.fldGrpCVhh03.setVisibility(View.GONE);

        Cluster testEb = new Cluster();
        testEb.setEbcode("909090909");
        testEb.setGeoarea("|Test District|Test Tehsil|Test City");

        if (!bi.hh01.getText().toString().equals(testEb.getEbcode())) {
            selectedCluster = db.getClusters(bi.hh01.getText().toString());

            if (selectedCluster != null) {
                listings.setGeoArea(selectedCluster.getGeoarea());
                MainApp.clusterInfo = sharedPref.getString(selectedCluster.getEbcode(), "0|0").split("\\|");
                MainApp.maxStructure = Integer.parseInt(MainApp.clusterInfo[0]);


                /*bi.hh01a.setError(null);
                bi.hh01b.setError(null);
                bi.hh01c.setError(null);*/

                String[] geoArea = selectedCluster.getGeoarea().split("\\|");
                /*bi.hh01a.setText(geoArea[1]);
                bi.hh01b.setText(geoArea[2]);
                bi.hh01c.setText(geoArea[3]);*/

                bi.fldGrpCVhh02.setVisibility(View.VISIBLE);

                if (bi.hh02.isChecked())
                    bi.openForm.setEnabled(true);
//                bi.openForm.setVisibility(View.VISIBLE);


                if (!MainApp.clusterInfo[0].equals("0")) {
                    //bi.fldGrpCVhh02e.setVisibility(View.GONE);
                    if (MainApp.clusterInfo[1].equals("A")) {
                        //bi.hh031.setChecked(true);
                        listings.setHh03("1");
                        bi.fldGrpCVhh03.setVisibility(View.GONE);

                    } else if (MainApp.clusterInfo[1].equals("B")) {
                        //bi.hh032.setChecked(true);
                        listings.setHh03("2");
                        bi.fldGrpCVhh03.setVisibility(View.GONE);

                    }
                    selectedTab = listings.getTabNo();
                } else {
                    bi.hh03.clearCheck();
                    listings.setHh03("");
                    bi.fldGrpCVhh03.setVisibility(View.VISIBLE);
                }
                MainApp.selectedTab = MainApp.clusterInfo[1];
                bi.ebMsg.setText("Existing structures: " + MainApp.maxStructure);


            }
        } else {
            selectedCluster = testEb;
            MainApp.maxStructure = 0;
            MainApp.selectedTab = "";
            bi.ebMsg.setText(null);
            /*bi.hh01a.setError("Not Found!");
            bi.hh01b.setError("Not Found!");
            bi.hh01c.setText("Not Found!");*/

        }

    }

}