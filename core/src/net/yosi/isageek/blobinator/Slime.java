package net.yosi.isageek.blobinator;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class Slime extends Actor
{
	Texture texture;
	Texture slime_stopped = new Texture(Gdx.files.internal("slime/blue_slime_stopped.png"));
	//Texture slime_moving = new Texture(Gdx.files.internal("slime/blue_slime_moving.png"));	Texture not done yet :P
	Texture slime_dead = new Texture(Gdx.files.internal("slime/blue_slime_death.png"));
	int isFlipped;
	Vector2 position, velocity;

	public Slime()
	{
		position = new Vector2(Game.stage.getWidth() / 2, 50);
		velocity = new Vector2(0, 0);
		texture = slime_stopped;
		isFlipped = -1;
	}

	@Override
	public void draw(Batch batch, float alpha){
		batch.draw(texture, position.x - texture.getWidth() * isFlipped, position.y, texture.getWidth() * 2 * isFlipped, texture.getHeight() * 2);
	}

	@Override
	public void act(float delta){
		if(Game.leftButton.isPressed() && !Game.rightButton.isPressed()) {
			velocity.x = -5;
			isFlipped = 1;
			//texture = slime_moving;
		} else if(Game.rightButton.isPressed() && !Game.leftButton.isPressed()) {
			velocity.x = 5;
			isFlipped = -1;
			//texture = slime_moving;
		} else {
			velocity.x = 0;
			texture = slime_stopped;
		}

		position.add(velocity);
	}
}
