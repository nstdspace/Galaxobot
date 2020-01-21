package de.nstdspace.galaxobot;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {

    private GalaxobotMain main;

    private int unitSize = 100;
    private float worldWidth = 10.0f * unitSize;
    private float worldHeight = 10.0f * unitSize;

    private int BLOCK_SIZE = unitSize;

    private Stage gameStage;
    private Viewport gameViewport;
    private GameLevel currentLevel;

    private Player player;

    private Color DEBUG_BACKGROUND_COLOR = Color.PURPLE;

    public GameScreen(GalaxobotMain main){
        this.main = main;
        initViewportAndCamera();
        initPlayer();
        initLevel();
        initGameStage();

        player.setZIndex(gameStage.getActors().size + 1);
        putPlayerOnGridPosition(9, 3, true);
    }

    private void initLevel() {
        currentLevel = new GameLevel(10, 20);
        Block blocks[] = currentLevel.getFlattenedBlocks();
        for (int i = 0; i < blocks.length; i++) {
            fixBlock(blocks[i]);
        }
    }

    /**
     * fixes size and world position of a new created block object
     * @param block
     */
    private void fixBlock(Block block){
        block.setPosition(getWorldPositionX(block), getWorldPositionY(block));
        block.setSize(BLOCK_SIZE, BLOCK_SIZE);
    }

    /**
     * checked move function - tests if move by given amount of rows and cols is valid.
     *
     * if the player is moved through the grid, this method should be called.
     * changes col and row value of player and adapts world position respectively.
     *
     * @param rows amount of rows to move player
     * @param cols amount of cols to move player
     */
    public void movePlayerOnGrid(int rows, int cols){
        int targetRow = player.getRow() + rows;
        int targetCol = player.getCol() + cols;
        boolean movingDownwards = rows <= 0;
        boolean targetRowValid = targetRow >= 0 && targetRow < currentLevel.getLevelRows();
        boolean targetColValid = targetCol >= 0 && targetCol < currentLevel.getLevelCols();
        if(movingDownwards && targetRowValid && targetColValid){
            Block targetBlock = currentLevel.getBlockAt(targetRow, targetCol);
            if (targetBlock.getType() != BlockType.AIR) {
                digBlock(targetBlock);
            }
            putPlayerOnGridPosition(targetRow, targetCol, true);
        }
    }

    /**
     * does not check validity of given grid coordinates, just puts the player there.
     */
    private void putPlayerOnGridPosition(int row, int col, boolean centerCamera){
        player.setRow(row);
        player.setCol(col);
        player.setX(getWorldPositionX(col));
        player.setY(getWorldPositionY(row));
        centerCameraRelativeToPlayer();
    }

    private void digBlock(Block block) {
        block.remove();
        Block newBlock = new Block(BlockType.AIR, block.getRow(), block.getCol());
        fixBlock(newBlock);
        gameStage.addActor(newBlock);
//        Resources.PARTICLE_EFFECT_SPLASH.setPosition(block.getX(), block.getY());
//        Resources.PARTICLE_EFFECT_SPLASH.start();
    }

    private void centerCameraRelativeToPlayer() {
        gameViewport.getCamera().position.x = player.getX();
        gameViewport.getCamera().position.y = player.getY();
        gameViewport.getCamera().update();
    }

    private float getWorldPositionX(Block block){
        return getWorldPositionX(block.getCol());
    }

    private float getWorldPositionY(Block block){
        return getWorldPositionY(block.getRow());
    }

    private float getWorldPositionX(int col) {
        return col * BLOCK_SIZE;
    }

    private float getWorldPositionY(int row){
        return row * BLOCK_SIZE;
    }

    private void initGameStage() {
        gameStage = new Stage(gameViewport);
        Block blocks[] = currentLevel.getFlattenedBlocks();
        for (int i = 0; i < blocks.length; i++) {
            gameStage.addActor(blocks[i]);
        }
        gameStage.addActor(player);
    }

    private void initPlayer() {
        player = new Player();
        player.setSize(unitSize, unitSize);
    }

    private void initViewportAndCamera() {
        OrthographicCamera camera = new OrthographicCamera(worldWidth, worldHeight);
        gameViewport = new FitViewport(worldWidth, worldHeight, camera);
        camera.position.setZero();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.getViewport().update(width, height, false);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.debugRenderer.begin(ShapeRenderer.ShapeType.Filled);
        main.debugRenderer.setColor(DEBUG_BACKGROUND_COLOR);
        main.debugRenderer.rect(0, 0, worldWidth, worldHeight);
        main.debugRenderer.end();
        gameStage.act();
        gameStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameStage.dispose();
    }
}
