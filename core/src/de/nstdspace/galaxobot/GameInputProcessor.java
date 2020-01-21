package de.nstdspace.galaxobot;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

    private GameScreen gameScreen;

    public GameInputProcessor(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.RIGHT){
            gameScreen.movePlayerOnGrid(0, 1);
        } else if(keycode == Input.Keys.LEFT){
            gameScreen.movePlayerOnGrid(0, -1);
        } else if(keycode == Input.Keys.UP){
            gameScreen.movePlayerOnGrid(1, 0);
        } else if(keycode == Input.Keys.DOWN){
            gameScreen.movePlayerOnGrid(-1, 0);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}