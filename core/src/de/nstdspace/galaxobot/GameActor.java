package de.nstdspace.galaxobot;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameActor extends Image {

    private TextureResource resource;

    public GameActor(TextureResource resource){
        super(resource.getRegion());
        this.resource = resource;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO: avoid null textures?
        if(resource.getRegion() == null){
            return;
        }
        super.draw(batch, parentAlpha);
    }
}
