package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 23/01/17.
 */

public class SelectCatQuery {

    @SerializedName("type")
    String type = "select";

    @SerializedName("args")
    Args args;

    public SelectCatQuery(Integer userId) {
        args = new Args();
//        args.where = new Where();
//        args.where.userId = userId;
    }

    class Args {

        @SerializedName("table")
        String table = "categories";

        @SerializedName("columns")
        String[] columns = {
                "id","category","image"
        };
        @SerializedName("where")
        Where where;

    }

    class Where {
        @SerializedName("id")
        Integer userId;
    }

}
