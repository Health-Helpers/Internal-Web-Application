
package cat.ehh.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * Mon Dec 21 11:59:10 CET 2015
 * Generated source version: 2.7.14.redhat-1
 */

@XmlRootElement(name = "getPatientLocation", namespace = "http://ws.ehh.cat/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPatientLocation", namespace = "http://ws.ehh.cat/")

public class GetPatientLocation {

    @XmlElement(name = "patientId",namespace = "http://ws.ehh.cat/")
    private int arg0;

    public int getArg0() {
        return this.arg0;
    }

    public void setArg0(int newArg0)  {
        this.arg0 = newArg0;
    }

}

