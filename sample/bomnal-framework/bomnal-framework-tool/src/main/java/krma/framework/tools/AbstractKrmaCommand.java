package krma.framework.tools;

import hone.bom.tools.command.AbstractCommand;

import javax.annotation.Resource;
import javax.sql.DataSource;

public abstract class AbstractKrmaCommand extends AbstractCommand {
	@Resource(name="bizDs")
	private DataSource bizDataSource;

	public DataSource getBizDataSource() {
		return bizDataSource;
	}
	
	
}
