# Steps to engage the RoleBasedAuthorization handler in wso2 mi 4.2.0 
1. build the Role-Based-Authorization-RestAPI-Handler module with,
 ```sh
   mvn clean install
   ```
2. Copy the handler .jar file in the targets/ directory in to,
 ```sh
   <WSO2_HOME>/lib/
   ```
3. Add carbon database for hybrid role management.
 ```
[[datasource]]
id = "WSO2CarbonDB"  # "WSO2_COORDINATION_DB"
url = "jdbc:mysql://localhost:3306/userdb   ?useSSL=false"
username = "root"
password = "root"
driver = "com.mysql.jdbc.Driver"
   ```
4. Add user store.
 ```
[user_store] 
type = "read_only_ldap"
   ```
5. Add the handler configuration into API xml file.

Ex:
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<api xmlns="http://ws.apache.org/ns/synapse" name="respond" context="/test">
    <resource methods="POST GET">
        <inSequence>
            <log level="custom">
                <property name="***** message in ****" value="******** incoming *******" />
            </log>
            <send>
                <endpoint>
                    <http uri-template="http://run.mocky.io/v3/f4cb0c3b-753c-40fd-ba08-1e20bf92c65a/%23/abc" />
                </endpoint>
            </send>
        </inSequence>
        <outSequence>
            <log level="custom">
                <property name="***** message out ****" value="******** outgoing *******" />
            </log>
            <respond />
        </outSequence>
    </resource>
    <handlers>
        <handler class="com.sample.RoleBasedAuthorization">
            <property name="allowedRoles" value="admin, Internal/everyone, temp" />
        </handler>
    </handlers>
</api>
```
## Sample request to invoke the API with user credentials
 ```sh
curl --location 'http://localhost:8290/test' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--data '{
 "AccountType": "Ledger",
 "CreditAmount": 10000.57,
 "DebitAmount": 0
}'
   ```
The Authorization Basic header contains <base64-encoded(admin:admin)>
