package de.nstdspace.galaxobot;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {

    private GalaxobotMain main;

    private int unitSize = 100;
    private float worldWidth = 10.0f * unitSize;
    private float worldHeight = 10.0f * unitSize;

    private Stage gameStage;
    private Viewport gameViewport;

    private ArrayList<GameActor> gameActors;

    private Color DEBUG_BACKGROUND_COLOR = Color.PURPLE;

    public GameScreen(GalaxobotMain main){
        this.main = main;
        initViewportAndCamera();
        gameActors = new ArrayList<>();
        initPlayer();
        initLevel();
        initGameStage();
    }

    private void initLevel() {
        GameLevel level = new GameLevel();
        Block blocks[] = level.getBlocks();
        for (int i = 0; i < blocks.length; i++) {
            gameActors.add(blocks[i]);
        }
    }

    private void initGameStage() {
        gameStage = new Stage(gameViewport);
        for(GameActor actor : gameActors){
            gameStage.addActor(actor);
        }
    }

    private void initPlayer() {
        Player player = new Player();
        player.setSize(unitSize, unitSize);
        gameActors.add(player);
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
