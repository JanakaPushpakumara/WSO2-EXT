
This user store is compatible with APIM 4.0.0, 4.1.0 and 4.2.0 and has the custom unique user id JDBC user store implementation.

# Steps

1. Build this using `mvn clean install` command 
2. Copy the `custom-jdbc-user-store-manager-1.0.0.jar` file to `<APIM-HOME>/repository/components/dropins` folder
3. Configure your deployment.toml file with the following configurations

Here, you need to configure the custom user store manager including the existing user store managers.
```
[user_store_mgt]
allowed_user_stores=["org.wso2.carbon.user.core.jdbc.UniqueIDJDBCUserStoreManager", "org.wso2.carbon.user.core.ldapUniqueIDActiveDirectorvUserStoreManager" ,"org.wso2.carbon.user.core.ldap.UniqueIDReadOnlyLDAPUserStoreManager", "ora.wso2.carbon.user.core.ldap.UniqueIDReadWriteLDAPUserStoreManager" , "org.wso2.carbon.user.core.jdbc.JDBCUserStoreManager", "org.wso2.custom.user.store.CustomUserStoreManager" ]```
```
4. Restart the API Manager. 
5. The custom User store will be visible in the carbon console under User Stores.
![image](https://github.com/JanakaPushpakumara/WSO2-EXT/assets/37293166/7e08150e-0418-4297-822f-0b7909c4e7c1)
