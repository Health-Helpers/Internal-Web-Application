
package cat.ehh.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * Thu Dec 17 12:31:51 CET 2015
 * Generated source version: 2.7.14.redhat-1
 */

@XmlRootElement(name = "readResponsibleResponse", namespace = "http://ws.ehh.cat/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "readResponsibleResponse", namespace = "http://ws.ehh.cat/")

public class ReadResponsibleResponse {

    @XmlElement(name = "return",namespace = "http://ws.ehh.cat/")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

