package bomnal.online.example.service;

import java.io.IOException;

import bomnal.online.example.dto.Image;

public interface ImageService {

	//blob 예제1 
	public Boolean insertImage(Image image) throws IOException;

}
