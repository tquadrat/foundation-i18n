This library helps with the internationalisation of an application; it allows the generation of `properties` files for `ResourceBundle` from annotated Strings in the code. 

Refer also to [`foundation-i18n-ap`](https://tquadrat.github.io/foundation-i18n-ap/) 

### Documentation

- [Javadoc Reference](https://tquadrat.github.io/foundation-i18n/javadoc/index.html)

- Maven POM
    ```xml
    <dependency>
      <groupId>org.tquadrat.library</groupId>
      <artifactId>org.tquadrat.foundation.i18n</artifactId>
      <version>0.1.0</version>
    </dependency>
    ```
- Gradle
    ```Groovy
    dependencies {
      …
  
      //---* The tquadrat Internationalisation stuff *------------------------
      implementation 'org.tquadrat.library:org.tquadrat.foundation.i18n:0.1.0'
      annotationProcessor 'org.tquadrat.tool:org.tquadrat.foundation.i18n.ap:0.1.0'
      …
    }  //  dependencies
    ```