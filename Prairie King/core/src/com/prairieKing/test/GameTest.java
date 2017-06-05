package com.prairieKing.test;


import com.badlogic.gdx.Application;

public class GameTest {

    // This is our "test" application
    private static Application application;

    // Before running any tests, initialize the application with the headless backend
    //@org.junit.BeforeClass
    public static void init() {
        // Note that we don't need to implement any of the listener's methods
        //application = new HeadlessApplication(new ApplicationListener() {
          /* @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        // Use Mockito to mock the OpenGL methods since we are running headlessly
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;*/
    }

    // After we are done, clean up the application
    //@AfterClass
    public static void cleanUp() {
        // Exit the application first
        application.exit();
        application = null;
    }
}