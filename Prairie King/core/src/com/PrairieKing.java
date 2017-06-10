package com;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.model.GameLogic;
import com.view.LoseScreen;
import com.view.MainMenu;
import com.view.WinScreen;

/** Main Game Class.
 */
public class PrairieKing extends Game {
    public static int currentState;

    private Music mainTheme;
    private Sound spaceBar;
    private Music footsteps, deathHero;
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
        loadSounds();
        menu = new MainMenu(this);
        loseScreen = new LoseScreen( this);
        winScreen = new WinScreen(this);
        gameLogic = new GameLogic(this, false);
        setScreen(menu);
    }

    private void loadSounds() {
        mainTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/maintheme.mp3"));
        mainTheme.setVolume(0.5f);
        mainTheme.setLooping(true);
        spaceBar = Gdx.audio.newSound(Gdx.files.internal("sounds/start.mp3"));
        footsteps = Gdx.audio.newMusic(Gdx.files.internal("sounds/footstep.mp3"));
        deathHero = Gdx.audio.newMusic(Gdx.files.internal("sounds/heroDeath.mp3"));
    }

    /** Loads all assets onto AssetManager.
     */
    public void loadAssets() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapas/map.tmx");

        assetManager.load("menus/losescreen.png", Texture.class);
        assetManager.load("menus/winscreen.png", Texture.class);
        assetManager.load("mapas/mapafinal.png", Texture.class);
        assetManager.load("sprites/mainspritesheet.png", Texture.class);
        assetManager.load("sprites/transitiontowin.png", Texture.class);
        assetManager.load("sprites/blockbackground.png", Texture.class);
        assetManager.load("mapas/map.png", Texture.class);
        assetManager.load("menus/menu.png", Texture.class);

        assetManager.finishLoading();
        assetManager.update();
    }


    /** Responsible for the correct render, depending on the current gameState.
     */
    @Override
    public void render() {
        if(gameLogic.getGameStage().getHasWon()) {
            decreaseVolume(mainTheme, 0.005f);
        }

        if (currentState == 0) {
           setScreen(menu);
           if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                spaceBar.play();
                currentState = 1;
            }
        }
        else if (currentState == 1) {
            setScreen(gameLogic.getGameStage());
            gameLogic.act();
            if(!deathHero.isPlaying()) {
                mainTheme.play();
            }

        }
        else if (currentState == 2){
            setScreen(loseScreen);
            mainTheme.pause();

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 0;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
        }
        else {
            mainTheme.pause();
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

    /** Gets the main theme song
     *
     * @return music
     */
    public Music getMusic() {
        return mainTheme;
    }

    /** Gets the hero's footsteps sound
     *
     * @return Music footsteps
     */
    public Music getFootstepsSound() {
        return footsteps;
    }

    /** Gets the hero's death sound
     *
     * @return Music deathHero
     */
    public Music getDeathHeroSound() {
        return deathHero;
    }

    /** Called when finished.
     */
    public void dispose(){
        mainTheme.dispose();
        spaceBar.dispose();
        deathHero.dispose();
        footsteps.dispose();
        map.dispose();
        assetManager.dispose();
    }


}
