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
				-0.5f, 0.5f, 0f, 
				-0.5f, -0.5f, 0f, 
				0.5f, -0.5f, 0f, 
				//Right top triangle
				0.5f, -0.5f, 0f,
				0.5f, 0.5f,0f,
				-0.5f, 0.5f, 0f
		};
		RawModel model = loader.loadtoVAO(vertices);
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
