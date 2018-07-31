package bomnal.online.example.service;

import org.springframework.stereotype.Service;
import bomnal.online.example.dto.Customer;
import hone.bom.annotation.ServiceId;
import hone.bom.annotation.ServiceName;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	@ServiceId("EXCUST001")
	@ServiceName("고객정보 조회")
	public Customer getCustomer(Customer inVo) {
		return inVo;
	}

}
