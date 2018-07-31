package bomnal.online.example.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import bomnal.online.example.dto.Image;

@Repository
public class ImageArchivingDao {

	/* Beans */
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	private StoredProcedure procedure;

	/* Properties */
	private static final String ARCHIVING_REPOSITORY = "src/test/resources/";
	private static final String PROC_NAME = "IMAGE_ARCHIVING";
	private static final String CALL_SQL = "{call " + PROC_NAME + "(?, ?, ?)}";

	/* Configurations */
	@Autowired
	public ImageArchivingDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		procedure = new GenericStoredProcedure();
		procedure.setDataSource(dataSource);
	}

	// Example for Using CallableStatement
	public String archiveImageUsingCallableStatement(Image image) {
		String archiveFileName = null;
		
		try {
			CallableStatement callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall(CALL_SQL);
			callableStatement.setLong(1, image.getNo());
			callableStatement.registerOutParameter(2, Types.BLOB);
			callableStatement.registerOutParameter(3, Types.VARCHAR);

			callableStatement.executeUpdate();
			
			byte[] blobAsBytes = callableStatement.getBytes(2);
			archiveFileName = ARCHIVING_REPOSITORY + callableStatement.getString(3);
			Files.write(Paths.get(archiveFileName), blobAsBytes);
			
		} catch (SQLException | IOException ex) {
			throw new RuntimeException("Fails - archiveImageUsingCallableStatement : " + ex);
		}
		
		return archiveFileName;
	}

	// Example for Using CallableCreator
	public String archiveImageUsingCallableCreator(final Image image) {

		Map<String, Object> result = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) {
				try {
					CallableStatement callableStatement = connection.prepareCall(CALL_SQL);
					callableStatement.setLong(1, image.getNo());
					callableStatement.registerOutParameter(2, Types.BLOB);
					callableStatement.registerOutParameter(3, Types.VARCHAR);
					
					return callableStatement;
				} catch (SQLException ex) {
					throw new RuntimeException("Fails - archiveImageUsingCallableCreator : " + ex);
				}
			}
		}, Arrays.asList(new SqlParameter[]{
				new SqlParameter(Types.BIGINT),
				new SqlOutParameter("O_DATA", Types.BLOB),
				new SqlOutParameter("O_FILENAME", Types.VARCHAR)
		}));
	
		String archiveFileName = null;
		try {
		
			Blob blob = (Blob)result.get("O_DATA");
			byte[] blobAsBytes = blob.getBytes(1, (int)blob.length());
			blob.free();
			
			archiveFileName = ARCHIVING_REPOSITORY + result.get("O_FILENAME");
			Files.write(Paths.get(archiveFileName), blobAsBytes);
			
		} catch( SQLException | IOException ex ) {
			throw new RuntimeException("Fails - archiveImageUsingCallableCreator : " + ex);
		}

		return archiveFileName;
	}

	// Example for Using SimpleJdbcCall
	public String archiveImageUsingSimpleJdbcCall(Image image) {

		simpleJdbcCall.withProcedureName(PROC_NAME).declareParameters(
				new SqlParameter("P_NO", Types.BIGINT),
				new SqlOutParameter("O_DATA", Types.BLOB),
				new SqlOutParameter("O_FILENAME", Types.VARCHAR));
		
		Map<String, Object> result = simpleJdbcCall.execute(new MapSqlParameterSource("P_NO", image.getNo()));

		String archiveFileName = null;
		try {		
			Blob blob = (Blob)result.get("O_DATA");
			byte[] blobAsBytes = blob.getBytes(1, (int)blob.length());
			blob.free();
			
			archiveFileName = ARCHIVING_REPOSITORY + result.get("O_FILENAME");
			Files.write(Paths.get(archiveFileName), blobAsBytes);
			
		} catch( SQLException | IOException ex ) {
			throw new RuntimeException("Fails - archiveImageUsingSimpleJdbcCall : " + ex);
		}

		return archiveFileName;		
	}

	// Example for Using StoredProcedure
	public String archiveImageUsingGenericStoredProcedure(Image image) {

		procedure.setSql(PROC_NAME);
		procedure.setFunction(false); // set true if database function called
		procedure.setParameters(new SqlParameter[] {
				new SqlParameter("P_NO", Types.BIGINT),
				new SqlOutParameter("O_DATA", Types.BLOB),
				new SqlOutParameter("O_FILENAME", Types.VARCHAR)
		});

		procedure.compile();
		Map<String, Object> result = procedure.execute(image.getNo());

		String archiveFileName = null;
		try {			
			Blob blob = (Blob)result.get("O_DATA");
			byte[] blobAsBytes = blob.getBytes(1, (int)blob.length());
			blob.free();
			
			archiveFileName = ARCHIVING_REPOSITORY + result.get("O_FILENAME");
			Files.write(Paths.get(archiveFileName), blobAsBytes);
			
		} catch( SQLException | IOException ex ) {
			throw new RuntimeException("Fails - archiveImageUsingGenericStoredProcedure : " + ex);
		}

		return archiveFileName;		
	}
}
