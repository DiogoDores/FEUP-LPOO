package com.prairieKing;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.prairieKing.view.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.prairieKing.model.GameLogic;
import com.prairieKing.view.LoseScreen;
import com.prairieKing.view.Menu;

public class PrairieKing extends Game {
    public static int currentState;
    private Music music;
    private Sound sound;
    private Menu menu;
    private LoseScreen loseScreen;
    private WinScreen winScreen;
    private int highScore;
    private GameLogic gameLogic;

    private TmxMapLoader mapLoader;
    private TiledMap map;

    public static final float PPM = 100;
    private float volume = 0.5f;

    private AssetManager assetManager;

    public PrairieKing() {
        currentState = 0;
        highScore = 0;
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        menu = new Menu(this);
        loseScreen = new LoseScreen( this);
        winScreen = new WinScreen(this);
        gameLogic = new GameLogic(this, false);
        setScreen(menu);
    }

    public void loadAssets() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Mapas/Map.tmx");

        music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/mainTheme.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/start.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);

        assetManager.load("Menus/LoseScreen.png", Texture.class);
        assetManager.load("Menus/WinScreen.png", Texture.class);
        assetManager.load("Mapas/MapaFinal.png", Texture.class);
        assetManager.load("Sprites/MainSpriteSheet.png", Texture.class);
        assetManager.load("Sprites/TransitionToWin.png", Texture.class);
        assetManager.load("Sprites/BlockBackground.png", Texture.class);
        assetManager.load("Mapas/Map.png", Texture.class);
        assetManager.load("Menus/Menu1.png", Texture.class);
        assetManager.finishLoading();
        assetManager.update();
    }


    @Override
    public void render() {
        if(gameLogic.getGameStage().getHasWon()) {
            decreaseVolume(music, 0.005f);
        }

        if (currentState == 0) {
           setScreen(menu);
           if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                sound.play();
                currentState = 1;
            }
        }
        else if (currentState == 1) {
            setScreen(gameLogic.getGameStage());
            gameLogic.act();
            music.play();

        }
        else if (currentState == 2){
            setScreen(loseScreen);
            music.pause();

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 0;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
        }
        else {
            music.pause();
            setScreen(winScreen);

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 0;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
        }
        super.render();

    }

    public float decreaseVolume(Music music, float rate) {
        if (music.isPlaying()) {
            volume -= rate;
            if (volume > 0)
                music.setVolume(volume);
            else
                music.stop();
        }
        return volume;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getMaxHighScore() {
        return highScore;
    }

    public void setHighScore(int HighScore) {
        this.highScore = HighScore;
        loseScreen.updateHighScore(HighScore);
        winScreen.updateHighScore(HighScore);
    }

    public TiledMap getMap() {
        return map;
    }

    public void dispose(){
        music.dispose();
        sound.dispose();
    }


}
