package com.prairieKing.controller;

import com.prairieKing.PrairieKing;
import com.prairieKing.controller.entities.EntityController;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Diogo on 05/06/2017.
 */
public class GameLogicTest extends GameTest{

    @Test
    public void testGameLost() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing);

        EntityController controller = new EntityController(50, 50);
        game.checkLose(controller);
        assertEquals(2, prairieKing.currentState);
    }


}