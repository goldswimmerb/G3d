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
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
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
		
		Entity entity = new Entity(staticModel, new Vector3f(-1,0,0),0,0,0,1);
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
		//game logic
		renderer.render(entity,shader);
		shader.stop();
		DisplayManager.updateDisplay();

	}
		shader.cleanUp();
		loader.cleanUP();
	DisplayManager.closeDisplay();
	}
}
