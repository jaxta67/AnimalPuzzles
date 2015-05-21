package com.example.cameron.animalpuzzles;

import android.content.ClipData;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;


public class PlayGame extends ActionBarActivity {

    int setX = 0;
    int setY = 0;
    int numY = 2;
    int numX = 2;
    ImageView[][] puzzleGrid = new ImageView[numY][numX];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        TableLayout layout = (TableLayout) findViewById(R.id.tableGrid);





       //Black box
        ImageView boxOne = (ImageView) findViewById(R.id.test2);



        //Box two is where the object will be dropped on
        boxOne.setOnTouchListener(new dropTouchListener());


        for(int y = 0;y<numY;y++){
            for(int x = 0;x<numX;x++){
                puzzleGrid[y][x] = new ImageView(this);
                puzzleGrid[y][x].setImageResource(R.drawable.ic_launcher);
                puzzleGrid[y][x].setMinimumHeight(10);
                puzzleGrid[y][x].setMinimumWidth(10);
                puzzleGrid[y][x].setX(setX);
                puzzleGrid[y][x].setY(setY);
                puzzleGrid[y][x].setOnDragListener(new puzzleDragListener());

                setX += 10;
                layout.addView(puzzleGrid[y][x]);

            }
            setY += 10;
            setX = 0;

        }












    }

    //Listener Classes implemented here

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


     class puzzleDragListener implements View.OnDragListener {
        //Handles dragging

        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag event
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action needed
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action needed
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action needed
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged image
                    View view = (View) event.getLocalState();
                    view.setVisibility(View.INVISIBLE);
                    ImageView dropTarget = (ImageView) v;
                    ImageView dropped = (ImageView) view;

                    dropTarget.setImageResource(R.drawable.fox);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action needed
                    break;
                default:
                    break;
            }

            return true;
        }
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
