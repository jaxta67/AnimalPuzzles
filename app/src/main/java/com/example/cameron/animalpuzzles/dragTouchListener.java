package com.example.cameron.animalpuzzles;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Cameron on 5/25/2015.
 */
class dropTouchListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Handles touch dropping
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Drag code here
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);


            return true;

        } else {
            return false;
        }

    }
}
