package Shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{
	//tells computer where the shaders files are since openGL3+ is an open pipeline
private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	private int location_transformationMatrix;
	private int location_projectionMatrix;

	//loads vertex and fragment shader
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		// TODO Auto-generated method stub
		
	}

	@Override
	//Gets values from Matrices declared in GSLS
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
	}
	//loads transformation matrix
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
