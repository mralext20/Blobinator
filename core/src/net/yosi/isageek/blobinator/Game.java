package net.yosi.isageek.blobinator;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button.*;

public class Game implements ApplicationListener
{

    public static Stage stage;
	public static Button leftButton;
	public static Button rightButton;

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Slime slime = new Slime();
		stage.addActor(slime);
		slime.setTouchable(Touchable.disabled);
		
		Skin skin = new Skin();
		skin.add("button_normal", new Texture(Gdx.files.internal("gui/button1.png")));
		skin.add("button_pressed", new Texture(Gdx.files.internal("gui/button2.png")));
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.newDrawable("button_normal");
		buttonStyle.down = skin.newDrawable("button_pressed");
		skin.add("default", buttonStyle);
		Table controlsLayout = new Table();
		stage.addActor(controlsLayout);
		controlsLayout.toFront();
		controlsLayout.bottom().setFillParent(true);
		controlsLayout.defaults().expandX().size(150, 150).fill().pad(50).bottom();
		leftButton = new Button(skin);
		controlsLayout.add(leftButton).left();
		rightButton = new Button(skin);
		controlsLayout.add(rightButton).right();
	}

	@Override
	public void render()
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}