package game.levels;

import collision.Block;
import collision.Velocity;
import game.LevelInformation;
import visuals.levelsBackgrounds.ImageBackground;
import visuals.levelsBackgrounds.SolidColorBackground;

import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * The class is used to define levels using a file.
 */
public class LevelSpecificationReader {
    /**
     * The function received a "Reader" variable and uses it to define a list of levels from a file.
     *
     * @param reader a "Reader" variable used to read the levels' information from a file.
     * @return a list of levels that were defined using a file.
     */
    public List<LevelInformation> levelReader(Reader reader) {
        List<LevelInformation> allLevels = new ArrayList<>();
        BufferedReader lineReader = new BufferedReader(reader);
        try {
            String line = lineReader.readLine();
            while (line != null) {
                if (line.equals("START_LEVEL")) {
                    BlockDefinitionReader blockDefinitionReader =
                            new BlockDefinitionReader(new BlocksFromSymbolsFactory());
                    GenericLevel newLevel = new GenericLevel();
                    List<Block> newLevelBlocks = new ArrayList<>();
                    int blockStartingX = 0;
                    int blockStartingY = 0;
                    int rowHeight = 0;
                    while (!line.equals("END_LEVEL")) {
                        StringBuilder contentBuffer = new StringBuilder();
                        contentBuffer.append(line.trim());
                        String[] parts = contentBuffer.toString().split(":");
                        switch (parts[0]) {
                            case "level_name":
                                newLevel.setName(parts[1]);
                                break;
                            case "paddle_speed":
                                newLevel.setPaddleSpeed(Integer.parseInt(parts[1]));
                                break;
                            case "paddle_width":
                                newLevel.setPaddleWidth(Integer.parseInt(parts[1]));
                                break;
                            case "num_blocks":
                                newLevel.setNumberOfBlocksToRemove(Integer.parseInt(parts[1]));
                                break;
                            case "ball_velocities":
                                StringBuilder tempContentBuffer1 = new StringBuilder();
                                tempContentBuffer1.append(parts[1]);
                                String[] velocities = tempContentBuffer1.toString().split(" ");
                                newLevel.setNumberOfBalls(velocities.length);
                                List<Velocity> velocityList = new ArrayList<>();
                                for (int index = 0; index < velocities.length; index++) {
                                    StringBuilder tempContentBuffer2 = new StringBuilder();
                                    tempContentBuffer2.append(velocities[index]);
                                    String[] velocitiesPieces = tempContentBuffer2.toString().split(",");
                                    velocityList.add(new Velocity(Double.parseDouble(velocitiesPieces[0]), Double
                                            .parseDouble(velocitiesPieces[1])));
                                }
                                newLevel.setInitialBallVelocities(velocityList);
                                break;
                            case "background":
                                StringBuilder tempContentBuffer3 = new StringBuilder();
                                tempContentBuffer3.append(parts[1]);
                                String[] type = {parts[1].substring(0, 5), parts[1].substring(6, parts[1].length()
                                        - 1)};
                                switch (type[0]) {
                                    case "image":
                                        newLevel.setBackground(new ImageBackground(ImageIO.read(getClass()
                                                .getClassLoader().getResource(type[1]))));
                                        break;
                                    case "color":
                                        Color color;
                                        if (type[1].substring(0, 3).equals("RGB")) {
                                            StringBuilder tempContentBuffer4 = new StringBuilder();
                                            String colorValues = type[1].substring(4, type[1].length() - 1);
                                            tempContentBuffer4.append(colorValues);
                                            String[] colorValue = tempContentBuffer4.toString().split(",");
                                            if (colorValue.length > 3) {
                                                return null;
                                            }
                                            color = new Color(Integer.parseInt(colorValue[0]),
                                                    Integer.parseInt(colorValue[1]), Integer.parseInt(colorValue[2]));
                                        } else {
                                            try {
                                                Field field = Class.forName("java.awt.Color").getField(type[1]);
                                                color = (Color) field.get(null);
                                            } catch (Exception e) {
                                                return null;
                                            }
                                        }
                                        newLevel.setBackground(new SolidColorBackground(color));
                                        break;
                                    default:
                                        System.out.println("Meaningless expression: " + type[0]);
                                }
                                break;
                            case "block_definitions":
                                blockDefinitionReader.fromReader(parts[1]);
                                break;
                            case "blocks_start_x":
                                blockStartingX = Integer.parseInt(parts[1]);
                                break;
                            case "blocks_start_y":
                                blockStartingY = Integer.parseInt(parts[1]);
                                break;
                            case "row_height":
                                rowHeight = Integer.parseInt(parts[1]);
                                break;
                            case "START_BLOCKS":
                                int rowNum = 0;
                                line = lineReader.readLine();
                                while (!line.equals("END_BLOCKS")) {
                                    String rowInfo = line;
                                    int rowBlockX = 0;
                                    for (int index = 0; index < rowInfo.length(); index++) {
                                        String symbol = rowInfo.substring(index, index + 1);
                                        if (blockDefinitionReader.getBlocksSymbolsFactory().isSpaceSymbol(symbol)) {
                                            rowBlockX += blockDefinitionReader.getBlocksSymbolsFactory().
                                                    getSpaceWidth(symbol);
                                        } else if (blockDefinitionReader.getBlocksSymbolsFactory()
                                                .isBlockSymbol(symbol)) {
                                            Block newBlock = new Block((Block) blockDefinitionReader
                                                    .getBlocksSymbolsFactory().getBlockCreator(symbol));
                                            newBlock.create(blockStartingX + rowBlockX, blockStartingY
                                                    + rowHeight * rowNum);
                                            newLevelBlocks.add(newBlock);
                                            rowBlockX += ((Block) blockDefinitionReader
                                                    .getBlocksSymbolsFactory().getBlockCreator(symbol)).getWidth();
                                        }
                                    }
                                    rowNum++;
                                     line = lineReader.readLine();
                                }
                                newLevel.setBlocks(newLevelBlocks);
                                break;
                            default:
                                System.out.println("Meaningless expression: " + parts[0]);
                        }
                        line = lineReader.readLine();
                        if (line.equals("END_LEVEL")) {
                            allLevels.add(newLevel);
                        }
                    }
                }
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        return allLevels;
    }
}
