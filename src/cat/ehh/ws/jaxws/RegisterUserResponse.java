
package cat.ehh.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * Fri Jan 15 16:35:59 CET 2016
 * Generated source version: 2.7.14.redhat-1
 */

@XmlRootElement(name = "registerUserResponse", namespace = "http://ws.ehh.cat/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUserResponse", namespace = "http://ws.ehh.cat/")

public class RegisterUserResponse {

    @XmlElement(name = "return")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

