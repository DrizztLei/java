
public class RequestParamsBean {

	private String userid;
	private String kid;
	private String key;

	private Integer remoteType;
	private String order;

	private String baseOrder;
	private String extraOrder;
	
	private String username;
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getRemoteType() {
		return remoteType;
	}

	public void setRemoteType(Integer remoteType) {
		this.remoteType = remoteType;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBaseOrder() {
		return baseOrder;
	}

	public void setBaseOrder(String baseOrder) {
		this.baseOrder = baseOrder;
	}

	public String getExtraOrder() {
		return extraOrder;
	}

	public void setExtraOrder(String extraOrder) {
		this.extraOrder = extraOrder;
	}

}
