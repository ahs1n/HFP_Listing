package edu.aku.hassannaqvi.hfplisting.models

import android.database.Cursor
import android.provider.BaseColumns
import org.apache.commons.lang3.StringUtils
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by muhammad.hussain on 22/10/2022
 */
class HealthFacilities {
    var districtCode: String = StringUtils.EMPTY
    var facilityCode: String = StringUtils.EMPTY
    var facilityName: String = StringUtils.EMPTY

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): HealthFacilities {
        districtCode = jsonObject.getString(TableHealthFacilities.COLUMN_DISTRICT_CODE)
        facilityCode = jsonObject.getString(TableHealthFacilities.COLUMN_FACILITY_CODE)
        facilityName = jsonObject.getString(TableHealthFacilities.COLUMN_FACILITY_NAME)
        return this
    }

    fun hydrate(cursor: Cursor): HealthFacilities {
        districtCode =
            cursor.getString(cursor.getColumnIndexOrThrow(TableHealthFacilities.COLUMN_DISTRICT_CODE))
        facilityCode =
            cursor.getString(cursor.getColumnIndexOrThrow(TableHealthFacilities.COLUMN_FACILITY_CODE))
        facilityName =
            cursor.getString(cursor.getColumnIndexOrThrow(TableHealthFacilities.COLUMN_FACILITY_NAME))
        return this
    }

    object TableHealthFacilities : BaseColumns {
        const val TABLE_NAME = "hf_list"
        const val COLUMN_NAME_NULLABLE = "nullColumnHack"
        const val COLUMN_ID = "_ID"
        const val COLUMN_DISTRICT_CODE = "distcode"
        const val COLUMN_FACILITY_NAME = "hf_name"
        const val COLUMN_FACILITY_CODE = "hf_code"
    }
}