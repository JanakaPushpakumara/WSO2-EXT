# Steps to engage the global synapse handler in ESB 500
1. build the Websocket-Synapse-Handler module with,
 ```sh
   mvn clean install
   ```
2. Copy the handler .jar file in the targets/ directory in to,
 ```sh
   <ESB_HOME>/repository/components/lib/
   ```
3. Add the handler configuration into the repository/conf/synapse-handlers.xm file.
Ex:
 ```xml
<handlers>
    <handler name="WebSocketHandler" class="com.esb.sample.WebSocketHandler"/>
</handlers>
   ```

