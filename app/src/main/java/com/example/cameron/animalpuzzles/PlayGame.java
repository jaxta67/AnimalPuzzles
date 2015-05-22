package com.example.cameron.animalpuzzles;

import android.content.ClipData;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        TableLayout layout = (TableLayout) findViewById(R.id.tableGrid);



        //The grid of squares used as puzzle boxes
        TableLayout table = (TableLayout) findViewById(R.id.tableGrid);
            for (int r=1; r<=rowCount; r++){
            TableRow tr = new TableRow(this);
            for (int c=1; c<=columnCount; c++){

                ImageViewBox im = new ImageViewBox(this, r); // Create a new ImageViewBox Object
                im.setPuzzleId(c);
                im.setImageResource(R.drawable.ic_launcher);
                //im.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
                im.setPadding(0, 0, 0, 0); //padding in each image if needed
                //add here on click event etc for each image...
                im.setOnDragListener(new puzzleDragListener());
                //Width Height of image
                tr.addView(im,imageWidth, imageHeight);
            }
            table.addView(tr);
        }

        //Piece Grid - Puzzle pieces in grid
        TableLayout tbl = (TableLayout) findViewById(R.id.pieceGrid);
             for (int r=1; r<=rowCount; r++){
            TableRow tr = new TableRow(this);
            for (int c=1; c<=columnCount; c++){

                ImageViewBox im = new ImageViewBox(this, r); // Create a new ImageViewBox Object
                if(c == 1)  im.setImageResource(R.drawable.whale1);
                if(c == 2)  im.setImageResource(R.drawable.whale2);
                if(c == 3)  im.setImageResource(R.drawable.whale3);
                if(c == 4)  im.setImageResource(R.drawable.whale4);
                if(c == 5)  im.setImageResource(R.drawable.whale5);
                if(c == 6)  im.setImageResource(R.drawable.whale6);
                if(c == 7)  im.setImageResource(R.drawable.whale7);
                if(c == 8)  im.setImageResource(R.drawable.whale8);
                if(c == 9)  im.setImageResource(R.drawable.whale9);




                im.setPadding(0, 0, 0, 0);

                im.setOnTouchListener(new dropTouchListener());
                //Gives the piece an id
                im.setPuzzleId(c);
                //Width Height of image
                tr.addView(im,imageWidth, imageHeight);
            }
            table.addView(tr);
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
        //Handles the event that occurs when puzzle piece is dragged onto it

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
