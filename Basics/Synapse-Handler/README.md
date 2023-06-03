# Class Mediator
The Synapse handler creates an instance of a custom-specified class and sets it as a global synapse handler. 
The class must implement the **org.apache.synapse.AbstractSynapseHandler** interface. 
If any properties are specified, the corresponding setter methods are invoked once on the handler class during initialization.

The Synapse handler is a custom Java class, which you need to maintain by yourself. 
Therefore, it is recommended to use the for very user-specific scenarios, for which, there is no built-in handler
that already provides the required functionality.

## **Syntax**
To engage the deployed Synapse handler, you need to add the following configuration to the <EI_HOME>/conf/synapse-handlers.xml file.
```
<handlers>
    <handler name="TestSynapseHandler" class="com.sample.TestSynapseHandler"/>
</handlers>
```
