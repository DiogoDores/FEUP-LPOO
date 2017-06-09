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
import com.prairieKing.view.MainMenu;

/** Main Game Class.
 */
public class PrairieKing extends Game {
    public static int currentState;
    private Music music;
    private Sound sound;
    private MainMenu menu;
    private LoseScreen loseScreen;
    private WinScreen winScreen;
    private int highScore;
    private GameLogic gameLogic;

    private TmxMapLoader mapLoader;
    private TiledMap map;

    public static final float PPM = 100;
    private float volume = 0.5f;

    private AssetManager assetManager;

    /** Constructor for Prairie King, starts the state and highScore at 0.
     */
    public PrairieKing() {
        currentState = 0;
        highScore = 0;
    }

    /** Override the Create function, instantiates all classes.
     */
    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        menu = new MainMenu(this);
        loseScreen = new LoseScreen( this);
        winScreen = new WinScreen(this);
        gameLogic = new GameLogic(this, false);
        setScreen(menu);
    }

    /** Loads all assets onto AssetManager.
     */
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


    /** Responsible for the correct render, depending on the current gameState.
     */
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

    /** Gradually decrease volume.
     *
     * @param music Music to be muted.
     * @param rate Rate at whiich volume decreases.
     * @return New Volume.
     */
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

    /** Returns the created AssetManager.
     *
     * @return AssetManager.
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }

    /** Returns the highScore of the last game.
     *
     * @return HighScore.
     */
    public int getHighScore() {
        return highScore;
    }

    /** Sets the new HighScore after finishing a game.
     *
     * @param HighScore New HighScore.
     */
    public void setHighScore(int HighScore) {
        this.highScore = HighScore;
        loseScreen.updateHighScore(HighScore);
        winScreen.updateHighScore(HighScore);
    }

    /** Returns the Map.
     *
     * @return Map.
     */
    public TiledMap getMap() {
        return map;
    }

    /** Called when finished.
     */
    public void dispose(){
        music.dispose();
        sound.dispose();
    }


}
