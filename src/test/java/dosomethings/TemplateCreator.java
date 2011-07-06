package dosomethings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.webgearz.tb.domain.models.Template;
import com.webgearz.tb.services.TemplateService;

@ContextConfiguration(locations={
"classpath:/dosomethings/mongo-config.xml"})
public class TemplateCreator extends AbstractJUnit4SpringContextTests{

	@Resource
	private TemplateService templateService;
	
	@Test
	public void insertNewTemplate(){
		Template tempalte = createTemplateOne();
		templateService.createTemplate(tempalte);
		
	}

	private Template createTemplate() {
		Template tem = new Template();
		tem.setTemplateName("sample");
		List<String> divIds = new ArrayList<String>();
		divIds.add("template1_div1");
		divIds.add("template1_div2");
		tem.setDivIds(divIds);
		
		return tem;
	}
	private Template createTemplateOne(){
		Template template = new Template();
		template.setTemplateName("template_one");
		List<String> divIds = new ArrayList<String>();
		divIds.add("templateone_logo");
		divIds.add("templateone_footer_info");
		divIds.add("templateone_home_one");
		divIds.add("templateone_home_two");
		divIds.add("templateone_home_three");
		divIds.add("templateone_home_four");
		template.setDivIds(divIds);
		return template;
		
	}
}
