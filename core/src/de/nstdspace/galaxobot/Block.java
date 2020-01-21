package de.nstdspace.galaxobot;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Block extends GameActor {

    private int row;
    private int col;
    private BlockType type;

    public Block(BlockType blockType, int row, int col) {
        super(blockType.getResource());
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public BlockType getType() {
        return type;
    }
}
