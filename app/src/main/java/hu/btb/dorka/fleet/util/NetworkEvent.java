package hu.btb.dorka.fleet.util;

public abstract class NetworkEvent {
	private int code;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">
	public NetworkEvent() {
	}

	public NetworkEvent(int code, Throwable throwable) {
		this.code = code;
		this.throwable = throwable;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	//</editor-fold>
}
