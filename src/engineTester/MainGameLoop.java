package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Entities.Light;
import Entities.Player;
import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import terrains.TerrainTexture;
import terrains.TerrainTexturePack;

//import renderEngine.Renderer;
public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayManager.createDisplay();
		Loader loader = new Loader();

		// StaticShader shader = new StaticShader();
		// Renderer renderer = new Renderer(shader);

		/*
		 * these are for a 2d rectange float[] vertices = { //Left bottom
		 * Triangle -0.5f, 0.5f, 0f,//v0 -0.5f, -0.5f, 0f, //v1 0.5f, -0.5f, 0f,
		 * //v2 //Right top triangle //0.5f, -0.5f, 0f,//v1 0.5f, 0.5f,0f,//v2
		 * //-0.5f, 0.5f, 0f//v3 }; int[] indicies = { 0, 1, 3, 3, 1, 2 };
		 * float[] textureCoords ={ 0,0, //V0 0,1, //V1 1,1, //V2 1,0 //V3 };
		 * 
		 * this is a cube
		 * 
		 * float[] vertices = { -0.5f,0.5f,-0.5f, -0.5f,-0.5f,-0.5f,
		 * 0.5f,-0.5f,-0.5f, 0.5f,0.5f,-0.5f,
		 * 
		 * -0.5f,0.5f,0.5f, -0.5f,-0.5f,0.5f, 0.5f,-0.5f,0.5f, 0.5f,0.5f,0.5f,
		 * 
		 * 0.5f,0.5f,-0.5f, 0.5f,-0.5f,-0.5f, 0.5f,-0.5f,0.5f, 0.5f,0.5f,0.5f,
		 * 
		 * -0.5f,0.5f,-0.5f, -0.5f,-0.5f,-0.5f, -0.5f,-0.5f,0.5f,
		 * -0.5f,0.5f,0.5f,
		 * 
		 * -0.5f,0.5f,0.5f, -0.5f,0.5f,-0.5f, 0.5f,0.5f,-0.5f, 0.5f,0.5f,0.5f,
		 * 
		 * -0.5f,-0.5f,0.5f, -0.5f,-0.5f,-0.5f, 0.5f,-0.5f,-0.5f,
		 * 0.5f,-0.5f,0.5f
		 * 
		 * };
		 * 
		 * float[] textureCoords = {
		 * 
		 * 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1,
		 * 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0
		 * 
		 * 
		 * };
		 * 
		 * int[] indices = { 0,1,3, 3,1,2, 4,5,7, 7,5,6, 8,9,11, 11,9,10,
		 * 12,13,15, 15,13,14, 16,17,19, 19,17,18, 20,21,23, 23,21,22
		 * 
		 * };
		 */
		// Terrain Texture stuff
		TerrainTexture backgroundTexture = new TerrainTexture(
				loader.loadTexture("grass"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(
				loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

		TerrainTexturePack texturePack = new TerrainTexturePack(
				backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(
				loader.loadTexture("blendMap"));

		RawModel model = OBJLoader.loadObjModel("Tree", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(
				loader.loadTexture("tree")));

		/*
		 * TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel(
		 * "grassModel", loader), new ModelTexture(
		 * loader.loadTexture("grassTexture")));
		 */
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern",
				loader), new ModelTexture(loader.loadTexture("fern")));
		TexturedModel lowTree = new TexturedModel(OBJLoader.loadObjModel(
				"lowPolyTree", loader), new ModelTexture(
				loader.loadTexture("lowPolyTree")));

		RawModel bunnyModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel,
				new ModelTexture(loader.loadTexture("playerTexture")));
		/*
		 * RawModel model1 = OBJLoader.loadObjModel("grassModel", loader); //
		 * TexturedModel staticModel1 = new TexturedModel(model1, new //
		 * ModelTexture(loader.loadTexture("grassTexture"))); // /TexturedModel
		 * staticModel = new TexturedModel(model, texture); ModelTexture texture
		 * = staticModel.getTexture(); // ModelTexture texture1 =
		 * staticModel1.getTexture(); // texture.setShineDamper(5);
		 * texture.setReflectivity(1);
		 */
		Entity entity = new Entity(staticModel, new Vector3f(10, 0, -25), 0, 0,
				0, 1);
		Player player = new Player(stanfordBunny, new Vector3f(100, 0, -50), 0,
				180, 0, 0.4f);
		// Entity entity1 = new Entity(staticModel1, new
		// Vector3f(20,0,-25),0,0,0,1);

		Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1,
				1, 1));

		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap,
				"heightmap");
		// Terrain terrain2 = new Terrain(1,-1, loader, texturePack, blendMap,
		// "heightmap");

		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random(676452);

		for (int i = 0; i < 500; i++) {
			if (i % 20 == 0) {
				float x = random.nextFloat() * 800 - 400;
				float z = random.nextFloat() * -600;
				float y = terrain.getHeightOfTerrain(x, z);

				entities.add(new Entity(fern, new Vector3f(x, y, z), 0, random
						.nextFloat() * 360, 0, 0.9f));
				fern.getTexture().setHasTransparancy(true);
			}
			if (i % 5 == 0) {
				float x = random.nextFloat() * 800 - 400;
				float z = random.nextFloat() * -600;
				float y = terrain.getHeightOfTerrain(x, z);

				entities.add(new Entity(staticModel, new Vector3f(x, y, z), 0,
						0, 0, random.nextFloat() * 1 + 4));
				x = random.nextFloat() * 800 - 400;
				z = random.nextFloat() * -600;
				y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(lowTree, new Vector3f(x, y, z), 0,
						random.nextFloat() * 360, 0,
						random.nextFloat() * 0.1f + 0.6f));
				// adds grass but introduces texture bug atm
				/*
				 * z = random.nextFloat() * -600; y =
				 * terrain.getHeightOfTerrain(x, z); entities.add(new
				 * Entity(grass, new Vector3f(x, y, z), 0, random.nextFloat() *
				 * 360 , 0, random.nextFloat() * 0.5f + 0.6f));
				 * 
				 * grass.getTexture().setHasTransparancy(true);
				 * grass.getTexture().setUseFakeLighting(true);
				 */
			}
		}

		MasterRenderer renderer = new MasterRenderer();

		Camera camera = new Camera(player);
		while (!Display.isCloseRequested()) {
			/*
			 * sets change in position x, y, z // entity.increaseRotation(0, 1,
			 * 0); entity.increasePosition(0, 0, -0.1f);
			 */
			player.move(terrain);
			camera.move();

			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			// renderer.processTerrain(terrain2);
			renderer.processEntity(entity);

			for (Entity all : entities) {
				renderer.processEntity(all);
			}

			/*
			 * sets change in rotation x, y, z // entity.increaseRotation(0, 0,
			 * 0); // prepares the renderer to "draw" the program //
			 * renderer.prepare(); // starts the shaders // shader.start(); //
			 * shader.loadLight(light); // shader.loadViewMatrix(camera); //
			 * game logic // tells renderer what to render //
			 * renderer.render(entity,shader); // stops the shaders
			 * shader.stop();
			 */
			renderer.render(light, camera);
			DisplayManager.updateDisplay();

		}
		// shader.cleanUp();
		renderer.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
}
