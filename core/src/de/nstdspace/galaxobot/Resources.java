package de.nstdspace.galaxobot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Resources {

    private static final String DEFAULT_IMAGE_FORMAT = "png";

    public static TextureResource TEXTURE_RESOURCE_EMPTY;
    public static TextureResource TEXTURE_RESOURCE_BLOCK_DIRT;
    public static TextureResource TEXTURE_RESOURCE_BLOCK_STONE;
    public static TextureResource TEXTURE_RESOURCE_ROBOT;

    public static ParticleEffect PARTICLE_EFFECT_SPLASH;

    private static HashMap<String, Texture> textureMap;

    public static void load(){
        textureMap = new HashMap<>();

//        TEXTURE_RESOURCE_EMPTY = new TextureResource(null);
        TEXTURE_RESOURCE_EMPTY = loadDefaultTextureResource("air_debug");

        TEXTURE_RESOURCE_BLOCK_DIRT = loadDefaultTextureResource("block_dirt");
        TEXTURE_RESOURCE_BLOCK_STONE = loadDefaultTextureResource("block_stone");
        TEXTURE_RESOURCE_ROBOT = loadDefaultTextureResource("galaxobot");

        PARTICLE_EFFECT_SPLASH = new ParticleEffect();
        TextureAtlas atlas = new TextureAtlas();
        atlas.addRegion("particle", new Texture("particle.png"), 0, 0, 32, 32);
        PARTICLE_EFFECT_SPLASH.load(Gdx.files.internal("splash_1"), atlas);
    }

    private static TextureResource loadDefaultTextureResource(String textureName){
        Texture texture = retrieveTexture(textureName);
        TextureRegion region = new TextureRegion(texture);
        TextureResource resource = new TextureResource(region);
        return resource;
    }

    /**
     * Either returns a cached texture from the map or loads a new texture from disk.
     *
     * @param textureName The name of the texture without file ending
     * @return retrieved texture
     */
    private static Texture retrieveTexture(String textureName) {
        if(!textureMap.containsKey(textureName)){
            Texture texture = new Texture(textureName + "." + DEFAULT_IMAGE_FORMAT);
            textureMap.put(textureName, texture);
        }
        return textureMap.get(textureName);
    }

    public static void dispose() {

    }
}
