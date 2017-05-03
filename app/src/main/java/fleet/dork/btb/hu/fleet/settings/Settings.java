package fleet.dork.btb.hu.fleet.settings;

import javax.inject.Inject;
import javax.inject.Singleton;

import fleet.dork.btb.hu.fleet.network.model.Token;
import fleet.dork.btb.hu.fleet.util.Store;


@Singleton
public class Settings {

	private static final String SESSION_TOKEN = "SESSION_TOKEN";

	@Inject
	public Settings() {
	}

	public void saveSessionToken(Token sessionToken) {
		Store.put(SESSION_TOKEN, sessionToken);
	}

	public void deleteSessionToken() {
		Store.remove(SESSION_TOKEN);
	}

	public Token getSessionToken() {
		return Store.get(SESSION_TOKEN, Token.class);
	}

	public boolean isLoggedIn() {
		return Store.get(SESSION_TOKEN, Token.class) != null;
	}
}
