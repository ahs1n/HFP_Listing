package edu.aku.hassannaqvi.hfplisting;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.sharedPref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import java.io.File;

import edu.aku.hassannaqvi.hfplisting.core.MainApp;
import edu.aku.hassannaqvi.hfplisting.database.AndroidManager;
import edu.aku.hassannaqvi.hfplisting.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.hfplisting.ui.ChangePasswordActivity;
import edu.aku.hassannaqvi.hfplisting.ui.LoginActivity;
import edu.aku.hassannaqvi.hfplisting.ui.SyncActivity;
import edu.aku.hassannaqvi.hfplisting.ui.lists.ListingsReporter;
import edu.aku.hassannaqvi.hfplisting.ui.sections.SectionAActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);
        setSupportActionBar(bi.toolbar);
        bi.adminView.setVisibility(MainApp.admin ? View.VISIBLE : View.GONE);
        bi.username.setText("Welcome, " + MainApp.user.getFullname() + "!");


        String latestVersionName = sharedPref.getString("versionName", "");
        int latestVersionCode = Integer.parseInt(sharedPref.getString("versionCode", "0"));

        if (MainApp.appInfo.getVersionCode() < latestVersionCode) {
            bi.newApp.setVisibility(View.VISIBLE);
            bi.newApp.setText("NOTICE: There is a newer version of this app available on server (" + latestVersionName + latestVersionCode + "). \nPlease download update the app now.");
        } else {
            bi.newApp.setVisibility(View.GONE);

        }
    }

    public void sectionPress(View view) {
        switch (view.getId()) {

            case R.id.openChildForm:
                //MainApp.cr = new Listings();
                //    finish();
                startActivity(new Intent(this, SectionAActivity.class));
                break;
            case R.id.dbm:
                startActivity(new Intent(this, AndroidManager.class));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_database:
                intent = new Intent(MainActivity.this, AndroidManager.class);
                startActivity(intent);
                break;
            case R.id.action_data_sync:
                intent = new Intent(MainActivity.this, SyncActivity.class);
                startActivity(intent);
                break;
            case R.id.action_view_listing:
                intent = new Intent(MainActivity.this, ListingsReporter.class);
                startActivity(intent);
                break;
            case R.id.changePassword:
                intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.sendDB:
                sendEmail();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem action_database = menu.findItem(R.id.action_database);

        action_database.setVisible(MainApp.admin);
        return true;

    }

    // Email database to specified email address as attachment
    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"khalid.feroz@aku.edu"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "HFP Listing Database - For Issue Monitoring");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "HFP Listing database upload from the device which has issues while uploading the data." +
                "This is just for testing/checking purpose.");
        File file = LoginActivity.dbBackup(MainActivity.this);
//        File file = copyFileToFilesDir(DATABASE_NAME);
        if (file == null || !file.exists() || !file.canRead()) {
            Toast.makeText(this, getString(R.string.file_not_found), Toast.LENGTH_SHORT).show();
            return;
        }
//        Uri uri =  FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);

//        Uri uri = Uri.fromFile(file);

//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
//        String type = mime.getMimeTypeFromExtension(ext);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(this, "edu.aku.hassannaqvi.hfplisting.fileProvider", file);
        }else{
            uri = Uri.fromFile(file);
        }
//        emailIntent.setDataAndType(uri, type);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(emailIntent, getString(R.string.pick_email_provider)));
    }
}