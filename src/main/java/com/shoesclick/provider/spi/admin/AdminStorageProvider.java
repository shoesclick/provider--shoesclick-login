package com.shoesclick.provider.spi.admin;

import com.shoesclick.provider.spi.adapter.UserAdapterModel;
import com.shoesclick.provider.spi.model.User;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

import java.util.HashMap;
import java.util.Map;

public class AdminStorageProvider implements
        UserStorageProvider,
        UserLookupProvider,
        CredentialInputValidator {

    private AdminUserStore adminUserStore;
    KeycloakSession keycloakSession;
    ComponentModel componentModel;
    protected Map<String, UserModel> loadedUsers = new HashMap<>();

    public AdminStorageProvider(KeycloakSession session, ComponentModel model,
                                AdminUserStore adminUserStore) {
        this.adminUserStore = adminUserStore;
        this.keycloakSession = session;
        this.componentModel = model;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        StorageId storageId = new StorageId(id);
        String username = storageId.getExternalId();
        return getUserByUsername(realm, username);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        UserModel adapter = loadedUsers.get(username);
        if (adapter == null) {
            User user = adminUserStore.getUser(username);
            if (user != null) {
                adapter = createAdapter(realm, user);
                loadedUsers.put(username, adapter);
            }
        }
        return adapter;
    }

    private UserModel createAdapter(RealmModel realm, User user) {
        return new UserAdapterModel(keycloakSession,realm,componentModel,user);
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return credentialType.equals(PasswordCredentialModel.TYPE);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {

        try {
            String password = adminUserStore.getUser(user.getUsername()).getPassword();
            return credentialType.equals(PasswordCredentialModel.TYPE) && password != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        if (!supportsCredentialType(credentialInput.getType())) return false;

        try {
            String password = adminUserStore.getUser(user.getUsername()).getPassword();
            if (password == null) return false;
            return password.equals(credentialInput.getChallengeResponse());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
