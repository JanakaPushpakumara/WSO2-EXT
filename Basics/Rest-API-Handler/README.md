# Rest API handler
The class must implement the **org.apache.synapse.rest.Handler** interface. 
If any properties are specified, the corresponding setter methods are invoked once on the handler class during initialization.

The Rest API handler is a custom Java class, which you need to maintain by yourself. 
Therefore, it is recommended to use the for very user-specific scenarios, for which, there is no built-in rest api handler
that already provides the required functionality.

## **Syntax**
To engage and deployed the Rest API handler, you need to add the following configuration in to the synapse api xml file.
```
<handlers>
  <handler class="com.sample.RestAPIHandler"/>
</handlers>

```
### Ex:
```
<?xml version="1.0" encoding="UTF-8"?>
<api xmlns="http://ws.apache.org/ns/synapse" name="respond" context="/test">
   <resource methods="POST GET">
      <inSequence>
         <log level="custom">
            <property name="***** message in ****" value="******** incoming *******" />
         </log>
         <send>
            <endpoint>
               <http
                  uri-template="http://run.mocky.io/v3/f4cb0c3b-753c-40fd-ba08-1e20bf92c65a/%23/abc" />
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
       <handler class="com.sample.RestAPIHandler" />
   </handlers>
</api>
```

