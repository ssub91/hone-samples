package bomnal.online.example.dao;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bomnal.framework.test.config.OnlineTestConfig;
import bomnal.framework.test.config.TestConfig;
import bomnal.online.example.dto.Image;
import bomnal.online.example.dto.ImageSqlLob;


// class AbstractOnlineTest에 @Before 버그가 있는 것 같음.
// 상속받지 않고 @RunWith, @ContextConfiguration 직접 사용
// public class ImageDaoTest extends AbstractOnlineTest {

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, OnlineTestConfig.class})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드 이름 순서대로 테스트 진행하도록...
public class ImageDaoTest {
	
	private static Long testImageNo = 0L;  
	
	@Autowired
	private ImageDao imageDao;

	@Test
	public void imageDaoNotBeNull() {
		assertNotNull(imageDao);
	}
	
	
	
	//
	// Image DTO 사용하는 메소드들 테스트. 
	//
	//
	
	@Test
	@Commit
	public void test01_inertImage() throws Exception {
		File file = new File( "src/test/resources/ssub1.jpg" );
		if ( file.exists() == false ) {
			Assert.fail("Image File Not Found");
		}

		Image image = new Image();
		image.setData( Files.readAllBytes( file.toPath() ) );

		testImageNo = imageDao.insertImage(image);
		assertNotNull(testImageNo);
	}
	
	@Test
	public void test02_selectImage() {
		Image image = new Image();
		image.setNo(testImageNo);
		
		image = imageDao.selectImage(image);
		assertNotNull(image.getData());
	}
	
	@Test
	@Commit
	public void test03_updateImage() throws Exception {
		File file = new File( "src/test/resources/ssub2.jpg" );
		if ( file.exists() == false ) {
			Assert.fail("Image File Not Found");
		}		
		
		Image image = new Image();
		image.setNo(testImageNo);		
		image.setData( Files.readAllBytes( file.toPath() ) );

		int count = imageDao.updateImage(image);
		assert(count == 1);
	}
	
	@Test
	@Commit
	public void test04_deleteImage() {
		Image image = new Image();
		image.setNo(testImageNo);
		
		int count = imageDao.deleteImage(image);
		assertNotNull(count == 1);
	}		

	
	
	//
	// ImageSqlLob DTO 사용하는 메소드들 테스트. 
	//
	
	@Test
	@Commit
	public void test05_inertImage() throws Exception {
		File file = new File( "src/test/resources/ssub1.jpg" );
		if ( file.exists() == false ) {
			Assert.fail("Image File Not Found");
		}
		
		ImageSqlLob imageSqlLob = new ImageSqlLob();
		imageSqlLob.setData( Files.readAllBytes( file.toPath() ) );

		testImageNo = imageDao.insertImage(imageSqlLob);
		assertNotNull(testImageNo);		
	}
	
	@Test
	public void test06_selectImage() {
		ImageSqlLob imageSqlLob = new ImageSqlLob();
		imageSqlLob.setNo(testImageNo);
		
		imageSqlLob = imageDao.selectImage( imageSqlLob );
		assertNotNull( imageSqlLob.getData() );
	}
	
	@Test
	@Commit
	public void test07_updateImage() throws Exception {
		File file = new File( "src/test/resources/ssub2.jpg" );
		if ( file.exists() == false ) {
			Assert.fail("Image File Not Found");
		}
		
		ImageSqlLob imageSqlLob = new ImageSqlLob();
		imageSqlLob.setNo(testImageNo);		
		imageSqlLob.setData( Files.readAllBytes( file.toPath() ) );

		int count = imageDao.updateImage(imageSqlLob);
		assert(count == 1);
	}
	
	@Test
	@Commit
	public void test08_deleteImage() {
		ImageSqlLob imageSqlLob = new ImageSqlLob();
		imageSqlLob.setNo(testImageNo);
		
		int count = imageDao.deleteImage(imageSqlLob);
		assertNotNull(count == 1);
	}
	

	
	//
	// AbstractLobCreatingPreparedStatementCallback 테스트 
	//
	
	@Test
	@Commit
	public void test09_insertImage() throws Exception {
		InputStream is = new FileInputStream( "src/test/resources/ssub1.jpg" );
		int count = imageDao.insertImage( 9999999999L, is );
		
		assertNotNull( count == 1 );
	}
	
	
	@Test
	@Commit
	public void test10_selectImage() throws Exception {
		OutputStream os = new FileOutputStream( "src/test/resources/ssub3.jpg" );
		imageDao.selectImage( 9999999999L, os );
		
		assert( new File( "src/test/resources/ssub3.jpg" ).exists() );
		
		// 삭제 처리	
		Image image = new Image();
		image.setNo( 9999999999L );
		int count = imageDao.deleteImage(image);
		assertNotNull(count == 1);
		
		new File( "src/test/resources/ssub3.jpg" ).deleteOnExit();
	}
}