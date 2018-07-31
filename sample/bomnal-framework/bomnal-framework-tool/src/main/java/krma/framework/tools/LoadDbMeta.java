package krma.framework.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("krma.load.meta")
public class LoadDbMeta extends AbstractKrmaCommand {
	private JdbcTemplate jdbcTemplate;

	@Override
	public void execute(String cmd, String[] args) {
		jdbcTemplate = new JdbcTemplate(getBizDataSource());

		String sql = "select tb.table_name, tb.column_name, tb.data_type, tb.nullable " +
				", tb.data_length, tb.data_precision, tb.data_scale " +
				", (select tc.comments from user_tab_comments tc where tc.table_name = ta.object_name) as table_comments " +
				", (select cc.comments from user_col_comments cc where cc.table_name = ta.object_name and cc.column_name = tb.column_name) as column_comments " +
				", (select case count(*) when 0 then 'N' else 'Y' end from dba_constraints cons, dba_cons_columns cons_col " +
				"where cons.constraint_type='P' " +
				"and cons.constraint_name = cons_col.constraint_name " +
				"and cons.table_name = ta.object_name and cons_col.column_name = tb.column_name) as is_primary_key " +
				"from dba_objects ta, dba_tab_columns tb " +
				"where ta.owner='KRMA_APP'  " +
				"and ta.object_type='TABLE'  " +
				"and ta.status = 'VALID' " +
				"and ta.owner = tb.owner " +
				"and ta.object_name = tb.table_name " +
				//				"and rownum < 100 " +
				"order by ta.object_name, tb.column_id";

		List<Map<String, Object>> rowSet = jdbcTemplate.query(sql, new ColumnMapRowMapper());
		FileWriter fw = null;
//		try {
//			fw = new FileWriter("C:\\KRMADEV\\workspace\\HONE Preferences\\datasource\\entityMeta.data");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		for (Map<String, Object> row : rowSet) {

			String columnComments = "";
			if (row.get("COLUMN_COMMENTS") != null) {
				columnComments = (String)row.get("COLUMN_COMMENTS"); 
				if (columnComments.indexOf("\n") > 0) {
					columnComments = columnComments.substring(0, columnComments.indexOf("\n"));
				}
			}
			String meta = String.format("%s|%s|%s|%s|%s|%s|N|%s\n", row.get("TABLE_NAME")
					, (row.get("TABLE_COMMENTS") != null ? row.get("TABLE_COMMENTS") : "")
					, columnComments
					, row.get("COLUMN_NAME")
					, row.get("DATA_TYPE")
					, row.get("IS_PRIMARY_KEY")
					, row.get("NULLABLE")
					);
//			try {
//				fw.write(meta);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
//		try {
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

}
