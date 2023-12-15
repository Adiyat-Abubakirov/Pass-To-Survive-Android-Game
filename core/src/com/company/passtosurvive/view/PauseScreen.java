package com.company.passtosurvive.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseScreen implements Screen { // the pause menu is launched when the pause button is pressed in the game
    final Main game;
    private TextureAtlas atlas;
    private Skin skin;
    private Stage stage;
    private ImageButton resume, restart, mainMenu;
    public PauseScreen(final Main game) {
        this.game=game;
        stage=new Stage();
        atlas=new TextureAtlas("AllComponents.pack");
        skin=new Skin(Gdx.files.internal("Buttons.json"), atlas);
        resume=new ImageButton(skin, "default5");
        restart=new ImageButton(skin, "default6");
        mainMenu=new ImageButton(skin, "default3");
        stage.addActor(restart);
        stage.addActor(resume);
        stage.addActor(mainMenu);
        Gdx.input.setInputProcessor(stage);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                if (Main.screen==1) {
                    game.setScreen(new Level1ScreenPart1(game));
                }
                else if(Main.screen==2){
                    game.setScreen(new Level1ScreenPart2(game));
                }
                else if(Main.screen==3){
                    game.setScreen(new Level2ScreenFloor1(game));
                }
                else if(Main.screen==4){
                    game.setScreen(new Level2ScreenFloor2(game));
                }
            }
        });
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.HumanY=0;
                Main.HumanX=0;
                Main.HumanXCheckpoint=0;
                Main.HumanYCheckpoint=0;
                dispose();
                if (Main.screen==1) {
                    game.setScreen(new Level1ScreenPart1(game));
                }
                else if(Main.screen==2){
                    game.setScreen(new Level1ScreenPart2(game));
                }
                else if(Main.screen==3){
                    game.setScreen(new Level2ScreenFloor1(game));
                }
                else if(Main.screen==4){
                    game.setScreen(new Level2ScreenFloor2(game));
                }
            }
        });
        mainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // cleanup
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // cleanup
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { // in the presentation I explained why I divide 1794 by Main.width
        resume.setSize(700/(1794/Main.width), 234f/(1794/Main.width));
        restart.setSize(700/(1794/Main.width), 234f/(1794/Main.width));
        mainMenu.setSize(700/(1794/Main.width), 234f/(1794/Main.width));
        resume.setPosition((Main.width/2)-((resume.getWidth())/2), (Main.height/2)+150f/(1794/Main.width));
        restart.setPosition((Main.width/2)-((resume.getWidth())/2), (Main.height/2)-100f/(1794/Main.width));
        mainMenu.setPosition((Main.width/2)-((resume.getWidth())/2), (Main.height/2)-350f/(1794/Main.width));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        game.dispose();
    }
}
