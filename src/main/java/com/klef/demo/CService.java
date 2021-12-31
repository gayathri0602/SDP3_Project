package com.klef.demo;

import java.util.List;
import java.util.Optional;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Image;



import java.util.ArrayList;


import org.springframework.web.servlet.ModelAndView;




import com.klef.demo.Student;

@Service
public class CService {
	
	 private static final Logger logger = LoggerFactory.getLogger("CService.class");
	 
	 private static final String PDF_RESOURCES = "";
	    private SpringTemplateEngine templateEngine;
	
	@Autowired
	ARepository admrepository;
	@Autowired
	StudentRepository sturepo;
	@Autowired
	ProfileRepository dao2;
	@Autowired
    ProfileRepository dao;
    @Autowired
    AddJobRepository jobrepo;
    @Autowired
    ORepository orgrepository;
    @Autowired
    ApplyJobRepository dbFileRepository;
    @Autowired
    AddJobRepository addjob;
    @Autowired
    SendAcceptedMail sendamail;
    @Autowired
    SendRejectedMail sendrmail;
    @Autowired
    ReqDelJobsRepository reqdel;

  
    public CService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
	
	
	
	public boolean validate(String uname,String password)
	  {
	    try
	    {
	    Configuration cfg = new Configuration();
	    cfg.configure("hibernate.cfg.xml");
	    SessionFactory sf = cfg.buildSessionFactory();
	    Session session = sf.openSession();
	    //String uname=adm.getUname();
	   // String pass=adm.getPassword();
	    
	    Query qry = session.createQuery("from AdminClass where uname='"+uname+"' and password='"+password+"' ");
	    int flag=0;
	    List<AdminClass> l1 = qry.list();
	    /*for(AdminClass admin : l1)
	    {
	      System.out.println(adm.getUname());
	      if(admin.getUname().equals(adm.getUname())&&admin.getPassword().equals(adm.getPassword()))
	        flag=1;
	     }*/
	    System.out.println(l1.size()+" "+uname+" "+password);
	    Transaction txt = session.beginTransaction();
	    txt.commit();
	    session.close();
	    sf.close();
	    if(l1.size()>0)
	      return true;
	    else
	      return false;
	    }
	    catch(Exception e)
	    {
	      System.out.println(e);
	    }
	    return false;
	  }
	 public boolean checklogin(String email,String password)
	 {
		  try
		    {
		    Configuration cfg = new Configuration();
		    cfg.configure("hibernate.cfg1.xml");
		    SessionFactory sf = cfg.buildSessionFactory();
		    Session session = sf.openSession();
		    Query qry = session.createQuery("from Student where email='"+email+"' and password='"+password+"' ");
		    int flag=0;
		    List<AdminClass> l1 = qry.list();
		    System.out.println(l1.size()+" "+email+" "+password);
		    Transaction txt = session.beginTransaction();
		
		    if(l1.size()>0)
		    {
		    	Query qry2=session.createQuery("update Student set status=:s1 where email=:e1");
				qry2.setString("s1","TRUE");
		    	qry2.setString("e1",email);
		    	System.out.println(email);
				int n1=qry2.executeUpdate();
				System.out.println(n1+"Record Updated");
			    txt.commit();
				 session.close();
				    sf.close();
		    	return true;
		    }
		    else
		    {
		    	 session.close();
				    sf.close();
		      return false;
		    }
		    }
		    catch(Exception e)
		    {
		      System.out.println(e);
		    }
		    return false;
		 
	 }
	 public List<Student> getloginstudentrecords()
	 {

		    Configuration cfg = new Configuration();
		    cfg.configure("hibernate.cfg1.xml");
		    SessionFactory sf = cfg.buildSessionFactory();
		    Session session = sf.openSession();
		    Query qry4=session.createQuery("from Student where status=:v");
		      qry4.setParameter("v","TRUE");
		     List<Student> list4=qry4.list();
		     return list4;
	 }  
	 
	 public List<Student> getnotloginstudentrecords()
	 {

		    Configuration cfg = new Configuration();
		    cfg.configure("hibernate.cfg1.xml");
		    SessionFactory sf = cfg.buildSessionFactory();
		    Session session = sf.openSession();
		    Query qry4=session.createQuery("from Student where status IS NULL ");
		     // qry4.setParameter("v","TRUE");
		     List<Student> list4=qry4.list();
		     return list4;
	 }  
	 
	 public  void addstudentrecord(Student stu, String pass)
	  {
	    stu.setPassword(pass);
	    sturepo.save(stu);
	  }
	
	  
	  public Profile getuview(Long id)
	  {
	  	System.out.println("Service Class");
	  	System.out.println("ID ID: "+id);
	      Optional findById = dao2.findById(id);
	      if (findById.isPresent()) {
	         Profile getuv= (Profile) findById.get();
	          return getuv;
	      } 
	      else {
	          return null;
	      }
	  }
	  
	  
	  public int updateprofile(Profile model,Long id) 
	  {
		  try
		  {
		  Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg2.xml");
			SessionFactory sf=cfg.buildSessionFactory();// Transient State
			Session session=sf.openSession(); 
			System.out.println("Service Class:     "+model.getAemail());
			System.out.println(id+"S"+model.getId());
			Query qry3=session.createQuery("update Profile set aemail=:v1,bpwd=:v16,cname=:v2,dgender=:v17,esname=:v3,fcname=:v4,gscgpa=:v5,hccgpa=:v6,igcname=:v7,jbranch=:v8,kgcgpa=:v9,lspec=:v10,mskills=:v11,nproject=:v12,oexperience=:v13,pfile=:v14,qspecs=:v15 where id=:v67");
			qry3.setParameter("v1",model.getAemail());
			qry3.setParameter("v16",model.getBgyear());
			qry3.setParameter("v2",model.getCname());
			qry3.setParameter("v17",model.getDgender());
			qry3.setParameter("v3",model.getEsname());
			qry3.setParameter("v4",model.getFcname());
			qry3.setParameter("v5",model.getGscgpa());
			qry3.setParameter("v6",model.getHccgpa());
			qry3.setParameter("v7",model.getIgcname());
			qry3.setParameter("v8",model.getJbranch());
			qry3.setParameter("v9",model.getKgcgpa());
			qry3.setParameter("v10",model.getLspec());
			qry3.setParameter("v11",model.getMskills());
			qry3.setParameter("v12",model.getNproject());
			qry3.setParameter("v13",model.getOexperience());
			qry3.setParameter("v14",model.getPfile());
			qry3.setParameter("v15",model.getQspecs());
			qry3.setParameter("v67", id);
			
			
			Transaction transaction=session.beginTransaction();
			int n1=qry3.executeUpdate();
			System.out.println("hello"+n1);
			System.out.println(n1+"Record Updated"); 
			transaction.commit();
			session.close(); // Detached State
			sf.close();
			return n1;
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		return 0;
	}
	  
	  

	  
	  
	  
	  // Change Password
	  public int changepassword(String email,String opassword,String npassword) 
	  {
	    try
	    {
	      Configuration cfg=new Configuration();
	    cfg.configure("hibernate.cfg1.xml");
	    SessionFactory sf=cfg.buildSessionFactory();// Transient State
	    Session session=sf.openSession(); 
	  
	    Query qry3=session.createQuery("update Student set password=:v2 where email=:v3 and password=:v1");
	    qry3.setParameter("v1",opassword);
	    qry3.setParameter("v2",npassword);
	    qry3.setParameter("v3",email);
	    Transaction transaction=session.beginTransaction();
	    int n1=qry3.executeUpdate();
	    System.out.println("hello"+n1);
	    System.out.println(n1+"Record Updated"); 
	    transaction.commit();
	    session.close(); // Detached State
	    sf.close();
	    return n1;
	    }
	    catch(Exception e)
	    {
	      System.out.println(e);
	    }
	  return 0;
	}
	  
	  
	
	  
	  public int saveProfile(Profile model) {
	        try {
	            dao.save(model);
	            return 1;
	        } catch (Exception e) {
	            logger.error("ERROR", e);
	            return 0;
	        }
	    }
	    public List<Profile> getImages(String email) {
	    	
	    	 Configuration cfg=new Configuration();
	 		cfg.configure("hibernate.cfg2.xml");
	 		SessionFactory sf=cfg.buildSessionFactory();// Transient State
	 		Session session=sf.openSession(); 
	 		Query qry3=session.createQuery("from Profile where aemail=:v3");
	 		qry3.setParameter("v3",email);
	 		Transaction transaction=session.beginTransaction();
	 		//int n1=qry3.executeUpdate();
	 		List<Profile> list=qry3.list();
	 		System.out.println(list.size());
	 		//System.out.println("hello"+n1);
	 		//System.out.println(n1+"Record Updated"); 
	 	//	List<Profile> imageList= new ArrayList<Profile>();
     //     for (Profile m : new ArrayList<Profile>(list)) {
       //   	byte[] encode = java.util.Base64.getEncoder().encode(m.getPfile());
      //    	m.add("pfile", new String(encode, "UTF-8"));
       //   }
	 		return list;
}
	    
	    
	    public Profile getResume(Long id) {
	        Optional findById = dao.findById(id);
	        if (findById.isPresent()) {
	           Profile getResumeDetails = (Profile) findById.get();
	    logger.info("id= " + getResumeDetails.getId()+ " aemail= " + getResumeDetails.getAemail(),"cname="+getResumeDetails.getCname(),"esname="+getResumeDetails.getEsname(),"fcname="+getResumeDetails.getFcname(),"gscgpa="+getResumeDetails.getGscgpa(),"hccgpa="+getResumeDetails.getHccgpa(),"igcname="+getResumeDetails.getIgcname(),"jbranch="+getResumeDetails.getJbranch(),"kgcgpa="+getResumeDetails.getKgcgpa(),"lspec="+getResumeDetails.getLspec(),"mskills="+getResumeDetails.getMskills(),"nproject="+getResumeDetails.getNproject(),"oexperience="+getResumeDetails.getOexperience(),"qspecs="+getResumeDetails.getQspecs());
	            return getResumeDetails;
	        } 
	        else {
	            return null;
	        }
	    }
	    
	    // View Students
	    
	    public List<Student> getallstudentrecords()
	    {
	      return (List<Student>)sturepo.findAll();
	    }
	    
	    //Adding Organization
	    
	    public int saveorganization(Organization model) {
	        try {
	            orgrepository.save(model);
	            return 1;
	        } catch (Exception e) {
	            logger.error("ERROR", e);
	            return 0;
	        }
	    }
	    
	    // View Organization
	    
	    public List<Organization> getallorgrecords()
	    {
	      return (List<Organization>)orgrepository.findAll();
	    }
	    
	    public  void addjob(AddJob addjob)
	    {
	      jobrepo.save(addjob);
	    }
	    
	    public ApplyJob storeFile(MultipartFile file, String msg, String email, String fname, long stuid,String org,String status,String role) {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            ApplyJob dbFile = new ApplyJob(fileName, file.getContentType(), file.getBytes(),stuid, fname, msg, email);
	            dbFile.setOrg(org);
	            dbFile.setStatus(status);
	            dbFile.setRole(role);
	            return dbFileRepository.save(dbFile);
	        } catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

	    public ApplyJob getFile(String fileId) {
	        return dbFileRepository.findById(fileId)
	                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	    }
	    
	    
	    // View Jobs
	    
	    public List<AddJob> getjobrecords(	String email)
	    {

	     	/* Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg2.xml");
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	        Query qry=session1.createQuery("from Profile where aemail=:v1");
	        qry.setParameter("v1",email);
	        List<Profile> l=qry.list();
	        int y=0;
	        for (Profile m :l)
	        {
	        	y=m.getBgyear();
	        	break;
	        }
	        System.out.println("Apply Service"+y);
	    */
	    	Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg2.xml");
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	    	Criteria cr = session1.createCriteria(Profile.class)
	    		    .setProjection(Projections.projectionList()
	    		      .add(Projections.property("bgyear"), "bgyear"))
	    		    
	    		    .setResultTransformer(Transformers.aliasToBean(Profile.class));
	    		  cr.add(Restrictions.eq("aemail",email));

	    		  List<Profile> list = cr.list();
	    		  int yr=0;
	    	      for(Profile p:list)
	    	      {
	    	    	  yr=p.getBgyear();
	    	    	  break;
	    	    	  
	    	      }
	    	
	         Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg3.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	          Query qry4=session.createQuery("from AddJob where yr=:v1");
	          qry4.setParameter("v1", yr);
	          List<AddJob> list4=qry4.list();
	          return list4;
	    }
	    
	    
	    // View Org Job Details
	    
	    public List<AddJob> getorgjobrecords(	String email)
	    {
	    	Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg5.xml");
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	    	Criteria cr = session1.createCriteria(Organization.class)
	    			 .setProjection(Projections.projectionList()
	    	    		      .add(Projections.property("name"), "name"))
	    	    		    
	    	    		    .setResultTransformer(Transformers.aliasToBean(Organization.class));
	    		  cr.add(Restrictions.eq("email",email));

	    		  List<Organization> list = cr.list();
	    		  String gor="null";
	    	      for(Organization p:list)
	    	      {
	    	    	  gor=p.getName();
	    	    	  break;
	    	      }
	    	System.out.println(gor);
	    	System.out.println(email);
	         Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg3.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	          Query qry4=session.createQuery("from AddJob where org=:v1");
	          qry4.setParameter("v1", gor);
	          List<AddJob> list4=qry4.list();
	          return list4;
	    }
	    
	    // View Applicant Records
	    
	    public List<AddJob> getviewapplicantrecords(String email)
	    {
	    	Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg5.xml");
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	    	Criteria cr = session1.createCriteria(Organization.class)
	    			 .setProjection(Projections.projectionList()
	    	    		      .add(Projections.property("name"), "name"))
	    	    		    
	    	    		    .setResultTransformer(Transformers.aliasToBean(Organization.class));
	    		  cr.add(Restrictions.eq("email",email));

	    		  List<Organization> list = cr.list();
	    		  String gor="null";
	    	      for(Organization p:list)
	    	      {
	    	    	  gor=p.getName();
	    	    	  break;
	    	      }
	    	System.out.println(gor);
	    	System.out.println(email);
	         Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg4.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	          Query qry4=session.createQuery("from ApplyJob where org=:v1 and status=:v2");
	          qry4.setParameter("v1", gor);
	          qry4.setParameter("v2", "PENDING");
	          List<AddJob> list4=qry4.list();
	          return list4;
	    }

	    
	    // View Job Status
	    
	    public List<AddJob> viewjobstatus(String email)
	    {

	         Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg4.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	         Query qry4=session.createQuery("from ApplyJob where email=:v1");
	         qry4.setParameter("v1", email);
	          List<AddJob> list4=qry4.list();
	          return list4;
	    }
	    
	    
	    // Organization Login
	    
	    public boolean validateOrg(String uname, String pass)
	    {
	      try
	      {
	      Configuration cfg = new Configuration();
	      cfg.configure("hibernate.cfg5.xml");
	      SessionFactory sf = cfg.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      Query qry = session.createQuery("from Organization where email='"+uname+"' and password='"+pass+"' ");
	      int flag=0;
	      List<AdminClass> l1 = qry.list();
	      /*for(AdminClass admin : l1)
	      {
	        System.out.println(adm.getUname());
	        if(admin.getUname().equals(adm.getUname())&&admin.getPassword().equals(adm.getPassword()))
	          flag=1;
	       }*/
	      System.out.println(l1.size()+" "+uname+" "+pass);
	      Transaction txt = session.beginTransaction();
	      txt.commit();
	      session.close();
	      sf.close();
	      if(l1.size()>0)
	        return true;
	      else
	        return false;
	      }
	      catch(Exception e)
	      {
	        System.out.println(e);
	      }
	      return false;
	    }
	    
	    
	    public List<ApplyJob> acceptrequest(String email,String role,String org) 
	    {
	  	    Configuration cfg=new Configuration();
	  		cfg.configure("hibernate.cfg4.xml");
	  		SessionFactory sf=cfg.buildSessionFactory();// Transient State
	  		Session session=sf.openSession(); 
	  		System.out.println("Service Class");
	  		System.out.println(email);
	  		Query qry3=session.createQuery("update ApplyJob set status=:v2 where email=:v3 and role=:v1");
	  		qry3.setParameter("v1",role);
	  		qry3.setParameter("v2","ACCEPTED");
	  		qry3.setParameter("v3",email);
	  		Transaction transaction=session.beginTransaction();
	  		int n1=qry3.executeUpdate();
	  	//	transaction.commit();
	  	
	  		System.out.println(n1);
	  		
	  		Query qry4=session.createQuery("from ApplyJob where status=:v4");
	  		qry4.setParameter("v4", "ACCEPTED");
	  		List<ApplyJob> l=qry4.list();
	  	  sendamail.sendMail(role,email,org);
	  	  
			transaction.commit();
	  	    session.close();
	  	    sf.close();
	  		return l;	    
	  }
	    
	    
	    // Reject Request
	    
	    
	    public List<ApplyJob> rejectrequest(String email,String role,String org) 
	    {
	  	    Configuration cfg=new Configuration();
	  		cfg.configure("hibernate.cfg4.xml");
	  		SessionFactory sf=cfg.buildSessionFactory();// Transient State
	  		Session session=sf.openSession(); 
	  		System.out.println("Service Class");
	  		System.out.println(email);
	  		Query qry3=session.createQuery("update ApplyJob set status=:v2 where email=:v3 and role=:v1");
	  		qry3.setParameter("v1",role);
	  		qry3.setParameter("v2","REJECTED");
	  		qry3.setParameter("v3",email);
	  		Transaction transaction=session.beginTransaction();
	  		int n1=qry3.executeUpdate();
	  	//	transaction.commit();
	  	
	  		System.out.println(n1);
	  		
	  		Query qry4=session.createQuery("from ApplyJob where status=:v4");
	  		qry4.setParameter("v4", "REJECTED");
	  		List<ApplyJob> l=qry4.list();
	  		sendrmail.sendMail(role,email,org);
			transaction.commit();
	  	    session.close();
	  	    sf.close();
	  		return l;	    
	  }
	    
	    
	    // View Accepted Students
	    
	    public List<ApplyJob> viewacceptrequest(	String email) 
	    {
	    	Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg4.xml");
	    	System.out.println(email);
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	    	Criteria cr = session1.createCriteria(ApplyJob.class)
	    			 .setProjection(Projections.projectionList()
	    	    		      .add(Projections.property("org"), "org"))
	    	    		    
	    	    		    .setResultTransformer(Transformers.aliasToBean(ApplyJob.class));
	    		  cr.add(Restrictions.eq("email",email));

	    		  List<ApplyJob> list = cr.list();
	    		  String org="null";
	    	      for(ApplyJob p:list)
	    	      {
	    	    	  org=p.getOrg();
	    	    	  break;
	    	      }
	    	System.out.println(org);
	         Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg4.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	          Query qry4=session.createQuery("from ApplyJob where org=:v1 and status=:v2");
	          qry4.setParameter("v1", org);
	          qry4.setParameter("v2", "ACCEPTED");
	          List<ApplyJob> list4=qry4.list();
	          System.out.println("SERVICE");
	          System.out.println(email);
	        
	          return list4;
	    }
	    
	    
	    // View Rejected Students
	    
	    public List<ApplyJob> viewrejectrequest(String email)
	    {
	    	Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg4.xml");
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	    	Criteria cr = session1.createCriteria(ApplyJob.class)
	    			 .setProjection(Projections.projectionList()
	    	    		      .add(Projections.property("org"), "org"))
	    	    		    
	    	    		    .setResultTransformer(Transformers.aliasToBean(ApplyJob.class));
	    		  cr.add(Restrictions.eq("email",email));

	    		  List<ApplyJob> list = cr.list();
	    		  String org="null";
	    	      for(ApplyJob p:list)
	    	      {
	    	    	  org=p.getOrg();
	    	    	  break;
	    	      }
	    	System.out.println(org);
	         Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg4.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	          Query qry4=session.createQuery("from ApplyJob where org=:v1 and status=:v2");
	          qry4.setParameter("v1", org);
	          qry4.setParameter("v2", "REJECTED");
	          List<ApplyJob> list4=qry4.list();
	          return list4;
	    }
	    
	    // View All Jobs
	    
	    public List<AddJob> getalljobrecords()
	    {
	      return (List<AddJob>)addjob.findAll();
	    }
	    
	    
	    
	    // Search by role
	    
	    
	    public List<AddJob> searchbyrole(String search,String email)
	    {
	    	Configuration cfg2=new Configuration();
	    	cfg2.configure("hibernate.cfg4.xml");
	    	SessionFactory sf1 = cfg2.buildSessionFactory();
	        Session session1 = sf1.openSession();
	    	Criteria cr = session1.createCriteria(ApplyJob.class)
	    			 .setProjection(Projections.projectionList()
	    	    		      .add(Projections.property("org"), "org"))
	    	    		    
	    	    		    .setResultTransformer(Transformers.aliasToBean(ApplyJob.class));
	    		  cr.add(Restrictions.eq("email",email));

	    		  List<ApplyJob> list = cr.list();
	    		  String org="null";
	    	      for(ApplyJob p:list)
	    	      {
	    	    	  org=p.getOrg();
	    	    	  break;
	    	      }
	    	System.out.println(org);
	    	 Configuration cfg = new Configuration();
	         cfg.configure("hibernate.cfg3.xml");
	         SessionFactory sf = cfg.buildSessionFactory();
	         Session session = sf.openSession();
	          Query qry4=session.createQuery("from AddJob where org=:v1 and role=:v2");
	          qry4.setParameter("v1", org);
	          qry4.setParameter("v2", search);
	          List<AddJob> list4=qry4.list();
	          return list4;
	    }

	    
	    // Request to delete Jobs
	    
	    public void sendrequesttodelete(int jid,String org,String role)
	    {
	    	RequesttoDeleteJobs rd=new RequesttoDeleteJobs();
	    	rd.setJid(jid);
	    	rd.setOrg(org);
	    	rd.setRole(role);
	         reqdel.save(rd);
	 		
	     }
	    
	    private String convertToXhtml(String html) throws UnsupportedEncodingException {
	    	  Tidy tidy = new Tidy();
	    	  tidy.setXHTML(true);
	    	  tidy.setIndentContent(true);
	    	  tidy.setPrintBodyOnly(true);
	    	  tidy.setInputEncoding("UTF-8");
	    	  tidy.setOutputEncoding("UTF-8");
	    	  tidy.setSmartIndent(true);
	    	  tidy.setShowWarnings(false);
	    	  tidy.setQuiet(true);
	    	  tidy.setTidyMark(false);

	    	  Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

	    	  OutputStream out = new ByteArrayOutputStream();
	    	  tidy.pprint(htmlDOM, out);
	    	  return out.toString();
	    	}
	    

	    public File generatePdf(@PathVariable("id") long id) throws IOException, DocumentException {
	        Context context = getContext(id);
	        System.out.println("Generate PDF="+id);
	        String html = loadAndFillTemplate(context);
	        String xhtml = convertToXhtml(html);
	        return renderPdf(xhtml);
	    }


	    private File renderPdf(String html) throws IOException, DocumentException {
	        File file = File.createTempFile("students", ".pdf");
	        OutputStream outputStream = new FileOutputStream(file);
	        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
	        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
	        renderer.layout();

	        renderer.createPDF(outputStream);
	        outputStream.close();
	        file.deleteOnExit();
	        return file;
	    }
	
	    private Context getContext(@PathVariable("id") long id) {
	        Context context = new Context();
	        context.setVariable("students", getResume(id));
	        System.out.println("PDF SEVICE="+id);
	        return context;
	    }

	    private String loadAndFillTemplate(Context context) 
	    {
	        return templateEngine.process("thymeleaf/pdf_resume", context);
	    }

	    
   
	
}