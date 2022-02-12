# Steps to engage the global synapse handler in wso2am-3.2.0
1. build the Websocket-Synapse-Handler module with,
 ```sh
   mvn clean install
   ```
2. Copy the handler .jar file in the targets/ directory in to,
 ```sh
   <APIM_HOME>/repository/components/lib/
   ```
3. Add the handler configuration into the repository/conf/deployment.toml file.
Ex:
 ```sh
enabled_global_handlers= ["WebSocketHandler"]
[synapse_handlers]
WebSocketHandler.name= "WebSocketHandler"
WebSocketHandler.class= "com.sample.WebSocketHandler"
   ```

