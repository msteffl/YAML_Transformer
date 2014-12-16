package org.opentosca.yamlconverter.main;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.opentosca.model.tosca.*;
import org.opentosca.yamlconverter.main.dozer.MapAdapter;
import org.opentosca.yamlconverter.yamlmodel.yaml.element.YAMLFileRoot;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import java.util.*;

/**
 * @author Sebi
 */
public class SimpleCustomConverter implements CustomConverter {

    private final MapAdapter mapAdapter = new MapAdapter();

    @Override
    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }
        TDefinitions dest = null;
        YAMLFileRoot sourceObj = null;
        if (source instanceof YAMLFileRoot) {
            sourceObj = (YAMLFileRoot) source;
            if (destination == null) {
                dest = new TDefinitions();
            } else {
                dest = (TDefinitions) destination;
            }
            List<TEntityTemplate> nodeTemplates = new ArrayList<>();
            Set<String> keys = sourceObj.getNode_templates().keySet();
            for(String key : keys) {
                try {
                    TNodeTemplate tNodeTemplate = new TNodeTemplate();
                    tNodeTemplate.setName(key);

//                    TNodeTemplate.Properties props = new TNodeTemplate.Properties();
//                    Maaap map = new Maaap();
//                    map.map = sourceObj.getNode_templates().get(key).getProperties();
//
//                    props.setAny(map);

                    Map<QName, String> attributes = new HashMap<>();

                    tNodeTemplate.getOtherAttributes().putAll(attributes);
                    nodeTemplates.add(tNodeTemplate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            TTopologyTemplate tTopologyTemplate = new TTopologyTemplate();
            tTopologyTemplate.getNodeTemplateOrRelationshipTemplate().addAll(nodeTemplates);
            TServiceTemplate tServiceTemplate = new TServiceTemplate();
            tServiceTemplate.setTopologyTemplate(tTopologyTemplate);
            dest.getServiceTemplateOrNodeTypeOrNodeTypeImplementation().add(tServiceTemplate);

            return dest;
        }
        throw new MappingException("Not supported");
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Maaap {

        @XmlJavaTypeAdapter(MapAdapter.class)
        public Map<String, Object> map = new HashMap<>();
    }

    @XmlRegistry
    public static class ObjectFactory {

        @XmlElementDecl(name="any")
        @SuppressWarnings("rawtypes")
        public JAXBElement<Maaap> createMap(Maaap map) {
            return new JAXBElement<>(new QName("any"), Maaap.class, map);
        }
    }
}
