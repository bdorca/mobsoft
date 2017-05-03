package fleet.dork.btb.hu.fleet.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Token {

	@SerializedName("id")
	private String id;

	@SerializedName("ttl")
	private long ttl;

	@SerializedName("created")
	private Date created;

	@SerializedName("userId")
	private Long userId;

	//<editor-fold desc="Constructors|Getters|Setters">
	public Token() {
	}

	public Token(String id, long ttl, Date created, Long userId) {
		this.id = id;
		this.ttl = ttl;
		this.created = created;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	//</editor-fold>
}
