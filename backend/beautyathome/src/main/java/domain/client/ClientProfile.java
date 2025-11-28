package domain.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClientProfile {

	private final Client client;
	private final Set<String> preferredCategories = new HashSet<>();
	private boolean marketingConsent;

	public ClientProfile(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void addPreferredCategory(String categoryName) {
		preferredCategories.add(categoryName);
	}

	public Set<String> getPreferredCategories() {
		return Collections.unmodifiableSet(preferredCategories);
	}

	public boolean hasMarketingConsent() {
		return marketingConsent;
	}

	public void setMarketingConsent(boolean marketingConsent) {
		this.marketingConsent = marketingConsent;
	}
}
