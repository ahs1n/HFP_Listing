package edu.aku.hassannaqvi.hfplisting.models;

import static edu.aku.hassannaqvi.hfplisting.core.MainApp.PROJECT_NAME;

import android.database.Cursor;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.PropertyChangeRegistry;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.hfplisting.BR;
import edu.aku.hassannaqvi.hfplisting.contracts.TableContracts;
import edu.aku.hassannaqvi.hfplisting.core.MainApp;

public class Children extends BaseObservable {

    private final String TAG = "Children";
    private final transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    // APP VARIABLES
    private String projectName = PROJECT_NAME;
    // APP VARIABLES
    private String id = StringUtils.EMPTY;
    private String uid = StringUtils.EMPTY;
    private String uuid = StringUtils.EMPTY;
    private String cluster = StringUtils.EMPTY;
    private String enumCode = StringUtils.EMPTY;
    private String userName = StringUtils.EMPTY;
    private String sysDate = StringUtils.EMPTY;
    private String tabNo = StringUtils.EMPTY;
    private String geoArea = StringUtils.EMPTY;
    private String deviceId = StringUtils.EMPTY;
    private String deviceTag = StringUtils.EMPTY;
    private String appver = StringUtils.EMPTY;
    private String endTime = StringUtils.EMPTY;
    private String startTime = StringUtils.EMPTY;
    private String iStatus = StringUtils.EMPTY;
    private String iStatus96x = StringUtils.EMPTY;
    private String synced = StringUtils.EMPTY;
    private String syncDate = StringUtils.EMPTY;
    private String gpsLat = StringUtils.EMPTY;
    private String gpsLng = StringUtils.EMPTY;
    private String gpsDT = StringUtils.EMPTY;
    private String gpsAcc = StringUtils.EMPTY;

    private String hh01 = StringUtils.EMPTY;
    private String hh04 = StringUtils.EMPTY;
    private String hh05 = StringUtils.EMPTY;
    private String childName = StringUtils.EMPTY;
    private String vcard = StringUtils.EMPTY;
    private String ageInMonths = StringUtils.EMPTY;

    public void populateMeta() {

        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setDeviceId(MainApp.deviceid);
        setAppver(MainApp.appInfo.getAppVersion());
        setProjectName(PROJECT_NAME);

        setUserName(MainApp.user.getUserName());
        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setDeviceId(MainApp.deviceid);
        setAppver(MainApp.versionName + "." + MainApp.versionCode);
        setEnumCode(MainApp.user.getDist_id());

    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getTabNo() {
        return tabNo;
    }

    public void setTabNo(String tabNo) {
        this.tabNo = tabNo;
    }

    public String getGeoArea() {
        return geoArea;
    }

    public void setGeoArea(String geoArea) {
        this.geoArea = geoArea;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceTag() {
        return deviceTag;
    }

    public void setDeviceTag(String deviceTag) {
        this.deviceTag = deviceTag;
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getiStatus() {
        return iStatus;
    }

    public void setiStatus(String iStatus) {
        this.iStatus = iStatus;
    }

    public String getiStatus96x() {
        return iStatus96x;
    }

    public void setiStatus96x(String iStatus96x) {
        this.iStatus96x = iStatus96x;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getHh01() {
        return hh01;
    }

    public void setHh01(String hh01) {
        this.hh01 = hh01;
    }

    public String getHh04() {
        return hh04;
    }

    public void setHh04(String hh04) {
        this.hh04 = hh04;
    }

    public String getHh05() {
        return hh05;
    }

    public void setHh05(String hh05) {
        this.hh05 = hh05;
    }

    @Bindable
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
        notifyPropertyChanged(BR.childName);
    }

    @Bindable
    public String getVcard() {
        return vcard;
    }

    public void setVcard(String vcard) {
        this.vcard = vcard;
        notifyPropertyChanged(BR.vcard);
    }

    @Bindable
    public String getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(String ageInMonths) {
        this.ageInMonths = ageInMonths;
        notifyPropertyChanged(BR.ageInMonths);
    }

    public Children Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_UID));
        this.uuid = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_UUID));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_USERNAME));
        this.cluster = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_CLUSTER));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_SYSDATE));
        this.tabNo = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_TAB_NO));
        this.geoArea = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_GEOAREA));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_DEVICEID));
        this.deviceTag = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_DEVICETAGID));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_SYNCED_DATE));
        this.endTime = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_END_TIME));
        this.startTime = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_START_TIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_GPSACC));
        sVHydrate(cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.ChildrenTable.COLUMN_SV)));

        return this;
    }

    public void sVHydrate(String string) throws JSONException {
        Log.d(TAG, "sVHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.hh01 = json.getString("hh01");
            this.hh04 = json.getString("hh04");
            this.hh05 = json.getString("hh05");
            this.childName = json.getString("childName");
            this.vcard = json.getString("vcard");
            this.ageInMonths = json.getString("ageInMonths");
        }
    }

    public String sVtoString() throws JSONException {
        Log.d(TAG, "sVtoString: ");
        JSONObject json = new JSONObject();

        json.put("hh01", hh01)
                .put("hh04", hh04)
                .put("hh05", hh05)
                .put("childName", childName)
                .put("vcard", vcard)
                .put("ageInMonths", ageInMonths);

        return json.toString();
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(TableContracts.ChildrenTable.COLUMN_ID, this.id);
        json.put(TableContracts.ChildrenTable.COLUMN_UID, this.uid);
        json.put(TableContracts.ChildrenTable.COLUMN_UUID, this.uuid);
        json.put(TableContracts.ChildrenTable.COLUMN_USERNAME, this.userName);
        json.put(TableContracts.ChildrenTable.COLUMN_CLUSTER, this.cluster);
        json.put(TableContracts.ChildrenTable.COLUMN_SYSDATE, this.sysDate);
        json.put(TableContracts.ChildrenTable.COLUMN_TAB_NO, this.tabNo);
        json.put(TableContracts.ChildrenTable.COLUMN_GEOAREA, this.geoArea);
        json.put(TableContracts.ChildrenTable.COLUMN_DEVICEID, this.deviceId);
        json.put(TableContracts.ChildrenTable.COLUMN_DEVICETAGID, this.deviceTag);
        json.put(TableContracts.ChildrenTable.COLUMN_ISTATUS, this.iStatus);
        json.put(TableContracts.ChildrenTable.COLUMN_APPVERSION, this.appver);
        json.put(TableContracts.ChildrenTable.COLUMN_SYNCED, this.synced);
        json.put(TableContracts.ChildrenTable.COLUMN_SYNCED_DATE, this.syncDate);
        json.put(TableContracts.ChildrenTable.COLUMN_GPSLAT, this.gpsLat);
        json.put(TableContracts.ChildrenTable.COLUMN_GPSLNG, this.gpsLng);
        json.put(TableContracts.ChildrenTable.COLUMN_GPSDATE, this.gpsDT);
        json.put(TableContracts.ChildrenTable.COLUMN_GPSACC, this.gpsAcc);
        json.put(TableContracts.ChildrenTable.COLUMN_SV, new JSONObject(sVtoString()));
        json.put(TableContracts.ChildrenTable.COLUMN_END_TIME, this.endTime);
        json.put(TableContracts.ChildrenTable.COLUMN_START_TIME, this.startTime);

        return json;

    }

}
