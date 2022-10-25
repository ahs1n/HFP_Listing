package edu.aku.hassannaqvi.hfplisting.models

import android.database.Cursor
import edu.aku.hassannaqvi.hfplisting.contracts.TableContracts.ClusterTable
import edu.aku.hassannaqvi.hfplisting.core.MainApp._EMPTY_
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by hassan.naqvi on 11/30/2016.
 */
class Cluster {
    var ID: Long = 0
    var geoarea: String = _EMPTY_
    var distId: String = _EMPTY_
    var area_code: String = _EMPTY_
    var ebcode: String = _EMPTY_
    var distName: String = _EMPTY_
    var area: String = _EMPTY_
    var hf_name: String = _EMPTY_
    var hf_code: String = _EMPTY_


    constructor() {
        // Default Constructor
    }

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): Cluster {
        geoarea = jsonObject.getString(ClusterTable.COLUMN_GEOAREA)
        distId = jsonObject.getString(ClusterTable.COLUMN_DIST_ID)
        ebcode = jsonObject.getString(ClusterTable.COLUMN_EB_CODE)
        distName = jsonObject.getString(ClusterTable.COLUMN_DIST_NAME)
        area = jsonObject.getString(ClusterTable.COLUMN_AREA)
        area_code = jsonObject.getString(ClusterTable.COLUMN_AREA_CODE)
        hf_name = jsonObject.getString(ClusterTable.COLUMN_HF_NAME)
        hf_code = jsonObject.getString(ClusterTable.COLUMN_HF_CODE)


        return this
    }

    fun hydrate(cursor: Cursor): Cluster {
        ID = cursor.getLong(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_ID))

        geoarea = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_GEOAREA))
        distId = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_DIST_ID))
        area_code = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_AREA_CODE))
        ebcode = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_EB_CODE))
        distName = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_DIST_NAME))
        area = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_AREA))
        hf_name = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_HF_NAME))
        hf_code = cursor.getString(cursor.getColumnIndexOrThrow(ClusterTable.COLUMN_HF_CODE))


        return this
    }


}