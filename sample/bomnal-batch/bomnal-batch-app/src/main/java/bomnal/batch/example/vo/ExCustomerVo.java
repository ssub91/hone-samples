/**
 */
package bomnal.batch.example.vo;

import hone.bom.io.metadata.annotation.Length;
import hone.bom.web.simple.dto.SerializableDto;

/**
 * {논리명}
 * 
 * <p>
 * {설명}
 * 
 * <p>
 * <b>변경이력:</b>
 * 
 * <pre>
 * - Gildong Hong(gildong) 2017. 9. 4. 초기작성
 * </pre>
 * @author Gildong Hong(gildong)
 *
 */
public class ExCustomerVo implements SerializableDto {

    /**
     * 
     */
    private static final long serialVersionUID = 765307123511673016L;

    @Length(10)
    private String custNo;
    
    @Length(60)
    private  String custNm;
    
    @Length(6)
    private String zipCd;
    
    @Length(150)
    private String custAddr1;
    
    @Length(150)
    private String custAddr2;
    
    @Length(4)
    private String offTelDdd1;
    
    @Length(15)
    private String offTel1;
    
    public String getCustNo() {
        return custNo;
    }
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
    public String getCustNm() {
        return custNm;
    }
    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }
    public String getZipCd() {
        return zipCd;
    }
    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }
    public String getCustAddr1() {
        return custAddr1;
    }
    public void setCustAddr1(String custAddr1) {
        this.custAddr1 = custAddr1;
    }
    public String getCustAddr2() {
        return custAddr2;
    }
    public void setCustAddr2(String custAddr2) {
        this.custAddr2 = custAddr2;
    }
    public String getOffTelDdd1() {
        return offTelDdd1;
    }
    public void setOffTelDdd1(String offTelDdd1) {
        this.offTelDdd1 = offTelDdd1;
    }
    public String getOffTel1() {
        return offTel1;
    }
    public void setOffTel1(String offTel1) {
        this.offTel1 = offTel1;
    }
    
}
