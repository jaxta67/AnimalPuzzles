package com.example.cameron.animalpuzzles;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;


public class PlayGame extends ActionBarActivity {


    int rowCount = 3;
    int columnCount = 3;
    int imageWidth = 44;
    int imageHeight = 44;
    int pieces = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        populatePuzzlePieceGrid();
        populateDropBoxes();


    }
    public void populateDropBoxes(){
        //The grid of squares used as puzzle boxes
        TableLayout table = (TableLayout) findViewById(R.id.tableGrid);
        int x = 1;
        for (int r=1; r<=rowCount; r++){
            TableRow tr = new TableRow(this);
            for (int c=1; c<=columnCount; c++){

                ImageViewBox im = new ImageViewBox(this, r); // Create a new ImageViewBox Object
                im.setPuzzleId(x);
                im.setImageResource(R.drawable.gridbox);

                im.setPadding(0, 0, 0, 0); //padding in each image if needed
                //add here on click event etc for each image...
                im.setOnDragListener(new puzzleDragListener());
                //Width Height of image
                tr.addView(im,imageWidth ,imageHeight);
                x++;
            }
            table.addView(tr);
        }



    }



    public void populatePuzzlePieceGrid(){
        int x = 1; // counter
        TableLayout tbl = (TableLayout) findViewById(R.id.pieceGrid);
        for (int r=1; r<=rowCount; r++){
            TableRow tr = new TableRow(this);

            for (int c=1; c<=columnCount; c++){


                int someId = PlayGame.this.getResources().getIdentifier("whale"+x,"drawable",PlayGame.this.getPackageName());
                ImageViewBox im = new ImageViewBox(this, someId,someId); // Create a new ImageViewBox Object
                im.setImageResource(someId);

                im.setPadding(0, 0, 0, 0);
                im.setOnTouchListener(new dropTouchListener());
                im.setPuzzleId(x);

                //Width Height of image
                tr.addView(im,imageHeight, imageWidth);
                x++;


            }
            tbl.addView(tr);

        }
    }
    public boolean checkCompleteStatus() {

        boolean outcome = false;
        int counter = 0;
        TableLayout table = (TableLayout) findViewById(R.id.tableGrid);
        int numImageBox = table.getChildCount();
        for (int i = 0; i < numImageBox; i++) {
            TableRow tr = (TableRow) table.getChildAt(i);
            for (int x = 0; x < tr.getChildCount(); x++) {
                ImageViewBox img = (ImageViewBox) tr.getChildAt(x);
                if (img.isComplete()) {
                    outcome = true;
                } else {
                    return false;
                }

            }

        }


        //if everything isComplete() then go to new activity
        Intent i = new Intent(PlayGame.this, MainActivity.class);
        startActivity(i);
        return true;
    }
    //Listener Classes implemented here

    /*class dropTouchListener implements View.OnTouchListener {

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
    } */



    class puzzleDragListener implements View.OnDragListener {
        //Handles the event that occurs when puzzle piece is dragged onto it

        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag event
            if (event.getAction() == DragEvent.ACTION_DROP) {


                //handle the dragged image
                View view = (View) event.getLocalState();

                ImageViewBox dropTarget = (ImageViewBox) v;
                ImageViewBox dropped = (ImageViewBox) view;
                if(dropped.equalId(dropTarget)) {
                    dropTarget.setImageResource(dropped.getDrawableId());
                    view.setVisibility(View.INVISIBLE);
                    dropTarget.setComplete();
                    checkCompleteStatus();
                    
                }

            }

            return true;
        }
    }

















    
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
