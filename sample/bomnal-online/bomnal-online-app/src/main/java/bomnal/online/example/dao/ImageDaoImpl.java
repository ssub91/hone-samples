package bomnal.online.example.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;

import bomnal.online.example.dto.Image;
import bomnal.online.example.dto.ImageSqlLob;
import hone.bom.dao.hqml.support.HqmlDefaultDaoSupport;

@Repository
public class ImageDaoImpl extends HqmlDefaultDaoSupport implements ImageDao {

	@Override
	public Long insertImage(Image image) {
		
		// Primary Key 를 받기 위해...
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int count = update(INSERT_IMAGE_QUERY, image, keyHolder, new String[]{"NO"});
		if( count != 1 ) {
			return null;
		}
		
		Number key = keyHolder.getKey();
		if( key == null ) {
			return null;
		}
		
		return key.longValue();
	}
	
	@Override
	public Long insertImage(ImageSqlLob imageSqlLob) {
		//
		// 혼 적용
		// HqmlDefaultDaoSupport의 문제
		//
		// 1. HoneBaseSqlParameterSource 는 SqlLobValue 를 지원하지 않음, 
		//    지원하는 MapSqlParameterSource를 사용
		//
		// 2. HqmlDefaultDaoSupport의 구현된 update는 MapSqlParameterSource를 지원하지 않음
		//    내장된 HoneNamedParameterJdbcTemplate를 통해 직접 호출해야 함. 따라서 Hone Mapper를 사용하지 않는다.
		//
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue( "data", imageSqlLob.getData(), Types.BLOB);	
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int count = getDaoTemplate().update( "INSERT INTO IMAGE_TABLE(NO, DATA) VALUES (IMAGE_TABLE_SEQ.nextval, :data)", paramSource, keyHolder, new String[]{"NO"} );
		if( count != 1 ) {
			return null;
		}
		
		Number key = keyHolder.getKey();
		if( key == null ) {
			return null;
		}
		
		return key.longValue();		
	}

	@Override
	public int insertImage(final Long no, final InputStream is) {
		return getDaoTemplate().getJdbcOperations().execute( "INSERT INTO IMAGE_TABLE(NO, DATA) VALUES (?, ?)", new AbstractLobCreatingPreparedStatementCallback( new DefaultLobHandler() ) {
			@Override
			protected void setValues( PreparedStatement preparedstatement, LobCreator lobcreator ) {
				try {
					preparedstatement.setLong(1, no);
					lobcreator.setBlobAsBytes( preparedstatement, 2, IOUtils.toByteArray( is ) );
				} catch (SQLException | IOException ex) {
					throw new RuntimeException( "Exception: InserImage : " + ex );
				}
			}
		});
	}
	
	@Override
	public int deleteImage(Image image) {
		return update(DELETE_IMAGE_QUERY, image);
	}

	@Override
	public int deleteImage(ImageSqlLob imageSqlLob) {
		return update(DELETE_IMAGE_QUERY, imageSqlLob);
	}
	
	@Override
	public int updateImage(Image image) {
		return update(UPDATE_IMAGE_QUERY, image);
	}
	
	@Override
	public int updateImage(ImageSqlLob imageSqlLob) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue( "no", imageSqlLob.getNo() );
		paramSource.addValue( "data", imageSqlLob.getData(), Types.BLOB);	
		
		return getDaoTemplate().update( "UPDATE IMAGE_TABLE SET DATA = :data WHERE NO = :no", paramSource);
	}
	
	@Override
	public Image selectImage(Image image) {
		return queryForObject(SELECT_IMAGE_QUERY, image, Image.class);
	}

	@Override
	public void selectImage(final Long no, final OutputStream os) {
		getDaoTemplate().getJdbcOperations().query( "SELECT DATA FROM IMAGE_TABLE WHERE NO = :no", new Object[] {no}, new AbstractLobStreamingResultSetExtractor<byte[]>() {
				public void streamData(ResultSet rs) throws SQLException, IOException {
					IOUtils.copy( new DefaultLobHandler().getBlobAsBinaryStream( rs, 1 ), os );
				}
        	});		
	}
	
	@Override
	public ImageSqlLob selectImage(ImageSqlLob imageSqlLob) {
		Image image = queryForObject(SELECT_IMAGE_QUERY, imageSqlLob, Image.class);

		imageSqlLob.setData(image.getData());
		return imageSqlLob;
	}
}
