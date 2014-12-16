package org.opentosca.yamlconverter.main;

import org.junit.Assert;
import org.junit.Test;
import org.opentosca.yamlconverter.main.interfaces.IToscaYaml2XmlConverter;

/**
 * @author Sebi
 */
public class ToscaYaml2XmlConverterTest extends BaseTest {

    private IToscaYaml2XmlConverter converter = new ToscaYaml2XmlConverter();

    @Test
    public void testYaml2Xml() throws Exception {
        String yamlString = testUtils.readYamlTestResource("/yaml/helloworld.yaml");
        String xmlString = converter.yaml2xml(yamlString);
        Assert.assertNotNull(xmlString);
    }
}
