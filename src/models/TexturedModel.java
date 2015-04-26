package models;

import Textures.ModelTexture;

public class TexturedModel {
	private RawModel rawModel;
	
	private ModelTexture texture;
	
	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel = model;
		this.texture = texture;
	}
}
