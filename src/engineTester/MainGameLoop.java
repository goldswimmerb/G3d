package engineTester;
import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
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
		
		RawModel model = loader.loadtoVAO(vertices, indicies);
		while(!Display.isCloseRequested()){
			renderer.prepare();
		//game logic
		renderer.render(model);;
		DisplayManager.updateDisplay();

	}
		loader.cleanUP();
	DisplayManager.closeDisplay();
	}
}
