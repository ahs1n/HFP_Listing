package edu.aku.hassannaqvi.hfplisting.ui.sections;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.listings;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.maxStructure;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.childNumber;
import static edu.aku.hassannaqvi.hfplisting.core.MainApp.child;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.hfplisting.R;
import edu.aku.hassannaqvi.hfplisting.contracts.TableContracts;
import edu.aku.hassannaqvi.hfplisting.core.MainApp;
import edu.aku.hassannaqvi.hfplisting.database.DatabaseHelper;
import edu.aku.hassannaqvi.hfplisting.databinding.ActivityVaccinationBinding;
import edu.aku.hassannaqvi.hfplisting.models.Children;

public class VaccinationActivity extends AppCompatActivity {
    private static final String TAG = "VaccinationActivity";
    ActivityVaccinationBinding bi;
    String st = "";
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_vaccination);
        bi.setCallback(this);
        child = new Children();
        bi.setChild(child);
        st = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime());
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;

        bi.hhid.setText(MainApp.selectedFacilityName + " | " + MainApp.selectedAreaName + "\n" + "HFP-" + MainApp.selectedAreaCode + "\n" + String.format("%04d", maxStructure) + "-" + (MainApp.hhid_char) + "\n" + String.format("Child - %02d", ++childNumber));
    }

    public void btnContinue(View view) {
        if (!formValidation()) return;

        if (insertRecord()) {
            finish();

            Intent i = null;
            if (childNumber < Integer.parseInt(listings.getHh13a())) {
                i = new Intent(this, VaccinationActivity.class);
            } else if (MainApp.hhid < Integer.parseInt(MainApp.listings.getHh10()) || listings.getHh15().equals("1")) {
                //   Toast.makeText(this, "Staring Family", Toast.LENGTH_SHORT).show();

                MainApp.hhid_char = String.valueOf((char) (MainApp.hhid_char.charAt(0) + 1));
                i = new Intent(this, FamilyListingActivity.class);

            } else {
                i = new Intent(this, SectionBActivity.class);
            }
            startActivity(i);

        } else Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();

    }

    public void btnEnd(View view) {
        bi.childName.setText("Deleted");
        bi.vcard.setText("Deleted");
        bi.ageInMonths.setText("");

        //saveDraft();
        if (insertRecord()) {
            finish();
            Intent i = null;
            if (childNumber < Integer.parseInt(listings.getHh13a())) {
                i = new Intent(this, VaccinationActivity.class);
            } else if (MainApp.hhid < Integer.parseInt(MainApp.listings.getHh10()) || listings.getHh15().equals("1")) {
                //   Toast.makeText(this, "Staring Family", Toast.LENGTH_SHORT).show();

                MainApp.hhid_char = String.valueOf((char) (MainApp.hhid_char.charAt(0) + 1));
                i = new Intent(this, FamilyListingActivity.class);

            } else {
                i = new Intent(this, SectionBActivity.class);
            }

            startActivity(i);

        } else Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
    }

    private boolean insertRecord(){
        long rowId = 0;
        child.populateMeta();
        child.setUuid(listings.getUid());
        child.setHh01(listings.getHh01());
        child.setHh04(listings.getHh04());
        child.setHh05(listings.getHh05());
        try {
            rowId = db.addChild(child);

            if (rowId > 0) {
                long updCount = 0;

                child.setId(String.valueOf(rowId));
                child.setUid(child.getDeviceId() + child.getId());

                updCount = db.updateFormChildrenColumn(TableContracts.ChildrenTable.COLUMN_UID, child.getUid());

                if (updCount > 0) {
                    return true;
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

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.ChildrenGroup);
    }
}