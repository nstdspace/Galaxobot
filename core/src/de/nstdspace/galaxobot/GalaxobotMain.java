package de.nstdspace.galaxobot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GalaxobotMain extends Game {

	public ShapeRenderer debugRenderer;
	private GameScreen gameScreen;

	@Override
	public void create () {
		Resources.load();
		debugRenderer = new ShapeRenderer();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.0f, 0.6f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
		debugRenderer.dispose();
		Resources.dispose();
	}
}
