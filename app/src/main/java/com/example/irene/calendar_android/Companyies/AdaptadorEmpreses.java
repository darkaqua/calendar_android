package com.example.irene.calendar_android.Companyies;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.example.irene.calendar_android.R;

public class AdaptadorEmpreses  extends SimpleCursorAdapter {

    public AdaptadorEmpreses(ListActivity appCompatActivity, int row_empreses, MatrixCursor mc, String[] from, int[] to, int i) {
        super(appCompatActivity, row_empreses, mc, from, to);
    }


}
