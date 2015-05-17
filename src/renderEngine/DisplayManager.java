package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 1024;
	public static final int MASTER_RACE = 120;
	private static long lastFrameTime;
	private static float delta;

	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3, 2)
				.withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("first Game");
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getCurrentTime();

	}

	public static void updateDisplay() {
		Display.sync(MASTER_RACE);
		Display.update();
		long currentFrameTime = getCurrentTime();

		delta = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;

	}

	public static float getFrameTimeSeconds() {
		return delta;
	}

	public static void closeDisplay() {

		Display.destroy();

	}

	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
