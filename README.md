This library helps with the internationalisation of an application; it allows the generation of `properties` files for `ResourceBundle` from annotated Strings in the code. 

Refer also to [`foundation-i18n-ap`](https://tquadrat.github.io/foundation-i18n-ap/) 

### Documentation

- [Javadoc Reference](https://tquadrat.github.io/foundation-i18n/javadoc/index.html)

- Maven POM
    ```xml
    <dependency>
      <groupId>org.tquadrat.library</groupId>
      <artifactId>org.tquadrat.foundation.i18n</artifactId>
      <version>0.25.5</version>
    </dependency>
    ```
- Gradle
    ```Groovy
    dependencies {
      …
  
      //---* The tquadrat Foundation Internationalisation stuff *--------------
      implementation 'org.tquadrat.library:org.tquadrat.foundation.i18n:0.25.5'
      annotationProcessor 'org.tquadrat.tool:org.tquadrat.foundation.i18n.ap:0.25.5'
      …
    }  //  dependencies
    ```
  
The current version for the annotation processor can be different from that for this component.

---  
Last updated: 2026-05-25T22:09:26.674952914+02:00[Europe/Berlin]