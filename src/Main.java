import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		try {
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
