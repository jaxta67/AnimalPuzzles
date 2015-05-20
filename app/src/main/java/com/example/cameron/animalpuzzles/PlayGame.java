package com.example.cameron.animalpuzzles;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;



public class PlayGame extends ActionBarActivity {
    ImageView testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //ImageView
        ImageView testImage = (ImageView) findViewById(R.id.imageView);
        testImage.setBackgroundColor(Color.rgb(100, 100, 50));


        testImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //DragShadow dragShadow = new DragShadow(v);
                ClipData clip = ClipData.newPlainText("", "");
                v.startDrag(clip,  new View.DragShadowBuilder(v), v, 0);
                return true;
            }
        });

        ImageView testImage2 = (ImageView) findViewById(R.id.imageView2);

        testImage2.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {

                int dragEvent = event.getAction();

                switch(dragEvent){
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.i("Drag Event", "Enetered Piece Area");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED  :
                        Log.i("Drag Event", "Left Piece Area");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.i("Drag Event", "Piece Dropped");
                        ImageView target = (ImageView) v;
                        //returns the dragged information passed into dragged object
                        ImageView dragged = (ImageView) event.getLocalState();
                        target.setBackgroundColor(Color.BLUE);
                        int image = R.drawable.ic_launcher;
                        target.setImageResource(image);
                        break;
                }

                return true;
            }
        });
    }
    /*

    private class DragShadow extends View.DragShadowBuilder{

        //ColorDrawable greybox;


        public DragShadow(View view) {
            super(view);
           // greybox = new ColorDrawable(Color.LTGRAY);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            //greybox.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
         //  View v = getView();
          //  greybox.setBounds(0,0,48,48);
           // shadowSize.set(48,48);
            shadowTouchPoint.set(48/2,48/2);

        }


    }
    */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
