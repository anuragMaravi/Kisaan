package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 23/01/17.
 */

public class SelectSubCategoryQuery {

    @SerializedName("type")
    String type = "select";

    @SerializedName("args")
    Args args;

    public SelectSubCategoryQuery(String category) {
        args = new Args();
        args.where = new Where();
        args.where.category = category;
    }

    class Args {

        @SerializedName("table")
        String table = "sub_category";

        @SerializedName("columns")
        String[] columns = {
                "id","type","item_name", "image"
        };
        @SerializedName("where")
        Where where;

    }

    class Where {
        @SerializedName("type")
        String category;
    }

}
