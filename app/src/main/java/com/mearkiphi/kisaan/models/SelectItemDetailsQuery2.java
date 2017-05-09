package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 23/01/17.
 */

public class SelectItemDetailsQuery2 {

    @SerializedName("type")
    String type = "select";

    @SerializedName("args")
    Args args;

    public SelectItemDetailsQuery2(String category) {
        args = new Args();
//        args.where = new Where();
//        args.where.category = category;
    }

    class Args {

        @SerializedName("table")
        String table = "sell_page";

        @SerializedName("columns")
        String[] columns = {
                "id","user_id","location", "category", "rate", "sub_category", "image"
        };
        @SerializedName("where")
        Where where;

    }

    class Where {
        @SerializedName("type")
        String category;
    }

}
