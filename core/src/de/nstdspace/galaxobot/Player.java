package de.nstdspace.galaxobot;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Player extends GameActor {

    private int row;
    private int col;

    public Player() {
        super(Resources.TEXTURE_RESOURCE_ROBOT);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
