package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 23/01/17.
 */

public class SelectItemDetailsQuery {

    @SerializedName("type")
    String type = "select";

    @SerializedName("args")
    Args args;

    public SelectItemDetailsQuery(String category) {
        args = new Args();
//        args.where = new Where();
//        args.where.category = category;
    }

    class Args {

        @SerializedName("table")
        String table = "sell_page";

        @SerializedName("columns")
        String[] columns = {
                "id","user_id","location", "category", "rate", "sub_category"
        };
        @SerializedName("where")
        Where where;

    }

    class Where {
        @SerializedName("type")
        String category;
    }

}
