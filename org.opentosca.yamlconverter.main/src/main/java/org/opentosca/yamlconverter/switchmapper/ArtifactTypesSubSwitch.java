package org.opentosca.yamlconverter.switchmapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.opentosca.model.tosca.TArtifactType;
import org.opentosca.model.tosca.TExtensibleElements;
import org.opentosca.yamlconverter.yamlmodel.yaml.element.ArtifactType;

public class ArtifactTypesSubSwitch extends AbstractSubSwitch {

	public ArtifactTypesSubSwitch(Yaml2XmlSwitch parentSwitch) {
		super(parentSwitch);
	}

	@Override
	public void process() {
		if (getServiceTemplate().getArtifact_types() != null) {
			getDefinitions().getServiceTemplateOrNodeTypeOrNodeTypeImplementation().addAll(
					getArtifactTypesFromYaml(getServiceTemplate().getArtifact_types()));
		}
	}

	private Collection<? extends TExtensibleElements> getArtifactTypesFromYaml(Map<String, ArtifactType> artifact_types) {
		final List<TArtifactType> artifactTypes = new ArrayList<TArtifactType>();

		for (final Entry<String, ArtifactType> entry : artifact_types.entrySet()) {
			final TArtifactType artifactType = new TArtifactType();
			artifactType.setName(entry.getKey());

			final ArtifactType value = entry.getValue();
			artifactType.setDerivedFrom(parseDerivedFrom(value.getDerived_from()));
			artifactType.setPropertiesDefinition(parsePropertiesDefinition(value.getProperties(), entry.getKey()));

			artifactTypes.add(artifactType);
		}

		return artifactTypes;
	}

}