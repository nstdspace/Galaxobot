package de.nstdspace.galaxobot;

public class Block extends GameActor {

    public Block(BlockType blockType) {
        super(blockType.getResource());
    }
}
