package bomnal.batch.example.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import bomnal.batch.example.vo.PersonVo;

public class SsubItemReader implements ItemReader<PersonVo> {
	private List<PersonVo> items;
    private int currentIndex = 0;

    public SsubItemReader() {
    	initialize();
    }
    
	@Override
	public PersonVo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (currentIndex < items.size()) {
            return items.get( currentIndex++ );
        }
        
		return null;
	}
    
    private void initialize() {
		// 테스트 데이터
		items = new ArrayList<PersonVo>();
		items.add( new PersonVo( "Jill", "Doe" ) );
		items.add( new PersonVo( "Joe", "Doe" ) );
		items.add( new PersonVo( "Justin", "Doe" ) );    	
    }	
}