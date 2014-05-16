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

//@Scope("prototype") 原型模式：每次请求生成一个controller对象，默认为单例模式，只有一个对象，单例模式下不能有类变量
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
	
	//重定向用法
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
	 * SpringMVC ajax操作
	 * jar包冲突：jackson-mapper-asl-1.9.*.jar与spring自带的jar包
	 * com.springsource.org.codehaus.jackson.mapper-1.4.2.jar冲突，
	 * 从而在项目初始中抛出异常java.lang.NoSuchMethodErro。
	 * 解决方法是将com.springsource.org.codehaus.jackson.mapper-1.4.2.jar移除。

      *  spring MVC在使用jackson时一定要注意：
      *  jackson-core-asl-1.9.*.jar和jackson-mapper-asl-1.9.*.jar的版本号一定要相同，
      *  如：同时使用1.9.5。
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
	
	public static CategoryDataset createDataset(int prenum) //创建柱状图数据集
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(10,"a","管理人员");
        dataset.setValue(20,"b","市场人员");
        dataset.setValue(prenum,"c","开发人员");
        dataset.setValue(15,"d","销售人员");
        dataset.setValue(15,"e","销售人员");
        dataset.setValue(15,"f","其他人员");
        return dataset;
    }
    
    public static JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        JFreeChart chart=ChartFactory.createBarChart3D("hi", "人员分布", 
                "人员数量", dataset, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle("某公司组织结构图",new Font("宋体",Font.BOLD+Font.ITALIC,20)));//可以重新设置标题，替换“hi”标题
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        
        plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();
         //水平底部列表
         domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));
         //水平底部标题
         domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));
         //垂直标题
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
         rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
          chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        
        
        return chart;
    }
    
  //**文件下载
	
  	@RequestMapping(value = "/download.do",method = RequestMethod.GET)
      public ResponseEntity<byte[]> downFile(HttpServletRequest req)  {
          String downFileName = "";
          //HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
          String path = req.getSession().getServletContext().getRealPath("/datapic");
          System.out.println(path);
          try {
              downFileName = URLEncoder.encode("messages.properties", "UTF-8");//转码解决IE下文件名乱码问题
          } catch (Exception e) {
              e.printStackTrace();
          }
          //Http响应头
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          headers.setContentDispositionFormData("attachment", downFileName);

          try {
              return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(path + "/" + downFileName)),
                                                headers,
                                                HttpStatus.OK);
          } catch (Exception e) {
              e.printStackTrace();
              //日志
              //TODO
          }
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          headers.setContentDispositionFormData("attachment", "error.txt");
          return new ResponseEntity<byte[]>("文件不存在.".getBytes(), headers, HttpStatus.OK);
      }
}
