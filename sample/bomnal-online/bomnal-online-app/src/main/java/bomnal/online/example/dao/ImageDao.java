package bomnal.online.example.dao;

import java.io.InputStream;
import java.io.OutputStream;

import bomnal.online.example.dto.Image;
import bomnal.online.example.dto.ImageSqlLob;

public interface ImageDao {

	static final String HQML_NAME = "bomnal.online.example.dao.ImageDao";

	static final String SELECT_IMAGE_QUERY = HQML_NAME + "." + "selectImage";
	static final String INSERT_IMAGE_QUERY = HQML_NAME + "." + "insertImage";
	static final String UPDATE_IMAGE_QUERY = HQML_NAME + "." + "updateImage";
	static final String DELETE_IMAGE_QUERY = HQML_NAME + "." + "deleteImage";
	
	public Long insertImage(Image image);                      		// C
	public Long insertImage(ImageSqlLob imageSqlLob);          		// C
	public int insertImage(final Long no, final InputStream is);      // C

	public Image selectImage(Image image);                     		// R
	public ImageSqlLob selectImage(ImageSqlLob imageSqlLob);   		// R
	public void selectImage(final Long no, final OutputStream os);	// R
	
	public int updateImage(Image image); 	                  		// U
	public int updateImage(ImageSqlLob imageSqlLob); 	          		// U
	
	public int deleteImage(Image image); 	                  		// D
	public int deleteImage(ImageSqlLob imageSqlLob); 	          		// D
}
