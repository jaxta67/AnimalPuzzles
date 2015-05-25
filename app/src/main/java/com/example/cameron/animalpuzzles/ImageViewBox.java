package com.example.cameron.animalpuzzles;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Cameron on 5/22/2015.
 */
public class ImageViewBox extends ImageView {

    int id;
    int drawable;
    boolean complete = false;

    public ImageViewBox(Context context, int id) {
        super(context);
        setPuzzleId(id);

    }

    public ImageViewBox(Context context, int id, int drawable) {
        super(context);
        setPuzzleId(id);
        setDrawableId(drawable);
    }

    public void setDrawableId(int drawableId){
        this.drawable = drawableId;
    }

    public int getDrawableId(){
        return this.drawable;
    }

    public void setPuzzleId(int id){

        this.id = id;
    }

    public int getPuzzleId(){
        return id;
    }
    public boolean equalId(ImageViewBox compare){
        if(compare.getPuzzleId() == this.id){
            return true;
        }
        return false;


    }
    public boolean checkPuzzlePieceAgainstBoard(int pieceId){
        return this.id == pieceId;
    }

    public boolean isComplete(){
        return complete;
    }

    public void setComplete(){
        complete = true;
    }

}