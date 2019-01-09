package domains.puzzle;

import framework.problem.State;
import java.util.Arrays;

/**
 * This class represents states of various tile-moving puzzle problems,
 * including the 8-Puzzle, which involves a 3x3 display area.
 * It can also represent other displays of different dimensions, e.g. 4x4.
 * A puzzle state is represented with a 2D array of integers.
 * @author tcolburn
 */
public class PuzzleState implements State {
    
    /**                                                                                                 
       A 2D integer array represents the tiles.                                                                   
     */
    private final int[][] tiles;

    /**
     * Getter for the underlying 2D array.
     * Most users of this class will not access these representation details.
     * This will be useful when the problem solving framework adds heuristic
     * search to its abilities.
     * @return the underlying 2D array
     */
    public int[][] getTiles() {
        return tiles;
    }
    
    /**
     * A puzzle state constructor that accepts a 2D array of integers.
     * @param tiles a 2d array of integers
     */
    public PuzzleState(int[][] tiles) {
        this.tiles = tiles;
    }
    
    /**
     * A nested class that represents a location within a puzzle state
     * as a row and column pair.
     */
    public static class Location {
        
        /**
         * Creates a new location object.
         * @param row the row index of this location, zero origin
         * @param column the column index of this location, zero origin
         */
        public Location(int row, int column) {
            this.row = row;
            this.column = column;
        }

        /**
         * Getter for this location's row index.
         * @return this location's row index
         */
        public int getRow() {
            return row;
        }

        /**
         * Getter for this location's column index.
         * @return this location's column index
         */
        public int getColumn() {
            return column;
        }
        
        /**
         * Creates a string representation of this location object for
         * debugging purposes.
         * @return a string representation of this location object as "(row,column)"
         */
        @Override
        public String toString() {
            return "(" +row+ "," +column+ ")";
        }
        
        /**
         * Tests for equality of this location with another.
         * @param o the other location
         * @return true if this location has same row and column indexes as
         * the other, false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o.getClass() != this.getClass()) return false;
            Location other = (Location) o;
            return this.row == other.row && this.column == other.column;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + this.row;
            hash = 53 * hash + this.column;
            return hash;
        }
        
        private int row;
        private int column;
    }
    
    /**
     * A puzzle state constructor that creates a new puzzle state from a
     * given puzzle state and two locations.
     * A copy of the given state is created and the contents of the two
     * given locations are swapped.
     * The original puzzle state is not changed.
     * @param state an existing puzzle state
     * @param loc1 a location within the given state
     * @param loc2 another location within the given state
     * @throws ArrayIndexOutOfBoundsException if either location is invalid
     * for this state
     */
    public PuzzleState (PuzzleState state, Location loc1, Location loc2) {
        tiles = copyTiles(state.tiles);
        int temp = tiles[loc1.row][loc1.column];
        tiles[loc1.row][loc1.column] = tiles[loc2.row][loc2.column];
        tiles[loc2.row][loc2.column] = temp;
    }
    
    /**
     * Finds the location of a given tile in this state.
     * @param tile the tile to find
     * @return the location of the tile
     * @throws RuntimeException if the tile is not found in the state
     */
    public Location getLocation(int tile) {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (tiles[r][c] == tile) {
                    return new Location(r,c);
                }
            }
        }
        throw new RuntimeException("Tile " +tile+ " not found in\n" +toString());
    }
    
    /**
     * Tests for equality of this puzzle state with another.
     * @param o the other state
     * @return true if the underlying arrays are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != PuzzleState.class) return false;
        PuzzleState other = (PuzzleState) o;
        if (this == other) return true;
        return Arrays.deepEquals(this.tiles, other.tiles);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Arrays.deepHashCode(this.tiles);
        return hash;
    }
    
    /**
     * Creates a string representation of this state, with the tiles laid out
     * in a table with dashes and vertical bars used to separate rows and columns.
     * @return the string representation of this state
     */
    @Override
    public String toString() {
        int width = tiles[0].length;
        StringBuilder builder = new StringBuilder();
        for (int[] row : tiles) {
            builder.append(horizontalDivider(width));
            builder.append("\n");
            builder.append(horizontalRow(row));
            builder.append("\n");
        }
        builder.append(horizontalDivider(width));
        return builder.toString();
    }
    
    /*
    Private helper methods follow.
    */
    
    private static String horizontalDivider(int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            builder.append("+---");
        }
        builder.append("+");
        return builder.toString();
    }
    
    private static String horizontalRow(int[] tiles) {
        StringBuilder builder = new StringBuilder();
        for (int tile : tiles) {
            builder.append("|");
            builder.append(tileString(tile));
        }
        builder.append("|");
        return builder.toString();
    }
    
    private static String tileString(int tile) {
        if ( tile == 0 ) return "   ";
        if ( tile/10 == 0 ) return " " + tile + " ";
        return tile + " ";
    }
    
    private static int[][] copyTiles(int[][] source) {
        int rows = source.length;
        int columns = source[0].length;
        int[][] dest = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                dest[r][c] = source[r][c];
            }
        }
        return dest;
    }
}
