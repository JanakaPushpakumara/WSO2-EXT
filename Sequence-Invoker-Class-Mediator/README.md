# Sequence Invoke Mediator 
The SequenceInvokerMediator is a custom mediator in WSO2 EI/MI designed to invoke a specified sequence dynamically during message mediation. This mediator creates an instance of the user-specified class, which must implement the org.apache.synapse.api.Mediator interface. It is intended for use in scenarios where there is a need to dynamically trigger sequences at runtime.

## **Syntax**
```
<class name="com.sample.SequenceInvokerMediator">
   <property name="sequenceToInvoke" value="testseq"/>
</class>
```
### Important Note

The SequenceInvokerMediator has certain limitations due to its single-threaded execution approach. It cannot be used for sequences that contain non-blocking call mediators or send mediators inline. If your sequence requires such mediators, it is advisable to use the built-in sequence mediator implementation provided by WSO2 EI/MI server.