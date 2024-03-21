package com.shoesclick.provider.spi.site;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class SiteStorageProviderFactory implements UserStorageProviderFactory<SiteStorageProvider> {

    public static final String PROVIDER_NAME = "site-user-store";
    SiteUserStore userStore = new SiteUserStore();

    @Override
    public SiteStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new SiteStorageProvider(session, model, userStore);
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }
}
