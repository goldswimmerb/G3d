package engineTester;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Shaders.StaticShader;
import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
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
		RawModel model = loader.loadtoVAO(vertices, textureCoords, indicies);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("doge-600")));
		//TexturedModel staticModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-1),0,0,0,1);
		while(!Display.isCloseRequested()){
			//sets change in position x, y, z
			entity.increasePosition(0, 0, -0.1f);
			//sets change in rotation x, y, z
			entity.increaseRotation(0, 0, 0);
			//prepares the renderer to "draw" the program
			renderer.prepare();
			//starts the shaders
			shader.start();
		//game logic
			//tells renderer what to render
		renderer.render(entity,shader);
		//stops the shaders
		shader.stop();
		DisplayManager.updateDisplay();

	}
		shader.cleanUp();
		loader.cleanUP();
	DisplayManager.closeDisplay();
	}
}
