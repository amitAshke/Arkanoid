package game.levels;

import collision.BlockCreator;

import java.util.Map;
import java.util.TreeMap;

/**
 * The class is used to maintain blocks and spaces once defined by the "BlockDefinitionReader" class.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new TreeMap<>();
    private Map<String, BlockCreator> blockCreators = new TreeMap<>();

    /**
     * The function input a value "i" with a key "s" in the "Map" of defined spaces.
     *
     * @param s the key.
     * @param i the value.
     */
    public void addSpaceSymbol(String s, Integer i) {
        spacerWidths.put(s, i);
    }

    /**
     * The function input a value "i" with a key "s" in the "Map" of defined blocks.
     *
     * @param s the key.
     * @param b the value.
     */
    public void addBlockSymbol(String s, BlockCreator b) {
        blockCreators.put(s, b);
    }

    /**
     * The function returns true or false depending on whether or not the is a space associated with the "key it
     * receives.
     *
     * @param s the key.
     * @return true if there's a space that is associated with the key, and false otherwise.
     */
    public boolean isSpaceSymbol(String s) {
        return (spacerWidths.get(s) != null);
    }

    /**
     * The function returns true or false depending on whether or not the is a block associated with the "key it
     * receives.
     *
     * @param s the key.
     * @return true if there's a block that is associated with the key, and false otherwise.
     */
    public boolean isBlockSymbol(String s) {
        return (blockCreators.get(s) != null);
    }

    /**
     * The function finds a space the is accessed with the key "s" and return it's width.
     *
     * @param s the key to a space.
     * @return the width of the space associated with the key "s".
     */
    public Integer getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * The function finds a "BlockCreator" variable the is accessed with the key "s" and return it.
     *
     * @param s the key to a space.
     * @return the "BlockCreator" variable associated with the key "s".
     */
    public BlockCreator getBlockCreator(String s) {
        return this.blockCreators.get(s);
    }
}
