package com.prairieKing.controller;

import com.prairieKing.PrairieKing;
import com.prairieKing.controller.entities.EntityController;
import com.prairieKing.model.GameLogic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest extends GameTest{

    @Test
    public void testGameLost() {
        PrairieKing prairieKing = new PrairieKing();
//        GameLogic game = new GameLogic(prairieKing);

        EntityController entity = new EntityController(50, 50);
        entity.kill();
/*
        game.checkLose(entity);

        assertEquals(2, prairieKing.currentState);*/
    }


}