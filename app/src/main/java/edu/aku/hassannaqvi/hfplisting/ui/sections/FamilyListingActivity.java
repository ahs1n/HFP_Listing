package edu.aku.hassannaqvi.hfplisting.ui.sections;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.listings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.hfplisting.R;
import edu.aku.hassannaqvi.hfplisting.contracts.TableContracts;
import edu.aku.hassannaqvi.hfplisting.core.MainApp;
import edu.aku.hassannaqvi.hfplisting.database.DatabaseHelper;
import edu.aku.hassannaqvi.hfplisting.databinding.ActivityFamilyListingBinding;

public class FamilyListingActivity extends AppCompatActivity {
    private static final String TAG = "FamilyListingActivity";
    ActivityFamilyListingBinding bi;
    String st = "";
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_family_listing);
        bi.setCallback(this);
        bi.setListings(listings);
        st = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime());
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;

        MainApp.hhid++;
        MainApp.mwraCount = 0;
        listings.setHh05(String.valueOf(MainApp.hhid));
        listings.setHh11("");
        listings.setHh12("");
        listings.setHh12a("");
        listings.setHh12b("");
        listings.setHh12c("");
        listings.setHh13("");
        listings.setHh13a("");
        listings.setHh14("");
        listings.setHh14a("");
        listings.setHh15("");
        bi.btnEnd.setVisibility(MainApp.hhid == 1 ? View.GONE : View.VISIBLE);

        if (MainApp.hhid >= Integer.parseInt(MainApp.listings.getHh10())) {
            bi.fldGrpCVhh15.setVisibility(View.VISIBLE);
        }

        bi.hhid.setText("HFP-" + MainApp.listings.getHh01() + "\n" + MainApp.selectedTab + "-" + String.format("%04d", MainApp.maxStructure) + "-" + String.format("%03d", MainApp.hhid));
        Toast.makeText(this, "Staring Household", Toast.LENGTH_SHORT).show();

        bi.hh1301.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    bi.hh13a.setMaxvalue(Float.parseFloat(bi.hh12.getText().toString()) - 1f);
                } catch (NumberFormatException e) {
                    bi.hh13a.setMaxvalue(0f);
                }

            }
        });

        bi.hh1401.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    bi.hh14a.setMaxvalue(Float.parseFloat(bi.hh13a.getText().toString()));
                } catch (NumberFormatException e) {
                    bi.hh14a.setMaxvalue(0f);
                }
            }
        });
    }

    private boolean updateDB() {
        long updcount = 0;
        try {
            updcount = db.updateFormColumn(TableContracts.ListingsTable.COLUMN_SC, listings.sCtoString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, R.string.upd_db_form + e.getMessage());
            Toast.makeText(this, R.string.upd_db_form + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (updcount > 0) return true;
        else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private boolean insertRecord() {
        long rowId = 0;

        try {
            rowId = db.addListings(listings);

            if (rowId > 0) {
                long updCount = 0;

                listings.setId(String.valueOf(rowId));
                listings.setUid(listings.getDeviceId() + listings.getId());

                updCount = db.updateFormColumn(TableContracts.ListingsTable.COLUMN_UID, listings.getUid());

                if (updCount > 0) {
                    return updateDB();
                }

            } else {
                Toast.makeText(this, "Updating Database… ERROR!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSONException(CR):" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return false;

    }


    public void btnContinue(View view) {
        if (!formValidation()) return;
        //saveDraft();
        if (MainApp.hhid == 1 ? updateDB() : insertRecord()) {
            finish();
            if (MainApp.hhid < Integer.parseInt(MainApp.listings.getHh10()) || listings.getHh15().equals("1")) {
                //   Toast.makeText(this, "Staring Family", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, FamilyListingActivity.class));

            } else {
                //     Toast.makeText(this, "Staring Household", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SectionBActivity.class));

            }
        } else Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
    }


    public void btnEnd(View view) {
        bi.hh11.setText("Deleted");
        //bi.hh14.setText("Deleted");
        bi.hh14a.setText("Deleted");
        bi.hh13.clearCheck();
        bi.hh13a.setText("00");

        //saveDraft();
        if (insertRecord()) {
            finish();

            //     Toast.makeText(this, "Staring Household", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SectionBActivity.class));

        } else Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
    }


    private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) return false;

        if (!bi.hh12.getText().toString().equals("")) {
            if (Integer.parseInt(bi.hh12a.getText().toString()) > Integer.parseInt(bi.hh12.getText().toString())) {
                Validator.emptyCustomTextBox(this, bi.hh12a, "Total male cannot be more than Total Members");
                return false;
            }

            if (Integer.parseInt(bi.hh12b.getText().toString()) > Integer.parseInt(bi.hh12.getText().toString())) {
                Validator.emptyCustomTextBox(this, bi.hh12b, "Total female cannot be more than Total Members");
                return false;
            }

            if (Integer.parseInt(bi.hh12c.getText().toString()) > Integer.parseInt(bi.hh12.getText().toString())) {
                Validator.emptyCustomTextBox(this, bi.hh12c, "Total maried women cannot be more than Total Members");
                return false;
            }

            if (Integer.parseInt(bi.hh12c.getText().toString()) > Integer.parseInt(bi.hh12b.getText().toString())) {
                Validator.emptyCustomTextBox(this, bi.hh12c, "Total maried women cannot be more than Total Females");
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
      /*  finish();
        startActivity(new Intent(this, MainActivity.class));*/
    }
}
