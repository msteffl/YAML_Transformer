package org.opentosca.yamlconverter.main.dozer;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebi
 */
public final class MapAdapter extends XmlAdapter<MapAdapterType, Map<String, Object>> {

    @Override
    public Map<String, Object> unmarshal(MapAdapterType mapAdapterType) throws Exception {
        Map<String, Object> map = new HashMap<>();
        for(MapAdapterEntry entry : mapAdapterType.entry) {
            map.put(entry.key, entry.value);
        }
        return null;
    }

    @Override
    public MapAdapterType marshal(Map<String, Object> map) throws Exception {
        MapAdapterType mapAdapterType = new MapAdapterType();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            MapAdapterEntry adapterEntry = new MapAdapterEntry();
            adapterEntry.key = entry.getKey();
            adapterEntry.value = entry.getValue();
            mapAdapterType.entry.add(adapterEntry);
        }
        return mapAdapterType;
    }
}
