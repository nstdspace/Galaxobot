package de.nstdspace.galaxobot;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureResource {

    private TextureRegion region;

    public TextureResource(TextureRegion region){
        this.region = region;
    }

    public TextureRegion getRegion() {
        return region;
    }
}
