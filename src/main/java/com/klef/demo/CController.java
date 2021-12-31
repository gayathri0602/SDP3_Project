package com.klef.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.mail.MessagingException;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.RequestMethod;


import com.lowagie.text.DocumentException;



@RestController
public class CController {
	private static final Logger logger = LoggerFactory.getLogger(CController.class);

    @Autowired
    CService cservice;
    @Autowired
    SendEmail sndmail;
    @Autowired
    SendMailOrg sendmail;
    
    
    @RequestMapping(value="/index")
  public ModelAndView index()
  {
    ModelAndView mv=new ModelAndView();
    mv.setViewName("index");
    return mv;
  }
    
    @GetMapping("/appjob/{org}/{role}")
    public ModelAndView appjob(@PathVariable("org") String org,@PathVariable("role") String role)
    {
    	System.out.println(org);
    	   System.out.println("Apply Job role"+role);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("ApplyJob");
      mv.addObject("org",org);
      mv.addObject("role",role);
   
      return mv;
    }
    
    
    @RequestMapping(value="/viewpdf")
     public ModelAndView view()
     {
       ModelAndView mv=new ModelAndView();
       mv.setViewName("view");
       return mv;
     }


    @PostMapping("/uploadFile/{org}/{role}")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("message") String message, @RequestParam("email") String email, @RequestParam("full_name") String fname,@RequestParam("stuid") long stuid,@PathVariable String org,@PathVariable String role) {
        String status="PENDING";
    	ApplyJob dbFile = cservice.storeFile(file,message, email, fname, stuid,org,status,role);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(dbFile.getId()).toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        ApplyJob dbFile = cservice.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    
    // VIEW JOB
    
    @GetMapping("/viewjobs/{email}")
    public ModelAndView viewemployees(@PathVariable String email)
    {
      Profile p=new Profile();
      List<AddJob> jobs=cservice.getjobrecords(email);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("ViewJobs");
      mv.addObject("jobdata",jobs);
      return mv;
    }
    
    // View All Jobs
    
    @GetMapping("/viewalljobs")
    public ModelAndView viewalljobs()
    {
      Profile p=new Profile();
      List<AddJob> jobs=cservice.getalljobrecords();
      ModelAndView mv=new ModelAndView();
      mv.setViewName("ViewAJob");
      mv.addObject("jobdata",jobs);
      return mv;
    }
    
    // View Status 
    
    @GetMapping("/viewstatus/{email}")
    public ModelAndView viewstatus(@PathVariable String email)
    {
      List<AddJob> jobs=cservice.viewjobstatus(email);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("ViewStatus");
      mv.addObject("jobdata",jobs);
      return mv;
    }
    
    
    @GetMapping("/vieworgjobs/{email}")
    public ModelAndView vieworgjobs(@PathVariable String email)
    {
      Profile p=new Profile();
      List<AddJob> jobs=cservice.getorgjobrecords(email);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("ViewOrgJobs");
      mv.addObject("jobdata",jobs);
      return mv;
    }
    
    // View Applicants
    
    @GetMapping("/viewapplicants/{email}")
    public ModelAndView viewapplicants(@PathVariable String email)
    {
      List<AddJob> jobs=cservice.getviewapplicantrecords(email);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("ViewApplicants");
      mv.addObject("jobdata",jobs);
      return mv;
    }
    
    // Organization Login
    
    @GetMapping("/orglogin")
    public ModelAndView orgloginpage()
    {
      ModelAndView mv=new ModelAndView();
      mv.setViewName("OrgLogin");
      return mv;
    }

    // Check Org Login 
    
    @PostMapping("/checkorglogin")// resp method gets executed, this is known as Handler Mapping
    public ModelAndView orgLogin(@RequestParam("username") String uname,@RequestParam("pass") String pass)
    {
      boolean val=cservice.validateOrg(uname, pass);
      ModelAndView mv=new ModelAndView();
      if(val==true)
      {
        mv.setViewName("OrgHome");
        mv.addObject("email",uname);
      }
      else
      {
        mv.setViewName("adminloginfail");
      }
      return mv;
    }
    
    // Accept Student Request
    
    @GetMapping("/acceptrequest/{email}/{role}/{org}")
    public ModelAndView acceptrequest(@PathVariable String email,@PathVariable String role,@PathVariable String org)
    {
    	List<ApplyJob> students= cservice.acceptrequest(email,role,org);
    	System.out.println("Controller Class");
    	System.out.println(email);
    	System.out.println(role);
    	 System.out.println(org);
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("AcceptedStudents");
	  	  mv.addObject("jobdata",students);
	  	  return mv;
    }
    
    // Rejected Students
    
    @GetMapping("/rejectrequest/{email}/{role}/{org}")
    public ModelAndView rejectrequest(@PathVariable String email,@PathVariable String role,@PathVariable String org)
    {
    	List<ApplyJob> students= cservice.rejectrequest(email,role,org);
    	System.out.println("Controller Class");
    	System.out.println(email);
    	System.out.println(role);
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("RejectedStudents");
	  	  mv.addObject("jobdata",students);
	  	  return mv;
    }
    
    // View Accepted Students
    @GetMapping("/viewacceptedstudents/{email}")
    public ModelAndView viewacceptedstudents(@PathVariable String email) 
    {
    	List<ApplyJob> students= cservice.viewacceptrequest(email);
    	System.out.println("Controller Class");
    	System.out.println(email);
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("AcceptedStudents");
	  	  mv.addObject("jobdata",students);
	  	  return mv;
    }
    
    // View Rejected Students
    
    @GetMapping("/viewrejectedstudents/{email}")
    public ModelAndView viewrejectedstudents(@PathVariable String email)
    {
    	List<ApplyJob> students=cservice.viewrejectrequest(email);
    	System.out.println("Controller Class");
    	System.out.println(email);
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("RejectedStudents");
	  	  mv.addObject("jobdata",students);
	  	  return mv;
    }
    
    // Search by Role
    
    @PostMapping("/searchbyrole/{email}")// resp method gets executed, this is known as Handler Mapping
    public ModelAndView searchbyrole(@RequestParam("search") String search,@PathVariable String email)
    {
    	  List<AddJob> jobs=cservice.searchbyrole(search,email);
          ModelAndView mv=new ModelAndView();
          mv.setViewName("ViewOrgJobs");
          mv.addObject("jobdata",jobs);
          return mv;
    	
    }
    
    
    // Request to delete Jobs
    
    @GetMapping("/sendrequest/{id}/{org}/{role}")
    public ModelAndView sendreqttodelete(@PathVariable int id,@PathVariable String org,@PathVariable String role)
    {
    	cservice.sendrequesttodelete(id,org,role);
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("userhome");
    	return mv;
    	
    }
    
    @RequestMapping(value="/userlogin")
	public ModelAndView userlogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("userlogin");
		return mv;
	}
	@RequestMapping(value="/profile")
	public ModelAndView profile()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("profile");
		return mv;
	}
	
	
	@RequestMapping(value="/userhome")
	public ModelAndView userhome()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("userhome");
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/viewprofile")
	public ModelAndView viewprofile()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("viewprofile");
		return mv;
	}
	@RequestMapping(value="/userhome1")
	public ModelAndView userhome1()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("userhome1");
		return mv;
	}
	@RequestMapping(value="/resume")
	public ModelAndView resume()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("resumee");
		return mv;
	}
	@RequestMapping(value="/checkstudents1")
	public ModelAndView checkstudents()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("checkstudents");
		return mv;
	}
	
	// Login View Students
	
	 @GetMapping("/loginviewstudents")
	    public ModelAndView viewemployees()
	    {
	  	  List<Student> students= cservice.getloginstudentrecords();
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("ViewStudents");
	  	  mv.addObject("studentdata",students);
	  	  return mv;
	    }
	 
	 // Not Login Students
	 
	 @GetMapping("/loginnotviewstudents")
	    public ModelAndView viewnotloginstudents()
	    {
	  	  List<Student> students= cservice.getnotloginstudentrecords();
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("ViewStudents");
	  	  mv.addObject("studentdata",students);
	  	  return mv;
	    }
	 
	 // Add Jobs
	 
	 
	 @GetMapping("/addjob/{email}")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView addjob(@PathVariable String email)
	  {
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("AddJob");
	    mv.addObject(email);
	    return mv;
	  }
	 
	 @PostMapping("/submitjobdata")
	  public ModelAndView submitjobdata(@ModelAttribute("job") AddJob job,@RequestParam("org") String org, @RequestParam("jtitle") String jtitle, @RequestParam("loc") String loc, @RequestParam("tp") int tp, @RequestParam("amt") double amt, @RequestParam("yr") int yr)//mthod name can be anything
	  {
	    
	    AddJob add=new AddJob();
	    add.setOrg(org);
	    add.setRole(jtitle);
	    add.setLoc(loc);
	    add.setTp(tp);
	    add.setAmt(amt);
	    add.setYr(yr);
	    
	    cservice.addjob(add);
	    
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("AddSuccess");
	    return mv;
	  }
	 
	 
	 // ORGANIZATION HOME PAGE
	 
	 @RequestMapping(value="/orghome")
		public ModelAndView orghome()
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("orghome");
			return mv;
		}
	 
	 @GetMapping("/home")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView home()
	  {
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("home");
	    return mv;
	  }
	  
	  
	  


	  @PostMapping("/checkuser")
	  public ModelAndView auth1(@RequestParam("uname") String username,@RequestParam("pwd") String password,HttpServletRequest request )
	  {
	    HttpSession session=request.getSession(); // creating session variable as same as servlet
	  //uname and pwd will have values given in form
	  // check login logic using HQL
	    ModelAndView mv=new ModelAndView();
	    System.out.println(username);
	    if((username.equals("gayathri")&&password.equals("gayathri"))||(username.equals("sravani")&&password.equals("sravani"))||(username.equals("supraja")&&password.equals("supraja")))
	    {
	    mv.setViewName("adminhome");
	    }
	    else
	      mv.setViewName("adminloginfail");
	    return mv;
	  // other code
	  }
	  
	  @GetMapping("/adminhome")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView addstu() 
	  {
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("adminhome");
	    return mv;
	  }
	  
	  @GetMapping("/adminlogin")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView admlogin()
	  {
	    //emp-req obj of type Employee
	    return new ModelAndView("AdminLogin1","adm",new AdminClass());
	    
	  }
	  
	  @PostMapping("/adminlogincheck")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView admlogin(@RequestParam("uname") String uname,@RequestParam("password") String password)
	  {
	    boolean val=cservice.validate(uname,password);
	    ModelAndView mv=new ModelAndView();
	    if(val==true)
	    {
	      mv.setViewName("adminhome");
	      mv.addObject("name",uname);
	    }
	    else
	    {
	      mv.setViewName("adminloginfail");
	    }
	    return mv;
	  }
	  
	  @PostMapping("/submitstudata")
	  public ModelAndView submitempdata(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("email") String email)
	  {  
		Student stu=new Student();
		stu.setId(id);
		stu.setName(name);
		stu.setEmail(email);
	    String str=sndmail.sendMail(stu); 
	    cservice.addstudentrecord(stu,str);
	    System.out.println(str);
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("AddSuccess");
	    mv.addObject("name",stu.getName());
	    return mv;
	  }
	   
	  @GetMapping("/addstudent")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView addstudent()
	  {
	    //emp-req obj of type Employee
	    return new ModelAndView("AddStudent");
	  }
	  
	  @GetMapping("/viewstu")
	  public ModelAndView viewdata()
	  {
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("ViewStudents");
	    return mv;
	  }
	  
	  
	  
	  @GetMapping("/viewstudents")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView viewstudents()
	  {
	    ModelAndView mv=new ModelAndView();
	    mv.setViewName("ViewStudents");
	    return mv;
	  }
	  
	  @PostMapping("/userlogincheck")// resp method gets executed, this is known as Handler Mapping
	  public ModelAndView userlogin(@RequestParam("aemail") String email,@RequestParam("apassword") String password)
	  {
	    boolean val=cservice.checklogin(email,password);
	    ModelAndView mv=new ModelAndView();
	    if(val==true)
	    {
	      mv.setViewName("userhome");
	      mv.addObject("email",email);
	    }
	    else
	    {
	      mv.setViewName("adminloginfail");
	    }
	    return mv;
	  }
	  
	  // Update Profile View
	  
	  @GetMapping("/updateprofileview/{id}")
	  public ModelAndView updateprofile(@PathVariable String id, Model model)
	  {
		  ModelAndView mv=new ModelAndView();
	  	 try 
	  	 {
		            logger.info("Id= " + id);
		            Profile uv= cservice.getuview(Long.parseLong(id));
		            System.out.println(id);
		            System.out.println(uv.getAemail());
		            System.out.println("Controller Class");
		            model.addAttribute("id", uv.getId());
		            model.addAttribute("aemail",uv.getAemail());
		            model.addAttribute("cname",uv.getCname());
		            model.addAttribute("esname", uv.getEsname());
		            model.addAttribute("fcname",uv.getFcname());
		            model.addAttribute("gscgpa",uv.getGscgpa());
		            model.addAttribute("hccgpa",uv.getHccgpa());
		            model.addAttribute("igcname",uv.getIgcname());
		            model.addAttribute("jbranch",uv.getJbranch());
		            model.addAttribute("kgcgpa",uv.getKgcgpa());
		            model.addAttribute("lspec",uv.getLspec());
		            model.addAttribute("mskills",uv.getMskills());
		            model.addAttribute("nproject",uv.getNproject());
		            model.addAttribute("oexperience",uv.getOexperience());
		            model.addAttribute("qspecs",uv.getQspecs());
		           
		            byte[] encode = java.util.Base64.getEncoder().encode(uv.getPfile());
		            model.addAttribute("pfile", new String(encode, "UTF-8"));
		            mv.setViewName("updateprofile");
		        } catch (Exception e) {
		            logger.error("Error", e);
		            model.addAttribute("message", "Error in getting image");
		            mv.setViewName("adminloginfail");
		        }
	  	 return mv;
	  }
	  
	  
	  @PostMapping("/updateprofilecheck/{id}")
	  public String fileUpload(@PathVariable String id,@RequestParam("email") String email,@RequestParam("bgyear") int bgyear,@RequestParam("name") String name,@RequestParam("gender") String gender,@RequestParam("sname") String sname,@RequestParam("cname") String cname, @RequestParam("scgpa") double scgpa,@RequestParam("ccgpa") double ccgpa,@RequestParam("gcname") String gcname,@RequestParam("branch") String branch,@RequestParam("gcgpa") double gcgpa,@RequestParam("spec") String spec,@RequestParam("skills") String skills,@RequestParam("project") String project,@RequestParam("experience") String experience,@RequestParam("file") MultipartFile file,@RequestParam("specs") String specs) {
	      try {
	          logger.info("Name= " + name);
	          byte[] file1 = file.getBytes();
	          Profile model = new Profile(email,bgyear,name,gender,sname,cname,scgpa,ccgpa,gcname,branch,gcgpa,spec,skills,project,experience,file1,specs);
	          int saveProfile = cservice.updateprofile(model,Long.parseLong(id));
	          if (saveProfile == 1) {
	              return "success";
	          } 
	          else 
	          {
	              return "error";
	          }
	      } catch (Exception e) {
	         // logger.error("ERROR", e);
	          return "error";
	      }
	  }
	  
	  
	  // Change Password View
	  
	  
	  @GetMapping("/changepasswordview/{email}")
	  public ModelAndView changepasswordview(@PathVariable String email)
	  {
		  ModelAndView mv=new ModelAndView();
	     
	              System.out.println("Controller Class");
	              mv.setViewName("changepassword");
	              mv.addObject("email",email); 
	              
	          return mv;
	  }
	  
	  
	  
	// Change Password
	  
	  
	  @PostMapping("/changepassword/{email}")
	  public ModelAndView changepassword(@PathVariable String email,@RequestParam("oldpassword") String opassword,@RequestParam("newpassword") String npassword) 
	  {
		  ModelAndView mv=new ModelAndView();
	          Student stu= new Student();
	          int saveProfile = cservice.changepassword(email,opassword,npassword);
	          if (saveProfile == 1) 
	              mv.setViewName("userhome");
	        
	          
	          return mv;
	     
	  }
	  @PostMapping("/fileupload")
	    public ModelAndView fileUpload(RedirectAttributes redirectAttributes,@RequestParam("email") String email,@RequestParam("gyear") int gyear,@RequestParam("name") String name,@RequestParam("gender") String gender,@RequestParam("sname") String sname,@RequestParam("cname") String cname, @RequestParam("scgpa") double scgpa,@RequestParam("ccgpa") double ccgpa,@RequestParam("gcname") String gcname,@RequestParam("branch") String branch,@RequestParam("gcgpa") double gcgpa,@RequestParam("spec") String spec,@RequestParam("skills") String skills,@RequestParam("project") String project,@RequestParam("experience") String experience,@RequestParam("file") MultipartFile file,@RequestParam("specs") String specs) {
		  ModelAndView mv = null;
	        try {
	            logger.info("Name= " + name);
	            byte[] file1 = file.getBytes();
	            
	            Profile model = new Profile(email,gyear,name,gender,sname,cname,scgpa,ccgpa,gcname,branch,gcgpa,spec,skills,project,experience,file1,specs);
	            Date curr_date = new Date(System.currentTimeMillis());
	              model.setDt(curr_date.toString());
	            int saveProfile = cservice.saveProfile(model);
	            if (saveProfile == 1) {
	            	redirectAttributes.addAttribute("email", email);
	            	mv=new ModelAndView("redirect:/getDetail/");
	            } else {
	            	 mv.setViewName("adminloginfail");
	            }
	        } catch (Exception e) {
	            logger.error("ERROR", e);
	            mv.setViewName("error");
	            
	        }
	        return mv;
	    }
	    @GetMapping("/getDetail/{email}")
	    public ModelAndView getDbDetils(@PathVariable String email) throws UnsupportedEncodingException {
	      
	        	
	    /*        List<Profile> imageList= new ArrayList<Profile>();
	            for (Profile m : new ArrayList<Profile>(imagesObj)) {
	                String base64Encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(m.getPfile());
	                Profile imagepath = new Profile();
	                imagepath.setPfile(base64Encoded);
	             //   imagepath.setItemName(m.getItemName());
	                imageList.add(imagepath);
	            } 
	    */        
	    	ModelAndView model=new ModelAndView();
          List<Profile> imagesObj = cservice.getImages(email);
	            System.out.println(" Controller class print :"+email);
	          //  model.addObject("id", Profile.getId());
	           // model.addObject("aemail", ((Profile) imagesObj).getAemail());
	          //  byte[] encode = java.util.Base64.getEncoder().encode(((Profile) imagesObj).getPfile());
       //  model.addObject("pfile", new String(encode, "UTF-8"));
	          
	             
	            model.addObject("profile",imagesObj);
	            //model.addObject("img");
	           model.setViewName( "viewprofile");
	            return model;
	        
		
	    }
	    
	    @GetMapping("/getresume/{id}")
	    public String getresume(@PathVariable String id, Model model) {
	        try {
	            logger.info("Id= " + id);
	            Profile imagesObj = cservice.getResume(Long.parseLong(id));
	            model.addAttribute("id", imagesObj.getId());
	            model.addAttribute("aemail", imagesObj.getAemail());
	            model.addAttribute("cname",imagesObj.getCname());
	            model.addAttribute("esname", imagesObj.getEsname());
	            model.addAttribute("fcname",imagesObj.getFcname());
	            model.addAttribute("gscgpa",imagesObj.getGscgpa());
	            model.addAttribute("hccgpa",imagesObj.getHccgpa());
	            model.addAttribute("igcname",imagesObj.getIgcname());
	            model.addAttribute("jbranch",imagesObj.getJbranch());
	            model.addAttribute("kgcgpa",imagesObj.getKgcgpa());
	            model.addAttribute("lspec",imagesObj.getLspec());
	            model.addAttribute("mskills",imagesObj.getMskills());
	            model.addAttribute("nproject",imagesObj.getNproject());
	            model.addAttribute("oexperience",imagesObj.getOexperience());
	            model.addAttribute("qspecs",imagesObj.getQspecs());
	           
	            byte[] encode = java.util.Base64.getEncoder().encode(imagesObj.getPfile());
	            model.addAttribute("pfile", new String(encode, "UTF-8"));
	            return "thymeleaf/resume";
	        } catch (Exception e) {
	            logger.error("Error", e);
	            model.addAttribute("message", "Error in getting image");
	            return "redirect:/";
	        }
	    }
	    
	    
	    @GetMapping("/students/{id}")
	    public ModelAndView studentsView(ModelAndView modelAndView ,@PathVariable("id") long id) {
	        modelAndView.addObject("students", cservice.getResume(id));
	        modelAndView.setViewName("thymeleaf/resume");
	        return modelAndView;
	    }
	    
	    
	    @RequestMapping(value="/download-pdf/{id1}",method=RequestMethod.GET)
	    public void downloadPDFResource(HttpServletResponse response,@PathVariable String id1) {
	        try {
	        	 Path file = Paths.get(cservice.generatePdf(Long.parseLong(id1)).getAbsolutePath());
	            if (Files.exists(file)) {
	                response.setContentType("application/pdf");
	                response.addHeader("Content-Disposition",
	                        "attachment; filename=" + file.getFileName());
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            }
	        } catch (DocumentException | IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    @GetMapping("/alo")
	    public ModelAndView admo()
	    {
	    	ModelAndView mv=new ModelAndView();
	    	mv.setViewName("adminlogin");
	    	return mv;
	    }

	    
	    // View Students
	    
	    @GetMapping("/dviewstudents")
	    public ModelAndView viewallstudents()
	    {
	  	  List<Student> students= cservice.getallstudentrecords();
	  	  ModelAndView mv=new ModelAndView();
	  	  mv.setViewName("ViewStudents");
	  	  mv.addObject("studentdata",students);
	  	  return mv;
	    }
	    
	    
	    // ADD ORGANIZATION
	    
	    @GetMapping("/addorg")// resp method gets executed, this is known as Handler Mapping
	    public ModelAndView addorg()
	    {
	      ModelAndView mv=new ModelAndView();
	      mv.setViewName("AddOrganization");
	      return mv;
	    }
	    
	    @PostMapping("/submitorgdata")
	    public ModelAndView Addorganization(@RequestParam("img") MultipartFile img,@RequestParam("email") String email,@RequestParam("name") String name) throws IOException {
	            logger.info("Name= " + name);
	            byte[] img1 = img.getBytes();
	           Organization org= new Organization();
	            org.setImg(img1);
	            org.setEmail(email);
	            org.setName(name);
	           System.out.println(" Controller:"+org.getEmail());
	        	String pas=sendmail.sendMail(org);
	        	
	        	 org.setPassword(pas);
	        	 System.out.println(pas);
	        	  cservice.saveorganization(org);
	        	    ModelAndView mv=new ModelAndView();
	        	    mv.setViewName("AddSuccess");
	        	    mv.addObject("name",org.getName());
	        	    return mv;
	        } 
	    // View Organization
	    
	    @GetMapping("/vieworg")
	    public ModelAndView vieworg()
	    {
	      List<Organization> org= cservice.getallorgrecords();
	      ModelAndView mv=new ModelAndView();
	      mv.setViewName("ViewOrganization");
	      mv.addObject("orgdata",org);
	      return mv;
	    }
	
	
	
}
