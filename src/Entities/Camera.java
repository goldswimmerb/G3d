package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	private Vector3f position = new Vector3f(250,2,0);
	
	
	//cameras angle
	private float pitch = 20;
	//l or r
	private float yaw = 0;
	//cameras "rotation"
	private float roll;
	private Player player;
	
	public Camera(Player player){
		this.player = player;
	}
	
	public void move(){
		/*if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z-=-0.1f;
		}*/
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY()+ angleAroundPlayer);
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	private void calculateCameraPosition(float horizDistance, float veticDistance ){
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + veticDistance;
		
	}
	private float calculateHorizontalDistance(){
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	private float calculateVerticalDistance(){
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	private void calculateZoom(){
		float zoomLevel = Mouse.getDWheel() * .01f;
		distanceFromPlayer -= zoomLevel;
	}
	private void calculatePitch(){
		if(Mouse.isButtonDown(1)){
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}
	private void calculateAngleAroundPlayer(){
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
	}
}
