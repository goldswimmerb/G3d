package engineTester;
import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DisplayManager.createDisplay();
		while(!Display.isCloseRequested()){
		//game logic
		//render
		DisplayManager.updateDisplay();

	}
	DisplayManager.closeDisplay();
	}
}
