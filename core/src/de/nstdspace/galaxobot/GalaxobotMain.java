package de.nstdspace.galaxobot;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class GalaxobotMain extends Game {

	public ShapeRenderer debugRenderer;
	private GameScreen gameScreen;

	private static Random random;
	private static final long seed = 123456789;

	@Override
	public void create () {
		Resources.load();
		random = new Random(seed);
		debugRenderer = new ShapeRenderer();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		Gdx.input.setInputProcessor(new GameInputProcessor(gameScreen));
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
