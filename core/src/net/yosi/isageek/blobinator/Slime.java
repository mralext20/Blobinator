package net.yosi.isageek.blobinator;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.*;

public class Slime extends Actor {
	Stage stage;
	
	Texture texture;
	Texture blue_slime_stopped = new Texture(Gdx.files.internal("slime/blue_slime_stopped.png"));
	//Texture blue_slime_moving = new Texture(Gdx.files.internal("slime/blue_slime_moving.png"));	Texture not done yet :P
	Texture blue_slime_dead = new Texture(Gdx.files.internal("slime/blue_slime_death.png"));
	
	int isFlipped;
	Body body;
	

	public Slime() {
		stage = new Stage();
		
		texture = blue_slime_stopped;
		isFlipped = -1;
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(Gdx.graphics.getWidth() / 2 / Game.PPM, 2000 / Game.PPM);
		bdef.type = BodyType.DynamicBody;
		body = Game.world.createBody(bdef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getWidth() / Game.PPM, texture.getHeight() / Game.PPM);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
	}

	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(texture, body.getPosition().x * Game.PPM - texture.getWidth() * isFlipped, body.getPosition().y * Game.PPM - texture.getHeight(), texture.getWidth() * 2 * isFlipped, texture.getHeight() * 2);
	}

	@Override
	public void act(float delta){
		if(Game.leftButton.isPressed() && !Game.rightButton.isPressed()) {
			body.applyForceToCenter(-2, 0, true);
			isFlipped = 1;
			//texture = blue_slime_moving;
		} else if(Game.rightButton.isPressed() && !Game.leftButton.isPressed()) {
			body.applyForceToCenter(2, 0, true);
			isFlipped = -1;
			//texture = blue_slime_moving;
		} else {
			texture = blue_slime_stopped;
		}
	}
}
