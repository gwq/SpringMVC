package com.app.user;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.service.UserService;

//@Scope("prototype") ԭ��ģʽ��ÿ����������һ��controller����Ĭ��Ϊ����ģʽ��ֻ��һ�����󣬵���ģʽ�²����������
@Controller  
@RequestMapping("/user")  
public class userController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/configSet.do", method = RequestMethod.POST)  
    public ModelAndView userInfconfigSet(@RequestParam("name") String name){  
		
        ModelAndView mav = new ModelAndView(); 
        mav.addObject("methodtype", "POST Method");
        mav.setViewName("/user/userconfig");  
        return mav;  
    }  
     
	@RequestMapping(value = "/config.do", method = RequestMethod.GET)  
    public ModelAndView userInfo(){  
        ModelAndView mav = new ModelAndView(); 
        System.out.println("UserService Result:"+userService.userNum());
        mav.setViewName("/user/userconfig");
        mav.addObject("methodtype", "GET Method");
        return mav;  
    }  
	
	@RequestMapping(value = "/usertest.do", method = RequestMethod.GET)  
    public ModelAndView userInfotest(){  
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/usertest");
        return mav;  
    }  
	
	//�ض����÷�
	@RequestMapping(value = "/useredirect.do", method = RequestMethod.GET)  
    public String useredirect(){  
       return "redirect:/user/config.do";
    }  
	
	@RequestMapping(value = "/freechart.do", method = RequestMethod.GET)  
    public ModelAndView freechart(HttpServletRequest request,
            HttpServletResponse response){  
		
		JFreeChart chart =createChart(createDataset(40));
		java.io.FileOutputStream out1;
		try {
			out1 = new java.io.FileOutputStream(request.getSession().getServletContext().getRealPath("/")+"/datapic/areachart.png");
			org.jfree.chart.ChartUtilities.writeChartAsPNG(out1, chart, 600, 360);
	        out1.flush();
	        out1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
		
		ModelAndView mav = new ModelAndView();  
        mav.setViewName("/user/freechart");  
        return mav;  
    }  
	
	/*
	 * SpringMVC ajax����
	 * jar����ͻ��jackson-mapper-asl-1.9.*.jar��spring�Դ���jar��
	 * com.springsource.org.codehaus.jackson.mapper-1.4.2.jar��ͻ��
	 * �Ӷ�����Ŀ��ʼ���׳��쳣java.lang.NoSuchMethodErro��
	 * ��������ǽ�com.springsource.org.codehaus.jackson.mapper-1.4.2.jar�Ƴ���

      *  spring MVC��ʹ��jacksonʱһ��Ҫע�⣺
      *  jackson-core-asl-1.9.*.jar��jackson-mapper-asl-1.9.*.jar�İ汾��һ��Ҫ��ͬ��
      *  �磺ͬʱʹ��1.9.5��
	 */
	@RequestMapping(value = "/ajaxpost.do", method = RequestMethod.POST) 
	@ResponseBody 
    public Map<String, Object> ajaxpost(@RequestParam("name") String name){ 
		System.out.println(name);
		Map<String, Object> modelMap = new HashMap<String, Object>();  
	    modelMap.put("total", "1");  
	    //modelMap.put("data", list);  
	    modelMap.put("success", name + "ajax post success!!");
       return modelMap;
    }  
	
	@RequestMapping(value = "/chartchange.do", method = RequestMethod.POST) 
	@ResponseBody 
    public Map<String, Object> jfreechartchange(HttpServletRequest request,@RequestParam("pernum") String name){ 
		
		System.out.println("pernum:"+name);
		JFreeChart chart =createChart(createDataset(Integer.valueOf(name)));
		java.io.FileOutputStream out1;
		try {
			out1 = new java.io.FileOutputStream(request.getSession().getServletContext().getRealPath("/")+"/datapic/areachart.png");
			org.jfree.chart.ChartUtilities.writeChartAsPNG(out1, chart, 600, 360);
	        out1.flush();
	        out1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Map<String, Object> modelMap = new HashMap<String, Object>();  
	    modelMap.put("total", "1");  
	    //modelMap.put("data", list);  
	    modelMap.put("success", "JFreeChart Change success!!");
       return modelMap;
    }  
	
	public static CategoryDataset createDataset(int prenum) //������״ͼ���ݼ�
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(10,"a","������Ա");
        dataset.setValue(20,"b","�г���Ա");
        dataset.setValue(prenum,"c","������Ա");
        dataset.setValue(15,"d","������Ա");
        dataset.setValue(15,"e","������Ա");
        dataset.setValue(15,"f","������Ա");
        return dataset;
    }
    
    public static JFreeChart createChart(CategoryDataset dataset) //�����ݼ�����һ��ͼ��
    {
        JFreeChart chart=ChartFactory.createBarChart3D("hi", "��Ա�ֲ�", 
                "��Ա����", dataset, PlotOrientation.VERTICAL, true, true, false); //����һ��JFreeChart
        chart.setTitle(new TextTitle("ĳ��˾��֯�ṹͼ",new Font("����",Font.BOLD+Font.ITALIC,20)));//�����������ñ��⣬�滻��hi������
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//���ͼ���м䲿�֣���plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//��ú�����
        categoryAxis.setLabelFont(new Font("΢���ź�",Font.BOLD,12));//���ú���������
        
        plot=chart.getCategoryPlot();//��ȡͼ���������
        CategoryAxis domainAxis=plot.getDomainAxis();
         //ˮƽ�ײ��б�
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));
         //ˮƽ�ײ�����
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));
         //��ֱ����
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        
        
        return chart;
    }
    
  //**�ļ�����
	
  	@RequestMapping(value = "/download.do",method = RequestMethod.GET)
      public ResponseEntity<byte[]> downFile(HttpServletRequest req)  {
          String downFileName = "";
          //HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
          String path = req.getSession().getServletContext().getRealPath("/datapic");
          System.out.println(path);
          try {
              downFileName = URLEncoder.encode("messages.properties", "UTF-8");//ת����IE���ļ�����������
          } catch (Exception e) {
              e.printStackTrace();
          }
          //Http��Ӧͷ
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          headers.setContentDispositionFormData("attachment", downFileName);

          try {
              return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(path + "/" + downFileName)),
                                                headers,
                                                HttpStatus.OK);
          } catch (Exception e) {
              e.printStackTrace();
              //��־
              //TODO
          }
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          headers.setContentDispositionFormData("attachment", "error.txt");
          return new ResponseEntity<byte[]>("�ļ�������.".getBytes(), headers, HttpStatus.OK);
      }
}
