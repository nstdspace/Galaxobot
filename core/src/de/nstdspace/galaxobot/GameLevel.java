package de.nstdspace.galaxobot;

public class GameLevel {

    private Block blocks[][];

    /**
     * contains the blocks in a flattened 1-dimensional array.
     * bijection: b[i, j] -> b'[i * COLS + j]
     */
    private Block flattenedBlocks[];

    public GameLevel(int rows, int cols){
        blocks = new Block[rows][cols];
        flattenedBlocks = new Block[rows * cols];
        for (int row = 0; row < blocks.length; row++) {
            Block blockRow[] = blocks[row];
            for (int col = 0; col < blockRow.length; col++) {
                BlockType type = Math.random() > 0.5 ? BlockType.DIRT : BlockType.STONE;
                if(row == rows - 1) type = BlockType.AIR;
                Block block = new Block(type, row, col);
                blockRow[col] = block;
                flattenedBlocks[row * cols + col] = block;
            }
        }
    }

    public int getLevelRows(){
        return blocks.length;
    }

    public int getLevelCols(){
        return blocks[0].length;
    }

    public Block[] getFlattenedBlocks() {
        return flattenedBlocks;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public Block getBlockAt(int row, int col) {
        return blocks[row][col];
    }
}
