package edu.aku.hassannaqvi.hfplisting.database

import edu.aku.hassannaqvi.hfplisting.contracts.TableContracts.*
import edu.aku.hassannaqvi.hfplisting.core.MainApp.PROJECT_NAME
import edu.aku.hassannaqvi.hfplisting.models.HealthFacilities

object CreateTable {

    const val DATABASE_NAME = "$PROJECT_NAME.db"
    const val DATABASE_COPY = "${PROJECT_NAME}_copy.db"
    const val DATABASE_VERSION = 3

    const val SQL_CREATE_LISTINGS = ("CREATE TABLE "
            + ListingsTable.TABLE_NAME + "("
            + ListingsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ListingsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + ListingsTable.COLUMN_UID + " TEXT,"
            + ListingsTable.COLUMN_USERNAME + " TEXT,"
            + ListingsTable.COLUMN_CLUSTER + " TEXT,"
            + ListingsTable.COLUMN_SYSDATE + " TEXT,"
            + ListingsTable.COLUMN_TAB_NO + " TEXT,"
            + ListingsTable.COLUMN_GEOAREA + " TEXT,"
            + ListingsTable.COLUMN_START_TIME + " TEXT,"
            + ListingsTable.COLUMN_END_TIME + " TEXT,"
            + ListingsTable.COLUMN_ISTATUS + " TEXT,"
            + ListingsTable.COLUMN_DEVICEID + " TEXT,"
            + ListingsTable.COLUMN_DEVICETAGID + " TEXT,"
            + ListingsTable.COLUMN_GPSLAT + " TEXT,"
            + ListingsTable.COLUMN_GPSLNG + " TEXT,"
            + ListingsTable.COLUMN_GPSDATE + " TEXT,"
            + ListingsTable.COLUMN_GPSACC + " TEXT,"
            + ListingsTable.COLUMN_AREA_CODE + " TEXT,"
            + ListingsTable.COLUMN_SYNCED + " TEXT,"
            + ListingsTable.COLUMN_SYNCED_DATE + " TEXT,"
            + ListingsTable.COLUMN_APPVERSION + " TEXT,"
            + ListingsTable.COLUMN_SA + " TEXT,"
            + ListingsTable.COLUMN_SB + " TEXT,"
            + ListingsTable.COLUMN_SC + " TEXT"
            + " );"
            )

    const val SQL_CREATE_MWRA = ("CREATE TABLE "
            + MwraTable.TABLE_NAME + "("
            + MwraTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MwraTable.COLUMN_PROJECT_NAME + " TEXT,"
            + MwraTable.COLUMN_UID + " TEXT,"
            + MwraTable.COLUMN_UUID + " TEXT,"
            + MwraTable.COLUMN_USERNAME + " TEXT,"
            + MwraTable.COLUMN_SYSDATE + " TEXT,"
            + MwraTable.COLUMN_START_TIME + " TEXT,"
            + MwraTable.COLUMN_END_TIME + " TEXT,"
            + MwraTable.COLUMN_ISTATUS + " TEXT,"
            + MwraTable.COLUMN_DEVICEID + " TEXT,"
            + MwraTable.COLUMN_DEVICETAGID + " TEXT,"
            + MwraTable.COLUMN_SYNCED + " TEXT,"
            + MwraTable.COLUMN_SYNCED_DATE + " TEXT,"
            + MwraTable.COLUMN_APPVERSION + " TEXT,"
            + MwraTable.COLUMN_SMWRA + " TEXT"

            + " );"
            )

    const val SQL_CREATE_USERS = ("CREATE TABLE "
            + UsersTable.TABLE_NAME + "("
            + UsersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULLNAME + " TEXT,"
            + UsersTable.COLUMN_DIST_ID + " TEXT,"
            + UsersTable.COLUMN_ENABLED + " TEXT,"
            + UsersTable.COLUMN_ISNEW_USER + " TEXT,"
            + UsersTable.COLUMN_PWD_EXPIRY + " TEXT,"
            + UsersTable.COLUMN_DESIGNATION + " TEXT"
            + " );"
            )


/*    const val SQL_CREATE_VERSIONAPP = ("CREATE TABLE "
            + VersionTable.TABLE_NAME + " ("
            + VersionTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VersionTable.COLUMN_VERSION_CODE + " TEXT, "
            + VersionTable.COLUMN_VERSION_NAME + " TEXT, "
            + VersionTable.COLUMN_PATH_NAME + " TEXT "
            + ");"
            )*/

    const val SQL_CREATE_CLUSTERS = ("CREATE TABLE "
            + ClusterTable.TABLE_NAME + "("
            + ClusterTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ClusterTable.COLUMN_GEOAREA + " TEXT,"
            + ClusterTable.COLUMN_DIST_ID + " TEXT,"
            + ClusterTable.COLUMN_AREA_CODE + " TEXT,"
            + ClusterTable.COLUMN_EB_CODE + " TEXT,"
            + ClusterTable.COLUMN_DIST_NAME + " TEXT,"
            + ClusterTable.COLUMN_AREA + " TEXT,"
            + ClusterTable.COLUMN_HF_NAME + " TEXT,"
            + ClusterTable.COLUMN_HF_CODE + " TEXT"
            + " );"
            )

    const val SQL_CREATE_FACILITIES =
        ("CREATE TABLE " + HealthFacilities.TableHealthFacilities.TABLE_NAME + "("
                + HealthFacilities.TableHealthFacilities.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HealthFacilities.TableHealthFacilities.COLUMN_DISTRICT_CODE + " TEXT,"
                + HealthFacilities.TableHealthFacilities.COLUMN_FACILITY_NAME + " TEXT,"
                + HealthFacilities.TableHealthFacilities.COLUMN_FACILITY_CODE + " TEXT"
                + " );")

    const val SQL_CREATE_ENTRYLOGS = ("CREATE TABLE "
            + EntryLogTable.TABLE_NAME + "("
            + EntryLogTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EntryLogTable.COLUMN_PROJECT_NAME + " TEXT,"
            + EntryLogTable.COLUMN_UID + " TEXT,"
            + EntryLogTable.COLUMN_UUID + " TEXT,"
            + EntryLogTable.COLUMN_EB_CODE + " TEXT,"
            + EntryLogTable.COLUMN_HHID + " TEXT,"
            + EntryLogTable.COLUMN_USERNAME + " TEXT,"
            + EntryLogTable.COLUMN_SYSDATE + " TEXT,"
            + EntryLogTable.COLUMN_DEVICEID + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_DATE + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS96x + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_TYPE + " TEXT,"
            + EntryLogTable.COLUMN_SYNCED + " TEXT,"
            + EntryLogTable.COLUMN_SYNC_DATE + " TEXT,"
            + EntryLogTable.COLUMN_APPVERSION + " TEXT"
            + " );"
            )

    const val SQL_CREATE_CHILDREN = ("CREATE TABLE "
            + ChildrenTable.TABLE_NAME + "("
            + ChildrenTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ChildrenTable.COLUMN_PROJECT_NAME + " TEXT,"
            + ChildrenTable.COLUMN_UID + " TEXT,"
            + ChildrenTable.COLUMN_UUID + " TEXT,"
            + ChildrenTable.COLUMN_USERNAME + " TEXT,"
//            + ChildrenTable.COLUMN_CLUSTER + " TEXT,"
            + ChildrenTable.COLUMN_SYSDATE + " TEXT,"
//            + ChildrenTable.COLUMN_TAB_NO + " TEXT,"
//            + ChildrenTable.COLUMN_GEOAREA + " TEXT,"
//            + ChildrenTable.COLUMN_START_TIME + " TEXT,"
//            + ChildrenTable.COLUMN_END_TIME + " TEXT,"
//            + ChildrenTable.COLUMN_ISTATUS + " TEXT,"
            + ChildrenTable.COLUMN_DEVICEID + " TEXT,"
//            + ChildrenTable.COLUMN_DEVICETAGID + " TEXT,"
//            + ChildrenTable.COLUMN_GPSLAT + " TEXT,"
//            + ChildrenTable.COLUMN_GPSLNG + " TEXT,"
//            + ChildrenTable.COLUMN_GPSDATE + " TEXT,"
//            + ChildrenTable.COLUMN_GPSACC + " TEXT,"
            + ChildrenTable.COLUMN_SYNCED + " TEXT,"
            + ChildrenTable.COLUMN_SYNCED_DATE + " TEXT,"
            + ChildrenTable.COLUMN_APPVERSION + " TEXT,"
            + ChildrenTable.COLUMN_SV + " TEXT"
            + " );"
            )

    const val SQL_ALTER_LISTING_GPS_LAT =
        ("ALTER TABLE " + ListingsTable.TABLE_NAME + " ADD " + ListingsTable.COLUMN_GPSLAT + " TEXT; ")
    const val SQL_ALTER_LISTING_GPS_LNG =
        ("ALTER TABLE " + ListingsTable.TABLE_NAME + " ADD " + ListingsTable.COLUMN_GPSLNG + " TEXT; ")
    const val SQL_ALTER_LISTING_GPS_DATE =
        ("ALTER TABLE " + ListingsTable.TABLE_NAME + " ADD " + ListingsTable.COLUMN_GPSDATE + " TEXT; ")
    const val SQL_ALTER_LISTING_GPS_ACC =
        ("ALTER TABLE " + ListingsTable.TABLE_NAME + " ADD " + ListingsTable.COLUMN_GPSACC + " TEXT; ")
    const val SQL_ALTER_LISTING_AREA_CODE =
        ("ALTER TABLE " + ListingsTable.TABLE_NAME + " ADD " + ListingsTable.COLUMN_AREA_CODE + " TEXT; ")
}