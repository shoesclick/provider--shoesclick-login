package com.shoesclick.provider.spi.admin;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class AdminStorageProviderFactory implements UserStorageProviderFactory<AdminStorageProvider> {

    public static final String PROVIDER_NAME = "admin-user-store";
    AdminUserStore userStore = new AdminUserStore();

    @Override
    public AdminStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new AdminStorageProvider(session, model, userStore);
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }
}
