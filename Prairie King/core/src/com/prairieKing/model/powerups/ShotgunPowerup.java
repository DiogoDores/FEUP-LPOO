package com.prairieKing.model.powerups;

import com.prairieKing.controller.bodies.ProjectileBody;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.entities.ProjectileModel;



public class ShotgunPowerup extends GunPowerups {

    private Gun gun;


    public ShotgunPowerup(GameLogic gameLogic) {
        super(gameLogic);
        this.gun = gameLogic.getHero().getGun();
        System.out.println("Cheguei ao construtor");
        setEffectTime(15);
        shoot(10,10,0,0);
    }


    @Override
    public void shoot(float posX, float posY, float vX, float vY) {

        System.out.println("Entrei");
        gun.shoot(posX, posY, -vX, -vY);


        if (vX == 0) { // Para cima ou para baixo

        }
        else if (vY == 0) { // Para a esquerda ou para a direita

        }

        else { // Nas diagonais

        }
    }

    @Override
    public void removeEffect() {

    }
}
