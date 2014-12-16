package org.opentosca.yamlconverter.main.dozer;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Sebi
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class MapAdapterEntry {

    @XmlAttribute
    public String key;

    @XmlValue
    public Object value;
}
