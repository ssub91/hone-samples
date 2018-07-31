package bomnal.online.example.dao;

import java.io.File;
import java.nio.file.Files;
import java.util.Stack;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, OnlineTestConfig.class})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageArchivingDaoTest {
	
	private static Stack<Image> images = new Stack<Image>();
	
	@Autowired
	private ImageArchivingDao imageArchivingDao;
	
	@Autowired
	ImageDao imageDao;
	
	@Test
	@Commit
	public void _beforeSetup() throws Exception  {
		for(int i = 0; i < 4; i++) {
			File file = new File("src/test/resources/ssub1.jpg");
			if (file.exists() == false) {
				Assert.fail("Image File Not Found");
			}
	
			Image image = images.push(new Image()); ;
			image.setData(Files.readAllBytes(file.toPath()));
	
			Long no = imageDao.insertImage(image);
	
			image.setNo(no);
			image.setData(null);
		}
	}
	
	@Test
	@Commit
	public void testArchiveImageUsingCallableCreator() {
		String archiveFileName = imageArchivingDao.archiveImageUsingCallableCreator(images.pop());
		assert( new File( archiveFileName ).exists() );
	}
	
	@Test
	@Commit
	public void testArchiveImageUsingCallableStatement() {
		String archiveFileName = imageArchivingDao.archiveImageUsingCallableStatement(images.pop());
		assert( new File( archiveFileName ).exists() );
	}	
	
	@Test
	@Commit
	public void testArchiveImageUsingSimpleJdbcCall() throws Exception {
		String archiveFileName = imageArchivingDao.archiveImageUsingSimpleJdbcCall(images.pop());
		assert( new File( archiveFileName ).exists() );
	}
	
	@Test
	@Commit
	public void testArchiveImageUsingGenericStoredProcedure() {
		String archiveFileName = imageArchivingDao.archiveImageUsingGenericStoredProcedure(images.pop());
		assert( new File( archiveFileName ).exists() );
	}	
	
}
