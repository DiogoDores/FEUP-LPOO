package com.prairieKing.model.powerups;

import com.prairieKing.controller.bodies.ProjectileBody;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.entities.ProjectileModel;



public class ShotgunPowerup extends GunPowerups {

    private Gun gun;
    private GameLogic gameLogic;
    private float effectTime = 20;

    public ShotgunPowerup(GameLogic gameLogic) {
        super(gameLogic);
        gameLogic.getHero().setGun(this);
        //this.gun = gameLogic.getHero().getGun();
       // setEffectTime(15);
        System.out.println("i tried so hard");
    }

    @Override
    public void shoot(float posX, float posY, float vX, float vY) {
        System.out.println("Entrei");
        super.shoot(posX, posY, -vX, -vY);

        if (vX == 0) { // Para cima ou para baixo

        }
        else if (vY == 0) { // Para a esquerda ou para a direita

        }
        else { // Nas diagonais

        }
    }

 /*   @Override
    public void removeEffect() {

    }*/

}
