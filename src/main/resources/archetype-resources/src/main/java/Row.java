#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Row {
    @Id
    @GeneratedValue
    private Long id;
    private String value;

    /**
     * Empty constructor needed for JPA.
     */
    @SuppressWarnings("unused")
    public Row() {
    }

    public Row(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
