package com.prairieKing;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.prairieKing.view.*;
import com.prairieKing.model.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.prairieKing.model.GameLogic;
import com.prairieKing.view.GameStage;
import com.prairieKing.view.LoseScreen;
import com.prairieKing.view.Menu;

public class PrairieKing extends Game {
    public static int currentState;
    private Music music;
    private Sound sound;
    Menu menu;
    LoseScreen loseScreen;
    int maxHighScore;
    GameLogic gameLogic;

    public static final float PPM = 100;

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();

        currentState = 0;
        menu = new Menu(this);
        setScreen(menu);
        loseScreen = new LoseScreen("LoseScreen", this);
        gameLogic = new GameLogic(this);
        maxHighScore = 0;
     }

    public void loadAssets() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/mainTheme.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/start.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        assetManager.load("Menus/LoseScreen.png", Texture.class);
        assetManager.load("Sprites/MainSpriteSheet.png", Texture.class);
        assetManager.load("Sprites/BlockBackground.png", Texture.class);
        assetManager.load("Mapas/Map.png", Texture.class);
        assetManager.load("Menus/Menu1.png", Texture.class);
        assetManager.finishLoading();
        assetManager.update();
    }

    // Utilizada para verficar sempre qual é o render que deve estar ativo.

    @Override
    public void render() {
        if (currentState == 0) { // Main Menu
            setScreen(menu);
            menu.render(0);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                sound.play();
                currentState = 1;
            }
        }
        else if (currentState == 1) { // Game Mode
            music.play();
            setScreen(gameLogic.getGameStage());
            gameLogic.act();
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 2;
            }
        }
        else {  // Lose Screen
            setScreen(loseScreen);
            music.pause();

            loseScreen.render(0);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 0;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
        }

    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getMaxHighScore() {
        return maxHighScore;
    }

    public void setMaxHighScore(int maxHighScore) {
        this.maxHighScore = maxHighScore;
        loseScreen.updateHighScore(maxHighScore);
    }

    public void dispose(){
        music.dispose();
        sound.dispose();
    }
}
