package net.yosi.isageek.blobinator;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button.*;
public class Game implements ApplicationListener{
	
	public static final int PPM = 10;
	
	public static World world;
    public static Stage guiStage;
    public static Stage worldStage;
	public static Button leftButton;
	public static Button rightButton;
	Slime slime;
	
	Box2DDebugRenderer dbgRenderer;
	OrthographicCamera gameCamera;
	
	@Override
	public void create() {
		world = new World(new Vector2(0, -9.81f), true);
		
		dbgRenderer = new Box2DDebugRenderer();
		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho(false, Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
		
		BodyDef platformBody = new BodyDef();
		platformBody.position.set(Gdx.graphics.getWidth() / 2 / PPM, 300 / PPM);
		platformBody.type = BodyType.StaticBody;
		Body platform = Game.world.createBody(platformBody);
		PolygonShape platformShape = new PolygonShape();
		platformShape.setAsBox(300 / PPM, 50 / PPM);
		FixtureDef platformFixture = new FixtureDef();
		platformFixture.shape = platformShape;
		platform.createFixture(platformFixture);
		
		guiStage = new Stage();
		Gdx.input.setInputProcessor(guiStage);
		
		worldStage = new Stage();
		slime = new Slime();
		worldStage.addActor(slime);
		
		Skin skin = new Skin();
		skin.add("button_normal", new Texture(Gdx.files.internal("gui/button1.png")));
		skin.add("button_pressed", new Texture(Gdx.files.internal("gui/button2.png")));
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.newDrawable("button_normal");
		buttonStyle.down = skin.newDrawable("button_pressed");
		skin.add("default", buttonStyle);
		Table controlsLayout = new Table();
		guiStage.addActor(controlsLayout);
		controlsLayout.toFront();
		controlsLayout.bottom().setFillParent(true);
		controlsLayout.defaults().expandX().size(150, 150).fill().pad(50).bottom();
		leftButton = new Button(skin);
		controlsLayout.add(leftButton).left();
		rightButton = new Button(skin);
		controlsLayout.add(rightButton).right();
	}

	@Override
	public void render() {        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
		guiStage.act(Gdx.graphics.getDeltaTime());
		guiStage.draw();
		
		worldStage.act();
		worldStage.draw();
		
		dbgRenderer.render(world, gameCamera.combined);
		
		world.step(Gdx.graphics.getDeltaTime() * 40, 6, 2);
	}

	@Override
	public void dispose()
	{
		guiStage.dispose();
		worldStage.dispose();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
}
