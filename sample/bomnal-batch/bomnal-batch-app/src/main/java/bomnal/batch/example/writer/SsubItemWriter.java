package bomnal.batch.example.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class SsubItemWriter<T> implements ItemWriter<T> {

	private static final Logger log = LoggerFactory.getLogger( SsubItemWriter.class );

	@Override
	public void write(List<? extends T> items) throws Exception {
		log.info( "SsubItemWriter.write called[items size:" + items.size() + "]" );
	}
}