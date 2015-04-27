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
import Shaders.StaticShader;
import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
//import renderEngine.Renderer;
public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		//StaticShader shader = new StaticShader();
		//Renderer renderer = new Renderer(shader);
		
		/* these are for a 2d rectange
		float[] vertices = { 
				//Left bottom Triangle
				-0.5f, 0.5f, 0f,//v0 
				-0.5f, -0.5f, 0f, //v1
				0.5f, -0.5f, 0f, //v2
				//Right top triangle
				//0.5f, -0.5f, 0f,//v1
				0.5f, 0.5f,0f,//v2
				//-0.5f, 0.5f, 0f//v3
		};
		int[] indicies = {
				0, 1, 3,
				3, 1, 2
		};
		float[] textureCoords ={
				0,0, //V0
				0,1, //V1
				1,1, //V2
				1,0 //V3
		};
		*/
		// this is a cube
		/*float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15, 
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		*/
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("doge-600")));
		
		
		///TexturedModel staticModel = new TexturedModel(model, texture);
		ModelTexture texture = staticModel.getTexture();
		//texture.setShineDamper(5);
		//texture.setReflectivity(1);
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-25),0,0,0,1);
		

		Light light = new Light(new Vector3f(3000,2000,3000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,-1, loader, new ModelTexture(loader.loadTexture("doge-600")));
		Terrain terrain2 = new Terrain(1,-1, loader, new ModelTexture(loader.loadTexture("doge-600")));
		Camera camera = new Camera();
		//List<Entity> allCubes = new ArrayList<Entity>();
		//Random random = new Random();
		
		/*for(int i = 0; i<200;i++){
			float x = random.nextFloat() * 100-50;
			float y = random.nextFloat() * 100-50;
			float z = random.nextFloat() * -300;
			allCubes.add(new Entity(staticModel, new Vector3f(x,y,z), random.nextFloat() *180f,
					random.nextFloat() *180f,0f,1f));*/
		
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			//sets change in position x, y, z
			entity.increaseRotation(0, 1, 0);
			//entity.increasePosition(0, 0, -0.1f);
			camera.move();
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			
			//for(Entity cube : allCubes){
				//renderer.processEntity(cube);
			//}
			//sets change in rotation x, y, z
			//entity.increaseRotation(0, 0, 0);
			//prepares the renderer to "draw" the program
			//renderer.prepare();
			//starts the shaders
			//shader.start();
			//shader.loadLight(light);
			//shader.loadViewMatrix(camera);
		//game logic
			//tells renderer what to render
		//renderer.render(entity,shader);
		//stops the shaders
		//shader.stop();
		renderer.render(light,camera);
		DisplayManager.updateDisplay();

	}
		//shader.cleanUp();
		renderer.cleanUp();
		loader.cleanUP();
	DisplayManager.closeDisplay();
	}
}
