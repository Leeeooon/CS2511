/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.puzzle;

import domains.puzzle.PuzzleState.Location;
import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Ryan Lenz
 */
public class PuzzleMover extends Mover {

    public static final String slideTile1 = "Slide Tile 1";
    public static final String slideTile2 = "Slide Tile 2";
    public static final String slideTile3 = "Slide Tile 3";
    public static final String slideTile4 = "Slide Tile 4";
    public static final String slideTile5 = "Slide Tile 5";
    public static final String slideTile6 = "Slide Tile 6";
    public static final String slideTile7 = "Slide Tile 7";
    public static final String slideTile8 = "Slide Tile 8";

    public PuzzleMover() {
        addMove(slideTile1, s -> slideTile1(s));
        addMove(slideTile2, s -> slideTile2(s));
        addMove(slideTile3, s -> slideTile3(s));
        addMove(slideTile4, s -> slideTile4(s));
        addMove(slideTile5, s -> slideTile5(s));
        addMove(slideTile6, s -> slideTile6(s));
        addMove(slideTile7, s -> slideTile7(s));
        addMove(slideTile8, s -> slideTile8(s));
    }

    private State slideTile1(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(1);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            System.out.println("success");
            return new PuzzleState(ps, l1, l2);
        }

        return null;
    }

    private State slideTile2(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(2);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }
        return null;
    }

    private State slideTile3(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(3);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }

        return null;
    }

    private State slideTile4(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(4);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }
        return null;
    }

    private State slideTile5(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(5);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }
        return null;
    }

    private State slideTile6(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(6);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }
        return null;
    }

    private State slideTile7(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(7);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }
        return null;
    }

    private State slideTile8(State state) {
        PuzzleState ps = (PuzzleState) state;
        Location l1 = ps.getLocation(8);
        Location l2 = ps.getLocation(0);

        if (validMove(l1, l2)) {
            return new PuzzleState(ps, l1, l2);
        }
        return null;
    }

    // Checks whether the move will work, if the tilelcation is adjacent to the blank tile
    private boolean validMove(Location tileLocation, Location blankLocation) {
        if (((tileLocation.getColumn() - 1 == blankLocation.getColumn()
                || tileLocation.getColumn() + 1 == blankLocation.getColumn())
                && (tileLocation.getRow() == blankLocation.getRow()))
                || ((tileLocation.getRow() - 1 == blankLocation.getRow()
                || tileLocation.getRow() + 1 == blankLocation.getRow())
                && tileLocation.getColumn() == blankLocation.getColumn())) {
            return true;
        } else {
            return false;
        }
    }
}