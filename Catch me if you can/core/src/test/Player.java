package test;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Chapter3;

public class Player extends Sprite{
	public World world;
	public Body b2body;
	public TextureRegion playerStand;
	public int pos_x=48, pos_y=48;
	//public TextureAtlas atlas;
	
	public Player(World world, Chapter3 screen) {
		super(screen.atlas.findRegion("stand"));
		this.world = world;
		definePlayer();
		playerStand = screen.atlas.findRegion("stand");
		setBounds(pos_x-24, pos_y-24, 48, 48);
		setRegion(playerStand);
	}
	
	public void update(float delta) {
		setPosition(b2body.getPosition().x-24, b2body.getPosition().y-24);
	}
	
	public void definePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(pos_x, pos_y);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(20);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		

	}
}
