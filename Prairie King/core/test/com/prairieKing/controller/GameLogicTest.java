package com.prairieKing.controller;

import com.prairieKing.PrairieKing;
import com.prairieKing.controller.entities.EntityController;
import com.prairieKing.model.GameLogic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest extends GameTest{

    @Test
    public void killHero() {
        PrairieKing prairieKing = new PrairieKing();
        assertEquals(0, prairieKing.currentState);
        GameLogic game = new GameLogic(prairieKing, true);

        EntityController entity = game.getHero();
        entity.kill();

        assertEquals(2, game.getHero().getLives());
    }


}