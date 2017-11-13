package test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Chapter1;
import com.mygdx.game.Chapter2;
import com.mygdx.game.Character;
import com.mygdx.game.Clipping;
import com.mygdx.game.Main;

public class Chapter3Tester implements Screen{
	final Main main;
	
	//SpriteBatch, Texture variables
	public SpriteBatch batch;
	public Texture background;
	public TextureAtlas atlas;
	
	//Character variables
	public Character player1;
	public Character player2;
	public Player player3;
	
	//TiledMap variables
	public TiledMap tiledMap;
	public TiledMapRenderer tiledMapRenderer;
	public OrthographicCamera camera;
	
	//Box2D variables
	public World world;
	public Box2DDebugRenderer b2dr;
	
	public Chapter3(Main main) {
		this.main = main;
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
		
		//Sprite, Batch, Texture, Atlas
		batch = main.batch;
		
		//TiledMap
		tiledMap = new TmxMapLoader().load("tilemap/testmap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);	
		
		//Camera
		camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        
        //World
        world = new World(new Vector2(0, -0.7f), true);
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        for (MapObject object : tiledMap.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	
        	bdef.type = BodyDef.BodyType.StaticBody;
        	bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getWidth()/2);
        	
        	body = world.createBody(bdef);
        	
        	shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
        	fdef.shape = shape;
        	body.createFixture(fdef);
        }
        
		//Character
		player1 = new Character();
		player2 = new Character(1280, 680);
		
		/*-------------Texture,Animation of Objects-----------*/
		//Player1
		player1.atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		player1.go_up = new Animation(1/2f, player1.atlas.findRegion("walkback1"), player1.atlas.findRegion("walkback2"));
		player1.go_down = new Animation(1/2f, player1.atlas.findRegion("walk1"), player1.atlas.findRegion("walk2"));
		player1.go_right = new Animation(1/2f, player1.atlas.findRegion("walkR1"), player1.atlas.findRegion("walkR2"), player1.atlas.findRegion("walkR3"));
		player1.go_left = new Animation(1/2f, player1.atlas.findRegion("walkL1"), player1.atlas.findRegion("walkL2"), player1.atlas.findRegion("walkL3"));
		player1.stand = new Animation(1/2f, player1.atlas.findRegion("stand"), player1.atlas.findRegion("standback"), player1.atlas.findRegion("standR"), player1.atlas.findRegion("standL"));
		
		//Player2
		player2.atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		player2.go_up = new Animation(1/2f, player1.atlas.findRegion("walkback1"), player1.atlas.findRegion("walkback2"));
		player2.go_down = new Animation(1/2f, player1.atlas.findRegion("walk1"), player1.atlas.findRegion("walk2"));
		player2.go_right = new Animation(1/2f, player1.atlas.findRegion("walkR1"), player1.atlas.findRegion("walkR2"), player1.atlas.findRegion("walkR3"));
		player2.go_left = new Animation(1/2f, player1.atlas.findRegion("walkL1"), player1.atlas.findRegion("walkL2"), player1.atlas.findRegion("walkL3"));
		player2.stand = new Animation(1/2f, player1.atlas.findRegion("stand"), player1.atlas.findRegion("standback"), player1.atlas.findRegion("standR"), player1.atlas.findRegion("standL"));
		
		//Player3
		atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		//Player3
		player3 = new Player(world, this);
	}
	
	public void handle(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)) {
//			player3.pos_y += 5;
//			player3.b2body.setTransform(player3.pos_x, player3.pos_y, 0);
			//player3.b2body.applyForce(new Vector2(0, 500), player3.b2body.getWorldCenter(), true);
			player3.b2body.applyLinearImpulse(new Vector2(0, 4f), player3.b2body.getWorldCenter(), true);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
//			player3.pos_y -= 5;
//			player3.b2body.setTransform(player3.pos_x, player3.pos_y, 0);
			player3.b2body.applyLinearImpulse(new Vector2(0, -4f), player3.b2body.getWorldCenter(), true);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
//			player3.pos_x -= 5;
//			player3.b2body.setTransform(player3.pos_x, player3.pos_y, 0);
			player3.b2body.applyLinearImpulse(new Vector2(-4f, 0), player3.b2body.getWorldCenter(), true);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
//			player3.pos_x += 5;
//			player3.b2body.setTransform(player3.pos_x, player3.pos_y, 0);
			player3.b2body.applyLinearImpulse(new Vector2(4f, 0), player3.b2body.getWorldCenter(), true);
		}
		//else
			//player3.b2body.setLinearVelocity(new Vector2(0, 0));
	}
	
	
	@Override
	public void render(float delta) {
		handle(delta);
		player3.update(delta);
		world.step(1/60f, 6, 2);
		

		//Clear
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//----------------------------------------//
		
		//Change map
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			main.setScreen(new Chapter2(main));
			//dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			main.setScreen(new Chapter1(main));
			//dispose();
		}
		//Check Clipping//
		player1.pos_x = Clipping.check_x(player1.pos_x, player1.size_x);
		player1.pos_y = Clipping.check_y(player1.pos_y, player1.size_y);
		
		player2.pos_x = Clipping.check_x(player2.pos_x, player2.size_x);
		player2.pos_y = Clipping.check_y(player2.pos_y, player2.size_y);
		
		//-----------------------------------//
		
		
		//Player1 Standing//
		if (player1.stop == 0)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0, true);
		if (player1.stop == 1)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
		if (player1.stop == 2)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1f, true);
		if (player1.stop == 3)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1.5f, true);
		//-------------------------------------------------------------------------------//
		
		//Player2 Standing//
		if (player2.stop == 0)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0, true);
		if (player2.stop == 1)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
		if (player2.stop == 2)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1f, true);
		if (player2.stop == 3)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1.5f, true);
		//-------------------------------------------------------------------------------//
		
		
		//Player1 Movement//
		if (true) {
			if(Gdx.input.isKeyPressed(Input.Keys.UP)){
	            player1.currentFrame = (TextureRegion) player1.go_up.getKeyFrame(player1.elapsedTime, true);
	            player1.pos_y += 5;
	            player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
	            player1.stop = 1;
	        }
			else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				player1.currentFrame = (TextureRegion) player1.go_down.getKeyFrame(player1.elapsedTime, true);
				player1.pos_y -= 5;
				player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player1.stop = 0;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				player1.currentFrame = (TextureRegion) player1.go_right.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x += 5;
				player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player1.stop = 2;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				player1.currentFrame = (TextureRegion) player1.go_left.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x -= 5;
				player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player1.stop = 3;
			}
		}
		//-----------------------------------------------------------------------------------------------------//
		
		//Player2 Movement//
		if (true) {
			if(Gdx.input.isKeyPressed(Input.Keys.W)) {
				player2.currentFrame = (TextureRegion) player2.go_up.getKeyFrame(player2.elapsedTime, true);
			    player2.pos_y += 5;
			    player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
			    player2.stop = 1;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
				player2.currentFrame = (TextureRegion) player2.go_down.getKeyFrame(player2.elapsedTime, true);
				player2.pos_y -= 5;
				player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player2.stop = 0;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
				player2.currentFrame = (TextureRegion) player2.go_right.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x += 5;
				player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player2.stop = 2;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
				player2.currentFrame = (TextureRegion) player2.go_left.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x -= 5;
				player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player2.stop = 3;
			}
		}
		//-----------------------------------------------------------------------------------------------------//

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
        b2dr.render(world, camera.combined);
		//Graphics Rendering//
		batch.begin();
		batch.draw(player1.currentFrame, player1.pos_x, player1.pos_y, player1.size_x, player1.size_y);
		batch.draw(player2.currentFrame, player2.pos_x, player2.pos_y, player2.size_x, player2.size_y);
		player3.draw(batch);
		batch.end();
		//---------------------------------------------------------------------------------//
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		player1.atlas.dispose();
		player2.atlas.dispose();
		tiledMap.dispose();
		world.dispose();
		b2dr.dispose();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
