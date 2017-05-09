package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaison on 23/01/17.
 */

public class InsertTodoQuery {

    @SerializedName("type")
    String type = "insert";

    @SerializedName("args")
    Args args;

    public InsertTodoQuery(String location, Integer user_id, Integer rate, String category, String subCategory, String image) {
        args = new Args();
        args.objects = new ArrayList<>();
        TodoRecord record = new TodoRecord(location, user_id, rate, category, subCategory, image);
        args.objects.add(record);
    }

    class Args {

        @SerializedName("table")
        String table = "sell_page";

//        @SerializedName("returning")
//        String[] returning = {
//                "id","location","category"
//        };

        @SerializedName("objects")
        List<TodoRecord> objects;

    }

}
