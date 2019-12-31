package com.example.muhammadfakhar.pro;

import android.view.View;
import android.widget.AdapterView;

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
