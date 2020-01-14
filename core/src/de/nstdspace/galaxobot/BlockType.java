package de.nstdspace.galaxobot;

public enum BlockType {

    AIR(Resources.TEXTURE_RESOURCE_EMPTY),
    DIRT(Resources.TEXTURE_RESOURCE_BLOCK_DIRT),
    STONE(Resources.TEXTURE_RESOURCE_BLOCK_STONE);

    private TextureResource resource;

    BlockType(TextureResource resource){
        this.resource = resource;
    }

    public TextureResource getResource() {
        return resource;
    }
}
