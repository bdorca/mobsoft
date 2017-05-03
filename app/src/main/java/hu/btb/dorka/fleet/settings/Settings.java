package hu.btb.dorka.fleet.settings;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.btb.dorka.fleet.network.model.Token;
import hu.btb.dorka.fleet.util.Store;


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
