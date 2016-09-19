package bamboo.trove.common.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * POJO container for getting XML rules from API.
 * @author icaldwell
 *
 */
@XmlRootElement(name="rule")
@XmlAccessorType(XmlAccessType.FIELD)
public class RulePojo{
	private int id;
	private String policy;
	private String surt;
	private long embargo;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date captureStart;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date captureEnd;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date retrievedStart;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date retrievedEnd;
	private String who;
	private String exactMatch;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date lastModified;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getPolicy(){
		return policy;
	}
	public void setPolicy(String policy){
		this.policy = policy;
	}
	public String getSurt(){
		return surt;
	}
	public void setSurt(String surt){
		this.surt = surt;
	}
	public long getEmbargo(){
		return embargo;
	}
	public void setEmbargo(long embargo){
		this.embargo = embargo;
	}
	public Date getCaptureStart(){
		return captureStart;
	}
	public void setCaptureStart(Date captureStart){
		this.captureStart = captureStart;
	}
	public Date getCaptureEnd(){
		return captureEnd;
	}
	public void setCaptureEnd(Date captureEnd){
		this.captureEnd = captureEnd;
	}
	public Date getViewStart(){
		return retrievedStart;
	}
	public void setViewStart(Date viewStart){
		this.retrievedStart = viewStart;
	}
	public Date getViewEnd(){
		return retrievedEnd;
	}
	public void setViewEnd(Date viewEnd){
		this.retrievedEnd = viewEnd;
	}
	public String getWho(){
		return who;
	}
	public void setWho(String who){
		this.who = who;
	}
	public String getExactMatch(){
		return exactMatch;
	}
	public void setExactMatch(String exactMatch){
		this.exactMatch = exactMatch;
	}
	public Date getLastModified(){
		return lastModified;
	}
	public void setLastModified(Date lastModified){
		this.lastModified = lastModified;
	}
}
