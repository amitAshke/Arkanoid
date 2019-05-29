package game.levels;

import collision.Block;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

/**
 * The class is used to define blocks and spaces in a level using a file.
 */
public class BlockDefinitionReader {
    private BlocksFromSymbolsFactory blocksSymbolsFactory;

    /**
     * A constructor that receives a "BlocksFromSymbolsFactory" variable to be set as a member.
     *
     * @param factory a new "BlockFromSymbolsFactory" variable for the "BlockDefinitionReader" variable.
     */
    public BlockDefinitionReader(BlocksFromSymbolsFactory factory) {
        this.blocksSymbolsFactory = factory;
    }
    /**
     * The function return the "BlocksFromSymbolsFactory" variable.
     *
     * @return the "BlocksFromSymbolsFactory" variable.
     */
    public BlocksFromSymbolsFactory getBlocksSymbolsFactory() {
        return blocksSymbolsFactory;
    }
    /**
     * The function receives a string that represents a color which is represented as either 3 numbers or a color
     * name, and it return a "Color" value according to the string.
     *
     * @param s a string that represents a color which is represented as either 3 numbers or a color name
     * @return a "Color" value according to the string.
     */
    public Color figureOutTheColor(String s) {
        Color color;
        if (s.substring(6, 9).equals("RGB")) {
            StringBuilder tempContentBuffer4 = new StringBuilder();
            String colorValues = s.substring(10, s.length() - 2);
            tempContentBuffer4.append(colorValues);
            String[] colorValue = tempContentBuffer4.toString().split(",");
            if (colorValue.length > 3) {
                return null;
            }
            color = new Color(Integer.parseInt(colorValue[0]), Integer.parseInt(colorValue[1]),
                    Integer.parseInt(colorValue[2]));
        } else {
            s = s.substring(6, s.length() - 1);
            try {
                Field field = Class.forName("java.awt.Color").getField(s);
                color = (Color) field.get(null);
            } catch (Exception e) {
                return null;
            }
        }
        return color;
    }

    /**
     * The function sets the default values it got in the file and insert these values in a block it's received.
     *
     * @param defaults a map of the different values to be set to the block.
     * @param defaultFillings a map of the filling to be set to the block.
     * @param newBlock a block in which the values will be set.
     */
    public void setBlockDefaultValues(Map<String, Object> defaults, List<Object> defaultFillings,
                                             Block newBlock) {
        if (defaults.containsKey("width")) {
            newBlock.setWidth((int) defaults.get("width"));
        }
        if (defaults.containsKey("height")) {
            newBlock.setHeight((int) defaults.get("height"));
        }
        if (defaults.containsKey("hitPoints")) {
            newBlock.setHitPoints((int) defaults.get("hitPoints"));
        }
        if (defaults.containsKey("border")) {
            newBlock.setBorder((Color) defaults.get("border"));
        }
        newBlock.setFillings(defaultFillings);
    }

    /**
     * The function fills the maps it receives with the block's default values as read from the file.
     *
     * @param properties the "String" variable containing the blocks' default values.
     * @param defaults a "Map" variable to contain the blocks' values the are not fillings.
     * @param defaultFillingsMap a "Map" variable to contain the blocks' default fillings.
     * @throws IOException in case an error occurred while reading the file.
     */
    public void inCaseOfDefault(String[] properties, Map<String, Object> defaults, Map<Integer, Object>
            defaultFillingsMap) throws IOException {
        for (int index = 1; index < properties.length; index++) {
            StringBuilder tempContentBuffer2 = new StringBuilder();
            tempContentBuffer2.append(properties[index].trim());
            String[] defaultProperties = tempContentBuffer2.toString().split(":");
            switch (defaultProperties[0]) {
                case "width":
                    defaults.put("width", Integer.parseInt(defaultProperties[1]));
                    break;
                case "height":
                    defaults.put("height", Integer.parseInt(defaultProperties[1]));
                    break;
                case "hit_points":
                    defaults.put("hitPoints", Integer.parseInt(defaultProperties[1]));
                    break;
                case "stroke":
                    Color color = figureOutTheColor(defaultProperties[1]);
                    defaults.put("border", color);
                    break;
                default:
                    System.out.println("Meaningless expression: " + defaultProperties[0]);
            }
            if (defaultProperties[0].substring(0, 4).equals("fill")) {
                StringBuilder tempContentBuffer3 = new StringBuilder();
                tempContentBuffer3.append(defaultProperties[1]);
                String[] fillingType = {defaultProperties[1].substring(0, 5), defaultProperties[1]
                        .substring(6, defaultProperties[1].length() - 1)};
                switch (fillingType[0]) {
                    case "image":
                        if (defaultProperties[0].length() != 4) {
                            defaultFillingsMap.put(Integer.parseInt(defaultProperties[0].substring(5,
                                    6)) - 1, ImageIO.read(getClass().getClassLoader()
                                    .getResource(fillingType[1])));
                        } else {
                            defaultFillingsMap.put(0, ImageIO.read(getClass().getClassLoader()
                                    .getResource(fillingType[1])));
                        }
                        break;
                    case "color":
                        Color color = figureOutTheColor(fillingType[1]);
                        if (defaultProperties[0].length() != 4) {
                            defaultFillingsMap.put(Integer.parseInt(defaultProperties[0].substring(5, 6)
                            ) - 1, color);
                        } else {
                            defaultFillingsMap.put(0, color);
                        }
                        break;
                    default:
                        System.out.println("Meaningless expression: " + fillingType[0]);
                }
            }
        }
    }

    /**
     * The function receives a pathway to a file and use it to define block and space. Finally it sets them in the
     * "BlocksFromSymbolsFactory" variable.
     *
     * @param s a pathway to a file in a form of string.
     * @return the "BlocksFromSymbolsFactory" member after blocks and spaces were defined and placed in it.
     */
    public BlocksFromSymbolsFactory fromReader(String s) {
        try {
            BufferedReader blockReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(s.substring(0, s.length()))));
            String blockReaderLine = blockReader.readLine();
            Map<String, Object> defaults = new TreeMap<>();
            List<Object> defaultFillings = new ArrayList<>();
            Map<Integer, Object> defaultFillingsMap = new TreeMap<>();
            while (blockReaderLine != null) {
                StringBuilder tempContentBuffer1 = new StringBuilder();
                tempContentBuffer1.append(blockReaderLine.trim());
                String[] properties = tempContentBuffer1.toString().split(" ");
                switch (properties[0]) {
                    case "default":
                        inCaseOfDefault(properties, defaults, defaultFillingsMap);
                        break;
                    case "bdef":
                        String newBlockSymbol = null;
                        List<Object> uniqueFillings = new ArrayList<>();
                        Map<Integer, Object> uniqueFillingsMap = new TreeMap<>();
                        Block newBlock = new Block();
                        setBlockDefaultValues(defaults, defaultFillings, newBlock);
                        for (int index = 1; index < properties.length; index++) {
                            StringBuilder tempContentBuffer4 = new StringBuilder();
                            tempContentBuffer4.append(properties[index].trim());
                            String[] uniqueProperties = tempContentBuffer4.toString().split(":");
                            switch (uniqueProperties[0]) {
                                case "symbol":
                                    if (!blocksSymbolsFactory.isBlockSymbol(uniqueProperties[1])) {
                                        newBlockSymbol = uniqueProperties[1];
                                    }
                                    break;
                                case "width":
                                    newBlock.setWidth(Integer.parseInt(uniqueProperties[1]));
                                    break;
                                case "height":
                                    newBlock.setHeight(Integer.parseInt(uniqueProperties[1]));
                                    break;
                                case "hit_points":
                                    newBlock.setHitPoints(Integer.parseInt(uniqueProperties[1]));
                                    break;
                                case "stroke":
                                    Color color = figureOutTheColor(properties[1]);
                                    newBlock.setBorder(color);
                                    break;
                                default:
                                    System.out.println("Meaningless expression: " + uniqueProperties[0]);
                            }
                            if (uniqueProperties[0].substring(0, 4).equals("fill")) {
                                StringBuilder tempContentBuffer5 = new StringBuilder();
                                tempContentBuffer5.append(uniqueProperties[1]);
                                switch (uniqueProperties[1].substring(0, 5)) {
                                    case "image":
                                        if (uniqueProperties[0].length() != 4) {
                                            uniqueFillingsMap.put(Integer.parseInt(uniqueProperties[0].substring(5,
                                                    6)) - 1, ImageIO.read(getClass().getClassLoader()
                                                    .getResource(uniqueProperties[1].substring(6, uniqueProperties[1]
                                                            .length() - 1))));
                                        } else {
                                            uniqueFillingsMap.put(0, ImageIO.read(getClass().getClassLoader()
                                                    .getResource(uniqueProperties[1].substring(6, uniqueProperties[1]
                                                            .length() - 1))));
                                        }
                                        break;
                                    case "color":
                                        Color color = figureOutTheColor(uniqueProperties[1]);
                                        if (uniqueProperties[0].length() != 4) {
                                            uniqueFillingsMap.put(Integer.parseInt(uniqueProperties[0].substring(5,
                                                    6)) - 1, color);
                                        } else {
                                            uniqueFillingsMap.put(0, color);
                                        }
                                        break;
                                    default:
                                        System.out.println("Meaningless expression: "
                                                + uniqueProperties[1].substring(0, 5));
                                }
                            }
                        }
                        for (int index = 0; index < uniqueFillingsMap.size(); index++) {
                            uniqueFillings.add(uniqueFillingsMap.get(index));
                        }
                        newBlock.setFillings(uniqueFillings);
                        if (newBlockSymbol != null) {
                            blocksSymbolsFactory.addBlockSymbol(newBlockSymbol, newBlock);
                        }
                        break;
                    case "sdef":
                        String newSpaceSymbol = null;
                        int newSpaceWidth = -1;
                        for (int index = 1; index < properties.length; index++) {
                            StringBuilder tempContentBuffer5 = new StringBuilder();
                            tempContentBuffer5.append(properties[index].trim());
                            String[] spaceProperties = tempContentBuffer5.toString().split(":");
                            switch (spaceProperties[0]) {
                                case "symbol":
                                    if (!blocksSymbolsFactory.isSpaceSymbol(spaceProperties[1])) {
                                        newSpaceSymbol = spaceProperties[1];
                                    }
                                    break;
                                case "width":
                                    newSpaceWidth = Integer.parseInt(spaceProperties[1]);
                                    break;
                                default:
                                    System.out.println("Meaningless expression: " + spaceProperties[0]);
                            }
                        }
                        if (newSpaceSymbol != null) {
                            blocksSymbolsFactory.addSpaceSymbol(newSpaceSymbol, newSpaceWidth);
                        }
                        break;
                    default:
                        System.out.println("Meaningless expression: " + properties[0]);

                }
                blockReaderLine = blockReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!... again");
        }
         return blocksSymbolsFactory;
    }
}