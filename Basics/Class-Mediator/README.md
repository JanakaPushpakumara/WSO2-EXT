# Class Mediator
The Class Mediator creates an instance of a custom-specified class and sets it as a mediator. 
The class must implement the **org.apache.synapse.api.Mediator** interface. 
If any properties are specified, the corresponding setter methods are invoked once on the class during initialization.

The Class mediator is a custom Java class, which you need to maintain by yourself. 
Therefore, it is recommended to use the Class mediator only for not frequently re-used custom developments and 
very user-specific scenarios, for which, there is no built-in mediator that already provides the required functionality.

## **Syntax**

```
<class name="class-name">
<property name="string" (value="literal" | expression="[XPath|json-eval(JSON Path)]")/>
</class>
```

### Ex:
* ```  <class name="com.sample.TestMediator"/> ```
* ```
    <class name="com.sample.TestMediator">
       <property name="property1" value="value1")/>
    </class>
    ```