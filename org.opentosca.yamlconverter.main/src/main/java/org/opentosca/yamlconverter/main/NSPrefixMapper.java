package org.opentosca.yamlconverter.main;

import java.util.HashMap;
import java.util.Map;

import org.opentosca.yamlconverter.switchmapper.Yaml2XmlSwitch;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * This class defines some namespaces to preferred prefixes for XML.
 */
public class NSPrefixMapper extends NamespacePrefixMapper {

	private final Map<String, String> prefixMap;

	public NSPrefixMapper() {
		this.prefixMap = new HashMap<String, String>();
		this.prefixMap.put(Yaml2XmlSwitch.TYPES_NS, "types");
		this.prefixMap.put(Yaml2XmlSwitch.TOSCA_IMPORT_TYPE, Yaml2XmlSwitch.TOSCA_NS_PREFIX);
	}

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		if (this.prefixMap.containsKey(namespaceUri)) {
			return this.prefixMap.get(namespaceUri);
		}
		return suggestion;
	}

}
