package com.shoesclick.provider.adapter;

import com.shoesclick.provider.model.User;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.UserCredentialManager;
import org.keycloak.models.*;
import org.keycloak.storage.adapter.AbstractUserAdapter;

public class UserAdapterModel extends AbstractUserAdapter {

    private final User user;

    public UserAdapterModel(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, User user) {
        super(session, realm, storageProviderModel);
        this.user = user;

    }


    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return new UserCredentialManager(this.session, this.realm, this);
    }

}