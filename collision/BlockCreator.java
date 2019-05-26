package collision;

/**
 * An interface to create block at specific coordinates.
 */
public interface BlockCreator {
    /**
     * The function create a block at specific coordinates.
     *
     * @param xpos the x coordinate.
     * @param ypos the y coordinate.
     * @return the block thats created.
     */
    Block create(int xpos, int ypos);
}
